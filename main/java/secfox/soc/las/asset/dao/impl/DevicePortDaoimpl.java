package secfox.soc.las.asset.dao.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import org.springframework.stereotype.Repository;

import secfox.soc.las.asset.bean.T_DevicePort;
import secfox.soc.las.asset.dao.DevicePortDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

@Repository
public class DevicePortDaoimpl extends GenericDaoImpl<T_DevicePort, Long>
		implements DevicePortDao {

	public DevicePortDaoimpl() {
		super(T_DevicePort.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addDevicePort(ArrayList<T_DevicePort> portList) {
		// TODO Auto-generated method stub
		HashSet<Long> deviceIds = new HashSet<Long>();
		HashSet<String> ips = new HashSet<String>();
		for (T_DevicePort model : portList) {
			deviceIds.add(model.getDeviceID());
			ips.add(model.getIp());
		}
		StringBuilder idstr = new StringBuilder();
		for (Long id : deviceIds) {
			if (idstr.length() != 0)
				idstr.append(",");
			idstr.append(id);
		}
		StringBuilder ipstr = new StringBuilder();
		for (String ip : ips) {
			if (ipstr.length() != 0)
				ipstr.append(",");
			ipstr.append("'").append(ip).append("'");
		}
		HashMap<String, Long> exists = new HashMap<String, Long>();
		HashSet<Long> deletes = new HashSet<Long>();
		// String updateSql = "update t_deviceport set
		// Name=?,FoundTime=?,Description=?,VulnID=?,Source=? where ID=?";
		String deleteSql = "delete from t_deviceport ";
		QueryTemplate qt = QueryTemplate
				.create(
						QueryType.SQL,
						"select ID,DeviceID,ip,port,type from t_deviceport where deviceID in (:deviceID)");
		qt.addParameter("deviceID", idstr.toString());
		qt.append("or ip in (:ip)");
		qt.addParameter("ip", ipstr.toString());
		List<Object[]> resultList = find(qt);
		for (int i = 0; i < resultList.size(); i++) {
			exists.put(
					resultList.get(i)[1]
							+ "_"
							+ (resultList.get(i)[2] == null ? "" : resultList
									.get(i)[2]) + "_" + resultList.get(i)[3]
							+ "_" + resultList.get(i)[4], Long
							.parseLong(resultList.get(i)[0].toString()));
		}
		deletes.addAll((Collection<Long>) exists.values());
		for (T_DevicePort model : portList) {
			String distinct = model.getDeviceID() + "_"
					+ (model.getIp() == null ? "" : model.getIp()) + "_"
					+ model.getPort() + "_" + model.getType();
			if (exists.containsKey(distinct)) {
				deletes.remove(exists.get(distinct));
				continue;
			}
			super.persist(model);
			exists.put(distinct, model.getId());
			// model.setId(exists.get(distinct));
			// super.refresh(model);
		}
		if (deletes.size() > 0) {
			StringBuilder deleteIds = new StringBuilder();
			for (Long id : deletes) {
				if (deleteIds.length() != 0)
					deleteIds.append(",");
				deleteIds.append(id);
			}
			if (deleteIds != null && !deleteIds.equals(""))
				deleteSql += "where ID in (:ID)";

			qt = QueryTemplate.create(QueryType.SQL, deleteIds.toString());
			qt.addParameter("ID", deleteIds.toString());
			execute(qt);
		}
	}

}
