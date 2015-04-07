/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.web.tag.html;

import java.io.IOException;
import java.io.StringWriter;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.JspFragment;

/**
 * @since 1.0 2014年10月18日,下午2:55:08
 * 下拉框Tag
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class DropDownTag extends AbstractHtmlTag {
	
	private String id;
	
	private JspFragment values;
	
	private JspFragment menus;

	private boolean dropup;
	
	/**
	 * 
	 * @param dropup the dropup to set
	 */
	public void setDropup(boolean dropup) {
		this.dropup = dropup;
	}

	/**
	 * 
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
		addAtrribute("id", id);
		addAtrribute("menuId", getMenuId());
	}
	
	public String getCssClass() {
		if(this.dropup) {
			return "dropup";
		}
		return "dropdown";
	}
	
	/**
	 * 
	 * @return
	 */
	public String getMenuId() {
		return id + "DropDown";
	}

	
	/**
	 * 
	 * @param value the value to set
	 */
	public void setValues(JspFragment values) {
		this.values = values;
		addAtrribute("value", getValue());
	}
	
	/**
	 * 
	 * @return
	 */
	public String getValue() {
		StringWriter writer = new StringWriter();
		//首先解析头部
		try {
			values.invoke(writer);
		} catch (JspException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return writer.toString();
	}

	/**
	 * 
	 * @param menus the menus to set
	 */
	public void setMenus(JspFragment menus) {
		this.menus = menus;
		addAtrribute("menus", getMenu());
	}
	
	/**
	 * 
	 * @return
	 */
	public String getMenu() {
		StringWriter writer = new StringWriter();
		//首先解析头部
		try {
			menus.invoke(writer);
		} catch (JspException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return writer.toString();
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.web.tag.html.AbstractHtmlTag#doTag()
	 */
	@Override
	public void doTag() throws JspException, IOException {
		addAtrribute("cssClass", getCssClass());
		super.doTag();
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.web.tag.html.AbstractHtmlTag#getTemplate()
	 */
	@Override
	protected String getTemplate() {
		return "dropdownTag";
	}

}
