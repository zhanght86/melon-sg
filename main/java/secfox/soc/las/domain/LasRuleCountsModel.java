/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.las.domain;

import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import secfox.soc.las.domain.RuleCondition;
import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 2015年2月4日,下午5:29:41
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */

public class LasRuleCountsModel {
	
	/**
	 * 
	 */

	private Long id;//id
	
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

	private int counts;//重复次数
	
	private int times;//时间
	
	private int unit;//时间单位：秒，分钟，小时，天
	
	
	
	private String andAttr;
	
	private String[] andAttrs;
	
	/**
	 * @return the andAttrs
	 */
	public String[] getAndAttrs() {
		return andAttrs;
	}

	/**
	 * @param andAttrs the andAttrs to set
	 */
	public void setAndAttrs(String[] andAttrs) {
		this.andAttrs = andAttrs;
	}

	private String notAttr;
	
	private List<RuleCondition> conditions;
	
	private List<String> columns;

	/**
	 * @return the columns
	 */
	public List<String> getColumns() {
		return columns;
	}

	/**
	 * @param columns the columns to set
	 */
	public void setColumns(List<String> columns) {
		this.columns = columns;
	}

	/**
	 * @return the conditions
	 */
	public List<RuleCondition> getConditions() {
		return conditions;
	}

	/**
	 * @param conditions the conditions to set
	 */
	public void setConditions(List<RuleCondition> conditions) {
		this.conditions = conditions;
	}

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
}
