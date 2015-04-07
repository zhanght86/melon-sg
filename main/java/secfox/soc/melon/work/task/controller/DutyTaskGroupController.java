/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.work.task.controller;

import java.util.List;

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

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.util.UUIDUtils;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.ActionStatus;
import secfox.soc.melon.web.WebConstants;
import secfox.soc.melon.work.task.domain.DutyTaskGroup;
import secfox.soc.melon.work.task.query.DutyTaskGroupPageQuery;
import secfox.soc.melon.work.task.service.DutyTaskGroupService;

/**
 * 
 * @author  <a href="mailto:gaobg@legendsec.com">高保国</a>
 * @since   2014-11-12
 * @version  1.0
 */
@Controller
@RequestMapping("/work/dutyTaskGroup")
public class DutyTaskGroupController {

	private DutyTaskGroupService dutyTaskGroupService;
	
	private UUIDUtils uuidUtils;
	
	@Inject
	public DutyTaskGroupController(DutyTaskGroupService dutyTaskGroupService, UUIDUtils uuidUtils){
		super();
		this.dutyTaskGroupService = dutyTaskGroupService;
		this.uuidUtils = uuidUtils;
	}
	/**
	 * 跳转到list页面
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(@ModelAttribute("pageQuery") DutyTaskGroupPageQuery pageQuery) {
		return "work.taskGroup.list";
	}
	/**
	 * ajax列表查询
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.POST)
	@ResponseBody
	public Pagination<DutyTaskGroup> query(@ModelAttribute DutyTaskGroupPageQuery pageQuery) {
		return dutyTaskGroupService.findPage(pageQuery);
	}
	/**
	 * 跳转到添加页面
	 * @param taskItem
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String create(@ModelAttribute DutyTaskGroup taskGroup) {
		String additionId = uuidUtils.generate(DutyTaskGroup.class);
		taskGroup.setAdditionId(additionId);
		return "work.taskGroup.edit";
	}
	
	/**
	 * 添加
	 * @param taskItem
	 * @param result
	 * @param attribute
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(@Valid@ModelAttribute DutyTaskGroup taskGroup, BindingResult result, RedirectAttributes attribute) {
		if(result.hasErrors()) {
            return "work.taskGroup.edit";
        }
		dutyTaskGroupService.persist(taskGroup);
        attribute.addFlashAttribute(taskGroup);
        //添加提示信息
        ActionHint hint = ActionHint.create("security.role.create.hint", taskGroup.getName());
        attribute.addFlashAttribute(WebConstants.ACTION_HINT, hint);
        //导向到明细页面
        return "redirect:/work/dutyTaskGroup/list";
	}
	/**
	 * 跳转到编辑页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		DutyTaskGroup taskGroup = dutyTaskGroupService.find(id);
		model.addAttribute("dutyTaskGroup", taskGroup);
		return "work.taskGroup.edit";
	}
	/**
	 * 编辑
	 * @param taskItem
	 * @param result
	 * @param attribute
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.POST)
	public String update(@Valid@ModelAttribute DutyTaskGroup taskGroup, BindingResult result, RedirectAttributes attribute) {
		if(result.hasErrors()) {
            return "work.taskGroup.edit";
        }
		dutyTaskGroupService.merge(taskGroup);
        attribute.addFlashAttribute(taskGroup);
        //添加提示信息
        ActionHint hint = ActionHint.create("security.role.create.hint", taskGroup.getName());
        attribute.addFlashAttribute(WebConstants.ACTION_HINT, hint);
        //导向到明细页面
        return "redirect:/work/dutyTaskGroup/list";
	}
	/**
	 * 跳转到明细页面
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/show/{id}", method=RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model model) {
		DutyTaskGroup taskGroup = dutyTaskGroupService.find(id);
		model.addAttribute("dutyTaskGroup", taskGroup);
		return "work.taskGroup.show";
	}
	/**
	 * 定义岗责跳转
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/addDutyGroup/{roleId}", method=RequestMethod.GET)
	public String addDutyGroup(@PathVariable("roleId") Long roleId,Model model) {
		//根据roleId查询该角色是否已定义岗责,如果已定义，则跳转到编辑页面
		List<DutyTaskGroup> groups = dutyTaskGroupService.findByRoleId(roleId);
		DutyTaskGroup dutyTaskGroup = new DutyTaskGroup();
		if(groups != null && groups.size()>0){
			dutyTaskGroup = groups.get(0);
		}
		dutyTaskGroup.setRoleId(roleId);
		model.addAttribute("dutyTaskGroup", dutyTaskGroup);
		return "work.taskGroup.edit";
	}
	/**
	 * 定义岗责跳转
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/addDutyGroup/{roleId}", method=RequestMethod.POST)
	public String editDutyGroup(@Valid@ModelAttribute DutyTaskGroup taskGroup,Model model) {
		if(taskGroup.hasId()){
			dutyTaskGroupService.merge(taskGroup);
		}else{
			dutyTaskGroupService.persist(taskGroup);
		}
		return "redirect:/security/role/list";
	}
	
}
