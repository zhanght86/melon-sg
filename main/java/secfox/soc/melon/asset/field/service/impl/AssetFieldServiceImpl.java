/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.field.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.melon.asset.field.dao.AssetFieldDao;
import secfox.soc.melon.asset.field.dao.AssetFieldValueDao;
import secfox.soc.melon.asset.field.domain.AssetField;
import secfox.soc.melon.asset.field.domain.AssetFieldValue;
import secfox.soc.melon.asset.field.service.AssetFieldService;
import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.base.domain.Dictionary;
import secfox.soc.melon.base.service.DictionaryService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;

/**
 * @since 1.0 2014年10月21日,下午2:57:13
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Service
public class AssetFieldServiceImpl extends GenericServiceImpl<AssetField, Long> implements AssetFieldService {
	
	private AssetFieldDao dao ;
	
	private AssetFieldValueDao assetValueDao;
	
	private DictionaryService dictService;
	
	/**
	 * 
	 * @param dao
	 * @param dictService
	 * @param assetValueDao
	 */
	@Inject
	public AssetFieldServiceImpl(AssetFieldDao dao, DictionaryService dictService, AssetFieldValueDao assetValueDao) {
		super();
		this.dao = dao;
		this.dictService = dictService;
		this.assetValueDao = assetValueDao;
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<AssetField, Long> getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.asset.field.service.AssetFieldService#findFields(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly=true)
	public List<AssetField> findFields(Long assetTypeId) {
		String path = findAssetTypePath(assetTypeId);
		//获取查询结果
		QueryTemplate resultQt = QueryTemplate.create(QueryType.JQL, "select field from AssetField field ");
		resultQt.append(" where field.deviceType in(")
			  	.append(path)
			  	.append(") order by field.order asc ");
		List<AssetField> fields = findDomains(resultQt);
		for(AssetField field : fields) {
			//设置数据字典值
			initDictionary(field);
		}
		return fields;
	}

	/**
	 * @param assetTypeId
	 * @return
	 */
	private String findAssetTypePath(Long assetTypeId) {
		String path = String.valueOf(BaseConstants.ROOT_ID);
		//判断是否是根节点
		if(assetTypeId != BaseConstants.ROOT_ID) {
			QueryTemplate pathQt = QueryTemplate.create(QueryType.NamedQuery, "assetType.findPath");
			pathQt.addParameter("typeId", assetTypeId);
			List<Object[]> paths = find(pathQt);
			//获取类型的路径
			path = (String)paths.get(0)[0];
		}
		return path;
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#find(java.io.Serializable)
	 */
	@Override
	@Transactional(readOnly=true)
	public AssetField find(Long pk) {
		AssetField field = super.find(pk);
		initDictionary(field);
		return field;
	}

	/**
	 * 初始化数据字典
	 * @param field
	 */
	private void initDictionary(AssetField field) {
		if(field.getDictId() != null) {
			field.setDicts(dictService.findDict(field.getDictId()));
			Dictionary dict = dictService.find(field.getDictId());
			if(dict != null) {
				field.setDictLabel(dict.getLabel());
			}
		}
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.asset.field.service.AssetFieldService#findFieldsWithValue(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<AssetField> findFieldsWithValue(Long deviceId) {
		//获取查询结果
		QueryTemplate resultQt = QueryTemplate.create(QueryType.NamedQuery, "assetType.findFieldsByDeviceId");
		resultQt.addParameter("deviceId", deviceId);
		//
		List<Object[]> results = find(resultQt);
		List<AssetField> fields = new ArrayList<AssetField>(results.size());
		for(Object[] result : results) {
			AssetField field = (AssetField)result[0];
			initDictionary(field);
			String value = (String)result[1];
			field.setDefaultValue(value);
			fields.add(field);
		}
		return fields;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.asset.field.service.AssetFieldService#refreshFields(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly=false)
	public List<AssetField> refreshFields(Long id, Long deviceId) {
		remove(id);
		return findFields(deviceId);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.asset.field.service.AssetFieldService#saveOrUpdate(java.util.List)
	 */
	@Override
	@Transactional(readOnly=false)
	public void saveOrUpdate(List<AssetFieldValue> values) {
		assetValueDao.saveOrUpdate(values);
	}

}
