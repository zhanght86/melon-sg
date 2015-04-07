/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.home.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.home.dao.PortalViewDao;
import secfox.soc.melon.home.domain.PortalView;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 * @since 2014-10-23,上午11:28:03
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Repository
class PortalViewDaoImpl extends GenericDaoImpl<PortalView, Long> implements	PortalViewDao {

	public PortalViewDaoImpl() {
		super(PortalView.class);
	}

}
