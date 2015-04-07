/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.melon.asset.dao.AssetGroupDao;
import secfox.soc.melon.asset.dao.InfoSystemDao;
import secfox.soc.melon.asset.domain.AssetGroup;
import secfox.soc.melon.asset.domain.AssetType;
import secfox.soc.melon.asset.domain.DeviceRoles;
import secfox.soc.melon.asset.domain.GroupRelation;
import secfox.soc.melon.asset.domain.InfoSystem;
import secfox.soc.melon.asset.query.InfoSystemPageQuery;
import secfox.soc.melon.asset.service.AssetTypeService;
import secfox.soc.melon.asset.service.DeviceRolesService;
import secfox.soc.melon.asset.service.GroupRelationService;
import secfox.soc.melon.asset.service.InfoSystemService;
import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.SortOrder;
import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.organ.domain.Organization;
import secfox.soc.melon.organ.serivce.OrganizationService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.JqlQueryTemplate;
import secfox.soc.melon.persistence.NamedQueryTemplate;
import secfox.soc.melon.persistence.PaginationBuilder;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.SqlQueryTemplate;
import secfox.soc.melon.persistence.SqlResultMappingQueryTemplate;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.security.util.SecurityContextUtils;

import com.google.common.collect.Lists;

/**
 * 信息系统服务类实现接口
 * 
 * @since 2014-9-25
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
@Service
public class InfoSystemServiceImpl extends GenericServiceImpl<InfoSystem, Long>
		implements InfoSystemService {

	private InfoSystemDao infoSystemDao;
	private DeviceRolesService deviceRolesService;
	private GroupRelationService groupRelationService;
	private AssetGroupDao assetGroupDao;
	private OrganizationService organizationService;
	private AssetTypeService assetTypeService;
	@Inject
	public InfoSystemServiceImpl(AssetTypeService assetTypeService,OrganizationService organizationService,AssetGroupDao assetGroupDao,GroupRelationService groupRelationService,DeviceRolesService deviceRolesService,
			InfoSystemDao infoSystemDao) {
		super();
		this.infoSystemDao = infoSystemDao;
		this.assetGroupDao = assetGroupDao;
		this.groupRelationService = groupRelationService;
		this.deviceRolesService = deviceRolesService;
		this.organizationService = organizationService;
		this.assetTypeService = assetTypeService;
	}

	@Override
	protected GenericDao<InfoSystem, Long> getDao() {
		return infoSystemDao;
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#remove(java.io.Serializable)
	 */
	@Override
	@Transactional(readOnly = false)
	public void remove(Long infoId) {
		List<DeviceRoles> findByInfoId = deviceRolesService.findByInfoId(infoId);
		if(findByInfoId !=null && findByInfoId.size()>0){
			// 删除该信息系统与设备的关系
			deviceRolesService.removeByInfoId(infoId);
		}
		List<GroupRelation> findBySysId = groupRelationService.findBySysId(infoId);
		if (findBySysId !=null&&findBySysId.size()>0) {
			//删除安全域
			groupRelationService.removeBySysId(infoId);
		}
		infoSystemDao.remove(infoId);
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.InfoSystemService#findPages(secfox.soc.melon.asset.query.InfoSystemPageQuery)
	 */
	@Override
	public Pagination<InfoSystem> findPages(InfoSystemPageQuery pageQuery) {

		return findDomainPage(QueryType.JQL, pageQuery,
				new PaginationBuilder<InfoSystem, InfoSystemPageQuery>() {

					@Override
					public void buildSelect(QueryTemplate qt) {
						qt.append("select a from InfoSystem a ");
					}

					@Override
					public void buildWhere(InfoSystem s, QueryTemplate qt) {
						//此条件用于实现对比功能
						if(s.getId() != null){
							qt.append(" and a.id not in (:id) ");
							qt.addParameter("id", s.getId());
						}
						
						// 按名称查询
						if (StringUtils.isNotBlank(s.getName())) {
							qt.append(" and a.name like :name ");
							qt.addParameter("name",QueryTemplate.buildAllLike(StringUtils.strip(s.getName())));
						}

						// 编号
						if (StringUtils.isNotBlank(s.getCode())) {
							qt.append(" and a.code like :code ");
							qt.addParameter("code",
									QueryTemplate.buildAllLike(StringUtils.strip(s.getCode())));
						}

						// 系统状态 -1:全部
						if (s.getUsing() > -1) {
							qt.append(" and a.using = :state ");
							qt.addParameter("state", s.getUsing());
						}
						
						// 单位名称
						if (StringUtils.isNotBlank(s.getOrganName())) {
							qt.append(" and a.organName like :organName ");
							qt.addParameter("organName", QueryTemplate.buildAllLike(StringUtils.strip(s.getOrganName())));
						}

						// 单位路径
						if (StringUtils.isNotBlank(s.getOrganPath())&&!"null".equals(s.getOrganPath())) {
							qt.append(" and ( a.organPath like :organPath ");
							qt.addParameter("organPath",QueryTemplate.buildAllLike(","+s.getOrganPath()));
							qt.append(" or a.organPath like :organRightPath )");
							qt.addParameter("organRightPath", QueryTemplate.buildRightLike(s.getOrganPath()));
						}
						
						// 类型
						if (StringUtils.isNotBlank(s.getTypePath())&&!"null".equals(s.getTypePath())) {
							qt.append(" and ( a.typePath like :typePath ");
							qt.addParameter("typePath",QueryTemplate.buildAllLike(","+s.getTypePath()));
							qt.append(" or a.typePath like :typeRightPath )");
							qt.addParameter("typeRightPath", QueryTemplate.buildRightLike(s.getTypePath()));
						}
						//级别
						if(s.getSafeLeven()!=0){
							qt.append(" and a.safeLeven =:level ");
							qt.addParameter("level", s.getSafeLeven());
						}
						// TODO 网络类型查询 wwy
					}

					@Override
					public void buildBys(String column, SortOrder order,
							QueryTemplate qt) {
						if (StringUtils.isBlank(column)) {
							column = "organName";
						}
						qt.append(QueryTemplate.buildOrderBy("a", column, order));
					}

					@Override
					public void buildCount(QueryTemplate qt) {
						qt.append("select count(a.id) from InfoSystem a ");
					}
				});

	}
	
	/*
	 * czj获取系统信息
	 * @see secfox.soc.melon.asset.service.InfoSystemService#findByCode(java.lang.String)
	 */
	@Override
	public InfoSystem findByCode(String code) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"infoSystem.findByCode");
		qt.addParameter("code", code);
		return findFirstDomain(qt);
	}

	/*
	 * czj查询全部信息
	 * @see secfox.soc.melon.asset.service.InfoSystemService#findByUrl(java.lang.String)
	 */
	@Override
	public InfoSystem findByUrl(String url) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"infoSystem.findByUrl");
		qt.addParameter("url", url);
		return findFirstDomain(qt);
	}

	/*
	 * czj查询全部信息
	 * @see secfox.soc.melon.asset.service.InfoSystemService#findAll()
	 */
	@Override
	public List<InfoSystem> findAll() {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"infoSystem.findAll");
		return findDomains(qt);
	}

	/*
	 * czj信息系统home数据
	 * @see secfox.soc.melon.asset.service.InfoSystemService#listOrganWithCount()
	 */
	@Override
	public List<Object[]> listOrganWithCount() {
		//分组统计单位人数
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "infoSystem.groupByOrgan");
		return find(qt);
	}

	/*
	 * czj
	 * @see secfox.soc.melon.asset.service.InfoSystemService#findByOrgan(java.lang.Long)
	 */
	@Override
	public List<InfoSystem> findByOrgan(Long organId) {
		//根据单位查信息系统
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "infoSystem.findByOrgan");
		qt.addParameter("organId", organId);
		return findDomains(qt);
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#find(java.io.Serializable)
	 */
	@Override
	@Transactional(readOnly = true)
	public InfoSystem find(Long pk) {
		InfoSystem infoSystem = super.find(pk);
		// 获取安全域name和id
		List<Object[]> assetGroup = groupRelationService.findByTypeAssetId(1,pk);
		if (assetGroup.size() > 0) {
			Long[] domainIds = new Long[assetGroup.size()]; // 所属安全域ids
			String[] domainNames = new String[assetGroup.size()];// 所属安全域names
			for (int i = 0; i < assetGroup.size(); i++) {
				domainNames[i] = assetGroup.get(i)[0].toString();
				domainIds[i] = Long.valueOf(assetGroup.get(i)[1].toString());
			}
			infoSystem.setDomainIds(domainIds); 
			infoSystem.setDomainNames(domainNames);
		}
		return infoSystem;
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#persist(secfox.soc.melon.persistence.Identifiable)
	 */
	@Override
	@Transactional(readOnly = false)
	public void persist(InfoSystem entity) {
		UserInfo currentUserInfo = SecurityContextUtils.getCurrentUserInfo();
		entity.setModifierId(currentUserInfo.getUserId());
		entity.setModifierName(currentUserInfo.getUsername());
		entity.setCreateTime(new Date());
		
		
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "infoSystem.findNumberSum"); 
		qt.addParameter("organId", entity.getOrganId());
		InfoSystem result = findFirstDomain(qt);
		
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
		
		
		
		super.persist(entity);
		
		//安全域
		Long[] domainIds = entity.getDomainIds();
		if(domainIds !=null && domainIds.length>0){
			for (int i = 0; i < domainIds.length; i++) {
				String path = "";
				AssetGroup assetGroup = assetGroupDao.find(domainIds[i]);
				if(assetGroup!=null){
					path = assetGroup.getPath();
				}
				// 保存安全域与设备的关系
				GroupRelation createDeviceConnection = GroupRelation.createSysConnection(entity.getId(), domainIds[i],path);
				groupRelationService.persist(createDeviceConnection);
			}
		}
		
		//设备关系
		Long[] deviceIds = entity.getDeviceIds();
		if(deviceIds!=null && deviceIds.length>0){
			for (int i = 0; i < deviceIds.length; i++) {
				DeviceRoles roles = DeviceRoles.createInfoConnection(entity.getId(),deviceIds[i]);
				deviceRolesService.persist(roles);
			}
		}
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#merge(secfox.soc.melon.persistence.Identifiable)
	 */
	@Override
	//@Transactional(readOnly = false)
	public InfoSystem merge(InfoSystem entity) {
		UserInfo currentUserInfo = SecurityContextUtils.getCurrentUserInfo();
		entity.setModifierId(currentUserInfo.getUserId());
		entity.setModifierName(currentUserInfo.getUsername());
		entity.setCreateTime(new Date());
		//删除设备安全域关系数据在保存
		groupRelationService.removeBySysId(entity.getId());
		
		//TODO
		deviceRolesService.removeByInfoId(entity.getId());
		
		Long[] domainIds = entity.getDomainIds();
		if(domainIds !=null && domainIds.length>0){
			for (int i = 0; i < domainIds.length; i++) {
				String path = "";
				AssetGroup assetGroup = assetGroupDao.find(domainIds[i]);
				if(assetGroup!=null){
					path = assetGroup.getPath();
				}
				// 保存安全域与设备的关系
				GroupRelation createDeviceConnection = GroupRelation.createSysConnection(entity.getId(), domainIds[i],path);
				groupRelationService.persist(createDeviceConnection);
			}
		}
		
		//设备关系
		Long[] deviceIds = entity.getDeviceIds();
		if(deviceIds!=null && deviceIds.length>0){
			for (int i = 0; i < deviceIds.length; i++) {
				DeviceRoles roles = DeviceRoles.createInfoConnection(entity.getId(),deviceIds[i]);
				deviceRolesService.persist(roles);
			}
		}
		
		
		
		return super.merge(entity);
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.InfoSystemService#findByAssetGroup(java.lang.Long)
	 */
	@Override
	public List<InfoSystem> findByAssetGroup(Long groupId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"info.findByRelation");
		qt.addParameter("groupId", groupId);
		return findDomains(qt);
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.InfoSystemService#findByIds(java.lang.Long[])
	 */
	@Override
	public List<InfoSystem> findByIds(Long[] ids) {
		List<Long> idd = Lists.newArrayList(ids);
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select a from InfoSystem a where ");
		qt.appendIn("id", idd);
		List<InfoSystem> tempInfo = findDomains(qt); //获取到信息系统
		List<InfoSystem> infoSystems = Lists.newArrayList();
		for (int i = 0; i < tempInfo.size(); i++) {
			InfoSystem infoSystem = this.find(tempInfo.get(i).getId());
			infoSystems.add(infoSystem);
		}
		return infoSystems;
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.InfoSystemService#findByUserBusiness(java.lang.Long)
	 */
	@Override
	public List<InfoSystem> findByUserBusiness(Long userId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "infoSystem.findByUserBusiness");
		qt.addParameter("userId", userId);
		return findDomains(qt);
	}

	@Override
	public List<InfoSystem> findByQuickNote(Long userId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "infoSystem.findByQuickNote");
		qt.addParameter("userId", userId);
		return findDomains(qt);
	}

	
	public Pagination<Map<String,Object>> findPageByGroup(InfoSystemPageQuery query) {
		Pagination<Map<String,Object>> findPageByGroup = infoSystemDao.findPageByGroup(query);
		return findPageByGroup;
	}
	
	public InfoSystem createRoot() {
		InfoSystem is = new InfoSystem();
		is.setName("信息系统");
		is.setParentId(null);
		is.setId(1L);
//		is.setAsRoot(true);
//		is.setOrder(0);
		is.getState().setOpened(true);
		return is;
	}
	
	
	protected EntityManager entityManager;
	
	/**
     * 根据查询模板创建查询对象
     * @param qt
     * @param clazz
     * @return
     */
    protected <R> Query createQuery(QueryTemplate qt, Class<R> clazz) {
    	Query query = null;
    	if(qt instanceof JqlQueryTemplate) {
    		if(clazz == null) {
    			query = entityManager.createQuery(qt.getQuery());
    		} else {
    			query = entityManager.createQuery(qt.getQuery(), clazz);
    		}
    	}
    	if(qt instanceof NamedQueryTemplate) {
    		if(clazz == null) {
    			query = entityManager.createNamedQuery(qt.getQuery());
    		} else {
    			query = entityManager.createNamedQuery(qt.getQuery(), clazz);
    		}
    	}
    	if(qt instanceof SqlQueryTemplate) {
    		query = entityManager.createNativeQuery(qt.getQuery());
    	}
    	if(qt instanceof SqlResultMappingQueryTemplate) {
    		SqlResultMappingQueryTemplate sqt = (SqlResultMappingQueryTemplate)qt;
    		query = entityManager.createNativeQuery(qt.getQuery(), sqt.getSqlResultMapping());
    	}
    	//拼接查询条件
        if(qt.getParameters() != null) {
            for(Entry<String, ?> paramEntry : qt.getParameters().entrySet()) {
                query.setParameter(paramEntry.getKey(), paramEntry.getValue());
            }
        }
        //构建查询缓存
        if(qt.isCachable()) {
            query.setHint("org.hibernate.cacheable", true);
            if(StringUtils.isNotBlank(qt.getCacheRegion())) {
                query.setHint("org.hibernate.cacheRegion", qt.getCacheRegion());
            } else {
                query.setHint("org.hibernate.cacheRegion", clazz.getName());
            }
        }
        //构建分页
        query.setFirstResult(qt.getFirstResult());
        query.setMaxResults(qt.getMaxResults());
     	return query;
    }
	
		@Override
		public List<InfoSystem> findTree(Long rootId) {
			Long cId = SecurityContextUtils.getCurrentAccount().getCompanyId();
			
			StringBuffer sb = new StringBuffer();
			sb.append("SELECT ");
			sb.append("	I.PK, I.A_NAME, I.PARENTID, (SELECT COUNT(D_DEVICEID) FROM T_ASSET_CONN_DI DI WHERE DI.D_INFOID = I.PK) ");
			sb.append("FROM ");
			sb.append("	T_ASSET_INFO I ");
			sb.append("WHERE ");
			sb.append("	I.ORGAN_PATH LIKE '%" + cId + "%'");
			
			List<InfoSystem> lists = infoSystemDao.findInfoSystem(sb.toString());
			
			InfoSystem is = new InfoSystem();
			
			if(rootId==BaseConstants.ROOT_ID){
				is = createRoot();
			} else {
				is = find(rootId); //根据id获取
//				path = is.getPath();
			}
			lists.add(is);
			
			return lists;
		}


		/* (non-Javadoc)
		 * @see secfox.soc.melon.asset.service.InfoSystemService#findByType(java.lang.Long)
		 */
		@Override
		public List<InfoSystem> findByType(Long typeId) {
			AssetType type=assetTypeService.find(typeId);
			if(type == null ){
				return new ArrayList<InfoSystem>();
			}
			String typePath=type.getPath();
			List<InfoSystem> list=infoSystemDao.findByTypePath(typePath);
			return list;
		}
	
}
