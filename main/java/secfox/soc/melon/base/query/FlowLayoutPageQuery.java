package secfox.soc.melon.base.query;

import secfox.soc.melon.base.PageQuery;
import secfox.soc.melon.base.domain.FlowLayout;

/**
 * 
 * @author 熊超
 * 2014-10-29
 * @version 1.0
 */
public class FlowLayoutPageQuery extends PageQuery<FlowLayout> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4909007345155041777L;

	@Override
	protected FlowLayout initSearchForm() {
		return new FlowLayout();
	}
	
}
