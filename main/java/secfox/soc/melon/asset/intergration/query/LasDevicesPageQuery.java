package secfox.soc.melon.asset.intergration.query;

import secfox.soc.melon.asset.intergration.domain.LasDevices;
import secfox.soc.melon.base.PageQuery;

public class LasDevicesPageQuery extends PageQuery<LasDevices> {

	private static final long serialVersionUID = 1L;

	@Override
	protected LasDevices initSearchForm() {
		return new LasDevices();
	}

}
