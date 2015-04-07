package secfox.soc.las.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import secfox.soc.las.domain.Alert;
import secfox.soc.las.domain.AlertRule;
import secfox.soc.las.domain.LasRuleCounts;
import secfox.soc.las.domain.LasRuleCountsModel;
import secfox.soc.las.domain.RuleCondition;
import secfox.soc.las.query.AlertPageQuery;
import secfox.soc.las.query.LasRulePageQuery;
import secfox.soc.las.domain.LasRule;
import secfox.soc.las.service.AlertRuleService;
import secfox.soc.las.service.AlertService;
import secfox.soc.las.service.LasRuleCountsService;
import secfox.soc.las.service.LasRuleService;
import secfox.soc.las.service.RuleConditionService;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.security.util.SecurityContextUtils;
import secfox.soc.melon.web.ActionStatus;

@Controller
@RequestMapping("/las/sim")
public class LasController {
	
	private AlertService filterService;	//las规则
	private AlertRuleService alertRuleService; //las规则动作配置
	private LasRuleService lasRuleService;
	private LasRuleCountsService lasRuleCountsService;
	private RuleConditionService ruleConditionService;
	private String baseUrl;
	
	@Inject
	public LasController(RuleConditionService ruleConditionService,LasRuleCountsService lasRuleCountsService,AlertService filterService, AlertRuleService alertRuleService,LasRuleService lasRuleService, @Named("lasBaseUrl")String lasBaseUrl) {
		this.filterService = filterService;
		this.alertRuleService = alertRuleService;
		this.baseUrl = lasBaseUrl;
		this.lasRuleService = lasRuleService;
		this.lasRuleCountsService = lasRuleCountsService;
		this.ruleConditionService = ruleConditionService;
	}
	
	/**
	 * las-规则页面
	 * @param model
	 * @return
	 */
	@RequestMapping("/rule")
	public String rule(Model model) {
		model.addAttribute("url", baseUrl+"rule/rule.do?method=queryRule&groupId=1&ums=true");
		return "las.sim.embed";
	}
	
	/**
	 * las-过滤器配置
	 * @param model
	 * @return
	 */
	@RequestMapping("/filter")
	public String filter(Model model) {
		model.addAttribute("url", baseUrl+"utils/filter.do?method=queryFilter&groupId=507&ums=true");
		return "las.sim.embed";
	}
	
	/**
	 * las-资源配置
	 * @param model
	 * @return
	 */
	@RequestMapping("/resource")
	public String resource(Model model) {
		model.addAttribute("url", baseUrl+"asset/resource.do?method=getResourceList&parent_id=1&type=1&ums=true");
		return "las.sim.embed";
	}
	
	/**
	 * las-黑白名单配置
	 * @param model
	 * @return
	 */
	@RequestMapping("/blackAndWhite")
	public String blackAndWhite(Model model) {
		model.addAttribute("url", baseUrl+"operation/BlackAndWhiteList.do?method=viewall&parent_id=1&ums=true");
		return "las.sim.embed";
	}
	
	/**
	 * 跳转到告警规则页面
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping("/alert/list")
	public String listAlert(@ModelAttribute("pageQuery") AlertPageQuery pageQuery) {
		return "las.alert.list";
	}
	
	/**
	 * 查询告警规则配置
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value="/alert/list", method=RequestMethod.POST)
	@ResponseBody
	public Pagination<Alert> queryAlert(@ModelAttribute AlertPageQuery pageQuery) {
		return filterService.pagination(pageQuery);
	}
	
	/**
	 * 跳转到告警规则页面
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping("/rule/list")
	public String listRule(@ModelAttribute("pageQuery") LasRulePageQuery pageQuery) {
		return "las.rule.list";
	}
	
	/**
	 * 查询告警规则配置
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value="/rule/list", method=RequestMethod.POST)
	@ResponseBody
	public Pagination<LasRule> queryRule(@ModelAttribute LasRulePageQuery pageQuery) {
		return lasRuleService.pagination(pageQuery);
	}
	
	
	/**
	 * 跳转到告警新建页面
	 * @return
	 */
	@RequestMapping(value="/rule/create", method=RequestMethod.GET)
	public String createRule(Model model) {
		LasRule lasRule = new LasRule();
		lasRule.setCreator(SecurityContextUtils.getCurrentUserInfo());
		lasRule.setCreateTime(new Date());
		model.addAttribute("lasRule", lasRule);
		return "las.rule.edit";
	}
	
	/**
	 * 跳转到告警新建页面
	 * @return
	 */
	@RequestMapping(value="/rule/create", method=RequestMethod.POST)
	public String createRule(@Valid@ModelAttribute LasRule lasRule, BindingResult result) {
		if(result.hasErrors()) {
			return "las.rule.edit";
		}
		lasRuleService.addRule(lasRule);
		return "redirect:/las/sim/rule/list";
	}
	
	/**
	 * 告警规则配置
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/rule/update/{id}", method=RequestMethod.GET)
	public String updateRule(@PathVariable("id") Long id, Model model) {
		model.addAttribute("lasRule", lasRuleService.findRule(id));
		return "las.rule.edit";
	}
	
	/**
	 * 告警规则配置保存
	 * @param rule
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/rule/update/{id}", method=RequestMethod.POST)
	public String updateRule(@Valid@ModelAttribute LasRule lasRule, BindingResult result) {
		if(result.hasErrors()) {
			return "las.rule.edit";
		}
		lasRuleService.mergeRule(lasRule);
		return "redirect:/las/sim/rule/list";
	}
	
	/**
	 * 删除规则
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/rule/remove/{id}", method=RequestMethod.POST)
	@ResponseBody
	public ActionStatus remove(@PathVariable("id") Long id) {
		lasRuleService.remove(id);
		return ActionStatus.SUCCESS;
	}
	
	/**
	 * 告警规则配置
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/alert/update/{id}", method=RequestMethod.GET)
	public String updateAlert(@PathVariable("id") String id, Model model) {
		model.addAttribute("alertRule", filterService.find(id));
		return "las.alert.edit";
	}
	
	/**
	 * 告警规则配置保存
	 * @param rule
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/alert/update/{id}", method=RequestMethod.POST)
	public String updateAlert(@Valid@ModelAttribute AlertRule rule, BindingResult result) {
		if(result.hasErrors()) {
            return "las.alert.edit";
        }
		alertRuleService.merge(rule);
		return "redirect:/las/sim/alert/list";
	}
	/**
	 * 跳转到告警计数页面
	 * @return
	 */
	@RequestMapping(value="/alert/countsEdit", method=RequestMethod.GET)
	public String countsEdit(@RequestParam("id") Long id,Model model) {
		
		RuleCondition con = null;
//		List<RuleCondition> list = new ArrayList<RuleCondition>();
//		for (int i = 0; i < 5; i++) {
//			con = new RuleCondition();
//			con.setId((long) i);
//			con.setName("事件"+i);
//			list.add(con);
//		}
		List<String> column = new ArrayList<String>();
		for (int i = 0; i < 10; i++) {
			column.add("字段"+i);
		}
		List<RuleCondition> list = ruleConditionService.findEveByRuleId(id);
		
		LasRuleCountsModel counts = new LasRuleCountsModel();
		counts.setConditions(list);
		counts.setColumns(column);
		model.addAttribute("ruleCounts", counts);
		return "las.alert.countsEdit";
	}
	
	/**
	 * 保存到告警计数页面
	 * @return
	 */
	@RequestMapping(value="/alert/countsEdit", method=RequestMethod.POST)
	public String countsEdit(Model model,@Valid@ModelAttribute LasRuleCountsModel countsModel,BindingResult result) {
		
		
		LasRuleCounts counts = new LasRuleCounts();
		counts.setCounts(countsModel.getCounts());
		counts.setTimes(countsModel.getTimes());
		counts.setUnit(countsModel.getUnit());
		counts.setAndAttr(countsModel.getAndAttr());
		
		lasRuleCountsService.addRule(counts);
		return "redirect:/las/sim/rule/list";
	}
}
