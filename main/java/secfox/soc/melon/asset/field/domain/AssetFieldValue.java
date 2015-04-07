/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.field.domain;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import secfox.soc.melon.asset.AssetConstants;
import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 1.0 2014年10月15日,下午3:44:02
 * 动态属性值
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name="T_ASSET_FIELD_VAL")
@NamedQuery(name="assetFieldValue.findExist",
		query="select value from AssetFieldValue value where value.deviceId = :deviceId and value.fieldId = :fieldId")
public class AssetFieldValue extends BaseDomain<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4179441044760754258L;
	
	//主键
	@Id
	@Column(name = "PK")
	@GeneratedValue(generator="GEN_SEQ_ASSET")
	private Long id;
	
	/**
	 * 设备或信息系统ID
	 */
	@Column(name = "DEVICE_ID")
	private Long deviceId;
	
	//对应的属性主键
	@Column(name="FIELD_ID")
	private Long fieldId;
	
	//属性值
	@Column(name="FIELD_VALUE", length=256)
	private String value;
	
	/**
	 * 
	 * @return
	 */
	public String[] getValues() {
		if(StringUtils.isNotBlank(value)) {
			return Iterables.toArray(Splitter.on(BaseConstants.SPLITER_FLAG).trimResults().split(value), String.class);
		}
		return null;
	}
	
	/**
	 * 
	 * @param deviceId
	 * @param fieldName
	 * @param fieldValue
	 * @return
	 */
	public static AssetFieldValue create(Long deviceId, String fieldName, Object fieldValue) {
		AssetFieldValue value = new AssetFieldValue();
		//
		value.setDeviceId(deviceId);
		//设置主键
		String primaryKey = StringUtils.removeStart(fieldName, AssetConstants.FIELD_PREFIX);
		value.setFieldId(Long.parseLong(primaryKey));
		//设置值
		if(fieldValue.getClass().isArray()) {
			String[] vals = (String[])fieldValue;
			value.setValue(Joiner.on(BaseConstants.SPLITER_FLAG).skipNulls().join(vals));
		} else {
			String val = (String)fieldValue;
			value.setValue(val);
		}
		return value;
	}
	
	/**
	 * 
	 * @param results
	 * @return
	 */
	public static List<AssetFieldValue> extractAssetField(Long deviceId, Map<String, Object> results) {
		List<AssetFieldValue> fields = Lists.newArrayList();
		//
		for(Map.Entry<String, Object> entry: results.entrySet()) {
			String key = entry.getKey();
			if(StringUtils.startsWith(key, AssetConstants.FIELD_PREFIX)) {
				AssetFieldValue value = AssetFieldValue.create(deviceId, key, entry.getValue());
				fields.add(value);
			}
		}
		return fields;
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
	 * @return the deviceId
	 */
	public Long getDeviceId() {
		return deviceId;
	}

	/**
	 * 
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * 
	 * @return the fieldId
	 */
	public Long getFieldId() {
		return fieldId;
	}

	/**
	 * 
	 * @param fieldId the fieldId to set
	 */
	public void setFieldId(Long fieldId) {
		this.fieldId = fieldId;
	}

	/**
	 * 
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * 
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((deviceId == null) ? 0 : deviceId.hashCode());
		result = prime * result + ((fieldId == null) ? 0 : fieldId.hashCode());
		return result;
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
		AssetFieldValue other = (AssetFieldValue) obj;
		if (deviceId == null) {
			if (other.deviceId != null)
				return false;
		} else if (!deviceId.equals(other.deviceId))
			return false;
		if (fieldId == null) {
			if (other.fieldId != null)
				return false;
		} else if (!fieldId.equals(other.fieldId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AssetFieldValue [id=" + id + ", deviceId=" + deviceId
				+ ", fieldId=" + fieldId + ", value=" + value + "]";
	}

}
