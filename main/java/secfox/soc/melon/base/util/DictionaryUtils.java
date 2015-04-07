/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base.util;

import secfox.soc.melon.base.service.DictionaryService;


/**
 * @since 1.0 2014年9月20日,上午9:32:48
 * 数据字典工具类
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public abstract class DictionaryUtils {
	
	/**
	 * 将枚举值转换为可识别的字符串
	 * @param dictionaryKey 数据字典的键值
	 * @param value 待转换的枚举值
	 * @return 待转换的枚举值的标签值
	 */
	public static final String filterValue(String dictionaryKey , Object value) {
		return ApplicationContextUtils.getBean(DictionaryService.class)
									  .filterValue(dictionaryKey, value);
	}
	
}
