package secfox.soc.las.asset.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import secfox.soc.melon.persistence.BaseDomain;

@Entity
@Table(name="T_VulnFoundValue")
public class T_VulnFoundValue extends BaseDomain<Long>{
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//	  `Ext` text,
	@Id@Column(name = "id", length = 20)
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;
	
	@Column(name = "deviceID", length = 22)
	private long deviceID;
	
	@Column(name = "calTime", length = 22)
	private long calTime;
	
	@Column(name = "vulnValue", length = 11)
	private double vulnValue;
	
	@Column(name = "calRecords", length = 20)
	private int calRecords;
	
	@Column(name = "ext", length = 255)
	private String ext;

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

	public long getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(long deviceID) {
		this.deviceID = deviceID;
	}

	public long getCalTime() {
		return calTime;
	}

	public void setCalTime(long calTime) {
		this.calTime = calTime;
	}

	public double getVulnValue() {
		return vulnValue;
	}

	public void setVulnValue(double vulnValue) {
		this.vulnValue = vulnValue;
	}

	public int getCalRecords() {
		return calRecords;
	}

	public void setCalRecords(int calRecords) {
		this.calRecords = calRecords;
	}

	public String getExt() {
		return ext;
	}

	public void setExt(String ext) {
		this.ext = ext;
	}
	
}
