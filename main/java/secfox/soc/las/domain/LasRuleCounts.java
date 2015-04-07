/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.las.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 2015年2月4日,下午5:29:41
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */

@Entity
@Table(name = "T_Las_Counts")
public class LasRuleCounts extends BaseDomain<Long>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id@Column(name="ID")
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;//id
	
	@Column(name="COUNT")
	private int counts;//重复次数
	
	@Column(name="TIMES")
	private int times;//时间
	
	@Column(name="UNIT")
	private int unit;//时间单位：秒，分钟，小时，天
	
	
	@Column(name="AND_ATTR")
	private String andAttr;
	

	@Column(name="NOT_ARRT")
	private String notAttr;

	/**
	 * @return the count
	 */
	public int getCounts() {
		return counts;
	}

	/**
	 * @param count the count to set
	 */
	public void setCounts(int counts) {
		this.counts = counts;
	}

	/**
	 * @return the time
	 */
	public int getTimes() {
		return times;
	}

	/**
	 * @param time the time to set
	 */
	public void setTimes(int time) {
		this.times = time;
	}

	/**
	 * @return the unit
	 */
	public int getUnit() {
		return unit;
	}

	/**
	 * @param unit the unit to set
	 */
	public void setUnit(int unit) {
		this.unit = unit;
	}


	/**
	 * @return the andAttr
	 */
	public String getAndAttr() {
		return andAttr;
	}

	/**
	 * @param andAttr the andAttr to set
	 */
	public void setAndAttr(String andAttr) {
		this.andAttr = andAttr;
	}

	/**
	 * @return the notAttr
	 */
	public String getNotAttr() {
		return notAttr;
	}

	/**
	 * @param notAttr the notAttr to set
	 */
	public void setNotAttr(String notAttr) {
		this.notAttr = notAttr;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.Identifiable#getId()
	 */
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return id;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.Identifiable#setId(java.io.Serializable)
	 */
	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub
		this.id = id;
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
