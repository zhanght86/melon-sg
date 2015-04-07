package secfox.soc.business.message.controller;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.MailMessage;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import secfox.soc.business.message.MailConstants;
import secfox.soc.business.message.domain.SysMail;
import secfox.soc.business.message.domain.SysMailReply;
import secfox.soc.business.message.domain.SysMailState;
import secfox.soc.business.message.query.SysMailPageQuery;
import secfox.soc.business.message.query.SysMailStatePageQuery;
import secfox.soc.business.message.service.SysMailReplyService;
import secfox.soc.business.message.service.SysMailService;
import secfox.soc.business.message.service.SysMailStateService;
import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.base.util.UUIDUtils;
import secfox.soc.melon.security.util.SecurityContextUtils;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.ActionStatus;
import secfox.soc.melon.web.WebConstants;

/**
 * @since 1.0 2014-10-20,下午3:08:37
 * 
 * @author  <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0 
 */

@Controller
@RequestMapping("/message/mail")
public class SysMailController {
	
	private SysMailService mailService;
	
	private UUIDUtils uuidUtils;
	
	private SysMailStateService mailStateService;
	
	private SysMailReplyService mailReplyService;

	private final String REPLY_PRE;
	
	private final String FORWARD_PRE;
	/**
	 * service注入
	 * @param mailService
	 */
	@Inject
	public SysMailController(SysMailService mailService, UUIDUtils uuidUtils,SysMailStateService mailStateService,SysMailReplyService mailReplyService) {
		super();
		this.mailService = mailService;
		this.uuidUtils = uuidUtils;
		this.mailStateService = mailStateService;
		this.mailReplyService = mailReplyService;
		REPLY_PRE = MessageSourceUtils.getMessage("mail.reply.pre");
		FORWARD_PRE = MessageSourceUtils.getMessage("mail.forward.pre");
	}
	
	
	//邮箱主页
	@RequestMapping("/mailList")
	public String mailList(){
		return "message.mail.mailList";
	}
	
	//邮箱登陆首页
	@RequestMapping(value="/welcome", method=RequestMethod.GET)
	public String welcome() {
		return "business/mail/home";
	}
	
	/**
	 * 跳转到账号添加页面
	 * @param account
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String create(@ModelAttribute("mail") SysMail mail ) {
		String attaches = uuidUtils.generate(SysMail.class);
		mail.setAttachId(attaches);
		mail.setSender(SecurityContextUtils.getCurrentUserInfo());
		mail.setSendTime(new Date());
		return "business/mail/edit";
	}
	
	/**
	 * 账号信息添加
	 * @param account
	 * @param result
	 * @param attribute
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(@Valid@RequestParam("type") int type,@ModelAttribute("mail") SysMail mail, BindingResult result, RedirectAttributes attribute,HttpServletRequest req) {
		if(result.hasErrors()) {
            return "business/mail/edit";
        }
		if(type == 1){//1表示发送
			//设置成功标记
			mailService.sendMail(mail);
			//添加提示信息
			ActionHint hint = ActionHint.create("message.mail.send.hint", mail.getTitle());
		    attribute.addFlashAttribute(WebConstants.ACTION_HINT, hint);
			//转到发件箱
			return "redirect:/business/mail/list/"+MailConstants.OUTBOX ;
		}else{//其他表示保存草稿
			mailService.draftMail(mail);
			//添加提示信息
			ActionHint hint = ActionHint.create("message.mail.draft.hint", mail.getTitle());
		    attribute.addFlashAttribute(WebConstants.ACTION_HINT, hint);
			return "redirect:/business/mail/list/"+MailConstants.DRAFTS ;
		}
	}
	
	/**
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, Model model) {
		SysMail mail = (SysMail) model.asMap().get("mail");
		if (null == mail) {
			mail = mailService.find(id);
		}
		model.addAttribute("mail", mail);
		return "business.mail.show";
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/remove/{type}/{id}")
	public @ResponseBody ActionStatus remove(@PathVariable("id") Long id,@PathVariable int type) {
		mailService.removeMail(id,type);
		return ActionStatus.SUCCESS;
	}
	
	/**
	 * 显示详细页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/show/{type}/{id}")
	public String detail(@PathVariable Long id,@PathVariable int type, Model model) {
		model.addAttribute("type", type);
		SysMailState sysMailState = mailStateService.find(id);
		model.addAttribute("sysMailState", sysMailState);
		//
		if(type==1){//如果是收件箱
			sysMailState.setReadState(true);
			mailStateService.merge(sysMailState);
		}else if(type==3){//如果是草稿箱
			model.addAttribute("sysmail",sysMailState.getMailId());
			return "melon.task.mailMessage.show"; 
		}
		
		return "business.mail.show";
	}
	

	/**
	 * @param stateId MessageState主键
	 * @param type 判断邮件在哪个类型中
	 */
	private void removeMessage(Long stateId, int type) {
		mailService.removeMail(stateId,type);
	}

	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/removeBatch/{type}", method = RequestMethod.POST)
	public @ResponseBody Boolean removeBatch(@RequestBody Long[] ids,@PathVariable int type) {
		mailService.removeBatch(ids);
		for(int i=0;i<ids.length;i++){
			removeMessage(ids[i],type);
		}
		return true;
	}
	
	/**
	 * 邮箱列表页
	 * @param pageQuery 
	 * @param type 1:收件箱,2:发件箱,3:草稿箱,4:已删除邮件
	 * @return
	 */
	@RequestMapping(value ="/list/{type}",method=RequestMethod.GET)
	public String toList(@ModelAttribute("pageQuery") SysMailStatePageQuery pageQuery,@PathVariable int type,Model model){
		model.addAttribute("type", type);
		return "business.mail.list";
	}
	
	/**
	 * 转到回复页面
	 * @param mailId
	 * @param model
	 * @return
	 */
	@RequestMapping("/toReply/{mailId}")
	public String toReply(@PathVariable Long mailId,Model model){
		UserInfo userInfo = SecurityContextUtils.getCurrentUserInfo();
		SysMailState mailState = mailStateService.findByMailIdAndUserId(mailId,userInfo.getUserId());
		SysMail oldMail = mailService.find(mailState.getMailId());
		//model.addAttribute("flag",flag);
		SysMail mail = replyInit(mailState.getId(), "E:"+oldMail.getSender().getUserId()+",",
				oldMail.getSender().getUsername() ,REPLY_PRE+oldMail.getTitle());
		
		model.addAttribute("mail",mail);
		
		model.addAttribute("oldContent",oldMail.getContent());
		//model.addAttribute("toReply",true);
		return "business.mail.edit"; 
	}



	/**
	 * 回复初始化
	 * @param messageStateId 消息状态id
	 * @param receiverIds 接收人id串
	 * @param receivers 接收人名称串
	 * @param title 邮件标题
	 * @return
	 */
	private SysMail replyInit(Long mStateId,
			String receiverIds, String receivers,String title) {
		SysMail mail = new SysMail();
		String attaches = uuidUtils.generate(SysMail.class);
		mail.setAttachId(attaches);
		mail.setReceivIds(receiverIds);
		mail.setReceivNames(receivers);
		mail.setStateId(mStateId);
		mail.setTitle(title);
		return mail;
	}
	
	/**
	 * 转到回复全部页面
	 * @param messageId
	 * @param model
	 * @return
	 */
	@RequestMapping("/toReplyAll/{messageId}")
	public String toReplyAll(@PathVariable Long messageId,Model model){
        UserInfo userInfo = SecurityContextUtils.getCurrentUserInfo();
		SysMailState mailState = mailStateService.findByMailIdAndUserId(messageId,userInfo.getUserId());
		SysMail oldMail = mailService.find(mailState.getMailId());

		
		SysMail mail = replyInit(mailState.getId(), "E:"+oldMail.getSender().getUserId()+","
                +oldMail.getReceivIds().replace("E:"+userInfo.getUserId()+",", ""),
                oldMail.getSender().getUsername()+","+oldMail.getReceivNames().replace(userInfo.getUsername()+",", "") 
                ,REPLY_PRE+oldMail.getTitle());
		model.addAttribute("mail",mail);
		model.addAttribute("oldContent",oldMail.getContent());
		model.addAttribute("toReply",true);
		
		return "business.mail.edit"; 
	}
	
	/**
	 * 跳转到主题邮件列表
	 * @param pageQuery
	 * @return
	 * @author <a href="mailto:wangxba@legendsec.com">王新兵</a>
	 */
	@RequestMapping(value="/subject/list", method=RequestMethod.GET)
	public String subjectList(@ModelAttribute("pageQuery") SysMailPageQuery pageQuery){
		return "business.mail.subject.list";  
	}
	
	/**
	 * 跳转到发起主题讨论
	 * @param infoSystem
	 * @return
	 */
	@RequestMapping(value = "/subject/create", method = RequestMethod.GET)
	public String toSubject(@ModelAttribute SysMail mail) {
		String attaches = uuidUtils.generate(MailMessage.class);
		mail.setAttachId(attaches);
		mail.setType(MailConstants.TYPE_SUBJECT);//主题讨论邮件
		return "business.mail.subject.edit";
	}
	
	/**
	 * 添加主题讨论
	 * @param mailMessage
	 * @param result
	 * @param attribute
	 * @return
	 */
	@RequestMapping(value = "/subject/create", method = RequestMethod.POST)
	public String subjectCreate(@Valid@ModelAttribute("sysMail") SysMail sysMail,
			BindingResult result,RedirectAttributes attribute,HttpServletRequest request) {
		if(result.hasErrors()) {
            return "business.mail.subject.edit";
        }
		//发送主题邮件
		mailService.sendSubjectMail(sysMail);
		//设置成功标记
		attribute.addFlashAttribute("success", sysMail);
		//转到主题邮件列表
		return "redirect:/business/mail/subject/list";
	}
	
	/**
	 * 主题邮件讨论页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/subject/show/{id}")
	public String subjectShow(@PathVariable("id") Long id,Model model) {

		SysMail mail = mailService.find(id);
		List<SysMailReply> replyContents = mailReplyService.findList(id);
		
		model.addAttribute("mail", mail);
		model.addAttribute("replyContents", replyContents);
		if(StringUtils.isNotBlank(mail.getAttachId())){
			
			
		}
		return "business.mail.subject.show";
	}
	
	/**
	 * 主题邮件回复
	 * @param replyContent
	 * @param request
	 * @return
	 * @author <a href="mailto:wangxba@legendsec.com">王新兵</a>
	 */
	@RequestMapping(value="/subject/reply",method=RequestMethod.POST)
	public @ResponseBody SysMailReply reply(@RequestBody SysMailReply reply,HttpServletRequest request){
		UserInfo replyer = SecurityContextUtils.getCurrentUserInfo();
		reply.setReplyer(replyer);
		reply.setReplyTime(new Date());
		mailReplyService.persist(reply);
		return reply;
	}
	
	/**
	 * 转到转发页面
	 * @param messageId
	 * @param model
	 * @return
	 * @author <a href="mailto:wangxba@legendsec.com">王新兵</a>
	 */
	@RequestMapping("/toForward/{messageId}")
	public String toForward(@PathVariable Long messageId,Model model){
		UserInfo userInfo = SecurityContextUtils.getCurrentUserInfo();
		SysMailState mailState = mailStateService.findByMailIdAndUserId(messageId,userInfo.getUserId());
		SysMail oldMail = mailService.find(mailState.getMailId());

		SysMail mail = new SysMail();
		String attaches = uuidUtils.generate(MailMessage.class);
		mail.setAttachId(attaches);
		mail.setStateId(mailState.getId());
		mail.setTitle(FORWARD_PRE+oldMail.getTitle());
		
		model.addAttribute("oldContent",oldMail.getContent());
		model.addAttribute("toForward",true);
		model.addAttribute("mail",mail);
		return "business.mail.edit"; 
	}
	
	/**
	 * 回执
	 * @param stateId
	 * @return
	 * @author <a href="mailto:wangxba@legendsec.com">王新兵</a>
	 */
	@RequestMapping(value="receipt/{stateId}",method=RequestMethod.POST)
	public @ResponseBody boolean receipt(@PathVariable Long stateId){
		mailService.receipt(stateId);
		return true;
	}
}

