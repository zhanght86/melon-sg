/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.report.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.persistence.impl.GenericDaoImpl;
import secfox.soc.melon.report.dao.ReportDao;
import secfox.soc.melon.report.domain.Report;

/**
 * @since 2015-3-10,上午10:36:04
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Repository
public class ReportDaoImpl extends GenericDaoImpl<Report, Long> implements ReportDao {

	/**
	 * @param persistentClass
	 */
	public ReportDaoImpl() {
		super(Report.class);
	}

}
