package secfox.soc.melon.datas.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.datas.dao.ArticleReceiverDao;
import secfox.soc.melon.datas.domain.ArticleReceiver;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

@Repository
public class ArticleReceiverDaoImpl extends GenericDaoImpl<ArticleReceiver,Long>
	implements ArticleReceiverDao{

	public ArticleReceiverDaoImpl() {
		super(ArticleReceiver.class);
	}

}
