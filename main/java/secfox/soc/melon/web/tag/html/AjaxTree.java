/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.web.tag.html;

import java.util.Map;

import secfox.soc.melon.base.SortOrder;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.google.common.collect.Maps;

/**
 * @since 1.0 2014年9月21日,下午9:53:33
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class AjaxTree {
	
	/**
	 * 
	 */
	private static final String TREE = "_TREE";

	/**
	 * 
	 */
	private String id;
	
	/**
	 * 
	 */
	private String sortName;
	
	/**
	 * 
	 */
	private SortOrder sortOrder = SortOrder.asc;
	
	/**
	 * Ajax的请求地址
	 */
	private String url;
	
	/**
	 * 
	 */
	private Long rootId;
	
	/**
	 * 
	 */
    @JsonRawValue
	private String params;
    
	private boolean scriptSelf;
	
    /**
     * 
     */
	private boolean multiSelect;
	
	 //用于存放动态属性
    @JsonUnwrapped(enabled=true)
    private Map<String, String> attributes;
	
	
    @JsonIgnore
    private String configs;
    
    public String getEl() {
    	return id + TREE;
    }

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	  
    public String getSortName() {
		return sortName;
	}

	public void setSortName(String sortName) {
		this.sortName = sortName;
	}

	public SortOrder getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(SortOrder sortOrder) {
		this.sortOrder = sortOrder;
	}

	/**
	 * @return the rootId
	 */
	public Long getRootId() {
		return rootId;
	}

	/**
	 * @param rootId the rootId to set
	 */
	public void setRootId(Long rootId) {
		this.rootId = rootId;
	}

	/**
	 * @return the params
	 */
	public String getParams() {
		return params;
	}

	/**
	 * @param params the params to set
	 */
	public void setParams(String params) {
		this.params = params;
	}

	/**
	 * @return the multiSelect
	 */
	public boolean isMultiSelect() {
		return multiSelect;
	}

	/**
	 * @param multiSelect the multiSelect to set
	 */
	public void setMultiSelect(boolean multiSelect) {
		this.multiSelect = multiSelect;
	}

	/**
	 * @return the attributes
	 */
	public Map<String, String> getAttributes() {
		return attributes;
	}

	/**
	 * @param attributes the attributes to set
	 */
	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	
	public void addAttribute(String key, String value) {
		if(this.attributes == null) {
			this.attributes = Maps.newHashMap();
		}
		this.attributes.put(key, value);
	}

	/**
	 * @return the scriptSelf
	 */
	public boolean isScriptSelf() {
		return scriptSelf;
	}

	/**
	 * @param scriptSelf the scriptSelf to set
	 */
	public void setScriptSelf(boolean scriptSelf) {
		this.scriptSelf = scriptSelf;
	}

	/**
	 * @return the configs
	 */
	public String getConfigs() {
		return configs;
	}

	/**
	 * @param configs the configs to set
	 */
	public void setConfigs(String configs) {
		this.configs = configs;
	}
	
}
