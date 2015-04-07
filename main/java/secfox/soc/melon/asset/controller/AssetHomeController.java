/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import secfox.soc.melon.asset.service.DeviceService;

import com.google.common.collect.Lists;

/**
 * @since 1.0 2014年10月16日,下午2:23:41
 * 安全对象管理主页
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Controller
@RequestMapping("/asset")
public class AssetHomeController {
	
	private DeviceService deviceService;
	
	@Inject
	public AssetHomeController(DeviceService deviceService) {
		this.deviceService = deviceService;
	}
	
	/**
	 * 系统管理主页
	 * @return
	 */
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String home(Model model) {
		//获取柱状图数据
		List<Object[]> typeViewDb = deviceService.findTypeViewDb();
		model.addAttribute("typeView", typeViewDb);
		return "asset.home";
	}
	
	
	
}
