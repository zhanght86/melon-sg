/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.report.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.SortOrder;
import secfox.soc.melon.report.query.ReportSearchForm;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.PaginationBuilder;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.report.dao.ReportDao;
import secfox.soc.melon.report.domain.Report;
import secfox.soc.melon.report.domain.ReportFile;
import secfox.soc.melon.report.query.ReportPageQuery;
import secfox.soc.melon.report.service.ReportFileService;
import secfox.soc.melon.report.service.ReportService;

/**
 * @since 2015-3-10,上午10:39:26
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Service
public class ReportServiceImpl extends GenericServiceImpl<Report, Long> implements
		ReportService {

	private ReportDao dao;
	private ReportFileService fileService;
	
	@Inject
	public ReportServiceImpl(ReportDao dao, ReportFileService fileService) {
		super();
		this.dao = dao;
		this.fileService = fileService;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<Report, Long> getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.report.service.ReportService#countByType()
	 */
	@Override
	public List<Object[]> countByType() {
		//默认值
		Object[] param1 = {1, 0};
		Object[] param2 = {2, 0};
		Object[] param3 = {3, 0};
		List<Object[]> result = Lists.newArrayList(param1, param2, param3);
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select rpt.type, count(rpt) from Report rpt, ReportFile file where rpt.businessId = file.businessId group by rpt.type order by rpt.type");
		List<Object[]> counts = find(qt);
		for(Object[] o : counts) {
			result.set(Integer.parseInt(String.valueOf(o[0]))-1, o);
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.report.service.ReportService#findByType(int)
	 */
	@Override
	public List<Report> findByType(int type) {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select new secfox.soc.melon.report.domain.Report(rpt.id, rpt.name, file.name, rpt.type, rpt.category, rpt.createDate) from Report rpt, ReportFile file where rpt.type = :type and rpt.businessId = file.businessId order by rpt.createDate");
		qt.addParameter("type", type);
		qt.setFirstResult(0);
		qt.setMaxResults(8);
		List<Report> result = findDomains(qt);
		/*int size = result.size();
		if(size<9) {
			for(int i=0;i<9-size;i++) {
				result.add(new Report());
			}
		}*/
		return result;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.report.service.ReportService#findByType()
	 */
	@Override
	public Map<String, List<Report>> findByType() {
		Map<String, List<Report>> results = Maps.newHashMap();
		//添加分组
		List<Report> r1 = Lists.newArrayList();
		List<Report> r2 = Lists.newArrayList();
		List<Report> r3 = Lists.newArrayList();
		results.put("sys", r1);
		results.put("log", r2);
		results.put("syn", r3);
		//获取所有报表数据
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select rpt from Report rpt order by rpt.createDate");
		List<Report> reports = findDomains(qt);
		for(Report report : reports) {
			//系统报表
			if(report.getType() == 1) {
				results.get("sys").add(report);
			}
			//日志报表
			else if(report.getType() == 2) {
				results.get("log").add(report);
			}
			//综合报表
			else {
				results.get("syn").add(report);
			}
		}
		return results;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.report.service.ReportService#findPages()
	 */
	@Override
	public Pagination<Report> findPages(ReportPageQuery pageQuery) {
		return findDomainPage(QueryType.JQL, pageQuery, new PaginationBuilder<ReportSearchForm, ReportPageQuery>() {

			@Override
			public void buildSelect(QueryTemplate qt) {
				qt.append("select a from Report a, ReportFile b");
			}

			@Override
			public void buildWhere(ReportSearchForm s, QueryTemplate qt) {
				if(StringUtils.isNotBlank(s.getName())){
					qt.append(" and a.name like :name");
					qt.addParameter("name", QueryTemplate.buildAllLike(StringUtils.strip(s.getName())));
				}
				qt.append(" and a.type = :type");
				qt.append(" and a.businessId = b.businessId");
				qt.addParameter("type", s.getType());
			}

			@Override
			public void buildBys(String column, SortOrder order,
					QueryTemplate qt) {
				if(StringUtils.isBlank(column)) {
					column = "id";
				}
				qt.append(QueryTemplate.buildOrderBy("a", column, order));
			}

			@Override
			public void buildCount(QueryTemplate qt) {
				qt.append("select count(a.id) from Report a, ReportFile b ");
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.report.service.ReportService#getFileName(java.lang.Long)
	 */
	@Override
	public String getFileName(Long id) {
		Report report = getReference(id);
		ReportFile file = fileService.findByBusinessId(report.getBusinessId());
		return file.getName();
	}

}
