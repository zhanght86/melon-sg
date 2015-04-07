/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.web.tag.html;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.DynamicAttributes;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.context.ContextLoader;

/**
 * @since 1.0 2014年9月19日,下午1:55:45
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class ColumnTag extends SimpleTagSupport implements DynamicAttributes{
	
	private Column column;
	
	/**
	 * 默认构造器
	 */
	public ColumnTag() {
		super();
		column = new Column();
	}

	/* (non-Javadoc)
	 * @see javax.servlet.jsp.tagext.DynamicAttributes#setDynamicAttribute(java.lang.String, java.lang.String, java.lang.Object)
	 */
	@Override
	public void setDynamicAttribute(String url, String key, Object value)	throws JspException {
		column.addAttribute(key, (String)value);
	}
	
	/**
     * @param name the name to set
     */
    public void setName(String name) {
        column.setName(name);
    }
    
    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        MessageSource messageSource = ContextLoader.getCurrentWebApplicationContext().getBean("messageSource", MessageSource.class);
        String label = messageSource.getMessage(title, null, title, LocaleContextHolder.getLocale());
        column.setLabel(label);
    }
    
    /**
     * 设置是否可排序
     * @param sortable
     */
    public void setSortable(boolean sortable) {
    	column.setSortable(sortable);
	}
    
    /**
     * 设置查询字段
     * @param index
     */
	public void setIndex(String index) {
		column.setIndex(index);
	}
    
    /**
     * 设置格式化管理器
     * @param formatter
     */
    public void setFormatter(String formatter) {
    	column.setFormatter(formatter);
	}
    
    /**
     * 设置宽度
     * @param width
     */
	public void setWidth(int width) {
		column.setWidth(width);
	}

	/**
	 * 设置是否显示
	 * @param hidden
	 */
	public void setHidden(boolean hidden) {
		column.setHidden(hidden);
	}

	/**
	 * 设置对齐方式
	 * @param align
	 */
	public void setAlign(String align) {
		column.setAlign(align);
	}
	
    @Override
    public void doTag() throws JspException, IOException {
        //将列定义添加到Grid
    	GridTag ancestor = (GridTag)findAncestorWithClass(this, GridTag.class);
        ancestor.addColumn(column);
    }
}
