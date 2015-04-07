/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.home.service;

import java.util.List;

import secfox.soc.melon.home.domain.PortalLayout;
import secfox.soc.melon.home.domain.PortalView;
import secfox.soc.melon.persistence.GenericService;

/**
 * @since 2014-10-23,上午11:30:31
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public interface PortalLayoutService extends GenericService<PortalLayout, Long> {
	
	/**
	 * 为当前布局添加视图
	 * @param layoutId
	 * @return
	 */
	PortalView addView(Long layoutId, PortalView view);
	
	/**
	 * 复制视图的仪表盘
	 * @param targetId
	 * @param sourceId
	 */
	PortalLayout copy(Long targetId, Long sourceId);
	
	/**
	 * 获取当前用户portal布局
	 * @param owner
	 * @return
	 */
	List<PortalLayout> findLayout(Long userId);
	
	/**
	 * 获取当前布局下的视图
	 * @param layoutId
	 * @return
	 */
	List<PortalView> findViews(Long layoutId);
	
	/**
	 * 改变layout的顺序
	 * @param layoutId
	 * @return
	 */
	void sortLayout(String layoutIds);
	
	/**
	 * 获取全部可见的视图
	 * @return
	 */
	List<PortalLayout> findPubViews();
	
	/**
	 * 设置主页
	 */
	void setDefaultHome(Long userId);
	
	/**
	 * 保存
	 * @param layout
	 * @param userId
	 * @return
	 */
	PortalLayout persist(PortalLayout layout, Long userId);

}
