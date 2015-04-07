package secfox.soc.las.asset.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import secfox.soc.data.audit.risk.DeviceVulnModel;
import secfox.soc.las.asset.bean.T_VulnImportRelationBean;
import secfox.soc.las.asset.dao.VulnImportRelationDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

@Repository
public class VulnImportRelationDaoimpl extends
		GenericDaoImpl<T_VulnImportRelationBean, Long> implements
		VulnImportRelationDao {
	private static final SimpleDateFormat tformat = new SimpleDateFormat(
			"yyyyMMdd");
	private static final int VULN_SEVERITY_HIGH = 3;

	public VulnImportRelationDaoimpl() {
		super(T_VulnImportRelationBean.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<DeviceVulnModel> getVulnListByDevID(long devid,
			long importTime) {
		// TODO Auto-generated method stub
		ArrayList<DeviceVulnModel> resultList = new ArrayList<DeviceVulnModel>();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String sql = "select id ,importTime from T_VulnImportRelationBean where deviceID="
				+ ":deviceID order by importTime desc";
		QueryTemplate qry = QueryTemplate.create(QueryType.JQL, sql);
		qry.addParameter("deviceID", devid);
		List<Object[]> results = find(qry);
		if (!results.isEmpty())// 表t_vulnimportrelation中有该资产的上一次的纪录，如果没有则创建表t_vulnfound_
		{
			long lastImportTime = (Long) results.get(0)[1];
			if (lastImportTime != importTime)
				createVulnFoundTable(importTime);
			String dt = df.format(lastImportTime);
			String vSql = "select * from t_vulnfound_" + dt
					+ "  where deviceID=:deviceID and foundTime=:foundTime";

			qry = QueryTemplate.create(QueryType.SQL, vSql);
			qry.addParameter("deviceID", devid);
			qry.addParameter("foundTime", lastImportTime);

			results = find(qry);
			//				} catch (SQLException e) // 如果没有当前日期的表，则创建
			//				{
			//					if (e.getMessage().matches("Table.*doesn't exist")) {
			//						createVulnFoundTable(importTime);
			//						return new ArrayList<DeviceVulnModel>();
			//					}
			//				}
			for (Object[] obj : results) {
				DeviceVulnModel model = new DeviceVulnModel();
				model.setID(Long.parseLong(obj[0].toString()));
				model.setDeviceID(Long.parseLong(obj[1].toString()));
				model.setVulnID(Long.parseLong(obj[2].toString()));
				model.setStatus(Integer.parseInt(obj[3].toString()));
				model.setFoundTime(Long.parseLong(obj[4].toString()));
				model.setCause(obj[5].toString());
				model.setSolution(obj[6].toString());
				model.setDescription(obj[7].toString());
				model.setTag(Integer.parseInt(obj[8].toString()));
				model.setSource(obj[9].toString());
				model.setHistory(obj[10].toString());
				resultList.add(model);
			}
		} else // 需要创建表
		{
			createVulnFoundTable(importTime);
			return new ArrayList<DeviceVulnModel>();
		}
		return resultList;
	}

	/**
	 * @param importTime
	 *            根据时间创建t_vulnfound表
	 * @throws SQLException
	 */
	private void createVulnFoundTable(long importTime) {
		importTime = System.currentTimeMillis();
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String dt = df.format(importTime);
		StringBuffer sb = new StringBuffer();
		sb.append("CREATE TABLE IF NOT EXISTS `").append("t_vulnfound_")
				.append(dt).append("` (").append("\n");
		sb.append("`id` bigint(20) unsigned NOT NULL default 0,").append("\n");
		sb.append("`deviceID` bigint(20) NOT NULL,").append("\n");
		sb.append("`vulnID` bigint(20) NOT NULL,").append("\n");
		sb.append("`status` int(1) NOT NULL,").append("\n");
		sb.append("`foundTime` bigint(20) NOT NULL,").append("\n");
		sb.append("`cause`  varchar(255),").append("\n");
		sb.append("`solution` text,").append("\n");
		sb.append("`description` text,").append("\n");
		sb.append("`tag` int(4) NOT NULL,").append("\n");
		sb.append("`source` varchar(16),").append("\n");
		sb.append("`history` text,").append("\n");
		sb.append("`method` int(4) default 0,");
		sb.append("PRIMARY KEY  (`id`),").append("\n");
		sb.append("KEY `DeviceIndex` (`deviceID`),");
		sb.append("KEY `TimeIndex` (`foundTime`)");
		sb.append(") ENGINE=MyISAM DEFAULT CHARSET=utf8;");
		Query query = entityManager.createNativeQuery(sb.toString());
		query.executeUpdate();
	}

	@Override
	public void addVulnImportRelation(T_VulnImportRelationBean model) {
		// TODO Auto-generated method stub

		super.persist(model);

	}

	@Override
	public int[] getImportInfo(long id) {
		// TODO Auto-generated method stub
		String timeSql = "select max(importTime) from T_VulnImportRelation where deviceId=:deviceId";

		QueryTemplate qt = QueryTemplate.create(QueryType.SQL, timeSql);
		qt.addParameter("deviceId", id);

		long time = 0;
		List<Object[]> result = find(qt);
		if (!result.isEmpty() && result.size() > 0)
			time = Long.parseLong(result.get(0)[0].toString());
		int count = 0;
		int[] importInfo = new int[2];
		String name = getTableName(id);
		String sql = "select count(v.id), b.severity from "
				+ name
				+ " as v inner join t_vuln as b on v.vulnId = b.id where v.tag<>3 and v.deviceId =:deviceId ";
		qt = QueryTemplate.create(QueryType.SQL, sql);
		qt.addParameter("deviceId", id);
		qt.append(" and foundtime=:foundtime group by b.severity ");
		qt.addParameter("foundtime", time);

		List<Object[]> list = find(qt);
		for (int i = 0; i < list.size(); i++) {
			if (Integer.parseInt(list.get(i)[1].toString()) == VULN_SEVERITY_HIGH) {
				count += Integer.parseInt(list.get(i)[0].toString());
			}
			importInfo[0] += Integer.parseInt(list.get(i)[0].toString());
		}
		importInfo[1] = count;
		return importInfo;
	}

	/**
	 * 获取某资产最新的一张表名
	 * 
	 * @param long deviceId
	 * @return  String  name   
	 * @throws SQLException
	 */
	private String getTableName(long deviceId) {
		long temp = 0;
		String name = "t_vulnfound_";
		//获取相关最近一次导入时间用来构造表名
		String sql = " select max(importTime) as result from T_VulnImportRelationBean where deviceID=:deviceID";
		//构造表名
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, sql);
		qt.addParameter("deviceID", deviceId);
		List<Object[]> list = find(qt);
		if (!list.isEmpty() && list.size() > 0)
			temp = Long.parseLong(list.get(0)[0].toString());
		String time = tformat.format(temp);
		name += time;
		return name;
	}
}
