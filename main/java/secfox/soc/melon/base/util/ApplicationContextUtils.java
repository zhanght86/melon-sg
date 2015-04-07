/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @since 1.0 2013-3-12,下午1:44:26
 * ApplicationContext的工具类，提供了获取Bean的静态方法
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Component
public class ApplicationContextUtils implements ApplicationContextAware {

	private static ApplicationContext applicationContext;
	
	private final static Logger logger = LoggerFactory.getLogger(ApplicationContextUtils.class);
	
	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext context) throws BeansException {
		applicationContext = context;
		logger.info("Spring ApplicationContext is STARTED!");
	}
	
	/**
	 * 通过BeanName、Class获取Bean
	 * @param beanName
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(String beanName, Class<T> clazz) {
		return applicationContext.getBean(beanName, clazz);
	}
	
	/**
	 * 通过Class获取Bean，例如通过接口的名称来获取Bean
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		return applicationContext.getBean(clazz);
	}
	
	/**
	 * 通过Bean的名称来获取Bean
	 * @param name
	 * @return
	 */
	public static Object getBean(String name) {
	    return applicationContext.getBean(name);
	}
	
	/**
	 * 获取当前Bean容器
	 * @return Bean容器
	 */
	public static ApplicationContext getContext() {
	    return applicationContext;
	}
	
}
