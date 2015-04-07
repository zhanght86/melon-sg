/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.web;

import secfox.soc.melon.base.util.MessageSourceUtils;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;

/**
 * @since 1.0 2014年9月20日,上午10:21:03
 * 操作提示信息
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class ActionHint {
	
	//格式化参数
	@JsonIgnore
	private Object[] params;
	
	//i18n键值
	@JsonIgnore
	private String key;
	
	//操作提示级别
	private HintLevel level = HintLevel.SUCCESS;
	
	//设置业务对象
	private Object domain;
	
	//设置操作类型
	//0:新增,1:修改,2:删除,3:
	private int actionType = 0;
	
	/**
	 * @param key
	 */
	public ActionHint(String key) {
		super();
		this.key = key;
	}

	/**
	 * @param params
	 * @param key
	 */
	public ActionHint(String key, Object[] params) {
		super();
		this.params = params;
		this.key = key;
	}
	
	/**
	 * @param params
	 * @param key
	 * @param level
	 */
	public ActionHint(String key, Object[] params, HintLevel level) {
		super();
		this.params = params;
		this.key = key;
		this.level = level;
	}
	
	/**
	 * 创建静态对象
	 * @param key
	 * @param params
	 * @return
	 */
	public static ActionHint create(String key, Object... params) {
		return new ActionHint(key, params);
	}
	
	/**
	 * 创建操作提示对象
	 * @param key
	 * @return
	 */
	public static ActionHint create(String key) {
		return new ActionHint(key);
	}
	
	/**
	 * 
	 * @return the domain
	 */
	public Object getDomain() {
		return domain;
	}

	/**
	 * 
	 * @param domain the domain to set
	 */
	public void setDomain(Object domain) {
		this.domain = domain;
	}

	/**
	 * 
	 * @return the actionType
	 */
	public int getActionType() {
		return actionType;
	}

	/**
	 * 
	 * @param actionType the actionType to set
	 */
	public void setActionType(int actionType) {
		this.actionType = actionType;
	}

	/**
	 * 操作提示级别
	 * @return
	 */
	public HintLevel getLevel() {
		return level;
	}

	/**
	 * 设置操作提示级别
	 * @param level
	 */
	public void setLevel(HintLevel level) {
		this.level = level;
	}

	/**
	 * 获取格式化参数
	 * @return the params
	 */
	public Object[] getParams() {
		return params;
	}

	/**
	 * 设置格式化参数
	 * @param params the params to set
	 */
	public void setParams(Object... params) {
		this.params = params;
	}

	/**
	 * 获取国际化键值
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * 设置国际化键值
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}
	
	/**
	 * 获取格式化后的国际化字符串
	 * @return
	 */
	public String getMessage() {
		return MessageSourceUtils.getMessage(key, params);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
					  .add("key", MessageSourceUtils.getMessage(key))
					  .add("level", level)
					  .add("message", getMessage())
					  .toString();
	}
	
}
