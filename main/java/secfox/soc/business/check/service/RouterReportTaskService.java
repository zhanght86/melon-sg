package secfox.soc.business.check.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import secfox.soc.business.check.domain.Router;
import secfox.soc.business.check.query.RouterReportTaskPageQuery;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.GenericService;
/**
 * @since 1.0 2014年5月16日
 * @author <a href="mailto:fengxy@legendsec.com">冯夏彦</a>
 * @version  1.0
 */
@Service
public interface RouterReportTaskService extends
		GenericService<Router, Long> {
	
	
	/**
	 * 获取设备名称
	 * @param assetInfo
	 * @param rr
	 * @param assetService
	 */
	//void conserve(Asset assetInfo,RouterReportTask rr,AssetService assetService);
	/**
     * 分页查询
     * @param query
     * @return
     */
    Pagination<Router> findPages(RouterReportTaskPageQuery query);
    
    /**
     * 所有路由器上报信息
     * @param id
     * @param user
     * @return
     */
    List<Router> findbytaskId(Long id);
    
    /**
     * 按ip查看路由器设备cpu5秒钟利用率
     * @param ip
     * @return
     */
    List<Router> findByIpRate1(String ip,Date time);
    
   /**
     * 按ip查看路由器设备cpu5分钟利用率
     * @param ip
     * @return
     */
    List<Router> findByIpRate3(String ip,Date time);
}
