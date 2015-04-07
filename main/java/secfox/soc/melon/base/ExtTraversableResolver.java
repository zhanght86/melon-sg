/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base;

import java.lang.annotation.ElementType;

import javax.validation.Path;
import javax.validation.TraversableResolver;

/**
 * @since 1.0 2014年10月9日,下午6:18:00
 * 
 * @author <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version 1.0
 */
public class ExtTraversableResolver implements TraversableResolver {

	/*
	 * (non-Javadoc)
	 * @see javax.validation.TraversableResolver#isReachable(java.lang.Object, javax.validation.Path.Node, java.lang.Class, javax.validation.Path, java.lang.annotation.ElementType)
	 */
	public final boolean isReachable(Object traversableObject,
			Path.Node traversableProperty, Class<?> rootBeanType,
			Path pathToTraversableObject, ElementType elementType) {
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see javax.validation.TraversableResolver#isCascadable(java.lang.Object, javax.validation.Path.Node, java.lang.Class, javax.validation.Path, java.lang.annotation.ElementType)
	 */
	public final boolean isCascadable(Object traversableObject,
			Path.Node traversableProperty, Class<?> rootBeanType,
			Path pathToTraversableObject, ElementType elementType) {
		return true;
	}

}
