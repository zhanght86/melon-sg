/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.work.task.service;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.GenericService;
import secfox.soc.melon.work.task.domain.DutyTaskItem;
import secfox.soc.melon.work.task.query.DutyTaskItemPageQuery;

/**
 * 
 * @author  <a href="mailto:gaobg@legendsec.com">高保国</a>
 * @since   2014-11-11
 * @version  1.0
 */
public interface DutyTaskItemService  extends GenericService<DutyTaskItem, Long>{

	Pagination<DutyTaskItem> findPage(DutyTaskItemPageQuery pageQuery); 

}
