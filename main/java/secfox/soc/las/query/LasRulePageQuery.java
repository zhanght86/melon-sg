/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.las.query;

import secfox.soc.las.domain.LasRule;
import secfox.soc.melon.base.PageQuery;

/**
 * @since 2015-2-5,上午9:41:24
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public class LasRulePageQuery extends PageQuery<LasRule> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8242986058042265124L;

	/* (non-Javadoc)
	 * @see secfox.soc.melon.base.PageQuery#initSearchForm()
	 */
	@Override
	protected LasRule initSearchForm() {
		return new LasRule();
	}

}
