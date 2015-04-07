/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.asset.staff.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.base.util.UUIDUtils;
import secfox.soc.melon.security.util.SecurityContextUtils;
import secfox.soc.melon.asset.staff.domain.Certificate;
import secfox.soc.melon.asset.staff.domain.InterStaff;
import secfox.soc.melon.asset.staff.query.InterStaffPageQuery;
import secfox.soc.melon.asset.staff.service.InterStaffService;
import secfox.soc.melon.system.domain.AjaxFile;
import secfox.soc.melon.system.service.AjaxFileService;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.ActionStatus;
import secfox.soc.melon.web.HintLevel;

import com.google.common.collect.Lists;

/**
 * @since 2014-11-20,下午2:59:45
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Controller
@RequestMapping("/staff/inter")
public class InterStaffController {
	
	private InterStaffService interService;
	private UUIDUtils uuidUtils;
	private AjaxFileService ajaxFileService;
	
	@Inject
	public InterStaffController(InterStaffService interService, UUIDUtils uuidUtils, AjaxFileService ajaxFileService) {
		this.interService = interService;
		this.uuidUtils = uuidUtils;
		this.ajaxFileService =ajaxFileService;
	}
	
	/**
	 * 跳转到查询页面
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value="/home", method = RequestMethod.GET)
	public String list(Model model) {
		Long organId = SecurityContextUtils.getCurrentUserInfo().getOrganId();
		int interCount = interService.getCount(organId);
		List<Object[]> datas = interService.interCount(organId);
		model.addAttribute("interCount", interCount);
		model.addAttribute("datas", datas);
		return "staff.inter.home";
		
	}
	
	/**
	 * 加载查询页面
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value="/load", method = RequestMethod.GET)
	public String load(@ModelAttribute("pageQuery") InterStaffPageQuery pageQuery) {
		return "melon/asset/staff/inter/list";
		
	}
	
	/**
	 * 查询
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value="/list", method = RequestMethod.POST)
	public @ResponseBody Pagination<InterStaff> query(@ModelAttribute("pageQuery") InterStaffPageQuery pageQuery) {
		Pagination<InterStaff> result = null;
		try{
			 result = interService.findPagination(pageQuery);
		}catch(Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	/**
	 * 跳转到新建页面
	 * @param interStaff
	 * @return
	 */
	@RequestMapping(value="/create", method = RequestMethod.GET)
	public String create(Model model) {
		InterStaff interStaff = new InterStaff();
		interStaff = interService.create();
		interStaff.setCertificateBusiness(uuidUtils.generate(interStaff));
		interStaff.setPaperBusiness(uuidUtils.generate(interStaff));
		interStaff.setSecProtocolBusiness(uuidUtils.generate(interStaff));
		model.addAttribute("interStaff", interStaff);
		model.addAttribute("organId", SecurityContextUtils.getCurrentUserInfo().getOrganId());
		return "staff.inter.edit";
	}
	
	/**
	 * 保存数据
	 * @param interStaff
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/create", method = RequestMethod.POST)
	public String create(@Valid@ModelAttribute("interStaff") InterStaff interStaff, BindingResult result,HttpServletRequest req) {
		ActionHint actionHint = ActionHint.create("melon.interStaff.create.hint", interStaff.getName());
		//如果服务器验证错误
		if(result.hasErrors()){
			actionHint.setLevel(HintLevel.ERROR);
			return "staff.inter.edit";
		}
		//获取证书
		List<Certificate> certs = interService.getCerts(req);
		interStaff.setCerts(certs);
		interStaff.setEditor(SecurityContextUtils.getCurrentUserInfo());
		interService.persist(interStaff);
		return "redirect:/staff/inter/home";
	}
	
	/**
	 * 跳转到更新页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//InterStaff interStaff = interService.findWrappedStaffId(id);
		InterStaff interStaff = interService.updateCreate(interService.find(id),interService.find(id).getCertificate(),id);
		model.addAttribute("certs",interStaff.getCerts());
		model.addAttribute("interStaff",interStaff);
		model.addAttribute("organId", SecurityContextUtils.getCurrentUserInfo().getOrganId());
		return "staff.inter.edit";
	}
	
	/**
	 * 保存更新修改
	 * @param staff
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method = RequestMethod.POST)
	public String update(@Valid@ModelAttribute InterStaff staff, BindingResult result,HttpServletRequest req) {
		ActionHint actionHint = ActionHint.create("melon.interStaff.update.hint", staff.getName());
		//如果服务器验证错误
		if(result.hasErrors()){
			actionHint.setLevel(HintLevel.ERROR);
			return "staff.inter.edit";
		}
		//获取证书
		List<Certificate> certs = interService.getCerts(req);
		staff.setCerts(certs);
		//保存业务对象
		staff.setEditor(SecurityContextUtils.getCurrentUserInfo());
		interService.merge(staff);
		//添加操作协助提示(本处因为树性操作结构)，依赖于客户端代码，此句可不执行
		actionHint.setActionType(1);//设置为新增
		actionHint.setDomain(staff);
		return "redirect:/staff/inter/home";
	}
	
	
	/***
	 * 导出所有内部部用户
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/export",method=RequestMethod.GET, produces={"application/vnd.ms-excel"})
	public String export(HttpServletRequest req,Model model) {
		List<InterStaff> stafflist = Lists.newArrayList();
		//设置标题
		model.addAttribute("title",MessageSourceUtils.getMessage("melon.interStaff.exportAll"));
		stafflist = interService.findAll();
		//取到要导出的数据集
		model.addAttribute("results", stafflist);
		return "interStaffXls";
	}
	
	
	/**
	 * 明细页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/show/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model model) {
		InterStaff staff = interService.find(id);
		//安全协议附件
		List<AjaxFile> secProFiles = ajaxFileService.findFile(staff.getSecProtocolBusiness());
		//证书附件
		List<AjaxFile> cerFiles = ajaxFileService.findFile(staff.getCertificateBusiness());
		//论文附件
		List<AjaxFile> paperFiles = ajaxFileService.findFile(staff.getPaperBusiness());
		model.addAttribute("interStaff", staff);
		model.addAttribute("secProFiles", secProFiles);
		model.addAttribute("cerFiles", cerFiles);
		model.addAttribute("paperFiles", paperFiles);
		return "staff.inter.show";
	}
	
	/**
	 * 删除记录
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/remove/{id}", method = RequestMethod.POST)
	public @ResponseBody ActionStatus remove(@PathVariable("id") Long id) {
		interService.remove(id);
		return ActionStatus.SUCCESS;
	}
	
}
