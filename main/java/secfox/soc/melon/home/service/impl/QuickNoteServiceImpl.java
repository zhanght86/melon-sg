package secfox.soc.melon.home.service.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.melon.home.dao.QuickNoteDao;
import secfox.soc.melon.home.domain.QuickNote;
import secfox.soc.melon.home.service.QuickNoteService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.security.util.SecurityContextUtils;

/**
 * @since 1.0 2014-10-31,下午4:13:04
 * 
 * @author  <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0 
 */
@Service
public class QuickNoteServiceImpl extends GenericServiceImpl<QuickNote, Long>
		implements QuickNoteService {

	private QuickNoteDao quickNoteDao;
	
	@Inject
	public QuickNoteServiceImpl(QuickNoteDao quickNoteDao){
		super();
		this.quickNoteDao = quickNoteDao;
	}
	
	
	@Override
	protected GenericDao<QuickNote, Long> getDao() {
		return this.quickNoteDao;
	}

	@Override
	public int count(Long type, Long businessId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "qnote.ListNotes");
		qt.addParameter("type", type);
		qt.addParameter("businessId", businessId);
		List<QuickNote> qls = findDomains(qt);
		return qls.size();
	}


	@Override
	public Long delByBussId(Long type, Long businessId, Long userId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "qnote.findNoteUsers");
		qt.addParameter("type", type);
		qt.addParameter("businessId", businessId);
		qt.addParameter("userId", userId);
		QuickNote qn= findFirstDomain(qt);
		Long id = qn.getId();
		return id;
	}


	/* (non-Javadoc)
	 * @see secfox.soc.melon.home.service.QuickNoteService#saveNotes(javax.servlet.http.HttpServletRequest)
	 */
	@Override
    @Transactional(readOnly = false)
	public void saveNotes(HttpServletRequest request,Long type,Long businessId) {
		QuickNote quickNote = new QuickNote();
		int level = Integer.valueOf(request.getParameter("level"));
		String remarks = request.getParameter("remarks");
		
		quickNote.setCreateTime(new Date());
		quickNote.setCreator(SecurityContextUtils.getCurrentUserInfo());
		quickNote.setType(type);
		quickNote.setBusinessId(businessId);
		quickNote.setLevel(level);
		quickNote.setRemarks(remarks);
		quickNoteDao.persist(quickNote);
	}


	/* (non-Javadoc)
	 * @see secfox.soc.melon.home.service.QuickNoteService#find(java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<QuickNote> find(Long type, Long businessId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "qnote.ListNotes");
		qt.addParameter("type", type);
		qt.addParameter("businessId", businessId);
		return findDomains(qt);
	}


	/* (non-Javadoc)
	 * @see secfox.soc.melon.home.service.QuickNoteService#findByUserId(java.lang.Long)
	 */
	@Override
	public List<QuickNote> findByUserId(Long userId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "qnote.findByUserId");
		qt.addParameter("userId", userId);
		return findDomains(qt);
	}
	

	@Override
	public List<QuickNote> findNotedDevices(Long userId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "qnote.findNotedDevices");
		qt.addParameter("userId", userId);
		return findDomains(qt);
	}
	
	@Override
	public List<QuickNote> findNotedInfoSystems(Long userId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "qnote.findNotedInfoSystems");
		qt.addParameter("userId", userId);
		return findDomains(qt);
	}
}

