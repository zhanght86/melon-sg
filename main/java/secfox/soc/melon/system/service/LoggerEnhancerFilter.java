/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.service;

import org.springframework.integration.core.MessageSelector;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import secfox.soc.melon.system.domain.Logger;

/**
 * @since 1.0 2014年9月28日,下午4:25:09
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Component("loggerEnhancer")
public class LoggerEnhancerFilter implements MessageSelector {

	/* (non-Javadoc)
	 * @see org.springframework.integration.core.MessageSelector#accept(org.springframework.messaging.Message)
	 */
	@Override
	public boolean accept(Message<?> message) {
		Object payload = message.getPayload();
		if(payload instanceof Logger) {
			Logger logger = (Logger)payload;
			if(logger.getId() % 2 == 0) {
				return true;
			}
		}
		return false;
	}

}
