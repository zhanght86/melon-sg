/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.indicator.serivce;

import secfox.soc.melon.indicator.domain.Indicator;

/**
 * @since 1.0 2014年10月15日,下午7:42:49
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public interface IndicatorService {
	
	/**
	 * 
	 * @param code
	 * @param type
	 * @return
	 */
	Indicator find(Long code, int indType, int timeType);
	
}
