/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.security;

import java.util.Comparator;

/**
 * @since 1.0 2014年10月6日,下午9:18:11
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class AntPathRequestMatcherComparator implements Comparator<AntPathRequestMatcher> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(AntPathRequestMatcher o1, AntPathRequestMatcher o2) {
		//如果地址完全相同,则覆盖
		if(o1.getPattern().equals(o2.getPattern())) {
			return 0;
		} else {
			//如果相等，则使用字符的自然排序
			if(o1.getOrder() == o2.getOrder()) {
				return o1.getPattern().compareTo(o2.getPattern());
			}
			return o1.getOrder() - o2.getOrder();//越小的排列在越前面
		}
	}

}
