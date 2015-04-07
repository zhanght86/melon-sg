/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.controller;

import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import secfox.soc.melon.asset.domain.AssetGroup;
import secfox.soc.melon.asset.query.AssetGroupPageQuery;
import secfox.soc.melon.asset.service.AssetGroupService;
import secfox.soc.melon.asset.service.DeviceService;
import secfox.soc.melon.asset.service.InfoSystemService;
import secfox.soc.melon.asset.tool.AssetToolClass;
import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.organ.domain.Organization;
import secfox.soc.melon.organ.serivce.OrganizationService;
import secfox.soc.melon.security.domain.Account;
import secfox.soc.melon.security.util.SecurityContextUtils;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.ActionStatus;
import secfox.soc.melon.web.HintLevel;

/**
 * 安全域控制器
 * @since @2014-10-10,@下午4:50:39
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
@Controller
@RequestMapping("/asset/group")
public class AssetGroupController {
	private AssetGroupService assetGroupService;
	private DeviceService deviceService;
	private InfoSystemService infoSystemService;
	private OrganizationService organizationService;
	private AssetToolClass assetToolClass;
	@Inject
	public AssetGroupController(AssetToolClass assetToolClass,OrganizationService organizationService,InfoSystemService infoSystemService,DeviceService deviceService,AssetGroupService assetGroupService) {
		this.assetToolClass = assetToolClass;
		this.assetGroupService = assetGroupService;
		this.deviceService = deviceService;
		this.infoSystemService = infoSystemService;
		this.organizationService = organizationService;
		
	}

	/**
	 * 添加页面
	 * @param parentId 父节点id
	 * @param assetType
	 * @return
	 */
	@RequestMapping(value="/create/{parentId}", method=RequestMethod.GET)
	public String update(Model model,@PathVariable("parentId") Long parentId, @ModelAttribute AssetGroup assetGroup) {
		assetGroup.setParentId(parentId);
		model.addAttribute("organId", SecurityContextUtils.getCurrentAccount().getCompanyId());
		String domainId = String.valueOf(assetToolClass.getValueByKey("type.domain"));
		model.addAttribute("domainId", domainId);
		return "melon/asset/assetGroup/create";
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
	public ActionHint create(@PathVariable("parentId") Long parentId, @Valid@ModelAttribute AssetGroup assetGroup, BindingResult result) {
		//添加操作提示                        
		ActionHint actionHint = ActionHint.create("asset.assetGroup.create.hint", assetGroup.getName());
		if(result.hasErrors()) {
			actionHint.setLevel(HintLevel.ERROR);
		}
		assetGroupService.persist(assetGroup);
		//添加操作协助提示，依赖于客户端代码，此句可不执行
		actionHint.setActionType(0);//设置为新增
		actionHint.setDomain(assetGroup);
		return actionHint;
	}
	
	/**
	 * 根据单位id过滤
	 * @return
	 */
	@RequestMapping("/findByOrgan")
	@ResponseBody
	public List<AssetGroup> findByOrgan(){
		//TODO 现在没有实现通过单位id过滤
		return assetGroupService.findByOrgan(0l);
	}
	
	/**
	 * 通过节点获取树
	 * @param id 节点id
	 * @return
	 */
	@RequestMapping(value="/find")
	public @ResponseBody List<AssetGroup> findTree(@RequestParam String id) {
		Long rootId = BaseConstants.ROOT_ID;
		try {
			rootId = Long.parseLong(id);
		} catch(NumberFormatException exception) {
			rootId = BaseConstants.ROOT_ID;
		}
		return assetGroupService.findTree(rootId,false);
	}
	
	
	/**
	 * 单位设备视图
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String listOrgan(Model model) {
		List<Object[]> list= assetGroupService.getHomeView();
		model.addAttribute("homeViewDb",list);
		return "asset.assetGroup.view.home";
	}
	
	/**
	 * 根据id获取 
	 * @param groupId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/byGroupId/{groupId}", method=RequestMethod.GET)
	public String listById(@PathVariable("groupId") Long groupId,Model model) {
		model.addAttribute("groupName",assetGroupService.find(groupId));
		model.addAttribute("assetTable", deviceService.findByAssetGroup(groupId));
		model.addAttribute("sysTable", infoSystemService.findByAssetGroup(groupId));
		return "asset.assetGroup.list.byGroupId";
	}
	
	/**
	 * 删除安全域
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/remove/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ActionStatus remove(@PathVariable("id") Long id) {
		assetGroupService.remove(id);
		return ActionStatus.SUCCESS;
	}
	
	/**
	 * 明细安全域
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/show/{id}")
	public String show(@PathVariable("id") Long id,Model model) {
		model.addAttribute("assetGroup", assetGroupService.find(id));
		return "asset.assetGroup.show";
	}
	
	
	/**
	 * 访问安全域树
	 * @return
	 */
	@RequestMapping("/tree")
	public String tree(){
		return "asset.assetGroup.tree";
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
			return "melon/asset/assetGroup/root";
		}
		AssetGroup  assetGroup  = assetGroupService.find(id);
		model.addAttribute("assetGroup", assetGroup);
		model.addAttribute("organId", SecurityContextUtils.getCurrentAccount().getCompanyId());
		//获取类型中的安全域id
		String domainId = String.valueOf(assetToolClass.getValueByKey("type.domain"));
		model.addAttribute("domainId", domainId);
		return "melon/asset/assetGroup/create";
	}
	
	
	
	/**
	 * 更新设备类型
	 * @param organ
	 * @param result
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method = RequestMethod.POST)
	@ResponseBody
	public ActionHint update(@PathVariable("id") Long id, @Valid@ModelAttribute AssetGroup  assetGroup, BindingResult result) {
		//添加操作提示
		ActionHint actionHint = ActionHint.create("asset.assetGroup.update.hint", assetGroup.getName()); 
		if(result.hasErrors()) {
			actionHint.setLevel(HintLevel.ERROR);
		}
		assetGroup.setCreateTime(new Date());
		assetGroupService.merge(assetGroup);
		//添加操作协助提示，依赖于客户端代码，此句可不执行
		actionHint.setActionType(1);//设置为新增
		actionHint.setDomain(assetGroup);//依赖于客户端代码，此句可不执行
		return actionHint;
	}
	
	/**
	 * yb 跳转到安全域行政区域视图
	 */
	@RequestMapping(value="/listByPol")
	public String listByPol(Model model){
		Long parentId = null;
		//1.获取登录人所在单位
		Account currentAccount = SecurityContextUtils.getCurrentAccount();
		if(currentAccount !=null){
			Long companyId = currentAccount.getCompanyId();
			//2.根据单位id查出organ
			Organization find = organizationService.find(companyId);
			if(find !=null){
				parentId = find.getParentId();
			}
		}
		//3.根据organ.parid给RootID赋值
		model.addAttribute("rootId", parentId);
		return "asset.assetGroup.view.byPol";
	}
	
	/**
	 * yb 安全域行政区域视图中内容
	 * @param model
	 * @param polPath	行政区域路径
	 * @return
	 */
    @RequestMapping(value = "/byPolGrid/{polPath}", method = RequestMethod.GET)
	public String byPolGrid(Model model,@PathVariable("polPath") String polPath) {
    	model.addAttribute("pageQuery", new AssetGroupPageQuery());
    	model.addAttribute("polPath", polPath);
		return "melon/asset/assetGroup/grid/polGrid";
	}
    
    /**
     * yb 安全域行政区域视图中内容
     * @param req
     * @param pageQuery
     * @return
     */
    @RequestMapping(value = "/listByPol", method = RequestMethod.POST)
    @ResponseBody
	public Pagination<AssetGroup> byPolgrid(HttpServletRequest req,@ModelAttribute AssetGroupPageQuery pageQuery) {
    	String polPath = req.getParameter("polPath");
    	AssetGroup searchForm = pageQuery.getSearchForm();
    	if(StringUtils.isNotBlank(polPath)){
    		searchForm.setOrganPath(polPath);
    	}
    	searchForm.setOrganId(SecurityContextUtils.getCurrentAccount().getCompanyId());
    	pageQuery.setSearchForm(searchForm);
		return assetGroupService.findPages(pageQuery);
	}
    
    /**
     * yb 跳转到安全域类型视图
     * @param rootId 根节点id
     * @param model	
     * @return
     */
	@RequestMapping(value="/listByType")
	public String listByType(Model model){
		String domainId = String.valueOf(assetToolClass.getValueByKey("type.domain"));
		model.addAttribute("domainId", domainId);
		return "asset.assetGroup.view.byType";
	}
	
	/**
	 * yb 安全域类型视图中内容
	 * @param model	模型 
	 * @param typePath 类型路径
	 * @return
	 */
    @RequestMapping(value = "/byTypeGrid/{typePath}", method = RequestMethod.GET)
	public String byTypeGrid(Model model,@PathVariable("typePath") String typePath) {
    	model.addAttribute("pageQuery", new AssetGroupPageQuery());
    	model.addAttribute("typePath", typePath);
		return "melon/asset/assetGroup/grid/typeGrid";
	}
    
    /**
     * yb 安全域类型视图中内容
     * @param req	请求对象
     * @param pageQuery	查询对象
     * @return	安全域的分页对象
     */
    @RequestMapping(value = "/listByType", method = RequestMethod.POST)
    @ResponseBody
	public Pagination<AssetGroup> byTypeGrid(HttpServletRequest req,@ModelAttribute AssetGroupPageQuery pageQuery) {
    	String typePath = req.getParameter("typePath");
    	AssetGroup searchForm = pageQuery.getSearchForm();
    	if(StringUtils.isNotBlank(typePath)){
    		searchForm.setTypePath(typePath);
    	}
    	searchForm.setOrganId(SecurityContextUtils.getCurrentAccount().getCompanyId());
    	pageQuery.setSearchForm(searchForm);
		return assetGroupService.findPages(pageQuery);
	}
	
}


