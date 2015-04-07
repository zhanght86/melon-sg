package secfox.soc.melon.datas.service;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.datas.domain.ArticleReceiver;
import secfox.soc.melon.datas.query.ArticleReceiverPageQuery;
import secfox.soc.melon.persistence.GenericService;

public interface ArticleReceiverService extends GenericService<ArticleReceiver, Long>{
	
	Pagination<ArticleReceiver> findPages(ArticleReceiverPageQuery query);
}
