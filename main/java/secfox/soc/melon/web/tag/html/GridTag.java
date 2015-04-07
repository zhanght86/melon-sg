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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.ContextLoader;

import secfox.soc.melon.base.json.JsonFormat;
import secfox.soc.melon.base.template.StrTemplateManager;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @since 1.0 2014年9月19日,下午2:07:19
 * 列表Tag
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class GridTag extends SimpleTagSupport implements DynamicAttributes{
	
	private Grid define;

	private String var;
	
	/**
	 * 
	 */
	public GridTag() {
		super();
		define = new Grid();
	}
	
	/**
	 * 设置是否自己管理JS代码
	 * @param scriptSelf
	 */
	public void setScriptSelf(boolean scriptSelf) {
		define.setScriptSelf(scriptSelf);
	}

	/**
	 * 设置转换属性
	 * @param var
	 */
	public void setVar(String var) {
		this.var = var;
	}
	
	public String getVar() {
		return var;
	}

	/**
     * 添加列定义
     * @param column
     */
    public void addColumn(Column column) {
    	define.addColumn(column);
    }

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.DynamicAttributes#setDynamicAttribute(java.lang.String, java.lang.String, java.lang.Object)
	 */
	@Override
	public void setDynamicAttribute(String uri, String key, Object value)	throws JspException {
		define.addAttribute(key, (String)value);
	}
	
	/**
	 * 获取当前请求的路径
	 * @return
	 */
	private String getContextPath() {
		PageContext context = (PageContext) getJspContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        String contextPath = request.getContextPath() + "/";
        return contextPath;
	}
	
	/**
	 * 设置表格的ID
	 * @param id
	 */
	public void setId(String id) {
		define.setId(id);
	}
	
	/**
	 * 设置查询地址
	 * @param queryUrl
	 */
	public void setQueryUrl(String queryUrl) {
		if(StringUtils.isNotBlank(queryUrl)) {
			define.setQueryUrl(getContextPath() + queryUrl);
		}
	}
	
	/**
	 * 设置更新地址
	 * @param editUrl
	 */
	public void setUpdateUrl(String updateUrl) {
		if(StringUtils.isNotBlank(updateUrl)) {
			define.setEditUrl(getContextPath() + updateUrl);
		}
	}
	
	/**
	 * 设置删除地址
	 * @param deleteUrl
	 */
	public void setDeleteUrl(String deleteUrl) {
		if(StringUtils.isNotBlank(deleteUrl)) {
			define.setDeleteUrl(getContextPath() + deleteUrl);
		}
		
	}
	
	/**
	 * 设置显示地址
	 * @param showUrl
	 */
	public void setShowUrl(String showUrl) {
		if(StringUtils.isNotBlank(showUrl)) {
			define.setShowUrl(getContextPath() + showUrl);
		}
	}

	/**
	 * 是否使用分页
	 * @param hasPages
	 */
	public void setHasPages(boolean hasPages) {
		define.setHasPages(hasPages);
	}
	
	/**
	 * 设置请求方法的类型
	 * @param method
	 */
	public void setMethod(String method) {
		if(StringUtils.equals("GET", StringUtils.upperCase(method))) {
			define.setMethod(RequestMethod.GET);
		} else {
			define.setMethod(RequestMethod.POST);
		}
		
	}
	
	/**
	 * 设置获取查询参数的方法
	 * @param query
	 */
	public void setQuery(String query) {
		define.setQuery(query);
	}
	
	/**
	 * 设置是否使用多选
	 * @param multiSelect
	 */
	public void setMultiSelect(boolean multiSelect) {
		define.setMultiSelect(multiSelect);
	}
	
	/**
	 * 设置JSON数据的类型,是数组或者OBJECT
	 * @param dataType
	 */
	public void setDataType(String dataType) {
		if(StringUtils.equals("ARRAY", StringUtils.upperCase(dataType))) {
			define.setDataType(JsonFormat.ARRAY);
		} else {
			define.setDataType(JsonFormat.OBJECT);
		}
	}
	

    /* (non-Javadoc)
     * @see javax.servlet.jsp.tagext.SimpleTagSupport#doTag()
     */
    @Override
    public void doTag() throws JspException, IOException {
    	//先解析col标签
    	getJspBody().invoke(null);
    	ObjectMapper objectMapper = ContextLoader.getCurrentWebApplicationContext().getBean("objectMapper", ObjectMapper.class);
        String configs = objectMapper.writeValueAsString(define);
        if(define.isScriptSelf() && StringUtils.isNotBlank(var)) {
        	getJspContext().setAttribute(var, configs);
        } else {
        	define.setConfigs(configs);
        }
    	String scripts = StrTemplateManager.find("ajaxGrid").format(define);
    	getJspContext().getOut().print(scripts);
    }
}
