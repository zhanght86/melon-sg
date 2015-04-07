package secfox.soc.las.asset.service;

import java.util.ArrayList;
import java.util.HashMap;

import secfox.soc.las.asset.bean.T_VulnImport;
import secfox.soc.las.asset.bean.T_vulnBean;
import secfox.soc.melon.persistence.GenericService;


public interface VulnService extends GenericService<T_vulnBean,Long>{ 
	void addVulnObj(ArrayList<Object[]> pluginList);
	/**
	 * 用于存储对象的id和VULN_ID的对应关系
	 * @return
	 */
	HashMap<String, Long> getRelationMap();
}
