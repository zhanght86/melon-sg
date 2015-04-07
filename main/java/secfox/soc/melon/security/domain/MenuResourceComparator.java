/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.security.domain;

import java.util.Comparator;

import org.apache.commons.lang3.StringUtils;

/**
 * @since 1.0 2014年10月6日,下午10:28:54
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class MenuResourceComparator implements Comparator<MenuResource> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(MenuResource o1, MenuResource o2) {
		if(!StringUtils.equals(o1.getPath(), o2.getPath())) {
			return o1.getOrder() - o2.getOrder();
		}
		return o1.getPath().compareTo(o2.getPath());
	}

}
