/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.query;

import secfox.soc.melon.asset.domain.InfoSystem;
import secfox.soc.melon.base.PageQuery;


/**
 * @since @2014-9-25,@上午10:28:46
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
public class InfoSystemPageQuery  extends PageQuery<InfoSystem> {


	private static final long serialVersionUID = 5221353518996092434L;
	
	private String groupPath;
	
	

	/**
	 * @return the groupPath
	 */
	public String getGroupPath() {
		return groupPath;
	}

	/**
	 * @param groupPath the groupPath to set
	 */
	public void setGroupPath(String groupPath) {
		this.groupPath = groupPath;
	}

	@Override
	protected InfoSystem initSearchForm() {
		return new InfoSystem();
	}
}


