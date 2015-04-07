/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.controller;


import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import secfox.soc.melon.asset.domain.AssetGroup;
import secfox.soc.melon.asset.domain.AssetType;
import secfox.soc.melon.asset.domain.Device;
import secfox.soc.melon.asset.domain.DeviceRoles;
import secfox.soc.melon.asset.domain.InfoSystem;
import secfox.soc.melon.asset.domain.IpAddress;
import secfox.soc.melon.asset.field.domain.AssetField;
import secfox.soc.melon.asset.field.domain.AssetFieldValue;
import secfox.soc.melon.asset.field.service.AssetFieldService;
import secfox.soc.melon.asset.query.DevicePageQuery;
import secfox.soc.melon.asset.query.InfoSystemPageQuery;
import secfox.soc.melon.asset.service.AssetGroupService;
import secfox.soc.melon.asset.service.AssetTypeService;
import secfox.soc.melon.asset.service.DeviceRolesService;
import secfox.soc.melon.asset.service.DeviceService;
import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.security.domain.Account;
import secfox.soc.melon.organ.domain.Organization;
import secfox.soc.melon.organ.serivce.OrganizationService;
import secfox.soc.melon.security.util.SecurityContextUtils;
import secfox.soc.melon.web.ActionHint;
import secfox.soc.melon.web.ActionStatus;
import secfox.soc.melon.web.HintLevel;
import secfox.soc.melon.web.WebConstants;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.primitives.Longs;

/**
 * 设备控制类
 * @since 2014-9-26
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version  1.0
 */
@Controller
@RequestMapping("/asset/device")
public class DeviceController {
	
	private DeviceService deviceService;
	
	private AssetTypeService assetTypeService;
	
	private AssetFieldService assetFieldService;
	
	private AssetGroupService assetGroupService;
	
	private ObjectMapper objectMapper;
	
	private DeviceRolesService deviceRolesService;
	
	private OrganizationService organizationService;

	@Inject
	public DeviceController(DeviceRolesService deviceRolesService,AssetGroupService assetGroupService,ObjectMapper objectMapper,AssetTypeService assetTypeService,DeviceService deviceService, AssetFieldService assetFieldService,OrganizationService organizationService) {
		super();
		this.deviceRolesService= deviceRolesService;
		this.assetGroupService =assetGroupService;
		this.objectMapper = objectMapper;
		this.deviceService = deviceService;
		this.assetTypeService = assetTypeService;
		this.assetFieldService = assetFieldService;
		this.organizationService=organizationService;
	}
	
	/**
     * 判断编码的唯一性
     * @param request
     * @return
     */
    @RequestMapping(value="/checkUnique", method=RequestMethod.POST)
    public @ResponseBody boolean checkUnique(@RequestParam("code") String code) {
        Device device = deviceService.findByCode(code);   
        return device == null;
    }
    
    /**
	 * 跳转到添加页面
	 * @param device
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public String create(@ModelAttribute Device device,Model model) {
		Date createDate = new Date();
		device.setCreateTime(createDate);//创建时间赋值
		List<Device> deviceAll = deviceService.findAll();
		String newDate = DateFormat.getDateInstance().format(createDate); //格式化时间为yyyy-MM-dd
		model.addAttribute("newDate", newDate); //为了限制选择日期范围
		model.addAttribute("deviceAll", deviceAll);
		return "asset.device.edit";
	}
	
	 /**
	 * 选择设备对比页面
	 * @param infoSystem
	 * @return
	 */
	@RequestMapping(value = "/contrast/{deviceId}", method = RequestMethod.GET)
	public String contrast(@ModelAttribute("pageQuery") DevicePageQuery pageQuery,@PathVariable Long deviceId,Model model) {
		pageQuery.getSearchForm().setId(deviceId);
		model.addAttribute("deviceId", deviceId);
		model.addAttribute("deviceName",deviceService.find(deviceId).getName());
		return "asset.device.list.byContrast";
	}
	
	/**
	 * 设备对比分页查询
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value="/contrast/{deviceId}", method = RequestMethod.POST)
	@ResponseBody
	public  Pagination<Device> contrast(@ModelAttribute DevicePageQuery pageQuery, @PathVariable Long deviceId) {
		return deviceService.findPages(pageQuery);
	}
	
	
	
	/**
	 * 去重页面
	 * @return
	 */
	@RequestMapping(value="/repeat")
	public String  exclusionRepeat(Model model){
		List<List<Device>> exclusionRepeat = deviceService.exclusionRepeat();
		model.addAttribute("repeat", exclusionRepeat);
		return "asset.device.list.repeat"; 
	}
	
	/**
	 * 对比页面
	 * @param id 比项
	 * @param sysId 被比项
	 * @param model 
	 * @return
	 */
	@RequestMapping(value="/startContrast/{deviceIds}", method=RequestMethod.GET)
	public String reset(@PathVariable String deviceIds, Model model) {
		
		
			//方便与扩展, 要传来需要对比的所有id用","号隔开
			Iterable<String> devicesIter = Splitter.on(BaseConstants.SPLITER_FLAG)
												 .trimResults()
												 .omitEmptyStrings()
												 .split(deviceIds);
			//再把String[]转成Long数组
			Long[] ids = Iterables.toArray(Longs.stringConverter().convertAll(devicesIter), Long.class);
			List<Device> devices = Lists.newArrayList();
			Device device = new Device();
			//查找到各个设备的动态属性
			for (int i = 0; i < ids.length; i++) {
				device = deviceService.find(ids[i]);
				List<AssetField> deviceField = assetFieldService.findFieldsWithValue(ids[i]);
				if(deviceField != null && deviceField.size()>0){
					device.setField(deviceField);
				}
				devices.add(device);
			}
		
		model.addAttribute("devices", devices); //要对比的设备
		
		return "asset.device.list.contrast";
	}
	
	
	/**
	 * 添加信息
	 * @param device 设备对象
	 * @param result
	 * @param attribute
	 * @return
	 */
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String create(@Valid @ModelAttribute("device") Device device, BindingResult result, HttpServletRequest request, RedirectAttributes attribute) {
		ActionHint actionHint = ActionHint.create("asset.device.create.hint", device.getName());
		/*//判断code为不为空
		if (StringUtils.isBlank(device.getCode())) {
			result.rejectValue("code","asset.abstract.code.hint");
		} else {
			//如果有该对象
			if (deviceService.findByCode(device.getCode()) != null) {
				result.rejectValue("code","asset.abstract.code.hint");
			}
		}*/
		//如果服务器验证错误
		if(result.hasErrors()){
			actionHint.setLevel(HintLevel.ERROR);
			return "asset.device.edit";
		}
		//获取所有的IP地址
		//首先判断是否具有IP地址
		if(device.isHasIp()) {
			//获取所有的IP地址
			device.setIps(extractIpAddress(request));
		}
		
		//获取安全域的ids
		String ids = request.getParameter("domainIds");
		if(StringUtils.isNotBlank(ids)){
			String[] domainIdsStr = StringUtils.split(ids, BaseConstants.SPLITER_FLAG); //分割出id
			Long [] domainIds = new Long[domainIdsStr.length]; //类型转换容器 
			for (int i = 0; i < domainIdsStr.length; i++) {
				//类型转换
				domainIds[i] = Long.parseLong(domainIdsStr[i]);
			}
			device.setDomainIds(domainIds);
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
			device.setDeviceIds(deviceIds);
		}
		
		//获取动态属性
		@SuppressWarnings("unchecked")
		Map<String, Object> results = request.getParameterMap();
		//保存
		deviceService.persist(device);
		
		//保存动态属性
		List<AssetFieldValue> fields = AssetFieldValue.extractAssetField(device.getId(), results);
		assetFieldService.saveOrUpdate(fields);
		//添加操作协助提示，依赖于客户端代码，此句可不执行
		actionHint.setActionType(0);//设置为修改
		actionHint.setDomain(device);
		attribute.addFlashAttribute(WebConstants.ACTION_HINT, actionHint);
        return "redirect:/asset/device/list"; //导向到list
	}
	
	/**
	 * 导出
	 * @param model
	 * @param req
	 * @param orgIdString 单位id
	 * @param netType 网络设备 
	 * @param virual 虚拟设备
	 * @param person 负责人
	 * @param domainId 安全域
	 * @return
	 */
	@RequestMapping(value="/export",method=RequestMethod.GET, produces={"application/vnd.ms-excel"})
	public String export(Model model,HttpServletRequest req) {
		String orgIdString = req.getParameter("organId");  //单位列表
		String netType = req.getParameter("netType"); //网络设备
		String virtual = req.getParameter("virtual");   //虚拟设备
		String person = req.getParameter("person");   //负责人
		String domain = req.getParameter("domainId"); //安全域
		String titleName = "";
		List<Device> results = Lists.newArrayList();
		//单位导出
		if(StringUtils.isNotBlank(orgIdString)) {
			results = deviceService.findByOrgan(Long.parseLong(orgIdString)); //查找数据集
			results =  getAssetGroup(results);
			if(results != null){
				String [] name = {results.get(0).getOrganName()};	//给国际化文件赋参数
				titleName = MessageSourceUtils.getMessage("asset.device.organ.export", name);//读取国际化
			}
		}else if(StringUtils.isNotBlank(netType)) {  //网络设备导出
			results = deviceService.findByHasIp(true); //查找数据集
			results =  getAssetGroup(results);
			if(results != null){
				titleName = MessageSourceUtils.getMessage("asset.device.netType.export");//设置标题
			}
		}else if(StringUtils.isNotBlank(virtual)) {//虚拟设备导出
			results = deviceService.findByVirtual(true); //查找数据集
			results =  getAssetGroup(results);
			if(results != null){
				titleName = MessageSourceUtils.getMessage("asset.device.virtual.export");//设置标题
			}
		}else if(StringUtils.isNotBlank(person)){
			Long personId = Long.valueOf(person);
			results = deviceService.findByCharger(personId); //查找数据集
			results =  getAssetGroup(results);
			if(results != null){
				String [] name = {results.get(0).getChargeName()};
				titleName = MessageSourceUtils.getMessage("asset.device.person.export",name);//设置标题
			}
		}else if(StringUtils.isNotBlank(domain)){
			Long domainId = Long.valueOf(domain);//安全域
			results = deviceService.findByAssetGroup(domainId);
			results =  getAssetGroup(results);
			titleName = MessageSourceUtils.getMessage("asset.device.organ.export");
		}else {
			results = deviceService.findAll();
			results =  getAssetGroup(results);//导出的数据集
			//给results中的Device赋值安全域名称
			titleName =  MessageSourceUtils.getMessage("asset.device.export"); //标题及文件名称
		}
		model.addAttribute("title",titleName); //设置标题
		model.addAttribute("results", results); //获取导出数据
		return "assetXls";
	}
	
	@RequestMapping(value="/getByOrgan",method=RequestMethod.GET)
	@ResponseBody
	private List<Device> getByOrgan(){
		Long orgId = SecurityContextUtils.getCurrentAccount().getCompanyId();
		return deviceService.findByOrgan(orgId);
	}
	
	/**
	 * 支持于导出功能,用于获取每个设备的所属安全域
	 * @param devices 数据源
	 * @return
	 */
	private List<Device> getAssetGroup(List<Device> devices){
		if(devices.size() <1){
			return null;
		}
		//所属安全域
		List<AssetGroup> groups = Lists.newArrayList();
		for (int i = 0; i < devices.size(); i++) {
			//获取获取该设备
			Device device = devices.get(i);
			//获取该设备的安全域
			groups = assetGroupService.findByAsset(device.getId(), 0);
			//如果有安全域,则添加安全域
			if(groups.size()>0){
				String[] domainName = new String[groups.size()];
				for (int j = 0; j < groups.size(); j++) {
					domainName[j] = groups.get(j).getName();
				}
				device.setDomainNames(domainName);
			}
		}
		return devices;
	}
	
	/**
     * 判断编码的唯一性
     * @param request
     * @return
     */
    @RequestMapping(value="/findByOrgan", method=RequestMethod.GET)
    public @ResponseBody List<Device> findByOrgan() {
    	Long companyId = SecurityContextUtils.getCurrentAccount().getCompanyId();//获取单位id
        return  deviceService.findByOrgan(companyId);
    }
    
    
    /**
     * 弹框页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/deviceFrame", method = RequestMethod.GET)
	public String deviceGrid(Model model) {
    	model.addAttribute("pageQuery", new DevicePageQuery());//设备分页
		return "asset.device.deviceFrame";
	}
    /**
     * 跳入弹框内容页面
     * @param pageQuery
     * @return
     */
    @RequestMapping(value = "/deviceGrid", method = RequestMethod.GET)
    public String deviceGr(@ModelAttribute("pageQuery") DevicePageQuery pageQuery) {
    	return "asset.device.deviceGrid";
    }
    /**
     * 获取弹框中的列表内容
     * @param pageQuery
     * @return
     */
	@RequestMapping(value = "/deviceGrid", method = RequestMethod.POST)
	@ResponseBody
	public  Pagination<Device> deviceGri(@ModelAttribute DevicePageQuery pageQuery) {
		pageQuery.getSearchForm().setUsing(-1);
		return deviceService.findPages(pageQuery);
	}
	
	
    
    
    /**
     * 跳到导入页面
     */
    @RequestMapping("/importList")
    public String importList(@ModelAttribute Device device,Model model){
    	model.addAttribute(device);
    	return "asset.device.importList";
    }
	
    /**
     * 导入
     * @param request 
     * @param response
     * @param file 上传的文件
     */
    @RequestMapping("/import")
	public void importExl(HttpServletRequest request, HttpServletResponse response, @RequestParam("files[]") MultipartFile file) {
    	String accept = request.getHeader("accept");
		//针对IE HACK
		if(StringUtils.contains(accept, "application/json") || StringUtils.contains(accept, "text/javascript")) {
			response.setContentType("application/json");
		} else {
			response.setContentType("text/plain");
		}
		//
		Map<String, Object> result = Maps.newHashMap();
		if(file != null) {
			InputStream inputStream = null; 
			try {
				inputStream = file.getInputStream();
				Map<String, Object> impRes = deviceService.excelToDb(inputStream);
				result.put("impRes", impRes);
			} catch (Exception e) {
				result.put("result", true);
			}finally{
				if(inputStream != null){
					try {
						inputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}
		try {
			PrintWriter writer = response.getWriter();
			writer.write(objectMapper.writeValueAsString(result));
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
    
	/**
	 * 设备主页视图
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listOrgans", method=RequestMethod.GET)
	public String listOrgan(Model model) {
		model.addAttribute("devices", deviceService.listOrganWithCount());
		return "asset.device.view.home";
	}

	/**
	 * 所有设备列表分页查询
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	@ResponseBody
	public  Pagination<Device> query(@ModelAttribute DevicePageQuery pageQuery,@RequestParam(required=false) String osFlag) {
		pageQuery.setOsFlag(Integer.parseInt(osFlag));
		return deviceService.findPages(pageQuery);
	}

	/**
	 * 所有设备列表
	 * @param pageQuery
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(@ModelAttribute("pageQuery") DevicePageQuery pageQuery,@RequestParam(required=false) String osFlag,Model model) {
		model.addAttribute("osFlag", osFlag);
		return "asset.device.list";
	}
	
	/**
	 * 设备单位列表
	 * @param organId
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listByOrgan/{organId}", method=RequestMethod.GET)
	public String listAccountByOrgan(@PathVariable("organId") Long organId, Model model) {
		model.addAttribute("devices", deviceService.findByOrgan(organId));
		return "asset.device.list.byOrgan";
	}
	
	/**
	 * 网络设备列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listByHasIp", method=RequestMethod.GET)
	public String listByHasIp(Model model) {
		model.addAttribute("devices", deviceService.findByHasIp(true));
		return "asset.device.list.byHasIp";
	}
	
	/**
	 * 负责人设备列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listByPerson/{personId}", method=RequestMethod.GET)
	public String listByPerson(@PathVariable("personId") Long personId,Model model) {
		model.addAttribute("devices", deviceService.findByCharger(personId));
		return "melon/asset/device/list/byPerson";
	}
	
	/**
	 * 虚拟设备列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listByVirtual", method=RequestMethod.GET)
	public String listByVirtual(Model model) {
		model.addAttribute("devices", deviceService.findByVirtual(true));
		return "asset.device.list.byVirtual";
	}
	
	
	/**
	 * 跳转到修改页面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.GET)
	public String update(@PathVariable("id") Long id, Model model) {
		Device device = deviceService.find(id);
		String domainIds = Joiner.on(",").join(device.getDomainIds());
		String domainNames = Joiner.on(",").join(device.getDomainNames());
		model.addAttribute("device", device);
		model.addAttribute("domainIds", domainIds);
		model.addAttribute("domainNames", domainNames);
		return "asset.device.edit";
	}
	
	
	/**
	 * 更新操作
	 * @param id 设备id
	 * @param device 修改后的设备对象
	 * @param result 
	 * @param attribute
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/update/{id}", method=RequestMethod.POST)
	public String update(@PathVariable("id") Long id, @Valid@ModelAttribute Device device, BindingResult result, RedirectAttributes attribute,HttpServletRequest request) {
		ActionHint actionHint = ActionHint.create("asset.device.update.hint", device.getName());

		if(result.hasErrors()) {
            return "asset.device.edit";
        }
		//首先判断是否具有IP地址
		if(device.isHasIp()) {
			//获取所有的IP地址
			device.setIps(extractIpAddress(request));
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
			device.setDomainIds(domainIds);
		}
		deviceService.merge(device);
		//添加操作协助提示，依赖于客户端代码，此句可不执行
		actionHint.setActionType(1);//设置为修改
		actionHint.setDomain(device);
		attribute.addFlashAttribute(WebConstants.ACTION_HINT, actionHint);
        return "redirect:/asset/device/list"; //导向到明细页面
	}
	
	/**
	 * 此方法支持与添加和修改
	 * @return
	 */
	private List<IpAddress> extractIpAddress(HttpServletRequest request) {
		String[] ips = request.getParameterValues("ipv4");
		String[] macs = request.getParameterValues("mac");
		List<IpAddress> address = new ArrayList<IpAddress>(ips.length);
		if(ips != null) {
			for(int i = 0; i < ips.length; i++) {
				String ip = ips[i];
				String mac = macs[i];
				if(StringUtils.isNotBlank(ip)) {
					IpAddress add = new IpAddress();
					add.setIpv4(ip);
					add.setMac(mac);
					//
					address.add(add);
				}
			}
		}
		return address;
	}
	
	
	/**
	 * 内容详细页面
	 * @param id 设备id
	 * @param model
	 * @return
	 */
	@RequestMapping("/show/{id}")
	public String show(@PathVariable Long id, Model model) {
		Device device = (Device)model.asMap().get("device");
		if (device == null) {
        	device = deviceService.find(id);
        }
		List<AssetField> assetFields = assetFieldService.findFieldsWithValue(id);
		model.addAttribute("assetFields",assetFields);
		AssetType assetType = assetTypeService.find(device.getTypeId());
		model.addAttribute("imgName", assetType.getImgName());
		
		//通过设备id查找设备关联的设备
		List<DeviceRoles> findByDeviceId = deviceRolesService.findByInfoId(device.getId());
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
		model.addAttribute(device);
		return "asset.device.show";
	}
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	@RequestMapping("/remove/{id}")
	public @ResponseBody ActionStatus remove(@PathVariable Long id) {
		deviceService.remove(id);
		return ActionStatus.SUCCESS;
	}
	
	

	
	/**
	 * 以下为视图专区
	 */
	/**
	 * 物理环境视图
	 */
	@RequestMapping(value="/listByEnvi")
	public String listByEnvi(){
		return "asset.device.view.byEnvi";
	}
	
	/**
     * 物理环境视图中内容
     * @param model
     * @return
     */
    @RequestMapping(value = "/byEnviGrid/{enviPath}", method = RequestMethod.GET)
	public String byEnviGrid(Model model,@PathVariable("enviPath") String enviPath) {
    	model.addAttribute("pageQuery", new DevicePageQuery());
    	model.addAttribute("enviPath", enviPath);
		return "melon/asset/device/grid/enviGrid";
	}
    
    /**
     * 信息系统视图中内容
     * @param model
     * @return
     */
    @RequestMapping(value = "/byDeviceInfoGrid/{enviPath}", method = RequestMethod.GET)
	public String byDeviceInfoGrid(Model model,@PathVariable("enviPath") String enviPath) {
    	model.addAttribute("pageQuery", new DevicePageQuery());
    	model.addAttribute("enviPath", enviPath);
		return "melon/asset/device/grid/deviceInfoGrid";
	}
    
    
    /**
     * 物理环境视图中内容
     * @param model
     * @return
     */
    @RequestMapping(value = "/listByEnvi", method = RequestMethod.POST)
    @ResponseBody
	public Pagination<Device> listByEnvi(HttpServletRequest req,@ModelAttribute DevicePageQuery pageQuery) {
    	String enviPath = req.getParameter("enPath");
    	Device searchForm = pageQuery.getSearchForm();
    	if(StringUtils.isNotBlank(enviPath)){
    		searchForm.setEnviPath(enviPath);
    	}
    	searchForm.setUsing(-1);
    	searchForm.setOrganId(SecurityContextUtils.getCurrentAccount().getCompanyId());
    	pageQuery.setSearchForm(searchForm);
		return deviceService.findPages(pageQuery);
	}
	
    /**
     * 物理环境视图中内容
     * @param model
     * @return
     */
    @RequestMapping(value = "/listByDeviceInfo", method = RequestMethod.POST)
    @ResponseBody
	public Pagination<Device> byDeviceInfoGrid(HttpServletRequest req,@ModelAttribute DevicePageQuery pageQuery) {
    	String enviPath = req.getParameter("enPath");
    	
    	
    	
    	Device searchForm = pageQuery.getSearchForm();
    	if(StringUtils.isNotBlank(enviPath)){
    		searchForm.setEnviPath(enviPath);
    	}
    	searchForm.setUsing(-1);
    	searchForm.setOrganId(SecurityContextUtils.getCurrentAccount().getCompanyId());
    	pageQuery.setSearchForm(searchForm);
		return deviceService.findPageByDeviceInfo(pageQuery, enviPath);
	}
    
    /**
	 * 安全域视图
	 */
	@RequestMapping(value="/listByDomain")
	public String listByDomain(@RequestParam(required=false) String rootId,Model model){
		model.addAttribute("rootId", rootId);
		return "asset.device.view.byDomain";
	}
	
	/**
	 * 安全域视图
	 */
	@RequestMapping(value="/listByDeviceInfo")
	public String listByDeviceInfo(){
		return "asset.device.view.byDeviceInfo";
	}
	
	/**
     * 访问安全域设备列表页面
     * @param model
     * @return
     */
    @RequestMapping(value = "/byDomainGrid/{domainPath}", method = RequestMethod.GET)
	public String byDomainGrid(Model model,@PathVariable("domainPath") String domainPath) {
    	model.addAttribute("pageQuery", new DevicePageQuery());
    	model.addAttribute("domainPath", domainPath);
		return "melon/asset/device/grid/domainGrid";
	}
    
//    /**
//     * 安全域设备列表内容
//     * @param model
//     * @return
//     */
//    @RequestMapping(value = "/listByDomain", method = RequestMethod.POST)
//    @ResponseBody
//	public Pagination<Device> byDomainGrid(HttpServletRequest req,@ModelAttribute DevicePageQuery pageQuery) {
//    	String domianPath = req.getParameter("domianPath"); //获取安全域路径
//    	Device searchForm = pageQuery.getSearchForm();
//    	if(StringUtils.isNotBlank(domianPath)){
//    		pageQuery.setDomainPath(domianPath);
//    	}
//    	searchForm.setUsing(-1);
//    	searchForm.setOrganId(SecurityContextUtils.getCurrentAccount().getCompanyId());
//    	pageQuery.setSearchForm(searchForm);
//		return deviceService.findPageByDomain(pageQuery);
//	}
    
    /**
     * 安全域设备列表内容
     * @param req
     * @param pageQuery
     * @return
     */
	@RequestMapping(value = "/listByDomain", method = RequestMethod.POST)
	@ResponseBody
	public Pagination<Map<String,Object>> byDomainGrid(HttpServletRequest req,@ModelAttribute DevicePageQuery pageQuery) {
		String domainPath = req.getParameter("domainPath"); //获取安全域路径
		if(StringUtils.isNotBlank(domainPath)){
			pageQuery.setDomainPath(domainPath);
		}
		return deviceService.findPageByDomain(pageQuery);
	}
    
	
    /**
	 * yb 跳转到设备行政区域视图
	 */
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
		return "asset.device.view.byOrgan";
	}
	
	/**
     * yb 设备行政区域视图中内容
     * @param model
     * @return
     */
    @RequestMapping(value = "/byOrganGrid/{organPath}", method = RequestMethod.GET)
	public String byOrganGrid(Model model,@PathVariable("organPath") String organPath) {
    	model.addAttribute("pageQuery", new DevicePageQuery());
    	model.addAttribute("organPath", organPath);
		return "melon/asset/device/grid/organGrid";
	}
    
    /**
     * yb 安全域行政区域视图中内容
     * @param model
     * @return
     */
    @RequestMapping(value = "/listByOrgan", method = RequestMethod.POST)
    @ResponseBody
	public Pagination<Device> byPolgrid(HttpServletRequest req,@ModelAttribute DevicePageQuery pageQuery) {
    	String organPath = req.getParameter("organPath");
    	Device searchForm = pageQuery.getSearchForm();
    	if(StringUtils.isNotBlank(organPath)){
    		searchForm.setOrganPath(organPath);
    	}
    	searchForm.setUsing(-1);
    	searchForm.setOrganId(SecurityContextUtils.getCurrentAccount().getCompanyId());
    	pageQuery.setSearchForm(searchForm);
    	Pagination<Device> a= deviceService.findPages(pageQuery);
    	return a;
	}
    
    /**
	 * yb 跳转到设备类型视图
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
		return "asset.device.view.byType";
	}
	
	/**
     * yb 设备类型视图中内容
     * @param model
     * @return
     */
    @RequestMapping(value = "/byTypeGrid/{typePath}", method = RequestMethod.GET)
	public String byTypeGrid(Model model,@PathVariable("typePath") String typePath) {
    	model.addAttribute("pageQuery", new DevicePageQuery());
    	model.addAttribute("typePath", typePath);
		return "melon/asset/device/grid/typeGrid";
	}
    
    /** yb 设备类型视图中内容
     * @param model
     * @return
     */
    @RequestMapping(value = "/listByType", method = RequestMethod.POST)
    @ResponseBody
	public Pagination<Device> byTypeGrid(HttpServletRequest req,@ModelAttribute DevicePageQuery pageQuery) {
    	String typePath = req.getParameter("typePath");
    	Device searchForm = pageQuery.getSearchForm();
    	if(StringUtils.isNotBlank(typePath)){
    		searchForm.setTypePath(typePath);
    	}
    	searchForm.setUsing(-1);
    	searchForm.setOrganId(SecurityContextUtils.getCurrentAccount().getCompanyId());
    	pageQuery.setSearchForm(searchForm);
		return deviceService.findPages(pageQuery);
	}
    
    @RequestMapping(value="/byDomainTopo")
	public String byDomainTopo(){
		return "melon.asset.device.topo.domainTopo";
	}
}
