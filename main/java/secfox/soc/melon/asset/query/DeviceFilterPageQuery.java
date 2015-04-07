/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.query;

import secfox.soc.melon.asset.domain.DeviceFilter;
import secfox.soc.melon.base.PageQuery;

/**
 *
 * @since 1.0 2014年11月20日下午3:17:44
 * @author <a href="mailto:huangxm@legendsec.com">黄晓梅</a>
 * @version 1.0
 */

public class DeviceFilterPageQuery extends PageQuery<DeviceFilter> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected DeviceFilter initSearchForm() {
		return new DeviceFilter();
	}

}

