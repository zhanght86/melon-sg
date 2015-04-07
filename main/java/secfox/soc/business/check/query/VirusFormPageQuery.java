/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.business.check.query;

import secfox.soc.melon.base.PageQuery;


/**
 * 信息系统情况
 * @since @2014-4-23,@下午4:40:06
 * @author <a href="mailto:liubing@legendsec.com>刘冰</a>
 * @version 1.0
 */
public class VirusFormPageQuery extends PageQuery<VirusFormSearchForm> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2180595565584892344L;
	
	@Override
	protected VirusFormSearchForm initSearchForm() {
		VirusFormSearchForm vf=new VirusFormSearchForm();
		return vf;
	}


}
