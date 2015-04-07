/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.organ.dao;

import secfox.soc.melon.organ.domain.Organization;
import secfox.soc.melon.persistence.GenericDao;

/**
 * @since 2014-9-3,下午7:04:15
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public interface OrganDao extends GenericDao<Organization, Long> {
	
	/**
	 * 按名称查找组织机构
	 * @param name
	 * @return
	 */
	Organization findByName(String name);

}
