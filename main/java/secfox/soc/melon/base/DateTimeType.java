/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * @since 1.0 2014年8月26日,下午2:31:00
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public enum DateTimeType {
	
	/**
	 * 长日期时间格式，精确到秒
	 */
	LONG_DATE(BaseConstants.LONG_DATE_FORMAT),
	
	/**
	 * 短日期时间格式，精确到天
	 */
	SHORT_DATE(BaseConstants.SHORT_DATE_FORMAT),
	
	/**
	 * 时间格式，精确到秒
	 */
	TIME(BaseConstants.TIME_FORMAT);
	
	//格式化字符串
	private DateFormat formatter;
	
	//私有构造函数
	private DateTimeType(String formatStr) {
		this.formatter = new SimpleDateFormat(formatStr);
	}
	
	/**
	 * 获取对应时间格式化器
	 * @return 对应时间格式化器
	 */
	public DateFormat getFormatter() {
		return this.formatter;
	}
	
}
