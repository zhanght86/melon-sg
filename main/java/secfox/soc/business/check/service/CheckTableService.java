/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.business.check.service;

import secfox.soc.business.check.domain.CheckTable;
import secfox.soc.business.check.query.CheckTablePageQuery;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.GenericService;

/**
*
* @since 1.0 2014年10月14日
* @author <a href="mailto:fengxy@legendsec.com">冯夏彦</a>
* @version 1.0
*/
public interface CheckTableService extends GenericService<CheckTable, Long> {
	/**
	 * 安全检查表格注册的分页查询信息
	 * @param pageQuery
	 * @return
	 */
	Pagination<CheckTable> findPages(CheckTablePageQuery pageQuery);

}
