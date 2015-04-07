package secfox.soc.melon.event.service;

import java.util.List;

/**
 *
 * @since 1.0 2014年12月6日上午11:22:46
 * @author <a href="mailto:xugfa@legendsec.com">徐广飞</a>
 * @version 1.0
 */

public interface FluxEventService {

	public List<Object[]> findInAndOutFlux(String ip);
}
