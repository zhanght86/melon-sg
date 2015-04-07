/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base.json.deserializer;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * @since 1.0 2014年8月26日,下午2:49:16
 * JSON转日期的反串行化工具类
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public abstract class DateTimeDeserializer extends JsonDeserializer<Date>{
	
	/**
	 * 获取日期格式化器
	 * @return
	 */
	protected abstract DateFormat getDateFormatter();
	
	private final static Logger logger = LoggerFactory.getLogger(DateTimeDeserializer.class);
	
	/* (non-Javadoc)
	 * @see org.codehaus.jackson.map.JsonDeserializer#deserialize(org.codehaus.jackson.JsonParser, org.codehaus.jackson.map.DeserializationContext)
	 */
	@Override
	public Date deserialize(JsonParser jp, DeserializationContext ctxt)	throws IOException, JsonProcessingException {
			String date = jp.getText();
		if(StringUtils.isNotBlank(date)) {
			try {
				return getDateFormatter().parse(date);
			} catch (ParseException e) {
				logger.warn("JSON PARSE DATE ERROR : {}", date);
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}
}
