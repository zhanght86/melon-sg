/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.asset.secpass.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.asset.secpass.dao.SecPassDao;
import secfox.soc.melon.asset.secpass.domain.SecPass;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 * @since 2014-11-17,下午3:58:39
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Repository
public class SecPassDaoImpl extends GenericDaoImpl<SecPass, Long> implements SecPassDao {
	
	public SecPassDaoImpl() {
		super(SecPass.class);
	}

}
