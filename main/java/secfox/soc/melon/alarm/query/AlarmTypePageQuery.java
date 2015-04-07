package secfox.soc.melon.alarm.query;

import secfox.soc.melon.alarm.domain.AlarmType;
import secfox.soc.melon.base.PageQuery;
import secfox.soc.melon.indicator.domain.IndicatorDefine;

/**
 * @since 1.0 2014-10-21,下午3:20:19
 * 
 * @author  <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0 
 */
public class AlarmTypePageQuery extends PageQuery<AlarmType> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1417917052628273385L;

	@Override
	protected AlarmType initSearchForm() {
		return new AlarmType();
	}
	
}

