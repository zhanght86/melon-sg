package secfox.soc.business.message.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.business.message.dao.SysMailDao;
import secfox.soc.business.message.domain.SysMail;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 * @since 1.0 2014-10-20,下午3:21:42
 * 
 * @author  <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0 
 */
@Repository
public class SysMailDaoImpl extends GenericDaoImpl<SysMail, Long> implements
		SysMailDao {

	public SysMailDaoImpl(){
		super (SysMail.class);
	}

}

