/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.google.common.collect.Lists;

import secfox.soc.melon.asset.dao.AssetGroupDao;
import secfox.soc.melon.asset.domain.AssetGroup;
import secfox.soc.melon.asset.query.AssetGroupPageQuery;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;
import secfox.soc.melon.security.util.SecurityContextUtils;
/**
 * @since @2014-10-10,@下午4:50:22
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
@Repository
public class AssetGroupDaoImpl extends GenericDaoImpl<AssetGroup, Long> implements AssetGroupDao{

	
	public AssetGroupDaoImpl() {
		super(AssetGroup.class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericDaoImpl#remove(java.io.Serializable)
	 */
	@Override
	public void remove(Long pk) {
		AssetGroup assetGroup = find(pk);
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"assetGroup.remove");
		qt.addParameter("path", QueryTemplate.buildLeftLike(assetGroup.getPath()));
		execute(qt);
		
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.dao.AssetGroupDao#findByName(java.lang.String)
	 */
	@Override
	public AssetGroup findByName(String name) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"assetGroup.findByName");
		qt.addParameter("name", name);
		return findFirstDomain(qt);
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.dao.AssetGroupDao#findByName(java.lang.String[])
	 */
	@Override
	public List<AssetGroup> findByName(String[] names) {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select a from AssetGroup a where a.name in (");
		for (int i = 0, length=names.length; i < length; i++) {
			qt.append("'")
			  .append(names[i])
			  .append("'");
			if(i < names.length - 1) {
				qt.append(",");
			}
		}
		qt.append(")");
		return findDomains(qt);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.asset.dao.AssetGroupDao#findPageByInfo(secfox.soc.melon.asset.query.AssetGroupPageQuery)
	 */
	@Override
	public Pagination<Map<String, Object>> findPageByInfo(
			AssetGroupPageQuery pageQuery) {
		String sql = "SELECT gdi.GROUP_ID,g.T_NAME,g.T_CODE,g.ORGAN_NAME,g.NET_TYPE,g.USE_STATE "+
		           " FROM T_ASSET_CONN_GDI GDI,T_ASSET_GROUP g "+
		           " where RELATION_TYPE = 0 and gdi.GROUP_ID = g.pk ";
		StringBuffer sqlBuffer = new StringBuffer(sql);
		
		//信息系统id
		if(pageQuery.getInfoSystemId()!=null){
			sqlBuffer.append(" and gdi.pk= "+pageQuery.getInfoSystemId());
		}
		
		Query query = entityManager.createNativeQuery(sql);
		query.setFirstResult(pageQuery.getFirstResult());
		query.setMaxResults(pageQuery.getMaxResults());
		@SuppressWarnings("unchecked")
		List<Object[]> resultList = query.getResultList();
		List<Map<String, Object>> values = Lists.newArrayList();
		for (int i = 0; i < resultList.size(); i++) {
			Map<String, Object> maps = new HashMap<String, Object>();
			maps.put("groupId", resultList.get(i)[0]);
			maps.put("groupName", resultList.get(i)[1]);
			maps.put("groupCode", resultList.get(i)[2]);
			maps.put("organName", resultList.get(i)[3]);
			maps.put("netType", resultList.get(i)[4]);
			maps.put("useState", resultList.get(i)[5]);
			values.add(maps);
		}
		Pagination<Map<String, Object>> pa = new Pagination<Map<String, Object>>();
		pa.setCurrPage(pageQuery.getCurrPage());
		pa.setPageSize(pageQuery.getPageSize());
		pa.setResults(values);
		pa.setCount(getResultCount(pageQuery));
		return pa;
	}
	
	private int getResultCount(AssetGroupPageQuery pageQuery) {
		String sql = "SELECT count(*) "+
					 " FROM T_ASSET_CONN_GDI GDI,T_ASSET_GROUP g "+
					 " where RELATION_TYPE = 0 and gdi.GROUP_ID = g.pk ";
		if(pageQuery.getInfoSystemId()!=null){
			sql+=" and gdi.pk= "+pageQuery.getInfoSystemId();
		}
		Query createQuery = entityManager.createNativeQuery(sql);
		return Integer.parseInt(createQuery.getSingleResult().toString());
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.asset.dao.AssetGroupDao#findByTypePath(java.lang.String)
	 */
	@Override
	public List<AssetGroup> findByTypePath(String typePath) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"assetGroup.findByTypePath");
		qt.addParameter("path", QueryTemplate.buildAllLike(","+typePath));
		qt.addParameter("rightPath", QueryTemplate.buildRightLike(typePath));
		Long organId=SecurityContextUtils.getCurrentAccount().getCompanyId();
		qt.addParameter("organPath", QueryTemplate.buildAllLike(","+organId+","));
		qt.addParameter("organId", organId);
		return findDomains(qt);
	}
}


