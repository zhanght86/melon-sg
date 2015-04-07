package secfox.soc.melon.security.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.persistence.impl.GenericDaoImpl;
import secfox.soc.melon.security.dao.LoginRecordDao;
import secfox.soc.melon.security.domain.LoginRecord;

@Repository
public class LoginRecordDaoImpl extends GenericDaoImpl<LoginRecord, Long> implements LoginRecordDao {

	public LoginRecordDaoImpl() {
		super(LoginRecord.class);
	}

}
