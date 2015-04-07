/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.security.formatter;

import java.text.ParseException;
import java.util.Locale;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import secfox.soc.melon.security.domain.Role;
import secfox.soc.melon.security.service.RoleService;

/**
 * @since 1.0 2013-5-7,上午8:58:51
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Component("roleFormatter")
public class RoleFormatter implements Formatter<Role> {

    private RoleService roleService;
    
    /**
     * @param roleService
     */
    @Inject
    public RoleFormatter(RoleService roleService) {
        super();
        this.roleService = roleService;
    }

    /* (non-Javadoc)
     * @see org.springframework.format.Printer#print(java.lang.Object, java.util.Locale)
     */
    @Override
    public String print(Role role, Locale arg1) {
        if(role != null) {
            return String.valueOf(role.getId());
        }
        return null;
    }

    /* (non-Javadoc)
     * @see org.springframework.format.Parser#parse(java.lang.String, java.util.Locale)
     */
    @Override
    public Role parse(String roleId, Locale arg1) throws ParseException {
        if(StringUtils.isNotBlank(roleId)) {
            try{
                return roleService.find(Long.parseLong(roleId));
            } catch(NumberFormatException ex) {
                return null;
            }
        }
        return null;
    }
    
}
