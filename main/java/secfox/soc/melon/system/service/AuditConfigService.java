/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.service;

import secfox.soc.melon.base.AuditConfig;
import secfox.soc.melon.system.domain.Logger;

/**
 * @since 1.0 2014年10月10日,上午10:18:09
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public interface AuditConfigService {
	
	/**
	 * 获取日志配置信息
	 * @return
	 */
	AuditConfig get();
	
	/**
	 * 
	 * @return
	 */
	Logger enhance(Logger logger);
	
}
