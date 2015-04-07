/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.alarm.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import secfox.soc.melon.alarm.service.AlarmService;

/**
 * @since 1.0 2014年10月16日,下午6:09:24
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Controller
@RequestMapping("/alarm")
public class AlarmHomeController {
	
	private AlarmService alarmService;

	/**
	 * service注入
	 * @param alarmService
	 */
	@Inject
	public AlarmHomeController(AlarmService alarmService) {
		super();
		this.alarmService = alarmService;
	}
	/**
	 * 跳转到list页面
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String list(Model model) {
		 List<Object[]> devicelist = alarmService.listDevicesCount();
		 List<Object[]> typelist = alarmService.listTypesCount();
		 List<Object[]> longest= alarmService.listDaysCount();
		 List<Object[]> handles = alarmService.listHandleCount();
		 List<Object[]> todays = alarmService.listTodayCount();
		 if(todays.size()!=0){
			 model.addAttribute("today", todays.get(0)[1]);
		 }else{
			 model.addAttribute("today", 0);
		 }
		 if(handles.size()!=0){
			 model.addAttribute("handle", handles.get(0)[1]);
		 }else{
			 model.addAttribute("handle", 0);
		 }
		 model.addAttribute("longest", longest);
		 model.addAttribute("typelist", typelist);
		 model.addAttribute("devicelist", devicelist);
		return "alarm.home";
	}
	
}
