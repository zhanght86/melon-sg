/*
 * (c) Copyright 2012 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base;

import net.sf.ehcache.management.ManagementService;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.jmx.support.JmxUtils;

/**
 * @since 0.1 2012-12-9,下午12:14:04
 * 将缓存管理器注册为MBean,可动态观察
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  0.1 
 */
public class JmxStaticsEhCacheCacheManager extends EhCacheCacheManager {

	/* (non-Javadoc)
	 * @see org.springframework.cache.support.AbstractCacheManager#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() {
		super.afterPropertiesSet();
		ManagementService.registerMBeans(this.getCacheManager(), JmxUtils.locateMBeanServer(), false, false, false, true);
	}

	
}
