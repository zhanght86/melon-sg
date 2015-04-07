/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.dao.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import secfox.soc.melon.asset.dao.DeviceDao;
import secfox.soc.melon.asset.domain.Device;
import secfox.soc.melon.asset.query.DevicePageQuery;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.util.DictionaryUtils;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;
import secfox.soc.melon.security.util.SecurityContextUtils;

/**
 * 设备dao实现
 * @since 2014-9-26
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version  1.0
 */
@Repository
public class DeviceDaoImpl extends GenericDaoImpl<Device,Long> implements DeviceDao {
	
	/**
	 * @param persistentClass
	 */
	public DeviceDaoImpl() {
		super(Device.class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.dao.DeviceDao#findByName(java.lang.String)
	 */
	@Override
	public Device findByName(String name) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"device.findByName");
		qt.addParameter("name", name);
		return findFirstDomain(qt);
	}

	@Override
	public Pagination<Map<String, Object>> findPageByDomain(DevicePageQuery pageQuery) {
		String sql = "SELECT distinct GDI.ASSET_ID as id,DEVICE.A_NAME,device.A_CODE,DEVICE.CHARGER_NAME,DEVICE.ORGAN_NAME,DEVICE.US_ING " +
					 "FROM T_ASSET_CONN_GDI GDI,T_ASSET_DEVICE DEVICE " +
					 "where RELATION_TYPE = 0 and gdi.ASSET_ID = device.pk ";
		StringBuffer sqlBuffer = new StringBuffer(sql);
		
		sqlBuffer.append(buildWhere(pageQuery)).append(buildOrderBy(pageQuery));
		
		Query query = entityManager.createNativeQuery(sqlBuffer.toString());
		query.setFirstResult(pageQuery.getFirstResult());
		query.setMaxResults(pageQuery.getMaxResults());
		@SuppressWarnings("unchecked")
		List<Object[]> resultList = query.getResultList();
		List<Map<String, Object>> values = Lists.newArrayList();
		for (int i = 0; i < resultList.size(); i++) {
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("id", resultList.get(i)[0]);
			maps.put("name", resultList.get(i)[1]);
			maps.put("code", resultList.get(i)[2]);
			maps.put("chargeName", resultList.get(i)[3]);
			maps.put("organName", resultList.get(i)[4]);
			String usingValue = DictionaryUtils.filterValue("usingState",resultList.get(i)[5]);
			maps.put("using", usingValue);
			values.add(maps);
		}
		Pagination<Map<String, Object>> pa = new Pagination<Map<String, Object>>();
		pa.setCurrPage(pageQuery.getCurrPage());
		pa.setPageSize(pageQuery.getPageSize());
		pa.setResults(values);
		pa.setCount(getResultCount(pageQuery));
		return pa;
	}
	
	public Pagination<Map<String, Object>> findPageByDeviceInfo(DevicePageQuery pageQuery, String enviPath) {
		
		Long cId = SecurityContextUtils.getCurrentAccount().getCompanyId();
		
		String name = pageQuery.getSearchForm().getName();
		
		StringBuffer sb = new StringBuffer();
		sb.append("SELECT ");
		sb.append(" DISTINCT D.PK as id,D.A_CODE,D.A_NAME,D.TYPE_NAME,D.ORGAN_NAME, D.US_ING ");
		sb.append("FROM ");
		sb.append("	T_ASSET_DEVICE D,T_ASSET_INFO I,T_ASSET_CONN_DI DI ");
		sb.append("WHERE ");
		sb.append("	DI.D_TYPE = 1 AND DI.D_DEVICEID = D.PK AND DI.D_INFOID = I.PK ");
		sb.append(" AND I.ORGAN_PATH LIKE '%" + cId + "%'");
		
		if(! "1".equals(enviPath)) {
			sb.append(" AND I.PK = " + enviPath);
		}
		
		if(null != name && !"".equals(name.trim())) {
			sb.append(" AND D.A_NAME LIKE '%" + name + "%'");
		}
		sb.append(buildOrderBy(pageQuery));
		Query query = entityManager.createNativeQuery(sb.toString());
		query.setFirstResult(pageQuery.getFirstResult());
		query.setMaxResults(pageQuery.getMaxResults());
		@SuppressWarnings("unchecked")
		List<Object[]> resultList = query.getResultList();
		List<Map<String, Object>> values = Lists.newArrayList();
		for (int i = 0; i < resultList.size(); i++) {
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("id", resultList.get(i)[0]);
			maps.put("code", resultList.get(i)[1]);
			maps.put("name", resultList.get(i)[2]);
			maps.put("type", resultList.get(i)[3]);
			maps.put("organName", resultList.get(i)[4]);
			String usingValue = DictionaryUtils.filterValue("usingState",resultList.get(i)[5]);
			maps.put("using", usingValue);
			values.add(maps);
		}
		Pagination<Map<String, Object>> pa = new Pagination<Map<String, Object>>();
		pa.setCurrPage(pageQuery.getCurrPage());
		pa.setPageSize(pageQuery.getPageSize());
		pa.setResults(values);
		pa.setCount(pa.getResults().size());
		return pa;
	}
	
	/**
	 * 获取总数
	 * @param pageQuery
	 * @return
	 */
	private int getResultCount(DevicePageQuery pageQuery) {
		String sql = "SELECT count(distinct GDI.ASSET_ID) "+
					 " FROM T_ASSET_CONN_GDI GDI,T_ASSET_DEVICE DEVICE "+
					 " where RELATION_TYPE = 0 and gdi.ASSET_ID = device.pk";
		StringBuffer sqlBuffer = new StringBuffer(sql);
		
		sqlBuffer.append(buildWhere(pageQuery));
		Query createQuery = entityManager.createNativeQuery(sqlBuffer.toString());
		return Integer.parseInt(createQuery.getSingleResult().toString());
	}
	
	
	/**
	 * yb 拼接where条件
	 * @param pageQuery
	 * @return where条件
	 */
	private StringBuffer buildWhere(DevicePageQuery pageQuery){
		StringBuffer sqlBuffer = new StringBuffer();
		//当前用户组织id
		sqlBuffer.append(" and DEVICE.organ_path like '"+QueryTemplate.buildAllLike(SecurityContextUtils.getCurrentAccount().getCompanyId().toString())+"'");
		//安全域路径
		if(StringUtils.isNotBlank(pageQuery.getDomainPath()) && !"null".equals(pageQuery.getDomainPath())){
			sqlBuffer.append("and ( GDI.GROUP_PATH like '"+QueryTemplate.buildAllLike(","+pageQuery.getDomainPath())+"'");
			sqlBuffer.append(" or GDI.GROUP_PATH like '"+QueryTemplate.buildRightLike(pageQuery.getDomainPath().toString())+"')");
		}
		
		Device searchForm=pageQuery.getSearchForm();
		//名称
		if(StringUtils.isNotBlank(searchForm.getName())){
			sqlBuffer.append(" and DEVICE.a_name like '"+QueryTemplate.buildAllLike(searchForm.getName())+"'");
		}
		return sqlBuffer;
	}
	/**
	 * yb 拼接order by 
	 * @param pageQuery
	 * @return order by语句
	 */
	private StringBuffer buildOrderBy(DevicePageQuery pageQuery){
		return new StringBuffer(" order by "+pageQuery.getSortColumn()+" "+pageQuery.getOrder());
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.asset.dao.DeviceDao#findByType(java.lang.Long)
	 */
	@Override
	public List<Device> findByTypePath(String typePath) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"device.findByTypePath");
		qt.addParameter("path", QueryTemplate.buildAllLike(","+typePath));
		qt.addParameter("rightPath", QueryTemplate.buildRightLike(typePath));
		Long organId=SecurityContextUtils.getCurrentAccount().getCompanyId();
		qt.addParameter("organPath", QueryTemplate.buildAllLike(","+organId+","));
		qt.addParameter("organId", organId);
		List<Device> list=findDomains(qt);
		return list==null?new ArrayList<Device>():list;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.asset.dao.DeviceDao#findByOs()
	 */
	@Override
	public List<Device> findByOs() {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"device.findByOs");
		Long organId=SecurityContextUtils.getCurrentAccount().getCompanyId();
		qt.addParameter("organPath", QueryTemplate.buildAllLike(","+organId+","));
		qt.addParameter("organId", organId);
		List<Device> list=findDomains(qt);
		return list==null?new ArrayList<Device>():list;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.asset.dao.DeviceDao#findByEnvi()
	 */
	@Override
	public List<Device> findByEnviPath(String enviPath) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"device.findByEnviPath");
		qt.addParameter("path", QueryTemplate.buildAllLike(","+enviPath));
		qt.addParameter("rightPath", QueryTemplate.buildRightLike(enviPath));
		Long organId=SecurityContextUtils.getCurrentAccount().getCompanyId();
		qt.addParameter("organPath", QueryTemplate.buildAllLike(","+organId+","));
		qt.addParameter("organId", organId);
		List<Device> list=findDomains(qt);
		return list==null?new ArrayList<Device>():list;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.asset.dao.DeviceDao#findByOrganPath(java.lang.String)
	 */
	@Override
	public List<Device> findByOrganPath(String organPath) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"device.findByOrganPath");
		qt.addParameter("path", QueryTemplate.buildAllLike(","+organPath));
		qt.addParameter("rightPath", QueryTemplate.buildRightLike(organPath));
		List<Device> list=findDomains(qt);
		return list==null?new ArrayList<Device>():list;
	}
		
}
