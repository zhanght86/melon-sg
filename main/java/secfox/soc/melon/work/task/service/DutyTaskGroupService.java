/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.work.task.service;

import java.util.List;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.GenericService;
import secfox.soc.melon.work.task.domain.DutyTaskGroup;
import secfox.soc.melon.work.task.query.DutyTaskGroupPageQuery;

/**
 * 
 * @author  <a href="mailto:gaobg@legendsec.com">高保国</a>
 * @since   2014-11-12
 * @version  1.0
 */
public interface DutyTaskGroupService   extends GenericService<DutyTaskGroup, Long>{
	Pagination<DutyTaskGroup> findPage(DutyTaskGroupPageQuery pageQuery); 
	List<DutyTaskGroup> findByRoleId(Long roleId);
}
