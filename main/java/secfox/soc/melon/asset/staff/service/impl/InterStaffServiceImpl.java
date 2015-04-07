/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.asset.staff.service.impl;

import java.sql.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.SortOrder;
import secfox.soc.melon.base.service.DictionaryService;
import secfox.soc.melon.base.util.ApplicationContextUtils;
import secfox.soc.melon.base.util.DictionaryUtils;
import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.base.util.UUIDUtils;
import secfox.soc.melon.organ.domain.Organization;
import secfox.soc.melon.organ.serivce.OrganizationService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.PaginationBuilder;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.asset.staff.dao.InterStaffDao;
import secfox.soc.melon.asset.staff.domain.Certificate;
import secfox.soc.melon.asset.staff.domain.InterStaff;
import secfox.soc.melon.asset.staff.query.InterStaffPageQuery;
import secfox.soc.melon.asset.staff.query.InterStaffSearchForm;
import secfox.soc.melon.asset.staff.service.CertificateService;
import secfox.soc.melon.asset.staff.service.InterStaffService;
import secfox.soc.melon.asset.staff.service.OuterStaffService;

import com.google.common.collect.Lists;

/**
 * @since 2014-11-20,下午2:46:11
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Service
public class InterStaffServiceImpl extends GenericServiceImpl<InterStaff, Long> implements
		InterStaffService {

	private InterStaffDao dao;
	private OrganizationService organService;
	private OuterStaffService outerService;
	private UUIDUtils uuidUtils;
	private DictionaryService dictiionaryService;
	private CertificateService certService;
	
	@Inject
	public InterStaffServiceImpl(InterStaffDao dao, OrganizationService organService, OuterStaffService outerService, DictionaryService dictiionaryService,UUIDUtils uuidUtils,CertificateService certService) {
		super();
		this.dao = dao;
		this.organService = organService;
		this.outerService = outerService;
		this.uuidUtils = uuidUtils;
		this.dictiionaryService = dictiionaryService;
		this.certService = certService;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<InterStaff, Long> getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.staff.service.InterStaffService#findPagination(secfox.soc.melon.staff.query.InterStaffPageQuery)
	 */
	@Override
	public Pagination<InterStaff> findPagination(InterStaffPageQuery pageQuery) {
		return this.findDomainPage(QueryType.JQL, pageQuery, new PaginationBuilder<InterStaffSearchForm, InterStaffPageQuery>() {

			@Override
			public void buildSelect(QueryTemplate qt) {
				qt.append("select a from InterStaff a ");
			}

			@Override
			public void buildWhere(InterStaffSearchForm s, QueryTemplate qt) {
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
				if(StringUtils.isNotBlank(s.getOrganName())) {
					qt.append(" and a.organName like :organName");
					qt.addParameter("organName", QueryTemplate.buildAllLike(s.getOrganName()));
				}
				/*//安全专家
				if(s.getExpert() != 0) {
					qt.append(" and a.expert = :expert");
					qt.addParameter("expert", s.getExpert());
				}
				//项目组a
				if(s.getProjectTeam() != 0) {
					qt.append(" and a.projectTeam = :projectTeam");
					qt.addParameter("projectTeam", s.getProjectTeam());
				}*/
				
			}

			@Override
			public void buildBys(String column, SortOrder order, QueryTemplate qt) {
				if (StringUtils.isBlank(column))
					column = "name";
				qt.append(QueryTemplate.buildOrderBy("a", column, order));
			}

			@Override
			public void buildCount(QueryTemplate qt) {
				qt.append("select count(a.id) from InterStaff a");
			}

		});
	}
	
	@Override
	@Transactional(readOnly=false)
	public void persist(InterStaff staff) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "interStaff.findOrder"); 
		qt.addParameter("organId", staff.getOrganId());
		//获取编号最大的人员信息
		InterStaff result = findFirstDomain(qt);
		//获取组织机构
		Organization organ = organService.getReference(staff.getOrganId());
		if(result == null) {
			//人员编码：组织机构编码+NB+编号
			staff.setCode(organ.getCode()+BaseConstants.INTERSTAFF+BaseConstants.DEFAULTORDER);
			staff.setOrder(0);
		} else {
			//获取顺序
			int order = result.getOrder();
			//设置顺序
			int curOrder = order+1;
			//获取编码前缀
			String code = organ.getCode()+BaseConstants.INTERSTAFF;
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
		//adapter(staff);
		super.persist(staff);
		List<Certificate> certs = staff.getCerts();
		if(certs != null) {
			for(int i =0; i < certs.size(); i++) {
				certs.get(i).setStaffId(staff.getId());
				certService.persist(certs.get(i));
			}
    	}
	}	
	@Override
	@Transactional(readOnly=false)
	public InterStaff merge(InterStaff staff) {
		//adapter(staff);
		List<Certificate> certOlds = certService.findStaffId(staff.getId());
		for(Certificate cert : certOlds){
			certService.remove(cert.getId());
		}
		List<Certificate> certNews = staff.getCerts();
		 if(certNews != null){
			 for(int i=0;i<certNews.size();i++){
				 certNews.get(i).setStaffId(staff.getId());
				 certService.persist(certNews.get(i));
			 }
		 }
		super.merge(staff);
		return staff;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.staff.service.InterStaffService#getCount(java.lang.Long)
	 */
	@Override
	public int getCount(Long organId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "interStaff.findCount");
		qt.addParameter("organId", organId);
		return findCount(qt).intValue();
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.staff.service.InterStaffService#getPie(java.lang.Long)
	 */
	@Override
	public List<Object[]> getPie(Long organId) {
		List<Object[]> datas = Lists.newArrayList();
		Object[] data1 = new Object[2];
		Object[] data2 = new Object[2];
		String interTitle = MessageSourceUtils.getMessage("melon.staff.inter");
		String outerTitle = MessageSourceUtils.getMessage("melon.staff.outer");
		data1[0] = interTitle;
		data1[1] = getCount(organId);
		data2[0] = outerTitle;
		data2[1] = outerService.getCount(organId);
		datas.add(data1);
		datas.add(data2);
		return datas;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.staff.service.InterStaffService#interCount(java.lang.Long)
	 */
	@Override
	public List<Object[]> interCount(Long organId) {
		List<Object[]> result = Lists.newArrayList();
		//查询单位下的人员
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select staff from InterStaff staff where staff.organId = :organId");
		qt.addParameter("organId", organId);
		List<InterStaff> staffs = findDomains(qt);
		//获取专职岗位数据字典
		Map<String, String> dic = dictiionaryService.findByKey("interstaff.fulljob");
		Iterator<String> keyIte = dic.keySet().iterator();
		while(keyIte.hasNext()) {
			Object[] data = new Object[2];
			String next = keyIte.next();
			if(next.equals("100")) {
				continue;
			}
			int count = 0;
			//获取数据字典key值
			Integer key = Integer.valueOf(next);
			//获取数据字典valu值
			String job = dic.get(next);
			data[0] = job;
			for(InterStaff staff : staffs) {
				Integer[] fullJobs = staff.getFullJobs();
				for(int i=0;i<fullJobs.length;i++) {
					if(fullJobs[i].compareTo(key) == 0) {
						count++;
					}
				}
			}
			data[1] = count;
			result.add(data);
		}
		return result;
	}
	
/*	private void adapter(InterStaff staff) {
		Integer[] fullJobs = staff.getFullJobs();
		if(fullJobs != null) {
			staff.setFullJob(Joiner.on(BaseConstants.SPLITER_FLAG).skipNulls().join(fullJobs));
		}
		//
		Integer[] partJobs = staff.getPartJobs();
		if(partJobs != null) {
			staff.setPartJob(Joiner.on(BaseConstants.SPLITER_FLAG).skipNulls().join(partJobs));
		}
		//
		Integer[] techSkills = staff.getTechSkills();
		if(techSkills != null) {
			staff.setTechSkill(Joiner.on(BaseConstants.SPLITER_FLAG).skipNulls().join(techSkills));
		}
		//
		Integer[] certificates = staff.getCertificates();
		if(certificates != null) {
			staff.setCertificate(Joiner.on(BaseConstants.SPLITER_FLAG).skipNulls().join(certificates));
		}
	}*/

	/* (non-Javadoc)
	 * @see secfox.soc.melon.staff.service.InterStaffService#findAll()
	 */
	@Override
	public List<InterStaff> findAll() {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "interstaff.findAll");
		return findDomains(qt);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.staff.service.InterStaffService#create()
	 */
	@Override
	public InterStaff create() {
		InterStaff staff = new InterStaff();
		List<Certificate> certs = Lists.newArrayList();
		Map<String, String> dic = ApplicationContextUtils.getBean(DictionaryService.class).findByKey("interstaff.certificate");
		Iterator<String> certiterator = dic.values().iterator();
		for(int i=0; i<dic.size();i++){
			Certificate cert = new Certificate();
			cert.setCertificate(certiterator.next());
			cert.setCertificateBusiness(uuidUtils.generate(cert));
			certs.add(cert);
		}
		staff.setCerts(certs);
		return staff;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.staff.service.InterStaffService#create()
	 */
	@Override
	public InterStaff updateCreate(InterStaff staff,String certificate,Long staffId) {
		List<Certificate> certs = Lists.newArrayList();
		//获取数据字典的值（用于初始化显示）
		Map<String, String> dic = ApplicationContextUtils.getBean(DictionaryService.class).findByKey("interstaff.certificate");
		Iterator<String> certiterator = dic.values().iterator();
		//根据选择的证书id查找证书是否选择并初始化
		String[] certificates = certificate.split(",");
		    if(certificates.length > 0){
			//根据数据字典迭代所有证书
			for(int i=0; i<dic.size();i++){
				Certificate cert = new Certificate();
				//初始化遍历的当前证书值
				String certeratortmp = certiterator.next();
				//根据当前证书存储的id判断是否存在此证书
				for(int j=0;j<certificates.length;j++){
					//根据数据字典key获取value
					String certificatetext = DictionaryUtils.filterValue("interstaff.certificate", Long.valueOf(certificates[j]));
					//根据名字去查询证书实体对象
					Certificate certOld = certService.findByName(certificatetext,staffId);
					//判断证书实体对象是否和当前遍历的证书名称相同（取出实体对象）
					if(certificatetext.equals(certeratortmp)){
						cert.setEndDate(certOld.getEndDate());
						cert.setCertificate(certeratortmp);
						cert.setCertificateBusiness(certOld.getCertificateBusiness());
					}
				}
				//不存在的证书设置初始值
				if(StringUtils.isBlank(cert.getCertificate())){
					cert.setCertificate(certeratortmp);
					cert.setCertificateBusiness(uuidUtils.generate(cert));
				}
				certs.add(cert);
			}
			staff.setCerts(certs);
		    }
			return staff;
		}
		
	/* (non-Javadoc)
	 * @see secfox.soc.melon.staff.service.InterStaffService#getCerts(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public List<Certificate> getCerts(HttpServletRequest request) {
		List<Certificate> certs = Lists.newArrayList();
		String cert_checked = request.getParameter("cert_checked");
		String[] certNum = cert_checked.split(",");
		for(int i=0;i<certNum.length;i++){
			Certificate cert = new Certificate();
			cert.setCertificate(request.getParameter("certificate"+certNum[i]));
			if(StringUtils.isNotBlank(request.getParameter("endDate"+certNum[i]))){
				cert.setEndDate(Date.valueOf(request.getParameter("endDate"+certNum[i])));
			}
			cert.setCertificateBusiness(request.getParameter("business"+certNum[i]));
			certs.add(cert);
		}
		return certs;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.staff.service.InterStaffService#findWrappedStaffId(java.lang.Long)
	 */
	@Override
	public InterStaff findWrappedStaffId(Long staffId) {
		InterStaff staff = find(staffId);
		List<Certificate> certs = certService.findStaffId(staffId);
		staff.setCerts(certs);
		return staff;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#remove(java.io.Serializable)
	 */
	@Override
	@Transactional(readOnly = false)
	public void remove(Long id) {
		List<Certificate> certs = certService.findStaffId(id);
		for(Certificate cert : certs){
			certService.remove(cert.getId());
		}
		super.remove(id);
	}
	
	
}
