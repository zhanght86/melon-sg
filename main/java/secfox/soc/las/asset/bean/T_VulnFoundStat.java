package secfox.soc.las.asset.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import secfox.soc.melon.persistence.BaseDomain;

@Entity
@Table(name="T_VulnFoundStat")
public class T_VulnFoundStat extends BaseDomain<Long>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id@Column(name = "id", length = 20)
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;
	
	@Column(name = "deviceID", length = 22)
	private long deviceID;
	
	@Column(name = "calTime", length = 22)
	private long calTime;
	
	@Column(name = "severity", length = 11)
	private long severity;
	
	@Column(name = "tag", length = 11)
	private long tag;
	
	@Column(name = "status", length = 11)
	private long status;
	
	@Column(name = "cnt", length = 22)
	private long cnt;
	
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

	public long getDeviceID() {
		return deviceID;
	}

	public void setDeviceID(long ID) {
		this.deviceID = ID;
	}

	public long getCalTime() {
		return calTime;
	}

	public void setCalTime(long calTime) {
		this.calTime = calTime;
	}

	public long getSeverity() {
		return severity;
	}

	public void setSeverity(long severity) {
		this.severity = severity;
	}

	public long getTag() {
		return tag;
	}

	public void setTag(long tag) {
		this.tag = tag;
	}

	public long getStatus() {
		return status;
	}

	public void setStatus(long status) {
		this.status = status;
	}

	public long getCnt() {
		return cnt;
	}

	public void setCnt(long cnt) {
		this.cnt = cnt;
	}

}
