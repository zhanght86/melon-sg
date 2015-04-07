package secfox.soc.melon.datas.query;

import secfox.soc.melon.base.PageQuery;

public class ArticlePageQuery extends PageQuery<ArticleSearchForm>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected ArticleSearchForm initSearchForm() {
		return new ArticleSearchForm();
	}

}
