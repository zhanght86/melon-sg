/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.home.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.primitives.Longs;

import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.home.dao.PortalLayoutDao;
import secfox.soc.melon.home.domain.PortalLayout;
import secfox.soc.melon.home.domain.PortalView;
import secfox.soc.melon.home.service.PortalLayoutService;
import secfox.soc.melon.home.service.PortalViewService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;

/**
 * @since 2014-10-23,上午11:32:08
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Service
public class PortalLayoutServiceImpl extends GenericServiceImpl<PortalLayout, Long>	implements PortalLayoutService {
	
	private PortalLayoutDao layoutDao;
	private PortalViewService viewService;

	@Inject
	public PortalLayoutServiceImpl(PortalLayoutDao layoutDao, PortalViewService viewService) {
		super();
		this.layoutDao = layoutDao;
		this.viewService = viewService;
		
	}

	@Override
	protected GenericDao<PortalLayout, Long> getDao() {
		return layoutDao;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.home.service.PortalLayoutService#findLayout(java.lang.Long)
	 */
	@Override
	public List<PortalLayout> findLayout(Long userId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "portal.findLayout");
		qt.addParameter("userId", userId);
		return findDomains(qt);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.home.service.PortalLayoutService#changeOrder(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly=false)
	public void sortLayout(String layoutIds) {
		Iterable<String> idStr = Splitter.on(BaseConstants.SPLITER_FLAG).trimResults().omitEmptyStrings().split(layoutIds);
		Long[] ids = Iterables.toArray(Longs.stringConverter().convertAll(idStr), Long.class);
		for(int i =0;i < ids.length; i++) {
			PortalLayout layout = layoutDao.getReference(ids[i]);
			layout.setOrder(i);
			layoutDao.merge(layout);
		}
		
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.home.service.PortalLayoutService#findView(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly=false)
	public List<PortalView> findViews(Long layoutId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "portal.findViews");
		qt.addParameter("id", layoutId);
		PortalLayout layout = findFirstDomain(qt);
		return layout.getViews();
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.home.service.PortalLayoutService#addView(java.lang.Long, secfox.soc.melon.home.domain.PortalView)
	 */
	@Override
	@Transactional(readOnly=false)
	public PortalView addView(Long layoutId, PortalView view) {
		PortalLayout layout = find(layoutId);
		layout.addView(view);
		layoutDao.merge(layout);
		return view;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.home.service.PortalLayoutService#copy(java.lang.Long, java.lang.Long)
	 */
	@Override
	@Transactional(readOnly=false)
	public PortalLayout copy(Long targetId, Long sourceId) {
		PortalLayout targetLayout = find(targetId);//获取复制对象
		PortalLayout sourceLayout = getReference(sourceId);//获取原布局对象
		sourceLayout.setLeftToRight(targetLayout.isLeftToRight());//设置对齐方式
		layoutDao.merge(sourceLayout);
		viewService.removeViews(sourceId);//删除原有视图
		//复制布局对象
		for(PortalView targetView : targetLayout.getViews()) {
			PortalView view = targetView.copy();
			viewService.addView(sourceLayout.getId(), view);
		}
		return sourceLayout;
		
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.home.service.PortalLayoutService#findPubViews()
	 */
	@Override
	public List<PortalLayout> findPubViews() {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "portal.findPubViews");
		return findDomains(qt);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.home.service.PortalLayoutService#setHomeDefault()
	 */
	@Override
	@Transactional(readOnly=false)
	public void setDefaultHome(Long userId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "portal.setDefaultHome");
		qt.addParameter("userId", userId);
		layoutDao.execute(qt);
	}
	
	@Override
	@Transactional(readOnly=false)
	public PortalLayout persist(PortalLayout layout, Long userId) {
		//判断是否为默认主页设置
		if(layout.isDefaultHome()) {
			setDefaultHome(userId);
		}
		layout.setUserId(userId);
		layoutDao.persist(layout);
		return layout;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.home.service.PortalLayoutService#merge(secfox.soc.melon.home.domain.PortalLayout, java.lang.Long)
	 */
	@Override
	@Transactional(readOnly=false)
	public PortalLayout merge(PortalLayout layout) {
		//判断是否为默认主页设置
		if(layout.isDefaultHome()) {
			setDefaultHome(layout.getUserId());
		}
		layoutDao.merge(layout);
		return layout;
	}
	

}
