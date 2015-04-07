package secfox.soc.las.asset.dao;

import java.util.ArrayList;
import java.util.HashMap;

import secfox.soc.las.asset.bean.T_vulnBean;
import secfox.soc.melon.persistence.GenericDao;


public interface VulnDao extends GenericDao<T_vulnBean,Long>{
	void addT_vulnBean(ArrayList<Object[]> pluginList);
	
	/**
	 * 用于存储对象的id和VULN_ID的对应关系
	 * @return
	 */
	HashMap<String, Long> getRelationMap();
}
