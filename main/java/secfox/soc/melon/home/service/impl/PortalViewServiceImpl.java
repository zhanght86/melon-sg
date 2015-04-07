/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.home.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.primitives.Longs;

import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.home.dao.PortalViewDao;
import secfox.soc.melon.home.domain.PortalLayout;
import secfox.soc.melon.home.domain.PortalView;
import secfox.soc.melon.home.service.PortalViewService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;

/**
 * @since 2014-10-23,上午11:37:02
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Service
public class PortalViewServiceImpl extends GenericServiceImpl<PortalView, Long> implements
		PortalViewService {

	private PortalViewDao viewDao;
	
	@Inject
	public PortalViewServiceImpl(PortalViewDao viewDao) {
		super();
		this.viewDao = viewDao;
	}

	@Override
	protected GenericDao<PortalView, Long> getDao() {
		return viewDao;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.home.service.PortalViewService#changeOrder(java.lang.String)
	 */
	@Override
	@Transactional(readOnly=false)
	public void sortView(String viewIds) {
		Iterable<String> ids = Splitter.on(BaseConstants.SPLITER_FLAG).trimResults().omitEmptyStrings().split(viewIds);
		Long[] pks = Iterables.toArray(Longs.stringConverter().convertAll(ids), Long.class);
		//
		for(int i=0, length = pks.length; i < length; i++) {
			PortalView view = viewDao.getReference(pks[i]);
			view.setXpos(i);
			merge(view);
		}
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.home.service.PortalViewService#addView(java.lang.Long, secfox.soc.melon.home.domain.PortalView)
	 */
	@Override
	@Transactional(readOnly=false)
	public PortalView addView(Long layoutId, PortalView view) {
		PortalLayout layout = new PortalLayout();
		layout.setId(layoutId);
		view.setLayout(layout);
		persist(view);
		return view;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.home.service.PortalViewService#findViews(java.lang.Long)
	 */
	@Override
	public List<PortalView> findViews(Long layoutId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "portalView.findViews");
		qt.addParameter("layoutId", layoutId);
		List<Object[]> results = find(qt);
		List<PortalView> views = new ArrayList<PortalView>(results.size());
		//
		for(Object[] result : results) {
			PortalView view = (PortalView)result[0];
			view.setTitle((String)result[1]);//设置仪表盘的标题
			view.setUrl((String)result[2]);//设置仪表盘的访问地址
			views.add(view);
		}
		
		return views;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.home.service.PortalViewService#updateView(java.lang.Long, secfox.soc.melon.home.domain.PortalView)
	 */
	@Override
	@Transactional(readOnly=false)
	public PortalView updateView(Long layoutId, PortalView view) {
		PortalLayout layout = new PortalLayout();
		layout.setId(layoutId);
		view.setLayout(layout);
		return merge(view);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.home.service.PortalViewService#removeViews(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly=false)
	public void removeViews(Long layoutId) {
		List<PortalView> views = findViews(layoutId);
		removeBatch(views);
	}
	
	
}
