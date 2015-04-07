/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.home.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import secfox.soc.melon.home.dao.AttentionDao;
import secfox.soc.melon.home.domain.Attention;
import secfox.soc.melon.home.service.AttentionService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;

/**
 * @since 1.0 2014年10月31日,上午11:53:55
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Service
public class AttentionServiceImpl extends GenericServiceImpl<Attention, Long> implements AttentionService {
	
	private AttentionDao attentionDao;
	
	
	/**
	 * @param attentionDao
	 */
	@Inject
	public AttentionServiceImpl(AttentionDao attentionDao) {
		super();
		this.attentionDao = attentionDao;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<Attention, Long> getDao() {
		return attentionDao;
	}
	
	/*
	 * 获取关注的list
	 * (non-Javadoc)
	 * @see secfox.soc.melon.home.service.AttentionService#findList(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<Attention> findList(Long type, Long businessId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "attent.findAttentUsers");
		qt.addParameter("type", type);
		qt.addParameter("businessId", businessId);
		return findDomains(qt);
	}
	
	/*
	 * 判断是否被关注
	 * (non-Javadoc)
	 * @see secfox.soc.melon.home.service.AttentionService#isAttentioned(java.lang.Long, java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<Attention> isAttentioned(Long type, Long businessId, Long userId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "attent.findAttentOwern");
		qt.addParameter("type", type);
		qt.addParameter("businessId", businessId);
		qt.addParameter("userId", userId);
		return findDomains(qt);
	}
	
	/**
	 * 通过用户id 分类查询所有关注的内容
	 */
	@Override
	public List<Attention> findAllMyAtten(Long userId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "attent.findMyAtten");
		qt.addParameter("userId", userId);
		return findDomains(qt);
	}
	
}
