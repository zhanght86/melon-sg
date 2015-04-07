/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.business.check.service;

import java.util.List;

import secfox.soc.business.check.domain.Invade;
import secfox.soc.business.check.query.InvadeFormPageQuery;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.GenericService;

/**
 * 信息系统情况
 * @since @2014-5-21,@下午4:40:06
 * @author <a href="mailto:liubing@legendsec.com>n刘冰</a>
 * @version 1.0
 */
public interface InvadeFormService extends GenericService<Invade, Long> {
	
	
	Invade create();
	
	
	Invade findWrappedForm(Long id);

	/**
	 * 根据id查询用户信息
	 * @param id
	 * @return
	 */
	Invade findById(Long id);

	/**
	 * 分頁查詢
	 * @param query
	 * @return
	 */
	Pagination<Invade> findPages(InvadeFormPageQuery query);

	/**
	 * 获取设备名称
	 * @param assetInfo
	 * @param rr
	 * @param assetService
	 *//*
	void conserve(Asset assetInfo,InvadeForm rr,AssetService assetService,List<EventLevelReport> Events,List<TopEventReport> tops);
	*//**
	 * 更新设备名称
	 * @param assetInfo
	 * @param rr
	 * @param assetService
	 *//*
	void renew(Asset assetInfo,InvadeForm rr,AssetService assetService,List<EventLevelReport> Events,List<TopEventReport> tops);
	*//**
     * 入侵防御上报信息
     * @param id
     * @param user
     * @return
     */
    List<Invade> findbytaskId(Long id);
    
    Long findFormId(Long id);
}
