/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.asset.staff.query;

import secfox.soc.melon.base.PageQuery;

/**
 * @since 2014-11-21,下午1:43:17
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public class OuterStaffPageQuery extends PageQuery<OuterStaffSearchForm> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see secfox.soc.melon.base.PageQuery#initSearchForm()
	 */
	@Override
	protected OuterStaffSearchForm initSearchForm() {
		return new OuterStaffSearchForm();
	}

}
