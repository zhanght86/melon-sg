/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.service;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import secfox.soc.melon.system.domain.Logger;

/**
 * @since 1.0 2014年9月28日,下午4:37:26
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Component("loggerPersistActivator")
public class LoggerPersistActivator {

	private LoggerService loggerService;
	
	/**
	 * @param loggerService
	 */
	@Inject
	public LoggerPersistActivator(LoggerService loggerService) {
		super();
		this.loggerService = loggerService;
	}
	
	/**
	 * 保存日志对象
	 * @param logger
	 */
	public void persist(Logger logger) {
		loggerService.persist(logger);
	}
	
}
