/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.business.check.query;

import java.util.Date;

import secfox.soc.business.check.domain.Virus;
import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.base.json.serializer.ShortDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * 信息系统情况
 * @since @2014-4-23,@下午4:40:06
 * @author <a href="mailto:liubing@legendsec.com>刘冰</a>
 * @version 1.0
 */
public class VirusFormSearchForm extends Virus {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8085880949904764762L;
	
	
	public VirusFormSearchForm(){
		super();
	}
	/**
	 * 开始时间
	 */
	@DateFormatter(DateTimeType.SHORT_DATE)
	@JsonSerialize(using=ShortDateSerializer.class)
	private Date startTime;

	/**
	 * 结束时间
	 */
	@DateFormatter(DateTimeType.SHORT_DATE)
	@JsonSerialize(using=ShortDateSerializer.class)private Date endTime;
	private String organName;
	
	

	/**
	 * @return the organName
	 */
	public String getOrganName() {
		return organName;
	}

	/**
	 * @param organName the organName to set
	 */
	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
}
