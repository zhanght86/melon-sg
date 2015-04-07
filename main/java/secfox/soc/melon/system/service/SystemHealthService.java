/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.service;

/**
 * @since 1.0 2014年10月15日,下午5:42:48
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public interface SystemHealthService {
	
	/**
	 * 
	 * @return
	 */
	public double getMemoryUsage();
	
	/**
	 * 
	 * @return
	 */
	public double getCpuUsage();
}
