package secfox.soc.business.check.query;

import java.util.Date;

import secfox.soc.business.check.domain.Router;
import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.base.json.serializer.ShortDateSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
/**
 * @since 1.0 2014年5月16日
 * @author <a href="mailto:fengxy@legendsec.com">冯夏彦</a>
 * @version  1.0
 */
public class RouterReportTaskSearchForm extends Router {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8085880949904764762L;

	public RouterReportTaskSearchForm() {
		super();
	}
	

	/**
	 * 开始时间
	 */
	@DateFormatter(DateTimeType.SHORT_DATE)
	@JsonSerialize(using=ShortDateSerializer.class)
	private Date startTime;

	
	/**
	 * 单位
	 */
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

	/**
	 * 结束时间
	 */
	@DateFormatter(DateTimeType.SHORT_DATE)
	@JsonSerialize(using=ShortDateSerializer.class)
	private Date endTime;

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
