/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.las.controller;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.elasticsearch.common.collect.Lists;
import org.elasticsearch.common.collect.Maps;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import secfox.soc.las.domain.RuleCondition;
import secfox.soc.las.service.RuleConditionService;
import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.ActionStatus;
import secfox.soc.melon.web.HintLevel;

/**
 * @since 2015-2-2,下午4:00:10
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Controller
@RequestMapping("/las/condition")
public class RuleConController {
	
	private RuleConditionService conditionService;
	
	@Inject
	public RuleConController(RuleConditionService conditionService) {
		this.conditionService = conditionService;
	}
	
	/**
	 * 树页面
	 * @return
	 */
	@RequestMapping("/tree")
	public String tree(@RequestParam("id") Long ruleId, Model model) {
		model.addAttribute("ruleId", ruleId);
		return "las.condition.tree";
	}
	
	/**
	 * 获取数据
	 * @param ruleType 规则类型
	 * @return
	 */
	@RequestMapping("/find/{ruleType}/{ruleId}")
	@ResponseBody
	public List<RuleCondition> findTree(@PathVariable("ruleType") int ruleType, @PathVariable("ruleId") Long ruleId) {
		return conditionService.findTree(ruleType, ruleId);
	}
	
	/**
	 * 加载编辑页面
	 * @param id 条件id
	 * @param model
	 * @return
	 */
	@RequestMapping("/update/{id}")
	public String update(@PathVariable("id") Long id, Model model) {
		//返回根节点说明页面
		if(id == BaseConstants.ROOT_ID) {
			return "las/condition/root";
		}
		RuleCondition condition = conditionService.find(id);
		model.addAttribute("condition", condition);
		//如果为具体条件节点，需要提供可选字段
		if(condition.getConditionType() == 2) {
			//测试
			List<Map<String, String>> fields = Lists.newArrayList();
			for(int i=0;i<10;i++) {
				Map<String, String> field = Maps.newHashMap();
				field.put("value", "type"+i);
				field.put("text", "字段"+i);
				fields.add(field);
			}
			
			model.addAttribute("fields", fields);
		}
		return "las/condition/edit";
	}
	
	/**
	 * 保存修改
	 * @param id 
	 * @param condition
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ActionHint update(@PathVariable("id") Long id, @Valid@ModelAttribute("condition") RuleCondition condition, BindingResult result) {
		ActionHint actionHint = ActionHint.create("las.condition.update.hint", condition.getName());
		if(result.hasErrors()) {
			actionHint.setLevel(HintLevel.ERROR);
		}
		conditionService.merge(condition);
		actionHint.setActionType(1);//设置为新增
		actionHint.setDomain(condition);//依赖于客户端代码，此句可不执行
		return actionHint;
	}
	
	/**
	 * 加载新建页面
	 * @param parentId 父节点id
	 * @param ruleType 规则类型
	 * @param condType 条件类型
	 * @param condition
	 * @return
	 */
	@RequestMapping(value="/create/{parentId}", method = RequestMethod.GET)
	public String create(@PathVariable("parentId") Long parentId, @RequestParam("ruleType") int ruleType, @RequestParam("condType") int condType, @RequestParam("ruleId") Long ruleId, HttpServletRequest request, Model model) {
		RuleCondition condition = new RuleCondition();
		condition.setParentId(parentId);
		condition.setConditionType(condType);
		condition.setRuleType(ruleType);
		condition.setRuleId(ruleId);
		model.addAttribute("condition", condition);
		//如果为具体条件节点，需要提供可选字段
		if(condType == 2) {
			//测试
			List<Map<String, String>> fields = Lists.newArrayList();
			for(int i=0;i<10;i++) {
				Map<String, String> field = Maps.newHashMap();
				field.put("value", "type"+i);
				field.put("text", "字段"+i);
				fields.add(field);
			}
			
			model.addAttribute("fields", fields);
		}
		return "las/condition/edit";
	}
	
	/**
	 * 新建条件
	 * @param parentId
	 * @param condition
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/create/{parentId}", method = RequestMethod.POST)
	@ResponseBody
	public ActionHint create(@PathVariable("parentId") Long parentId, @Valid@ModelAttribute("condition") RuleCondition condition, BindingResult result) {
		ActionHint actionHint = ActionHint.create("las.condition.create.hint", condition.getName());
		//如果服务器验证错误
		if(result.hasErrors()){
			actionHint.setLevel(HintLevel.ERROR);
		}
		List<RuleCondition> results = conditionService.add(condition);
		actionHint.setActionType(0);//设置为新增
		actionHint.setDomain(results);
		return actionHint;
	}
	
	/**
	 * 删除操作
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/remove/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ActionStatus remove(@PathVariable("id") Long id) {
		conditionService.remove(id);
		return ActionStatus.SUCCESS;
	}

}
