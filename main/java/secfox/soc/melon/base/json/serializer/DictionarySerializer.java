/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base.json.serializer;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;

import secfox.soc.melon.base.util.DictionaryUtils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @since 1.0 2014年10月3日,上午11:08:34
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class DictionarySerializer extends JsonSerializer<Object> {

	private final String key;
	
	/**
	 * @param key
	 */
	public DictionarySerializer(String key) {
		super();
		this.key = key;
	}

	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.JsonSerializer#serialize(java.lang.Object, com.fasterxml.jackson.core.JsonGenerator, com.fasterxml.jackson.databind.SerializerProvider)
	 */
	@Override
	public void serialize(Object value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		String output = StringUtils.EMPTY;
		if(value != null) {
			output = DictionaryUtils.filterValue(key, value);
		}
		jgen.writeString(output);
	}

}
