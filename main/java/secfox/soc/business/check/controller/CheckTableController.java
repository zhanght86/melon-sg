/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.business.check.controller;

import java.text.DateFormat;
import java.util.Date;

import javax.inject.Inject;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import secfox.soc.business.check.domain.CheckTable;
import secfox.soc.business.check.query.CheckTablePageQuery;
import secfox.soc.business.check.service.CheckTableService;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.ActionStatus;
import secfox.soc.melon.web.HintLevel;

/**
 * 安全检查表格注册控制器
 * @since 1.0 2014年9月29日
 * @author <a href="mailto:fengxy@legendsec.com">冯夏彦</a>
 * @version 1.0
 */
@Controller
@RequestMapping("/business/checkTable")
public class CheckTableController {
	
	private CheckTableService checkTableService;

	/**
	 * service注入
	 * @param alarmService
	 */
	@Inject
	public CheckTableController(CheckTableService checkTableService) {
		super();
		this.checkTableService = checkTableService;
	}
	
	/**
	 * 跳转到添加页面
	 * @param infoSystem
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(@ModelAttribute CheckTable checkTable, Model model) {
		String newDate = DateFormat.getDateInstance().format(new Date());
		model.addAttribute("maxDate",newDate);
		return "check.edit";
	}
	
	/**
	 * 添加信息
	 * @param infoSystem
	 * @param result
	 * @param attribute
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("checkTable") CheckTable checkTable,BindingResult result, RedirectAttributes attribute) {
		//添加操作提示                        
		ActionHint actionHint = ActionHint.create("check.create.hint", checkTable.getTitle());
		if(result.hasErrors()) {
			actionHint.setLevel(HintLevel.ERROR);
		}
		checkTableService.persist(checkTable);
		//添加操作协助提示，依赖于客户端代码，此句可不执行
		actionHint.setActionType(0);//设置为新增
		actionHint.setDomain(checkTable);
        //导向到明细页面
        return "redirect:/check/list";
	}
	
	/**
	 * 跳转到修改页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		model.addAttribute("checkTable", checkTableService.find(id));
		return "check.edit";
	}
	
	/**
	 * 保存账号信息修改
	 * @param account
	 * @param result
	 * @param model
	 * @param attribute
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.POST)
	public String update(@PathVariable("id") Long id,@Valid@ModelAttribute CheckTable checkTable, BindingResult result, RedirectAttributes attribute) {
		//添加操作提示                        
		ActionHint actionHint = ActionHint.create("check.update.hint", checkTable.getTitle());
		if(result.hasErrors()) {
            return "check.edit";
        }
		checkTableService.merge(checkTable);
		//添加操作协助提示，依赖于客户端代码，此句可不执行
		actionHint.setActionType(1);//设置为修改
		actionHint.setDomain(checkTable);
        //导向到明细页面
        return "redirect:/check/list";
	}

	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/remove/{id}")
	public @ResponseBody ActionStatus remove(@PathVariable("id") Long id) {
		checkTableService.remove(id);
		return ActionStatus.SUCCESS;
	}
	
	/**
	 * 跳转到list页面
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@ModelAttribute("pageQuery") CheckTablePageQuery pageQuery) {
		return "check.list";
	}
	
	/**
	 * 进行ajax查询获取安全检查表格注册列表信息
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public @ResponseBody Pagination<CheckTable> query(@ModelAttribute("pageQuery") CheckTablePageQuery pageQuery) {
		return checkTableService.findPages(pageQuery);
	}
	
	/**
	 * 
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/show/{id}", method = RequestMethod.GET)
	public String show(@PathVariable Long id, Model model) {
		CheckTable checkTable = (CheckTable) model.asMap().get("checkTable");
		if (checkTable == null) {
			checkTable = checkTableService.find(id);
		}
		model.addAttribute("checkTable", checkTable);
		return "check.show";
	}
}
