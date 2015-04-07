package secfox.soc.melon.event.dao;

import java.util.List;
import java.util.Map;

/**
 *
 * @since 1.0 2014年12月4日上午11:34:29
 * @author <a href="mailto:xugfa@legendsec.com">徐广飞</a>
 * @version 1.0
 */

public interface EventJdbcDao {

	List<Map<String,Object>> findSourceAdd(String ip,String tableName, Map<String, Object> params);
	
	List<Map<String,Object>> findTargetAdd(String ip,String tableName, Map<String, Object> params);
	
	List<Map<String,Object>> findTarAndsourAdd(String ip,String tableName, Map<String, Object> params);
}
