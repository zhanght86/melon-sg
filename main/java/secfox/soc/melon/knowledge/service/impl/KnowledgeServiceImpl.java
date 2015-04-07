/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.knowledge.service.impl;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.SortOrder;
import secfox.soc.melon.knowledge.dao.KnowledgeDao;
import secfox.soc.melon.knowledge.domain.Knowledge;
import secfox.soc.melon.knowledge.query.KnowledgePageQuery;
import secfox.soc.melon.knowledge.query.KnowledgeSearchForm;
import secfox.soc.melon.knowledge.query.TermPageQuery;
import secfox.soc.melon.knowledge.service.KnowledgeService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.PaginationBuilder;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;

/**
 * @since @2014-10-28,@下午2:50:26
 * @author <a href="mailto:chengzhan@legendsec.com>程展</a>
 * @version 1.0
 */
@Service
public class KnowledgeServiceImpl extends GenericServiceImpl<Knowledge,Long> implements KnowledgeService{

	private KnowledgeDao dao;

	@Inject
	public KnowledgeServiceImpl(KnowledgeDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	public Pagination<Knowledge> findPage(KnowledgePageQuery pageQuery) {
		return this.findDomainPage(QueryType.JQL, pageQuery, new PaginationBuilder<KnowledgeSearchForm, KnowledgePageQuery>() {

			@Override
			public void buildSelect(QueryTemplate qt) {
				qt.append("select k from Knowledge k");
			}

			@Override
			public void buildWhere(KnowledgeSearchForm s, QueryTemplate qt) {
				// 标题
				String title = s.getTitle();
				if (StringUtils.isNotBlank(title)) {
					qt.append(" and k.title like :title");
					qt.addParameter("title", QueryTemplate.buildAllLike(title));
				}
				// 告警分类
				Long type = s.getType();
				if (type!=null) {
					qt.append(" and k.type = :type");
					qt.addParameter("type", type);
				}
				//
				String issueOrgan = s.getIssueOrgan();
				if(StringUtils.isNotBlank(issueOrgan)){
					qt.append(" and k.issueOrgan like :organ ");
					qt.addParameter("organ",  QueryTemplate.buildAllLike(issueOrgan));
				}
				//
				String keywords = s.getKeywords();
				if(StringUtils.isNotBlank(keywords)){
					qt.append(" and k.keywords like :keywords ");
					qt.addParameter("keywords", QueryTemplate.buildAllLike(keywords));
				}
			}

			@Override
			public void buildBys(String column, SortOrder order, QueryTemplate qt) {
				if (StringUtils.isBlank(column))
					column = "id";
				qt.append(QueryTemplate.buildOrderBy("k", column, order));
			}

			@Override
			public void buildCount(QueryTemplate qt) {
				qt.append("select count(k.id) from Knowledge k");
			}

		});
	}

	@Override
	protected GenericDao<Knowledge, Long> getDao() {
		return this.dao;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.knowledge.service.KnowledgeService#queryPage(secfox.soc.melon.knowledge.query.TermPageQuery)
	 */
	@Override
	@Transactional(readOnly=true)
	public Pagination<Knowledge> queryPage(TermPageQuery termPageQuery) {
		if(StringUtils.isBlank(termPageQuery.getSearchForm())) {
			return new Pagination<Knowledge>();
		}
		return dao.queryPage(termPageQuery);
	}

	@Override
	@Transactional(readOnly=false)
	public Knowledge count(Knowledge konwledge) {
		dao.refresh(konwledge);
		int count = konwledge.getCount();
		konwledge.setCount(count+1);
		dao.merge(konwledge);
		return konwledge;
	}
	
	
}

