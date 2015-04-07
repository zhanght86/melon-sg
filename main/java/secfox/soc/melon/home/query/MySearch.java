/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.home.query;

/**
 * @since 1.0 2014年10月31日,下午2:33:36
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class MySearch {
	
	private Long userId;
	
	private Long type;

	/**
	 * 
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * 
	 * @return the type
	 */
	public Long getType() {
		return type;
	}

	/**
	 * 
	 * @param type the type to set
	 */
	public void setType(Long type) {
		this.type = type;
	}
	
}
