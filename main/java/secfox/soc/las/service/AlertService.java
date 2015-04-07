package secfox.soc.las.service;

import secfox.soc.las.domain.Alert;
import secfox.soc.las.domain.AlertRule;
import secfox.soc.las.query.AlertPageQuery;
import secfox.soc.melon.base.Pagination;

public interface AlertService {
	
	public Pagination<Alert> list(AlertPageQuery pageQuery);
	
	/**
	 * 分页查询
	 */
	public Pagination<Alert> pagination(AlertPageQuery pageQuery);
	
	/**
	 * 查找单条信息
	 */
	public AlertRule find(String id);

}
