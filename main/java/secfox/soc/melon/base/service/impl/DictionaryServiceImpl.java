/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base.service.impl;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.base.dao.DictionaryDao;
import secfox.soc.melon.base.domain.Dictionary;
import secfox.soc.melon.base.service.DictionaryService;
import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSortedMap;
import com.google.common.collect.ImmutableSortedMap.Builder;
import com.google.common.collect.Iterables;

/**
 * @since 1.0 2014年9月17日,下午7:43:24
 * 数据字典服务实现类
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Service
public class DictionaryServiceImpl extends GenericServiceImpl<Dictionary, Long> implements DictionaryService {
	
	private MessageSource messageSource;
	
	private DictionaryDao dictionaryDao;
	
	/**
	 * @param messageSource
	 */
	@Inject
	public DictionaryServiceImpl(MessageSource messageSource, DictionaryDao dictionaryDao) {
		super();
		this.messageSource = messageSource;
		this.dictionaryDao = dictionaryDao;
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<Dictionary, Long> getDao() {
		return dictionaryDao;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.commons.service.DictionaryService#findByKey(java.lang.String)
	 */
	@Override
	public Map<String, String> findByKey(String key) {
		Builder<String, String> builder = new ImmutableSortedMap.Builder<String, String>(new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				try {
					Integer i1 = Integer.parseInt(o1);
					Integer i2 = Integer.parseInt(o2);
					return i1 -i2;
				} catch(NumberFormatException ex) {
					return o1.compareTo(o2);
				}
			}
			
		});
		//获取Message
		String values = messageSource.getMessage(BaseConstants.ENUM_PREFIX + key, null, LocaleContextHolder.getLocale());
		//分割为组
		Iterable<String> items = Splitter.on(BaseConstants.SPLITER_FLAG).split(values);
		//封装为单项Map
		for(String item : items) {
			String[] result = StringUtils.split(item, BaseConstants.VALUE_LABEL_SPLITER);
			builder.put(StringUtils.trim(result[0]), StringUtils.trim(result[1]));
		}
		return builder.build();
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.commons.service.DictionaryService#filterValue(java.lang.String, java.lang.Object)
	 */
	@Override
	public String filterValue(String dictionaryKey, Object value) {
		//获取到数据字典组合
		Map<String, String> dictionary = findByKey(dictionaryKey);
		//如果为空
		if(value == null) {
			return StringUtils.EMPTY;
		}
		//判断数据字典的类型
		//确定是否数组
		if(value.getClass().isArray()) {
			Object[] valueArray = null;
			try {
				valueArray = (Object[]) value;
			} catch(Exception e) {
				//必然是基本类型
				//继续判读其他类型
				valueArray = ArrayUtils.toObject((int[])value);
			}
			Collection<Object> valueCollection = Arrays.asList(valueArray);
			return joinFilterValues(dictionary, valueCollection);
		} else if (value instanceof Collection) {
			final Collection<?> valueCollection = (Collection<?>) value;
			return joinFilterValues(dictionary, valueCollection);
		} else {
			return dictionary.get(String.valueOf(value));
		}
	}

	/**
	 * 将数据字典容器合并为字符串
	 * @param dictionary
	 * @param valueArray
	 * @return
	 */
	private String joinFilterValues(final Map<String, String> dictionary,	Collection<?> valueArray) {
		Iterable<String> result = Iterables.transform(valueArray, new Function<Object, String>() {
			
			/**
			 * 将键值转换为字符串
			 */
			@Override
			public String apply(Object obj) {
				return dictionary.get(String.valueOf(obj));
			}
			
		});
		return Joiner.on(BaseConstants.SPLITER_FLAG).join(result);
	}

	/**
	 * 查询树的子节点
	 */
	@Override
	public List<Dictionary> findTree(Long rootId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select a from Dictionary a where a.path like :path ");
		Dictionary dictionary = new Dictionary();
		String path = String.valueOf(BaseConstants.ROOT_ID);
		//如果是默认节点
		if(rootId == BaseConstants.ROOT_ID){
			dictionary = createRoot();
		} else {
			dictionary = find(rootId); //根据id获取
			path = dictionary.getPath();
		}
		//路径匹配
		qt.addParameter("path", QueryTemplate.buildLeftLike(path));
		//排序
		qt.append(" order by a.order desc");
		List<Dictionary> types = findDomains(qt);//获取孙子节点
		//如果包含根节点
		if(types.contains(dictionary)){ 
			Dictionary typeRoot = types.get(types.indexOf(dictionary)); //获取这个对象的位置
			typeRoot.setAsRoot(true); //设置为根节点
			typeRoot.getState().setOpened(true); //默认打开节点
		}else{
			//不包含,就加进去
			types.add(dictionary);
		}
		return types;
	}
	
	/**
	 * 
	 * @return
	 */
	private Dictionary createRoot() {
		Dictionary dic = new Dictionary();
		dic.setId(BaseConstants.ROOT_ID);
		dic.setLabel(MessageSourceUtils.getMessage("base.dictionary.root"));
		dic.setId(BaseConstants.ROOT_ID);
		dic.setParentId(null);
		dic.getState().setOpened(true);
		return dic;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.base.service.DictionaryService#findDict(java.lang.Long)
	 */
	@Override
	public List<Dictionary> findDict(Long parentId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "dictionary.findDict");
		qt.addParameter("parentId", parentId);
		return findDomains(qt);
	}

}
