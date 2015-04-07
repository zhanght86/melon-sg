package secfox.soc.melon.security.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.security.dao.HistoryPassDao;
import secfox.soc.melon.security.domain.HistoryPass;
import secfox.soc.melon.security.service.HistoryPassService;

@Service
public class HistoryPassServiceImpl extends GenericServiceImpl<HistoryPass, Long> implements
		HistoryPassService {
	
	private HistoryPassDao dao;
	
	@Inject
	public HistoryPassServiceImpl(HistoryPassDao dao) {
		super();
		this.dao = dao;
	}

	@Override
	protected GenericDao<HistoryPass, Long> getDao() {
		return dao;
	}

	@Override
	public List<HistoryPass> findHistory(Long accountId, int num) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "historyPass.findHistory");
		qt.addParameter("accountId", accountId);
		qt.setFirstResult(0);
		qt.setMaxResults(num);
		return findDomains(qt);
	}

}
