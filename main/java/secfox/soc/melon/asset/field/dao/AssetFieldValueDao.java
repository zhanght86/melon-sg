/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.field.dao;

import java.util.List;

import secfox.soc.melon.asset.field.domain.AssetFieldValue;
import secfox.soc.melon.persistence.GenericDao;

/**
 * @since 1.0 2014年10月25日,下午4:47:27
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public interface AssetFieldValueDao extends GenericDao<AssetFieldValue, Long> {

	/**
	 * 
	 * @param value
	 */
	void saveOrUpdate(AssetFieldValue value);
	
	
	void saveOrUpdate(List<AssetFieldValue> values);
}
