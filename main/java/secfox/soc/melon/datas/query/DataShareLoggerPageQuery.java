/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
/**
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.datas.query;

import secfox.soc.melon.base.PageQuery;

/**
 *
 * @since 1.0 2014-11-10
 * @author <a href="mailto:huangxm@legendsec.com">黄晓梅</a>
 * @version 1.0
 */
public class DataShareLoggerPageQuery extends PageQuery<DataShareLoggerSearch> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/* (non-Javadoc)
	 * @see secfox.soc.melon.base.PageQuery#initSearchForm()
	 */
	@Override
	protected DataShareLoggerSearch initSearchForm() {
		return new DataShareLoggerSearch();
	}

}
