/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.indicator.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 1.0 2014年10月14日,下午1:49:48
 * 默认的指标值
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class DefaultIndicator extends BaseDomain<Long> implements Indicator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5051789971392073628L;
	
	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_IND")
	private Long id;
	
	//指标ID
	@Column(name="IND_ID")
	private Long indicatorId;
	
	//创建时间,提示于什么时候更新
	@Column(name="CREATE_TIME")
	private Date createTime;
	
	//指标值
	@Column(name="IND_VALUE")
	private Long value;
	
	@Embedded
	private UserInfo creator;
	
	/**
	 * 
	 * @return the indicatorId
	 */
	public Long getIndicatorId() {
		return indicatorId;
	}

	/**
	 * 
	 * @param indicatorId the indicatorId to set
	 */
	public void setIndicatorId(Long indicatorId) {
		this.indicatorId = indicatorId;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.indicator.domain.Indicator#getCreateTime()
	 */
	@Override
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.indicator.domain.Indicator#getValue()
	 */
	@Override
	public Long getValue() {
		return value;
	}

	/**
	 * 
	 * @param value the value to set
	 */
	public void setValue(Long value) {
		this.value = value;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.indicator.domain.Indicator#getCreator()
	 */
	@Override
	public UserInfo getCreator() {
		return creator;
	}

	/**
	 * 
	 * @param creator the creator to set
	 */
	public void setCreator(UserInfo creator) {
		this.creator = creator;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.Identifiable#getId()
	 */
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.Identifiable#setId(java.io.Serializable)
	 */
	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.BaseDomain#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.BaseDomain#hashCode()
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.BaseDomain#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
