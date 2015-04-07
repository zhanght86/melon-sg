/*
 * (c) Copyright 2012 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.security;

import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.access.DefaultWebInvocationPrivilegeEvaluator;

import secfox.soc.melon.web.WebConstants;

/**
 * @since 0.1 2012-12-27,下午3:24:57下午
 * 页面权限控制器,能够精确地控制到按钮或页面的局部内容
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  0.1 
 */
public class WebInvocationPrivilegeEvaluatorWithPrefix extends DefaultWebInvocationPrivilegeEvaluator {
	
	private static final String _PREFIX = WebConstants.APP_CONTEXT_ROOT + WebConstants.CONTEXT_ROOT;

	/**
	 * @param securityInterceptor
	 */
	public WebInvocationPrivilegeEvaluatorWithPrefix(AbstractSecurityInterceptor securityInterceptor) {
		super(securityInterceptor);
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.web.access.DefaultWebInvocationPrivilegeEvaluator#isAllowed(java.lang.String, org.springframework.security.core.Authentication)
	 */
	@Override
	public boolean isAllowed(String url, Authentication authentication) {
		return super.isAllowed(_PREFIX + url, authentication);
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.web.access.DefaultWebInvocationPrivilegeEvaluator#isAllowed(java.lang.String, java.lang.String, java.lang.String, org.springframework.security.core.Authentication)
	 */
	@Override
	public boolean isAllowed(String arg0, String url, String arg2, Authentication arg3) {
		return super.isAllowed(arg0, _PREFIX + url, arg2, arg3);
	}

}
