/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.home.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.home.dao.PortalLayoutDao;
import secfox.soc.melon.home.domain.PortalLayout;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 * @since 2014-10-23,上午11:25:37
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Repository
public class PortalLayoutDaoImpl extends GenericDaoImpl<PortalLayout, Long> implements PortalLayoutDao {

	public PortalLayoutDaoImpl() {
		super(PortalLayout.class);
	}
}
