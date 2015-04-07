package secfox.soc.business.message.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import secfox.soc.business.message.dao.SysMailReplyDao;
import secfox.soc.business.message.domain.SysMailReply;
import secfox.soc.business.message.service.SysMailReplyService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;

/**
 * @since 1.0 2014-10-27,下午7:29:25
 * 
 * @author  <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0 
 */
@Service
public class SysMailReplyServiceImpl extends
		GenericServiceImpl<SysMailReply, Long> implements SysMailReplyService {

	private SysMailReplyDao sysMailReplyDao;
	
	
	@Inject
	public SysMailReplyServiceImpl(SysMailReplyDao sysMailReplyDao) {
		super();
		this.sysMailReplyDao = sysMailReplyDao;
	}

	@Override
	protected GenericDao<SysMailReply, Long> getDao() {
		return sysMailReplyDao;
	}
	
	@Override
	public List<SysMailReply> findList(Long mailId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "reply.findReplyByMailId");
		qt.addParameter("mailId", mailId);
		return findDomains(qt);
	}
	
}

