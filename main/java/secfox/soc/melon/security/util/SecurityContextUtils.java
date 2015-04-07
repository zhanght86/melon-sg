/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.security.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.security.SecurityConstants;
import secfox.soc.melon.security.domain.Account;

/**
 * @since 1.0 2013-3-20,上午9:17:54
 * 用户安全工具类
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public final class SecurityContextUtils {
	
    /**
	 * 阻止实例化
	 */
	private SecurityContextUtils() {
		
	}
	
	/**
	 * 获取当前登录用户
	 * @return
	 */
	public final static Account getCurrentAccount() {
		if(SecurityContextHolder.getContext() == null || SecurityContextHolder.getContext().getAuthentication() == null) {
			return null;
		}
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if(obj instanceof Account) {
			return (Account)obj;
		}
		return null;
	}
	
	/**
	 * 获取当前用户
	 * @return
	 */
	public static UserInfo getCurrentUserInfo() {
		Account account = getCurrentAccount();
		if(account != null) {
			return account.getUserInfo();
		}
		return null;
	}
	
	/**
	 * 通过Session获取当前用户
	 * @param session
	 * @return
	 */
	public final static Account getCurrentAccount(HttpSession session) {
	    Object obj = session.getAttribute(SecurityConstants.SPRING_SECURITY_CONTEXT);
	    if(obj instanceof SecurityContext) {
	        SecurityContext context = (SecurityContext)obj;
	        Object userDetail = context.getAuthentication().getPrincipal();
	    	if(userDetail instanceof Account) {
				return (Account)userDetail;
			}
        }
        return null;
	}
	
	/**
	 * 
	 * @param session
	 * @return
	 */
	public static UserInfo getCurrentUserInfo(HttpSession session) {
		Account account = getCurrentAccount(session);
		if(account != null) {
			return account.getUserInfo();
		}
		return null;
	}
	
	/**
	 * 通过Request获取当前用户
	 * @param request
	 * @return
	 */
	public final static Account getCurrentAccount(HttpServletRequest request) {
	    HttpSession session = request.getSession();
	    return getCurrentAccount(session);
	}
	
	/**
	 * 通过Request获取当前用户
	 * @param request
	 * @return
	 */
	public static UserInfo getCurrentUserInfo(HttpServletRequest request) {
		Account account = getCurrentAccount(request);
		if(account != null) {
			return account.getUserInfo();
		}
		return null;
	}
}
