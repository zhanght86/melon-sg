/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.security.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;
import secfox.soc.melon.security.dao.RoleDao;
import secfox.soc.melon.security.domain.Account;
import secfox.soc.melon.security.domain.Role;

/**
 * 
 * @since 2014-9-18,上午11:40:16
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Repository
public class RoleDaoImpl extends GenericDaoImpl<Role, Long> implements RoleDao {

	public RoleDaoImpl() {
		super(Role.class);
	}

	/* 
	 * 双向关联(放弃端)删除自身必须首先删除关联关系,然后删除自己
	 * (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericDaoImpl#remove(java.io.Serializable)
	 */
	@Override
	public void remove(Long pk) {
		Role role = getReference(pk);
		//先删除关联帐户里的角色
		for(Account account : role.getAccounts()) {
			account.removeRole(role);
		}
		//最后删除自己
		super.remove(pk);
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.security.service.RoleService#findAll()
	 */
	@Override
	public List<Role> findAll() {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "Role.findAll");
		qt.setCachable(true);
        return findDomains(qt);
	}

}
