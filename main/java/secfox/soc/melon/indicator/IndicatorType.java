/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.indicator;

import org.apache.commons.lang3.StringUtils;

/**
 * @since 1.0 2014年10月15日,下午8:04:22
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public enum IndicatorType {
	
	//0:默认,1:设备对象,2:信息系统,3:单位,4:个人
	DEFAULT,
	

	/*设备对象*/
	DEVICE,
	
	/*信息系统*/
	INFO_SYS,
	
	/*单位,查询与单位相关的指标*/
	ORGAN,
	
	/*单位,查询与个人相关的指标*/
	USER;
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	public static IndicatorType of(String type) {
		return IndicatorType.valueOf(StringUtils.upperCase(type));
	}
}
