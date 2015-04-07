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
 * 
 * @since 1.0 2014年5月21日,下午2:30:35
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0
 */
@Entity
@Table(name="T_SG_ROUTER")
public class Router extends BaseDomain<Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8271856634373410768L;

	/**
     * 主键id
     */
	@Id@Column(name="PK")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	/**
	 * 专题任务id
	 */
	@Column(name="TASK_ID")
	private Long taskId;
	
	/**
	 * 上报时间
	 */
	@Temporal(value=TemporalType.DATE)
	@DateFormatter(DateTimeType.SHORT_DATE)
	@Column(name="REPORT_TIME")
	private Date reportTime;
	
	/**
	 * 标志位  1:路由器,2:交换机,3:防火墙
	 */
	@Column(name="TYPE")
	private int type;
	
	/**
	 * 设备id
	 */
	@Column(name="ROUTER_ID")
	private Long deviceId;
	
	/**
	 * 设备名称
	 */
	@Column(name="ROUTER_NAME")
	private String deviceName;
	
	/**
	 * 系统名称
	 */
	@Column(name="SYS_NAME")
	private String sysName;
	
	/**
	 * IP地址
	 */
	@Column(name="IP")
	private String ip;
	
	/**
	 * 运行状况
	 */
	@Column(name="RUN_DESC")
	private String runDesc;

	/**
	 * CPU利用率:5秒利用率（%）
	 */
	@Column(name="RATE1")
	private double rate1;
	
	/**
	 * CPU利用率:1分钟利用率（%）
	 */
	@Column(name="RATE2")
	private double rate2;
	
	/**
	 * CPU利用率:5分钟利用率（%）
	 */
	@Column(name="RATE3")
	private double rate3;
	
	/**
	 * 日志报警信息
	 */
	@Column(name="LOG_INFO")
	private String logInfo;
	
	/**
	 * 创建人
	 */
	@Embedded
	private UserInfo creator;
	
	/**
	 * 修改人
	 */
	@Embedded
	@AttributeOverrides(value={
			@AttributeOverride(name="username",column=@Column(name="MODIFIER_NAME",length=200)),
			@AttributeOverride(name="userId",column=@Column(name="MOFIFIER_ID")),
			@AttributeOverride(name="organId",column=@Column(name="MOFIFIER_ORGAN_ID")),
			@AttributeOverride(name="organName",column=@Column(name="MOFIFIER_ORGAN_NAME"))
	})
	private UserInfo modifier;
	
	/**
	 * 创建时间
	 */
	@Temporal(value=TemporalType.DATE)
	@DateFormatter(DateTimeType.SHORT_DATE)
	@Column(name="CREATE_TIME")
	private Date createTime;
	
	/**
	 *  修改时间
	 */
	@Temporal(value=TemporalType.DATE)
	@DateFormatter(DateTimeType.SHORT_DATE)
	@Column(name = "UPDATE_TIME")
	private Date updateTime;

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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		
		this.id = id;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Date getReportTime() {
		return reportTime;
	}

	public void setReportTime(Date reportTime) {
		this.reportTime = reportTime;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public Long getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	public String getDeviceName() {
		return deviceName;
	}

	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	public String getSysName() {
		return sysName;
	}

	public void setSysName(String sysName) {
		this.sysName = sysName;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRunDesc() {
		return runDesc;
	}

	public void setRunDesc(String runDesc) {
		this.runDesc = runDesc;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public double getRate1() {
		return rate1;
	}

	public void setRate1(double rate1) {
		this.rate1 = rate1;
	}

	public double getRate2() {
		return rate2;
	}

	public void setRate2(double rate2) {
		this.rate2 = rate2;
	}

	public double getRate3() {
		return rate3;
	}

	public void setRate3(double rate3) {
		this.rate3 = rate3;
	}

	public String getLogInfo() {
		return logInfo;
	}

	public void setLogInfo(String logInfo) {
		this.logInfo = logInfo;
	}

	public UserInfo getCreator() {
		return creator;
	}

	public void setCreator(UserInfo creator) {
		this.creator = creator;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result + ((creator == null) ? 0 : creator.hashCode());
		result = prime * result
				+ ((deviceId == null) ? 0 : deviceId.hashCode());
		result = prime * result
				+ ((deviceName == null) ? 0 : deviceName.hashCode());
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
		Router other = (Router) obj;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (creator == null) {
			if (other.creator != null)
				return false;
		} else if (!creator.equals(other.creator))
			return false;
		if (deviceId == null) {
			if (other.deviceId != null)
				return false;
		} else if (!deviceId.equals(other.deviceId))
			return false;
		if (deviceName == null) {
			if (other.deviceName != null)
				return false;
		} else if (!deviceName.equals(other.deviceName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "DeviceReportTask [id=" + id + ", taskId=" + taskId
				+ ", deviceId=" + deviceId + ", deviceName=" + deviceName + "]";
	}
	
}
