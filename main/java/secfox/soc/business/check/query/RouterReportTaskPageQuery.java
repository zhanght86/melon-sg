package secfox.soc.business.check.query;

import secfox.soc.melon.base.PageQuery;

/**
 * @since 1.0 2014年5月16日
 * @author <a href="mailto:fengxy@legendsec.com">冯夏彦</a>
 * @version  1.0
 */
public class RouterReportTaskPageQuery extends
		PageQuery<RouterReportTaskSearchForm> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6983776461100034632L;

	@Override
	protected RouterReportTaskSearchForm initSearchForm() {
		RouterReportTaskSearchForm form = new RouterReportTaskSearchForm();
		return form;
	}

}
