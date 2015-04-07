/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base.service;

import java.util.List;
import java.util.Map;

import secfox.soc.melon.base.domain.Dictionary;
import secfox.soc.melon.persistence.GenericService;

/**
 * @since 1.0 2014年9月17日,下午7:33:53
 * 数据字典服务类,目前基本类型只支持整数型
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public interface DictionaryService extends GenericService<Dictionary, Long> {
	
	/**
	 * 从本地的数据字典文件中抽取数据字典
	 * @param key 数据字典的键值
	 * @return
	 */
	Map<String, String> findByKey(String key);
	
	/**
	 * 将枚举值转换为可识别的字符串
	 * @param dictionaryKey 数据字典的键值
	 * @param value 待转换的枚举值
	 * @return 待转换的枚举值的标签值
	 */
	String filterValue(String dictionaryKey , Object value);
	
	/**
	 * 查询树的节点
	 * @param rootId
	 * @return
	 */
	public List<Dictionary> findTree(Long rootId);
	
	/**
	 * 根据父节点查找子节点
	 * @param parentId
	 * @return
	 */
	public List<Dictionary> findDict(Long parentId);
}
