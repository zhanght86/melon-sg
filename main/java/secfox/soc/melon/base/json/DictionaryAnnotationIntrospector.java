/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base.json;

import secfox.soc.melon.base.json.serializer.DictionarySerializer;

import com.fasterxml.jackson.databind.introspect.Annotated;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;
import com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector;

/**
 * @since 1.0 2014年10月3日,上午10:47:39
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class DictionaryAnnotationIntrospector extends JacksonAnnotationIntrospector {

	private static final long serialVersionUID = -3464233939609459786L;

	/* (non-Javadoc)
	 * @see com.fasterxml.jackson.databind.introspect.JacksonAnnotationIntrospector#findSerializer(com.fasterxml.jackson.databind.introspect.Annotated)
	 */
	@Override
	public Object findSerializer(Annotated annotated) {
		//经测试,只对方法有用
		if(annotated instanceof AnnotatedMethod) {
			Dictionary dictionary = annotated.getAnnotated().getAnnotation(Dictionary.class);
			if(dictionary != null) {
				return new DictionarySerializer(dictionary.value());
			}
		}
		return super.findSerializer(annotated);
	}
	
	

}
