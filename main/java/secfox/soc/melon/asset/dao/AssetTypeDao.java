/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.dao;

import secfox.soc.melon.asset.domain.AssetType;
import secfox.soc.melon.persistence.GenericDao;


/**
 * @since @2014-9-22,@上午11:06:39
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
public interface AssetTypeDao extends GenericDao<AssetType, Long>{
	
	/**
	 * 获取设备类型
	 * @param name 类型名称
	 * @return
	 */
	AssetType findByName(String name);
	
}


