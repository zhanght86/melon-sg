/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package org.springframework.web.servlet.tags.form;

import javax.servlet.jsp.JspException;

/**
 * @since 1.0 2014年9月15日,下午2:01:53
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@SuppressWarnings("serial")
public class ButtonTag extends AbstractHtmlElementTag {

	/**
	 * The name of the '{@code disabled}' attribute.
	 */
	public static final String DISABLED_ATTRIBUTE = "disabled";

	private TagWriter tagWriter;

	private String name;

	private String value;

	private boolean disabled;


	/**
	 * Get the value of the '{@code name}' attribute.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Set the value of the '{@code name}' attribute.
	 */
	@Override
	public String getName() {
		return name;
	}

	/**
	 * Set the value of the '{@code value}' attribute.
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * Get the value of the '{@code value}' attribute.
	 */
	public String getValue() {
		return this.value;
	}

	/**
	 * Set the value of the '{@code disabled}' attribute.
	 */
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	/**
	 * Get the value of the '{@code disabled}' attribute.
	 */
	public boolean isDisabled() {
		return this.disabled;
	}


	@Override
	protected int writeTagContent(TagWriter tagWriter) throws JspException {
		tagWriter.startTag("button");
		writeDefaultAttributes(tagWriter);
		tagWriter.writeAttribute("type", getType());
		writeValue(tagWriter);
		if (isDisabled()) {
			tagWriter.writeAttribute(DISABLED_ATTRIBUTE, "disabled");
		}
		tagWriter.forceBlock();
		this.tagWriter = tagWriter;
		return EVAL_BODY_INCLUDE;
	}

	/**
	 * Writes the '{@code value}' attribute to the supplied {@link TagWriter}.
	 * Subclasses may choose to override this implementation to control exactly
	 * when the value is written.
	 */
	protected void writeValue(TagWriter tagWriter) throws JspException {
		String valueToUse = (getValue() != null) ? getValue() : getDefaultValue();
		tagWriter.writeAttribute("value", processFieldValue(getName(), valueToUse, getType()));
	}

	/**
	 * Return the default value.
	 * @return The default value if none supplied.
	 */
	protected String getDefaultValue() {
		return "Submit";
	}

	/**
	 * Get the value of the '{@code type}' attribute. Subclasses
	 * can override this to change the type of '{@code input}' element
	 * rendered. Default value is '{@code submit}'.
	 */
	protected String getType() {
		return "submit";
	}

	/**
	 * Closes the '{@code button}' block tag.
	 */
	@Override
	public int doEndTag() throws JspException {
		this.tagWriter.endTag();
		return EVAL_PAGE;
	}

	/**
	 * 设置tagWriter,便于继承与覆盖
	 * @param tagWriter
	 */
	public void setTagWriter(TagWriter tagWriter) {
		this.tagWriter = tagWriter;
	}
	
}

