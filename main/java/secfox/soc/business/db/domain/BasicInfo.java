/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.business.db.domain;

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
import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 1.0 2014年4月23日,下午2:48:08
 * 单位基本情况,是否可以为等保提供复制功能
 * 等保第一张表,直接与信息系统相联系
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name="T_DB_BASIC")
public class BasicInfo extends BaseDomain<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1814832391601906320L;
	
	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_DB")
	private Long id;
	
	//信息系统ID
	@Column(name="SYS_ID")
	private Long sysId;
	
	//创建时间
	@Column(name="CREATE_TIME")
	@DateFormatter(DateTimeType.LONG_DATE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	//创建人
	@Embedded
	private UserInfo creator;
	
	//01	单位名称
	@Column(name="ORG_NAME",length=100)
	private String organName;
	
	//02	单位地址
	@Column(name="ORGAN_ADDR",length=200)
	private String organAddr;
	
	//邮编编码
	@Column(name="POST_CODE", length=10)
	private String postCode;

	//行政区域代码
	@Column(name="ORGAN_CODE", length=20)
	private String organCode;
	
	//单位负责人 姓名
	@Column(name="CH_NAME",length=40)
	private String chName;
	
	//单位负责人 职务/职称
	@Column(name="CH_DUTY",length=30)
	private String chDuty;
	
	//单位负责人 电话
	@Column(name="CH_TEL",length=20)
	private String chTel;
	
	//单位负责人 Email
	@Column(name="CH_MAIL",length=40)
	private String chMail;
	
	//06	责任部门
	@Column(name="DEPART_NAME",length=40)
	private String departName;
	
	//责任部门联系人  姓名
	@Column(name="DE_NAME",length=40)
	private String deName;
	
	//责任部门联系人   职务/职称
	@Column(name="DE_DUTY",length=30)
	private String deDuty;
	
	//责任部门联系人  电话
	@Column(name="DE_TEL",length=20)
	private String deTel;
	
	//责任部门联系人  电话
	@Column(name="DE_PHONE",length=20)
	private String dePhone;
	
	//单位负责人 Email
	@Column(name="DE_MAIL",length=40)
	private String deMail;
	
	//08	隶属关系
	@Column(name="BE_LONG")
	private int belong;
	
	//09	单位类型
	@Column(name="ORGAN_TYPE")
	private int organType;
	
	//10	行业类别
	@Column(name="TRADE_TYPE")
	private int tradeType;

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
	 * @return the sysId
	 */
	public Long getSysId() {
		return sysId;
	}

	/**
	 * @param sysId the sysId to set
	 */
	public void setSysId(Long sysId) {
		this.sysId = sysId;
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
	 * @return the organName
	 */
	public String getOrganName() {
		return organName;
	}

	/**
	 * @param organName the organName to set
	 */
	public void setOrganName(String organName) {
		this.organName = organName;
	}

	/**
	 * @return the organAddr
	 */
	public String getOrganAddr() {
		return organAddr;
	}

	/**
	 * @param organAddr the organAddr to set
	 */
	public void setOrganAddr(String organAddr) {
		this.organAddr = organAddr;
	}

	/**
	 * @return the postCode
	 */
	public String getPostCode() {
		return postCode;
	}

	/**
	 * @param postCode the postCode to set
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	/**
	 * @return the organCode
	 */
	public String getOrganCode() {
		return organCode;
	}

	/**
	 * @param organCode the organCode to set
	 */
	public void setOrganCode(String organCode) {
		this.organCode = organCode;
	}

	/**
	 * @return the chName
	 */
	public String getChName() {
		return chName;
	}

	/**
	 * @param chName the chName to set
	 */
	public void setChName(String chName) {
		this.chName = chName;
	}

	/**
	 * @return the chDuty
	 */
	public String getChDuty() {
		return chDuty;
	}

	/**
	 * @param chDuty the chDuty to set
	 */
	public void setChDuty(String chDuty) {
		this.chDuty = chDuty;
	}

	/**
	 * @return the chTel
	 */
	public String getChTel() {
		return chTel;
	}

	/**
	 * @param chTel the chTel to set
	 */
	public void setChTel(String chTel) {
		this.chTel = chTel;
	}

	/**
	 * @return the chMail
	 */
	public String getChMail() {
		return chMail;
	}

	/**
	 * @param chMail the chMail to set
	 */
	public void setChMail(String chMail) {
		this.chMail = chMail;
	}

	/**
	 * @return the departName
	 */
	public String getDepartName() {
		return departName;
	}

	/**
	 * @param departName the departName to set
	 */
	public void setDepartName(String departName) {
		this.departName = departName;
	}

	/**
	 * @return the deName
	 */
	public String getDeName() {
		return deName;
	}

	/**
	 * @param deName the deName to set
	 */
	public void setDeName(String deName) {
		this.deName = deName;
	}

	/**
	 * @return the deDuty
	 */
	public String getDeDuty() {
		return deDuty;
	}

	/**
	 * @param deDuty the deDuty to set
	 */
	public void setDeDuty(String deDuty) {
		this.deDuty = deDuty;
	}

	/**
	 * @return the deTel
	 */
	public String getDeTel() {
		return deTel;
	}

	/**
	 * @param deTel the deTel to set
	 */
	public void setDeTel(String deTel) {
		this.deTel = deTel;
	}

	/**
	 * @return the dePhone
	 */
	public String getDePhone() {
		return dePhone;
	}

	/**
	 * @param dePhone the dePhone to set
	 */
	public void setDePhone(String dePhone) {
		this.dePhone = dePhone;
	}

	/**
	 * @return the deMail
	 */
	public String getDeMail() {
		return deMail;
	}

	/**
	 * @param deMail the deMail to set
	 */
	public void setDeMail(String deMail) {
		this.deMail = deMail;
	}

	/**
	 * @return the belong
	 */
	public int getBelong() {
		return belong;
	}

	/**
	 * @param belong the belong to set
	 */
	public void setBelong(int belong) {
		this.belong = belong;
	}

	/**
	 * @return the organType
	 */
	public int getOrganType() {
		return organType;
	}

	/**
	 * @param organType the organType to set
	 */
	public void setOrganType(int organType) {
		this.organType = organType;
	}

	/**
	 * @return the tradeType
	 */
	public int getTradeType() {
		return tradeType;
	}

	/**
	 * @param tradeType the tradeType to set
	 */
	public void setTradeType(int tradeType) {
		this.tradeType = tradeType;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((organName == null) ? 0 : organName.hashCode());
		result = prime * result + ((sysId == null) ? 0 : sysId.hashCode());
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
		BasicInfo other = (BasicInfo) obj;
		if (creator == null) {
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (organName == null) {
			if (other.organName != null)
				return false;
		} else if (!organName.equals(other.organName))
			return false;
		if (sysId == null) {
			if (other.sysId != null)
				return false;
		} else if (!sysId.equals(other.sysId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BasicInfo [id=" + id + ", sysId=" + sysId + ", createTime="
				+ createTime + ", creator=" + creator + ", organName="
				+ organName + ", organAddr=" + organAddr + ", postCode="
				+ postCode + ", organCode=" + organCode + ", chName=" + chName
				+ ", chDuty=" + chDuty + ", chTel=" + chTel + ", chMail="
				+ chMail + ", departName=" + departName + ", deName=" + deName
				+ ", deDuty=" + deDuty + ", deTel=" + deTel + ", dePhone="
				+ dePhone + ", deMail=" + deMail + ", belong=" + belong
				+ ", organType=" + organType + ", tradeType=" + tradeType + "]";
	}
	
}
