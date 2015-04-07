package secfox.soc.business.message.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.business.message.dao.SysMailStateDao;
import secfox.soc.business.message.domain.SysMailState;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 * @since 1.0 2014-10-20,下午3:21:42
 * 
 * @author  <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0 
 */
@Repository
public class SysMailStateDaoImpl extends GenericDaoImpl<SysMailState, Long> implements
		SysMailStateDao {

	public SysMailStateDaoImpl(){
		super (SysMailState.class);
	}

}

