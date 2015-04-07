package secfox.soc.las.asset.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import secfox.soc.melon.persistence.BaseDomain;

@Entity
@Table(name = "T_VulnImport")
public class T_VulnImport extends BaseDomain<Long>{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_APP")
	private long id;
	
	@Column(name = "importTime", length = 256)
    private long importTime;
	
	@Column(name = "taskID", length = 256)
    private long taskID;
	
	@Column(name = "scanCount", length = 26)
    private int scanCount;
	
	@Column(name = "taskName", length = 256)
    private String taskName;
	
	@Column(name = "startTime", length = 256)
    private long startTime;
	
	@Column(name = "endTime", length = 256)
    private long endTime;
	
	public long getImportTime() {
		return importTime;
	}
	public void setImportTime(long importTime) {
		this.importTime = importTime;
	}
	public long getTaskID() {
		return taskID;
	}
	public void setTaskID(long taskID) {
		this.taskID = taskID;
	}
	public int getScanCount() {
		return scanCount;
	}
	public void setScanCount(int scanCount) {
		this.scanCount = scanCount;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	public long getEndTime() {
		return endTime;
	}
	public void setEndTime(long endTime) {
		this.endTime = endTime;
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
		this.id = id;
	}
}
