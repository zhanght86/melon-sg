/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.work.plan.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.base.json.deserializer.ShortDateDeserializer;
import secfox.soc.melon.base.json.serializer.ShortDateSerializer;
import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 1.0 2014年10月23日,下午7:37:36
 * 计划,
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name = "T_PLAN")
@NamedQueries({
	@NamedQuery(name="plan.find", query="select plan from Plan plan where plan.creator.userId = :userId and (plan.start between :start and :end "
			+"or plan.end between :start and :end "
			+"or (plan.start <= :start and plan.end >= :end) )")
})
public class Plan extends BaseDomain<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4726034321397856168L;
	
	@Id
	@Column(name = "PK")
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;
	
	//是否为全天事件  全天事件，则结束时间包括在内；不是全天事件，则结束时间不包括在内
	@Column(name="P_ALLDAY")
	private boolean allDay = true;
	
	//计划开始时间
	@Column(name="P_STARTDATE")
	@Temporal(TemporalType.DATE)
	@DateFormatter(DateTimeType.SHORT_DATE)
	@JsonDeserialize(using=ShortDateDeserializer.class)
	@JsonSerialize(using=ShortDateSerializer.class)
	private Date start;
	
	//计划结束时间
	@Column(name="P_ENDDATE")
	@Temporal(TemporalType.DATE)
	@DateFormatter(DateTimeType.SHORT_DATE)
	@JsonDeserialize(using=ShortDateDeserializer.class)
	@JsonSerialize(using=ShortDateSerializer.class)
	private Date end;
	
	//级别 0:单位,1:部门,2:个人
	@Column(name="P_LEVEL")
	private int level;
	
	//计划类型,0:安全检查,1:设备巡检,2:信息系统巡检,100:其他
	@Column(name="P_SYSTYPE")
	private int sysType;
	
	//当计划类型选择其他时,让用户手动输入
	@Column(name="P_OTHERTYPE", length=50)
	private String otherType;
	
	//计划名称
	@Column(name="P_TITLE", length=50)
	private String title;
	
	//创建人
	@Embedded
	private UserInfo creator;
	
	//计划内容
	@Column(name="CON_TENT", length=300)
	@JsonIgnore
	private String content;
	
	//附件id
	@Column(name="P_ATTACHID")
	private String attachId;
	
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
	 * @return the allDay
	 */
	public boolean isAllDay() {
		return allDay;
	}

	/**
	 * @param allDay the allDay to set
	 */
	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}

	/**
	 * @return the start
	 */
	public Date getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(Date start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	public Date getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(Date end) {
		this.end = end;
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
	 * @return the sysType
	 */
	public int getSysType() {
		return sysType;
	}

	/**
	 * @param sysType the sysType to set
	 */
	public void setSysType(int sysType) {
		this.sysType = sysType;
	}

	/**
	 * @return the otherType
	 */
	public String getOtherType() {
		return otherType;
	}

	/**
	 * @param otherType the otherType to set
	 */
	public void setOtherType(String otherType) {
		this.otherType = otherType;
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
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the attachId
	 */
	public String getAttachId() {
		return attachId;
	}

	/**
	 * @param attachId the attachId to set
	 */
	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (allDay ? 1231 : 1237);
		result = prime * result
				+ ((attachId == null) ? 0 : attachId.hashCode());
		result = prime * result + ((end == null) ? 0 : end.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + level;
		result = prime * result + ((start == null) ? 0 : start.hashCode());
		result = prime * result + sysType;
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
		Plan other = (Plan) obj;
		if (allDay != other.allDay)
			return false;
		if (attachId == null) {
			if (other.attachId != null)
				return false;
		} else if (!attachId.equals(other.attachId))
			return false;
		if (end == null) {
			if (other.end != null)
				return false;
		} else if (!end.equals(other.end))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (level != other.level)
			return false;
		if (start == null) {
			if (other.start != null)
				return false;
		} else if (!start.equals(other.start))
			return false;
		if (sysType != other.sysType)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Plan [id=" + id + ", allDay=" + allDay + ", start=" + start
				+ ", end=" + end + ", level=" + level + ", sysType=" + sysType
				+ ", attachId=" + attachId + "]";
	}

	
}
