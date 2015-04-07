/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.asset.secpass.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;

import secfox.soc.melon.asset.secpass.dao.SecPassDao;
import secfox.soc.melon.asset.secpass.domain.SecPass;
import secfox.soc.melon.asset.secpass.encrypt.Encrypt;
import secfox.soc.melon.asset.secpass.query.SecPassPageQuery;
import secfox.soc.melon.asset.secpass.query.SecPassSearchForm;
import secfox.soc.melon.asset.secpass.service.SecPassService;
import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.SortOrder;
import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.PaginationBuilder;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.security.domain.Account;
import secfox.soc.melon.security.service.AccountService;
import secfox.soc.melon.work.myWork.domain.MyWork;

/**
 * @since 2014-11-17,下午4:01:11
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Service
public class SecPassServiceImpl extends GenericServiceImpl<SecPass, Long> 
		implements SecPassService {
	
	private SecPassDao dao;
	private AccountService accountService;
	
	@Inject
	public SecPassServiceImpl(SecPassDao dao, AccountService accountService) {
		super();
		this.dao = dao;
		this.accountService = accountService; 
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<SecPass, Long> getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.asset.secpass.service.SecPassService#findPagination(secfox.soc.melon.asset.secpass.query.SecPassPageQuery)
	 */
	@Override
	public Pagination<SecPass> findPagination(SecPassPageQuery pageQuery) {
		return this.findDomainPage(QueryType.JQL, pageQuery, new PaginationBuilder<SecPassSearchForm, SecPassPageQuery>() {

			@Override
			public void buildSelect(QueryTemplate qt) {
				qt.append("select a from SecPass a ");
			}

			@Override
			public void buildWhere(SecPassSearchForm s, QueryTemplate qt) {
				
				qt.append(" and a.state = 1 ");
				qt.append(" and a.user.userId = :userId");
				qt.addParameter("userId", s.getUserId());
				
				//设备名称
				if (StringUtils.isNotBlank(s.getAssetName())) {
					qt.append(" and a.assetName like :assetName");
					qt.addParameter("assetName", QueryTemplate.buildAllLike(s.getAssetName()));
				}
				
				//IP地址
				if (StringUtils.isNotBlank(s.getIp())) {
					qt.append(" and a.ip = :ip");
					qt.addParameter("ip", s.getIp());
				}
			}

			@Override
			public void buildBys(String column, SortOrder order, QueryTemplate qt) {
				if (StringUtils.isBlank(column))
					column = "assetName";
				qt.append(QueryTemplate.buildOrderBy("a", column, order));
			}

			@Override
			public void buildCount(QueryTemplate qt) {
				qt.append("select count(a.id) from SecPass a");
			}

		});
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.asset.secpass.service.SecPassService#findHistory(java.lang.Long)
	 */
	@Override
	public List<SecPass> findHistory(Long id) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "secpass.findHistory");
		qt.addParameter("mainId", id);
		return findDomains(qt);
	}
	
	/**
	 * 保存
	 */
	@Override
	@Transactional(readOnly=false)
	public void persist(SecPass secPass) {
		Encrypt encrypt = new Encrypt();
		int alert = secPass.getAlert();//获取提醒时间
		Date cur = new Date();
		Calendar calendar = DateUtils.toCalendar(cur);//获取当前日期
		calendar.add(Calendar.DAY_OF_YEAR, alert);//计算到期日期
		secPass.setCreateDate(cur);//创建时间
		secPass.setUpdateDate(cur);//口令更新时间
		secPass.setDueDate(calendar.getTime());
		secPass.setPassWord(encrypt.getEncString(secPass.getDesPassWord()));
		secPass.setState(1);
		dao.persist(secPass);
	}
	
	/**
	 * 修改
	 */
	@Override
	@Transactional(readOnly=false)
	public SecPass merge(SecPass secPass) {
		Encrypt encrypt = new Encrypt();
		//获取口令数据
		SecPass oriSecPass = find(secPass.getId());
		int alert = secPass.getAlert();//获取提醒时间
		Calendar calendar = DateUtils.toCalendar(secPass.getCreateDate());//获取当前日期
		calendar.add(Calendar.DAY_OF_YEAR, alert);//计算到期日期
		secPass.setDueDate(calendar.getTime());
		secPass.setPassWord(encrypt.getEncString(secPass.getDesPassWord()));//设置密码
		//判断是否更新口令
		if(encrypt.matches(secPass.getDesPassWord(), oriSecPass.getPassWord())) {
			//未更新口令，只保存基本信息
			return dao.merge(secPass);
		} else {
			//更新口令，保存信息并创建历史信息
			SecPass overSecPass = oriSecPass.copy();//创建历史数据
			overSecPass.setState(2);//设置数据位历史数据
			overSecPass.setMainId(oriSecPass.getId());//设置关联id
			dao.persist(overSecPass);
			secPass.setUpdateDate(new Date());
			//重新设置密码
			return dao.merge(secPass);
		}
	}
	
	/**
	 * 删除
	 */
	@Override
	@Transactional(readOnly=false)
	public void remove(Long id) {
		SecPass secPass = getReference(id);
		secPass.setState(0);
		dao.merge(secPass);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.asset.secpass.service.SecPassService#findDuePass(java.util.Date)
	 */
	@Override
	public List<Object[]> getTask(Date date) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "secpass.getTask");
		SimpleDateFormat df = new SimpleDateFormat(BaseConstants.SHORT_DATE_FORMAT);
		try {
			String dateStr = df.format(date);
			qt.addParameter("dueDate", df.parse(dateStr));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return find(qt); 
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.asset.secpass.service.SecPassService#findDuePass(java.util.Date, java.lang.Long)
	 */
	@Override
	public List<SecPass> findDuePass(Date date, Long userId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "secpass.findDuePass");
		SimpleDateFormat df = new SimpleDateFormat(BaseConstants.SHORT_DATE_FORMAT);
		try {
			String dateStr = df.format(date);
			qt.addParameter("dueDate", df.parse(dateStr));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		qt.addParameter("userId", userId);
		return findDomains(qt);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.work.myWork.service.WorkScanService#scan()
	 */
	@Override
	public List<MyWork> scan() {
		List<MyWork> works = Lists.newArrayList();
		Date date = new Date();
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "secpass.getTask");
		SimpleDateFormat df = new SimpleDateFormat(BaseConstants.SHORT_DATE_FORMAT);
		try {
			String dateStr = df.format(date);
			qt.addParameter("dueDate", df.parse(dateStr));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		List<Object[]> result =  find(qt); 
		for(Object object[] : result) {
			Account account = accountService.find(Long.valueOf(String.valueOf(object[0])));
			//标题
			String title = MessageSourceUtils.getMessage("asset.secPass.notice.title");
			//内容
			Object[] param = {object[1]};
			String content = MessageSourceUtils.getMessage("asset.secPass.notice.content", param);
			//执行人
			String execute = object[0] + BaseConstants.SPLITER_FLAG;
			//单位id
			Long companyId = account.getCompanyId();
			//单位名称
			String companyName = account.getCompanyName();
			//操作url
			String url = "asset/secpass/list?inline=0";
			MyWork work = new MyWork(title, 3, 2, execute, companyId, companyName, date, content, url, 2);
			works.add(work);
		}
		return works;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.work.myWork.service.WorkScanService#scanPassed()
	 */
	@Override
	public List<MyWork> scanPassed() {
		return null;
	}

}
