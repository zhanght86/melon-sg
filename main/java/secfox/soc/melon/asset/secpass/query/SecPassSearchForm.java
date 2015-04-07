/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.asset.secpass.query;

import secfox.soc.melon.asset.secpass.domain.SecPass;

/**
 * @since 2014-11-17,下午4:10:44
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public class SecPassSearchForm extends SecPass {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long userId;
	
	private boolean dueQuery;

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the dueQuery
	 */
	public boolean isDueQuery() {
		return dueQuery;
	}

	/**
	 * @param dueQuery the dueQuery to set
	 */
	public void setDueQuery(boolean dueQuery) {
		this.dueQuery = dueQuery;
	}
	
	

}
