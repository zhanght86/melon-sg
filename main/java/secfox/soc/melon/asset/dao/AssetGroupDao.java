/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.dao;

import java.util.List;
import java.util.Map;

import secfox.soc.melon.asset.domain.AssetGroup;
import secfox.soc.melon.asset.query.AssetGroupPageQuery;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.GenericDao;

/**
 * @since @2014-10-10,@下午4:49:52
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
public interface AssetGroupDao  extends GenericDao<AssetGroup, Long>{
	/**
	 * yb 根据类型查询安全域
	 * @param typeId
	 * @return
	 */
	List<AssetGroup> findByTypePath(String typePath);
	
	/**
	 * 获取安全域
	 * @param name安全域名称
	 * @return 安全域
	 */
	AssetGroup findByName(String name);
	
	/**
	 * 获取安全域
	 * @param names 名称
	 * @return List<AssetGroup>
	 */
	List<AssetGroup> findByName(String[] names);
	
	
	/**
	 * 分页
	 * @param pageQuery
	 * @return
	 */
	Pagination<Map<String, Object>> findPageByInfo(AssetGroupPageQuery pageQuery);
}


