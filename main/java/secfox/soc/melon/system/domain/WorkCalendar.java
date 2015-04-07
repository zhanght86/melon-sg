/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.domain;

import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.time.DateUtils;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.base.json.serializer.ShortDateSerializer;
import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 1.0 2014年10月9日,上午11:33:33
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name="T_SYSTEM_CALENDAR")
@NamedQueries({
		@NamedQuery(name="workCalendar.findByTime", 
					query="select work from WorkCalendar work "
							+ "where work.date between :start and :end")
})
public class WorkCalendar extends BaseDomain<Long> {

	private static final long serialVersionUID = -3986363303802784554L;
	
	@Id@Column(name = "PK")
	@GeneratedValue(generator="GEN_SEQ_CALENDAR")
	private Long id;
	
	//是否具有日期标志
	@Column(name="DATE_FLAG")
	private boolean dateFlag = false;
	
	//只精确到日
	//标记的日期
	@Column(name="W_DATE", length=100)
	@DateFormatter(DateTimeType.SHORT_DATE)
	@JsonSerialize(using=ShortDateSerializer.class)
	@Temporal(TemporalType.DATE)
	private Date date;
	
	//备注信息，如劳动节、国庆节、中秋节等
	@Column(name="REMARKS", length=100)
	private String remarks;
	
	//判断是否是工作日
	/**
	 * 判断是否是工作日
	 * @return
	 */
	public boolean isWorkDay() {
		boolean workDay = !isWeekend();
		//然后判断是否具有日期标志，则工作日变休息日，休息日变工作日
		if(dateFlag) {
			return !workDay;
		}
		return workDay;
	}
	
	/**
	 * 判断是否是周末
	 * @return
	 */
	public boolean isWeekend() {
		//首先判断是否是周末
		Calendar calendar = DateUtils.toCalendar(date);
		int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
		//判断是否是周末
		if(dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY) {
			return true;
		}
		return false;
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
	 * @return the dateFlag
	 */
	public boolean isDateFlag() {
		return dateFlag;
	}

	/**
	 * 
	 * @return
	 */
	@JsonSerialize(using=ShortDateSerializer.class)
	public Date getStart() {
		return date;
	}
	
	/**
	 * @param dateFlag the dateFlag to set
	 */
	public void setDateFlag(boolean dateFlag) {
		this.dateFlag = dateFlag;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}



	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}




	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return remarks;
	}
	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((date == null) ? 0 : date.hashCode());
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
		WorkCalendar other = (WorkCalendar) obj;
		if (date == null) {
			if (other.date != null)
				return false;
		} else if (!date.equals(other.date))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "WorkCalendar [id=" + id + ", dateFlag=" + dateFlag + ", date="
				+ date + "]";
	}

}
