/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.service;

import java.util.List;

import secfox.soc.melon.persistence.GenericService;
import secfox.soc.melon.system.domain.AjaxFile;

/**
 * @since 1.0 2014年9月23日,下午4:06:17
 * 文件管理器
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public interface AjaxFileService extends GenericService<AjaxFile, Long>{
	
	/**
	 * 按domainId查询
	 * @param domainId
	 * @return
	 */
	List<AjaxFile> findFile(String businessId);
	
}
