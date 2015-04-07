/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.knowledge.query;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.base.json.serializer.ShortDateSerializer;
import secfox.soc.melon.knowledge.domain.Knowledge;

/**
 * @since @2014-10-28,@下午2:56:21
 * @author <a href="mailto:chengzhan@legendsec.com>程展</a>
 * @version 1.0
 */
public class KnowledgeSearchForm extends Knowledge{

	private static final long serialVersionUID = 1L;

	//开始日期
	@Temporal(TemporalType.DATE)
	@DateFormatter(DateTimeType.SHORT_DATE)
	@JsonSerialize(using=ShortDateSerializer.class)
	@Column(name="START_DATE")
	private Date startDate;
	
	//结束日期
	@Temporal(TemporalType.DATE)
	@DateFormatter(DateTimeType.SHORT_DATE)
	@JsonSerialize(using=ShortDateSerializer.class)
	@Column(name="END_DATE")
	private Date endDate;

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
}

