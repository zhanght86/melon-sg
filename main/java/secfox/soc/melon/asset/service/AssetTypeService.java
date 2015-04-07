/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.service;

import java.util.List;

import secfox.soc.melon.asset.domain.AssetType;
import secfox.soc.melon.persistence.GenericService;

/**
 * 安全对象类型服务接口
 * @since @2014-9-22,@上午11:07:28
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
public interface AssetTypeService extends GenericService<AssetType, Long>{
	
	/**
	 * 删除
	 * @param pk
	 * @return
	 */
	String removeMe(Long pk);
	
	
	/**
	 * 动态的根节点
	 * @param rootId 节点id
	 * @param choosen 是否包含通用路径
	 * @return
	 */
	List<AssetType> findTree(Long rootId, boolean choosen);

	/**
	 * 获取类型
	 * @return
	 */
	List<AssetType> findAll();
	
	
	/**
	 * yb 获取不同的类型树
	 * @param type 0-信息系统，1-设备对象，2-安全域
	 * @return
	 */
	List<AssetType> findCustomTree(int type);

}


