/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package org.springframework.web.servlet.tags.form;

import javax.servlet.jsp.JspException;
/**
 * @since 1.0 2014年9月15日,下午1:26:07
 * 增加对Bootstrap的兼容性
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@SuppressWarnings("serial")
public abstract class AbstractSingleCheckedElementTag extends AbstractCheckedElementTag {

	/**
	 * The value of the '{@code value}' attribute.
	 */
	private Object value;

	/**
	 * The value of the '{@code label}' attribute.
	 */
	private Object label;


	/**
	 * Set the value of the '{@code value}' attribute.
	 * May be a runtime expression.
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * Get the value of the '{@code value}' attribute.
	 */
	protected Object getValue() {
		return this.value;
	}

	/**
	 * Set the value of the '{@code label}' attribute.
	 * May be a runtime expression.
	 */
	public void setLabel(Object label) {
		this.label = label;
	}

	/**
	 * Get the value of the '{@code label}' attribute.
	 */
	protected Object getLabel() {
		return this.label;
	}


	/**
	 * Renders the '{@code input(radio)}' element with the configured
	 * {@link #setValue(Object) value}. Marks the element as checked if the
	 * value matches the {@link #getValue bound value}.
	 * 添加了对Bootstrap的兼容性
	 * @author GanHuan
	 */
	@Override
	protected int writeTagContent(TagWriter tagWriter) throws JspException {
		String id = resolveId();
		Object resolvedLabel = evaluate("label", getLabel());
		if (resolvedLabel == null) {
			throw new JspException("label can't be null!");
		}
		tagWriter.startTag("label");
		tagWriter.writeAttribute("for", id);
		//添加默认的属性
		tagWriter.writeAttribute("class", "checkbox-inline");
		//添加输入框
		tagWriter.startTag("input");
		writeOptionalAttribute(tagWriter, "id", id);
		writeOptionalAttribute(tagWriter, "name", getName());
		writeOptionalAttributes(tagWriter);
		writeTagDetails(tagWriter);
		tagWriter.endTag();
		//添加label内容
		tagWriter.appendValue(convertToDisplayString(resolvedLabel));
		tagWriter.endTag();
		return SKIP_BODY;
	}

	/**
	 * Write the details for the given primary tag:
	 * i.e. special attributes and the tag's value.
	 */
	protected abstract void writeTagDetails(TagWriter tagWriter) throws JspException;

}

