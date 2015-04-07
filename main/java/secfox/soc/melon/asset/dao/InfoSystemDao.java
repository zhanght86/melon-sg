/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.dao;

import java.util.Map;
import java.util.List;

import secfox.soc.melon.asset.domain.InfoSystem;
import secfox.soc.melon.asset.query.InfoSystemPageQuery;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.GenericDao;

/**
 * 信息系统dao接口
 * @since 2014-9-25
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
public interface InfoSystemDao extends GenericDao<InfoSystem, Long> {
	/**
	 * yb 根据类型查询安全域
	 * @param typeId
	 * @return
	 */
	List<InfoSystem> findByTypePath(String typePath);
	
	/**
	 * yb 信息系统安全域视图分页
	 * @param pageQuery
	 * @return
	 */
	Pagination<Map<String, Object>> findPageByGroup(InfoSystemPageQuery pageQuery);
	
	List<InfoSystem> findInfoSystem(String sql) ;
}
