/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base.json;

/**
 * @since 1.0 2014年9月19日,下午1:43:08
 * Json内容的输出格式
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public enum JsonFormat {
	
	/**
	 * 以对象形式(每个值都有相应的键值),Map也属于此形式
	 */
	OBJECT, //对象形式
	
	/**
	 * 以数组形式(按顺序来确定值,例如ID的值默认为数组的第一个值)
	 */
	ARRAY //数组形式
}
