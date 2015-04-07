package secfox.soc.melon.rule.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.rule.domain.EplValue;
import secfox.soc.melon.rule.domain.EplVar;
import secfox.soc.melon.rule.domain.Rule;
import secfox.soc.melon.rule.domain.Variable;
import secfox.soc.melon.rule.query.EplValuePageQuery;
import secfox.soc.melon.rule.service.EplValueService;
import secfox.soc.melon.web.ActionHint;


@Controller
@RequestMapping("/las/rule")
public class LasRuleController {
	
	private EplValueService service;
	private XStreamMarshaller marshaller;
	
	@Inject
	public LasRuleController(EplValueService service, XStreamMarshaller marshaller) {
		this.service = service;
		this.marshaller = marshaller;
	}
	
	/**
	 * 跳转到list页面
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(@ModelAttribute("pageQuery") EplValuePageQuery pageQuery) {
		return "las.rule.list";
	}
	
	/**
	 * ajax查询
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.POST)
	@ResponseBody
	public Pagination<EplValue> query(@ModelAttribute("pageQuery") EplValuePageQuery pageQuery) {
		return service.findPage(pageQuery);
	}
	
	/**
	 * 更改状态
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value="/changeState", method=RequestMethod.GET)
	public String changeState(HttpServletRequest req) {
		String id = req.getParameter("id");
		service.changeState(Long.valueOf(id));
		return "redirect:/las/rule/list";
	}

	/**
	 * 跳转到配置页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/config", method=RequestMethod.GET)
	public String config(Model model) {
		//获取模板信息
		List<EplValue> values = service.findTemplate();
		model.addAttribute("rules", values);
		return "las.rule.config";
	}
	
	/**
	 * ajax加载页面
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/load", method=RequestMethod.GET)
	public String load(HttpServletRequest req, Model model) {
		String id = req.getParameter("id");
		EplValue value = service.find(Long.parseLong(id));
		//描述信息
		String desc = "暂无描述信息";
		//参数输入框
		List<String> inputs = Lists.newArrayList();
		//是否可修改
		boolean state = true; 
		if(value != null) {
			Rule rule = (Rule) marshaller.getXStream().fromXML(value.getContent());
			EplVar eplVar = rule.getEplvars();
			if(eplVar != null) {
				desc = eplVar.getDesc();
				//取参数项
				List<Variable> vars = eplVar.getVars();
				if(vars == null) {
					state = false;
				} else {
					for(Variable var : vars){
						//生成页面输入框
						String[] params = {var.getName(), var.getValue()};
						inputs.add(MessageSourceUtils.getMessage("rule.input", params));
					}
				}
			} else {
				state = false;
			}
			
		}
		model.addAttribute("desc", desc);
		model.addAttribute("inputs", inputs);
		model.addAttribute("isMerge", state);
		return "las/rule/edit";
	}
	
	/**
	 * 保存修改
	 * @param value
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method = RequestMethod.POST)
	public @ResponseBody ActionHint update(@PathVariable("id") Long id, HttpServletRequest req) {
		EplValue eplValue = service.find(id);
		//将xml转为java对象
		Rule rule = (Rule) marshaller.getXStream().fromXML(eplValue.getContent());
		EplVar eplVar = rule.getEplvars();
		List<Variable> vars = eplVar.getVars();
		//获取key值集合
		List<String> keys = Lists.newArrayList();
		for(Variable var : vars) {
			keys.add(var.getName());
		}
		//清空原来的阈值信息
		vars.clear();
		//获取参数
		for(String key : keys) {
			String value = req.getParameter(key);
			//创件参数
			Variable var = new Variable(key, value);
			vars.add(var);
		}
		//将java对象转为xml
		String xml = marshaller.getXStream().toXML(rule);
		eplValue.setContent(xml);
		ActionHint actionHint = ActionHint.create("rule.update.hint", eplValue.getName());
		service.merge(eplValue);
		//添加操作协助提示(本处因为树性操作结构)，依赖于客户端代码，此句可不执行
		actionHint.setActionType(0);//设置为新增
		actionHint.setDomain(eplValue);
		return actionHint;
	}
	
	/**
	 * 跳转到配置页面
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/config/{id}", method=RequestMethod.GET)
	public String config(@PathVariable("id") Long id, Model model) {
		//获取模板信息
		EplValue value = service.find(id);
		//描述信息
		String desc = "暂无描述信息";
		//参数输入框
		List<String> inputs = Lists.newArrayList();
		//是否可修改
		boolean state = true; 
		if(value != null) {
			Rule rule = (Rule) marshaller.getXStream().fromXML(value.getContent());
			EplVar eplVar = rule.getEplvars();
			if(eplVar != null) {
				desc = eplVar.getDesc();
				//取参数项
				List<Variable> vars = eplVar.getVars();
				if(vars == null) {
					state = false;
				} else {
					for(Variable var : vars){
						//生成页面输入框
						String[] params = {var.getName(), var.getValue()};
						inputs.add(MessageSourceUtils.getMessage("rule.input", params));
					}
				}
			} else {
				state = false;
			}
			
		}
		model.addAttribute("value", value);
		model.addAttribute("desc", desc);
		model.addAttribute("inputs", inputs);
		model.addAttribute("isMerge", state);
		return "las.rule.edit";
	}
	

	/**
	 * 保存修改
	 * @param value
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/update", method = RequestMethod.POST)
	public String update(HttpServletRequest req) {
		EplValue eplValue = service.find(Long.valueOf(req.getParameter("id")));
		//将xml转为java对象
		Rule rule = (Rule) marshaller.getXStream().fromXML(eplValue.getContent());
		EplVar eplVar = rule.getEplvars();
		List<Variable> vars = eplVar.getVars();
		//获取key值集合
		List<String> keys = Lists.newArrayList();
		for(Variable var : vars) {
			keys.add(var.getName());
		}
		//清空原来的阈值信息
		vars.clear();
		//获取参数
		for(String key : keys) {
			String value = req.getParameter(key);
			//创建参数
			Variable var = new Variable(key, value);
			vars.add(var);
		}
		//将java对象转为xml
		String xml = marshaller.getXStream().toXML(rule);
		eplValue.setContent(xml);
		service.merge(eplValue);
		return "redirect:/las/rule/list";
	}

}
