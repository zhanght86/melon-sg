/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.report.service;

import java.util.List;

import secfox.soc.melon.persistence.GenericService;
import secfox.soc.melon.report.domain.ReportFile;

/**
 * @since 2015-3-13,下午1:56:57
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public interface ReportFileService extends GenericService<ReportFile, Long> {
	
	/**
	 * 按业务id查询
	 * @param businessId
	 * @return
	 */
	ReportFile findByBusinessId(String businessId);
	
	/**
	 * 按businessId查询
	 * @param businessId
	 * @return
	 */
	List<ReportFile> findFile(String businessId);

}
