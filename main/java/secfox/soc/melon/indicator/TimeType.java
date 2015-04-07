/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.indicator;

import org.apache.commons.lang3.StringUtils;

/**
 * @since 1.0 2014年10月15日,下午7:58:06
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public enum TimeType {
	
	/**
	 * 默认,查询最新的记录
	 */
	DEFAULT,
	
	/*年*/
	YEAR,
	
	/*半年*/
	HALF_YEAR,
	
	/*季度*/
	QUARTER,
	
	/*月*/
	MONTH,
	
	/*周*/
	WEEK,
	
	/*天*/
	DAY,
	
	/*小时*/
	HOUR;
	
	/**
	 * 
	 * @param timeType
	 * @return
	 */
	public static TimeType of(String timeType) {
		return TimeType.valueOf(StringUtils.upperCase(timeType));
	}
}
