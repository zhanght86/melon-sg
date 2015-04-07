/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.query;

import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.base.json.serializer.LongDateSerializer;
import secfox.soc.melon.system.domain.Logger;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @since 1.0 2014年10月9日,下午4:22:39
 * 日志查询对象
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class LoggerSearch extends Logger {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5921684080078591203L;

	//查询的开始时间
	@Temporal(TemporalType.TIMESTAMP)
	@DateFormatter(DateTimeType.LONG_DATE)
	@JsonSerialize(using=LongDateSerializer.class)
	private Date startTime;
	
	//查询的结束时间
	@Temporal(TemporalType.TIMESTAMP)
	@DateFormatter(DateTimeType.LONG_DATE)
	@JsonSerialize(using=LongDateSerializer.class)
	private Date endTime;
	
	//高级查询的开始时间
	@Temporal(TemporalType.TIMESTAMP)
	@DateFormatter(DateTimeType.SHORT_DATE)
	@JsonSerialize(using=LongDateSerializer.class)
	private Date start;
	
	//高级查询的结束时间
	@Temporal(TemporalType.TIMESTAMP)
	@DateFormatter(DateTimeType.SHORT_DATE)
	@JsonSerialize(using=LongDateSerializer.class)
	private Date end;

	/**
	 * 
	 * @return the startTime
	 */
	public Date getStartTime() {
		return startTime;
	}

	/**
	 * 
	 * @param startTime the startTime to set
	 */
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	/**
	 * 
	 * @return the endTime
	 */
	public Date getEndTime() {
		return endTime;
	}

	/**
	 * 
	 * @param endTime the endTime to set
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
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
	
	
}
