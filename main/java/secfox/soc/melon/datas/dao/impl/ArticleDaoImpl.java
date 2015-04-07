package secfox.soc.melon.datas.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.datas.dao.ArticleDao;
import secfox.soc.melon.datas.domain.Article;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

@Repository
public class ArticleDaoImpl extends GenericDaoImpl<Article,Long>
	implements ArticleDao{

	public ArticleDaoImpl() {
		super(Article.class);
	}

}
