package secfox.soc.melon.security.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.persistence.impl.GenericDaoImpl;
import secfox.soc.melon.security.dao.HistoryPassDao;
import secfox.soc.melon.security.domain.HistoryPass;

@Repository
public class HistoryPassDaoImpl extends GenericDaoImpl<HistoryPass, Long> implements HistoryPassDao {

	public HistoryPassDaoImpl() {
		super(HistoryPass.class);
	}
}
