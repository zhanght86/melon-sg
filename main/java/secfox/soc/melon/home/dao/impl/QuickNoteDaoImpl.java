package secfox.soc.melon.home.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.home.dao.QuickNoteDao;
import secfox.soc.melon.home.domain.QuickNote;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 * @since 1.0 2014-10-31,下午4:09:52
 * 
 * @author  <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0 
 */
@Repository
public class QuickNoteDaoImpl extends GenericDaoImpl<QuickNote, Long>
       implements  QuickNoteDao {

	public QuickNoteDaoImpl() {
		super(QuickNote.class);
	}
	
}

