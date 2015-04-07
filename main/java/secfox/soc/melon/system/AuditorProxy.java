/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system;

import org.aspectj.lang.JoinPoint;
import org.slf4j.LoggerFactory;

import secfox.soc.melon.integration.MessageChannelUtils;
import secfox.soc.melon.system.domain.Logger;
import secfox.soc.melon.system.service.AuditConfigService;
import secfox.soc.melon.system.service.impl.LoggerServiceImpl;

/**
 * @since 1.0 2013年11月19日,下午12:47:55
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class AuditorProxy {
	
	private final static org.slf4j.Logger log = LoggerFactory.getLogger(AuditorProxy.class);
	
	private AuditConfigService auditConfigService;
	
	/**
	 * @param auditConfigService
	 */
	public AuditorProxy(AuditConfigService auditConfigService) {
		super();
		this.auditConfigService = auditConfigService;
	}

	/**
	 * 发表审计日志
	 * @param jp
	 */
	public void audit(JoinPoint jp) {
		Class<?> clazz= jp.getTarget().getClass();
		String methodName = jp.getSignature().getName();
		//防止死循环
		if(clazz != LoggerServiceImpl.class) {
			Logger logger = Logger.create(clazz);
			if(logger != null) {
				logger.setFunction(methodName);
				//确定是否需要审计并将操作名称转义为中文
				Logger exLogger = auditConfigService.enhance(logger);
				if(exLogger != null) {
					//
					if(log.isDebugEnabled()) {
						log.debug("AUDIT:{}", logger);
					}
					MessageChannelUtils.send("loggerBufferChannel", logger);
				}
			}
		}
		
	}
	
}
