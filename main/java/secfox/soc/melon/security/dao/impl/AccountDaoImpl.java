/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.security.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.persistence.impl.GenericDaoImpl;
import secfox.soc.melon.security.dao.AccountDao;
import secfox.soc.melon.security.domain.Account;

/**
 * 用户管理数据存储实现类
 * @since 2014-9-23,上午19:34:09
 * @author <a href="mailto:liubing@legendsec.com>刘冰</a>
 * @version  1.0
 */
@Repository
public class AccountDaoImpl extends GenericDaoImpl<Account, Long> implements AccountDao {

	public AccountDaoImpl() {
		super(Account.class);
	}

}
