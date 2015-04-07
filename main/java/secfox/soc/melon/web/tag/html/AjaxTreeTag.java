/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.web.tag.html;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.ContextLoader;

import secfox.soc.melon.base.SortOrder;
import secfox.soc.melon.base.template.StrTemplateManager;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @since 1.0 2014年9月21日,下午9:57:19
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class AjaxTreeTag extends SimpleTagSupport implements DynamicAttributes{
	
	private AjaxTree ajaxTree;
	
	private String var = "ajaxTreeConfigs";
	
	/**
	 * 
	 */
	public AjaxTreeTag() {
		super();
		ajaxTree = new AjaxTree();
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		ajaxTree.setId(id);
	}
	
	public void setSortName(String sortName) {
		ajaxTree.setSortName(sortName);
	}

	/**
	 * 
	 * @param sortOrder
	 */
	public void setSortOrder(String sortOrder) {
		if(SortOrder.desc.name().equals(StringUtils.lowerCase(sortOrder))) {
			ajaxTree.setSortOrder(SortOrder.desc);
		}
	}
	
	/**
	 * @param multiSelect the multiSelect to set
	 */
	public void setMultiSelect(boolean multiSelect) {
		ajaxTree.setMultiSelect(multiSelect);
	}
	
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		PageContext context = (PageContext) getJspContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        String contextPath = request.getContextPath() + "/";
		ajaxTree.setUrl(contextPath + url);
	}
	
	/**
	 * @param rootId the rootId to set
	 */
	public void setRootId(Long rootId) {
		ajaxTree.setRootId(rootId);;
	}
	
	/**
	 * @param params the params to set
	 */
	public void setParams(String params) {
		ajaxTree.setParams(params);
	}
	
	/**
	 * @param scriptSelf the scriptSelf to set
	 */
	public void setScriptSelf(boolean scriptSelf) {
		ajaxTree.setScriptSelf(scriptSelf);
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.DynamicAttributes#setDynamicAttribute(java.lang.String, java.lang.String, java.lang.Object)
	 */
	@Override
	public void setDynamicAttribute(String arg0, String key, Object value) throws JspException {
		ajaxTree.addAttribute(key, (String)value);
	}
	
	/**
	 * @return the var
	 */
	public String getVar() {
		return var;
	}

	/**
	 * @param var the var to set
	 */
	public void setVar(String var) {
		this.var = var;
	}
	
    /* (non-Javadoc)
     * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
     */
    @Override
    public void doTag() throws JspException, IOException {
    	super.doTag();
    	ObjectMapper objectMapper = ContextLoader.getCurrentWebApplicationContext().getBean("objectMapper", ObjectMapper.class);
        String configs = objectMapper.writeValueAsString(ajaxTree);
        if(ajaxTree.isScriptSelf()) {
        	getJspContext().setAttribute(var, configs);
        } else {
        	ajaxTree.setConfigs(configs);
        }
    	String scripts =  StrTemplateManager.find("ajaxTree").format(ajaxTree);
    	getJspContext().getOut().print(scripts);
    }
	
}
