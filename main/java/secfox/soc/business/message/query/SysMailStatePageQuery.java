package secfox.soc.business.message.query;

import secfox.soc.business.message.domain.SysMailState;
import secfox.soc.melon.base.PageQuery;

/**
 * @since 1.0 2014-10-20,下午3:18:03
 * 
 * @author  <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0 
 */
public class SysMailStatePageQuery extends PageQuery<SysMailStateSearch> {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = -6547715131804048042L;

	/* (non-Javadoc)
	 * @see secfox.soc.melon.base.PageQuery#initSearchForm()
	 */
	@Override
	protected SysMailStateSearch initSearchForm() {
		return new SysMailStateSearch();
	}

	
}

