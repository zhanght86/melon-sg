/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.intergration.dao;

import java.util.List;
import java.util.Map;

import secfox.soc.melon.asset.intergration.domain.DeviceLogStatus;

/**
 * @since @2014-12-3,@上午11:59:53
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
public interface DeviceLogJdbcDao {

	public abstract List<DeviceLogStatus> findBySql(String sql,Map<String, Object> params);
}


