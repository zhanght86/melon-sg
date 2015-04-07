/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.report.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.report.dao.ReportFileDao;
import secfox.soc.melon.report.domain.ReportFile;
import secfox.soc.melon.report.service.ReportFileService;

/**
 * @since 2015-3-13,下午1:57:49
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Service
public class ReportFileServiceImpl extends GenericServiceImpl<ReportFile, Long> implements ReportFileService {

	private ReportFileDao dao;
	
	@Inject
	public ReportFileServiceImpl(ReportFileDao dao) {
		super();
		this.dao = dao;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<ReportFile, Long> getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.report.service.ReportFileService#findByBusinessId(java.lang.String)
	 */
	@Override
	public ReportFile findByBusinessId(String businessId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select file from ReportFile file where file.businessId = :id");
		qt.addParameter("id", businessId);
		return findFirstDomain(qt);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.report.service.ReportFileService#findFile(java.lang.String)
	 */
	@Override
	public List<ReportFile> findFile(String businessId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "reportFile.findFile");
		qt.addParameter("businessId", businessId);
		return findDomains(qt);
	}

}
