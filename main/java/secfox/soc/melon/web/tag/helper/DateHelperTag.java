/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.web.tag.helper;

/**
 * @since 1.0 2014年9月26日,下午12:56:26
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class DateHelperTag extends AbstractHelperTag {
	
	/**
	 * 设置是否显示下拉框
	 * @param showSelect
	 */
	public void setShowSelect(boolean showSelect) {
		addConfig("showSelect", showSelect);
	}
	
	/**
	 * 设置最小日期,必须以yyyy-mm-dd的方式
	 * @param minDate
	 */
	public void setMinDate(String minDate) {
		addConfig("minDate", minDate);
	}
	
	/**
	 * 设置最大日期,必须以yyyy-mm-dd的方式
	 * @param maxDate
	 */
	public void setMaxDate(String maxDate) {
		addConfig("maxDate", maxDate);
	}
	
	/**
	 * 在启用下拉框的情况下,设置年度的有效范围,以yyyy:yyyy的形式,如2010:2020等
	 * @param yearRange
	 */
	public void setYearRange(String yearRange) {
		addConfig("yearRange", yearRange);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.web.tablib.helper.AbstractHelperTag#getTmplId()
	 */
	@Override
	public String getTmplId() {
		return "dateHelperScripts";
	}

}
