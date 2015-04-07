/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.asset.staff.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.base.util.UUIDUtils;
import secfox.soc.melon.security.domain.Account;
import secfox.soc.melon.security.util.SecurityContextUtils;
import secfox.soc.melon.asset.staff.domain.OuterStaff;
import secfox.soc.melon.asset.staff.query.OuterStaffPageQuery;
import secfox.soc.melon.asset.staff.service.OuterStaffService;
import secfox.soc.melon.system.domain.AjaxFile;
import secfox.soc.melon.system.service.AjaxFileService;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.ActionStatus;
import secfox.soc.melon.web.HintLevel;
import secfox.soc.melon.web.WebConstants;

/**
 * @since 2014-11-21,下午1:39:23
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Controller
@RequestMapping("/staff/outer")
public class OuterStaffController {

	private OuterStaffService outerStaffService;
	private UUIDUtils uuidUtils;
	private AjaxFileService ajaxFileService;
	
	@Inject
	public OuterStaffController(OuterStaffService outerStaffService, UUIDUtils uuidUtils, AjaxFileService ajaxFileService) {
		this.outerStaffService = outerStaffService;
		this.uuidUtils = uuidUtils;
		this.ajaxFileService = ajaxFileService;
	}
	
	/**
	 * 跳转到list页面
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String list(Model model) {
		Long organId = SecurityContextUtils.getCurrentUserInfo().getOrganId();
		int outerCount = outerStaffService.getCount(organId);
		List<Object[]> datas = outerStaffService.outerCount(organId);
		model.addAttribute("outerCount", outerCount);
		model.addAttribute("datas", datas);
		return "staff.outer.home";
	}
	
	/**
	 * load到查询页面
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value="/load", method=RequestMethod.GET)
	public String load(@ModelAttribute("pageQuery") OuterStaffPageQuery pageQuery) {
		return "melon/asset/staff/outer/list";
	}
	
	/**
	 * list查询
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.POST)
	public @ResponseBody Pagination<OuterStaff> query(@ModelAttribute("pageQuery") OuterStaffPageQuery pageQuery) {
		return outerStaffService.findPagination(pageQuery);
	}
	
	/**
	 * 跳转到新建页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String create(Model model) {
		OuterStaff outerStaff = new OuterStaff();
		outerStaff.setCertificateBusiness(uuidUtils.generate(outerStaff));
		outerStaff.setSecProtocolBusiness(uuidUtils.generate(outerStaff));
		model.addAttribute("outerStaff", outerStaff);
		model.addAttribute("organId", SecurityContextUtils.getCurrentUserInfo().getOrganId());
		return "staff.outer.edit";
	}
	
	/**
	 * 保存新建
	 * @param outerStaff
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(@Valid@ModelAttribute("outerStaff") OuterStaff outerStaff, BindingResult result,RedirectAttributes attribute) {
		
		ActionHint actionHint = ActionHint.create("melon.outerStaff.create.hint", outerStaff.getName());
		//如果服务器验证错误
		if(result.hasErrors()){
			actionHint.setLevel(HintLevel.ERROR);
			return "staff.inter.edit";
		}
		outerStaff.setEditor(SecurityContextUtils.getCurrentUserInfo());
		outerStaffService.persist(outerStaff);
		return "redirect:/staff/outer/home";
	}
	
	/**
	 * 跳转到更新页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method = RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		model.addAttribute("outerStaff", outerStaffService.find(id));
		model.addAttribute("organId", SecurityContextUtils.getCurrentUserInfo().getOrganId());
		return "staff.outer.edit";
	}
	
	/**
	 * 保存更新修改
	 * @param staff
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method = RequestMethod.POST)
	public String update(@Valid@ModelAttribute OuterStaff outerStaff, BindingResult result) {
		ActionHint actionHint = ActionHint.create("melon.interStaff.update.hint", outerStaff.getName());
		//如果服务器验证错误
		if(result.hasErrors()){
			actionHint.setLevel(HintLevel.ERROR);
			return "staff.inter.edit";
		}
		//保存业务对象
		outerStaffService.merge(outerStaff);
		//添加操作协助提示(本处因为树性操作结构)，依赖于客户端代码，此句可不执行
		actionHint.setActionType(1);//设置为新增
		actionHint.setDomain(outerStaff);
		return "redirect:/staff/outer/home";
	}
	
	/***
	 * 导出所有外部用户
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/export",method=RequestMethod.GET, produces={"application/vnd.ms-excel"})
	public String export(HttpServletRequest req,Model model) {
		List<OuterStaff> stafflist = Lists.newArrayList();
		//设置标题
		model.addAttribute("title",MessageSourceUtils.getMessage("melon.outerStaff.exportAll"));
		stafflist = outerStaffService.findAll();
		//取到要导出的数据集
		model.addAttribute("results", stafflist);
		return "outerStaffXls";
	}
	
	
	/**
	 * 明细页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/show/{id}", method = RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model model) {
		OuterStaff staff = outerStaffService.find(id);
		List<AjaxFile> secProFiles = ajaxFileService.findFile(staff.getSecProtocolBusiness());
		List<AjaxFile> cerFiles = ajaxFileService.findFile(staff.getCertificateBusiness());
		model.addAttribute("outerStaff", staff);
		model.addAttribute("secProFiles", secProFiles);
		model.addAttribute("cerFiles", cerFiles);
		return "staff.outer.show";
	}
	
	/**
	 * 删除记录
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/remove/{id}", method = RequestMethod.POST)
	public @ResponseBody ActionStatus remove(@PathVariable("id") Long id) {
		outerStaffService.remove(id);
		return ActionStatus.SUCCESS;
	}
	
	
}
