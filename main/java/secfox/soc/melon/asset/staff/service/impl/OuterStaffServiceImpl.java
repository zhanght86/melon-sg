/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.asset.staff.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Joiner;

import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.SortOrder;
import secfox.soc.melon.organ.domain.Organization;
import secfox.soc.melon.organ.serivce.OrganizationService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.PaginationBuilder;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.security.util.SecurityContextUtils;
import secfox.soc.melon.asset.staff.dao.OuterStaffDao;
import secfox.soc.melon.asset.staff.domain.OuterStaff;
import secfox.soc.melon.asset.staff.query.OuterStaffPageQuery;
import secfox.soc.melon.asset.staff.query.OuterStaffSearchForm;
import secfox.soc.melon.asset.staff.service.OuterStaffService;

/**
 * @since 2014-11-21,下午12:35:59
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Service
public class OuterStaffServiceImpl extends GenericServiceImpl<OuterStaff, Long> implements
		OuterStaffService {
	

	private OuterStaffDao dao;
	private OrganizationService organService;
	
	@Inject
	public OuterStaffServiceImpl(OuterStaffDao dao, OrganizationService organService) {
		super();
		this.dao = dao;
		this.organService = organService;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<OuterStaff, Long> getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.staff.service.OuterStaffService#findPagination(secfox.soc.melon.staff.query.OuterStaffPageQuery)
	 */
	@Override
	public Pagination<OuterStaff> findPagination(OuterStaffPageQuery pageQuery) {
		return this.findDomainPage(QueryType.JQL, pageQuery, new PaginationBuilder<OuterStaffSearchForm, OuterStaffPageQuery>() {

			@Override
			public void buildSelect(QueryTemplate qt) {
				qt.append("select a from OuterStaff a ");
			}

			@Override
			public void buildWhere(OuterStaffSearchForm s, QueryTemplate qt) {
				//姓名
				if(StringUtils.isNotBlank(s.getName())) {
					qt.append(" and a.name like :name");
					qt.addParameter("name", QueryTemplate.buildAllLike(s.getName()));
				}
				//负责人
				if(StringUtils.isNotBlank(s.getChargeName())) {
					qt.append(" and a.chargeName like :chargeName");
					qt.addParameter("chargeName", QueryTemplate.buildAllLike(s.getChargeName()));
				}
				//单位
				if(StringUtils.isNotBlank(s.getDepartName())) {
					qt.append(" and a.departName like :departName");
					qt.addParameter("departName", QueryTemplate.buildAllLike(s.getDepartName()));
				}
			}

			@Override
			public void buildBys(String column, SortOrder order, QueryTemplate qt) {
				if (StringUtils.isBlank(column))
					column = "name";
				qt.append(QueryTemplate.buildOrderBy("a", column, order));
			}

			@Override
			public void buildCount(QueryTemplate qt) {
				qt.append("select count(a.id) from OuterStaff a");
			}

		});
	}
	
	@Override
	@Transactional(readOnly=false)
	public void persist(OuterStaff staff) {
		Long organId = SecurityContextUtils.getCurrentUserInfo().getOrganId();
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "outerStaff.findOrder"); 
		qt.addParameter("organId", organId);
		//获取编号最大的人员信息
		OuterStaff result = findFirstDomain(qt);
		//获取组织机构
		//Organization organ = organService.getReference(staff.getOrganId());
		Organization organ = organService.getReference(organId);
		if(result == null) {
			//人员编码：组织机构编码+NB+编号
			staff.setCode(organ.getCode()+BaseConstants.OUTERSTAFF+BaseConstants.DEFAULTORDER);
			staff.setOrder(0);
		} else {
			//获取顺序
			int order = result.getOrder();
			//设置顺序
			int curOrder = order+1;
			//获取编码前缀
			String code = organ.getCode()+BaseConstants.OUTERSTAFF;
			//编码拼接
			if(order >= 0&&order < 10) {
				code += BaseConstants.ORDER_FIR + curOrder;
			} else if(order >= 10&&order < 100) {
				code += BaseConstants.ORDER_SEC + curOrder;
			} else if(order >= 100&&order < 1000) {
				code += BaseConstants.ORDER_TRD + curOrder;
			} else {
				code += curOrder;
			}
			staff.setOrder(curOrder);
			staff.setCode(code);
		}
		dao.persist(staff);
	}
	
	@Override
	@Transactional(readOnly=false)
	public OuterStaff merge(OuterStaff staff) {
		adapter(staff);
		return dao.merge(staff);
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.staff.service.InterStaffService#getCount(java.lang.Long)
	 */
	@Override
	public int getCount(Long organId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "outerStaff.findCount");
		qt.addParameter("organId", organId);
		return findCount(qt).intValue();
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.staff.service.OuterStaffService#outerCount(java.lang.Long)
	 */
	@Override
	public List<Object[]> outerCount(Long organId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select staff.departName, count(staff.departName) from OuterStaff staff where staff.organId = :organId group by staff.departName ");
		qt.addParameter("organId", organId);
		return find(qt);
	}
	
	private void adapter(OuterStaff staff) {
		//
		Integer[] fullJobs = staff.getFullJobs();
		if(fullJobs != null) {
			staff.setFullJob(Joiner.on(BaseConstants.SPLITER_FLAG).skipNulls().join(fullJobs));
		}
		//
		Integer[] certificates = staff.getCertificates();
		if(certificates != null) {
			staff.setCertificate(Joiner.on(BaseConstants.SPLITER_FLAG).skipNulls().join(certificates));
		}
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.staff.service.OuterStaffService#findALL()
	 */
	@Override
	public List<OuterStaff> findAll() {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "outerStaff.findAll");
		return findDomains(qt);
	}
	
}
