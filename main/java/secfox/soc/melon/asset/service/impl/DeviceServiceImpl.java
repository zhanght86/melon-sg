/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.service.impl;

import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.melon.asset.dao.AssetGroupDao;
import secfox.soc.melon.asset.dao.AssetTypeDao;
import secfox.soc.melon.asset.dao.DeviceDao;
import secfox.soc.melon.asset.dao.IpAddressDao;
import secfox.soc.melon.asset.domain.AssetGroup;
import secfox.soc.melon.asset.domain.AssetType;
import secfox.soc.melon.asset.domain.Device;
import secfox.soc.melon.asset.domain.DeviceFilter;
import secfox.soc.melon.asset.domain.DeviceRoles;
import secfox.soc.melon.asset.domain.GroupRelation;
import secfox.soc.melon.asset.domain.IpAddress;
import secfox.soc.melon.asset.query.DevicePageQuery;
import secfox.soc.melon.asset.service.AssetGroupService;
import secfox.soc.melon.asset.service.DeviceRolesService;
import secfox.soc.melon.asset.service.DeviceService;
import secfox.soc.melon.asset.service.GroupRelationService;
import secfox.soc.melon.asset.service.InfoSystemService;
import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.SortOrder;
import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.organ.dao.OrganDao;
import secfox.soc.melon.organ.domain.Organization;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.PaginationBuilder;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.persistence.templates.QueryTemplateManager;
import secfox.soc.melon.security.domain.Account;
import secfox.soc.melon.security.service.AccountService;
import secfox.soc.melon.security.util.SecurityContextUtils;

import com.google.common.collect.Lists;

/**
 * 设备实现接口
 * 
 * @since 2014-9-26
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
@Service
public class DeviceServiceImpl extends GenericServiceImpl<Device, Long>
		implements DeviceService {

	private DeviceDao dao;
	private IpAddressDao ipAddressDao;
	private GroupRelationService groupRelationService;
	private OrganDao organDao;
	private AssetGroupDao assetGroupDao;
	private AccountService accountService;
	private AssetTypeDao assetTypeDao;
	private DeviceRolesService deviceRolesService;
	
	private InfoSystemService infoSystemService;
	private AssetGroupService assetGroupService;
	
	@Inject
	public DeviceServiceImpl(DeviceRolesService deviceRolesService,AssetTypeDao assetTypeDao,AccountService accountService,AssetGroupDao assetGroupDao,OrganDao organDao,
			GroupRelationService groupRelationService,IpAddressDao ipAddressDao, DeviceDao dao,
			InfoSystemService infoSystemService,AssetGroupService assetGroupService) {
		super();
		this.dao = dao;
		this.deviceRolesService = deviceRolesService;
		this.assetTypeDao = assetTypeDao;
		this.organDao = organDao;
		this.assetGroupDao = assetGroupDao;
		this.accountService = accountService;
		this.groupRelationService = groupRelationService;
		this.ipAddressDao = ipAddressDao;
		this.infoSystemService=infoSystemService;
		this.assetGroupService=assetGroupService;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * secfox.soc.melon.framework.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<Device, Long> getDao() {
		return this.dao;
	}

	
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * secfox.soc.melon.asset.service.DeviceService#findPages(secfox.soc.melon
	 * .asset.query.DevicePageQuery)
	 */
	@Override
	public Pagination<Device> findPages(final DevicePageQuery query) {
		return findDomainPage(QueryType.JQL, query,
				new PaginationBuilder<Device, DevicePageQuery>() {

					@Override
					public void buildSelect(QueryTemplate qt) {
						qt.append("select a from Device a ");
					}

					@Override
					public void buildWhere(Device s, QueryTemplate qt) {

						//id
						if(s.getId() !=null){
							qt.append(" and a.id not in (:id) ");
							qt.addParameter("id", s.getId());
						}
						
						// 编号
						if (StringUtils.isNotBlank(s.getCode())) {
							qt.append(" and a.code like :code ");
							qt.addParameter("code",QueryTemplate.buildAllLike(StringUtils.strip(s.getCode())));
						}

						// 名称
						if (StringUtils.isNotBlank(s.getName())) {
							qt.append(" and a.name like :name ");
							qt.addParameter("name",QueryTemplate.buildAllLike(StringUtils.strip(s.getName())));
						}

						// 使用状态 -1为全部
						if (s.getUsing() > -1) {
							qt.append(" and a.using = :using ");
							qt.addParameter("using", s.getUsing());
						}

						// 型号
						if (StringUtils.isNotBlank(s.getModel())) {
							qt.append(" and a.model like :model ");
							qt.addParameter("model",QueryTemplate.buildAllLike(s.getModel()));
						}

						// 所属单位
						if (StringUtils.isNotBlank(s.getOrganName())) {
							qt.append(" and a.organName like :organName ");
							qt.addParameter("organName", QueryTemplate.buildAllLike(s.getOrganName()));
						}

						// 厂商名称
						if (StringUtils.isNotBlank(s.getProducer())) {
							qt.append(" and a.producer like :producer ");
							qt.addParameter("producer",QueryTemplate.buildAllLike(s.getProducer()));
						}
						
						//单位id
						if(s.getOrganId() !=null){
							qt.append(" and a.organId =:organId ");
							qt.addParameter("organId", s.getOrganId());
						}
						
						//物理环境
						if(StringUtils.isNotBlank(s.getEnviPath()) && !"null".equals(s.getEnviPath())){
							qt.append(" and ( a.enviPath like :enviPath ");
							qt.addParameter("enviPath",QueryTemplate.buildAllLike(","+s.getEnviPath()));
							qt.append(" or a.enviPath like :enviRightPath )");
							qt.addParameter("enviRightPath",QueryTemplate.buildRightLike(s.getEnviPath().toString()));
						}
						
						//yb 组织机构
						if(StringUtils.isNotBlank(s.getOrganPath()) && !"null".equals(s.getOrganPath())){
							qt.append(" and ( a.organPath like :organPath ");
							qt.addParameter("organPath",QueryTemplate.buildAllLike(","+s.getOrganPath()));
							qt.append(" or a.organPath like :organRightPath )");
							qt.addParameter("organRightPath",QueryTemplate.buildRightLike(s.getOrganPath()));
						}
						//是否有操作系统
						if(query.getOsFlag()!=-1){
							qt.append(" and a.hasOs = :osFlag ");
							qt.addParameter("osFlag",query.getOsFlag()==1);
						}
						
						//负责人
						
						//类型
						if(StringUtils.isNotBlank(s.getTypePath()) && !"null".equals(s.getTypePath())){
							qt.append(" and ( a.typePath like :typePath ");
							qt.addParameter("typePath",QueryTemplate.buildAllLike(","+s.getTypePath()));
							qt.append(" or a.typePath like :typeRightPath )");
							qt.addParameter("typeRightPath",QueryTemplate.buildRightLike(s.getTypePath()));
						}
						
						//保修开始时间
						
						//保修结束时间
					}

					@Override
					public void buildCount(QueryTemplate qt) {
						qt.append("select count(a.id) from Device a ");
					}

					@Override
					public void buildBys(String column, SortOrder order,
							QueryTemplate qt) {
						column = "id";
						qt.append(QueryTemplate.buildOrderBy("a", column, order));
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * secfox.soc.melon.asset.service.DeviceService#findByCode(java.lang.String)
	 */
	@Override
	public Device findByCode(String code) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"device.findByCode");
		qt.addParameter("code", code);
		return findFirstDomain(qt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * secfox.soc.melon.asset.service.DeviceService#findByOrgen(java.lang.Long)
	 */
	@Override
	public List<Device> findByOrgan(Long orgId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"device.findByOrgan");
		qt.addParameter("organId", orgId);
		return findDomains(qt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * secfox.soc.melon.asset.service.DeviceService#findByAssetGroup(java.lang
	 * .Long)
	 */
	@Override
	public List<Device> findByAssetGroup(Long assetGroupId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"device.findByRelation");
		qt.addParameter("groupId", assetGroupId);
		return findDomains(qt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see secfox.soc.melon.asset.service.DeviceService#findAll()
	 */
	@Override
	public List<Device> findAll() {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"device.findAll");
		return findDomains(qt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see secfox.soc.melon.asset.service.DeviceService#listOrganWithCount()
	 */
	@Override
	public List<Object[]> listOrganWithCount() {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"device.groupByOrgan");// 分组统计单位人数
		return find(qt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see secfox.soc.melon.asset.service.DeviceService#findByHasIp(boolean)
	 */
	@Override
	public List<Device> findByHasIp(boolean hasip) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"device.findByHasIp");
		qt.addParameter("hasIp", hasip);
		return findDomains(qt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see secfox.soc.melon.asset.service.DeviceService#findByVirtual(boolean)
	 */
	@Override
	public List<Device> findByVirtual(boolean vir) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"device.findByVirtual");
		qt.addParameter("virtual", vir);
		return findDomains(qt);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#find(java.io.
	 * Serializable)
	 */
	@Override
	@Transactional(readOnly = true)
	public Device find(Long pk) {
		Device device = dao.find(pk);
		// 找到ip
		List<IpAddress> address = ipAddressDao.findByDeviceId(pk);
		device.setIps(address);
		// 获取安全域name和id
		List<Object[]> assetGroup = groupRelationService.findByTypeAssetId(0,pk);
		if (assetGroup.size() > 0) {
			Long[] domainIds = new Long[assetGroup.size()]; // 所属安全域ids
			String[] domainNames = new String[assetGroup.size()];// 所属安全域names
			for (int i = 0; i < assetGroup.size(); i++) {
				domainNames[i] = assetGroup.get(i)[0].toString();
				domainIds[i] = Long.valueOf(assetGroup.get(i)[1].toString());
			}
			device.setDomainIds(domainIds);
			device.setDomainNames(domainNames);
		}
		return device;
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#merge(secfox.soc.melon.persistence.Identifiable)
	 */
	@Override
	@Transactional(readOnly = false)
	public Device merge(Device entity) {
		UserInfo currentUserInfo = SecurityContextUtils.getCurrentUserInfo();
		entity.setModifierId(currentUserInfo.getUserId());
		entity.setModifierName(currentUserInfo.getUsername());
		entity.setCreateTime(new Date());
		//如果包括修改ip
		if(entity.getIps() !=null && entity.getIps().size() >0){
			// 先删除后保存
			ipAddressDao.removeByDeviceId(entity.getId());
			ipAddressDao.persist(entity.getIps(), entity.getId());
		}
		//如果包括修改安全域
		if(entity.getDomainIds() !=null && entity.getDomainIds().length > -1){
			// 删除设备安全域关系数据在保存
			groupRelationService.removeByDeviceId(entity.getId());
			// 保存
			Long[] domainIds = entity.getDomainIds();
			for (int i = 0; i < domainIds.length; i++) {
				String path = "";
				AssetGroup assetGroup = assetGroupDao.find(domainIds[i]);
				if(assetGroup!=null){
					path = assetGroup.getPath();
				}
				// 保存安全域与设备的关系
				GroupRelation createDeviceConnection = GroupRelation.createDeviceConnection(entity.getId(), domainIds[i],path);
				groupRelationService.persist(createDeviceConnection);
			}
		}
		dao.merge(entity);
		return entity;
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#persist(secfox.soc.melon.persistence.Identifiable)
	 */
	@Override
	@Transactional(readOnly = false)
	public void persist(Device entity) {
		//获取登录人
		UserInfo currentUserInfo = SecurityContextUtils.getCurrentUserInfo();
		entity.setModifierId(currentUserInfo.getUserId());
		entity.setModifierName(currentUserInfo.getUsername());
		
		
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "device.findNumberSum"); 
		qt.addParameter("organId", entity.getOrganId());
		Device result = findFirstDomain(qt);
		
		Organization organization = organDao.find(entity.getOrganId());
		AssetType assetType = assetTypeDao.find(entity.getTypeId());
		String typeCode = assetType.getCode();
		String orgCode = organization.getCode();
		//组织编码+类型编码+4位顺序码，例如G110000IW0001
		if(result == null){
			entity.setCode(orgCode+typeCode+BaseConstants.DEFAULTORDER);
		}else{
			//获取顺序
			int numberSum = result.getNumberSum();
			//设置顺序
			int curNumber = numberSum+1;
			//获取编码前缀
			String code = orgCode+typeCode;
			
			//编码拼接
			if(numberSum >= 0 && numberSum < 10) {
				code += BaseConstants.ORDER_FIR + curNumber;
			} else if(numberSum >= 10 && numberSum < 100) {
				code += BaseConstants.ORDER_SEC + curNumber;
			} else if(numberSum >= 100 && numberSum < 1000) {
				code += BaseConstants.ORDER_TRD + curNumber;
			} else {
				code += curNumber;
			}
			entity.setNumberSum(curNumber);
			entity.setCode(code);
		}
		
		
		dao.persist(entity);
		//关联ip
		if (entity.getIps() != null) {
			ipAddressDao.persist(entity.getIps(), entity.getId()); // 保存ip
		}
		//安全域
		Long[] domainIds = entity.getDomainIds();
		//安全域
		if(domainIds !=null && domainIds.length>0){
			for (int i = 0; i < domainIds.length; i++) {
				String path = "";
				AssetGroup assetGroup = assetGroupDao.find(domainIds[i]);
				if(assetGroup!=null){
					path = assetGroup.getPath();
				}
				// 保存安全域与设备的关系
				GroupRelation createDeviceConnection = GroupRelation.createDeviceConnection(entity.getId(), domainIds[i],path);
				groupRelationService.persist(createDeviceConnection);
			}
		}
		
		
		//设备关系
		Long[] deviceIds = entity.getDeviceIds();
		if(deviceIds!=null && deviceIds.length>0){
			for (int i = 0; i < deviceIds.length; i++) {
				DeviceRoles roles = DeviceRoles.createDeviceConnection(entity.getId(),deviceIds[i]);
				deviceRolesService.persist(roles);
			}
		}
		
		
		
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#remove(java.io.Serializable)
	 */
	@Override
	@Transactional(readOnly = false)
	public void remove(Long pk) {
		dao.remove(pk);
		//删除ip
		ipAddressDao.removeByDeviceId(pk);
		//删除安全域
		List<GroupRelation> findByDeviceId = groupRelationService.findByDeviceId(pk);
		
		if(findByDeviceId != null && findByDeviceId.size()>0){
			groupRelationService.removeByDeviceId(pk);
		}
		//删除关联设备
		List<DeviceRoles> byDeviceId = deviceRolesService.findByDeviceId(pk);
		if(byDeviceId != null && byDeviceId.size()>0){
			deviceRolesService.removeByDeviceId(pk);
		}
		
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * secfox.soc.melon.asset.service.DeviceService#findByCharger(java.lang.
	 * Long)
	 */
	@Override
	public List<Device> findByCharger(Long personId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"device.findByCharger");
		qt.addParameter("chargerId", personId);
		return findDomains(qt);
	}


	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.DeviceService#findByEvi(java.lang.Long)
	 */
	@Override
	public List<Device> findByEvi(Long enviId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"device.findByEnviId");
		qt.addParameter("enviId",QueryTemplate.buildAllLike(String.valueOf(enviId)));
		return findDomains(qt);
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.DeviceService#findByIds(java.lang.Long[])
	 */
	@Override
	public List<Device> findByIds(Long[] ids) {
		List<Long> idd = Lists.newArrayList(ids);
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select a from Device a where ");
		qt.appendIn("id", idd);
		List<Device> tempDevice = findDomains(qt); //获取到信息系统
		List<Device> devices = Lists.newArrayList(); //获取到信息系统
		for (int i = 0; i < tempDevice.size(); i++) {
			Device device = this.find(tempDevice.get(i).getId());
			devices.add(device);
		}
		return devices;
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.DeviceService#excelToDb(java.io.InputStream)
	 */
	@Override
	@Transactional(readOnly = false)
	public Map<String, Object> excelToDb(InputStream inputStream) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		List<Device> excelDevice = Lists.newArrayList(); //获得读取的内容
		List<Device> correctDevice = Lists.newArrayList(); //存验证通过的数据
		List<Device> errorDevice = Lists.newArrayList(); //存验证不通过的数据
		List<Device> packDevice = Lists.newArrayList(); //验证通过后封装的对象
		List<Device> saveDevice = Lists.newArrayList(); //保存成功的数据
		
		//1.读取excl获得内容
		excelDevice = readExcelContent(inputStream);
		
		//2.验证
		for (int i = 0; i < excelDevice.size(); i++) {
			Device validateDevice = validateDevice(excelDevice.get(i));
			//如果是错误的数据
			if(validateDevice.isCorrect()){
				errorDevice.add(validateDevice);
			}else{
				correctDevice.add(validateDevice);
			}
		}
		
		//3-1.封装验证成功的数据再保存
		if(correctDevice != null && correctDevice.size() > 0){
			packDevice = packDbs(correctDevice); 
			for (int i = 0; i < packDevice.size(); i++) {
				Device device = packDevice.get(i);
				device.setCreateTime(new Date());
				this.persist(device);
				saveDevice.add(device);
			}
		}
		
		//3-2.对错误数据操作
		//思路1：在导出一个专门存错误数据的excel
		//思路2：展示到页面让用户手动改
		
		result.put("errorDevice", errorDevice); //需要修改的错误数据
		result.put("errorNumber", errorDevice.size());  //错误数据多少条
		result.put("correctDevice", saveDevice); //已经成功保存的数据
		result.put("correctNumber", saveDevice.size()); //正确且保存的数据多少条
		return result;
	}
	
	/*
	 * 1-1.获取excel中的内容并不做任何数据验证,封装成对象
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.DeviceService#readExcelContent(java.io.InputStream)
	 */
	@Override
	public List<Device> readExcelContent(InputStream inputStream) throws Exception {
		HSSFWorkbook book = new HSSFWorkbook(inputStream);
		HSSFSheet sheet = book.getSheetAt(0); // 获取第一个sheet
		HSSFRow row = sheet.getRow(1); // 获取第二行
		int rowNum = sheet.getLastRowNum(); // 得到总行数
		int cells = row.getPhysicalNumberOfCells(); // 得到总列数
		Device device = null;
		List<Device> devices = Lists.newArrayList();
		for (int i = 1; i < rowNum+1; i++) {
			row = sheet.getRow(i); // 从第二行开始获取
			device = validateDb(row , cells);
			if (device != null) {
				devices.add(device);
			}
		}
		inputStream.close();
		return devices;
	}
	
	/**
	 * 1-2验证excel并封装导入的数据
	 * @param row 表的行数
	 * @param cells 表的列数
	 * @return 封装后的对象
	 * @throws Exception
	 */
	private Device validateDb(HSSFRow row,int cells) throws Exception{
		Device device = new Device(); //每一行的数据封装成对象
		
		int i = 0;
		while (i < cells+1) {
			
			//从第0列开始获取格
			HSSFCell cell = row.getCell(i);
			
			Map<String, Object> cellValue = validateCells(cell);
			Date dateCellValue = null; //格子类型为时间的值
			String stringCellValue = null;  //格子类型为字符串的值
			String numberCellValue = null;  // 格子类型类数字类型
			
			if(cellValue.get("dateCellValue") != null ){
				dateCellValue = (Date)cellValue.get("dateCellValue");    
			}
			if(cellValue.get("stringCellValue") != null){
				stringCellValue = String.valueOf(cellValue.get("stringCellValue"));
			}
			if(cellValue.get("numberCellValue") != null){
				numberCellValue = String.valueOf(cellValue.get("numberCellValue"));
			}
			
			switch (i) {
			case 0: //名称
				device.setName(stringCellValue);
				break;
			case 1: //编码
				device.setCode(stringCellValue);
				break;
			case 2: //所属单位
				device.setOrganName(stringCellValue);
				break;
			case 3: //所属安全域
				device.setElsDomain(stringCellValue);
				break;
			case 4: //设备类型
				device.setTypeName(stringCellValue);
				break;
			case 5: //负责人
				device.setChargeName(stringCellValue);
				break;
			case 6: //使用状态类型转换 0:已停用,1:使用中,2:错误数据
				if("已安装".equals(stringCellValue)){
					device.setUsing(1);
					break;
				}else if("测试中".equals(stringCellValue)){
					device.setUsing(2);
					break;
				}else if("使用中".equals(stringCellValue)){
					device.setUsing(3);
					break;
				}else if("维护中".equals(stringCellValue)){
					device.setUsing(4);
					break;
				}else if("报废".equals(stringCellValue)){
					device.setUsing(5);
					break;
				}else if("备用".equals(stringCellValue)){
					device.setUsing(6);
					break;
				}else{
					device.setCorrect(false);
					break;
				}
			case 7: //判断虚拟设备 virtual   true：是 false:否
				if("是".equals(stringCellValue)){
					device.setVirtual(true);
					break;
				}else if("否".equals(stringCellValue)){
					device.setVirtual(false);
				}else{
					throw new Exception();  //TODO 如何处理内容不是 是和否
				}
				break;
			case 8: //宿主
				device.setElsMasterName(stringCellValue);
				break;
			case 9://网络设备 hasIp true：是 false:否
				if("是".equals(stringCellValue)){
					device.setHasIp(true);
				}else if("否".equals(stringCellValue)){
					device.setHasIp(false);
				}else{
					throw new Exception(); //TODO 如何处理内容不是 是和否
				}
				break;
			case 10:  // ip
				device.setElsIps(stringCellValue);
				break;
			case 11: //mac地址
				device.setElsMac(stringCellValue);
				break;
			case 12://设备型号
				device.setModel(stringCellValue);
				break;
			case 13://厂商名称
				device.setProducer(stringCellValue);
				break;
			case 14://厂商联系方式
				if(StringUtils.isNotBlank(numberCellValue)){
					device.setProducerTel(numberCellValue);
				}
				if(StringUtils.isNotBlank(stringCellValue)){
					device.setProducerTel(stringCellValue);
				}
				break;
			case 15: //出厂日期
				device.setProduceTime(dateCellValue);
				break;
			case 16: //操作系统 hasos true：是 false:否
				if("有".equals(stringCellValue)){
					device.setHasOs(true);
				}else if("无".equals(stringCellValue)){
					device.setHasOs(false);
				}else{
					throw new Exception();
				}
				break;
			case 17: //操作系统名称
				if(StringUtils.isNotBlank(stringCellValue)){
					if("WindowsXP".equals(stringCellValue)){
						device.setOsName(1);
					}else
					if("WindowsServer2003".equals(stringCellValue)){
						device.setOsName(2);
					}else
					if("WindowsServer2000".equals(stringCellValue)){
						device.setOsName(3);			
					}else
					if("WindowsVisita".equals(stringCellValue)){
						device.setOsName(4);
					}else
					if("Windows8".equals(stringCellValue)){
						device.setOsName(5);
					}else
					if("UNIX".equals(stringCellValue)){
						device.setOsName(6);
					}else
					if("Linux".equals(stringCellValue)){
						device.setOsName(7);
					}else{
						throw new Exception();
					}
				}
				break;
			case 18: //备注
				device.setRemarks(stringCellValue);
				break;
			}
			i++;
		}
		return device;
	}
	
	/**
	 * 1-2-1 根据格子类型获取不同类型的值
	 * @param cell
	 * @return
	 */
	private Map<String, Object> validateCells(HSSFCell cell){
		Map<String, Object> cellText = new HashMap<String, Object>();
		String stringCellValue = null;  //格子类型为字符串的值
		Date dateCellValue = null;    //格子类型为时间的值
		String numberCellValue = null;  // 格子类型类数字类型
		
		//如果格不为空
		if(cell != null){
			//获取该格的样式
			int getcellType = cell.getCellType();
			switch (getcellType) {
				case Cell.CELL_TYPE_BLANK: //空白的单元格类型
					cellText.put("stringCellValue", "");
					break;
					
				case Cell.CELL_TYPE_BOOLEAN: //boole的单元格类型
					//TODO 留用扩展使用
					break;
					
				case Cell.CELL_TYPE_NUMERIC: //数字的单元格类型
					//如果是date类型则 ，获取该cell的date值     
					if (HSSFDateUtil.isCellDateFormatted(cell)) {     
						dateCellValue = HSSFDateUtil.getJavaDate(cell.getNumericCellValue());
						cellText.put("dateCellValue",dateCellValue);
					} else { 
						// 纯数字     
						double number = cell.getNumericCellValue(); 
						//解决取值带一个E问题
						numberCellValue = new DecimalFormat("#").format(number); 
						cellText.put("numberCellValue",numberCellValue);
					}  
					break;
					
				case Cell.CELL_TYPE_STRING: //字符串的单元格类型
					stringCellValue = cell.getStringCellValue();// 获取该格的内容
					cellText.put("stringCellValue",stringCellValue);
					break;
					
				default:
					cellText.put("stringCellValue","");
					cellText.put("numberCellValue","");
					cellText.put("dateCellValue",null);
					break;
			}
		}else{
			cellText.put("stringCellValue","");
			cellText.put("numberCellValue","");
			cellText.put("dateCellValue",null);
		}
		return cellText;
	}

	/**
	 * 2.验证各个字段的合法性  (状态为2的均为错误数据)
	 * @param excelDevice
	 * @return
	 */
	private Device validateDevice(Device dexDe){
		Device master = null;
		//单位
		Organization organ = organDao.findByName(dexDe.getOrganName());
		//安全域
		List<AssetGroup> domains = assetGroupDao.findByName(StringUtils.split(dexDe.getElsDomain(),BaseConstants.SPLITER_FLAG));
		//设备类型
		AssetType assetType = assetTypeDao.findByName(dexDe.getTypeName());
		//负责人
		Account person = accountService.findByName(dexDe.getChargeName());
		//如果是虚拟机
		if(dexDe.isVirtual()){
			//宿主
			master = dao.findByName(dexDe.getElsMasterName());
		}
		//验证判断称错误数据
		if(organ == null || domains == null || assetType == null || person == null || domains.size()<1){
			//TODO 在这里可以处理错误数据例如添加些提示错误信息  例如：  organ.name +="（这个单位不存在或输入错误）"
			dexDe.setUsing(0);
		}else if(dexDe.isVirtual()){
			if(master == null){
				dexDe.setUsing(0);
			}
		}
		
		return dexDe;
	}
	
	/**
	 * 3.封装验证通过的device （该方法使用前一定要先通过validateDevice之后再用，否则报空指针）
	 * @param excelDevice
	 * @return
	 */
	private List<Device> packDbs(List<Device> excelDevice){
		
		List<Device> devices = Lists.newArrayList(); //要保存的对象
		//验证通过后开始封装(现在默认是验证都通过)
		
		for (int i = 0; i < excelDevice.size(); i++) {
			Device device = excelDevice.get(i);
			String[] domainNames = null; // 安全域name
			Long[] domainIds = null;  //安全域id

			//单位
			Organization organ = organDao.findByName(device.getOrganName());
			device.setOrganId(organ.getId());
			device.setOrganPath(organ.getPath());
			
			//安全域
			List<AssetGroup> assetGroups = assetGroupDao.findByName(StringUtils.split(device.getElsDomain(),BaseConstants.SPLITER_FLAG));
			domainNames = new String[assetGroups.size()];
			domainIds = new Long[assetGroups.size()];
			for (int j = 0; j < assetGroups.size(); j++) {
				String name = assetGroups.get(j).getName();
				Long id = assetGroups.get(j).getId();
				domainNames[j] = name;
				domainIds[j] = id;
			}
			device.setDomainIds(domainIds);
			device.setDomainNames(domainNames);
			
			//设备类型
			AssetType assetType = assetTypeDao.findByName(device.getTypeName());
			device.setTypeId(assetType.getId());
			device.setTypePath(assetType.getPath());
			
			
			//负责人
			Long chargerId = accountService.findByName(device.getChargeName()).getId();
			device.setChargerId(chargerId);
			
			
			//如果是虚拟机
			if(device.isVirtual()){
				//宿主
				Device virt = dao.findByName(device.getElsMasterName());
				//不为空
				if(virt != null){
					device.setMasterId(virt.getId());
				}
			}
			
			//如果是网络设备
			if(device.isHasIp()){
				String[] ips = StringUtils.split(device.getElsIps(),BaseConstants.SEMI_SPLITER);
				List<IpAddress> ipsAddress = Lists.newArrayList();
				for (int j = 0; j < ips.length; j++) {
					IpAddress address = new IpAddress();
					address.setIpv4(ips[j]);
					address.setMac(device.getElsMac());
					ipsAddress.add(address);
				}
				device.setIps(ipsAddress);
			}
			
			devices.add(device);
		}
		return devices;
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.DeviceService#findByUserBusiness(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<Device> findByUserBusiness(Long userId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "device.findByUserBusiness");
		qt.addParameter("userId", userId);
		return findDomains(qt);
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.DeviceService#findByQuickNote(java.lang.Long)
	 */
	@Override
	public List<Device> findByQuickNote(Long userId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "device.findByQuickNote");
		qt.addParameter("userId", userId);
		return findDomains(qt);
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.DeviceService#findByNameModel(java.lang.String, java.lang.String)
	 */
	@Override
	public List<List<Device>> exclusionRepeat() {
		QueryTemplate qt = QueryTemplateManager.find("device.exclusionRepeat");
		List<Object[]> nams = find(qt);
		
		List<List<Device>> homeView  = Lists.newArrayList();
		
		for (int i = 0; i < nams.size(); i++) {
			int number = Integer.parseInt(String.valueOf(nams.get(i)[2]));
			if(number >1){
				String na = String.valueOf(nams.get(i)[0]);
				String mo = String.valueOf(nams.get(i)[1]);
				List<Device> devices = findByNameModel(na,mo);
				homeView.add(devices);
			}
		}
		
		return  homeView;
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.DeviceService#findByNameModel(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Device> findByNameModel(String name, String mode) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "device.findByNameModel");
		qt.addParameter("name", name);
		qt.addParameter("model", mode);
		return findDomains(qt);
	}

	@Override
	public List<Device> findByIpOrName(String deviceName, String deviceIp) {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select a from Device a where 1=1");
		if(StringUtils.isNotBlank(deviceName)){
			qt.append(" and a.name=:name");
			qt.addParameter("name", deviceName);
		}
		return findDomains(qt);
	}

	@Override
	public Device findByIp(String Ip) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<Device> findRemindDevice(DeviceFilter deviceFilter) {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select device from Device device where 1=1 ");
		
		//单位
		if(deviceFilter.getCompanyId() != null){
			qt.append(" and device.organId=:organId");
			qt.addParameter("organId", deviceFilter.getCompanyId());
		}
		//开始时间
		if(deviceFilter.getStartTime() != null){
			qt.append(" and device.catTime >= :startTime");
			qt.addParameter("startTime", deviceFilter.getStartTime());
		}
		//结束时间
		if(deviceFilter.getEndTime() != null){
			qt.append(" and device.deadline <= :endTime");
			qt.addParameter("endTime", deviceFilter.getEndTime());
		}
		//qt.append(" and (1=1 ");
		

		//存放负责人Id
		List<Long> chargeIDS = null;
		
		//存放设备类型Id
		List<Long> typeIDS = null;
		
		//将负责人Id转换成Long类型
		if(deviceFilter.getChargeIds() != null && deviceFilter.getChargeIds().length() > 0){
			String[] chargeIds = deviceFilter.getChargeIds().split(",");
			Long[] chargeIdResult = new Long[chargeIds.length];
			for(int i=0; i< chargeIds.length; i++){
				chargeIdResult[i] = Long.parseLong(chargeIds[i]);
			}
			chargeIDS = Lists.newArrayList(chargeIdResult);
		}
		
		//将设备类型Id转换成Long类型
		if( deviceFilter.getTypeIds() != null &&  deviceFilter.getTypeIds().length() >0){
			String[] typeIds = deviceFilter.getTypeIds().split(",");
			Long[] typeIdResult = new Long[typeIds.length];
			for(int i=0; i< typeIds.length; i++){
				typeIdResult[i] = Long.parseLong(typeIds[i]);
			}
			typeIDS = Lists.newArrayList(typeIdResult);
		}
		
		//与或关系
		//关系与
		if(deviceFilter.getRelation() == 0) {
			//设备类型和负责人都不为空的情况
			if(StringUtils.isNotBlank(deviceFilter.getChargeIds()) && StringUtils.isBlank(deviceFilter.getTypeIds())) {
				qt.append(" and");
				qt.appendIn(" device.chargerId",chargeIDS);
			}
			//设备类型不为空和负责人为空的情况
			if(StringUtils.isNotBlank(deviceFilter.getTypeIds()) && StringUtils.isNotBlank(deviceFilter.getChargeIds())) {
				qt.append(" and");
				qt.appendIn(" device.chargerId",chargeIDS);
				qt.append(" and");
				qt.appendIn(" device.typeId",typeIDS);
			}
			//设备类型为空和负责人不为空的情况
			if(StringUtils.isNotBlank(deviceFilter.getTypeIds()) && StringUtils.isBlank(deviceFilter.getChargeIds())) {
				qt.append(" and");
				qt.appendIn(" device.typeId",typeIDS);
			}
			
		} else {//关系或
			//设备类型和负责人都不为空的情况
			if(StringUtils.isNotBlank(deviceFilter.getChargeIds()) && StringUtils.isBlank(deviceFilter.getTypeIds())) {
				qt.append(" and");
				qt.appendIn(" device.chargerId",chargeIDS);
			}
			//设备类型不为空和负责人为空的情况
			if(StringUtils.isNotBlank(deviceFilter.getTypeIds()) && StringUtils.isNotBlank(deviceFilter.getChargeIds())) {
				qt.append(" and");
				qt.appendIn(" device.chargerId",chargeIDS);
				qt.append(" or");
				qt.appendIn(" device.typeId",typeIDS);
			}
			//设备类型为空和负责人不为空的情况
			if(StringUtils.isNotBlank(deviceFilter.getTypeIds()) && StringUtils.isBlank(deviceFilter.getChargeIds())) {
				qt.append(" and");
				qt.appendIn(" device.typeId",typeIDS);
			}
		}
		return findDomains(qt);
	}


	@Override
	public Pagination<Device> findPageByDeviceInfo(DevicePageQuery query, String enviPath) {

		Pagination<Map<String,Object>> findPageByDomain = dao.findPageByDeviceInfo(query, enviPath);

		Pagination<Device> pageDevic = new Pagination<Device>();//分页构造器数据
		List<Device> listDevice = Lists.newArrayList(); //分页构造器中的设备数据
		
		//获取分页中的数据
		List<Map<String,Object>> results = findPageByDomain.getResults();
		//如果分页有值
		if(results.size() > 0 ){
			//重构分页构造器数据
			for (int i = 0; i < results.size(); i++) {
				Device device = new Device();
				device.setId(Long.parseLong(results.get(i).get("id").toString()));
				device.setCode(results.get(i).get("code").toString());
				device.setName(results.get(i).get("name").toString());
				device.setTypeName(results.get(i).get("type").toString());
				device.setOrganName(results.get(i).get("organName").toString());
				device.setUsing(Integer.parseInt(results.get(i).get("using").toString()));
				
				listDevice.add(device);
			}
			pageDevic.setCurrPage(findPageByDomain.getCurrPage());
			pageDevic.setPageSize(findPageByDomain.getPageSize());
			pageDevic.setResults(listDevice);
			pageDevic.setCount(findPageByDomain.getCount());
		}
		
		return pageDevic;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.DeviceService#findPageByDomain2(secfox.soc.melon.asset.query.DevicePageQuery)
	 */
	@Override
	public Pagination<Map<String, Object>> findPageByDomain(
			DevicePageQuery query) {
		return dao.findPageByDomain(query);
	}

	@Override
	public List<Object[]> findTypeViewDb() {
		List<Object[]> objList = Lists.newArrayList();
		
		//1.业务系统
		Object[] obj = new Object[3];
		obj[0]=MessageSourceUtils.getMessage("asset.assetType.service");
		obj[1]= infoSystemService.findByType(BaseConstants.TYPE_SERVICE).size();
		obj[2]=BaseConstants.TYPE_SERVICE;
		objList.add(obj);
		//2.安全域
		obj = new Object[3];
		obj[0]=MessageSourceUtils.getMessage("asset.assetType.group");
		obj[1]= assetGroupService.findByType(BaseConstants.TYPE_GROUP).size();
		obj[2]=BaseConstants.TYPE_GROUP;
		objList.add(obj);
		//3.机房
		obj = new Object[3];
		obj[0]=MessageSourceUtils.getMessage("asset.assetType.envi");
		obj[1]= dao.findByEnviPath(BaseConstants.TYPE_ENVI.toString()).size();
		obj[2]=BaseConstants.TYPE_ENVI;
		objList.add(obj);
		//4.网络设备
		obj = new Object[3];
		obj[0]=MessageSourceUtils.getMessage("asset.assetType.netDevice");
		obj[1]= findByType(BaseConstants.TYPE_NETDEVICE).size();
		obj[2]=BaseConstants.TYPE_NETDEVICE;
		objList.add(obj);
		//5.安全系统
		obj = new Object[3];
		obj[0]=MessageSourceUtils.getMessage("asset.assetType.safe");
		obj[1]= findByType(BaseConstants.TYPE_SAFE).size();
		obj[2]=BaseConstants.TYPE_SAFE;
		objList.add(obj);
		//6.主机
		obj = new Object[3];
		obj[0]=MessageSourceUtils.getMessage("asset.assetType.main");
		obj[1]= findByType(BaseConstants.TYPE_MAIN).size();
		obj[2]=BaseConstants.TYPE_MAIN;
		objList.add(obj);
		//7.操作系统
		obj = new Object[3];
		obj[0]=MessageSourceUtils.getMessage("asset.assetType.os");
		obj[1]= dao.findByOs().size();
		obj[2]=1L;
		objList.add(obj);
		//8.中间件
		obj = new Object[3];
		obj[0]=MessageSourceUtils.getMessage("asset.assetType.middle");
		obj[1]= findByType(BaseConstants.TYPE_MIDDLE).size();
		obj[2]=BaseConstants.TYPE_MIDDLE;
		objList.add(obj);
		//9.应用软件
		obj = new Object[3];
		obj[0]=MessageSourceUtils.getMessage("asset.assetType.app");
		obj[1]= findByType(BaseConstants.TYPE_APP).size();
		obj[2]=BaseConstants.TYPE_APP;
		objList.add(obj);
		//10.数据库服务器
		obj = new Object[3];
		obj[0]=MessageSourceUtils.getMessage("asset.assetType.db");
		obj[1]= findByType(BaseConstants.TYPE_DB).size();
		obj[2]=BaseConstants.TYPE_DB;
		objList.add(obj);
		//11.存储设备
		obj = new Object[3];
		obj[0]=MessageSourceUtils.getMessage("asset.assetType.save");
		obj[1]= findByType(BaseConstants.TYPE_SAVE).size();
		obj[2]=BaseConstants.TYPE_SAVE;
		objList.add(obj);
		//12.终端
		obj = new Object[3];
		obj[0]=MessageSourceUtils.getMessage("asset.assetType.terminal");
		obj[1]= findByType(BaseConstants.TYPE_TERMINAL).size();
		obj[2]=BaseConstants.TYPE_TERMINAL;
		objList.add(obj);
		
		return objList;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.DeviceService#findByType(java.lang.Long)
	 */
	@Override
	public List<Device> findByType(Long typeId) {
		AssetType type=assetTypeDao.find(typeId);
		if(type==null){
			return new ArrayList<Device>();
		}
		String typePath=type.getPath();
		List<Device> list=dao.findByTypePath(typePath);
		return list;
	}


}
