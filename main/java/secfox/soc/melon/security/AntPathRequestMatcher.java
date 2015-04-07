/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.RequestMatcher;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import secfox.soc.melon.base.CopyTo;
import secfox.soc.melon.security.domain.MenuResource;
import secfox.soc.melon.web.WebConstants;

import com.google.common.base.Objects;

import static secfox.soc.melon.security.SecurityConstants.MATCH_ALL;
/**
 * @since 0.1 2012-12-24,下午10:15:57
 * 请求地址模式判断器
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  0.1 
 * @see org.springframework.util.AntPathMatcher
 */
public final class AntPathRequestMatcher implements RequestMatcher, CopyTo<AntPathRequestMatcher> {
	
    private final Matcher matcher;
    
    private final String pattern;
    
    private final HttpMethod httpMethod;
    
    private final int order;
    
    //资源对应的ID
    private MenuResource menu;
    
    /**
     * Creates a matcher with the specific pattern which will match all HTTP methods.
     *
     * @param pattern the ant pattern to use for matching
     */
    public AntPathRequestMatcher(String pattern) {
        this(pattern, null, 0);
    }
    
    public AntPathRequestMatcher(String pattern, int order) {
    	this(pattern, null, order);
    }
    
	/**
     * Creates a matcher with the supplied pattern which will match all HTTP methods.
     *
     * @param pattern the ant pattern to use for matching
     * @param httpMethod the HTTP method. The {@code matches} method will return false if the incoming request doesn't
     * have the same method.
     */
    public AntPathRequestMatcher(String pattern, String httpMethod, int order) {
        Assert.hasText(pattern, "Pattern cannot be null or empty");
        //添加Web上下文前缀
        if(!pattern.startsWith( WebConstants.CONTEXT_ROOT)) {
        	pattern = WebConstants.CONTEXT_ROOT + pattern;
        }
        pattern = WebConstants.APP_CONTEXT_ROOT + pattern;
        //通用匹配则忽略
        if (pattern.equals(MATCH_ALL) || pattern.equals(SecurityConstants.WILD_CHAR_ALL)) {
            pattern = MATCH_ALL;
            matcher = null;
        } else {
            pattern = pattern.toLowerCase();//切换到小写模式,不区分大小写
            if (pattern.endsWith(MATCH_ALL) 
            		&& pattern.indexOf(SecurityConstants.PARAM_SPLIT) == -1//如果不包含参数 
            		&& pattern.indexOf(SecurityConstants.WILD_CHAR) == pattern.length() - 2) {//如果再没有包含其它的*
                matcher = new SubpathMatcher(pattern.substring(0, pattern.length() - 3));
            } else {
                matcher = new SpringAntMatcher(pattern);
            }
        }
        this.pattern = pattern;
        this.httpMethod = StringUtils.hasText(httpMethod) ? HttpMethod.valueOf(httpMethod) : null;
        this.order = order;
    }
    
    /**
     * 判断请求地址是否合法
     * @param url 请求地址
     * @return
     */
    public boolean matches(String url) {
    	return matcher.matches(url);
    }
    
    /**
     * 比较地址是否完全相等
     * @param url
     * @return
     */
    public boolean equalTo(String url) {
    	return pattern.equals(url);
    }
    
	/**
	 * @return the menu
	 */
	public MenuResource getMenu() {
		return menu;
	}

	/**
	 * @param menu the menu to set
	 */
	public void setMenu(MenuResource menu) {
		this.menu = menu;
	}

	/**
     * Returns true if the configured pattern (and HTTP-Method) match those of the supplied request.
     *
     * @param request the request to match against. The ant pattern will be matched against the
     *    {@code servletPath} + {@code pathInfo} of the request.
     */
    public boolean matches(HttpServletRequest request) {
        if (httpMethod != null && httpMethod != HttpMethod.valueOf(request.getMethod())) {
            return false;
        }
        if (pattern.equals(MATCH_ALL)) {
            return true;
        }
        String url = getRequestPath(request);
        String matchAll = request.getParameter(WebConstants.SUB_PATH);
        //比较特殊的参数
       
        if(org.apache.commons.lang3.StringUtils.isNotBlank(matchAll)) {
        	return pattern.contains((WebConstants.SUB_PATH + "=" + matchAll).toLowerCase());
        }
        return matcher.matches(url);
    }

    private String getRequestPath(HttpServletRequest request) {
        String url = request.getServletPath();
        if (request.getPathInfo() != null) {
            url += request.getPathInfo();
        }
        url = url.toLowerCase();
        return url;
    }

    public String getPattern() {
        return pattern;
    }

    
    
    /* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 * @author <a href="mailto:gaobg@legendsec.com">高保国</a>
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pattern == null) ? 0 : pattern.hashCode());
		return result;
	}

	/**
	 * 只要地址相同,就认为模式匹配是相等的
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AntPathRequestMatcher other = (AntPathRequestMatcher) obj;
		if (pattern == null) {
			if (other.pattern != null)
				return false;
		} else if (!pattern.equals(other.pattern))
			return false;
		return true;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
	 	return Objects.toStringHelper(this)
			      .add("pattern", this.pattern)
			      .add("httpMethod", this.httpMethod)
			      .add("order", this.order)
			      .toString();
	}

	/**
	 * 判断与地址是否匹配
	 * @since 1.0 2014年10月3日,上午9:12:47
	 * 
	 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
	 * @version  1.0
	 */
    private static interface Matcher {
        boolean matches(String path);
    }

    /**
     * 
     * @since 1.0 2014年10月3日,上午9:12:41
     * 模式地址匹配
     * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
     * @version  1.0
     */
    private static class SpringAntMatcher implements Matcher {
    	
        private static final AntPathMatcher antMatcher = new AntPathMatcher();

        private final String pattern;

        private SpringAntMatcher(String pattern) {
            this.pattern = pattern;
        }

        public boolean matches(String path) {
            return antMatcher.match(pattern, path);
        }
        
        public String toString() {
        	return Objects.toStringHelper(this)
        				  .add("pattern", pattern)
        				  .add("antMatcher", antMatcher)
        				  .toString();
        }
    }

    /**
     * Optimized matcher for trailing wildcards
     */
    private static class SubpathMatcher implements Matcher {
    	
        private final String subpath;
        
        private final int length;
        
        /**
         * 确认不再包含*
         * @param subpath
         */
        private SubpathMatcher(String subpath) {
            assert !subpath.contains(SecurityConstants.CONTEXT_ROOT);
            this.subpath = subpath;
            this.length = subpath.length();
        }

        /**
         * 确认地址完全匹配或者结尾多一个/
         */
        public boolean matches(String path) {
            return path.startsWith(subpath) && (path.length() == length || path.charAt(length) == SecurityConstants.CONTEXT_ROOT_CHAR);
        }
        
        public String toString() {
        	return Objects.toStringHelper(this)
        				  .add("subpath", subpath)
        				  .add("length", length)
        				  .toString();
        }
    }

	/* (non-Javadoc)
	 * @see secfox.soc.melon.base.CopyTo#copy()
	 */
	@Override
	public AntPathRequestMatcher copy() {
		return new AntPathRequestMatcher(pattern, null, order);
	}

	/**
	 * @return the matcher
	 */
	public Matcher getMatcher() {
		return matcher;
	}

	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}
	
}
