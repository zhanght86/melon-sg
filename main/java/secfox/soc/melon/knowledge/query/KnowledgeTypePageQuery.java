/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.knowledge.query;

import secfox.soc.melon.base.PageQuery;
import secfox.soc.melon.knowledge.domain.KnowledgeType;

/**
 * @since @2014-10-28,@下午3:10:22
 * @author <a href="mailto:chengzhan@legendsec.com>程展</a>
 * @version 1.0
 */
public class KnowledgeTypePageQuery extends PageQuery<KnowledgeType>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected KnowledgeType initSearchForm() {
		return new KnowledgeType();
	}

}

