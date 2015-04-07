/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.util;

import java.io.IOException;

import secfox.soc.melon.system.JoinType;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * @since 1.0 2013-3-23,下午5:50:07
 *
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class JoinTypeJsonDeserializer extends JsonDeserializer<JoinType> {

	/**
	 * 接收数字 
	 * (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext)
	 */
	@Override
	public JoinType deserialize(JsonParser jp, DeserializationContext context)	throws IOException, JsonProcessingException {
		String value = jp.getText();
		int order;
		//
		try {
			order = Integer.parseInt(value);
		} catch(NumberFormatException ex) {
			order = 0;
		}
		//
		for(JoinType type : JoinType.values()) {
			if(order == type.ordinal()) {
				return type;
			}
		}
		return null;
	}

}
