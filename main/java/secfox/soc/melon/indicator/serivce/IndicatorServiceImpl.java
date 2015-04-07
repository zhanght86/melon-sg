/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.indicator.serivce;

import org.springframework.stereotype.Service;

import secfox.soc.melon.indicator.domain.DefaultIndicator;
import secfox.soc.melon.indicator.domain.Indicator;

/**
 * @since 1.0 2014年10月15日,下午7:49:32
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Service
public class IndicatorServiceImpl implements IndicatorService {

	/* (non-Javadoc)
	 * @see secfox.soc.melon.indicator.service.IndicatorService#find(java.lang.Long, int)
	 */
	@Override
	public Indicator find(Long code, int type, int timeType) {
		DefaultIndicator in = new DefaultIndicator();
		in.setValue(Math.round(Math.random() * 1000));
		return in;
	}

}
