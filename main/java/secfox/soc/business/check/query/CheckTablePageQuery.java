/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.business.check.query;

import secfox.soc.business.check.domain.CheckTable;
import secfox.soc.melon.base.PageQuery;

/**
*
* @since 1.0 2014年10月14日
* @author <a href="mailto:fengxy@legendsec.com">冯夏彦</a>
* @version 1.0
*/
public class CheckTablePageQuery extends PageQuery<CheckTable> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5935907937508621458L;

	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.PageQuery#initSearchForm()
	 */
	
	@Override
	protected CheckTable initSearchForm() {
		return new CheckTable();
	}

}
