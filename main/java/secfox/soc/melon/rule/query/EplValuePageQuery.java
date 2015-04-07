package secfox.soc.melon.rule.query;

import secfox.soc.melon.base.PageQuery;
import secfox.soc.melon.rule.domain.EplValue;

public class EplValuePageQuery extends PageQuery<EplValue> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected EplValue initSearchForm() {
		return new EplValue();
	}

}
