package secfox.soc.las.asset.dao;

import secfox.soc.las.asset.bean.T_VulnFoundStat;
import secfox.soc.las.asset.bean.T_VulnFoundValue;
import secfox.soc.melon.persistence.GenericDao;

public interface VulnFoundStatDao extends GenericDao<T_VulnFoundStat, Long>{
	void addVulnFoundStat(T_VulnFoundStat model);
	T_VulnFoundValue statAndSaveDeviceCurrentVulnInfo(long id, long deviceId, long calTime);
}
