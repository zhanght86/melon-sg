package secfox.soc.melon.indicator.query;

import secfox.soc.melon.base.PageQuery;
import secfox.soc.melon.indicator.domain.IndicatorDefine;

/**
 * @since 1.0 2014-10-15,下午3:32:50
 * 
 * @author  <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0 
 */
public class IndicatorDefinePageQuery extends PageQuery<IndicatorDefine> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -643564374334381063L;

	/* (non-Javadoc)
	 * @see secfox.soc.melon.base.PageQuery#initSearchForm()
	 */
	@Override
	protected IndicatorDefine initSearchForm() {
		return new IndicatorDefine();
	}

	
}

