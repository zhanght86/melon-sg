/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.business.check.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.base.json.serializer.ShortDateSerializer;
import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 1.0 2014年10月14日,上午11:00:25
 * 安全检查表格注册,所有的安全检查表格都必须在此处注册
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name="T_CHECK_TABLE")
public class CheckTable extends BaseDomain<Long> {
	
	private static final long serialVersionUID = -6662588295214115084L;
	
	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_CHECK")
	private Long id;
	
	//标题
	@Column(name="TITLE", length=100)
	private String title;
	
	//颁发单位Id
	@Column(name="ISS_ORGANID")
	private Long issueOrganId;
	
	//颁发单位
	@Column(name="ISS_ORGAN", length=100)
	private String issueOrgan;
	
	//颁发时间
	@Temporal(TemporalType.DATE)
	@DateFormatter(DateTimeType.SHORT_DATE)
	@JsonSerialize(using=ShortDateSerializer.class)
	@Column(name="ISS_TIME")
	private Date issueTime;
	
	@Temporal(TemporalType.DATE)
	@DateFormatter(DateTimeType.SHORT_DATE)
	@JsonSerialize(using=ShortDateSerializer.class)
	@Column(name="START_TIME")
	private Date startTime;
	
	@Temporal(TemporalType.DATE)
	@DateFormatter(DateTimeType.SHORT_DATE)
	@JsonSerialize(using=ShortDateSerializer.class)
	@Column(name="END_TIME")
	private Date endTime;
	
	//引用附件
	@Column(name="BUSINESS_ID", length=40)
	@JsonIgnore
	private String businessId;
	
	//访问地址
	@Column(name="BASE_URL", length=100)
	@JsonIgnore
	private String baseUrl;
	
	//备注信息,用于提示用户填写的注意事项
	@Column(name="REMARKS", length=1000)
	@JsonIgnore
	private String remarks;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	
	public Long getIssueOrganId() {
		return issueOrganId;
	}

	public void setIssueOrganId(Long issueOrganId) {
		this.issueOrganId = issueOrganId;
	}

	public String getIssueOrgan() {
		return issueOrgan;
	}

	public void setIssueOrgan(String issueOrgan) {
		this.issueOrgan = issueOrgan;
	}

	public Date getIssueTime() {
		return issueTime;
	}

	public void setIssueTime(Date issueTime) {
		this.issueTime = issueTime;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}

	public String getBaseUrl() {
		return baseUrl;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((businessId == null) ? 0 : businessId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CheckTable other = (CheckTable) obj;
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
		return true;
	}

	@Override
	public String toString() {
		return "CheckTable [id=" + id + ", title=" + title + ", issueOrgan="
				+ issueOrgan + ", issueTime=" + issueTime + ", businessId="
				+ businessId + ", baseUrl=" + baseUrl + ", remarks=" + remarks
				+ "]";
	}

}
