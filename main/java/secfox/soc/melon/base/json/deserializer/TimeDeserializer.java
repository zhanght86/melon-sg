/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base.json.deserializer;

import java.text.DateFormat;

import secfox.soc.melon.base.DateTimeType;


/**
 * @since 1.0 2014年8月26日,下午2:59:58
 * 时间(HH:mm)JSON反串行化器
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class TimeDeserializer extends DateTimeDeserializer {

	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.oxm.json.DateTimeDeserializer#getDateFormatter()
	 */
	@Override
	protected DateFormat getDateFormatter() {
		return DateTimeType.TIME.getFormatter();
	}

}
