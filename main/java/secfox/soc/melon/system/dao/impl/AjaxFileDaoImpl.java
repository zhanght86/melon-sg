/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.system.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.persistence.impl.GenericDaoImpl;
import secfox.soc.melon.system.dao.AjaxFileDao;
import secfox.soc.melon.system.domain.AjaxFile;

/**
 * @since 2014-9-24,下午3:38:32
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Repository("ajaxFileDao")
public class AjaxFileDaoImpl extends GenericDaoImpl<AjaxFile, Long> implements AjaxFileDao {

	public AjaxFileDaoImpl(){
		super(AjaxFile.class);
	}

}
