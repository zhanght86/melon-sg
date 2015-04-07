/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.controller;


import java.io.IOException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.codehaus.jettison.json.JSONWriter;
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

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.primitives.Longs;

import secfox.soc.melon.asset.domain.AssetGroup;
import secfox.soc.melon.asset.domain.AssetType;
import secfox.soc.melon.asset.domain.Device;
import secfox.soc.melon.asset.domain.DeviceRoles;
import secfox.soc.melon.asset.domain.InfoSystem;
import secfox.soc.melon.asset.field.domain.AssetField;
import secfox.soc.melon.asset.field.domain.AssetFieldValue;
import secfox.soc.melon.asset.field.service.AssetFieldService;
import secfox.soc.melon.asset.query.AssetGroupPageQuery;
import secfox.soc.melon.asset.query.DevicePageQuery;
import secfox.soc.melon.asset.query.InfoSystemPageQuery;
import secfox.soc.melon.asset.service.AssetTypeService;
import secfox.soc.melon.asset.service.DeviceRolesService;
import secfox.soc.melon.asset.service.DeviceService;
import secfox.soc.melon.asset.service.InfoSystemService;
import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.organ.domain.Organization;
import secfox.soc.melon.organ.serivce.OrganizationService;
import secfox.soc.melon.security.domain.Account;
import secfox.soc.melon.security.util.SecurityContextUtils;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.ActionStatus;
import secfox.soc.melon.web.HintLevel;
import secfox.soc.melon.web.WebConstants;

/**
 * 信息系统控制类
 * @since 2014-9-26
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version  1.0
 */
@Controller
@RequestMapping("/asset/infoSystem")
public class InfoSystemController {
	private AssetTypeService  assetTypeService;
	private InfoSystemService infoSystemService;
	private AssetFieldService assetFieldService;
	private DeviceService deviceService;
	private DeviceRolesService deviceRolesService;
	private OrganizationService organizationService;
	
	@Inject
	public InfoSystemController(DeviceRolesService deviceRolesService,DeviceService deviceService,AssetTypeService  assetTypeService,AssetFieldService assetFieldService,InfoSystemService infoSystemService,OrganizationService organizationService) {
		super();
		this.assetTypeService = assetTypeService;
		this.infoSystemService = infoSystemService;
		this.assetFieldService = assetFieldService;
		this.deviceService = deviceService;
		this.deviceRolesService = deviceRolesService;
		this.organizationService=organizationService;
	}

	/**
     * 判断编码的唯一性
     * @param request
     * @return
     */
    @RequestMapping(value="/checkUrl", method=RequestMethod.POST)
    public @ResponseBody boolean checkUrl(@RequestParam("url") String url) {
        return infoSystemService.findByUrl(url) == null;
    }
	
	/**
     * 判断编码的唯一性
     * @param request
     * @return
     */
    @RequestMapping(value="/checkUnique", method=RequestMethod.POST)
    public @ResponseBody boolean checkUnique(@RequestParam("code") String code) {
        return infoSystemService.findByCode(code) == null;
    }
    
    
    /**
	 * 信息系统对比
	 * @param infoSystem
	 * @return
	 */
	@RequestMapping(value = "/contrast/{sysId}", method = RequestMethod.GET)
	public String contrast(@ModelAttribute("pageQuery") InfoSystemPageQuery pageQuery,@PathVariable Long sysId,Model model) {
		pageQuery.getSearchForm().setId(sysId);		
		model.addAttribute("sysId", sysId);
		model.addAttribute("sysName", infoSystemService.find(sysId).getName());
		return "asset.infoSystem.list.byContrast";
	}
	
	/**
	 * 信息系统对比分页查询
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value="/contrast/{sysId}", method = RequestMethod.POST)
	@ResponseBody
	public  Pagination<InfoSystem> contrast(@ModelAttribute InfoSystemPageQuery pageQuery,@PathVariable Long sysId) {
		return infoSystemService.findPages(pageQuery);
	}
	
	
    
	
	/**
	 * 跳转到添加页面
	 * @param infoSystem
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(@ModelAttribute InfoSystem infoSystem, Model model) {
		String newDate = DateFormat.getDateInstance().format(new Date());
		model.addAttribute("maxDate",newDate);
		List<Device> deviceAll = deviceService.findAll();
		model.addAttribute("deviceAll", deviceAll);
		DevicePageQuery pageQuery = new DevicePageQuery();
		model.addAttribute("pageQuery", pageQuery);
		
		return "asset.infoSystem.edit";
	}
	
	/**
	 * 添加信息
	 * @param infoSystem
	 * @param result
	 * @param attribute
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("infoSystem") InfoSystem infoSystem,BindingResult result,HttpServletRequest request, RedirectAttributes attribute) {
		//添加操作提示                        
		ActionHint actionHint = ActionHint.create("asset.infoSystem.create.hint", infoSystem.getName());
		//如果为空
		if (StringUtils.isBlank(infoSystem.getCode())) {
			result.rejectValue("code", "asset.abstract.code.hint");
		} else {
			// 如果有该对象
			if (infoSystemService.findByCode(infoSystem.getCode()) != null || infoSystemService.findByUrl(infoSystem.getUrl()) != null) {
				result.rejectValue("code", "asset.abstract.code.hint");
			}
		}
		//如果url为空
		if (StringUtils.isBlank(infoSystem.getUrl())) {
			result.rejectValue("code", "asset.abstract.code.hint");
		} else {
			// 如果有该对象
			if (infoSystemService.findByUrl(infoSystem.getUrl()) != null) {
				result.rejectValue("code", "asset.abstract.code.hint");
			}
		}
		
		// 获取安全域的ids
		String ids = request.getParameter("domainIds");
		if (StringUtils.isNotBlank(ids)) {
			String[] domainIdsStr = StringUtils.split(ids,BaseConstants.SPLITER_FLAG); // 分割出id
			Long[] domainIds = new Long[domainIdsStr.length]; // 类型转换容器
			for (int i = 0; i < domainIdsStr.length; i++) {
				// 类型转换
				domainIds[i] = Long.parseLong(domainIdsStr[i]);
			}
			infoSystem.setDomainIds(domainIds);
		}
		
		
		
		//获取设备的ids
		String deviceids = request.getParameter("deviceIds");
		if (StringUtils.isNotBlank(deviceids)) {
			//方便与扩展, 要传来需要对比的所有id用","号隔开
			Iterable<String> devicesIter = Splitter.on(BaseConstants.SPLITER_FLAG)
														 .trimResults()
														 .omitEmptyStrings()
														 .split(deviceids);
			//再把String[]转成Long数组
			Long[] deviceIds = Iterables.toArray(Longs.stringConverter().convertAll(devicesIter), Long.class);		
			infoSystem.setDeviceIds(deviceIds);
		}
		
		if(result.hasErrors()) {
			actionHint.setLevel(HintLevel.ERROR);
		}
		//获取动态属性
		@SuppressWarnings("unchecked")
		Map<String, Object> results = request.getParameterMap();
		
		infoSystemService.persist(infoSystem);
		//保存动态属性
		List<AssetFieldValue> fields = AssetFieldValue.extractAssetField(infoSystem.getId(), results);
		assetFieldService.saveOrUpdate(fields);
		//添加操作协助提示，依赖于客户端代码，此句可不执行
		actionHint.setActionType(0);//设置为新增
		actionHint.setDomain(infoSystem);
		attribute.addFlashAttribute(WebConstants.ACTION_HINT, actionHint);
        //导向到明细页面
        return "redirect:/asset/infoSystem/list";
	}
	
	/**
	 * 导出
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/export",method=RequestMethod.GET, produces={"application/vnd.ms-excel"})
	public String export(Model model,HttpServletRequest req) {
		String orgIdString = req.getParameter("organId");
		//如果有参数
		if(StringUtils.isNotBlank(orgIdString)){
			Long organId = Long.parseLong(orgIdString);
			List<InfoSystem> findByOrgan = infoSystemService.findByOrgan(organId); //获取数据集
			String [] name={findByOrgan.get(0).getOrganName()}; //给国际化参数赋值
			String fileName = MessageSourceUtils.getMessage("asset.infoSystem.organ.export",name );
			model.addAttribute("title",fileName); //设置标题
			model.addAttribute("results", findByOrgan); //获取导出数据
		}else{
			//设置标题
			model.addAttribute("title",MessageSourceUtils.getMessage("asset.infoSystem.export"));
			//取到要导出的数据集
			model.addAttribute("results", infoSystemService.findAll());
		}
		return "infoSystemXls";
	}
	
	
	/**
	 * 跳到list.jsp页面
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String list(@ModelAttribute("pageQuery") InfoSystemPageQuery pageQuery) {
		return "asset.infoSystem.list";
	}

	/**
	 * 分页查询
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value="/list", method = RequestMethod.POST)
	@ResponseBody
	public  Pagination<InfoSystem> query(@ModelAttribute InfoSystemPageQuery pageQuery) {
		InfoSystem searchForm = pageQuery.getSearchForm();
		searchForm.setUsing(-1);
		//yb 设置安全等级为0，查询全部
		searchForm.setSafeLeven(0);
		return infoSystemService.findPages(pageQuery); 
	}
	
	
	/**
	 * 单位视图
	 * @param organId 单位id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listByOrgan/{organId}", method=RequestMethod.GET)
	public String listAccountByOrgan(@PathVariable("organId") Long organId, Model model) {
		List<InfoSystem> infoSystems = infoSystemService.findByOrgan(organId);
		model.addAttribute("organId", infoSystems.get(0).getOrganId());
		model.addAttribute("infoSystems", infoSystems);
		model.addAttribute("infoSystemNum", infoSystems.size());
		model.addAttribute("organName",infoSystems.get(0).getOrganName());
		return "asset.infoSystem.list.byOrgan";
	}
	
	/**
	 * 单位信息系统视图(信息系统主页)
	 * @return
	 */
	@RequestMapping(value="/listOrgans", method=RequestMethod.GET)
	public String listOrgan(Model model) {
		List<Object[]> infos = infoSystemService.listOrganWithCount();
		model.addAttribute("infos", infos);
		return "asset.infoSystem.view.home";
	}
	
	
	/**
	 * 内容详细页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping("/show/{id}")
	public String show(@PathVariable Long id, Model model) {
		InfoSystem infoSystem = (InfoSystem)model.asMap().get("infoSystem");
        if (infoSystem == null) {
        	infoSystem = infoSystemService.find(id);
        }
		
        AssetType assetType = assetTypeService.find(infoSystem.getTypeId());
        List<AssetField> assetFields = assetFieldService.findFieldsWithValue(id);
        	//通过设备id查找设备关联的设备
      		List<DeviceRoles> findByDeviceId = deviceRolesService.findByInfoId(infoSystem.getId());
      		//如果有关联的设备
      		if(findByDeviceId!=null&& findByDeviceId.size()>0){
      			String[] deviceNames = new String[findByDeviceId.size()];  //被关联的设备名称 
      			Long[] deviceIds = new Long[findByDeviceId.size()];//被关联的设备id
      			for (int i = 0; i < findByDeviceId.size(); i++) {
      				//根据被关联设备的id找详细信息
      				Device find = deviceService.find(findByDeviceId.get(i).getDeviceId());
      				deviceNames[i] = find.getName();
      				deviceIds[i] = find.getId();
      			}
      			model.addAttribute("deviceNames", deviceNames);
      			model.addAttribute("deviceIds", deviceIds);
      		}
      		
        model.addAttribute("assetFields",assetFields);
        model.addAttribute("imgName", assetType.getImgName());
		model.addAttribute("infoSystem", infoSystem);
		return "asset.infoSystem.show";
	}
	
	/**
	 * 对比页面
	 * @param id 比项
	 * @param sysId 被比项
	 * @param model 
	 * @return
	 */
	@RequestMapping(value="/startContrast/{sysId}", method=RequestMethod.GET)
	public String reset(@RequestParam("id") Long id,@PathVariable Long sysId, Model model) {
		Long [] ids = {id,sysId};
		List<InfoSystem> findByIds = infoSystemService.findByIds(ids);
		model.addAttribute("findByIds", findByIds);
		
		
		List<AssetField> idFields = assetFieldService.findFieldsWithValue(id);
        model.addAttribute("idFields",idFields);
		
        List<AssetField> sysIdFields = assetFieldService.findFieldsWithValue(sysId);
        model.addAttribute("sysIdFields",sysIdFields);
        
		return "asset.infoSystem.list.contrast";
	}
	
	/**
	 * 跳转到修改页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		InfoSystem infoSystem = infoSystemService.find(id);
		String domainIds =Joiner.on(",").join(infoSystem.getDomainIds());
		String domainNames =Joiner.on(",").join(infoSystem.getDomainNames()) ;
		model.addAttribute("infoSystem", infoSystem);
		model.addAttribute("domainNames", domainNames);
		model.addAttribute("domainIds", domainIds);
		return "asset.infoSystem.edit";
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
	public String update(@PathVariable("id") Long id,@Valid@ModelAttribute InfoSystem infoSystem,HttpServletRequest request, BindingResult result, RedirectAttributes attribute) {
		//添加操作提示                        
		ActionHint actionHint = ActionHint.create("asset.infoSystem.update.hint", infoSystem.getName());
		if(result.hasErrors()) {
            return "asset.infoSystem.edit";
        }
		// 获取安全域的ids
		String ids = request.getParameter("domainIds");
		if (StringUtils.isNotBlank(ids)) {
			String[] domainIdsStr = StringUtils.split(ids,
					BaseConstants.SPLITER_FLAG); // 分割出id
			Long[] domainIds = new Long[domainIdsStr.length]; // 类型转换容器
			for (int i = 0; i < domainIdsStr.length; i++) {
				// 类型转换
				domainIds[i] = Long.parseLong(domainIdsStr[i]);
			}
			infoSystem.setDomainIds(domainIds);
		}
		infoSystemService.merge(infoSystem);
		//添加操作协助提示，依赖于客户端代码，此句可不执行
		actionHint.setActionType(1);//设置为修改
		actionHint.setDomain(infoSystem);
		attribute.addFlashAttribute(WebConstants.ACTION_HINT, actionHint);
        //导向到明细页面
        return "redirect:/asset/infoSystem/list";
	}

	@RequestMapping(value="/getInfo",method=RequestMethod.GET)
	@ResponseBody
	private List<InfoSystem> getByOrgan(){
		return infoSystemService.findAll();
	}
	
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/remove/{id}")
	public @ResponseBody ActionStatus remove(@PathVariable Long id) {
		infoSystemService.remove(id);
		return ActionStatus.SUCCESS;
	}
	
	/**
	 * 通过节点获取树
	 * @param id 节点id
	 * @return
	 */
	@RequestMapping("/find")
	public @ResponseBody List<InfoSystem> findTree(@RequestParam String id) {
		Long rootId = BaseConstants.ROOT_ID;
		try {
			rootId = Long.parseLong(id);
		} catch(NumberFormatException exception) {
			rootId = BaseConstants.ROOT_ID;
		}
		return infoSystemService.findTree(rootId);
	}
			
			
	@RequestMapping(value="/listByOrgan")
	public String listByOrgan(Model model){
		Long parentId=null;
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
		return "asset.infoSystem.view.byOrgan";
	}
	
	/**
	 * czj设备信息行政区域试图内容
	 * @param model
	 * @param organPath
	 * @return
	 */
	@RequestMapping(value = "/byOrganGrid/{organPath}", method = RequestMethod.GET)
	public String byOrganGrid(Model model,@PathVariable("organPath") String organPath){
		model.addAttribute("pageQuery", new DevicePageQuery());
    	model.addAttribute("organPath", organPath);
		return "melon/asset/infoSystem/grid/organGrid";
	}
	
	/**
	 * czj设备信息行政区域试图内容
	 * @param req
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value = "/listByOrgan", method = RequestMethod.POST)
    @ResponseBody
	public Pagination<InfoSystem> byOrgangrid(HttpServletRequest req,@ModelAttribute InfoSystemPageQuery pageQuery){
		String organPath = req.getParameter("orPath");
		InfoSystem searchForm = pageQuery.getSearchForm();
		//判断路径是否为空
		if(StringUtils.isNotBlank(organPath)&&!"null".equals(organPath)){
			searchForm.setOrganPath(organPath);
		}
		//yb 设置安全等级为0，查询全部
		searchForm.setSafeLeven(0);
    	searchForm.setUsing(-1);
    	searchForm.setOrganId(SecurityContextUtils.getCurrentAccount().getCompanyId());
    	pageQuery.setSearchForm(searchForm);
		return infoSystemService.findPages(pageQuery);
	}
	
	/**
	 * yb 跳转到信息系统安全域视图
	 */
	@RequestMapping(value="/listByGroup")
	public String listByGroup(Model model){
//		Long parentId=null;
//		//1.获取登录人所在单位
//		Account currentAccount = SecurityContextUtils.getCurrentAccount();
//		if(currentAccount !=null){
//			Long companyId = currentAccount.getCompanyId();
//			//2.根据单位id查出organ
//			Organization find = organizationService.find(companyId);
//			if(find !=null){
//				parentId = find.getParentId();
//			}
//		}
//		//3.根据organ.parid给RootID赋值
//		model.addAttribute("rootId", parentId);
		return "asset.infoSystem.view.byGroup";
	}
	
	/**
     * yb 信息系统安全域视图中内容
     * @param model
     * @return
     */
    @RequestMapping(value = "/byGroupGrid/{groupPath}", method = RequestMethod.GET)
	public String byGroupGrid(Model model,@PathVariable("groupPath") String groupPath) {
    	model.addAttribute("pageQuery", new InfoSystemPageQuery());
    	model.addAttribute("groupPath", groupPath);
		return "melon/asset/infoSystem/grid/groupGrid";
	}
    
    /**
     * yb 信息系统安全域视图中内容
     * @param model
     * @return
     */
    @RequestMapping(value = "/listByGroup", method = RequestMethod.POST)
    @ResponseBody
	public Pagination<Map<String,Object>> byGroupGrid(HttpServletRequest req,@ModelAttribute InfoSystemPageQuery pageQuery) {
    	String groupPath = req.getParameter("groupPath"); //获取安全域路径
    	InfoSystem searchForm = pageQuery.getSearchForm();
    	if(StringUtils.isNotBlank(groupPath)){
    		pageQuery.setGroupPath(groupPath);
    	}
    	searchForm.setSafeLeven(0);
    	searchForm.setUsing(-1);
    	searchForm.setOrganId(SecurityContextUtils.getCurrentAccount().getCompanyId());
    	pageQuery.setSearchForm(searchForm);
    	Pagination<Map<String,Object>> a=infoSystemService.findPageByGroup(pageQuery);
		return a;
	}
    
    
    
    /**
	 * yb 跳转到信息系统类型视图
	 */
	@RequestMapping(value="/listByType")
	public String listByType(@RequestParam(required=false) String rootId,Model model){
//		Long parentId=null;
//		//1.获取登录人所在单位
//		Account currentAccount = SecurityContextUtils.getCurrentAccount();
//		if(currentAccount !=null){
//			Long companyId = currentAccount.getCompanyId();
//			//2.根据单位id查出organ
//			Organization find = organizationService.find(companyId);
//			if(find !=null){
//				parentId = find.getParentId();
//			}
//		}
//		//3.根据organ.parid给RootID赋值
//		model.addAttribute("rootId", parentId);
		model.addAttribute("rootId", rootId);
		return "asset.infoSystem.view.byType";
	}
	
	/**
     * yb 信息系统类型视图中内容
     * @param model
     * @return
     */
    @RequestMapping(value = "/byTypeGrid/{typePath}", method = RequestMethod.GET)
	public String byTypeGrid(Model model,@PathVariable("typePath") String typePath) {
    	model.addAttribute("pageQuery", new InfoSystemPageQuery());
    	model.addAttribute("typePath", typePath);
		return "melon/asset/infoSystem/grid/typeGrid";
	}
    
    /** yb 信息系统类型视图中内容
     * @param model
     * @return
     */
    @RequestMapping(value = "/listByType", method = RequestMethod.POST)
    @ResponseBody
	public Pagination<InfoSystem> byTypeGrid(HttpServletRequest req,@ModelAttribute InfoSystemPageQuery pageQuery) {
    	String typePath = req.getParameter("typePath");
    	InfoSystem searchForm = pageQuery.getSearchForm();
    	if(StringUtils.isNotBlank(typePath)){
    		searchForm.setTypePath(typePath);
    	}
    	searchForm.setSafeLeven(0);
    	searchForm.setUsing(-1);
    	searchForm.setOrganId(SecurityContextUtils.getCurrentAccount().getCompanyId());
    	pageQuery.setSearchForm(searchForm);
		return infoSystemService.findPages(pageQuery);
	}

    	
    /**
	 * yb 生成级别树
	 * @param id 节点id
	 * @return
	 */
	@RequestMapping(value="/findLevelTree")
	@ResponseBody
	public List<Map<String,Object>> findLevelTree() throws Exception {
		String json=MessageSourceUtils.getMessage("infoLevelJson");
		List<Map<String,Object>> list= new ObjectMapper().readValue(json, List.class);
		return list;
	}
	
	/**
	 * yb 跳转到信息系统级别视图
	 */
	@RequestMapping(value="/listByLevel")
	public String listByLevel(){
		return "asset.infoSystem.view.byLevel";
	}
	
	/**
     * yb 信息系统级别视图中内容
     * @param model
     * @return
     */
    @RequestMapping(value = "/byLevelGrid/{level}", method = RequestMethod.GET)
	public String byLevelGrid(Model model,@PathVariable("level") String level) {
    	model.addAttribute("pageQuery", new InfoSystemPageQuery());
    	model.addAttribute("level", level);
		return "melon/asset/infoSystem/grid/levelGrid";
	}
    
    /** yb 信息系统级别视图中内容
     * @param model
     * @return
     */
    @RequestMapping(value = "/listByLevel", method = RequestMethod.POST)
    @ResponseBody
	public Pagination<InfoSystem> byLevelGrid(HttpServletRequest req,@ModelAttribute InfoSystemPageQuery pageQuery) {
    	String level = req.getParameter("level");
    	InfoSystem searchForm = pageQuery.getSearchForm();
    	if(StringUtils.isNotBlank(level)&&!"null".equals(level)&&!"undefined".equals(level)){
    		searchForm.setSafeLeven(Integer.parseInt(level));
    	}else{
    		searchForm.setSafeLeven(0);	//safeLeven默认值为1，将safeLeven设为0，这样才能查询全部
    	}
    	searchForm.setUsing(-1);
    	searchForm.setOrganId(SecurityContextUtils.getCurrentAccount().getCompanyId());
    	pageQuery.setSearchForm(searchForm);
		return infoSystemService.findPages(pageQuery);
	}
}
