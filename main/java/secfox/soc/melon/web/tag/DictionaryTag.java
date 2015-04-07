/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.web.tag;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import secfox.soc.melon.base.service.DictionaryService;
import secfox.soc.melon.base.util.ApplicationContextUtils;

/**
 * @since 1.0 2014年9月17日,下午7:58:15
 * 数据字典标签
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class DictionaryTag extends SimpleTagSupport {
	
	private String key;
	
	private String var;
	
	private Object value;

	/**
	 * 
	 * @return the key
	 */
	public String getKey() {
		return key;
	}


	/**
	 * 
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}


	/**
	 * 
	 * @return the var
	 */
	public String getVar() {
		return var;
	}


	/**
	 * 
	 * @param var the var to set
	 */
	public void setVar(String var) {
		this.var = var;
	}

	/**
	 * 
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * 
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
	 */
	@Override
	public void doTag() throws JspException, IOException {
		super.doTag();
		DictionaryService dictService = ApplicationContextUtils.getBean(DictionaryService.class);
		if(value != null) {
			String result = dictService.filterValue(key, value);
			getJspContext().getOut().write(result);
		} else {
			getJspContext().setAttribute(var, dictService.findByKey(key));
		}
	}
	
}
