/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.web.tag.html;

import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonRawValue;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.google.common.collect.Maps;

/**
 * @since 1.0 2014年9月19日,上午11:39:17
 * Grid的列属性
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class Column {
	
	//读取数据的列名
	private String name;
	
	//数据的列标题,支持i18n
	private String label;
	
	//数据的格式化器
    @JsonRawValue
    private String formatter;
    
    //是否排序
    private boolean sortable = true;
    
    //是否显示此列数据
    private boolean hidden;
    
    //设置对齐方式
    private String align;
    
    //宽度
    private int width;
    
    //查询的索引
    private String index;
    
    //动态属性
    @JsonUnwrapped(enabled=true)
    private Map<String, String> attributes;
    
    /**
     * 添加动态属性
     * @param key
     * @param value
     */
    public void addAttribute(String key, String value) {
    	if(attributes == null) {
    		attributes = Maps.newHashMap();
    	}
    	attributes.put(key, value);
    }
    
	public void setIndex(String index) {
		this.index = index;
	}



	public String getName() {
		return name;
	}
	
	public String getAlign() {
		return align;
	}

	public void setAlign(String align) {
		this.align = align;
	}

	/**
	 * 返回查询字段名
	 * @return
	 */
	public String getIndex() {
		if(StringUtils.isNotBlank(index)) {
			return index;
		}
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getFormatter() {
		return formatter;
	}

	public void setFormatter(String formatter) {
		this.formatter = formatter;
	}

	public boolean isSortable() {
		return sortable;
	}

	public void setSortable(boolean sortable) {
		this.sortable = sortable;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public Map<String, String> getAttributes() {
		return attributes;
	}

	public void setAttributes(Map<String, String> attributes) {
		this.attributes = attributes;
	}
	
	public boolean isHidden() {
		return hidden;
	}

	public void setHidden(boolean hidden) {
		this.hidden = hidden;
	}

	@Override
	public String toString() {
		return "Column [name=" + name + ", label=" + label + ", formatter="
				+ formatter + ", sortable=" + sortable + ", width=" + width
				+ ", attributes=" + attributes + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Column other = (Column) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
    
}
