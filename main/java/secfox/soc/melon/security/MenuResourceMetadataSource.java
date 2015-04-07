/*
 * (c) Copyright 2012 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.security;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;

import com.google.common.collect.Sets;

import secfox.soc.melon.security.service.MenuResourceService;

/**
 * @since 0.1 2012-12-24,下午10:15:57
 * 权限的数据库实现
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  0.1 
 */
@Component("securityMetadataSource")
public class MenuResourceMetadataSource implements FilterInvocationSecurityMetadataSource {
	
	private MenuResourceService menuResourceService;

	/**
	 * 构造器
	 * @param menuResourceService
	 */
	@Inject
	public MenuResourceMetadataSource(MenuResourceService menuResourceService) {
		this.menuResourceService = menuResourceService;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.access.SecurityMetadataSource#getAllConfigAttributes()
	 */
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Map<AntPathRequestMatcher, List<ConfigAttribute>> requestMap = menuResourceService.findAllConfigs();
		Set<ConfigAttribute> allAttributes = Sets.newHashSet();
        for (Map.Entry<AntPathRequestMatcher, List<ConfigAttribute>> entry : requestMap.entrySet()) {
            allAttributes.addAll(entry.getValue());
        }
        return allAttributes;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.access.SecurityMetadataSource#getAttributes(java.lang.Object)
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		Map<AntPathRequestMatcher, List<ConfigAttribute>> requestMap = menuResourceService.findAllConfigs();
		final HttpServletRequest request = ((FilterInvocation) object).getRequest();
        for (Map.Entry<AntPathRequestMatcher, List<ConfigAttribute>> entry : requestMap.entrySet()) {
        	//返回拥有的角色
            if (entry.getKey().matches(request)) {
                return entry.getValue();
            }
        }
	    return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.access.SecurityMetadataSource#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

}
