/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.report.query;

import secfox.soc.melon.base.PageQuery;
import secfox.soc.melon.report.query.ReportSearchForm;

/**
 * @since 2015-3-17,下午5:21:40
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public class ReportPageQuery extends PageQuery<ReportSearchForm> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4790967871464004642L;

	/* (non-Javadoc)
	 * @see secfox.soc.melon.base.PageQuery#initSearchForm()
	 */
	@Override
	protected ReportSearchForm initSearchForm() {
		return new ReportSearchForm();
	}

}
