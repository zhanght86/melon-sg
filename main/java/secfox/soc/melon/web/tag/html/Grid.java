/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.web.tag.html;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestMethod;

import secfox.soc.melon.base.json.JsonFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * @since 1.0 2014年9月19日,上午11:47:56
 * 列表对象
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class Grid {
	
	public static final String _PAGER = "_PAGER";

	public static final String _GRID = "_GRID";
	
	//id,标识Grid、EL
    private String id;
    
    //用于存放动态属性
    @JsonUnwrapped(enabled=true)
    private Map<String, String> attributes;
    
    //用于请求JSON地址
    private String queryUrl;
    
    //修改的地址
    private String editUrl;
    
    //删除地址
    private String deleteUrl;
    
    //明细地址
    private String showUrl;
   
    //是否使用分页
    private boolean hasPages;
    
    //HTTP请求方式
    private RequestMethod method = RequestMethod.POST;
    
    @JsonRawValue
    private String query;
    
    //是否支持多选
    private boolean multiSelect;
    
    //列定义
    private List<Column> colModel;
    
    //传送数据的格式,支持数据或Map形式
    private JsonFormat dataType = JsonFormat.OBJECT;
    
    //是否自己管理Script
    @JsonIgnore
    private boolean scriptSelf = false;
    
    @JsonIgnore
    private String configs;
    
    /**
     * 添加列定义
     * @param column
     */
    public void addColumn(Column column) {
    	if(colModel == null) {
    		colModel = Lists.newArrayList();
    	}
    	colModel.add(column);
    }
    
    /**
     * 添加新属性
     * @param key
     * @param value
     */
    public void addAttribute(String key, String value) {
    	if(attributes == null) {
    		attributes = Maps.newHashMap();
    	}
    	attributes.put(key, value);
    }
    
    
	public boolean isScriptSelf() {
		return scriptSelf;
	}

	public void setScriptSelf(boolean scriptSelf) {
		this.scriptSelf = scriptSelf;
	}

	public String getConfigs() {
		return configs;
	}

	public void setConfigs(String configs) {
		this.configs = configs;
	}

	public String getId() {
		return id;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getEl() {
		return id + _GRID;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getPager() {
		return id + _PAGER;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}

	public String getQueryUrl() {
		return queryUrl;
	}

	public void setQueryUrl(String queryUrl) {
		this.queryUrl = queryUrl;
	}

	public String getEditUrl() {
		return editUrl;
	}

	public void setEditUrl(String editUrl) {
		this.editUrl = editUrl;
	}

	public String getDeleteUrl() {
		return deleteUrl;
	}

	public void setDeleteUrl(String deleteUrl) {
		this.deleteUrl = deleteUrl;
	}

	public String getShowUrl() {
		return showUrl;
	}

	public void setShowUrl(String showUrl) {
		this.showUrl = showUrl;
	}

	public boolean isHasPages() {
		return hasPages;
	}

	public void setHasPages(boolean hasPages) {
		this.hasPages = hasPages;
	}

	public RequestMethod getMethod() {
		return method;
	}

	public void setMethod(RequestMethod method) {
		this.method = method;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public boolean isMultiSelect() {
		return multiSelect;
	}

	public void setMultiSelect(boolean multiSelect) {
		this.multiSelect = multiSelect;
	}

	public List<Column> getColModel() {
		return colModel;
	}

	public void setColModel(List<Column> colModel) {
		this.colModel = colModel;
	}

	public JsonFormat getDataType() {
		return dataType;
	}

	public void setDataType(JsonFormat dataType) {
		this.dataType = dataType;
	}
    
}
