package secfox.soc.melon.event.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import secfox.soc.melon.alarm.dao.impl.JdbcGenericDaoImpl;
import secfox.soc.melon.event.dao.EventJdbcDao;

/**
 *
 * @since 1.0 2014年12月4日上午11:36:01
 * @author <a href="mailto:xugfa@legendsec.com">徐广飞</a>
 * @version 1.0
 */
@Repository("eventJdbcDao")
public class EventJdbcDaoImpl extends JdbcGenericDaoImpl implements EventJdbcDao {
	 protected Logger logger = LoggerFactory.getLogger(getClass());
	@Inject
	public EventJdbcDaoImpl(@Named("mysqlDataBase") DataSource dataSource) {
		setDataSource(dataSource);
	}
	
	@Override
	public List<Map<String,Object>> findSourceAdd(String ip,String tableName, Map<String, Object> params) {
		StringBuffer buffer = new StringBuffer("select IFNULL(INET6_NTOA(saddr),'空') as saddr,count(*) as saddrNum from ");
		buffer.append(tableName).append(" where 1=1");
		if(ip != null && !"".equals(ip)){
			buffer.append(" and (INET6_NTOA(dAddr) ='"+ip+"'").append(" or INET6_NTOA(saddr) ='"+ip+"')");
		}
		buffer.append(" group by saddr");
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try{
				list = find(buffer.toString(), params, new RowMapper<Map<String,Object>>() {
		
				@Override
				public Map<String,Object> mapRow(ResultSet resultSet, int rowNum) throws SQLException {
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("category", 0);
					map.put("name", resultSet.getString("saddr"));
					map.put("value", resultSet.getString("saddrNum"));
					return map;
				}
			});
		}catch(Exception e){
			logger.info("动态生成的表ed_********（日期）不存在。",e);
		}
		return list;
	}
	
	@Override
	public List<Map<String,Object>> findTargetAdd(String ip,String tableName, Map<String, Object> params) {
		StringBuffer buffer = new StringBuffer("select IFNULL(INET6_NTOA(dAddr),'空') as dAddr,count(*) as daddrNum from ");
		buffer.append(tableName).append(" where 1=1");
		if(ip != null && !"".equals(ip)){
			buffer.append(" and (INET6_NTOA(dAddr) ='"+ip+"'").append(" or INET6_NTOA(saddr) ='"+ip+"')");
		}
		buffer.append(" group by dAddr");
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try{
			list =find(buffer.toString(), params, new RowMapper<Map<String,Object>>() {

				@Override
				public Map<String,Object> mapRow(ResultSet resultSet, int rowNum) throws SQLException {
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("category", 0);
					map.put("name", resultSet.getString("dAddr"));
					map.put("value", resultSet.getString("daddrNum"));
					return map;
				}
			});
		}catch(Exception e){
			logger.info("动态生成的表ed_********（日期）不存在。",e);
		}
		
		return list;
	}
	
	@Override
	public List<Map<String,Object>> findTarAndsourAdd(String ip,String tableName, Map<String, Object> params) {
		StringBuffer buffer = new StringBuffer("select IFNULL(INET6_NTOA(saddr),'空') as saddr,IFNULL(INET6_NTOA(dAddr),'空') as dAddr,count(*) as daddrNum from ");
		buffer.append(tableName).append(" where 1=1");
		if(ip != null && !"".equals(ip)){
			buffer.append(" and (INET6_NTOA(dAddr) ='"+ip+"'").append(" or INET6_NTOA(saddr) ='"+ip+"')");
		}
		buffer.append(" group by saddr,dAddr");
		List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
		try{
				list = find(buffer.toString(), params, new RowMapper<Map<String,Object>>() {
		
				@Override
				public Map<String,Object> mapRow(ResultSet resultSet, int rowNum) throws SQLException {
					Map<String,Object> map = new HashMap<String,Object>();
					map.put("source", resultSet.getString("saddr"));
					map.put("target", resultSet.getString("dAddr"));
					map.put("weight", resultSet.getString("daddrNum"));
					return map;
				}
			});
		}catch(Exception e){
			logger.info("动态生成的表ed_********（日期）不存在。",e);
		}
			return list;
	}
	
}
