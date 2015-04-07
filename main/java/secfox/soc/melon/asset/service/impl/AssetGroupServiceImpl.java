/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import secfox.soc.melon.asset.dao.AssetGroupDao;
import secfox.soc.melon.asset.dao.AssetTypeDao;
import secfox.soc.melon.asset.domain.AssetGroup;
import secfox.soc.melon.asset.domain.AssetType;
import secfox.soc.melon.asset.domain.Device;
import secfox.soc.melon.asset.query.AssetGroupPageQuery;
import secfox.soc.melon.asset.service.AssetGroupService;
import secfox.soc.melon.asset.service.AssetTypeService;
import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.SortOrder;
import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.organ.dao.OrganDao;
import secfox.soc.melon.organ.domain.Organization;
import secfox.soc.melon.organ.serivce.OrganizationService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.PaginationBuilder;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.security.SecurityConstants;
import secfox.soc.melon.security.util.SecurityContextUtils;

/**
 * @since @2014-10-10,@下午4:51:01
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
@Service
public class AssetGroupServiceImpl extends GenericServiceImpl<AssetGroup, Long> implements AssetGroupService{

	private AssetGroupDao assetGroupDao;
	private AssetTypeService assetTypeService;
	private OrganizationService organizationService;
	
	
	/**
	 * 
	 * @param assetGroupDao
	 */
	@Inject
	public AssetGroupServiceImpl(OrganizationService organizationService,AssetTypeService assetTypeService,OrganDao organDao,AssetGroupDao assetGroupDao) {
		super();
		this.assetGroupDao = assetGroupDao;
		this.assetTypeService = assetTypeService;
		this.organizationService = organizationService;
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<AssetGroup, Long> getDao() {
		return assetGroupDao;
	}

	@Override
	@Transactional(readOnly = false)
	public void persist(AssetGroup entity) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "assetGroup.findNumberSum"); 
		qt.addParameter("organId", entity.getOrganId());
		AssetGroup result = findFirstDomain(qt);
		
		Organization organization = organizationService.find(entity.getOrganId());
		AssetType assetType = assetTypeService.find(entity.getTypeId());
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

		Long parentId = entity.getParentId(); //父节点
		AssetGroup paren = this.find(parentId);
		//如果是根节点
		if(parentId == BaseConstants.ROOT_ID){
			entity.setDepth(1);//深度为1
		}else{
			entity.setDepth(paren.getDepth()+1);
		}
		entity.setCreateTime(new Date());//创建时间
		entity.setOrganId(SecurityContextUtils.getCurrentAccount().getCompanyId()); //单位id
		entity.setOrganName(SecurityContextUtils.getCurrentAccount().getCompanyName());//单位name
		
		super.persist(entity);// 1.保存entity
		
		
		
		
		// 如果父节点不为根节点
		if (parentId != BaseConstants.ROOT_ID) {
			// 2.保存path路径
			AssetGroup reference = super.getReference(entity.getParentId());
			if (reference != null) {
				String parPath = reference.getPath();
				// 如果path不为空就说明不是在根节点下建的
				if (StringUtils.isNotBlank(parPath)) {
					entity.setPath(entity.getId() + BaseConstants.SPLITER_FLAG+ parPath);
				} else {
					entity.setPath(entity.getId().toString());
				}
			}
		} else {
			// 2.保存path路径
			entity.setPath(entity.getId() + "," + parentId.toString());
		}
		
		
	}
	
	
	
	/**
	 * 创建根节点
	 * @return
	 */
	public AssetGroup createRoot() {
		AssetGroup assetGroup = new AssetGroup();
		assetGroup.setName(MessageSourceUtils.getMessage("asset.assetGroup.root"));
		assetGroup.setParentId(null);
		assetGroup.setId(BaseConstants.ROOT_ID);
		assetGroup.setOrder(0);
		assetGroup.getState().setOpened(true);
		return assetGroup;
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.AssetGroupService#findTree(java.lang.Long, boolean)
	 */
	@Override
	public List<AssetGroup> findTree(Long rootId, boolean choosen) {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select a from AssetGroup a where ");
		
		AssetGroup assetGroup = new AssetGroup();
		String path = String.valueOf(BaseConstants.ROOT_ID);
		
		//如果是默认节点
		if(rootId==BaseConstants.ROOT_ID){
			assetGroup = createRoot();
		} else {
			assetGroup = find(rootId); //根据id获取
			path = assetGroup.getPath();
		}
		
		//本单位及下级单位的安全域
		/*Account currentAccount = SecurityContextUtils.getCurrentAccount();
		Long companyId = currentAccount.getCompanyId();
		Organization organization = organDao.find(companyId);
		if(organization!=null){
			String orgPath = organization.getPath();
			if(StringUtils.isNotBlank(orgPath)){
				qt.append(" and ( a.organPath like :organPath ");
				qt.addParameter("organPath",QueryTemplate.buildAllLike(","+orgPath));
				qt.append(" or a.organPath like :organRightPath )");
				qt.addParameter("organRightPath",QueryTemplate.buildRightLike(orgPath));
			}
		}*/
		

		
		//路径匹配
		qt.append(" a.path like :path");
		qt.addParameter("path", QueryTemplate.buildLeftLike(path));
		//是否包含通用路径
		if(choosen) {
			qt.append(" and a.path not like :notPath ");
			qt.addParameter("notPath", QueryTemplate.buildLeftLike(SecurityConstants.PATH_COMMON));
		}
		
		
		
		//排序
		qt.append(" order by a.order desc");
		
		
		List<AssetGroup> types = findDomains(qt);//获取孙子节点
		//如果包含根节点
		if(types.contains(assetGroup)){ 
			AssetGroup groupRoot = types.get(types.indexOf(assetGroup)); //获取这个对象的位置
			groupRoot.setAsRoot(true); //设置为根节点
			groupRoot.getState().setOpened(true); //默认打开节点
		}else{
			//不包含,就加进去
			types.add(assetGroup);
		}
		return types;
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.AssetGroupService#findByOrgan(java.lang.Long)
	 */
	@Override
	public List<AssetGroup> findByOrgan(Long organId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"assetGroup.findByOrgan");
		qt.addParameter("organId", organId);
		return findDomains(qt);
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.AssetGroupService#findAll()
	 */
	@Override
	public List<AssetGroup> findAll() {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"assetGroup.findAll");
		return findDomains(qt);
	}

	/**
	 * 做安全域链接视图数据（暂时不做）
	 */
	@Override
	public List<Object[]> findRelationList() {
		String sql ="select assetGroup.PK, assetGroup.T_NAME, assetGroup.NET_TYPE,assetGroup.DEPTH,case when parent.T_NAME is null then '安全域' else parent.T_NAME end " +
					"from t_asset_group assetGroup  left  join t_asset_group parent " +
					"on assetGroup.TYPE_PARENTID = parent.pk ";
		QueryTemplate qt = QueryTemplate.create(QueryType.SQL,sql);
		return find(qt);
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.AssetGroupService#getGroupDevice()
	 */
	@Override
	public List<Object[]> getGroupDevice() {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"group.sumByDevice");
		return find(qt);
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.AssetGroupService#getGroupSys()
	 */
	@Override
	public List<Object[]> getGroupSys() {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"group.sumBySys");
		return find(qt);
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.AssetGroupService#getHomeView()
	 */
	@Override
	public List<Object[]> getHomeView() {
		
		/**
		 * 第一种实现思路
		 * 0.声明个视图数据
		 * 1.定义一条原数据结构
		 * 2.获取设备的信息并将值合并到原上
		 * 3.获取信息系统的信息
		 * 4.在原数据上合并
		 * 5.将格式化后的原数据加入视图数据中
		 */
		//return getviweHome_one();  //第一种实现过于复杂思路死板
		
		
		/**
		 * 第二种实现思路
		 * 1.查出所有安全域（页面也将所有安全域显示出来）（在安全域对象中声明两个字段分别是deviceNum，infoNum分别是@Transient）
		 * 2.查出安全域设备个数数据  getGroupDevice();  
		 * 3.查出安全域信息系统个数数据 getGroupSys();
		 * 4.封装成该有的数据格式
		 */
		return getviweHome_two();
		
		
		/**
		 * 第三种使用sql 利用全外连接 
		 */
		
	
	}
	
	private List<Object[]> getviweHome_two(){
		List<Object[]> homeDB = new ArrayList<Object[]>(); //视图数据
		List<Object[]> groupDevice = getGroupDevice();  //安全域设备数据
		List<Object[]> groupSys = getGroupSys(); //安全域信息系统数据
		List<AssetGroup> findAll = findAll(); //所有安全域
		Long deviceNum = 0l; //设备数量
		Long infoNum = 0l;//信息系统数量
		//加入所有安全域
		for (int i = 0; i < findAll.size(); i++) {
			Object[] dbObje = new Object[4];
			dbObje[0] = findAll.get(i).getId();
			dbObje[1] = findAll.get(i).getName();
			dbObje[2] = deviceNum;
			dbObje[3] = infoNum;
			homeDB.add(dbObje);
		}
		
		//设置设备数量
		for (int i = 0; i < homeDB.size(); i++) {
			//获取安全域id
			Long grouId = Long.valueOf(String.valueOf(homeDB.get(i)[0]));
			//循环设备信息
			for (int j = 0; j < groupDevice.size(); j++) {
				//获取设备中的安全域id
				Long tempId = Long.valueOf(String.valueOf(groupDevice.get(j)[0]));
				//如果相等就设置上设备的值
				if(grouId.equals(tempId)){
					deviceNum = Long.valueOf(String.valueOf(groupDevice.get(j)[2]));
					homeDB.get(i)[2] = deviceNum;
				}
			}
		}
		
		//设备信息系统数量
		for (int i = 0; i < homeDB.size(); i++) {
			//获取安全域id
			Long grouId = Long.valueOf(String.valueOf(homeDB.get(i)[0]));
			//循环信息系统
			for (int j = 0; j < groupSys.size(); j++) {
				//获取信息系统中的安全域id
				Long tempId = Long.valueOf(String.valueOf(groupSys.get(j)[0]));
				//如果相等就设置上信息系统的值
				if(grouId.equals(tempId)){
					deviceNum = Long.valueOf(String.valueOf(groupSys.get(j)[2]));
					homeDB.get(i)[3] = deviceNum;
				}
			}
		}
		return homeDB;
	}
	
	
	@SuppressWarnings("unused")
	private List<Map<String, Object>> getviweHome_one(){
		//查询出所有的安全域
				//执行这两次查询
				//
				List<Object[]> groupDevice = getGroupDevice();  //安全域设备数据
				List<Object[]> groupSys = getGroupSys(); //安全域信息系统数据
				List<Map<String, Object>> homeDB  = new ArrayList<Map<String,Object>>(); //视图数据
				Long infoNum = 0l;
				Long deviceNum = 0l;
				//0.如果设备数据比信息系统数据多
				if(groupDevice.size() > groupSys.size()){
					//循环安全域设备数据，得到一条忘map中加一条，同时循环安全域信息系统数据，把相同安全域的数据综合到一起存到map中
					for (int i = 0; i < groupDevice.size(); i++) {
						//1.定义一条原数据结构
						Map<String, Object> viewDb = new HashMap<String, Object>();
						viewDb.put("groupId", 0);
						viewDb.put("groupName", 0);
						viewDb.put("deviceNum", 0);
						viewDb.put("infoNum", 0);
						//2.获取设备的信息并将值合并到原上
						Long deviceGroupId = Long.valueOf(groupDevice.get(i)[0].toString());  //设备数据中的安全域id
						String deviceGroupName = groupDevice.get(i)[1].toString();  //设备数据中的安全域名称
						deviceNum = Long.valueOf(groupDevice.get(i)[2].toString());  //所在安全域的设备总数
						HashMap<String, Object> deviceMap = new HashMap<String, Object>();
						deviceMap.put("groupId", deviceGroupId);
						deviceMap.put("groupName", deviceGroupName);
						deviceMap.put("deviceNum", deviceNum);
						viewDb.putAll(deviceMap);
						
						//当信息系统数据长度小于设备的就不进操作了
						if(i<groupSys.size()){
							//3.获取信息系统的信息
							Long infoGroupId = Long.valueOf(groupSys.get(i)[0].toString());
							String infoGroupName = groupSys.get(i)[1].toString();
							infoNum = Long.valueOf(groupSys.get(i)[2].toString());
							HashMap<String, Object> infoMap = new  HashMap<String, Object>();
							//如果信息系统中的安全域id与设备中的安全域想等
							if(infoGroupId.equals(deviceGroupId)){
								//4.在原数据上合并
								infoMap.put("infoNum", infoNum);
								viewDb.putAll(infoMap);
							}else{
								//4.如果没有相等的,就新创建一条并放在视图数据中
								infoMap.put("groupId", infoGroupId);
								infoMap.put("groupName", infoGroupName);
								infoMap.put("infoNum", infoNum);
								viewDb.put("deviceNum", 0);
								homeDB.add(infoMap);
							}
						}
						//5.将格式化后的原数据加入视图数据中
						homeDB.add(viewDb);
					}
					//0.如果设备数据比信息系统数据少 以下代码和上面的代码意思同理
				}else if(groupDevice.size() < groupSys.size()){
					//循环安全域设备数据，得到一条忘map中加一条，同时循环安全域信息系统数据，把相同安全域的数据综合到一起存到map中
					for (int i = 0; i < groupSys.size(); i++) {
						Map<String, Object> viewDb = new HashMap<String, Object>();
						viewDb.put("groupId", 0);
						viewDb.put("groupName", 0);
						viewDb.put("deviceNum", 0);
						viewDb.put("infoNum", 0);
						Long infoGroupId = Long.valueOf(groupSys.get(i)[0].toString());
						String infoGroupName = groupSys.get(i)[1].toString();
						infoNum = Long.valueOf(groupSys.get(i)[2].toString());
						HashMap<String, Object> infoMap = new  HashMap<String, Object>();
						infoMap.put("groupId", infoGroupId);
						infoMap.put("groupName", infoGroupName);
						infoMap.put("infoNum", infoGroupName);
						viewDb.putAll(infoMap);
						
						if(i<groupDevice.size()){
							Long deviceGroupId = Long.valueOf(groupDevice.get(i)[0].toString());  //设备数据中的安全域id
							String deviceGroupName = groupDevice.get(i)[1].toString();  //设备数据中的安全域名称
							deviceNum = Long.valueOf(groupDevice.get(i)[2].toString());  //所在安全域的设备总数
							HashMap<String, Object> deviceMap = new HashMap<String, Object>();
							if(infoGroupId.equals(deviceGroupId)){
								deviceMap.put("deviceNum", deviceNum);
								viewDb.putAll(deviceMap);
							}else{
								infoMap.put("groupId", deviceGroupName);
								infoMap.put("groupName", deviceGroupName);
								infoMap.put("infoNum", 0);
								viewDb.put("deviceNum", deviceNum);
								homeDB.add(infoMap);
							}
						}
						homeDB.add(viewDb);
					}
				}else{
					//如果相等就直接操作
					//循环安全域设备数据，得到一条忘map中加一条，同时循环安全域信息系统数据，把相同安全域的数据综合到一起存到map中
					for (int i = 0; i < groupDevice.size(); i++) {
						Map<String, Object> viewDb = new HashMap<String, Object>();
						viewDb.put("groupId", 0);
						viewDb.put("groupname", 0);
						viewDb.put("deviceNum", 0);
						viewDb.put("infoNum", 0);
						
						Long deviceGroupId = Long.valueOf(groupDevice.get(i)[0].toString());  //设备数据中的安全域id
						String deviceGroupName = groupDevice.get(i)[1].toString();  //设备数据中的安全域名称
						deviceNum = Long.valueOf(groupDevice.get(i)[2].toString());  //所在安全域的设备总数
						HashMap<String, Object> deviceMap = new HashMap<String, Object>();
						deviceMap.put("groupId", deviceGroupId);
						deviceMap.put("groupname", deviceGroupName);
						deviceMap.put("deviceNum", deviceNum);
						deviceMap.put("infoNum", 0);
						viewDb.putAll(deviceMap);
							
						Long infoGroupId = Long.valueOf(groupSys.get(i)[0].toString());
						String infoGroupName = groupSys.get(i)[1].toString();
						infoNum = Long.valueOf(groupSys.get(i)[2].toString());
						HashMap<String, Object> infoMap = new  HashMap<String, Object>();
						if(infoGroupId.equals(deviceGroupId)){
							infoMap.put("infoNum", infoNum);
							viewDb.putAll(infoMap);
						}else{
							infoMap.put("infoGroupId", infoGroupId);
							infoMap.put("infoGroupName", infoGroupName);
							infoMap.put("infoNum", infoNum);
							viewDb.put("deviceNum", 0);
							homeDB.add(infoMap);
						}
						homeDB.add(viewDb);
					}
				}
				return homeDB;
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.AssetGroupService#findByAsset(java.lang.Long, int)
	 */
	@Override
	public List<AssetGroup> findByAsset(Long assetId, int type) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "group.byAssetID"); 
		qt.addParameter("assetId", assetId);
		qt.addParameter("type", type);
		return findDomains(qt);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.AssetGroupService#findPages(secfox.soc.melon.asset.query.AssetGroupPageQuery)
	 */
	@Override
	public Pagination<AssetGroup> findPages(AssetGroupPageQuery query) {
		return findDomainPage(QueryType.JQL, query, new PaginationBuilder<AssetGroup, AssetGroupPageQuery>() {

			@Override
			public void buildSelect(QueryTemplate qt) {
				qt.append("select a from AssetGroup a ");
			}

			@Override
			public void buildWhere(AssetGroup s, QueryTemplate qt) {
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

				//行政区域
				if(StringUtils.isNotBlank(s.getOrganPath()) && !"null".equals(s.getOrganPath())){
					qt.append(" and ( a.organPath like :organPath ");
					qt.addParameter("organPath",QueryTemplate.buildAllLike(","+s.getOrganPath()));
					qt.append(" or a.organPath like :organRightPath )");
					qt.addParameter("organRightPath",QueryTemplate.buildRightLike(s.getOrganPath()));
				}
				
				//类型
				if(StringUtils.isNotBlank(s.getTypePath()) && !"null".equals(s.getTypePath())){
					qt.append(" and ( a.typePath like :typePath ");
					qt.addParameter("typePath",QueryTemplate.buildAllLike(","+s.getTypePath()));
					qt.append(" or a.typePath like :typeRightPath )");
					qt.addParameter("typeRightPath",QueryTemplate.buildRightLike(s.getTypePath()));
				}
			}

			@Override
			public void buildBys(String column, SortOrder order,
					QueryTemplate qt) {
				column = "id";
				qt.append(QueryTemplate.buildOrderBy("a", column, order));
			}

			@Override
			public void buildCount(QueryTemplate qt) {
				qt.append("select count(a.id) from AssetGroup a ");
			}
		});
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.AssetGroupService#findPagesByInfo(secfox.soc.melon.asset.query.AssetGroupPageQuery)
	 */
	@Override
	public Pagination<AssetGroup> findPagesByInfo(AssetGroupPageQuery query) {
		Pagination<Map<String,Object>> pageMap = assetGroupDao.findPageByInfo(query);

		Pagination<AssetGroup> pageGroup = new Pagination<AssetGroup>();//分页构造器数据
		List<AssetGroup> listGroup = Lists.newArrayList(); //分页构造器中的设备数据
		
		//获取分页中的数据
		List<Map<String,Object>> results = pageMap.getResults();
		//如果分页有值
		if(results.size() > 0 ){
			//重构分页构造器数据
			for (int i = 0; i < results.size(); i++) {
				AssetGroup group=new AssetGroup();
				group.setId(Long.parseLong(results.get(i).get("groupId").toString()));
				group.setName(results.get(i).get("groupName").toString());
				group.setCode(results.get(i).get("groupCode").toString());
				group.setOrganName(results.get(i).get("organName").toString());
				group.setNetType(Integer.parseInt(results.get(i).get("netType").toString()));
				group.setUseState("1".equals(results.get(i).get("useState").toString()));
				listGroup.add(group);
			}
			pageGroup.setCurrPage(pageMap.getCurrPage());
			pageGroup.setPageSize(pageMap.getPageSize());
			pageGroup.setResults(listGroup);
			pageGroup.setCount(pageMap.getCount());
		}
		
		return pageGroup;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.AssetGroupService#findByType(java.lang.Long)
	 */
	@Override
	public List<AssetGroup> findByType(Long typeId) {
		AssetType type=assetTypeService.find(typeId);
		String typePath=type.getPath();
		List<AssetGroup> list=assetGroupDao.findByTypePath(typePath);
		return list;
	}

}


