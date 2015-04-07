/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.service;

import java.util.List;

import secfox.soc.melon.asset.domain.DeviceFilter;
import secfox.soc.melon.asset.query.DeviceFilterPageQuery;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.GenericService;
import secfox.soc.melon.work.myWork.domain.MyWork;

/**
 *
 * @since 1.0 2014年11月18日下午7:50:34
 * @author <a href="mailto:huangxm@legendsec.com">黄晓梅</a>
 * @version 1.0
 */

public interface DeviceFilterService extends GenericService<DeviceFilter, Long> {

	Pagination<DeviceFilter> findPages(DeviceFilterPageQuery pageQuery);

	List<DeviceFilter> findList();
	
	List<MyWork> findTask();
}

