/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.asset.staff.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.persistence.impl.GenericDaoImpl;
import secfox.soc.melon.asset.staff.dao.CertificateDao;
import secfox.soc.melon.asset.staff.domain.Certificate;

/**
 * @since 2014-11-21,下午12:31:28
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Repository
public class CertificateDaoImpl extends GenericDaoImpl<Certificate, Long> implements
	CertificateDao {

	public CertificateDaoImpl() {
		super(Certificate.class);
	}

}
