/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.indicator.domain;

import java.util.Date;

import secfox.soc.melon.base.UserInfo;

/**
 * @since 1.0 2014年10月15日,下午7:45:41
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public interface Indicator {

	/**
	 * 
	 * @return the createTime
	 */
	public abstract Date getCreateTime();

	/**
	 * 
	 * @return the value
	 */
	public abstract Long getValue();

	/**
	 * 
	 * @return the creator
	 */
	public abstract UserInfo getCreator();

}