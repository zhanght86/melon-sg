/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.util;

import java.io.IOException;

import secfox.soc.melon.system.FieldType;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

 /**
 * @since 1.0 2013-3-23,下午6:17:48
 * 传入数值型(0,1,2)
 * @author  <a href="mailto:gaoqi@legendsec.com">高岐</a>
 * @version  1.0 
 */
public class FieldTypeDeserializer extends JsonDeserializer<FieldType> {

	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext)
	 */
	@Override
	public FieldType deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
		String value = jp.getText();
		int order;
		//传入字符串 转换为int值
		try {
			order = Integer.parseInt(value);
		} catch(NumberFormatException ex) {
			order = 0;
		}
		//与枚举中值进行比较
		for (FieldType type : FieldType.values() ) {
			if(type.ordinal() == order){
				return type;
			}
		}
		return null;
	}

}
