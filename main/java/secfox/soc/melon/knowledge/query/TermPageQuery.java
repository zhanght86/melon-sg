/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.knowledge.query;

import secfox.soc.melon.base.PageQuery;

/**
 * @since 1.0 2014年10月29日,下午3:24:03
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class TermPageQuery extends PageQuery<String> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 937148721961927430L;

	/* (non-Javadoc)
	 * @see secfox.soc.melon.base.PageQuery#initSearchForm()
	 */
	@Override
	protected String initSearchForm() {
		return "";
	}

}
