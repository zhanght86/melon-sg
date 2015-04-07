/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.report.service;

import java.util.List;
import java.util.Map;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.GenericService;
import secfox.soc.melon.report.domain.Report;
import secfox.soc.melon.report.query.ReportPageQuery;

/**
 * @since 2015-3-10,上午10:38:23
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public interface ReportService extends GenericService<Report, Long> {
	
	/**
	 * 按类型统计
	 * @return
	 */
	List<Object[]> countByType();
	
	/**
	 * 根据类型查找
	 * @param type
	 * @return
	 */
	List<Report> findByType(int type);
	
	/**
	 * 查出全部数据，再按类型分组
	 * @return
	 */
	Map<String, List<Report>> findByType();
	
	/**
	 * 分页查询
	 * @param pageQuery
	 * @return
	 */
	Pagination<Report> findPages(ReportPageQuery pageQuery);
	
	/**
	 * 查询报表文件的名字
	 * @param id
	 * @return
	 */
	String getFileName(Long id);
}
