package secfox.soc.business.message.query;

import secfox.soc.business.message.domain.SysMail;
import secfox.soc.melon.base.PageQuery;

/**
 * @since 1.0 2014-10-20,下午3:18:03
 * 
 * @author  <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0 
 */
public class SysMailPageQuery extends PageQuery<SysMail> {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1787325312803568552L;

	/* (non-Javadoc)
	 * @see secfox.soc.melon.base.PageQuery#initSearchForm()
	 */
	@Override
	protected SysMail initSearchForm() {
		return new SysMail();
	}

	
}

