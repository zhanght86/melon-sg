/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.field.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Objects;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;

import secfox.soc.melon.asset.AssetConstants;
import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.base.domain.Dictionary;
import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 1.0 2014年10月15日,下午3:31:15
 * 动态属性字段
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name="T_ASSET_FIELD")
@NamedQueries({
	@NamedQuery(name="assetType.findPath",
			query = "select type.path from AssetType type where type.id = :typeId"),
	@NamedQuery(name="assetType.findFieldsByDeviceId",
			query = "select field, value.value "
					+ "from AssetField field, AssetFieldValue value "
					+ "where field.id = value.fieldId "
					+ "and value.deviceId = :deviceId "
					+ "order by field.order asc"),
			
})
public class AssetField extends BaseDomain<Long> {
	
	private static final long serialVersionUID = -3557812472996623954L;

	//主键
	@Id
	@Column(name = "PK")
	@GeneratedValue(generator="GEN_SEQ_ASSET")
	private Long id;
	
	//字段名称
	@Column(name="H_NAME", length=20)
	private String name;
	
	//字段说明
	@Column(name = "H_LABEL",length=20)
	private String label;
	
	//类型：文本框\多行文本框\单选框\复选框
	@Column(name = "FIELD_TYPE")
	private int fieldType;
	
	//数据字典值
	@Column(name = "DICT_ID")
	private Long dictId;
	
	//提示文字
	@Column(name = "H_HINT", length=200)
	private String hint;
	
	//必填项
	@Column(name = "H_REQUIRED")
	private boolean required = true;
	
	//校验类型 整数 字符串/	浮点数/IP/IPV6/EMAIL
	@Column(name = "VALID_TYPE")
	private int validType;
	
	//默认值
	@Column(name = "DEFAULT_VALUE", length=256)
	private String defaultValue;
	
	//顺序
	//数字越小,排序越靠前
	@Column(name = "H_ORDER")
	private int order;
	
	//对应资产类型 如信息系统\路由器等
	@Column(name = "DEVICE_TYPE")
	private Long deviceType;
	
	//数据字典
	@Transient
	private List<Dictionary> dicts;
	
	//数据字典标签
	@Transient
	private String dictLabel;
	
	/**
	 * 
	 * @return
	 */
	public String[] getValues() {
		if(StringUtils.isNotBlank(defaultValue)) {
			return Iterables.toArray(Splitter.on(BaseConstants.SPLITER_FLAG).trimResults().split(defaultValue), String.class);
		}
		return new String[]{};
	}
	
	/**
	 * 
	 * @return
	 */
	public String getFieldName() {
		return AssetConstants.FIELD_PREFIX + id;
	}
	
	/**
	 * 
	 * @return the dictLabel
	 */
	public String getDictLabel() {
		return dictLabel;
	}

	/**
	 * 
	 * @param dictLabel the dictLabel to set
	 */
	public void setDictLabel(String dictLabel) {
		this.dictLabel = dictLabel;
	}



	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.Identifiable#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.Identifiable#setId(java.io.Serializable)
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * 
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	/**
	 * 
	 * @return the hint
	 */
	public String getHint() {
		return hint;
	}

	/**
	 * 
	 * @param hint the hint to set
	 */
	public void setHint(String hint) {
		this.hint = hint;
	}

	/**
	 * 
	 * @return the required
	 */
	public boolean isRequired() {
		return required;
	}

	/**
	 * 
	 * @param required the required to set
	 */
	public void setRequired(boolean required) {
		this.required = required;
	}

	/**
	 * 
	 * @return the validType
	 */
	public int getValidType() {
		return validType;
	}

	/**
	 * 
	 * @param validType the validType to set
	 */
	public void setValidType(int validType) {
		this.validType = validType;
	}

	/**
	 * 
	 * @return the defaultValue
	 */
	public String getDefaultValue() {
		return defaultValue;
	}

	/**
	 * 
	 * @param defaultValue the defaultValue to set
	 */
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}

	/**
	 * 
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * 
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}
	
	/**
	 * 
	 * @return the fieldType
	 */
	public int getFieldType() {
		return fieldType;
	}

	/**
	 * 
	 * @param fieldType the fieldType to set
	 */
	public void setFieldType(int fieldType) {
		this.fieldType = fieldType;
	}

	/**
	 * @return the dictId
	 */
	public Long getDictId() {
		return dictId;
	}

	/**
	 * @param dictId the dictId to set
	 */
	public void setDictId(Long dictId) {
		this.dictId = dictId;
	}

	/**
	 * 
	 * @return the deviceType
	 */
	public Long getDeviceType() {
		return deviceType;
	}

	/**
	 * 
	 * @param deviceType the deviceType to set
	 */
	public void setDeviceType(Long deviceType) {
		this.deviceType = deviceType;
	}
	/**
	 * @return the dicts
	 */
	public List<Dictionary> getDicts() {
		return dicts;
	}

	/**
	 * @param dicts the dicts to set
	 */
	public void setDicts(List<Dictionary> dicts) {
		this.dicts = dicts;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AssetField other = (AssetField) obj;
		if (deviceType == null) {
			if (other.deviceType != null)
				return false;
		} else if (!deviceType.equals(other.deviceType))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (order != other.order)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deviceType == null) ? 0 : deviceType.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + order;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
					  .add("id", id)
					  .add("name", name)
					  .add("label", label)
					  .add("fieldType", fieldType)
					  .add("hint", hint)
					  .add("required", required)
					  .add("defaultValue", defaultValue)
					  .add("dictId", dictId)
				      .toString();
	}

}
