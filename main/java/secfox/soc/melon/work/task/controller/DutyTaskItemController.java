/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.work.task.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.security.domain.Account;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.ActionStatus;
import secfox.soc.melon.web.WebConstants;
import secfox.soc.melon.work.task.domain.DutyTaskItem;
import secfox.soc.melon.work.task.query.DutyTaskItemPageQuery;
import secfox.soc.melon.work.task.service.DutyTaskItemService;

/**
 * 
 * @author  <a href="mailto:gaobg@legendsec.com">高保国</a>
 * @since   2014-11-11
 * @version  1.0
 */
@Controller
@RequestMapping("/work/dutyTaskItem")
public class DutyTaskItemController {

	private DutyTaskItemService dutyTaskItemService;
	
	@Inject
	public DutyTaskItemController(DutyTaskItemService dutyTaskItemService){
		super();
		this.dutyTaskItemService = dutyTaskItemService;
	}
	/**
	 * 跳转到list页面
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(@ModelAttribute("pageQuery") DutyTaskItemPageQuery pageQuery) {
		return "work.taskItem.list";
	}
	/**
	 * ajax列表查询
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.POST)
	@ResponseBody
	public Pagination<DutyTaskItem> query(@ModelAttribute DutyTaskItemPageQuery pageQuery) {
		return dutyTaskItemService.findPage(pageQuery);
	}
	
	/**
	 * 跳转到添加页面
	 * @param taskItem
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String create(@ModelAttribute DutyTaskItem taskItem) {
		return "work.taskItem.edit";
	}
	
	/**
	 * 添加
	 * @param taskItem
	 * @param result
	 * @param attribute
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(@Valid@ModelAttribute DutyTaskItem taskItem, BindingResult result, RedirectAttributes attribute) {
		if(result.hasErrors()) {
            return "work.taskItem.edit";
        }
		dutyTaskItemService.persist(taskItem);
        attribute.addFlashAttribute(taskItem);
        //添加提示信息
        ActionHint hint = ActionHint.create("security.role.create.hint", taskItem.getName());
        attribute.addFlashAttribute(WebConstants.ACTION_HINT, hint);
        //导向到明细页面
        return "redirect:/work/dutyTaskItem/list";
	}
	/**
	 * 跳转到编辑页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		DutyTaskItem taskItem = dutyTaskItemService.find(id);
		model.addAttribute("dutyTaskItem", taskItem);
		return "work.taskItem.edit";
	}
	/**
	 * 编辑
	 * @param taskItem
	 * @param result
	 * @param attribute
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.POST)
	public String update(@Valid@ModelAttribute DutyTaskItem taskItem, BindingResult result, RedirectAttributes attribute) {
		if(result.hasErrors()) {
            return "work.taskItem.edit";
        }
		dutyTaskItemService.merge(taskItem);
        attribute.addFlashAttribute(taskItem);
        //添加提示信息
        ActionHint hint = ActionHint.create("security.role.create.hint", taskItem.getName());
        attribute.addFlashAttribute(WebConstants.ACTION_HINT, hint);
        //导向到明细页面
        return "redirect:/work/dutyTaskItem/list";
	}
	/**
	 * 跳转到明细页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/show/{id}", method=RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model model) {
		DutyTaskItem taskItem = dutyTaskItemService.find(id);
		model.addAttribute("dutyTaskItem", taskItem);
		return "work.taskItem.show";
	}
	/**
	 * 跳转到明细页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/remove/{id}", method=RequestMethod.POST)
	@ResponseBody
	public ActionStatus remove(@PathVariable("id") Long id) {
		dutyTaskItemService.remove(id);
		return ActionStatus.SUCCESS;
	}
	/**
	 * 提供查询所有dutyItem接口
	 * @param organId
	 * @return
	 */
	@RequestMapping(value="/findUsers",method=RequestMethod.GET)
	@ResponseBody
	public List<DutyTaskItem> findUsers(HttpServletRequest req){
		String roleId = req.getParameter("roleId");
		List<DutyTaskItem> taskItems = Lists.newArrayList();
		/*if(StringUtils.isBlank(roleId)){
			accounts = accountService.findUsers(-1l);	
		}else{
			accounts = accountService.findUsers(Long.valueOf(roleId));
		
		}*/
		return taskItems;
	}
}
