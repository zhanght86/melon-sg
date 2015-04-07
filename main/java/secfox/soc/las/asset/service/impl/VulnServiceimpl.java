package secfox.soc.las.asset.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import secfox.soc.data.audit.model.T_vulnModel;
import secfox.soc.las.asset.bean.T_VulnImport;
import secfox.soc.las.asset.bean.T_vulnBean;
import secfox.soc.las.asset.dao.VulnDao;
import secfox.soc.las.asset.service.VulnService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
@Service
public class VulnServiceimpl extends GenericServiceImpl<T_vulnBean,Long>
					implements VulnService{
	private VulnDao vulnDao;
	
	@Inject
	public VulnServiceimpl(VulnDao dao) {
		this.vulnDao = dao;
	}
	@Override
	@Transactional
	public void addVulnObj(ArrayList<Object[]> pluginList) {
		// TODO Auto-generated method stub
		if (pluginList == null || pluginList.isEmpty())
			return;
		vulnDao.addT_vulnBean(pluginList);
	}
	@Transactional
	public HashMap<String, Long> getRelationMap() {
		// TODO Auto-generated method stub
		return vulnDao.getRelationMap();
	}
	@Override
	protected GenericDao<T_vulnBean, Long> getDao() {
		// TODO Auto-generated method stub
		return this.vulnDao;
	}
}
