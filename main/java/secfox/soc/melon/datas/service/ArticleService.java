package secfox.soc.melon.datas.service;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.datas.domain.Article;
import secfox.soc.melon.datas.query.ArticlePageQuery;
import secfox.soc.melon.persistence.GenericService;

public interface ArticleService extends GenericService<Article,Long>{

	Pagination<Article> findPages(ArticlePageQuery quey);
}
