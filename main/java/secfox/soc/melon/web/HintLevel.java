/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.web;

/**
 * @since 1.0 2014年9月20日,上午10:21:44
 * 操作提示级别
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public enum HintLevel {
	
	/**
	 * 默认提示级别,普通文字
	 */
	INFORMATION,//普通提示信息
	
	/**
	 * 警告提示信息
	 */
	WARNING,//
	
	/**
	 * 操作成功
	 */
	SUCCESS,//成功提示信息
	
	/**
	 * 危险操作提示
	 */
	DANGER,//危险提示信息
	
	/**
	 * 错误提示信息
	 */
	ERROR//
	
}
