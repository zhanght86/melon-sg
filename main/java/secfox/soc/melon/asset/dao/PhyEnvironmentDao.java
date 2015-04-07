/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.asset.dao;

import java.util.List;

import secfox.soc.melon.asset.domain.PhyEnvironment;
import secfox.soc.melon.persistence.GenericDao;

/**
 * @since 2014-4-17,下午6:46:10
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public interface PhyEnvironmentDao extends GenericDao<PhyEnvironment, Long>{
	
	/**
	 * 根据类型查询本类型及以下类型 查询物理环境
	 * @param typeId
	 * @return
	 */
	List<PhyEnvironment> findByTypePath(Long typeId);

}
