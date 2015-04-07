/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.asset.staff.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.GenericService;
import secfox.soc.melon.asset.staff.domain.Certificate;
import secfox.soc.melon.asset.staff.domain.InterStaff;
import secfox.soc.melon.asset.staff.query.InterStaffPageQuery;

/**
 * @since 2014-11-20,下午2:44:33
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public interface InterStaffService extends GenericService<InterStaff, Long> {
	
	/**
	 * 新增页面初始化内部人员证书
	 * @return
	 */
	InterStaff create();
	
	/**
	 * 修改页面初始化内部人员证书
	 * @return
	 */
	InterStaff updateCreate(InterStaff staff,String certificate,Long staffId);
	
	/**
	 * 获取证书明细
	 * @param request
	 * @return
	 */
	List<Certificate> getCerts(HttpServletRequest request);
	
	
	
	/**
	 * 根据formId查找安全事件和病毒排名
	 * @param id
	 * @return
	 */
	InterStaff findWrappedStaffId(Long staffId);
	
	/**
	 * 分页查询
	 * @param pageQuery
	 * @return
	 */
	Pagination<InterStaff> findPagination(InterStaffPageQuery pageQuery);
	
	/**
	 * 获得内部人员的总数
	 * @param organId
	 * @return
	 */
	int getCount(Long organId);
	
	/**
	 * 生成饼图数据
	 * @param organId
	 * @return
	 */
	List<Object[]> getPie(Long organId);
	
	/**
	 * 内部人员统计图
	 * @param organId
	 * @return
	 */
	List<Object[]> interCount(Long organId);
	
	List<InterStaff> findAll();
	
}
