/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.query;

import java.util.Calendar;

import secfox.soc.melon.base.PageQuery;

/**
 *
 * @since 1.0 2014-10-8 下午8:36:49
 * @author <a href="mailto:huangxm@legendsec.com">黄晓梅</a>
 * @version 1.0
 */
public class LoggerPageQuery extends PageQuery<LoggerSearch>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7099726558169657380L;

	@Override
	protected LoggerSearch initSearchForm() {
		LoggerSearch search = new LoggerSearch();
		Calendar now = Calendar.getInstance();
		//
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		//设置开始时间
		search.setStartTime(now.getTime());
		now.add(Calendar.DAY_OF_WEEK, 1);
		//设置结束时间
		search.setEndTime(now.getTime());
		return search;
	}

}
