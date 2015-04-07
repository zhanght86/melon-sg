/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.alarm.query;

import secfox.soc.melon.base.PageQuery;

/**
 *
 * @since 1.0 2014年9月29日
 * @author <a href="mailto:jinml@legendsec.com">靳明雷</a>
 * @version 1.0
 */
public class AlarmPageQuery extends PageQuery<AlarmSearch> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5935907937508621458L;

	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.PageQuery#initSearchForm()
	 */
	@Override
	protected AlarmSearch initSearchForm() {
		
		return new AlarmSearch();
	}
	
	private String ids;

	/**
	 * @return the ids
	 */
	public String getIds() {
		return ids;
	}

	/**
	 * @param ids the ids to set
	 */
	public void setIds(String ids) {
		this.ids = ids;
	}
}
