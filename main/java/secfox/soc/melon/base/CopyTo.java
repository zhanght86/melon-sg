/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base;

/**
 * @since 1.0 2014年1月20日,下午3:02:14
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public interface CopyTo<T> {
	
	/**
	 * 将当前对象的属性值复制到目标对象中，防止多线程问题以及延迟加载问题
	 * @return 目标对象
	 */
	T copy();
	
}
