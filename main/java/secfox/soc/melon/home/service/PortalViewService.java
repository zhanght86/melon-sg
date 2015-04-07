/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.home.service;

import java.util.List;

import secfox.soc.melon.home.domain.PortalView;
import secfox.soc.melon.persistence.GenericService;

/**
 * @since 2014-10-23,上午11:29:31
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public interface PortalViewService extends GenericService<PortalView, Long> {

	/**
	 * 改变view的顺序
	 * @param viewIds
	 * @return
	 */
	void sortView(String viewIds);
	
	/**
	 * 添加view
	 * @param layoutId
	 * @param view
	 * @return
	 */
	PortalView addView(Long layoutId, PortalView view);
	
	/**
	 * 更新view
	 * @param layoutId
	 * @param view
	 * @return
	 */
	PortalView updateView(Long layoutId, PortalView view);
	
	/**
	 * 展示view
	 * @param layoutId
	 * @return
	 */
	List<PortalView> findViews(Long layoutId);
	
	/**
	 * 删除视图
	 * @param layoutId
	 */
	void removeViews(Long layoutId);
}
