/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.asset.controller;

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

import secfox.soc.melon.asset.domain.PhyEnvironment;
import secfox.soc.melon.asset.service.PhyEnvironmentService;
import secfox.soc.melon.asset.tool.AssetToolClass;
import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.security.util.SecurityContextUtils;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.HintLevel;

/**
 * @since 2014-4-18,上午10:39:55
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Controller
@RequestMapping(value="/asset/environment")
public class PhyEnvironmentController {
	
	private PhyEnvironmentService phyService;
	private AssetToolClass assetToolClass;
	
	@Inject
	public PhyEnvironmentController(AssetToolClass assetToolClass,PhyEnvironmentService phyService){
		super();
		this.phyService = phyService;
		this.assetToolClass =assetToolClass;
	}

	/**
	 * 添加页面
	 * @param parentId 父节点id
	 * @param phyEnvironment
	 * @return
	 */
	@RequestMapping(value="/create/{parentId}", method=RequestMethod.GET)
	public String update(@PathVariable("parentId") Long parentId,Model model, @ModelAttribute PhyEnvironment phyEnvironment) {
		phyEnvironment.setParentId(parentId);
		
		 //获取物理区域的id 
		 String enviId = String.valueOf(assetToolClass.getValueByKey("type.envi"));
		 model.addAttribute("enviId", enviId);
		 return "melon/asset/phyEnvironment/create";
	}
	
	/**
	 * 添加子节点
	 * @param parentId 父节点id
	 * @param phyEnvironment 物理环境
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/create/{parentId}", method = RequestMethod.POST)
	@ResponseBody
	public ActionHint create(@PathVariable("parentId") Long parentId, @Valid@ModelAttribute PhyEnvironment phyEnvironment, BindingResult result) {
		//添加操作提示                        
		ActionHint actionHint = ActionHint.create("asset.phyEnvironment.create.hint", phyEnvironment.getName());
		if(result.hasErrors()) {
			actionHint.setLevel(HintLevel.ERROR);
		}
		phyService.persist(phyEnvironment);
		//添加操作协助提示，依赖于客户端代码，此句可不执行
		actionHint.setActionType(0);//设置为新增
		actionHint.setDomain(phyEnvironment);
		return actionHint;
	}
	
	@RequestMapping("/tree")
	public String tree(){
		return "asset.phyEnvironment.tree";
	}
		
	/**
	 * 通过节点获取树
	 * @param id 节点id
	 * @return
	 */
	@RequestMapping(value="/find")
	public @ResponseBody List<PhyEnvironment> findTree(@RequestParam String id) {
		Long rootId = BaseConstants.ROOT_ID;
		try {
			rootId = Long.parseLong(id);
		} catch(NumberFormatException exception) {
			rootId = BaseConstants.ROOT_ID;
		}
		return phyService.findTree(rootId);
	}
	/**
	 * 内容详细页面
	 * @param id 设备id
	 * @param model
	 * @return
	 */
	@RequestMapping("/show/{id}")
	public String show(@PathVariable Long id, Model model) {
		PhyEnvironment  phyEnvironment = phyService.find(id);
		model.addAttribute(phyEnvironment);
		return "asset.phyEnvironment.show";
	}
	
	/**
	 * ajax获取设备类型
	 * @return
	 */
	@RequestMapping(value="/findAll" , method=RequestMethod.GET)
	@ResponseBody
	public List<PhyEnvironment> findType (){
		return  phyService.findAll();
	}
	
	/**
	 * 更新页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		//返回根节点说明页面
		if(id == BaseConstants.ROOT_ID) {
			return "melon/asset/phyEnvironment/root";
		}
		//获取物理区域的id
		String enviId = String.valueOf(assetToolClass.getValueByKey("type.envi"));
		model.addAttribute("enviId", enviId);
		PhyEnvironment  phyEnvironment = phyService.find(id);
		model.addAttribute("phyEnvironment", phyEnvironment);
		return "melon/asset/phyEnvironment/create";
	}
	
	/**
	 * 更新设备类型
	 * @param id
	 * @param phyEnvironment
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ActionHint update(@PathVariable("id") Long id, @Valid@ModelAttribute PhyEnvironment phyEnvironment, BindingResult result) {
		//添加操作提示
		ActionHint actionHint = ActionHint.create("asset.phyEnvironment.update.hint", phyEnvironment.getName()); 
		if(result.hasErrors()) {
			actionHint.setLevel(HintLevel.ERROR);
		}
		phyEnvironment.setUserInfo(SecurityContextUtils.getCurrentUserInfo());
		phyService.merge(phyEnvironment);
		
		//添加操作协助提示，依赖于客户端代码，此句可不执行
		actionHint.setActionType(1);//设置为新增
		actionHint.setDomain(phyEnvironment);//依赖于客户端代码，此句可不执行
		return actionHint;
	}
	
	/**
	 * 删除设备类型
	 * @param organ
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/remove/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ActionHint remove(@PathVariable("id") Long id) {
		boolean deleteEnvi = phyService.deleteEnvi(id);
		if(deleteEnvi){
			ActionHint actionHint =  ActionHint.create("asset.abstract.remove.hint");
			actionHint.setActionType(2);
			actionHint.setDomain(deleteEnvi);
			return actionHint;
		}else{
			ActionHint actionHint = ActionHint.create("asset.environment.rem.hint");
			actionHint.setActionType(2);
			actionHint.setDomain(deleteEnvi);
			return actionHint;
		}
	}
	


}
