/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.web.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspContext;
import javax.servlet.jsp.PageContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import secfox.soc.melon.web.WebConstants;

/**
 * @since 1.0 2014年10月2日,下午4:28:46
 * Http请求工具类
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class RequestUtils {
	
	/**
	 * 根据JSP Context获取当前访问路径
	 * @param jspContext
	 * @return
	 */
	public static String getContextPath(JspContext jspContext) {
		if(jspContext instanceof PageContext) {
			PageContext context = (PageContext) jspContext;
			return getContextPath(context);
		}
		return StringUtils.EMPTY;
	}
	
	/**
	 * 
	 * @param jspContext
	 * @return
	 */
	public static PageContext getPageContext(JspContext jspContext) {
		if(jspContext instanceof PageContext) {
			PageContext context = (PageContext) jspContext;
			return context;
		}
		return null;
	}
	
	/**
	 * 根据PageContext获取当前访问路径
	 * @param context
	 * @return
	 */
	public static String getContextPath(PageContext context) {
		HttpServletRequest request = (HttpServletRequest) context.getRequest();
		String contextPath = request.getContextPath();
		//默认加上在路径后加上/
		if(!contextPath.endsWith(WebConstants.CONTEXT_ROOT)) {
			return contextPath + WebConstants.CONTEXT_ROOT;
		}
		return contextPath;
	}
	
	/**
	 * 获取本地线程中的Reqeust对象
	 * @return
	 */
	public static HttpServletRequest getRequest() {
		try {
			RequestAttributes attrs = RequestContextHolder.currentRequestAttributes();
	        if(attrs instanceof ServletRequestAttributes) {
	            ServletRequestAttributes servletAttrs = (ServletRequestAttributes)attrs;
	            HttpServletRequest httpRequest = servletAttrs.getRequest();
	            return httpRequest;
	        }
	        return null;
		} catch(IllegalStateException exception) {
			return null;
		}
	}
	
	
}
