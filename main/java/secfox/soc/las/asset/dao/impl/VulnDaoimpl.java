package secfox.soc.las.asset.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.data.audit.model.T_vulnModel;
import secfox.soc.las.asset.bean.T_vulnBean;
import secfox.soc.las.asset.dao.VulnDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

@Repository
public class VulnDaoimpl extends GenericDaoImpl<T_vulnBean, Long> implements
		VulnDao {

	// 2009-06-08 by zhuyan: 是否为内置弱点
	private static final int VULN_IFSYSTEM_SYSTEM = 0;
	private HashMap<String, Long> relationMap;// 用于存储对象的id和VULN_ID的对应关系

	public VulnDaoimpl() {
		super(T_vulnBean.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	@SuppressWarnings("unchecked")
	public void addT_vulnBean(ArrayList<Object[]> pluginList) {
		// TODO Auto-generated method stub

		String vendor = ((T_vulnModel) pluginList.iterator().next()[1])
				.getVendor();
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL,
				"select distinct originalID from T_vulnBean where ");
		qt.append("vendor=:vendor ");
		qt.addParameter("vendor", vendor);
		List<Object[]> resultList = find(qt);
		HashSet sets = new HashSet();// 存放已经存在记录的vendorCode
		for (int i = 0; i < resultList.size(); i++) {
			sets.add(resultList.get(i)[0]);
		}

		relationMap = new HashMap<String, Long>();// 用于存储对象的id和VULN_ID的对应关系

		for (Object[] objs : pluginList) {
			// String vulID=(String) objs[0];
			T_vulnModel model = (T_vulnModel) objs[1];
			if (sets.contains(model.getOriginalID())) {
				continue;
			}
			T_vulnBean bean = new T_vulnBean();
			// bean.setId(model.getId());
			bean.setOriginalID(model.getOriginalID() == null ? "" : model
					.getOriginalID());
			bean.setCode("SECFOX");
			bean.setCreatedTime(model.getCreatedTime());
			bean.setVendor(model.getVendor() == null ? "" : model.getVendor());
			bean.setVendorCode(model.getVendorCode() == null ? "" : model
					.getVendorCode());
			bean.setName(model.getName() == null ? "" : model.getName());
			bean.setSeverity(model.getSeverity());
			bean.setOriSeverity(model.getOriSeverity() == null ? "" : model
					.getOriSeverity());
			bean.setDescription(model.getDescription() == null ? "" : model
					.getDescription());
			bean.setRemedy(model.getRemedy() == null ? "" : model.getRemedy());
			bean.setVulnType(model.getVulnType());
			bean.setCVE(model.getCVE() == null ? "" : model.getCVE());
			bean.setBUGTRAQ_ID(model.getBUGTRAQ_ID() == null ? "" : model
					.getBUGTRAQ_ID());
			bean.setExtensionMsg(model.getExtensionMsg() == null ? "" : model
					.getExtensionMsg());
			bean.setIfSystem(VULN_IFSYSTEM_SYSTEM);
			sets.add(model.getOriginalID());

			super.persist(bean);

			// bean.setCode("SECFOX-"+bean.getId());
			// relationMap.put(vulID, bean.getId());
			// super.refresh(bean);
			//			

			qt = QueryTemplate.create(QueryType.JQL,
					"select id,originalID from T_vulnBean ");
			qt.append(" where vendor=:vendor ");
			qt.addParameter("vendor", vendor);
			resultList = find(qt);

			for (int i = 0; i < resultList.size(); i++) {
				// Map<String, Object> maps = new HashMap<String, Object>();
				// maps.put("id", resultList.get(i)[0]);
				String originalId = (String) resultList.get(i)[1];
				if (originalId != null && originalId.trim().length() > 0)
					relationMap.put(originalId, (Long) resultList.get(i)[0]);
			}
		}
	}

	public HashMap<String, Long> getRelationMap() {
		// TODO Auto-generated method stub
		return relationMap;
	}
}
