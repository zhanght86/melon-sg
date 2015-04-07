/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.controller;

import java.util.Date;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;

import secfox.soc.melon.system.domain.WorkCalendar;
import secfox.soc.melon.system.service.WorkCalendarService;

/**
 * @since 1.0 2014年10月8日,下午7:37:52
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Controller
@RequestMapping(value="/system/calendar", method=RequestMethod.GET)
public class WorkCalendarController {
	
	private WorkCalendarService calendarService;
	
	/**
	 * 
	 * @param calendarService
	 */
	@Inject
	public WorkCalendarController(WorkCalendarService calendarService) {
		super();
		this.calendarService = calendarService;
	}
	
	/**
	 * 新建工作时间
	 * @param workCalendar
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.POST)
	@ResponseBody
	public WorkCalendar create(@ModelAttribute WorkCalendar workCalendar) {
		//保存日历对象
		workCalendar.setDateFlag(true);
		calendarService.persist(workCalendar);
		return workCalendar;
	}
	
	
	/**
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/show", method=RequestMethod.GET)
	public String show(Model model, HttpServletRequest request) {
		//添加今日时间,校正服务器时间
		//获取设置时间
		String now = request.getParameter("defaultDate");
		if(StringUtils.isNotBlank(now)) {
			model.addAttribute("defaultDate", now);
		}
		model.addAttribute("now", new Date());
		model.addAttribute("calendar", new WorkCalendar());
		return "system.calendar.show";
	}
	
	/**
	 * 初始化日历信息
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/find", method=RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> find(@RequestParam("start") String start, @RequestParam("end") String end) {
		//获取起始时期
		Map<String, Object> results = Maps.newHashMap();
		results.put("events", calendarService.find(start, end));
		results.put("start", start);
		results.put("end", end);
		return results;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/update", method=RequestMethod.POST)
	@ResponseBody
	public WorkCalendar update(@ModelAttribute WorkCalendar workCalendar) {
		return calendarService.update(workCalendar);
	}
	
	/**
	 * 判断工作日
	 * @param date
	 * @param workDay
	 * @return
	 */
	@RequestMapping(value="/confirm", method=RequestMethod.GET)
	@ResponseBody
	public boolean confirm(@RequestParam("date") String date, @RequestParam("workDay") boolean workDay) {
		return calendarService.confirm(date, workDay);
	}
	
	/**
	 * 删除日程
	 * @param id
	 */
	@RequestMapping(value="/remove/{id}", method=RequestMethod.POST)
	@ResponseBody
	public Long remove(@PathVariable("id") Long id) {
		calendarService.remove(id);
		return id;
	}
	
}
