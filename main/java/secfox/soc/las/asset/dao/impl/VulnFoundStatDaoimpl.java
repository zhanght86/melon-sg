package secfox.soc.las.asset.dao.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import secfox.soc.las.asset.bean.T_DevicePort;
import secfox.soc.las.asset.bean.T_VulnFoundStat;
import secfox.soc.las.asset.bean.T_VulnFoundValue;
import secfox.soc.las.asset.dao.DevicePortDao;
import secfox.soc.las.asset.dao.VulnFoundStatDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

@Repository
public class VulnFoundStatDaoimpl extends GenericDaoImpl<T_VulnFoundStat, Long>
		implements VulnFoundStatDao {

	public VulnFoundStatDaoimpl() {
		super(T_VulnFoundStat.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addVulnFoundStat(T_VulnFoundStat model) {
		// TODO Auto-generated method stub
		super.persist(model);
	}

	/**
	 * 更新资产弱点统计信息
	 * 操作过程：
	 * 首先操作t_vulnfoundstat表，删除资产原统计信息，插入新统计信息；
	 * 然后从t_vulnfoundstat表中查询资产最新统计的状态为未消除的各等级弱点数量，计算弱点值，
	 * 更新t_vulnfoundvalue表中相应记录的弱点值（VulnValue）、弱点数量（CalRecords）字段
	 * @param conn
	 * @param id t_vulnfoundvalue表中需要更新的记录ID
	 * @param deviceId 资产ID
	 * @param calTime 统计时间
	 * @throws SQLException
	 */
	public T_VulnFoundValue statAndSaveDeviceCurrentVulnInfo(long id,
			long deviceId, long calTime) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String dt = df.format(calTime);
		String table = "t_vulnfound_" + dt;
		String deleteSql = "delete from t_vulnfoundstat where DeviceID=:DeviceID";
		//		String insertSql = "select t.deviceid,t.foundtime,v.severity,t.tag,t.status,count(t.ID) as cnt from t_vuln as v, " + table + " as t where (t.vulnID=v.ID and t.deviceID=" + deviceId + " and t.foundtime=" + calTime + ") group by t.deviceid,t.foundtime,v.severity,t.tag,t.status";

		//		String updateSql = "update t_vulnfoundvalue set VulnValue=?, CalRecords=? where ID=?";

		ResultSet rs = null;
		PreparedStatement ps = null;
		int counts = 0;
		double severitys = 0;
		QueryTemplate qt = QueryTemplate.create(QueryType.SQL, deleteSql);
		qt.addParameter("DeviceID", deviceId);
		execute(qt);

		String selectSql = "select t.deviceid,t.foundtime,v.severity,t.tag,t.status,count(t.ID) as cnt from t_vuln as v, "
				+ table + " as t ";

		qt = QueryTemplate.create(QueryType.SQL, selectSql);
		qt.append("where (t.vulnID=v.ID and t.deviceID=:deviceID");
		qt.addParameter("deviceID", deviceId);
		qt.append(" and t.foundtime=:foundtime");
		qt.addParameter("foundtime", calTime);
		qt
				.append(") group by t.deviceid,t.foundtime,v.severity,t.tag,t.status");

		List<Object[]> list = find(qt);
		T_VulnFoundStat model;
		for (int i = 0; i < list.size(); i++) {
			model = new T_VulnFoundStat();
			model.setDeviceID(Long.parseLong(list.get(i)[0].toString()));
			model.setCalTime(Long.parseLong(list.get(i)[1].toString()));
			model.setSeverity(Long.parseLong(list.get(i)[2].toString()));
			model.setTag(Long.parseLong(list.get(i)[3].toString()));
			model.setStatus(Long.parseLong(list.get(i)[4].toString()));
			model.setCnt(Long.parseLong(list.get(i)[5].toString()));
			super.persist(model);
		}

		String querySql = "select Severity,SUM(Cnt) as Records from t_vulnfoundstat ";

		qt = QueryTemplate.create(QueryType.SQL, querySql);
		qt.append(" where DeviceID=:DeviceID");
		qt.addParameter("DeviceID", deviceId);
		qt.append(" and CalTime=:CalTime");
		qt.addParameter("CalTime", calTime);
		qt.append(" and status=2 group by Severity");

		List<Object[]> result = find(qt);
		double Severity = 0;
		double Records = 0;
		for (int i = 0; i < result.size(); i++) {
			counts = counts + Integer.parseInt((String) result.get(i)[1]);
			Severity = Double.parseDouble((String) result.get(i)[0]);
			Records = Double.parseDouble((String) result.get(i)[1]);
			severitys += Severity * Records;
		}
		T_VulnFoundValue models = new T_VulnFoundValue();
		models.setId(id);
		models.setVulnValue(counts == 0 ? 0 : severitys / counts);
		models.setCalRecords(counts);
		return models;
		//			super.refresh(models);
	}
}
