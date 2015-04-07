package secfox.soc.melon.security.service.impl;

import java.util.Date;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.security.dao.LoginRecordDao;
import secfox.soc.melon.security.domain.LoginRecord;
import secfox.soc.melon.security.service.LoginRecordService;

@Service
public class LoginRecordServiceImpl extends GenericServiceImpl<LoginRecord, Long> implements
		LoginRecordService {

	private LoginRecordDao dao;
	
	@Inject
	public LoginRecordServiceImpl(LoginRecordDao dao) {
		super();
		this.dao = dao;
	}
	
	@Override
	protected GenericDao<LoginRecord, Long> getDao() {
		return dao;
	}

	@Override
	public LoginRecord findByName(String name) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "loginRecord.findByAcc");
		qt.addParameter("account", name);
		return findFirstDomain(qt);
	}

	@Override
	@Transactional
	public void insertRecord(String name) {
		LoginRecord record = findByName(name);
		if(record == null) {
			//插入记录
			LoginRecord tmp = new LoginRecord(name, 1, new Date());
			persist(tmp);
		} else {
			//修改记录
			int count = record.getCount();
			record.setCount(count+1);
			record.setUpdateTime(new Date());
			merge(record);
		}
		
	}

	@Override
	@Transactional
	public void removeRecord(String name) {
		LoginRecord record = findByName(name);
		if(record != null) {
			remove(record);
		}
	}

}
