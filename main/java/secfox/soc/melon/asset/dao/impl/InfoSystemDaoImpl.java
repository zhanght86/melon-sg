/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.dao.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import secfox.soc.melon.asset.dao.InfoSystemDao;
import secfox.soc.melon.asset.domain.InfoSystem;
import secfox.soc.melon.asset.query.InfoSystemPageQuery;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.util.DictionaryUtils;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;
import secfox.soc.melon.security.util.SecurityContextUtils;

/**
 * 信息系统dao实现接口
 * 
 * @since 2014-9-25
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
@Repository
public class InfoSystemDaoImpl extends GenericDaoImpl<InfoSystem, Long> implements InfoSystemDao {

	/**
	 * @param persistentClass
	 */
	public InfoSystemDaoImpl() {
		super(InfoSystem.class);

	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.asset.dao.InfoSystemDao#findPageByGroup(secfox.soc.melon.asset.query.InfoSystemPageQuery)
	 */
	@Override
	public Pagination<Map<String, Object>> findPageByGroup(
			InfoSystemPageQuery pageQuery) {
		String sql = "select distinct info.pk as id,info.a_name,info.a_code,info.charger_name,info.organ_name,info.us_ing "+
					       " from T_ASSET_INFO info,T_ASSET_CONN_GDI gdi "+
					       " where RELATION_TYPE = 1 and gdi.asset_id = info.pk ";
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
			maps.put("chargerName", resultList.get(i)[3]);
			maps.put("organName", resultList.get(i)[4]);
			String usingValue = DictionaryUtils.filterValue("usingState",resultList.get(i)[5]);
			maps.put("using",usingValue );
			values.add(maps);
		}
		
		
		Pagination<Map<String, Object>> pa = new Pagination<Map<String, Object>>();
		pa.setCurrPage(pageQuery.getCurrPage());
		pa.setPageSize(pageQuery.getPageSize());
		pa.setResults(values);
		pa.setCount(getResultCount(pageQuery));
		return pa;
	}
	
	/**
	 * 获取总数
	 * @param pageQuery
	 * @return
	 */
	private int getResultCount(InfoSystemPageQuery pageQuery) {
		String sql = "SELECT count(distinct info.pk) "+
					" from T_ASSET_INFO info,T_ASSET_CONN_GDI gdi "+
					" where RELATION_TYPE = 1 and gdi.asset_id = info.pk ";
		StringBuffer sqlBuffer = new StringBuffer(sql);
		
		sqlBuffer.append(buildWhere(pageQuery));
		Query createQuery = entityManager.createNativeQuery(sqlBuffer.toString());
		return Integer.parseInt(createQuery.getSingleResult().toString());
	}
	
	/**
	 * yb 拼接where条件
	 * @param pageQuery
	 * @return where语句
	 */
	private StringBuffer buildWhere(InfoSystemPageQuery pageQuery){
		StringBuffer sqlBuffer = new StringBuffer();
		//当前用户组织id
//		sqlBuffer.append(" and info.organ_path like '"+QueryTemplate.buildAllLike(SecurityContextUtils.getCurrentAccount().getCompanyId().toString())+"'");
		//安全域路径
		if(StringUtils.isNotBlank(pageQuery.getGroupPath()) && !"null".equals(pageQuery.getGroupPath())){
			sqlBuffer.append(" and ( GDI.GROUP_PATH like '"+QueryTemplate.buildAllLike(","+pageQuery.getGroupPath())+"'");
			sqlBuffer.append(" or GDI.GROUP_PATH like '"+QueryTemplate.buildRightLike(pageQuery.getGroupPath().toString())+"')");
		}
		InfoSystem searchForm=pageQuery.getSearchForm();
		//名称
		if(StringUtils.isNotBlank(searchForm.getName())){
			sqlBuffer.append(" and info.a_name like '"+QueryTemplate.buildAllLike(searchForm.getName())+"'");
		}
		return sqlBuffer;
	}
	
	/**
	 * yb 拼接order by
	 * @param pageQuery
	 * @return order by语句
	 */
	private StringBuffer buildOrderBy(InfoSystemPageQuery pageQuery){
		String column=pageQuery.getSortColumn();
		return new StringBuffer(" order by "+column+" "+pageQuery.getOrder());
	}
	
	public List<InfoSystem> findInfoSystem(String sql) {
		Query query = entityManager.createNativeQuery(sql);
		@SuppressWarnings("unchecked")
		List<Object[]> resultList = query.getResultList();
		List<InfoSystem> lists = new ArrayList<InfoSystem>();
		for (int i = 0; i < resultList.size(); i++) {
			InfoSystem is = new InfoSystem();
			is.setId(Long.parseLong(resultList.get(i)[0].toString()));
			is.setName(resultList.get(i)[1].toString() + "(" + resultList.get(i)[3].toString() +")");
			is.setParentId(Long.parseLong(resultList.get(i)[2].toString()));
			lists.add(is);
		}
		return lists;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.asset.dao.InfoSystemDao#findByTypePath(java.lang.String)
	 */
	@Override
	public List<InfoSystem> findByTypePath(String typePath) {
		Long companyId = SecurityContextUtils.getCurrentAccount().getCompanyId();
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"infoSystem.findByTypePath");
		qt.addParameter("path", QueryTemplate.buildAllLike(","+typePath));
		qt.addParameter("rightPath", QueryTemplate.buildRightLike(typePath));
		qt.addParameter("organPath", QueryTemplate.buildAllLike(","+companyId+","));
		qt.addParameter("organId", companyId);
		return findDomains(qt);
	}
}
