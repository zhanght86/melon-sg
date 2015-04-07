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
import secfox.soc.las.asset.bean.T_DevicePort;
import secfox.soc.las.asset.bean.T_VulnFoundStat;
import secfox.soc.las.asset.bean.T_VulnFoundValue;
import secfox.soc.las.asset.dao.DevicePortDao;
import secfox.soc.las.asset.dao.VulnFoundValueDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

@Repository
public class VulnFoundValueDaoimpl extends
		GenericDaoImpl<T_VulnFoundValue, Long> implements VulnFoundValueDao {

	public VulnFoundValueDaoimpl() {
		super(T_VulnFoundValue.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addVulnFoundValue(T_VulnFoundValue model) {
		// TODO Auto-generated method stub
		super.persist(model);
	}

	@Override
	public boolean insertVuln(DeviceVulnModel dvModel) {
		// TODO Auto-generated method stub

		String sql = "select id,importTime from t_vulnimportrelation order by importTime desc limit 1";
		Query query = entityManager.createNativeQuery(sql);
		List<Object[]> result = query.getResultList();
		if (!result.isEmpty()) {
			long time = (Long) result.get(0)[1];
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String dt = df.format(time);
			sql = "insert into t_vulnfound_"
					+ dt
					+ " (ID,DeviceID,VulnID,Status,FoundTime,Cause,Solution,Description,Tag,Source,History) values(?,?,?,?,?,?,?,?,?,?,?)";

			long insertID = (Long) result.get(0)[0];
			query = entityManager.createNativeQuery(sql);

			query.setParameter(1, insertID);
			query.setParameter(2, dvModel.getDeviceID());
			query.setParameter(3, dvModel.getVulnID());
			query.setParameter(4, dvModel.getStatus());
			query.setParameter(5, dvModel.getFoundTime() == 0 ? time : dvModel
					.getFoundTime());
			query.setParameter(6, dvModel.getCause());
			query.setParameter(7, dvModel.getSolution());
			query.setParameter(8, dvModel.getDescription());
			query.setParameter(9, dvModel.getTag());
			query.setParameter(10, dvModel.getSource());
			query.setParameter(11, dvModel.getHistory());
			query.executeUpdate();
			query = entityManager
					.createNativeQuery("update t_vulnfoundvalue set vulnValue=(vulnValue*calRecords+"
							+ dvModel.getSeverity()
							+ ")/(calRecords+1), calRecords=CalRecords+1 where deviceID='"
							+ dvModel.getDeviceID()
							+ "' and calTime='"
							+ time
							+ "'");
			query.executeUpdate();
		}
		return false;
	}

	public int getVulnFoundTimeId(long importTime) {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String dt = df.format(importTime);
		String sql = "select id from t_vulnfound_" + dt + " order by id desc";
		QueryTemplate qt = QueryTemplate.create(QueryType.SQL, sql);
		List<Object[]> result = find(qt);
		int id = 0;
		if (result != null && !result.isEmpty()) {
			id = Integer.parseInt(result.get(0)[0].toString());
		}
		return id + 1;
	}

	@Override
	public void saveDataToVulnFoundTable(ArrayList<DeviceVulnModel> rsList,
			long importTime) {
		// TODO Auto-generated method stub
		Connection conn = null;
		long insertID;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String dt = df.format(importTime);

		String sql = "insert into t_vulnfound_"
				+ dt
				+ " (ID,DeviceID,VulnID,Status,FoundTime,Cause,Solution,Description,Tag,Source,History) values(?,?,?,?,?,?,?,?,?,?,?)";

		Query query = entityManager.createNativeQuery(sql);

		insertID = getVulnFoundTimeId(importTime);
		for (DeviceVulnModel dvm : rsList) {
			query.setParameter(1, insertID);
			query.setParameter(2, dvm.getDeviceID());
			query.setParameter(3, dvm.getVulnID());
			query.setParameter(4, dvm.getStatus());
			query.setParameter(5, dvm.getFoundTime());
			query.setParameter(6, dvm.getCause());
			query.setParameter(7, dvm.getSolution());
			query.setParameter(8, dvm.getDescription());
			query.setParameter(9, dvm.getTag());
			query.setParameter(10, dvm.getSource());
			query.setParameter(11, dvm.getHistory());
			query.executeUpdate();
			insertID += 1;
		}

	}

	@Override
	public long[] updateVuln(DeviceVulnModel dvModel) {
		// TODO Auto-generated method stub
		Connection conn = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String dt = df.format(dvModel.getFoundTime());

		String sql = "update t_vulnfound_"
				+ dt
				+ " set devID=?,status=?,cause=?,solution=?,description=?,tag=?,source=?,history=? where id=?";
		Query query = entityManager.createNativeQuery(sql);
		query.executeUpdate();

		return updateVulnFoundValue(dvModel.getDeviceID());
	}

	/**
	 * 供更新资产弱点的状态时调用，必须使用更新资产弱点用的同一个Connection对象
	 * @param conn
	 * @param deviceID
	 *            更新弱点计算中间值
	 * @throws SQLException
	 */
	public long[] updateVulnFoundValue(long deviceID) {
		String sql = null;
		//mysql
		sql = "select id, calTime from t_vulnfoundvalue where DeviceID=:DeviceID order by calTime desc limit 1";

		QueryTemplate qt = QueryTemplate.create(QueryType.SQL, sql);
		qt.addParameter("DeviceID", deviceID);
		List<Object[]> result = find(qt);
		long[] parm = null;
		if (!result.isEmpty()) {
			long id = (Long) result.get(0)[0];
			long time = (Long) result.get(0)[1];
			parm = new long[] { id, deviceID, time };
		}
		return parm;
	}
}
