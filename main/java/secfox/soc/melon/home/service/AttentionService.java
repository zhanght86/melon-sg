/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.home.service;

import java.util.List;

import secfox.soc.melon.asset.domain.Device;
import secfox.soc.melon.home.domain.Attention;
import secfox.soc.melon.persistence.GenericService;

/**
 * @since 1.0 2014年10月21日,下午4:16:44
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public interface AttentionService extends GenericService<Attention, Long> {

	/**
	 * 关注数量
	 * @param type
	 * @param businessId
	 * @return
	 */
	List<Attention> findList(Long type, Long businessId);
	
	/**
	 * 判断是否被关注了
	 * @param menuId
	 * @param domainId
	 * @param userId
	 * @return
	 */
	List<Attention> isAttentioned(Long type, Long businessId, Long userId);
	
	/**
	 * 查询当前登录人关注的所有内容
	 * @param userId
	 * @return
	 */
	List<Attention> findAllMyAtten(Long userId);
	
}
