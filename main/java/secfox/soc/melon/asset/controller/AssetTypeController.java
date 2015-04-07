/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.controller;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import secfox.soc.melon.asset.domain.AssetType;
import secfox.soc.melon.asset.service.AssetTypeService;
import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.security.util.SecurityContextUtils;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.HintLevel;


/**
 * 安全对象类型控制器 
 * @since @2014-9-22,@上午11:08:24
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
@Controller
@RequestMapping("/asset/type")
public class AssetTypeController {
	
	private AssetTypeService assetTypeService;
	
	@Inject
	public AssetTypeController(AssetTypeService assetTypeService) {
		super();
		this.assetTypeService = assetTypeService;
	}
	
	/**
	 * 添加页面
	 * @param parentId 父节点id
	 * @param assetType
	 * @return
	 */
	@RequestMapping(value="/create/{parentId}", method=RequestMethod.GET)
	public String update(@PathVariable("parentId") Long parentId, @ModelAttribute AssetType assetType) {
		assetType.setParentId(parentId);
		return "melon/asset/assetType/create";
	}
	
	/**
	 * 添加子节点
	 * @param parentId 父节点id
	 * @param assetType 子节点对象
	 * @param result
	 * @return
	 */
	@RequestMapping(value = "/create/{parentId}", method = RequestMethod.POST)
	@ResponseBody
	public ActionHint create(@PathVariable("parentId") Long parentId, @Valid@ModelAttribute AssetType assetType, BindingResult result) {
		//添加操作提示                        
		ActionHint actionHint = ActionHint.create("asset.assetType.create.hint", assetType.getName());
		if(result.hasErrors()) {
			actionHint.setLevel(HintLevel.ERROR);
		}
		assetTypeService.persist(assetType);
		//添加操作协助提示，依赖于客户端代码，此句可不执行
		actionHint.setActionType(0);//设置为新增
		actionHint.setDomain(assetType);
		return actionHint;
	}
	
	@RequestMapping("/tree")
	public String tree(){
		return "asset.assetType.tree";
	}
		
	/**
	 * 通过节点获取树
	 * @param id 节点id
	 * @return
	 */
	@RequestMapping(value="/find")
	public @ResponseBody List<AssetType> findTree(@RequestParam String id) {
		Long rootId = BaseConstants.ROOT_ID;
		try {
			rootId = Long.parseLong(id);
		} catch(NumberFormatException exception) {
			rootId = BaseConstants.ROOT_ID;
		}
		return assetTypeService.findTree(rootId,false);
	}
	/**
	 * 内容详细页面
	 * @param id 设备id
	 * @param model
	 * @return
	 */
	@RequestMapping("/show/{id}")
	public String show(@PathVariable Long id, Model model) {
		AssetType assetType = assetTypeService.find(id);
		model.addAttribute(assetType);
		return "asset.assetType.show";
	}
	/**
	 * ajax获取设备类型
	 * @return
	 */
	@RequestMapping(value="/findAll" , method=RequestMethod.GET)
	@ResponseBody
	public List<AssetType> findType (){
		return  assetTypeService.findAll();
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
			return "melon/asset/assetType/root";
		}
		AssetType assetType = assetTypeService.find(id);
		model.addAttribute("assetType", assetType);
		model.addAttribute("organId", SecurityContextUtils.getCurrentAccount().getCompanyId());
		return "melon/asset/assetType/create";
	}
	
	/**
	 * 更新设备类型
	 * @param organ
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ActionHint update(@PathVariable("id") Long id, @Valid@ModelAttribute AssetType assetType, BindingResult result) {
		//添加操作提示
		ActionHint actionHint = ActionHint.create("asset.assetType.update.hint", assetType.getName()); 
		if(result.hasErrors()) {
			actionHint.setLevel(HintLevel.ERROR);
		}
		assetTypeService.merge(assetType);
		//添加操作协助提示，依赖于客户端代码，此句可不执行
		actionHint.setActionType(1);//设置为新增
		actionHint.setDomain(assetType);//依赖于客户端代码，此句可不执行
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
		String res = assetTypeService.removeMe(id); //此方法可扩展删除信息提示的更详细（暂时不实现）
		String[] split = res.split(BaseConstants.SPLITER_FLAG);
		String str = split[0];
		//1.判断分割后是否为空，如果为空则删除成功
		if(split.length ==1 && StringUtils.isBlank(str)){
			ActionHint actionHint =  ActionHint.create("asset.abstract.remove.hint");
			actionHint.setActionType(2);
			actionHint.setDomain(true);
			return actionHint;
		}
		//2.如果不为空，则返回结果
		ActionHint actionHint = ActionHint.create("asset.type.rem.hint");
		actionHint.setActionType(2);
		actionHint.setDomain(false);
		return actionHint;
	}
	
	
	/**
	 * yb 通过节点获取树  
	 * @param type 
	 * @return
	 */
	@RequestMapping(value="/findCustomTree")
	public @ResponseBody List<AssetType> findCustomTree(@RequestParam int type) {
		return assetTypeService.findCustomTree(type);
	}
}


