/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.intergration.dao.impl;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.melon.alarm.dao.impl.JdbcGenericDaoImpl;
import secfox.soc.melon.asset.intergration.dao.DeviceLogJdbcDao;
import secfox.soc.melon.asset.intergration.domain.DeviceLogStatus;

/**
 * @since @2014-12-3,@下午12:04:04
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
@Repository
public class DeviceLogJdbcDaoImpl extends JdbcGenericDaoImpl implements DeviceLogJdbcDao{

	@Inject
	public DeviceLogJdbcDaoImpl(@Named("mysqlDataBase") DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	@Override
	@Transactional(readOnly = false)
	public List<DeviceLogStatus> findBySql(String sql,Map<String, Object> params) {
		return find(sql, params,new  RowMapper<DeviceLogStatus>(){

			@Override
			public DeviceLogStatus mapRow(ResultSet rs, int rowNum) throws SQLException {
				DeviceLogStatus dls = new DeviceLogStatus();
				//dls.setId(rs.getLong("id"));
				dls.setIp(rs.getString("ip"));
				dls.setStatus(rs.getString("status"));
				dls.setReceiveType(rs.getString("receiveType"));
				dls.setActive(rs.getString("isActive"));
				dls.setCounts(rs.getInt("counts"));
				//rs.getString("lastLogTime");
				return dls;
			}
			
		});
	}


}


