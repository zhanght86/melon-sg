package secfox.soc.business.check.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.business.check.dao.RouterReportTaskDao;
import secfox.soc.business.check.domain.Router;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;
/**
 * @since 1.0 2014年5月16日
 * @author <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0
 */
@Repository
public class RouterReportTaskDaoImpl extends
		GenericDaoImpl<Router, Long>  implements RouterReportTaskDao {
	
	public RouterReportTaskDaoImpl() {
		super(Router.class);
	}
}
