/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.home.query;

import secfox.soc.melon.base.PageQuery;

/**
 * @since 1.0 2014年10月31日,下午2:35:02
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class MyPageQuery extends PageQuery<MySearch> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5128833365360528495L;

	/* (non-Javadoc)
	 * @see secfox.soc.melon.base.PageQuery#initSearchForm()
	 */
	@Override
	protected MySearch initSearchForm() {
		return null;
	}

}
