/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.base.json.deserializer.ShortDateDeserializer;
import secfox.soc.melon.persistence.BaseDomain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * 筛选设备，为设备到期提醒提供数据
 * @since 1.0 2014年11月18日下午7:14:42
 * @author <a href="mailto:huangxm@legendsec.com">黄晓梅</a>
 * @version 1.0
 */
@Entity
@Table(name="T_DEVICE_FILTER")
public class DeviceFilter extends BaseDomain<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//提醒工单ID
	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_ASSET")
	private Long id;
	
	//提醒工单名称
	@Column(name="ORDER_NAME", length=80)
	private String orderName;
	
	//查询条件-组织机构
	//所属单位
    @Column(name="COMPANY_ID")
    private Long companyId;
    
  //查询条件-组织机构
	@Column(name="COMPANY_NAME",length=100)
	private String companyName;
	
	//保修日期开始日期
	@Column(name="START_TIME")
	@Temporal(TemporalType.DATE)
	@DateFormatter(DateTimeType.SHORT_DATE)
	@JsonDeserialize(using=ShortDateDeserializer.class)
	private Date startTime;
	
	//保修期结束时间
	@Column(name="END_TIME")
	@Temporal(TemporalType.DATE)
	@DateFormatter(DateTimeType.SHORT_DATE)
	@JsonDeserialize(using=ShortDateDeserializer.class)
	private Date endTime;
	
	//设备责任人ID
	@Column(name="CHARGER_IDS", length=2000)
	private String chargeIds;
	
	//设备责任人
	@Column(name="CHARGE_NAMES", length=2000)
	private String chargeNames;
	
	//类型id
	@Column(name = "TYPE_IDS", length=2000)
	private String typeIds;
		
	//设备类型
	@Column(name="TYPE_NAMES", length=2000)
	private String typeNames;
	
//	//负责人ids
//	@Transient
//	private Long[] chargeIds;
//	
//	//负责人names
//	@Transient
//	private String[] chargeNames;
//
//	//设备类型ids
//	@Transient
//	private Long[] typeIds;
//	
//	//设备类型names
//	@Transient
//	private String[] typeNames;
//	
	
	//责任人与设备类型关系 0:与，1:或
	@Column(name="RELATION")
	private int relation;
	
	//到期提醒时间
	@Column(name="REMIND_TIME")
	@Temporal(TemporalType.DATE)
	@DateFormatter(DateTimeType.SHORT_DATE)
	@JsonDeserialize(using=ShortDateDeserializer.class)
	private Date remindTime;
	
	//创建人
	@Embedded
	private UserInfo creator;

	/**
	 * @return the id
	 */
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
	 * @return the orderName
	 */
	public String getOrderName() {
		return orderName;
	}

	/**
	 * @param orderName the orderName to set
	 */
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	/**
	 * @return the companyId
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	
	/**
	 * @return the chargeIds
	 */
	public String getChargeIds() {
		return chargeIds;
	}

	/**
	 * @param chargeIds the chargeIds to set
	 */
	public void setChargeIds(String chargeIds) {
		this.chargeIds = chargeIds;
	}

	/**
	 * @return the chargeNames
	 */
	public String getChargeNames() {
		return chargeNames;
	}

	/**
	 * @param chargeNames the chargeNames to set
	 */
	public void setChargeNames(String chargeNames) {
		this.chargeNames = chargeNames;
	}

	/**
	 * @return the typeIds
	 */
	public String getTypeIds() {
		return typeIds;
	}

	/**
	 * @param typeIds the typeIds to set
	 */
	public void setTypeIds(String typeIds) {
		this.typeIds = typeIds;
	}

	/**
	 * @return the typeNames
	 */
	public String getTypeNames() {
		return typeNames;
	}

	/**
	 * @param typeNames the typeNames to set
	 */
	public void setTypeNames(String typeNames) {
		this.typeNames = typeNames;
	}

	/**
	 * @return the relation
	 */
	public int getRelation() {
		return relation;
	}

	/**
	 * @param relation the relation to set
	 */
	public void setRelation(int relation) {
		this.relation = relation;
	}

	/**
	 * @return the remindTime
	 */
	public Date getRemindTime() {
		return remindTime;
	}

	/**
	 * @param remindTime the remindTime to set
	 */
	public void setRemindTime(Date remindTime) {
		this.remindTime = remindTime;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((chargeIds == null) ? 0 : chargeIds.hashCode());
		result = prime * result
				+ ((companyId == null) ? 0 : companyId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((typeIds == null) ? 0 : typeIds.hashCode());
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
		DeviceFilter other = (DeviceFilter) obj;
		if (chargeIds == null) {
			if (other.chargeIds != null)
				return false;
		} else if (!chargeIds.equals(other.chargeIds))
			return false;
		if (companyId == null) {
			if (other.companyId != null)
				return false;
		} else if (!companyId.equals(other.companyId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (typeIds == null) {
			if (other.typeIds != null)
				return false;
		} else if (!typeIds.equals(other.typeIds))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DeviceFilter [id=" + id + ", orderName=" + orderName
				+ ", companyId=" + companyId + ", companyName=" + companyName
				+ ", startTime=" + startTime + ", endTime=" + endTime
				+ ", chargeIds=" + chargeIds + ", chargeNames=" + chargeNames
				+ ", typeIds=" + typeIds + ", typeNames=" + typeNames
				+ ", relation=" + relation + ", remindTime=" + remindTime
				+ ", creator=" + creator + "]";
	}
}

