/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
/**
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.business.db.controller;

import java.util.Date;

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

import secfox.soc.business.db.domain.BasicInfo;
import secfox.soc.business.db.service.BasicInfoService;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.ActionStatus;
import secfox.soc.melon.web.WebConstants;

/**
 *
 * @since 1.0 2014-10-20 下午4:16:20
 * @author <a href="mailto:huangxm@legendsec.com">黄晓梅</a>
 * @version 1.0
 */
@Controller
@RequestMapping("/db/basicInfo")
public class BasicInfoController {
	
	private BasicInfoService basicInfoService;
	
	@Inject
	public BasicInfoController(BasicInfoService basicInfoService){
		this.basicInfoService = basicInfoService;
	}
	/**
	 * 跳转到单位基本信息新建页面
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(@ModelAttribute BasicInfo basicInfo, Model model) {
		basicInfo.setOrganType(1);
		basicInfo.setTradeType(6);
		return "db.basicInfo.edit";
	}
	
	/**
	 * 保存单位基本信息
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("basicInfo") BasicInfo basicInfo,BindingResult result, RedirectAttributes attribute) {
		
        if(result.hasErrors()) {
            return "db.basicInfo.edit";
        }
        basicInfo.setCreateTime(new Date());
        
		basicInfoService.persist(basicInfo);
        attribute.addFlashAttribute(basicInfo);
        //添加提示信息
        ActionHint hint = ActionHint.create("db.basicInfo.create.hint", basicInfo.getOrganName());
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
		model.addAttribute("basicInfo", basicInfoService.find(id));
		return "db.basicInfo.edit";
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
	public String update(@Valid@ModelAttribute BasicInfo basicInfo, BindingResult result, RedirectAttributes attribute) {
		if(result.hasErrors()) {
            return "db.basicInfo.edit";
        }
		basicInfoService.merge(basicInfo);
        attribute.addFlashAttribute(basicInfo);
        //添加提示信息
        //添加提示信息
        ActionHint hint = ActionHint.create("db.basicInfo.update.hint", basicInfo.getOrganName());
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
		basicInfoService.remove(id);
		return ActionStatus.SUCCESS;
	}
	

	/**
	 * 跳转到明细页面
	 */
	@RequestMapping(value="/show/{id}",method=RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model model){
		BasicInfo basicInfo = (BasicInfo)model.asMap().get("basicInfo");
		if(basicInfo == null){
			basicInfo = basicInfoService.find(id);
		}
		model.addAttribute("basicInfo",basicInfo);
		return "db.basicInfo.edit";
	}
	
	
}
