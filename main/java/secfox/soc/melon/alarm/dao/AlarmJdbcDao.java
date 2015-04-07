/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.alarm.dao;

import java.util.List;
import java.util.Map;

import secfox.soc.melon.alarm.domain.Alarm;

/**
 * @since 1.0 2014年5月6日,上午11:27:18
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public interface AlarmJdbcDao {

	public abstract List<Alarm> findBySql(String sql,Map<String, Object> params);

}