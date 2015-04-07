/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.security;

/**
 * @since 1.0 2014年10月2日,下午4:23:42
 * 安全管理常量
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public interface SecurityConstants {

	/**
	 * 通用资源地址匹配符
	 */
	static final String MATCH_ALL = "/**";
	
	/**
	 * 
	 */
	static final char CONTEXT_ROOT_CHAR = '/';
	
	/**
	 * 
	 */
	static final String CONTEXT_ROOT = String.valueOf(CONTEXT_ROOT_CHAR);
	
	/**
	 * 
	 */
	static final String WILD_CHAR = "*";
	
	/**
	 * 
	 */
	static final String PARAM_SPLIT = "?";
	
	
	/**
	 * 通用配置符
	 */
	static final String WILD_CHAR_ALL = "**";
	
	/**
	 * 登陆错误提示符
	 */
	static final String LOGIN_ERROR = "LOGIN_ERROR";
	
	/**
	 * 通用资源访问路径的尾部标示符
	 */
	static final String PATH_COMMON = "2,1";
	
	/**
	 * 通用资源标识符
	 */
	static final String ROLE_COMMON = "ROLE_COMMON";
	
	/**
	 * 
	 */
	static final String ROLE_PREFIX = "ROLE_";
	
	/**
	 * 
	 */
	static final String SPRING_SECURITY_CONTEXT = "SPRING_SECURITY_CONTEXT";
	
}
