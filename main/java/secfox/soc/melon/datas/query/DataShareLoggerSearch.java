/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.datas.query;

import secfox.soc.melon.datas.domain.DataShareLogger;

/**
 *
 * @since 1.0 2014年11月14日下午3:29:48
 * @author <a href="mailto:huangxm@legendsec.com">黄晓梅</a>
 * @version 1.0
 */

public class DataShareLoggerSearch extends DataShareLogger {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long organId;
	
	private int type;

	/**
	 * @return the organId
	 */
	public Long getOrganId() {
		return organId;
	}

	/**
	 * @param organId the organId to set
	 */
	public void setOrganId(Long organId) {
		this.organId = organId;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}
	
	
}

