/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.asset.service;

import java.util.List;

import secfox.soc.melon.asset.domain.PhyEnvironment;
import secfox.soc.melon.persistence.GenericService;

/**
 * @since 2014-4-18,上午10:21:21
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public interface PhyEnvironmentService extends GenericService<PhyEnvironment, Long>{


	/**
	 * 动态的根节点
	 * @param rootId 节点id
	 * @return
	 */
	List<PhyEnvironment> findTree(Long rootId);

	/**
	 * 获取物理环境
	 * @return
	 */
	List<PhyEnvironment> findAll();
	
	/**
	 * 删除
	 * @param id
	 * @return
	 */
	boolean deleteEnvi(Long pk);

}
