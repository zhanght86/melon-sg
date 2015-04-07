package secfox.soc.melon.event.service;

import java.util.List;
import java.util.Map;

/**
 *
 * @since 1.0 2014年12月4日下午2:18:20
 * @author <a href="mailto:xugfa@legendsec.com">徐广飞</a>
 * @version 1.0
 */

public interface EventProcessService {

	public List<Map<String,Object>> findSourceAdd(String ip);
	
	public List<Map<String,Object>> findTarAndsourAdd(String ip);
}
