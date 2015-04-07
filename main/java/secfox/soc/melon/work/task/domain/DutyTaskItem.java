/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.work.task.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import secfox.soc.melon.base.json.Dictionary;
import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 1.0 2014年10月31日,下午6:34:42
 * 岗位规定的工作
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name="T_WORK_TASK_ITEM")
public class DutyTaskItem extends BaseDomain<Long> {

	private static final long serialVersionUID = -5852184762954717172L;

	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;
	
	//任务名称,与功能挂钩
	@Column(name="T_NAME")
	private String name;
		
	//任务类型,日,周,月,年
	@Column(name="T_TIME_TYPE")
	private int timeType;
	
	//任务编码,与功能挂钩
	@Column(name="T_TASK_CODE")
	private Long code;
	
	//任务名称,与功能挂钩
	@Column(name="T_TASK_NAME")
	private String taskName;
	
	//比较符,小于,大于,等于,每天设备巡检次数不少于3次,达到,超过
	@Column(name="T_OPERATOR")
	private int operator;
	
	//必须要完成几次,例如每天必须完成三次资产巡检,达标值,代办任务完成率达到100%,告警处理率达到56%
	@Column(name="T_PASSED")
	private double passed;
	
	//是否必须
	@Column(name="T_REQUIRED")
	private boolean required;
	
	@Column(name="T_ORDER")
	private int order;

	@ManyToMany(fetch = FetchType.LAZY, targetEntity=DutyTaskGroup.class,mappedBy="tasks")
	@JsonIgnore
	private List<DutyTaskGroup> groups;
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
	 * @return the timeType
	 */
	@Dictionary("taskItem.type")
	public int getTimeType() {
		return timeType;
	}

	/**
	 * @param timeType the timeType to set
	 */
	public void setTimeType(int timeType) {
		this.timeType = timeType;
	}

	/**
	 * @return the code
	 */
	public Long getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(Long code) {
		this.code = code;
	}

	/**
	 * @return the operator
	 */
	public int getOperator() {
		return operator;
	}

	/**
	 * @param operator the operator to set
	 */
	public void setOperator(int operator) {
		this.operator = operator;
	}

	/**
	 * @return the passed
	 */
	public double getPassed() {
		return passed;
	}

	/**
	 * @param passed the passed to set
	 */
	public void setPassed(double passed) {
		this.passed = passed;
	}

	/**
	 * @return the required
	 */
	@Dictionary("bool")
	public boolean isRequired() {
		return required;
	}

	/**
	 * @param required the required to set
	 */
	public void setRequired(boolean required) {
		this.required = required;
	}

	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the groups
	 */
	public List<DutyTaskGroup> getGroups() {
		return groups;
	}

	/**
	 * @param groups the groups to set
	 */
	public void setGroups(List<DutyTaskGroup> groups) {
		this.groups = groups;
	}

	/**
	 * @return the taskName
	 */
	public String getTaskName() {
		return taskName;
	}

	/**
	 * @param taskName the taskName to set
	 */
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 * @author <a href="mailto:gaobg@legendsec.com">高保国</a>
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((groups == null) ? 0 : groups.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + operator;
		result = prime * result + order;
		long temp;
		temp = Double.doubleToLongBits(passed);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (required ? 1231 : 1237);
		result = prime * result
				+ ((taskName == null) ? 0 : taskName.hashCode());
		result = prime * result + timeType;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 * @author <a href="mailto:gaobg@legendsec.com">高保国</a>
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DutyTaskItem other = (DutyTaskItem) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (groups == null) {
			if (other.groups != null)
				return false;
		} else if (!groups.equals(other.groups))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (operator != other.operator)
			return false;
		if (order != other.order)
			return false;
		if (Double.doubleToLongBits(passed) != Double
				.doubleToLongBits(other.passed))
			return false;
		if (required != other.required)
			return false;
		if (taskName == null) {
			if (other.taskName != null)
				return false;
		} else if (!taskName.equals(other.taskName))
			return false;
		if (timeType != other.timeType)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * @author <a href="mailto:gaobg@legendsec.com">高保国</a>
	 */
	@Override
	public String toString() {
		return "DutyTaskItem [id=" + id + ", name=" + name + ", timeType="
				+ timeType + ", code=" + code + ", taskName=" + taskName
				+ ", operator=" + operator + ", passed=" + passed
				+ ", required=" + required + ", order=" + order + ", groups="
				+ groups + "]";
	}

	
}
