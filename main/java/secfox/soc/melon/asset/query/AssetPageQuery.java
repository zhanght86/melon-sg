/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.query;
import secfox.soc.melon.base.PageQuery;
/**
 * @since @2014-12-9,@下午9:21:38
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
public class AssetPageQuery extends PageQuery<DeviceSearch>{


	private static final long serialVersionUID = 1L;

	@Override
	protected DeviceSearch initSearchForm() {
		return new DeviceSearch();
	}
	
	
	

}


