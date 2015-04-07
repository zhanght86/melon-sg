/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base.json.serializer;

import java.text.DateFormat;

import secfox.soc.melon.base.DateTimeType;

/**
 * @since 1.0 2014年8月26日,下午2:56:47
 * 时间格式化器(HH:mm)
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class TimeSerializer extends DateTimeSerializer {

	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.oxm.json.DateTimeSerializer#getDateFormatter()
	 */
	@Override
	protected DateFormat getDateFormatter() {
		return DateTimeType.TIME.getFormatter();
	}

}
