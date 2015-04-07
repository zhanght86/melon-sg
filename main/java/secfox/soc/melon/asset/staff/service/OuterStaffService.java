/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.asset.staff.service;

import java.util.List;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.GenericService;
import secfox.soc.melon.asset.staff.domain.OuterStaff;
import secfox.soc.melon.asset.staff.query.OuterStaffPageQuery;

/**
 * @since 2014-11-21,下午12:34:54
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public interface OuterStaffService extends GenericService<OuterStaff, Long> {
	
	/**
	 * 分页查询
	 * @param pageQuery
	 * @return
	 */
	Pagination<OuterStaff> findPagination(OuterStaffPageQuery pageQuery);
	
	/**
	 * 获得内部人员的总数
	 * @param organId
	 * @return
	 */
	int getCount(Long organId);
	
	/**
	 * 统计外部人员数据
	 * @param organId
	 * @return
	 */
	List<Object[]> outerCount(Long organId);
	
	List<OuterStaff> findAll();

}
