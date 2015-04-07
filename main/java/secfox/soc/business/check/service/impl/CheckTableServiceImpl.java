package secfox.soc.business.check.service.impl;
/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import secfox.soc.business.check.dao.CheckTableDao;
import secfox.soc.business.check.domain.CheckTable;
import secfox.soc.business.check.query.CheckTablePageQuery;
import secfox.soc.business.check.service.CheckTableService;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.SortOrder;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.PaginationBuilder;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;

/**
*
* @since 1.0 2014年10月14日
* @author <a href="mailto:fengxy@legendsec.com">冯夏彦</a>
* @version 1.0
*/
@Service
public class CheckTableServiceImpl extends GenericServiceImpl<CheckTable, Long>
		implements CheckTableService {

	private CheckTableDao checkTableDao;

	@Inject
	public CheckTableServiceImpl(CheckTableDao checkTableDao) {
		super();
		this.checkTableDao = checkTableDao;
	}

	@Override
	protected GenericDao<CheckTable, Long> getDao() {
		return checkTableDao;
	}

	@Override
	public Pagination<CheckTable> findPages(CheckTablePageQuery pageQuery) {

		return findDomainPage(QueryType.JQL, pageQuery,
				new PaginationBuilder<CheckTable, CheckTablePageQuery>() {

					@Override
					public void buildSelect(QueryTemplate qt) {
						qt.append("select a from CheckTable a ");
					}

					@Override
					public void buildWhere(CheckTable s,QueryTemplate qt) {

						// 标题
						if (StringUtils.isNotEmpty(s.getTitle())) {
							qt.append(" and a.title like :title ");
							qt.addParameter("title",QueryTemplate.buildAllLike(StringUtils.strip(s.getTitle())));
						}

						// 颁发单位
						if (StringUtils.isNotEmpty(s.getIssueOrgan())) {
							qt.append(" and a.issueOrgan like :issueOrgan ");
							qt.addParameter("issueOrgan",
									QueryTemplate.buildAllLike(StringUtils.strip(s.getIssueOrgan())));
						}

						// 颁发开始时间
						if (s.getStartTime() != null) {
							qt.append(" and a.issueTime >= :startTime");
							qt.addParameter("startTime",s.getStartTime());
						}
						
						// 颁发结束时间
						if(s.getEndTime() != null){
							qt.append(" and a.issueTime <= :endTime");
							qt.addParameter("endTime", s.getEndTime());
						}
					}

					@Override
					public void buildBys(String column, SortOrder order,
							QueryTemplate qt) {
						if (StringUtils.isBlank(column)) {
							column = "id";
						}
						qt.append(QueryTemplate.buildOrderBy("a", column, order));
								
					}

					@Override
					public void buildCount(QueryTemplate qt) {
						qt.append("select count(a.id) from CheckTable a ");
					}
				});
			
	}
	
}
