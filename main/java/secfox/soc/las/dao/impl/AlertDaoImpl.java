package secfox.soc.las.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import secfox.soc.las.dao.AlertDao;
import secfox.soc.las.domain.Alert;
import secfox.soc.melon.alarm.dao.impl.JdbcGenericDaoImpl;

@Repository
public class AlertDaoImpl extends JdbcGenericDaoImpl implements AlertDao {
	
	@Inject
	public AlertDaoImpl(@Named("lasDataBase") DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	@Override
	public List<Alert> findBySql(String sql, Map<String, Object> params) {
		return find(sql, params, new RowMapper<Alert>() {

			@Override
			public Alert mapRow(ResultSet result, int num) throws SQLException {
				Alert alert = new Alert();
				alert.setId(result.getString("Id"));
				alert.setName(result.getString("Name"));
				alert.setCondition(result.getString("Rule_content"));
				alert.setDescription(result.getString("Description"));
				alert.setCreateTime(new Date(result.getLong("Createtime")));
				alert.setCreator(result.getLong("Creator"));
				alert.setUpdateTime(new Date(result.getLong("Updatetime")));
				alert.setMender(result.getLong("Mender"));
				alert.setType(result.getInt("Type"));
				alert.setParentId(result.getInt("Parent_id"));
				return alert;
			}
			
		});
	}

}
