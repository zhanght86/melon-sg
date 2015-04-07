/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.work.task.service.impl;

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
import secfox.soc.melon.work.task.dao.DutyTaskItemDao;
import secfox.soc.melon.work.task.domain.DutyTaskItem;
import secfox.soc.melon.work.task.query.DutyTaskItemPageQuery;
import secfox.soc.melon.work.task.service.DutyTaskItemService;

/**
 * 
 * @author  <a href="mailto:gaobg@legendsec.com">高保国</a>
 * @since   2014-11-11
 * @version  1.0
 */
@Service
public class DutyTaskItemServiceImpl extends GenericServiceImpl<DutyTaskItem, Long> implements DutyTaskItemService {

	private DutyTaskItemDao dutyTaskItemDao;
	
	@Inject
	public DutyTaskItemServiceImpl(DutyTaskItemDao dutyTaskItemDao){
		super();
		this.dutyTaskItemDao = dutyTaskItemDao;
	}

	@Override
	protected GenericDao<DutyTaskItem, Long> getDao() {
		return dutyTaskItemDao;
	}
	
	@Override
	public Pagination<DutyTaskItem> findPage(DutyTaskItemPageQuery pageQuery) {

		return findDomainPage(QueryType.JQL, pageQuery, new PaginationBuilder<DutyTaskItem, DutyTaskItemPageQuery>() {

			@Override
			public void buildSelect(QueryTemplate qt) {
				qt.append("select dutyTaskItem from DutyTaskItem dutyTaskItem");
			}
			
			@Override
			public void buildWhere(DutyTaskItem s, QueryTemplate qt) {
				String name = s.getName();
				if(StringUtils.isNotBlank(name)) {
					qt.append(" and dutyTaskItem.name like :name ");
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
				qt.append(QueryTemplate.buildOrderBy("dutyTaskItem", column, order));
			}

			@Override
			public void buildCount(QueryTemplate qt) {
				qt.append("select count(dutyTaskItem) from DutyTaskItem dutyTaskItem");
			}
			
		});
	}
}
