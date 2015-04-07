package secfox.soc.melon.datas.service.impl;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.SortOrder;
import secfox.soc.melon.datas.dao.ArticleReceiverDao;
import secfox.soc.melon.datas.domain.ArticleReceiver;
import secfox.soc.melon.datas.query.ArticleReceiverPageQuery;
import secfox.soc.melon.datas.service.ArticleReceiverService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.PaginationBuilder;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;

/**
 * 待办
 * @author 11
 *
 */
@Service
public class ArticleReceiverServiceImpl extends GenericServiceImpl<ArticleReceiver,Long>
	implements ArticleReceiverService{

	private ArticleReceiverDao dao;

	@Inject
	public ArticleReceiverServiceImpl(ArticleReceiverDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	protected GenericDao<ArticleReceiver, Long> getDao() {
		return dao;
	}

	@Override
	public Pagination<ArticleReceiver> findPages(ArticleReceiverPageQuery query) {
		return findDomainPage(QueryType.JQL, query, new PaginationBuilder<ArticleReceiver, ArticleReceiverPageQuery>() {

			@Override
			public void buildSelect(QueryTemplate qt) {
				qt.append(" select ar from ArticleReceiver ar ");
			}

			@Override
			public void buildWhere(ArticleReceiver s, QueryTemplate qt) {
				Long organId = s.getReceiver().getOrganId();
				if(organId!=null) {
					qt.append(" and ar.receiver.organId = :organId ");
					qt.addParameter("organId", organId);
				}
			}

			@Override
			public void buildBys(String column, SortOrder order,
					QueryTemplate qt) {
				if(StringUtils.isBlank(column)) {
					column = "article.createTime";
				}
				qt.append(QueryTemplate.buildOrderBy("ar", column, order));
			}

			@Override
			public void buildCount(QueryTemplate qt) {
				qt.append(" select count(ar) from ArticleReceiver ar ");
			}
		});
	}
	
	
}
