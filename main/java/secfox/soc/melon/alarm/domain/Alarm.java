/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.alarm.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 1.0 2014年9月25日,上午11:45:29
 * 系统的核心,告警处理
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name = "T_ALARM")
public class Alarm extends BaseDomain<Long> {
	
	private static final long serialVersionUID = -615287338838769210L;

	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_ALARM")
	private Long id;
	
	//告警标题
	@Column(name="ALARM_TITLE",length=100)
	private String title;
	
	//告警分类 0:操作记录类,1:安全预警类,2:系统状态类,3:信息刺探类,4:恶意代码类
	//根据告警分类可给出具体的操作建议
	@Column(name="ALARM_TYPE")
	private Long type;
	
	//是否来自应急演练
	@Column(name="PRACTICE")
	private boolean practice;
	
	//响应级别
	@Column(name="RES_LEVEL")
	private int resLevel;
	
	//告警级别，分为5级,告警级别由事件级别与资产重要性决定
	@Column(name="ALARM_LEVEL")
	private int level;
	
	//原始告警级别/事件级别与
	@Column(name="EVENT_LEVEL")
	private int eventLevel;
	
	//设备重要性
	@Column(name="DEVICE_LEVEL")
	private int deviceLevel;
	
	//告警未处理的天数
	@Transient
	private int days;
	
	//发生时间
	@Column(name="ALARM_OCCURTIME")
	@Temporal(TemporalType.TIMESTAMP)
	@DateFormatter(DateTimeType.LONG_DATE)
	private Date occurTime;
	
	//告警IP
	@Column(name="DEVICE_IP",length=40)
	private String deviceIp;
	
	/**
	 * 设备ID
	 */
	@Column(name = "DEVICE_ID")
	private Long deviceId;
	
	/**
	 * 设备名称
	 */
	@Column(name = "DEVICE_NAME", length = 100)
	private String deviceName;
	
	/**
	 * 0:待确认,1:已忽略,2:待处理;3:处理中;4:已处理；
	 */
	//是否可以将所有类似的记录都统一改为某种状态,如将所有漏洞扫描待确认的告警改为已处理
	@Column(name="ALARM_STATE")
	private int state = 0;
	
	/**
	 * 告警来源 0:安管平台,1:安全监控,2:日志审计,3:漏洞扫描,4:关联分析,5:安全人员
	 */
	@Column(name="ALARM_FROM")
	private int from;
	
	/**
	 * 告警的原始ID
	 */
	@Column(name="SOURCE_ID")
	private String formId;
	
	//可以在原系统上访问的地址
	@Column(name="FROM_URL", length = 256)
	private String fromUrl;
	
	/**
	 * 告警所属单位ID
	 */
	@Column(name="ORGAN_ID")
	private Long organId;
	
	/**
	 * 告警所属单位
	 */
	@Column(name="ORGAN_NAME", length=100)
	private String organName;
	
	//流程处理ID
	@Column(name="PROC_ID")
	private Long processInstanceId;
	
	//额外的备注信息,例如原始日志等
	@Column(name="remarks", length=1000)
	private String remarks;
	
	/**
	 * 
	 * @return the practice
	 */
	public boolean isPractice() {
		return practice;
	}

	/**
	 * 
	 * @param practice the practice to set
	 */
	public void setPractice(boolean practice) {
		this.practice = practice;
	}

	/**
	 * 
	 * @return the eventLevel
	 */
	public int getEventLevel() {
		return eventLevel;
	}

	/**
	 * 
	 * @param eventLevel the eventLevel to set
	 */
	public void setEventLevel(int eventLevel) {
		this.eventLevel = eventLevel;
	}

	/**
	 * 
	 * @return the resLevel
	 */
	public int getResLevel() {
		return resLevel;
	}

	/**
	 * 
	 * @param resLevel the resLevel to set
	 */
	public void setResLevel(int resLevel) {
		this.resLevel = resLevel;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.Identifiable#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.Identifiable#setId(java.io.Serializable)
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * @return the deviceLevel
	 */
	public int getDeviceLevel() {
		return deviceLevel;
	}

	/**
	 * @param deviceLevel the deviceLevel to set
	 */
	public void setDeviceLevel(int deviceLevel) {
		this.deviceLevel = deviceLevel;
	}

	/**
	 * @return the type
	 */
	public Long getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Long type) {
		this.type = type;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the occurTime
	 */
	public Date getOccurTime() {
		return occurTime;
	}

	/**
	 * @param occurTime the occurTime to set
	 */
	public void setOccurTime(Date occurTime) {
		this.occurTime = occurTime;
	}

	/**
	 * @return the deviceIp
	 */
	public String getDeviceIp() {
		return deviceIp;
	}

	/**
	 * @param deviceIp the deviceIp to set
	 */
	public void setDeviceIp(String deviceIp) {
		this.deviceIp = deviceIp;
	}

	/**
	 * @return the deviceId
	 */
	public Long getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(Long deviceId) {
		this.deviceId = deviceId;
	}

	/**
	 * @return the deviceName
	 */
	public String getDeviceName() {
		return deviceName;
	}

	/**
	 * @param deviceName the deviceName to set
	 */
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}

	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	 * @return the from
	 */
	public int getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(int from) {
		this.from = from;
	}


	/**
	 * @return the processInstanceId
	 */
	public Long getProcessInstanceId() {
		return processInstanceId;
	}

	/**
	 * @param processInstanceId the processInstanceId to set
	 */
	public void setProcessInstanceId(Long processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	/**
	 * 
	 * @return the days
	 */
	public int getDays() {
		return days;
	}

	/**
	 * 
	 * @param days the days to set
	 */
	public void setDays(int days) {
		this.days = days;
	}

	/**
	 * 
	 * @return the formId
	 */
	public String getFormId() {
		return formId;
	}

	/**
	 * 
	 * @param formId the formId to set
	 */
	public void setFormId(String formId) {
		this.formId = formId;
	}

	/**
	 * 
	 * @return the fromUrl
	 */
	public String getFromUrl() {
		return fromUrl;
	}

	/**
	 * 
	 * @param fromUrl the fromUrl to set
	 */
	public void setFromUrl(String fromUrl) {
		this.fromUrl = fromUrl;
	}

	/**
	 * 
	 * @return the organId
	 */
	public Long getOrganId() {
		return organId;
	}

	/**
	 * 
	 * @param organId the organId to set
	 */
	public void setOrganId(Long organId) {
		this.organId = organId;
	}

	/**
	 * 
	 * @return the organName
	 */
	public String getOrganName() {
		return organName;
	}

	/**
	 * 
	 * @param organName the organName to set
	 */
	public void setOrganName(String organName) {
		this.organName = organName;
	}

	/**
	 * 
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * 
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((formId == null) ? 0 : formId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + level;
		result = prime * result + state;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Alarm other = (Alarm) obj;
		if (formId == null) {
			if (other.formId != null)
				return false;
		} else if (!formId.equals(other.formId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (level != other.level)
			return false;
		if (state != other.state)
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Alarm [id=" + id + ", title=" + title + ", type=" + type
				+ ", level=" + level + ", days=" + days + ", occurTime="
				+ occurTime + ", deviceIp=" + deviceIp + ", deviceId="
				+ deviceId + ", deviceName=" + deviceName + ", state=" + state
				+ ", from=" + from + ", formId=" + formId + ", fromUrl="
				+ fromUrl + ", organId=" + organId + ", organName=" + organName
				+ ", processInstanceId=" + processInstanceId + ", remarks="
				+ remarks + "]";
	}
	
}
