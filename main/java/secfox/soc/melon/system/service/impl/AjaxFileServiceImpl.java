/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.system.dao.AjaxFileDao;
import secfox.soc.melon.system.domain.AjaxFile;
import secfox.soc.melon.system.service.AjaxFileService;


/**
 * @since 1.0 2014年9月24日,上午11:00:18
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Service
public class AjaxFileServiceImpl extends GenericServiceImpl<AjaxFile, Long> implements AjaxFileService {
	
	private AjaxFileDao dao;
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<AjaxFile, Long> getDao() {
		return dao;
	}
	
	@Inject
	public AjaxFileServiceImpl(AjaxFileDao dao) {
		this.dao = dao;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.commons.service.AjaxFileService#findFill(java.lang.Long)
	 */
	@Override
	public List<AjaxFile> findFile(String businessId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "ajaxFile.findFile");
		qt.addParameter("businessId", businessId);
		return findDomains(qt);
	}
	
}
