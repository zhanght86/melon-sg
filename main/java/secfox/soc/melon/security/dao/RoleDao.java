/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.security.dao;

import java.util.List;

import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.security.domain.Role;

/**
 * @since 1.0 2014年9月17日,下午8:25:19
 * 角色管理数据访问接口
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public interface RoleDao extends GenericDao<Role, Long> {
	
	/**
     * 返回所有的角色
     * @return
     */
    List<Role> findAll();
	
}
