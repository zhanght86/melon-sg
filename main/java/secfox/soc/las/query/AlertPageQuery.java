package secfox.soc.las.query;

import secfox.soc.las.domain.Alert;
import secfox.soc.melon.base.PageQuery;

public class AlertPageQuery extends PageQuery<Alert> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected Alert initSearchForm() {
		return new Alert();
	}

}
