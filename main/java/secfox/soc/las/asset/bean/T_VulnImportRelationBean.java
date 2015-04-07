package secfox.soc.las.asset.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import secfox.soc.melon.persistence.BaseDomain;

@Entity
@Table(name = "T_VulnImportRelation")
public class T_VulnImportRelationBean extends BaseDomain<Long>{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;
	
	@Column(name = "importID", length = 25)
	private long importID;
	
	@Column(name = "deviceID", length = 25)
	private long deviceID;
	
	@Column(name = "ip", length = 255)
	private String ip;
	
	@Column(name = "importTime", length = 25)
	private long importTime;
	
	@Column(name = "vulnCount", length = 25)
	private int vulnCount;
	
	@Column(name = "portCount", length = 25)
	private int portCount;
	
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

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}

	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		this.id = id;
	}

	public long getImportID() {
		return importID;
	}

	public void setImportID(long importID) {
		this.importID = importID;
	}

	public long getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(long deviceID) {
		this.deviceID = deviceID;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public long getImportTime() {
		return importTime;
	}

	public void setImportTime(long importTime) {
		this.importTime = importTime;
	}

	public int getVulnCount() {
		return vulnCount;
	}

	public void setVulnCount(int vulnCount) {
		this.vulnCount = vulnCount;
	}

	public int getPortCount() {
		return portCount;
	}

	public void setPortCount(int portCount) {
		this.portCount = portCount;
	}
}
