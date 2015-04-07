package secfox.soc.melon.rule.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.SortOrder;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.PaginationBuilder;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.rule.dao.EplValueDao;
import secfox.soc.melon.rule.domain.EplValue;
import secfox.soc.melon.rule.query.EplValuePageQuery;
import secfox.soc.melon.rule.service.EplValueService;

@Service("eplValueService")
public class EplValueServiceImpl extends GenericServiceImpl<EplValue, Long> implements
		EplValueService {

	private EplValueDao dao;
	
	@Inject
	public EplValueServiceImpl(EplValueDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	protected GenericDao<EplValue, Long> getDao() {
		return dao;
	}

	@Override
	public EplValue findByName(String name) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "eplvalue.findByname");
		qt.addParameter("name", name);
		return findFirstDomain(qt);
	}

	@Override
	public List<EplValue> findTemplate() {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select value from EplValue value");
		return findDomains(qt);
	}

	@Override
	public Pagination<EplValue> findPage(EplValuePageQuery pageQuery) {
		return findDomainPage(QueryType.JQL, pageQuery, new PaginationBuilder<EplValue, EplValuePageQuery>() {

			@Override
			public void buildSelect(QueryTemplate qt) {
				qt.append("select value from EplValue value");
			}
			
			@Override
			public void buildWhere(EplValue s, QueryTemplate qt) {
				//按用户名查询
				String name = s.getName();
				if(StringUtils.isNotBlank(name)) {
					qt.append(" and value.name like :name ");
					qt.addParameter("name", QueryTemplate.buildAllLike(StringUtils.trim(name)));
				}
			}
			
			@Override
			public void buildBys(String column, SortOrder order, QueryTemplate qt) {
				//默认按序数进行排序
				if(StringUtils.isBlank(column) || StringUtils.equals(column, "id")) {
					column = "id";
					order = SortOrder.asc;
				}
				qt.append(QueryTemplate.buildOrderBy("value", column, order));
			}

			@Override
			public void buildCount(QueryTemplate qt) {
				qt.append("select count(value) from EplValue value");
			}
			
		});
	}

	@Override
	@Transactional
	public void changeState(Long id) {
		EplValue value = find(id);
		value.setEnable(!value.isEnable());
		merge(value);
	}
	
}
