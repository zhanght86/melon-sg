/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.field.service;

import java.util.List;

import secfox.soc.melon.asset.field.domain.AssetField;
import secfox.soc.melon.asset.field.domain.AssetFieldValue;
import secfox.soc.melon.persistence.GenericService;

/**
 * @since 1.0 2014年10月21日,上午11:20:57
 * 资产属性服务
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public interface AssetFieldService extends GenericService<AssetField, Long> {
	
	/**
	 * 根据资产类型获取所有的 资产属性
	 * @param assetTypeId
	 * @param dict 是否包含数据字典值
	 * @return
	 */
	List<AssetField> findFields(Long assetTypeId);
	
	/**
	 * 根据资产类型与业务ID获取所有的属性
	 * @param assetTypeId
	 * @param deviceId
	 * @return
	 */
	List<AssetField> findFieldsWithValue(Long deviceId);
	
	/**
	 * 删除资产属性之后刷新list
	 * @param id
	 * @param deviceId
	 * @return
	 */
	List<AssetField> refreshFields(Long id, Long deviceId);
	
	/**
	 * 保存资产属性
	 * @param values
	 */
	void saveOrUpdate(List<AssetFieldValue> values);
	
}
