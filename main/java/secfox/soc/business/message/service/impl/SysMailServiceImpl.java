package secfox.soc.business.message.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.business.message.MailConstants;
import secfox.soc.business.message.dao.SysMailDao;
import secfox.soc.business.message.domain.SysMail;
import secfox.soc.business.message.domain.SysMailReply;
import secfox.soc.business.message.domain.SysMailState;
import secfox.soc.business.message.service.SysMailReplyService;
import secfox.soc.business.message.service.SysMailService;
import secfox.soc.business.message.service.SysMailStateService;
import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.security.util.SecurityContextUtils;

import com.google.common.collect.Sets;

/**
 * @since 1.0 2014-10-20,下午3:25:31
 * 
 * @author  <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0 
 */
@Service
public class SysMailServiceImpl extends GenericServiceImpl<SysMail, Long>
		implements SysMailService {
	
	private SysMailDao sysMaildDao;
	
	private SysMailStateService mailStateService;
	
	private SysMailReplyService mailReplyService;
	/**
	 * 
	 */
	@Inject
	public SysMailServiceImpl(SysMailDao sysMaildDao,SysMailStateService mailStateService,SysMailReplyService mailReplyService) {
		super();
		this.sysMaildDao = sysMaildDao;
		this.mailStateService = mailStateService;
		this.mailReplyService = mailReplyService;
	}


	@Override
	protected GenericDao<SysMail, Long> getDao() {
		return sysMaildDao;
	}

	/*@Override
	public Pagination<SysMail> findPage(SysMailPageQuery pageQuery) {
		return this.findDomainPage(QueryType.JQL, pageQuery, new PaginationBuilder<SysMail, SysMailPageQuery>() {

			@Override
			public void buildSelect(QueryTemplate qt) {
				qt.append("SELECT a FROM SysMail a");
			}

			@Override
			public void buildWhere(SysMail mail, QueryTemplate qt) {
				// 标题
				String title = mail.getTitle();
				if (StringUtils.isNotBlank(title)) {
					qt.append(" AND a.title LIKE :title");
					qt.addParameter("title", QueryTemplate.buildAllLike(title));
				}
			}

			@Override
			public void buildBys(String column, SortOrder order, QueryTemplate qt) {
				if (StringUtils.isBlank(column))
					column = "createTime";
				qt.append(QueryTemplate.buildOrderBy("a", column, order));
			}

			@Override
			public void buildCount(QueryTemplate qt) {
				qt.append("SELECT COUNT(a.id) FROM SysMail a");
			}
		});
		
	}*/

	@Transactional
	@Override
	public void sendMail(SysMail mail) {
		
		//设置消息基本信息
		mail.setType(MailConstants.TYPE_DEFAULT);
		mail.setSender(SecurityContextUtils.getCurrentUserInfo());
		mail.setSendTime(new Date());
		mail.setDraft(false);
		
		//判断是否是回复邮件
		if(mail.getStateId()!=null){
			SysMailState state = mailStateService.find(mail.getStateId());
				state.setReplyState(true);
				mailStateService.merge(state);
		}
		//删除旧的MsgState
		removeMsgStateByMsgId(mail);
		
		mail = this.merge(mail);
		
		addMessageState(mail);
		
	}

	@Transactional
	@Override
	public void draftMail(SysMail mail) {
		if(mail.getType() == 1){
			mail.setType(MailConstants.TYPE_DEFAULT);
		}else{
			mail.setType(MailConstants.TYPE_SUBJECT);
		}
		mail.setSender(SecurityContextUtils.getCurrentUserInfo());
		mail.setSendTime(new Date());
		mail.setDraft(true);
		
		removeMsgStateByMsgId(mail);
		
		mail = this.merge(mail);
		
		addMessageState(mail);
	}
	
	
	@Transactional
	private void addMessageState(SysMail mail) {
		Set<UserInfo> receivers = Sets.newHashSet(); //infoLoader.load(mail.getReceiverIds());
		for(UserInfo receiver : receivers){
				SysMailState mState = new SysMailState();
				mState.setMailId(mail.getId());
				mState.setReceivedTime(new Date());
				mState.setReceiver(receiver);
				mailStateService.persist(mState);
		}
	}
	
	//删除指定邮件下所有接收状态
	@Transactional
	private void removeMsgStateByMsgId(SysMail mail) {
		if(mail.getId() !=null){
			List<SysMailState> states = mailStateService.finMailStatesById(mail.getId());
			for(SysMailState state : states){
				mailStateService.remove(state.getId());
			}
		}
	}
	
	@Override
	@Transactional
	public void removeMail(Long stateId, int type) {
		SysMailState mailState = mailStateService.find(stateId);
		switch(type){
			case 1://收件箱
				mailState.setDelState(true);
				mailStateService.merge(mailState);
				break;
			case 2:
			case 3://发件箱、草稿箱
				{
					Long mId = mailState.getMailId();
					SysMail mail = find(mId);
					mail.setDelState(true);
					merge(mail);
				}
				break;
			case 4://已删邮件
				if(mailState.isDelState()){//如果是收件箱
					mailStateService.remove(mailState);
				}
				SysMail mail = find(mailState.getMailId());
				if(mail.isDelState()){//如果是发件箱或草稿箱
					SysMail old = find(mail.getId());
					if(!mail.isDraft()){
						old.setDelRefuse(true);
						merge(old);
					}else{//草稿箱直接删除
						remove(old);
					}
				}
				break;
			}
	}
	
	@Override
	@Transactional
	public void sendSubjectMail(SysMail mail) {
		mail.setType(MailConstants.TYPE_SUBJECT);//主题邮件
		mail.setSender(SecurityContextUtils.getCurrentUserInfo());
		mail.setSendTime(new Date());
		mail.setDraft(false);
		persist(mail);
	}
	
	@Override
	public SysMailState findByMailIdAndUserId(Long mailId, Long userId) {
		return mailStateService.findByMailIdAndUserId(mailId, userId);
	}
	
	@Override
	public List<SysMailState> finMailStatesById(Long mailId) {
		return mailStateService.finMailStatesById(mailId);
	}
	
	/**
	 * 回执
	 * @param stateId
	 * @author <a href="mailto:wangxba@legendsec.com">王新兵</a>  
	 */
	@Transactional
	@Override
	public void receipt(Long stateId) {
		SysMailState msgState = mailStateService.find(stateId);
		msgState.setReceiptState(true);
		mailStateService.merge(msgState);//更新邮件已回执
		
		SysMail mail = new SysMail();//发送回执邮件
		String receiptContent = MessageSourceUtils.getMessage("task.mailMessage.receipt.content");
		String RE_PRE =  MessageSourceUtils.getMessage("task.mailMessage.receipt.pre");
		SysMail oldmail = find(msgState.getMailId());
		mail.setContent(receiptContent);
		mail.setSender(msgState.getReceiver());
		mail.setTitle(RE_PRE + oldmail.getTitle());
		mail.setSendTime(new Date());
		mail.setType(MailConstants.TYPE_DEFAULT);
		mail.setDraft(false);
		mail.setReceivIds("E:"+oldmail.getSender().getUserId());
		mail.setReceivNames(oldmail.getSender().getUsername());
		this.persist(mail);
		
		SysMailState newState = new SysMailState();
		newState.setMailId(mail.getId());
		newState.setReceivedTime(new Date());
		newState.setReceiver(oldmail.getSender());
		mailStateService.persist(newState);//回执邮件关联
	}


	@Override
	public List<SysMailReply> findReplyList(Long mailId) {
		return mailReplyService.findList(mailId);
	}

}

