/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.asset.staff.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import secfox.soc.melon.security.util.SecurityContextUtils;
import secfox.soc.melon.asset.staff.service.InterStaffService;
import secfox.soc.melon.asset.staff.service.OuterStaffService;

/**
 * @since 2014-11-22,上午10:44:35
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Controller
@RequestMapping("/asset/staff")
public class StaffController {
	
	private InterStaffService interStaffService;
	private OuterStaffService outerStaffService;
	
	@Inject
	public StaffController(InterStaffService interStaffService, OuterStaffService outerStaffService) {
		this.interStaffService = interStaffService;
		this.outerStaffService = outerStaffService;
	}
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(Model model) {
		Long organId = SecurityContextUtils.getCurrentUserInfo().getOrganId();
		int interCount = interStaffService.getCount(organId);
		int outerCount = outerStaffService.getCount(organId);
		model.addAttribute("datas", interStaffService.getPie(organId));
		model.addAttribute("interCount", interCount);
		model.addAttribute("outerCount", outerCount);
		return "staff.home";
	}

}
