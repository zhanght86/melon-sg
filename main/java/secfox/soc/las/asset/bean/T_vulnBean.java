package secfox.soc.las.asset.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import secfox.soc.melon.persistence.BaseDomain;

@Entity
@Table(name = "T_vuln")
public class T_vulnBean extends BaseDomain<Long> {

	private static final long serialVersionUID = 1L;

	@Id@Column(name="ID")
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;
	
	@Column(name = "OriginalID", length = 256)
	private String originalID;
	
	@Column(name = "Code", length = 128)
	private String code;
	
	@Column(name = "Vendor", length = 256)
	private String vendor;
	
	@Column(name = "VendorCode", length = 128)
	private String vendorCode;
	
	@Column(name = "Name", length = 128)
	private String name;
	
	@Column(name = "Severity", length = 11)
	private int severity;
	
	@Column(name = "OriSeverity", length = 255)
	private String oriSeverity;
	
	@Column(name = "Description", length = 255)
	private String description;
	
	@Column(name = "Remedy", length = 255)
	private String remedy;
	
	@Column(name = "VulnType", length = 2)
	private int vulnType;
	
	@Column(name = "CVE", length = 255)
	private String cVE;
	
	@Column(name = "BUGTRAQ_ID", length = 255)
	private String bUGTRAQ_ID;
	
	@Column(name = "ExtensionMsg", length = 255)
	private String extensionMsg;
	
	@Column(name = "CreatedTime", length = 255)
	private long createdTime;
	
	@Column(name = "ifSystem", length = 11)
	private int ifSystem;
	
	

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}




	public String getOriginalID() {
		return originalID;
	}



	public void setOriginalID(String originalID) {
		this.originalID = originalID;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getVendor() {
		return vendor;
	}



	public void setVendor(String vendor) {
		this.vendor = vendor;
	}



	public String getVendorCode() {
		return vendorCode;
	}



	public void setVendorCode(String vendorCode) {
		this.vendorCode = vendorCode;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getSeverity() {
		return severity;
	}



	public void setSeverity(int severity) {
		this.severity = severity;
	}



	public String getOriSeverity() {
		return oriSeverity;
	}



	public void setOriSeverity(String oriSeverity) {
		this.oriSeverity = oriSeverity;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public String getRemedy() {
		return remedy;
	}



	public void setRemedy(String remedy) {
		this.remedy = remedy;
	}



	public int getVulnType() {
		return vulnType;
	}



	public void setVulnType(int vulnType) {
		this.vulnType = vulnType;
	}



	public String getCVE() {
		return cVE;
	}



	public void setCVE(String cve) {
		cVE = cve;
	}



	public String getBUGTRAQ_ID() {
		return bUGTRAQ_ID;
	}



	public void setBUGTRAQ_ID(String bugtraq_id) {
		bUGTRAQ_ID = bugtraq_id;
	}



	public String getExtensionMsg() {
		return extensionMsg;
	}



	public void setExtensionMsg(String extensionMsg) {
		this.extensionMsg = extensionMsg;
	}



	public long getCreatedTime() {
		return createdTime;
	}



	public void setCreatedTime(long createdTime) {
		this.createdTime = createdTime;
	}



	public int getIfSystem() {
		return ifSystem;
	}



	public void setIfSystem(int ifSystem) {
		this.ifSystem = ifSystem;
	}



	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}



	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

}
