/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.alarm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import secfox.soc.melon.alarm.domain.Alarm;
import secfox.soc.melon.alarm.query.AlarmPageQuery;
import secfox.soc.melon.alarm.service.AlarmService;
import secfox.soc.melon.base.Pagination;

/**
 * 告警控制器
 * @since 1.0 2014年9月29日
 * @author <a href="mailto:jinml@legendsec.com">靳明雷</a>
 * @version 1.0
 */
@Controller
@RequestMapping("/alarm/base")
public class AlarmController {
	
	private AlarmService alarmService;

	/**
	 * service注入
	 * @param alarmService
	 */
	@Inject
	public AlarmController(AlarmService alarmService) {
		super();
		this.alarmService = alarmService;
	}
	
	/**
	 * 跳转到list页面
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@ModelAttribute("pageQuery") AlarmPageQuery pageQuery) {
		return "alarm.base.list";
	}
	
	/**
	 * 进行ajax查询获取告警列表信息
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody Pagination<Alarm> query(@ModelAttribute("pageQuery") AlarmPageQuery pageQuery) {
		return alarmService.findPage(pageQuery);
	}
	
	@RequestMapping(value = "/compare", method = RequestMethod.GET)
	public String compare(@RequestParam String alarmIds,Model model) {
		List<Alarm> alarmlist = new ArrayList<Alarm>();
		String[] aIds = alarmIds.split(",");
		for (int i = 0; i < aIds.length; i++) {
			alarmlist.add(i, alarmService.find(Long.parseLong(aIds[i])));
		}
		model.addAttribute("alarmlist", alarmlist);
		return "alarm.base.compare";
	}
	
	/**
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, Model model) {
		Alarm alarm = (Alarm) model.asMap().get("alarm");
		if (null == alarm) {
			alarm = alarmService.find(id);
		}
		model.addAttribute("alarm", alarm);
		return "alarm.base.show";
	}
	
	
	/*@RequestMapping(value="/listDevices",method=RequestMethod.GET)
	@ResponseBody
	public List<Object[]> listDevicesCount(Model model){
		 List<Object[]> devicelist = alarmService.listDevicesCount();
		 model.addAttribute("devicelist", devicelist);
		 return devicelist;
	}
	
	@RequestMapping(value="/listTypes",method=RequestMethod.GET)
	@ResponseBody
	public List<Object[]> listTypesCount(Model model){
		 List<Object[]> typelist = alarmService.listTypesCount();
		 model.addAttribute("typelist", typelist);
		 return typelist;
	}
	

	@RequestMapping(value="/listLongest",method=RequestMethod.GET)
	@ResponseBody
	public List<Object[]> listDaysCount(Model model){
		 List<Object[]> longest= alarmService.listDaysCount();
		 model.addAttribute("longest", longest);
		 return longest;
	}
	
	@RequestMapping(value="/listHandled",method=RequestMethod.GET)
	@ResponseBody
	public List<Object[]> listHandleCount(Model model){
		 List<Object[]> handles = alarmService.listHandleCount();
		 model.addAttribute("handles", handles);
		 return handles;
	}

	@RequestMapping(value="/listTodays",method=RequestMethod.GET)
	@ResponseBody
	public List<Object[]> listTodayCount(Model model){
		 List<Object[]> todays = alarmService.listTodayCount();
		 model.addAttribute("todays", todays);
		 return todays;
		
	}*/
	
	@RequestMapping(value="/findWeekCount",method=RequestMethod.GET)
	@ResponseBody
	public List<Object[]> findWeekCount(Model model){
		 return  alarmService.listWeekCount();
	}
}
