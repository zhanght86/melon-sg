/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.datas.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.base.json.serializer.LongDateSerializer;
import secfox.soc.melon.persistence.BaseDomain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @since 1.0 2014年11月10日,下午7:25:54
 * 数据共享日志
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name="T_DATA_SHARE_LOGGER")
public class DataShareLogger extends BaseDomain<Long> {
	
	private static final long serialVersionUID = -8344500305691268468L;
	
	@Id@Column(name = "PK")
	@GeneratedValue(generator="GEN_SEQ_DATAS")
	private Long id;
	
	//数据共享的类型
	//1:设备对象,2:信息系统,3:安全人员,11:安全检查,21:告警,31:等级保护,41:知识库
	@Column(name = "TYPE")
	private int type;
	
	//业务主键
	@Column(name = "BUSINESS_ID")
	private Long businessId;
	
	//共享时间
	@Column(name = "SHARE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	@DateFormatter(DateTimeType.LONG_DATE)
	@JsonSerialize(using=LongDateSerializer.class)
	private Date shareTime;
	
	//单位ID
	@Column(name = "ORGAN_ID")
	private Long organId;
	
	//单位名称
	@Column(name = "ORGAN_NAME", length=50)
	private String organName;
	
	//同步结果
	@Column(name = "RESULT")
	private boolean result;

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
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the businessId
	 */
	public Long getBusinessId() {
		return businessId;
	}

	/**
	 * @param businessId the businessId to set
	 */
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	/**
	 * @return the shareTime
	 */
	public Date getShareTime() {
		return shareTime;
	}

	/**
	 * @param shareTime the shareTime to set
	 */
	public void setShareTime(Date shareTime) {
		this.shareTime = shareTime;
	}

	/**
	 * @return the organId
	 */
	public Long getOrganId() {
		return organId;
	}

	/**
	 * @param organId the organId to set
	 */
	public void setOrganId(Long organId) {
		this.organId = organId;
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
	 * @return the result
	 */
	public boolean isResult() {
		return result;
	}

	/**
	 * @param result the result to set
	 */
	public void setResult(boolean result) {
		this.result = result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((businessId == null) ? 0 : businessId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((organId == null) ? 0 : organId.hashCode());
		result = prime * result + type;
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
		DataShareLogger other = (DataShareLogger) obj;
		if (businessId == null) {
			if (other.businessId != null)
				return false;
		} else if (!businessId.equals(other.businessId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (organId == null) {
			if (other.organId != null)
				return false;
		} else if (!organId.equals(other.organId))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "DataShareLogger [id=" + id + ", type=" + type + ", businessId="
				+ businessId + ", shareTime=" + shareTime + ", organId="
				+ organId + ", organName=" + organName + ", result=" + result
				+ "]";
	}

}
