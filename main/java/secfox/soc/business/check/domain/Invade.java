package secfox.soc.business.check.domain;

import java.util.Date;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 1.0 2014-5-30,下午3:00:00
 * 入侵检查系统
 * @author  <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0 
 */
@Entity
@Table(name="T_SG_INVADE")
public class Invade extends BaseDomain<Long> {

private static final long serialVersionUID = -3936931832061489021L;
	
	//主键id
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PK")
	private Long id;
	
	// 标志位  1:路由器,2:交换机,3:防火墙
	@Column(name="TYPE")
	private int type;
	
	//填报任务id
	@Column(name="TASK_ID")
	private Long taskId;
	
	//病毒系统id
	@Column(name="INVADE_ID")
	private Long invadeId;

	//病毒系统名称
	@Column(name="INVADE_NAME" , length = 100)
	private String invadeName;
	
	//ip地址
	@Column(name="IPV4" , length = 20)
	private String ipv4;
	
    //运行状况
	@Column(name="RUN_DESC")
	private String runDesc;
	
	//系统名称
	@Column(name="SYS_NAME", length = 100)
	private String sysName;
	
	@Temporal(value=TemporalType.DATE)
	@DateFormatter(DateTimeType.SHORT_DATE)
	@Column(name="REPORT_TIME")
	private Date reportTime;
	//创建人
	@Embedded
	private UserInfo creator;
	
	//修改人
	@Embedded
	@AttributeOverrides(value={
			@AttributeOverride(name="username",column=@Column(name="MODIFIER_NAME",length=200)),
			@AttributeOverride(name="userId",column=@Column(name="MOFIFIER_ID")),
			@AttributeOverride(name="organId",column=@Column(name="MOFIFIER_ORGAN_ID")),
			@AttributeOverride(name="organName",column=@Column(name="MOFIFIER_ORGAN_NAME"))
	})
	private UserInfo modifier;
	
	//创建时间
	@Temporal(value=TemporalType.DATE)
	@DateFormatter(DateTimeType.SHORT_DATE)
	@Column(name="CREATE_TIME")
	private Date createTime;

	/**
	 * @return the ipv4
	 */
	public String getIpv4() {
		return ipv4;
	}

	/**
	 * @param ipv4 the ipv4 to set
	 */
	public void setIpv4(String ipv4) {
		this.ipv4 = ipv4;
	}

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
	 * @return the taskId
	 */
	public Long getTaskId() {
		return taskId;
	}

	/**
	 * @param taskId the taskId to set
	 */
	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}


	/**
	 * @return the invadeId
	 */
	public Long getInvadeId() {
		return invadeId;
	}

	/**
	 * @param invadeId the invadeId to set
	 */
	public void setInvadeId(Long invadeId) {
		this.invadeId = invadeId;
	}

	/**
	 * @return the invadeName
	 */
	public String getInvadeName() {
		return invadeName;
	}

	/**
	 * @param invadeName the invadeName to set
	 */
	public void setInvadeName(String invadeName) {
		this.invadeName = invadeName;
	}

	/**
	 * @return the runDesc
	 */
	public String getRunDesc() {
		return runDesc;
	}

	/**
	 * @param runDesc the runDesc to set
	 */
	public void setRunDesc(String runDesc) {
		this.runDesc = runDesc;
	}

	/**
	 * @return the sysName
	 */
	public String getSysName() {
		return sysName;
	}

	/**
	 * @param sysName the sysName to set
	 */
	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	/**
	 * @return the reportTime
	 */
	public Date getReportTime() {
		return reportTime;
	}

	/**
	 * @param reportTime the reportTime to set
	 */
	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
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
	 * @return the modifier
	 */
	public UserInfo getModifier() {
		return modifier;
	}

	/**
	 * @param modifier the modifier to set
	 */
	public void setModifier(UserInfo modifier) {
		this.modifier = modifier;
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


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result	+ ((taskId == null) ? 0 : taskId.hashCode());
		result = prime * result	+ ((invadeId == null) ? 0 : invadeId.hashCode());
		result = prime * result + ((invadeName == null) ? 0 : invadeName.hashCode());
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
		Invade other = (Invade) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (creator == null) {
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		if (invadeId == null) {
			if (other.invadeId != null)
				return false;
		} else if (!invadeId.equals(other.invadeId))
			return false;
		if (taskId == null) {
			if (other.taskId != null)
				return false;
		} else if (!taskId.equals(other.taskId))
			return false;
		if (reportTime == null) {
			if (other.reportTime != null)
				return false;
		} else if (!reportTime.equals(other.reportTime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InvadeForm [id=" + id + ", taskId=" + taskId + ", invadeId=" + invadeId
				+", reportTime =" + reportTime + "]";
	}


}

