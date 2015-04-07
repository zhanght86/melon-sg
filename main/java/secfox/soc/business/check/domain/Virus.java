/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
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
 * @since 1.0 2014年5月21日,下午4:08:25
 * 病毒
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name="T_SG_VIRUS")
public class Virus extends BaseDomain<Long> {
	
	private static final long serialVersionUID = -3936931832061489021L;
	
	//主键id
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PK")
	private Long id;
	
	//1路由器，2交换机，3病毒入侵
	@Column(name="TYPE")
	private int type;
	
	//填报任务id
	@Column(name="TASK_ID")
	private Long taskId;
	
	/*@Transient
	List<EventLevelReport> events ;
	
	
	@Transient
	List<TopVirusReport> tops ;*/
	
	
	//病毒系统id
	@Column(name="AV_ID")
	private Long avId;

	//病毒系统名称
	@Column(name="AV_Name" , length = 100)
	private String avName;
	
	//ip地址
	@Column(name="IPV4" , length = 20)
	private String ipv4;
	
    //监控中心运行秦高
	@Column(name="NORMAL")
	private boolean normal;
	
	//病毒库升级情况
	@Column(name="UPDATED")
	private boolean updated;

	//系统名称
	@Column(name="SYS_NAME", length = 100)
	private String sysName;
	
	//本日病毒数量
	@Column(name="DAILY_AV_NUM")
	private int dailyAvNum;
	
	//前一日病毒数量
	@Column(name="LAST_AV_NUM")
	private int lastAvNum;
	
	//每日增幅
	@Column(name="DAILY_INCRE")
	private double dailyIncre;
	
	
	@Temporal(value=TemporalType.DATE)
	@DateFormatter(DateTimeType.SHORT_DATE)
	@Column(name="REPORT_TIME")
	private Date reportTime;
	/**
	 * @return the dailyAvNum
	 */
	public int getDailyAvNum() {
		return dailyAvNum;
	}

	/**
	 * @param dailyAvNum the dailyAvNum to set
	 */
	public void setDailyAvNum(int dailyAvNum) {
		this.dailyAvNum = dailyAvNum;
	}

	/**
	 * @return the lastAvNum
	 */
	public int getLastAvNum() {
		return lastAvNum;
	}

	/**
	 * @param lastAvNum the lastAvNum to set
	 */
	public void setLastAvNum(int lastAvNum) {
		this.lastAvNum = lastAvNum;
	}

	/**
	 * @return the dailyIncre
	 */
	public double getDailyIncre() {
		return dailyIncre;
	}

	/**
	 * @param dailyIncre the dailyIncre to set
	 */
	public void setDailyIncre(double dailyIncre) {
		this.dailyIncre = dailyIncre;
	}


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

	// 修改时间
	@Temporal(value=TemporalType.DATE)
	@DateFormatter(DateTimeType.SHORT_DATE)
	@Column(name = "UPDATE_TIME")
	private Date updateTime;
	

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
	 * @return the avId
	 */
	public Long getAvId() {
		return avId;
	}

	/**
	 * @param avId the avId to set
	 */
	public void setAvId(Long avId) {
		this.avId = avId;
	}

	/**
	 * @return the avName
	 */
	public String getAvName() {
		return avName;
	}

	/**
	 * @param avName the avName to set
	 */
	public void setAvName(String avName) {
		this.avName = avName;
	}

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
	 * @return the normal
	 */
	public boolean isNormal() {
		return normal;
	}

	/**
	 * @param normal the normal to set
	 */
	public void setNormal(boolean normal) {
		this.normal = normal;
	}

	/**
	 * @return the updated
	 */
	public boolean isUpdated() {
		return updated;
	}

	/**
	 * @param updated the updated to set
	 */
	public void setUpdated(boolean updated) {
		this.updated = updated;
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
	/**
	 * @return the updateTime
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result	+ ((taskId == null) ? 0 : taskId.hashCode());
		result = prime * result	+ ((avId == null) ? 0 : avId.hashCode());
		result = prime * result + ((avName == null) ? 0 : avName.hashCode());
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
		Virus other = (Virus) obj;
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
		if (avId == null) {
			if (other.avId != null)
				return false;
		} else if (!avId.equals(other.avId))
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
		return "VirusForm [id=" + id + ", taskId=" + taskId + ", avId=" + avId
				+", reportTime =" + reportTime + "]";
	}


}
