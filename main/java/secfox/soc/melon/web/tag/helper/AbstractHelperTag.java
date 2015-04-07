/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.web.tag.helper;

import java.io.IOException;
import java.util.Map;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;

import secfox.soc.melon.base.template.StrTemplateManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;

/**
 * @since 1.0 2014年9月26日,下午1:07:36
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public abstract class AbstractHelperTag extends SimpleTagSupport implements DynamicAttributes{
	
	private Map<String, Object> configs = Maps.newHashMap();
	
	private String var;
	
	private boolean scriptSelf;
	
	/**
	 * 设置组件引用的ID
	 * @param id
	 */
	public void setId(String id) {
		configs.put("el", id);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.DynamicAttributes#setDynamicAttribute(java.lang.String, java.lang.String, java.lang.Object)
	 */
	@Override
	public void setDynamicAttribute(String uri, String key, Object value) throws JspException {
		configs.put(key, (String)value);
	}
	
	public void addConfig(String key, Object value) {
		if(StringUtils.isNotBlank(key) && value != null) {
			configs.put(key, value);
		}
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		ObjectMapper objectMapper = ContextLoader.getCurrentWebApplicationContext().getBean("objectMapper", ObjectMapper.class);
        String configStr = objectMapper.writeValueAsString(configs);
        if(isScriptSelf()) {
        	getJspContext().setAttribute(var, configs);
        } else {
        	addConfig("scriptSelf", false);
        	addConfig("configs", configStr);
        }
    	String scripts = StrTemplateManager.find(getTmplId()).format(configs);
    	getJspContext().getOut().print(scripts);
	}
	
	/**
	 * 
	 * @return
	 */
	public abstract String getTmplId();
	

	public String getVar() {
		return var;
	}

	public void setVar(String var) {
		this.var = var;
	}

	public boolean isScriptSelf() {
		return scriptSelf;
	}

	public void setScriptSelf(boolean scriptSelf) {
		this.scriptSelf = scriptSelf;
		addConfig("scriptSelf", scriptSelf);
	}
	
}
