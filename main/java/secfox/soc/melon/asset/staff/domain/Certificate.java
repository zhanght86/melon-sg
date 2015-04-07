package secfox.soc.melon.asset.staff.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.base.json.deserializer.ShortDateDeserializer;
import secfox.soc.melon.base.json.serializer.ShortDateSerializer;
import secfox.soc.melon.persistence.BaseDomain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @since 1.0 2014-12-3,上午11:13:18
 * 
 * @author  <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0 
 */

@Entity
@Table(name="T_STAFF_CERTIFICATE")
@NamedQueries({
	//查看所有证书
	@NamedQuery(name="certify.findByStaffId", query="select a from Certificate a where a.staffId = :staffId"),
	//查看所有证书
	@NamedQuery(name="certify.findByName", query="select a from Certificate a where a.certificate =:certificate and a.staffId =:staffId")
})
public class Certificate extends BaseDomain<Long> {


	private static final long serialVersionUID = -1L;
	
	@Id
	@Column(name = "PK")
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;
	
	@Column(name="STAFF_ID")
	private Long staffId;

	//证书
	@Column(name="STAFF_CERT")
	private String certificate;
	
	//附件
	@Column(name="STAFF_CERTBUSS")
	private String certificateBusiness;
	
	//开始时间
	@Column(name="CERT_STARTDATE")
	@DateFormatter(DateTimeType.SHORT_DATE)
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = ShortDateSerializer.class)
	@JsonDeserialize(using = ShortDateDeserializer.class)
	private Date startDate;
	
	//结束时间
	@Column(name="CERT_ENDDATE")
	@DateFormatter(DateTimeType.SHORT_DATE)
	@Temporal(TemporalType.DATE)
	@JsonSerialize(using = ShortDateSerializer.class)
	@JsonDeserialize(using = ShortDateDeserializer.class)
	private Date endDate;
	
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getCertificate() {
		return certificate;
	}

	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}

	public String getCertificateBusiness() {
		return certificateBusiness;
	}

	public void setCertificateBusiness(String certificateBusiness) {
		this.certificateBusiness = certificateBusiness;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public Long getStaffId() {
		return staffId;
	}

	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((certificate == null) ? 0 : certificate.hashCode());
		result = prime
				* result
				+ ((certificateBusiness == null) ? 0 : certificateBusiness
						.hashCode());
		result = prime * result + ((endDate == null) ? 0 : endDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((startDate == null) ? 0 : startDate.hashCode());
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
		Certificate other = (Certificate) obj;
		if (certificate == null) {
			if (other.certificate != null)
				return false;
		} else if (!certificate.equals(other.certificate))
			return false;
		if (certificateBusiness == null) {
			if (other.certificateBusiness != null)
				return false;
		} else if (!certificateBusiness.equals(other.certificateBusiness))
			return false;
		if (endDate == null) {
			if (other.endDate != null)
				return false;
		} else if (!endDate.equals(other.endDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		return true;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Certificate [id=" + id + ", certificate=" + certificate
				+ ", certificateBusiness=" + certificateBusiness
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

}

