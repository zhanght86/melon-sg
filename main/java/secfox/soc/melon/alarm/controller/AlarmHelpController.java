/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.alarm.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import secfox.soc.melon.alarm.domain.Alarm;
import secfox.soc.melon.alarm.service.AlarmService;

/**
 * @since 1.0 2014年10月23日,上午10:08:01
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Controller
@RequestMapping("/alarm/base")
public class AlarmHelpController {
	
	private AlarmService alarmService;
	
	@Inject
	public AlarmHelpController(AlarmService alarmService){
		super();
		this.alarmService = alarmService;
	}
	/**
	 * 跳转到帮助页面
	 * @return
	 */
	@RequestMapping(value="/helpPage/{id}",method = RequestMethod.GET)
	public String toHelpPage(@PathVariable Long id, Alarm alarm, Model model){
		Alarm alarms = alarmService.find(id);
		model.addAttribute("alarm", alarms);
		
		return "alarm.base.helpPage";
	}
	
	
}
