package secfox.soc.melon.base.service;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.domain.FlowLayout;
import secfox.soc.melon.base.query.FlowLayoutPageQuery;
import secfox.soc.melon.persistence.GenericService;

/**
 * 
 * @author 熊超
 * 2014-10-28
 * @version 1.0
 */
public interface FlowLayoutService extends GenericService<FlowLayout, Long> {
	
	/**
	 * 分页
	 * @param pageQuery
	 * @return
	 */
	Pagination<FlowLayout> findPages(FlowLayoutPageQuery pageQuery);
	
}
