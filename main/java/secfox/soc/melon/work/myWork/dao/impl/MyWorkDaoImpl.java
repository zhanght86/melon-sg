package secfox.soc.melon.work.myWork.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.persistence.impl.GenericDaoImpl;
import secfox.soc.melon.work.myWork.dao.MyWorkDao;
import secfox.soc.melon.work.myWork.domain.MyWork;

/**
 * @author 曹贞杰
 *
 * 2014年11月20日
 * @version 1.0
 */
@Repository
public class MyWorkDaoImpl extends GenericDaoImpl<MyWork, Long> implements MyWorkDao {

	public MyWorkDaoImpl() {
		super(MyWork.class);
	}

}
