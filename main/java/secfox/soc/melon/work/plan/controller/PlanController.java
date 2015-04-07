/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.work.plan.controller;

import java.util.Date;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;

import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.base.util.UUIDUtils;
import secfox.soc.melon.security.util.SecurityContextUtils;
import secfox.soc.melon.work.plan.domain.Plan;
import secfox.soc.melon.work.plan.service.PlanService;

/**
 * @since 2014-11-6,下午3:59:28
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Controller
@RequestMapping("/work/plan")
public class PlanController {
	
	private PlanService planService;
	private UUIDUtils uuidUtils;
	
	@Inject
	public PlanController(PlanService planService, UUIDUtils uuidUtils) {
		this.planService = planService;
		this.uuidUtils = uuidUtils;
	}
	
	/**
	 * 跳转到日历页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/show", method=RequestMethod.GET)
	public String show(Model model) {
		model.addAttribute("now", new Date());
		model.addAttribute("planForm", new Plan());
		return "work.plan.show";
	}
	
	/**
	 * 创建计划
	 * @param plan
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String create(Model model) {
		Plan plan = new Plan();
		plan.setAttachId(uuidUtils.generate(plan));
		model.addAttribute("planForm", plan);
		return "melon/work/plan/planDialog";
		
	}
	
	/**
	 * 创建计划
	 * @param plan
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public @ResponseBody Plan create(@ModelAttribute Plan plan) {
		plan.setCreator(SecurityContextUtils.getCurrentUserInfo());
		planService.persist(plan);
		return plan;
		
	}
	
	/**
	 * 更新计划
	 * @param plan
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		model.addAttribute("planForm", planService.find(id));
		return "melon/work/plan/planDialog";
	}
	
	/**
	 * 修改计划
	 * @param plan
	 * @return
	 */
	@RequestMapping(value="/update", method=RequestMethod.POST)
	public @ResponseBody Plan update(@ModelAttribute Plan plan) {
		planService.merge(plan);
		return plan;
	}
	
	/**
	 * 删除计划
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/remove/{id}", method=RequestMethod.POST)
	public @ResponseBody Long remove(@PathVariable("id") Long id) {
		planService.remove(id);
		return id;
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
		UserInfo cur = SecurityContextUtils.getCurrentUserInfo();
		if(cur != null) {
			results.put("events", planService.find(start, end, cur.getUserId()));
		}else {
			results.put("events", planService.find(start, end, null));
		}
		results.put("start", start);
		results.put("end", end);
		return results;
	}

}
