/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.service;

import java.util.List;
import java.util.Map;

import secfox.soc.melon.asset.domain.Device;
import secfox.soc.melon.asset.domain.InfoSystem;
import secfox.soc.melon.asset.query.DevicePageQuery;
import secfox.soc.melon.asset.query.InfoSystemPageQuery;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.GenericService;

/**
 * 信息系统服务接口
 * 
 * @since 2014-9-25
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
public interface InfoSystemService extends GenericService<InfoSystem, Long> {
	
	/**
	 * 根据类型查询信息系统
	 * @param typeId
	 * @return
	 */
	List<InfoSystem> findByType(Long typeId);

	/**
	 * 动态的根节点
	 * @param rootId 节点id
	 * @return
	 */
	List<InfoSystem> findTree(Long rootId);
	
	/**
	 * 分页查询
	 * 
	 * @param query
	 *            条件
	 * @return 分页结果
	 */
	Pagination<InfoSystem> findPages(InfoSystemPageQuery query);

	/**
	 * 获取信息系统
	 * 
	 * @param url
	 *            URL地址
	 * @return
	 */
	InfoSystem findByUrl(String url);

	/**
	 * 获取信息系统
	 * 
	 * @param code
	 *            编码
	 * @return
	 */
	InfoSystem findByCode(String code);

	/**
	 * 查询全部信息系统
	 * 
	 * @return
	 */
	List<InfoSystem> findAll();

	/**
	 * 获取信息系统
	 * 
	 * @param organId
	 *            单位id
	 * @return 信息系统
	 */
	List<InfoSystem> findByOrgan(Long organId);

	/**
	 * 获取信息系统
	 * 
	 * @param groupId
	 *            安全域
	 * @return
	 */
	List<InfoSystem> findByAssetGroup(Long groupId);

	/**
	 * 信息系统home数据
	 * 
	 * @return
	 */
	List<Object[]> listOrganWithCount();

	/**
	 * 获取信息系统
	 * 
	 * @param ids
	 *            信息系统ids
	 * @return
	 */
	List<InfoSystem> findByIds(Long[] ids);

	/**
	 * 根据当前用户查询关注的信息系统
	 * 
	 * @param userId
	 * @return
	 */
	List<InfoSystem> findByUserBusiness(Long userId);

	/**
	 * 获取登录人批注的信息系统
	 * 
	 * @param userId
	 *            登录人id
	 * @return
	 */
	List<InfoSystem> findByQuickNote(Long userId);
	
	
	/**
	 * yb 根据安全域显示信息系统
	 * @param query
	 * @return
	 */
	Pagination<Map<String,Object>> findPageByGroup(InfoSystemPageQuery query);
}
