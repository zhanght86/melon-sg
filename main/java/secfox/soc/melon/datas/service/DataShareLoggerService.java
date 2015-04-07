/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
/**
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.datas.service;

import java.util.List;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.datas.domain.DataShareLogger;
import secfox.soc.melon.datas.query.DataShareLoggerPageQuery;
import secfox.soc.melon.persistence.GenericService;

/**
 *
 * @since 1.0 2014-11-10
 * @author <a href="mailto:huangxm@legendsec.com">黄晓梅</a>
 * @version 1.0
 */
public interface DataShareLoggerService extends
		GenericService<DataShareLogger, Long> {
	
	 List<Object[]> shareDatas();
	 
	 Pagination<DataShareLogger> findPage(DataShareLoggerPageQuery pageQuery);
}
