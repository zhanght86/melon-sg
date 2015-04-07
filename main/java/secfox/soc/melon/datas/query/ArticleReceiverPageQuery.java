package secfox.soc.melon.datas.query;

import secfox.soc.melon.base.PageQuery;
import secfox.soc.melon.datas.domain.ArticleReceiver;

public class ArticleReceiverPageQuery extends PageQuery<ArticleReceiver>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected ArticleReceiver initSearchForm() {
		return new ArticleReceiver();
	}

}
