/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.asset.field.dao.impl;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.asset.field.dao.AssetFieldDao;
import secfox.soc.melon.asset.field.domain.AssetField;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 * @since 2014-10-21,下午3:40:05
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Repository
public class AssetFieldDaoImpl extends GenericDaoImpl<AssetField, Long>
		implements AssetFieldDao {

	public AssetFieldDaoImpl() {
		super(AssetField.class);
	}
}
