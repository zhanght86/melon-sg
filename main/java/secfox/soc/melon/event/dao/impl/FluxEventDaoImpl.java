package secfox.soc.melon.event.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import secfox.soc.melon.alarm.dao.impl.JdbcGenericDaoImpl;
import secfox.soc.melon.event.dao.FluxEventDao;

/**
 *
 * @since 1.0 2014年12月6日上午10:33:46
 * @author <a href="mailto:xugfa@legendsec.com">徐广飞</a>
 * @version 1.0
 */
@Repository("fluxEventDao")
public class FluxEventDaoImpl extends JdbcGenericDaoImpl implements FluxEventDao {

	@Inject
	public FluxEventDaoImpl(@Named("mysqlDataBase") DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	public List<Object[]> findInFlux(String ip, Map<String, Object> params){
		StringBuffer buffer = new StringBuffer("select * from (SELECT t.time,IFNULL(t.numericValue,0)/1024 as numericValue FROM md_idsinflux t where indexid=10 ");
		if(ip != null){
			buffer.append(" and t.ip ='"+ip+"'");
		}
		buffer.append(" order by t.time desc limit 200) aa order by aa.time asc ");
		System.out.println(buffer.toString());
		List<Object[]> list = new ArrayList<Object[]>();
		try{
		list = find(buffer.toString(), params, new RowMapper<Object[]>() {
			
			@Override
			public Object[] mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				Object[] ob = new Object[2];
				ob[0] = resultSet.getLong("time");
				ob[1] = resultSet.getLong("numericValue");
				return ob;
			}
		});
		}catch(Exception e){
			e.printStackTrace();
		}
		return list;
	}
	
	public List<Object[]> findOutFlux(String ip, Map<String, Object> params){
		StringBuffer buffer = new StringBuffer("SELECT t.time,IFNULL(t.numericValue,0) as numericValue FROM md_idsoutflux t where  indexid=10");
		if(ip != null){
			buffer.append(" and t.ip ='"+ip+"'");
		}
		buffer.append(" order by t.time limit 200 ");
		List<Object[]> list = new ArrayList<Object[]>();
		list = find(buffer.toString(), params, new RowMapper<Object[]>() {
			
			@Override
			public Object[] mapRow(ResultSet resultSet, int rowNum) throws SQLException {
				Object[] ob = new Object[2];
				ob[0] = resultSet.getLong("time");
				ob[1] = resultSet.getLong("numericValue");
				return ob;
			}
		});
		return list;
	}
	
}
