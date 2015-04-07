/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.web.tag.html;

import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.jsp.JspException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.PropertyAccessorFactory;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.tags.form.HiddenInputTag;
import org.springframework.web.servlet.tags.form.SelectedValueComparator;
import org.springframework.web.servlet.tags.form.TagWriter;

/**
 * @since 1.0 2014年9月15日,下午2:13:38
 * 属性值标签,用于输出属性值
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class ValueTag extends HiddenInputTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = -820269864413236327L;
	
	/**
	 * The name of the property mapped to the '{@code value}' attribute
	 * of the '{@code input type="checkbox/radio"}' tag.
	 */
	private String itemValue;
	
	/**
	 * The value to be displayed as part of the '{@code input type="checkbox/radio"}' tag.
	 */
	private String itemLabel;
	
	/**
	 * The {@link java.util.Collection}, {@link java.util.Map} or array of objects
	 * used to generate the '{@code input type="checkbox/radio"}' tags.
	 */
	private Object items;
	
	/**
	 * 	对枚举类型的支持
	 *	增加对数组的支持
	 *	增加对数据字典的解析
	 */
	@Override
	@SuppressWarnings("rawtypes")
	protected int writeTagContent(TagWriter tagWriter) throws JspException {
		//基本类型值的支持
		if(items == null) {
			tagWriter.startTag("p");
			String cssClass = getCssClass() == null ? "" : getCssClass();
			//
			writeDefaultAttributes(tagWriter);
			if(isDisabled()) {
				tagWriter.writeAttribute(DISABLED_ATTRIBUTE, "disabled");
			}
			String value = getDisplayString(getBoundValue(), getPropertyEditor());
			//
			if(StringUtils.length((String)value) >= 30) {
				cssClass = cssClass + "form-control-block";
			} else {
				cssClass = cssClass + "form-control-static";
			}
			//
			tagWriter.writeAttribute("class", cssClass);
			tagWriter.appendValue(processFieldValue(getName(), value, "hidden"));
			tagWriter.endTag();
		} else {
			//准备解析数据字典
			//Object items = getItems();
			Object itemsObject = (items instanceof String ? evaluate("items", items) : items);
			String itemValue = getItemValue();
			String itemLabel = getItemLabel();
			//解析数据字典
			String valueProperty =
					(itemValue != null ? ObjectUtils.getDisplayString(evaluate("itemValue", itemValue)) : null);
			String labelProperty =
					(itemLabel != null ? ObjectUtils.getDisplayString(evaluate("itemLabel", itemLabel)) : null);
			//获取绑定自身的属性
			Class<?> boundType = getBindStatus().getValueType();
			if (itemsObject == null && boundType != null && boundType.isEnum()) {
				itemsObject = boundType.getEnumConstants();
			}
			//getBindStatus().getActualValue() instanceof Object[]
			boolean isArray = boundType.isArray() || getBindStatus().getActualValue() instanceof Collection;
			if(isArray) {
				tagWriter.startTag("ol");
				tagWriter.writeAttribute("class", "form-control-items");
			}
			//判断是否是数组
			if (itemsObject.getClass().isArray()) {
				Object[] itemsArray = (Object[]) itemsObject;
				for (int i = 0; i < itemsArray.length; i++) {
					Object item = itemsArray[i];
					writeObjectEntry(tagWriter, valueProperty, labelProperty, item, i, isArray);
				}
			} 
			//判断是否是集合
			else if (itemsObject instanceof Collection) {
				final Collection<?> optionCollection = (Collection<?>) itemsObject;
				int itemIndex = 0;
				for (Iterator<?> it = optionCollection.iterator(); it.hasNext(); itemIndex++) {
					Object item = it.next();
					writeObjectEntry(tagWriter, valueProperty, labelProperty, item, itemIndex, isArray);
				}
			}
			//判断是否是Map
			else if (itemsObject instanceof Map) {
				final Map<?, ?> optionMap = (Map<?, ?>) itemsObject;
				int itemIndex = 0;
				for (Iterator it = optionMap.entrySet().iterator(); it.hasNext(); itemIndex++) {
					Map.Entry entry = (Map.Entry) it.next();
					writeMapEntry(tagWriter, valueProperty, labelProperty, entry, itemIndex, isArray);
				}
			}
			else {
				throw new IllegalArgumentException("Attribute 'items' must be an array, a Collection or a Map");
			}
			
			if(isArray) {
				tagWriter.endTag();
			}

		}
		//tagWriter.endTag();
		return SKIP_BODY;
	}

	/**
	 * @param tagWriter
	 * @param valueProperty
	 * @param labelProperty
	 * @param entry
	 * @param itemIndex
	 * @throws JspException 
	 */
	@SuppressWarnings("rawtypes")
	private void writeMapEntry(TagWriter tagWriter, String valueProperty,
			String labelProperty, Entry entry, int itemIndex, boolean isArray) throws JspException {
		Object mapKey = entry.getKey();
		Object mapValue = entry.getValue();
		BeanWrapper mapKeyWrapper = PropertyAccessorFactory.forBeanPropertyAccess(mapKey);
		BeanWrapper mapValueWrapper = PropertyAccessorFactory.forBeanPropertyAccess(mapValue);
		Object renderValue = (valueProperty != null ?
				mapKeyWrapper.getPropertyValue(valueProperty) : mapKey.toString());
		Object renderLabel = (labelProperty != null ?
				mapValueWrapper.getPropertyValue(labelProperty) : mapValue.toString());
		writeElementTag(tagWriter, mapKey, renderValue, renderLabel, itemIndex, isArray);
	}

	/**
	 * @param tagWriter
	 * @param valueProperty
	 * @param labelProperty
	 * @param item
	 * @param i
	 * @throws JspException 
	 */
	private void writeObjectEntry(TagWriter tagWriter, String valueProperty, String labelProperty, Object item, int itemIndex, boolean isArray) throws JspException {
		BeanWrapper wrapper = PropertyAccessorFactory.forBeanPropertyAccess(item);
		Object renderValue;
		if (valueProperty != null) {
			renderValue = wrapper.getPropertyValue(valueProperty);
		}
		else if (item instanceof Enum) {
			renderValue = ((Enum<?>) item).name();
		}
		else {
			renderValue = item;
		}
		Object renderLabel = (labelProperty != null ? wrapper.getPropertyValue(labelProperty) : item);
		writeElementTag(tagWriter, item, renderValue, renderLabel, itemIndex, isArray);
	}

	/**
	 * @param tagWriter
	 * @param item
	 * @param renderValue
	 * @param renderLabel
	 * @param itemIndex
	 * @throws JspException 
	 */
	private void writeElementTag(TagWriter tagWriter, Object item, Object renderValue, Object renderLabel, int itemIndex, boolean isArray) throws JspException {
		if(SelectedValueComparator.isSelected(getBindStatus(), renderValue)) {
			if(isArray) {
				tagWriter.startTag("li");
				tagWriter.writeAttribute("class", "form-control-item");
			} else {
				tagWriter.startTag("p");
				tagWriter.writeAttribute("class", "form-control-static");
			}
			tagWriter.appendValue(String.valueOf(renderLabel));
			tagWriter.endTag();
		}
	}

	public String getItemValue() {
		return itemValue;
	}

	public void setItemValue(String itemValue) {
		this.itemValue = itemValue;
	}

	public String getItemLabel() {
		return itemLabel;
	}

	public void setItemLabel(String itemLabel) {
		this.itemLabel = itemLabel;
	}

	public Object getItems() {
		return items;
	}

	public void setItems(Object items) {
		this.items = items;
	}
	
}
