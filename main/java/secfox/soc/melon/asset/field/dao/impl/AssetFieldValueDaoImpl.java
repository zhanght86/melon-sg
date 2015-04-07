/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.field.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import secfox.soc.melon.asset.field.dao.AssetFieldValueDao;
import secfox.soc.melon.asset.field.domain.AssetFieldValue;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;

/**
 * @since 1.0 2014年10月25日,下午4:48:01
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Repository
public class AssetFieldValueDaoImpl extends	GenericDaoImpl<AssetFieldValue, Long> implements AssetFieldValueDao{

	/**
	 * @param persistentClass
	 */
	public AssetFieldValueDaoImpl() {
		super(AssetFieldValue.class);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.asset.field.dao.AssetFieldValueDao#saveOrUpdate(secfox.soc.melon.asset.field.domain.AssetFieldValue)
	 */
	@Override
	public void saveOrUpdate(AssetFieldValue value) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "assetFieldValue.findExist");
		qt.addParameter("deviceId", value.getDeviceId());
		qt.addParameter("fieldId", value.getFieldId());
		AssetFieldValue exist = findFirstDomain(qt);
		//首先判断是否存在这样的属性
		if(exist != null) {
			value.setId(exist.getId());
			merge(value);
		} else {
			persist(value);
		}
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.asset.field.dao.AssetFieldValueDao#saveOrUpdate(secfox.soc.melon.asset.field.dao.List)
	 */
	@Override
	public void saveOrUpdate(List<AssetFieldValue> values) {
		for(AssetFieldValue value : values) {
			saveOrUpdate(value);
		}
	}

}
