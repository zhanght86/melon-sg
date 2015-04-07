/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.security.controller;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.security.domain.MenuResource;
import secfox.soc.melon.security.service.MenuResourceService;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.ActionStatus;
import secfox.soc.melon.web.HintLevel;

/**
 * 菜单\资源\仪表盘统一管理控制器
 * @since 1.0 2014年10月3日,下午4:50:47
 * 菜单\资源\仪表盘统一管理控制器
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0
 */
@Controller
@RequestMapping("/security/menuResource")
public class MenuResourceController {
	
	//资源管理服务
	private MenuResourceService menuResourceService;
	
	/**
	 * 构造器
	 * @param menuResourceService 资源管理服务
	 */
	@Inject 
	public MenuResourceController(MenuResourceService menuResourceService){
		super();
		this.menuResourceService = menuResourceService;
	}
	
	/**
	 * 
	 * @param menuResource
	 * @return
	 */
	@RequestMapping("/tree")
	public String tree(){
		return "security.resource.tree";
	}
	
	/**
	 * 通过id查询下面的子节点
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/find")
	public @ResponseBody List<MenuResource> find(@RequestParam String id) {
		Long rootId = BaseConstants.ROOT_ID;
		try {
			rootId = Long.parseLong(id);
		} catch(NumberFormatException exception) {
			rootId = BaseConstants.ROOT_ID;
		}
		return menuResourceService.findTree(rootId, false);
	}
	
	/**
	 * 选择符
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/choose")
	public @ResponseBody List<MenuResource> choose(@RequestParam String id) {
		Long rootId = BaseConstants.ROOT_ID;
		try {
			rootId = Long.parseLong(id);
		} catch(NumberFormatException exception) {
			rootId = BaseConstants.ROOT_ID;
		}
		return menuResourceService.findTree(rootId, true);
	}
	
	/**
	 * 添加页面
	 * @param parentId
	 * @param menuResource
	 * @return
	 */
	@RequestMapping(value="/create/{parentId}", method=RequestMethod.GET)
	public String create(@PathVariable("parentId") Long parentId, @ModelAttribute MenuResource menuResource){
		//设置父节点
		menuResource.setParentId(parentId);
		return "melon/security/resource/create";
	}
	
	/**
	 * 创建节点
	 * @param menuResource
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/create/{parentId}", method=RequestMethod.POST)
	public @ResponseBody ActionHint create(@PathVariable("parentId") Long parentId, @Valid@ModelAttribute MenuResource menuResource , BindingResult result){
		ActionHint actionHint = ActionHint.create("security.resource.create.hint", menuResource.getShortName());
		//如果服务器验证错误
		if(result.hasErrors()){
			actionHint.setLevel(HintLevel.ERROR);
		}
		menuResourceService.persist(menuResource);
		//添加操作协助提示，依赖于客户端代码，此句可不执行
		actionHint.setActionType(0);//设置为新增
		actionHint.setDomain(menuResource);
		return actionHint;
	}
	
	/**
     * 判断唯一性
     * @param request
     * @return
     */
    @RequestMapping(value="/checkUnique", method=RequestMethod.POST)
    public @ResponseBody boolean checkUnique(@RequestParam("url") String url) {
    	MenuResource menuct = menuResourceService.checkUniqueUrl(url);       
        return menuct == null;
    }
    
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/remove/{id}")
	public @ResponseBody ActionStatus remove(@PathVariable("id") Long id){
		menuResourceService.remove(id);
		return ActionStatus.SUCCESS;
	}
	
	/**
	 * 修改页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model){
		//返回根节点说明页面
		if(id == BaseConstants.ROOT_ID) {
			return "melon/security/resource/root";
		}
		MenuResource menu = menuResourceService.find(id);
		model.addAttribute("menuResource", menu);
		return "melon/security/resource/create";
	}
	
	/**
	 * 修改
	 * @param id
	 * @param menuResource
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.POST)
	public @ResponseBody ActionHint update(@PathVariable("id") Long id, @Valid@ModelAttribute MenuResource menuResource , BindingResult result){
		ActionHint actionHint = ActionHint.create("security.resource.update.hint", menuResource.getShortName());
		//如果错误
		if(result.hasErrors()){
			actionHint.setLevel(HintLevel.ERROR);
		}
		menuResourceService.merge(menuResource);
		//添加操作协助提示，依赖于客户端代码，此句可不执行
		actionHint.setActionType(1);//设置为新增
		actionHint.setDomain(menuResource);//依赖于客户端代码，此句可不执行
		return actionHint;
	}
	
}
