/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.asset.staff.service;

import java.util.List;

import secfox.soc.melon.persistence.GenericService;
import secfox.soc.melon.asset.staff.domain.Certificate;

/**
 * @since 2014-11-21,下午12:34:54
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public interface CertificateService extends GenericService<Certificate, Long> {
	
	
	/**
	 * @param id
	 * @return
	 */
	List<Certificate> findStaffId(Long staffId);
	
	/**
	 * 按证书名查找
	 * @param name
	 * @return
	 */
	Certificate findByName(String name,Long staffId);
}
