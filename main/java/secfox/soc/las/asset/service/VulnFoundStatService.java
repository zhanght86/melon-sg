package secfox.soc.las.asset.service;

import java.util.ArrayList;
import java.util.HashMap;

import secfox.soc.las.asset.bean.T_VulnFoundStat;
import secfox.soc.las.asset.bean.T_VulnImport;
import secfox.soc.las.asset.bean.T_vulnBean;
import secfox.soc.melon.persistence.GenericService;


public interface VulnFoundStatService extends GenericService<T_VulnFoundStat,Long>{ 
	void addVulnFoundStat(T_VulnFoundStat model);
	void statAndSaveDeviceCurrentVulnInfo(long id, long deviceId, long calTime);
}
