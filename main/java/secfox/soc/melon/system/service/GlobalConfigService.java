/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.system.service;

import javax.servlet.http.HttpServletRequest;

import secfox.soc.melon.persistence.GenericService;
import secfox.soc.melon.system.domain.GlobalConfig;

/**
 * @since 2014-9-24,下午5:16:58
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public interface GlobalConfigService extends GenericService<GlobalConfig, Long> {
	
	/**
	 * 创建全局配置
	 * @return
	 */
	GlobalConfig create();
	
	/**
	 * 查找全局配置
	 * @return
	 */
	GlobalConfig find();
	
	/**
	 * 更新全局配置
	 * @return
	 */
	GlobalConfig update(GlobalConfig globalConfig, HttpServletRequest request);

}
