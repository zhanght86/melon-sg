/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base.json.serializer;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @since 1.0 2014年8月26日,下午2:42:36
 * 日期转JSON的串行化工具类
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public abstract class DateTimeSerializer extends JsonSerializer<Date>{
	
	/**
	 * 获取日期格式化器
	 * @return
	 */
	protected abstract DateFormat getDateFormatter();
	
	/* (non-Javadoc)
	 * @see org.codehaus.jackson.map.JsonSerializer#serialize(java.lang.Object, org.codehaus.jackson.JsonGenerator, org.codehaus.jackson.map.SerializerProvider)
	 */
	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException,JsonProcessingException {
	    String resultStr = "";
		if(value != null) {
			resultStr = getDateFormatter().format(value);  
	    }
        jgen.writeString(resultStr);  
	}
	
}
