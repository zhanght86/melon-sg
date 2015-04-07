/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.report.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.persistence.impl.GenericDaoImpl;
import secfox.soc.melon.report.dao.ReportFileDao;
import secfox.soc.melon.report.domain.ReportFile;

/**
 * @since 2015-3-13,下午1:55:34
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Repository
public class ReportFileDaoImpl extends GenericDaoImpl<ReportFile, Long> implements
		ReportFileDao {

	public ReportFileDaoImpl() {
		super(ReportFile.class);
	}

}
