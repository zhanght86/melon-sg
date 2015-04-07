/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.las.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import secfox.soc.las.domain.AlertRule;
import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.base.json.Dictionary;
import secfox.soc.melon.base.json.serializer.ShortDateSerializer;
import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 2015年2月2日,下午2:35:25
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */

@Entity
@Table(name = "T_Las_Rule")
public class LasRule extends BaseDomain<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 311910857827361842L;
	
	@Id@Column(name="ID")
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;
	
	//规则名称
	@Column(name="NAME", length = 125)
	private String name;
	
	//规则类型ID
	@Column(name="TYPEID")
	private Long typeId;
	
	//规则类型
	@Column(name="TYPE", length = 125)
	private String type;
	
	//是否启用
	@Column(name="ENABLED")
	private int enabled;
	
	//是否阻断
	//启用阻断后，排序在其后的规则器将不会执行，能显著提高执行效率
	@Column(name="LAST")
	private int last;
	
	//排序
	@Column(name="ORDERS")
	private int order;
	
	//创建时间
	@Column(name="CREATETIME")
	@Temporal(TemporalType.DATE)
	@DateFormatter(DateTimeType.SHORT_DATE)
	@JsonSerialize(using=ShortDateSerializer.class)
	private Date createTime;
	
	//创建人
	@Embedded
	private UserInfo creator;
	
	//描述信息
	//描述用途及作用，500字
	@Column(name="REMARKS", length = 256)
	private String remarks;
	
	@Transient
	private AlertRule alertRule;
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.Identifiable#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return the typeId
	 */
	public Long getTypeId() {
		return typeId;
	}

	/**
	 * @param typeId the typeId to set
	 */
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the enabled
	 */
	@Dictionary("enableds")
	public int getEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(int enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the last
	 */
	public int isLast() {
		return last;
	}

	/**
	 * @param last the last to set
	 */
	public void setLast(int last) {
		this.last = last;
	}

	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * @return the last
	 */
	public int getLast() {
		return last;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the creator
	 */
	public UserInfo getCreator() {
		return creator;
	}

	/**
	 * @param creator the creator to set
	 */
	public void setCreator(UserInfo creator) {
		this.creator = creator;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}


	/**
	 * @return the alertRule
	 */
	public AlertRule getAlertRule() {
		return alertRule;
	}

	/**
	 * @param alertRule the alertRule to set
	 */
	public void setAlertRule(AlertRule alertRule) {
		this.alertRule = alertRule;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + enabled;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + order;
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
		LasRule other = (LasRule) obj;
		if (enabled != other.enabled)
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "LasRule [id=" + id + ", name=" + name + ", enabled=" + enabled
				+ ", order=" + order + "]";
	}

}
