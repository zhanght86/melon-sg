package secfox.soc.melon.base.service.impl;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.SortOrder;
import secfox.soc.melon.base.dao.FlowLayoutDao;
import secfox.soc.melon.base.domain.FlowLayout;
import secfox.soc.melon.base.query.FlowLayoutPageQuery;
import secfox.soc.melon.base.service.FlowLayoutService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.PaginationBuilder;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;

/**
 * 
 * @author 熊超
 * 2014-10-28
 * @version 1.0
 */
@Service
public class FlowLayoutServiceImpl extends GenericServiceImpl<FlowLayout, Long> implements FlowLayoutService {
	
	private FlowLayoutDao flowLayoutDao;
	
	@Inject
	public FlowLayoutServiceImpl(FlowLayoutDao flowLayoutDao){
		super();
		this.flowLayoutDao = flowLayoutDao;
	}
	
	@Override
	protected GenericDao<FlowLayout, Long> getDao() {
		return flowLayoutDao;
	}

	@Override
	public Pagination<FlowLayout> findPages(FlowLayoutPageQuery pageQuery) {
		return findDomainPage(QueryType.JQL, pageQuery,
				new PaginationBuilder<FlowLayout, FlowLayoutPageQuery>() {

					@Override
					public void buildSelect(QueryTemplate qt) {
						qt.append("select a from FlowLayout a ");
					}

					@Override
					public void buildWhere(FlowLayout s, QueryTemplate qt) {
						// 按名称查询
						if (StringUtils.isNotBlank(s.getName())) {
							qt.append(" and a.name like :name ");
							qt.addParameter("name",QueryTemplate.buildAllLike(StringUtils.strip(s.getName())));
						}

					}

					@Override
					public void buildBys(String column, SortOrder order,
							QueryTemplate qt) {
						if (StringUtils.isBlank(column)) {
							column = "name";
						}
						qt.append(QueryTemplate.buildOrderBy("a", column, order));
					}

					@Override
					public void buildCount(QueryTemplate qt) {
						qt.append("select count(a.id) from FlowLayout a ");
					}
				});
	}

}
