/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.work.task.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.SortOrder;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.PaginationBuilder;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.work.task.dao.DutyTaskGroupDao;
import secfox.soc.melon.work.task.domain.DutyTaskGroup;
import secfox.soc.melon.work.task.query.DutyTaskGroupPageQuery;
import secfox.soc.melon.work.task.service.DutyTaskGroupService;

/**
 * 
 * @author  <a href="mailto:gaobg@legendsec.com">高保国</a>
 * @since   2014-11-12
 * @version  1.0
 */
@Service
public class DutyTaskGroupServiceImpl extends GenericServiceImpl<DutyTaskGroup, Long> implements DutyTaskGroupService {

	private DutyTaskGroupDao dutyTaskGroupDao;
	
	@Inject
	public DutyTaskGroupServiceImpl(DutyTaskGroupDao dutyTaskGroupDao){
		super();
		this.dutyTaskGroupDao = dutyTaskGroupDao;
	}
	
	@Override
	public Pagination<DutyTaskGroup> findPage(DutyTaskGroupPageQuery pageQuery) {

		return findDomainPage(QueryType.JQL, pageQuery, new PaginationBuilder<DutyTaskGroup, DutyTaskGroupPageQuery>() {

			@Override
			public void buildSelect(QueryTemplate qt) {
				qt.append("select dutyTaskGroup from DutyTaskGroup dutyTaskGroup");
			}
			
			@Override
			public void buildWhere(DutyTaskGroup s, QueryTemplate qt) {
				String name = s.getName();
				if(StringUtils.isNotBlank(name)) {
					qt.append(" and dutyTaskGroup.name like :name ");
					qt.addParameter("name", QueryTemplate.buildAllLike(StringUtils.trim(name)));
				}
			}
			
			@Override
			public void buildBys(String column, SortOrder order, QueryTemplate qt) {
				//默认按序数进行排序
				if(StringUtils.isBlank(column) || StringUtils.equals(column, "id")) {
					column = "order";
					order = SortOrder.asc;
				}
				qt.append(QueryTemplate.buildOrderBy("dutyTaskGroup", column, order));
			}

			@Override
			public void buildCount(QueryTemplate qt) {
				qt.append("select count(dutyTaskGroup) from DutyTaskGroup dutyTaskGroup");
			}
			
		});
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#getDao()
	 * @author <a href="mailto:gaobg@legendsec.com">高保国</a>
	 */
	@Override
	protected GenericDao<DutyTaskGroup, Long> getDao() {
		return dutyTaskGroupDao;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.work.task.service.DutyTaskGroupService#findByRoleId(java.lang.Long)
	 * @author <a href="mailto:gaobg@legendsec.com">高保国</a>
	 */
	@Override
	public List<DutyTaskGroup> findByRoleId(Long roleId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "taskGroup.findByRoleId");
		qt.addParameter("roleId", roleId);
		List<DutyTaskGroup> groups = findDomains(qt);
		return groups;
	}

}
