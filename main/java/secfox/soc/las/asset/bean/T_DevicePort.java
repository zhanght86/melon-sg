package secfox.soc.las.asset.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import secfox.soc.melon.persistence.BaseDomain;

@Entity
@Table(name = "T_DevicePort")
public class T_DevicePort extends BaseDomain<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id@Column(name = "id", length = 20)
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;
	
	@Column(name = "deviceID", length = 22)
	private long deviceID;
	
	@Column(name = "ip", length = 255)
	private String ip;
	
	@Column(name = "port", length = 11)
	private int port;
	
	@Column(name = "name", length = 128)
	private String name;
	
	@Column(name = "type", length = 32)
	private String type;
	
	@Column(name = "foundTime", length = 20)
	private long foundTime;
	
	@Column(name = "description", length = 255)
	private String description;
	
	@Column(name = "vulnID", length = 22)
	private long vulnID;
	
	@Column(name = "source", length = 4)
	private int source;

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

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getFoundTime() {
		return foundTime;
	}

	public void setFoundTime(long foundTime) {
		this.foundTime = foundTime;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public long getVulnID() {
		return vulnID;
	}

	public void setVulnID(long vulnID) {
		this.vulnID = vulnID;
	}

	public int getSource() {
		return source;
	}

	public void setSource(int source) {
		this.source = source;
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
		this.id=id;
	}
	
}
