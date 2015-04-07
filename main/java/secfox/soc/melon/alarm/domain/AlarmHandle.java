/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.alarm.domain;

import java.util.Arrays;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.persistence.BaseDomain;

/**
 * 告警处理
 * @author  yb
 * @version  1.0 
 */
@Entity
@Table(name = "T_ALARM_HANDLE")
public class AlarmHandle extends BaseDomain<Long> {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;
	

	//处理意见 输入	Max：2000汉字	必填
	@Column(name="REMARK",length=2000)
	private String remark;
	
	//告警状态		1：待处理；2：清除；3、已处理；
	@Column(name="STATUS")
	private int status;
	
	//告警类别		1、预告警，2、告警	从alarm对象中获取
	@Column(name="TYPE")
	private int type;
	
	//告警ID	FK
	@Column(name="ALARM_ID")
	private Long alarmId;
	
	//责任人  从alarm对象中获取
	@Column(name="RESPONSIBLE_PERSON_NAME")
	private String responsiblePersonName;
	@Column(name="RESPONSIBLE_PERSON_ID")
	private Long responsiblePersonId;
	
	//处理方式、多选	1：工单；2：邮件；3：短信；
	@Column(name="HANDLE_TYPE")
	private String handleType;
	
    //处理时间
  	@Temporal(TemporalType.TIMESTAMP)
	@DateFormatter(DateTimeType.LONG_DATE)
    @Column(name="HANDLE_TIME")
    private Date handleTime;

    //处理人姓名
  	@Column(name="HANDLE_USER_NAME",length=20)
    private String handleUserName;

    //处理人Id
  	@Column(name="HANDLE_USER_ID")
    private Long handleUserId;

    //工单流程id
  	@Column(name="TASK_ID")
  	private Long taskId;

    
    //从页面上接收参数,不持久化
    @Transient
    private String[] handleTypes;
    
	public String[] getHandleTypes() {
		return handleTypes;
	}

	public void setHandleTypes(String[] handleTypes) {
		this.handleTypes = handleTypes;
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
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
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
	 * @return the alarmId
	 */
	public Long getAlarmId() {
		return alarmId;
	}

	/**
	 * @param alarmId the alarmId to set
	 */
	public void setAlarmId(Long alarmId) {
		this.alarmId = alarmId;
	}

	/**
	 * @return the responsiblePersonName
	 */
	public String getResponsiblePersonName() {
		return responsiblePersonName;
	}

	/**
	 * @param responsiblePersonName the responsiblePersonName to set
	 */
	public void setResponsiblePersonName(String responsiblePersonName) {
		this.responsiblePersonName = responsiblePersonName;
	}

	/**
	 * @return the responsiblePersonId
	 */
	public Long getResponsiblePersonId() {
		return responsiblePersonId;
	}

	/**
	 * @param responsiblePersonId the responsiblePersonId to set
	 */
	public void setResponsiblePersonId(Long responsiblePersonId) {
		this.responsiblePersonId = responsiblePersonId;
	}

	/**
	 * @return the handleType
	 */
	public String getHandleType() {
		return handleType;
	}

	/**
	 * @param handleType the handleType to set
	 */
	public void setHandleType(String handleType) {
		this.handleType = handleType;
	}

	/**
	 * @return the handleTime
	 */
	public Date getHandleTime() {
		return handleTime;
	}

	/**
	 * @param handleTime the handleTime to set
	 */
	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}

	/**
	 * @return the handleUserName
	 */
	public String getHandleUserName() {
		return handleUserName;
	}

	/**
	 * @param handleUserName the handleUserName to set
	 */
	public void setHandleUserName(String handleUserName) {
		this.handleUserName = handleUserName;
	}

	/**
	 * @return the handleUserId
	 */
	public Long getHandleUserId() {
		return handleUserId;
	}

	/**
	 * @param handleUserId the handleUserId to set
	 */
	public void setHandleUserId(Long handleUserId) {
		this.handleUserId = handleUserId;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((alarmId == null) ? 0 : alarmId.hashCode());
		result = prime * result
				+ ((handleTime == null) ? 0 : handleTime.hashCode());
		result = prime * result
				+ ((handleType == null) ? 0 : handleType.hashCode());
		result = prime * result + Arrays.hashCode(handleTypes);
		result = prime * result
				+ ((handleUserId == null) ? 0 : handleUserId.hashCode());
		result = prime * result
				+ ((handleUserName == null) ? 0 : handleUserName.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((remark == null) ? 0 : remark.hashCode());
		result = prime
				* result
				+ ((responsiblePersonId == null) ? 0 : responsiblePersonId
						.hashCode());
		result = prime
				* result
				+ ((responsiblePersonName == null) ? 0 : responsiblePersonName
						.hashCode());
		result = prime * result + status;
		result = prime * result + ((taskId == null) ? 0 : taskId.hashCode());
		result = prime * result + type;
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
		AlarmHandle other = (AlarmHandle) obj;
		if (alarmId == null) {
			if (other.alarmId != null)
				return false;
		} else if (!alarmId.equals(other.alarmId))
			return false;
		if (handleTime == null) {
			if (other.handleTime != null)
				return false;
		} else if (!handleTime.equals(other.handleTime))
			return false;
		if (handleType == null) {
			if (other.handleType != null)
				return false;
		} else if (!handleType.equals(other.handleType))
			return false;
		if (!Arrays.equals(handleTypes, other.handleTypes))
			return false;
		if (handleUserId == null) {
			if (other.handleUserId != null)
				return false;
		} else if (!handleUserId.equals(other.handleUserId))
			return false;
		if (handleUserName == null) {
			if (other.handleUserName != null)
				return false;
		} else if (!handleUserName.equals(other.handleUserName))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (remark == null) {
			if (other.remark != null)
				return false;
		} else if (!remark.equals(other.remark))
			return false;
		if (responsiblePersonId == null) {
			if (other.responsiblePersonId != null)
				return false;
		} else if (!responsiblePersonId.equals(other.responsiblePersonId))
			return false;
		if (responsiblePersonName == null) {
			if (other.responsiblePersonName != null)
				return false;
		} else if (!responsiblePersonName.equals(other.responsiblePersonName))
			return false;
		if (status != other.status)
			return false;
		if (taskId == null) {
			if (other.taskId != null)
				return false;
		} else if (!taskId.equals(other.taskId))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "AlarmHandle [id=" + id + ", remark=" + remark + ", status="
				+ status + ", type=" + type + ", alarmId=" + alarmId
				+ ", responsiblePersonName=" + responsiblePersonName
				+ ", responsiblePersonId=" + responsiblePersonId
				+ ", handleType=" + handleType + ", handleTime=" + handleTime
				+ ", handleUserName=" + handleUserName + ", handleUserId="
				+ handleUserId + ", taskId=" + taskId + ", handleTypes="
				+ Arrays.toString(handleTypes) + "]";
	}


}
