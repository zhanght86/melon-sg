package secfox.soc.business.message.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import secfox.soc.business.message.dao.SysMailStateDao;
import secfox.soc.business.message.domain.SysMailState;
import secfox.soc.business.message.service.SysMailStateService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;

/**
 * @since 1.0 2014-10-27,下午6:29:28
 * 
 * @author  <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0 
 */
@Service
public class SysMailStateServiceImpl extends
		GenericServiceImpl<SysMailState, Long> implements SysMailStateService {

	
	private SysMailStateDao sysMaildStateDao;
	
	/**
	 * 
	 */
	@Inject
	public SysMailStateServiceImpl(SysMailStateDao sysMaildStateDao) {
		super();
		this.sysMaildStateDao = sysMaildStateDao;
	}

	
	
	@Override
	protected GenericDao<SysMailState, Long> getDao() {
		return sysMaildStateDao;
	}
	
	
	@Override
	public SysMailState findByMailIdAndUserId(Long mailId, Long userId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "mailState.findByMailIdAndUserId");
		qt.addParameter("mailId",mailId);
		qt.addParameter("userId",userId);
		return findFirstDomain(qt);
	}

	@Override
	public List<SysMailState> finMailStatesById(Long mailId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"mailState.finMailStatesById");
		qt.addParameter("mailId",mailId);
		return findDomains(qt);
	}

}

