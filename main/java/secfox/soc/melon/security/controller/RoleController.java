/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.security.controller;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.security.domain.MenuResource;
import secfox.soc.melon.security.domain.Role;
import secfox.soc.melon.security.query.RolePageQuery;
import secfox.soc.melon.security.service.RoleService;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.ActionStatus;
import secfox.soc.melon.web.WebConstants;
import secfox.soc.melon.work.task.domain.DutyTaskGroup;
import secfox.soc.melon.work.task.service.DutyTaskGroupService;

/**
 * @since 1.0 2014年10月3日,上午9:51:46
 * 岗位角色控制器
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Controller
@RequestMapping("/security/role")
public class RoleController {
	
	private RoleService roleService;
	
	private DutyTaskGroupService dutyTaskGroupService;
	
	@Inject
	public RoleController(RoleService roleService,DutyTaskGroupService dutyTaskGroupService){
		super();
		this.roleService = roleService;
		this.dutyTaskGroupService = dutyTaskGroupService;
	}
	
	/**
	 * 跳转到角色添加页面
	 * @param role
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.GET)
	public String create(@ModelAttribute Role role) {
		role.setLevel(new Integer[]{0,1,2});//默认选中范围
		role.setCreateTime(new Date());
		return "security.role.edit";
	}
	
	/**
	 * 角色添加
	 * @param role
	 * @param result
	 * @param attribute
	 * @return
	 */
	@RequestMapping(value="/create", method=RequestMethod.POST)
	public String create(@Valid@ModelAttribute Role role, BindingResult result, RedirectAttributes attribute) {
		if(result.hasErrors()) {
            return "security.role.edit";
        }
		roleService.persist(role);
        attribute.addFlashAttribute(role);
        //添加提示信息
        ActionHint hint = ActionHint.create("security.role.create.hint", role.getName());
        attribute.addFlashAttribute(WebConstants.ACTION_HINT, hint);
        //导向到明细页面
        return "redirect:/security/role/list";
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/resource", method=RequestMethod.GET)
	@ResponseBody
	public Set<MenuResource> listResources(@RequestParam("id") Long id) {
		return roleService.findResources(id);
	}
	
	/**
	 * 跳转到修改页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		model.addAttribute("role", roleService.find(id));
		return "security.role.edit";
	}
	
	/**
	 * 保存角色修改
	 * @param role
	 * @param result
	 * @param model
	 * @param attribute
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.POST)
	public String update(@Valid@ModelAttribute Role role, BindingResult result, RedirectAttributes attribute) {
		if(result.hasErrors()) {
            return "security.role.edit";
        }
		roleService.merge(role);
        attribute.addFlashAttribute(role);
        //添加提示信息
        //添加提示信息
        ActionHint hint = ActionHint.create("security.role.update.hint", role.getName());
        attribute.addFlashAttribute(WebConstants.ACTION_HINT, hint);
        //导向到明细页面
        return "redirect:/security/role/show/"+role.getId();
	}
	
	/**
	 * 跳转到list页面
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(@ModelAttribute("pageQuery") RolePageQuery pageQuery) {
		return "security.role.list";
	}

	/**
	 * ajax列表查询
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value="/list", method=RequestMethod.POST)
	@ResponseBody
	public Pagination<Role> query(@ModelAttribute RolePageQuery pageQuery) {
		return roleService.findPage(pageQuery);
	}
	
	/**
     * 判断帐号的唯一性
     * @param request
     * @return
     */
    @RequestMapping(value="/checkUnique", method=RequestMethod.POST)
    public @ResponseBody boolean checkUnique(@RequestParam("code") String code) {
        Role rolect = roleService.findByCode(code);       
        return rolect == null;
    }
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/remove/{id}", method=RequestMethod.POST)
	@ResponseBody
	public ActionStatus remove(@PathVariable("id") Long id) {
		roleService.remove(id);
		return ActionStatus.SUCCESS;
	}
	
	/**
	 * 角色明细页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/show/{id}", method=RequestMethod.GET)
	public String show(@PathVariable("id") Long id, Model model) {
		Role role = (Role)model.asMap().get("role");
        if (role == null) {
        	role = roleService.find(id);
        }
        //查询岗位职责
        List<DutyTaskGroup> taskGroup = dutyTaskGroupService.findByRoleId(id);
		model.addAttribute("role", role);
		model.addAttribute("taskGroup", taskGroup);
        return "security.role.show";
	}
	
}
