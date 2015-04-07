/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.asset.staff.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.asset.staff.dao.CertificateDao;
import secfox.soc.melon.asset.staff.domain.Certificate;
import secfox.soc.melon.asset.staff.service.CertificateService;

/**
 * @since 2014-11-20,下午2:46:11
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Service
public class CertificateServiceImpl extends GenericServiceImpl<Certificate, Long> implements
	CertificateService {

	private CertificateDao dao;
	
	@Inject
	public CertificateServiceImpl(CertificateDao dao) {
		super();
		this.dao = dao;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<Certificate, Long> getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.staff.service.CertificateService#findStaffId(java.lang.Long)
	 */
	@Override
	public List<Certificate> findStaffId(Long staffId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"certify.findByStaffId");
		qt.addParameter("staffId", staffId);
		return findDomains(qt);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.staff.service.CertificateService#findStaffId(java.lang.Long)
	 */
	@Override
	public Certificate findByName(String name,Long staffId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"certify.findByName");
		qt.addParameter("certificate", name);
		qt.addParameter("staffId", staffId);
		return findFirstDomain(qt);
	}
}
