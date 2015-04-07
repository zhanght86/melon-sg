/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.system.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.common.collect.Lists;

import secfox.soc.melon.system.domain.GlobalConfig;
import secfox.soc.melon.system.service.GlobalConfigService;

/**
 * @since 2014-9-24,下午5:20:48
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Controller
@RequestMapping("/system/globalConfig")
public class GlobalConfigController {
	
	
	private GlobalConfigService globalConfigService;
	
	@Inject
	public GlobalConfigController(GlobalConfigService globalConfigService) {
		super();
		this.globalConfigService = globalConfigService;
	}
	
	/**
	 * 获取全局配置
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/show", method=RequestMethod.GET)
	public String show(Model model) {
		GlobalConfig globalConfig = globalConfigService.find();
		model.addAttribute("globalConfig", globalConfig);
		return "system.globalconfig.show";
	}
	
	/**
	 * 跳转到编辑页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update", method=RequestMethod.GET)
	public String update(Model model) {
		GlobalConfig globalConfig = globalConfigService.find(1l);
		List<String> hours = Lists.newArrayList();
		for(int i=0;i<25;i++) {
			hours.add(String.valueOf(i));
		}
		model.addAttribute("globalConfig", globalConfig);
		model.addAttribute("hours", hours);
		return "system.globalconfig.edit";
	}
	
	/**
	 * 保存修改
	 * @param globalConfig
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public String update(@Valid@ModelAttribute("globalConfig") GlobalConfig globalConfig, HttpServletRequest request, BindingResult result) {
		if(result.hasErrors()){
			//出错重新返回编辑页面;
			return "system.globalconfig.edit";
		}
		globalConfigService.update(globalConfig, request);
		return "redirect:/system/globalConfig/show";
	}
	
	

}
