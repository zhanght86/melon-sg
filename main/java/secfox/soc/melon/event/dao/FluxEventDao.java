package secfox.soc.melon.event.dao;

import java.util.List;
import java.util.Map;

/**
 *
 * @since 1.0 2014年12月6日上午10:58:15
 * @author <a href="mailto:xugfa@legendsec.com">徐广飞</a>
 * @version 1.0
 */

public interface FluxEventDao {

	List<Object[]> findInFlux(String ip, Map<String, Object> params);
	
	List<Object[]> findOutFlux(String ip, Map<String, Object> params);
}
