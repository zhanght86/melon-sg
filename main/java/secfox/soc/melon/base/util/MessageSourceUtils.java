/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base.util;

import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

/**
 * @since 1.0 2014年9月21日,下午2:48:27
 * 提供获取国际化字符并格式化的方法,格式化的参数与内容配置请参见java.text.MessageFormat
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Component
public class MessageSourceUtils implements MessageSourceAware{
	
	private static MessageSource messageSource;

	/* (non-Javadoc)
	 * @see org.springframework.context.MessageSourceAware#setMessageSource(org.springframework.context.MessageSource)
	 */
	@Override
	public void setMessageSource(MessageSource source) {
		messageSource = source;
	}
	
	/**
	 * 获取国际化字符串
	 * @param key
	 * @return 返回国际化字符串
	 */
	public final static String getMessage(String key) {
		return messageSource.getMessage(key, null, LocaleContextHolder.getLocale());
	}
	
	/**
	 * 获取国际化字符串
	 * @param key 国际化的键值
	 * @param params 国际化的参数
	 * @return 返回国际化字符串
	 */
	public final static String getMessage(String key, Object[] params) {
		return messageSource.getMessage(key, params, LocaleContextHolder.getLocale());
	}
	
	/**
	 * 获取国际化字符串
	 * @param key 国际化的键值
	 * @param defaultValue 如果键值不存在,则返回此值
	 * @return 返回国际化字符串
	 */
	public final static String getMessage(String key, String defaultValue) {
		return messageSource.getMessage(key, null, defaultValue, LocaleContextHolder.getLocale());
	}
	
	/**
	 * 获取国际化字符串
	 * @param key 国际化的键值
	 * @param params 国际化的参数
	 * @param defaultValue 默认值,如果键值不存在,则返回此值
	 * @return 返回国际化字符串
	 */
	public final static String getMessage(String key, Object[] params, String defaultValue) {
		return messageSource.getMessage(key, params, defaultValue, LocaleContextHolder.getLocale());
	}
	

}
