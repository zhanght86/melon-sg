/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
/**
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.business.db.controller;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import secfox.soc.business.db.domain.SystemInfo;
import secfox.soc.business.db.service.SystemInfoService;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.ActionStatus;
import secfox.soc.melon.web.WebConstants;

/**
 *
 * @since 1.0 2014-10-22
 * @author <a href="mailto:huangxm@legendsec.com">黄晓梅</a>
 * @version 1.0
 */
@Controller
@RequestMapping("/db/systemInfo")
public class SystemInfoController {
	
	private SystemInfoService systemInfoService;
	
	@Inject
	public SystemInfoController(SystemInfoService systemInfoService){
		this.systemInfoService = systemInfoService;
	}
	
	/**
	 * 信息系统基本信息新建页面
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(@ModelAttribute SystemInfo systemInfo, Model model) {
		return "db.systemInfo.edit";
	}
	/**
	 * 添加操作
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("systemInfo") SystemInfo systemInfo,BindingResult result, RedirectAttributes attribute) {
        if(result.hasErrors()) {
            return "db.systemInfo.edit";
        }
        systemInfoService.persist(systemInfo);
        attribute.addFlashAttribute(systemInfo);
        //添加提示信息
        ActionHint hint = ActionHint.create("db.basicInfo.create.hint", systemInfo.getSysName());
        attribute.addFlashAttribute(WebConstants.ACTION_HINT, hint);
        //导向到列表页面
        return "redirect:/db/home";
	}
	
	/**
	 * 跳转到修改页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		model.addAttribute("systemInfo", systemInfoService.find(id));
		return "db.systemInfo.edit";
	}
	
	/**
	 * 保存基本单位修改
	 * @param role
	 * @param result
	 * @param model
	 * @param attribute
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.POST)
	public String update(@Valid@ModelAttribute SystemInfo systemInfo, BindingResult result, RedirectAttributes attribute) {
		if(result.hasErrors()) {
            return "db.systemInfo.edit";
        }
		systemInfoService.merge(systemInfo);
        attribute.addFlashAttribute(systemInfo);
        //添加提示信息
        //添加提示信息
        ActionHint hint = ActionHint.create("db.basicInfo.update.hint", systemInfo.getSysName());
        attribute.addFlashAttribute(WebConstants.ACTION_HINT, hint);
        //导向到明细页面
        return "redirect:/db/home";
	}
	
	/**
	 * 删除单位基本信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/remove/{id}", method=RequestMethod.GET)
	@ResponseBody
	public ActionStatus remove(@PathVariable("id") Long id) {
		systemInfoService.remove(id);
		return ActionStatus.SUCCESS;
	}
	

	/**
	 * 跳转到明细页面
	 */
	@RequestMapping(value="/show/{id}",method=RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model model){
		SystemInfo systemInfo = (SystemInfo)model.asMap().get("systemInfo");
		if(systemInfo == null){
			systemInfo = systemInfoService.find(id);
		}
		model.addAttribute("systemInfo",systemInfo);
		return "db.systemInfo.edit";
	}
}
