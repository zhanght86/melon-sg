/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.controller;

import java.math.BigInteger;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.organ.serivce.OrganizationService;
import secfox.soc.melon.security.service.AccountService;
import secfox.soc.melon.system.service.SystemHealthService;

import com.google.common.collect.Lists;

/**
 * @since 1.0 2014年10月8日,下午3:41:18
 * 系统管理主页
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Controller
@RequestMapping(value="/system", method=RequestMethod.GET)
public class SystemHomeController {
	
	private SystemHealthService healService;
	
	private AccountService accountService;
	
	private OrganizationService organizationService;
	
	/**
	 * 
	 * @param healService
	 * @param accountService
	 */
	@Inject
	public SystemHomeController(SystemHealthService healService, AccountService accountService,
			OrganizationService organizationService) {
		super();
		this.healService = healService;
		this.accountService = accountService;
		this.organizationService = organizationService;
	}
	
	/**
	 * 系统管理主页
	 * @return
	 */
/*	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(Model model) {
		List<Account> accounts = accountService.findOnlineAccounts();
		model.addAttribute("onlines", accounts);
		return "system.home";
	}
*/	
	/**
	 * 返回系统健康状况
	 * @return
	 */
	@RequestMapping(value="/home", method=RequestMethod.GET)
	//@ResponseBody
	public String  getSystemHealth(Model model) {
		List<Object[]> health = Lists.newArrayList();
		List<Object[]> roles = accountService.listRoleWithCount();
		
		//String minName = (String) roles.get(roles.size()-1)[1];
		health.add(new Object[]{MessageSourceUtils.getMessage("system.cpu"), healService.getCpuUsage()});
		health.add(new Object[]{MessageSourceUtils.getMessage("system.memory"), healService.getMemoryUsage()});
		model.addAttribute("cpu", health.get(0)[1]);
		model.addAttribute("mem", health.get(1)[1]);
		model.addAttribute("roles", roles);
		
		//获取直属单位、省级单位、县市单位数量
		List<Object[]> provinceCount = organizationService.getProvinceCount();
		int zsdwCount = 0,
			cityCount = 0,
			alldwSum = 0;
		for(int i =0;i< provinceCount.size();i++){
			int dwLevel = Integer.parseInt(String.valueOf(provinceCount.get(i)[0]));
			if(dwLevel ==0 || dwLevel==1){
				zsdwCount += Integer.parseInt(String.valueOf(provinceCount.get(i)[1]));
			}else{
				cityCount += Integer.parseInt(String.valueOf(provinceCount.get(i)[1]));
			}
			alldwSum += Integer.parseInt(String.valueOf(provinceCount.get(i)[1]));
		}
		model.addAttribute("cityCount", cityCount);
		model.addAttribute("zsdwCount", zsdwCount);
		model.addAttribute("alldwSum", alldwSum);
		
		//获取未激活帐号的数量
		BigInteger unActiveCount = accountService.getUnActiveCount();
		model.addAttribute("unActiveCount", unActiveCount);
		
		//获取所有用户数量
		BigInteger userCount = accountService.getTotalUser();
		model.addAttribute("userCount", userCount);
		
		return "system.home";
	}
}
