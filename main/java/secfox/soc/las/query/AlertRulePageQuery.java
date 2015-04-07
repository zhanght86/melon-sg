/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.las.query;

import secfox.soc.las.domain.AlertRule;
import secfox.soc.melon.base.PageQuery;

/**
 * @since 2015-1-21,上午10:28:35
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public class AlertRulePageQuery extends PageQuery<AlertRule> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7894412590646386685L;

	/* (non-Javadoc)
	 * @see secfox.soc.melon.base.PageQuery#initSearchForm()
	 */
	@Override
	protected AlertRule initSearchForm() {
		// TODO Auto-generated method stub
		return new AlertRule();
	}

}
