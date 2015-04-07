/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base.oxm;

import java.io.Writer;
import java.lang.reflect.Field;

import org.springframework.stereotype.Component;

import secfox.soc.melon.base.annotation.XStreamCDATA;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * @since 1.0 2014年12月22日,下午12:12:56
 * 
 * @author <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version 1.0
 */
@Component("driverCDATA")
public class XppDriverWithCDATA extends XppDriver {
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.thoughtworks.xstream.io.xml.AbstractXppDriver#createWriter(java.io
	 * .Writer)
	 */
	@Override
	public HierarchicalStreamWriter createWriter(Writer out) {
		return new PrettyPrintWriter(out) {
			
			private boolean cdata = false;
			
			
			@Override
			public void startNode(String name,	@SuppressWarnings("rawtypes") Class clazz) {
				super.startNode(name, clazz);
				// 业务处理，对于用XStreamCDATA标记的Field，需要加上CDATA标签
				if (!name.equals("xml")) {
					cdata = needCDATA(clazz, name);
				}
			}

			@Override
			protected void writeText(QuickWriter writer, String text) {
				if (cdata) {
					writer.write(wrapCDATA(text));
				} else {
					writer.write(text);
				}
			}
			
			private String wrapCDATA(String text) {
				String content = text.replaceAll("/n", "").replaceAll("/t", "").trim();
				return "<![CDATA[" + content + "]]>";
			}
			
		};
	}

	private static boolean needCDATA(Class<?> targetClass, String fieldAlias){
	    boolean cdata = false;
	    if(targetClass != null) {
	    	//first, scan self
		    cdata = existsCDATA(targetClass, fieldAlias);
		    if(cdata) {
		    	return cdata;
		    }
		    
		    Class<?> superClass = targetClass.getSuperclass();
		    while(!Object.class.equals(superClass)){
		      cdata = existsCDATA(superClass, fieldAlias);
		      if(cdata) {
		    	  return cdata;
		      }
		      superClass = superClass.getClass().getSuperclass();
		    }
		    return true;
	    }
	    return false;
	  }
	
	private static boolean existsCDATA(Class<?> clazz, String fieldAlias){
		if (clazz != null) {
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				// 1. exists XStreamCDATA
				if (field.getAnnotation(XStreamCDATA.class) != null) {
					XStreamAlias xStreamAlias = field.getAnnotation(XStreamAlias.class);
					if (null != xStreamAlias) {
						if (fieldAlias.equals(xStreamAlias.value())) {
							return true;
						}
					} else {
						return true;
						// not exists XStreamAlias
						/*if (fieldAlias.equals(field.getName())) {
							return true;
						}*/
					}
				}
			}
		}
		return false;
	  }
	
}
