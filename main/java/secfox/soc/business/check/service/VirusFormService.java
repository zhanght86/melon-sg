/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.business.check.service;

import java.util.List;

import secfox.soc.business.check.domain.Virus;
import secfox.soc.business.check.query.VirusFormPageQuery;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.GenericService;

/**
 * 信息系统情况
 * @since @2014-5-21,@下午4:40:06
 * @author <a href="mailto:liubing@legendsec.com>n刘冰</a>
 * @version 1.0
 */
public interface VirusFormService extends GenericService<Virus, Long> {
	
	
	Virus create();
	
	
	Virus findWrappedForm(Long id);

	/**
	 * 根据id查询用户信息
	 * @param id
	 * @return
	 */
	Virus findById(Long id);

	/**
	 * 分頁查詢
	 * @param query
	 * @return
	 */
	Pagination<Virus> findPages(VirusFormPageQuery query);


	/**
	 * 获取设备名称
	 * @param assetInfo
	 * @param rr
	 * @param assetService
	 */
	//void conserve(Asset assetInfo,VirusForm rr,AssetService assetService,List<EventLevelReport> Events,List<TopVirusReport> tops);
	/**
	 * 更新设备名称
	 * @param assetInfo
	 * @param rr
	 * @param assetService
	 */
	//void renew(Asset assetInfo,VirusForm rr,AssetService assetService,List<EventLevelReport> Events,List<TopVirusReport> tops);
	/**
     * 防病毒上报信息
     * @param id
     * @param user
     * @return
     */
    List<Virus> findbytaskId(Long id);
    
    Long findFormId(Long id);
}
