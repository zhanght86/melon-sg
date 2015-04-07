/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.security.service.impl;

import java.math.BigInteger;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
import secfox.soc.melon.persistence.templates.QueryTemplateManager;
import secfox.soc.melon.security.SecurityConstants;
import secfox.soc.melon.security.dao.AccountDao;
import secfox.soc.melon.security.domain.Account;
import secfox.soc.melon.security.domain.HistoryPass;
import secfox.soc.melon.security.domain.LoginRecord;
import secfox.soc.melon.security.domain.Password;
import secfox.soc.melon.security.domain.Role;
import secfox.soc.melon.security.query.AccountPageQuery;
import secfox.soc.melon.security.service.AccountService;
import secfox.soc.melon.security.service.HistoryPassService;
import secfox.soc.melon.security.service.LoginRecordService;
import secfox.soc.melon.system.domain.GlobalConfig;
import secfox.soc.melon.system.service.GlobalConfigService;

import com.google.common.collect.Lists;

/**
 * 帐户管理服务实现类
 * @since 2014-9-23,上午19:34:09
 * @author <a href="mailto:liubing@legendsec.com>刘冰</a>
 * @version  1.0
 */
@Service
public class AccountServiceImpl extends GenericServiceImpl<Account, Long> implements AccountService {

	private AccountDao dao;
	
	//密码加密管理
	private PasswordEncoder passwordEncoder;
	
	private SessionRegistry sessionRegistry;
	
	private HistoryPassService historyService;
	
	//登录记录管理
	private LoginRecordService recordService;
	
	private GlobalConfigService configService;
	
	private OrganizationService organService;
	
	@Inject
	public AccountServiceImpl(AccountDao dao, PasswordEncoder passwordEncoder, SessionRegistry sessionRegistry, HistoryPassService historyService, 
			LoginRecordService recordService, GlobalConfigService configService, OrganizationService organService){
		super();
		this.dao = dao;
		this.passwordEncoder = passwordEncoder;
		this.sessionRegistry = sessionRegistry;
		this.historyService = historyService;
		this.recordService = recordService;
		this.configService = configService;
		this.organService = organService;
				
	}
	
	@Override
	protected GenericDao<Account, Long> getDao() {
		return dao;
	}
	
	@Override
	public Pagination<Account> findPage(AccountPageQuery pageQuery) {
		return findDomainPage(QueryType.JQL, pageQuery, new PaginationBuilder<Account, AccountPageQuery>() {

			@Override
			public void buildSelect(QueryTemplate qt) {
				qt.append("select account from Account account");
			}
			
			@Override
			public void buildWhere(Account s, QueryTemplate qt) {
				//按用户名查询
				String username = s.getUsername();
				if(StringUtils.isNotBlank(username)) {
					qt.append(" and account.username like :username ");
					qt.addParameter("username", QueryTemplate.buildAllLike(StringUtils.trim(username)));
				}
				//按单位查询
				Long companyId = s.getCompanyId();
				if(companyId != null) {
					qt.append(" and account.companyId = :companyId ");
					qt.addParameter("companyId",companyId);
				}
				//按单位查询
				Long departId = s.getDepartId();
				if(departId != null) {
					qt.append(" and account.departId = :departId ");
					qt.addParameter("departId",departId);
				}
			}
			
			@Override
			public void buildBys(String column, SortOrder order, QueryTemplate qt) {
				//默认按序数进行排序
				if(StringUtils.isBlank(column) || StringUtils.equals(column, "id")) {
					column = "order";
					order = SortOrder.asc;
				}
				qt.append(QueryTemplate.buildOrderBy("account", column, order));
			}

			@Override
			public void buildCount(QueryTemplate qt) {
				qt.append("select count(account) from Account account");
			}
			
		});

	}
	
	/* 持久化信息前对密码加密
	 */
	@Override
	@Transactional
	public void persist(Account entity) {
		entity.setPassword(passwordEncoder.encode(entity.getPassword()));
		if(StringUtils.isBlank(entity.getSecPassWord())) {
			//初始密码123456
			entity.setSecPassWord(passwordEncoder.encode(BaseConstants.DEFAULTSECPASS));
		} else {
			entity.setSecPassWord(passwordEncoder.encode(entity.getSecPassWord()));
		}
		dao.persist(entity);
	}

	/* 修改密码
	 */
	@Override
	@Transactional
	public void resetPassword(Password pass, boolean active) {
		Account account = getReference(pass.getId());
		account.setUpdatePassTime(new Date());
		account.setPassword(passwordEncoder.encode(pass.getPassword()));
		//判断账号修改之后是否需要激活
		if(active) {
			account.setActive(false);
		}
		//将上一条密码保存为历史密码
		historyService.persist(new HistoryPass(account.getId(), account.getPassword(), account.getUpdatePassTime()));
		dao.merge(account);
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.security.service.AccountService#checkOriPass(java.lang.String)
	 */
	@Override
	public boolean checkOriPass(String pass, Long id) {
		return passwordEncoder.matches(pass, find(id).getSecPassWord());
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.security.service.AccountService#resetSecPassword(java.lang.String)
	 */
	@Override
	@Transactional(readOnly=false)
	public void resetSecPassword(Password password) {
		Account account = getReference(password.getId());
		account.setSecPassWord((passwordEncoder.encode(password.getPassword())));
		dao.merge(account);
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.security.service.AccountService#orgAccTree(java.lang.Long)
	 */
	@Override
	public List<Organization> orgAccTree(Long rootId) {
		List<Organization> organs = organService.getSubOrgan(rootId);
		List<Account> accouts = findUsersUnderCompany(rootId);
		for(Account acc : accouts) {
			Organization organ = new Organization(acc.getId(), acc.getName(), acc.getCompanyId(), 3);
			organs.add(organ);
		}
		return organs;
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.security.service.AccountService#findUsersUnderCompany(java.lang.Long)
	 */
	@Override
	public List<Account> findUsersUnderCompany(Long organId) {
		List<Organization> organs = organService.getSubOrgan(organId, true);
		List<Long> organIds = Lists.newArrayList();
		for(Organization organ: organs){
			organIds.add(organ.getId());
		}
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select account from Account account where ");
		qt.appendIn("companyId", organIds);
		return findDomains(qt);
	}

	/*
	 * (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = findByUsername(username);
		GlobalConfig config = configService.find();
		// 查找登录记录信息
		LoginRecord record = recordService.findByName(username);
		if (record != null) {
			// 登录错误数判断，每隔3次进行锁定判断
			if (record.getCount()%(config.getTryTimes())==0) {
				Date lastDate = record.getUpdateTime();
				//获取最新记录时间
				Calendar lastCal = DateUtils.toCalendar(lastDate);
				Calendar cal = Calendar.getInstance();
				//判断是否属于同一天
				if (lastCal.get(Calendar.YEAR) == cal.get(Calendar.YEAR)
						&& lastCal.get(Calendar.MONTH) == cal.get(Calendar.MONTH)
						&& lastCal.get(Calendar.DAY_OF_MONTH) == cal.get(Calendar.DAY_OF_MONTH)) {
					
					lastCal.add(Calendar.MINUTE, config.getLockedTime());
					//判断是否超出锁定时间
					if (lastCal.compareTo(Calendar.getInstance())>0) {
						account.setAccountLocked(true);
					} 
				}
			}
		}
		// 添加公用角色
		account.addRole(new Role(SecurityConstants.ROLE_COMMON));
		return account;
	}


	/* 
	 * 查找在线用户
	 */
	@Override
	public List<Account> findOnlineAccounts() {
		List<Object> principals = sessionRegistry.getAllPrincipals();
        List<Account> users = Lists.newArrayList();
        for(Object obj : principals) {
            if(obj instanceof Account) {
                users.add((Account)obj);
            }
        }
        //排序
        Collections.sort(users, new Comparator<Account>() {
        	
        	/*
        	 * (non-Javadoc)
        	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
        	 */
			@Override
			public int compare(Account o1, Account o2) {
				return o1.getOrder() - o2.getOrder();
			}
		});
        return users;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.security.service.AccountService#listUserByOrgans()
	 */
	@Override
	public List<Object[]> listOrganWithCount() {
		//分组统计单位人数
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "account.groupByOrgan");
		return find(qt);
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.security.service.AccountService#listRoleWithCount()
	 */
	@Override
	public List<Object[]> listRoleWithCount() {
		//分组统计岗位人数
		QueryTemplate qt = QueryTemplateManager.find("account.groupByRole");
		return find(qt);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.security.service.AccountService#findAccountByOrgan(java.lang.Long)
	 */
	@Override
	public List<Account> findAccountByOrgan(Long organId) {
		//按照单位Id查找单位人员列表
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "account.accountByOrgan");
		qt.addParameter("organId", organId);
		return findDomains(qt);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.security.service.AccountService#findByUsername(java.lang.String)
	 */
	@Override
	public Account findByUsername(String username) {
		//按照账号查找用户判断唯一性   
	    QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "account.loadUserByUsername");
	    qt.addParameter("username", username);
	    Account account = findFirstDomain(qt);
        return account;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.security.service.AccountService#findByUsername(java.lang.String)
	 */
	@Override
	public Account findByName(String name) {
		//按照账号查找用户判断唯一性   
	    QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "account.findByName");
	    qt.addParameter("name", name);
	    Account account = findFirstDomain(qt);
        return account;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.security.service.AccountService#findUsers(java.lang.Long)
	 */
	@Override
	public List<Account> findUsers(Long organId) {
		//提供查询用户列表
		QueryTemplate qt;
		//参数单位ID根据请求决定，如果为-1代表查找所有用户列表，否则代表查找指定单位的用户列表
		if(organId == -1l){
			qt = QueryTemplate.create(QueryType.NamedQuery, "account.findAllUsers");
		}else{
			qt = QueryTemplate.create(QueryType.NamedQuery, "account.accountByOrgan");
			qt.addParameter("organId", organId);
		}
		return findDomains(qt);
	}
	
	/**
	 * 获取未激活帐号的数量
	 */
	@Override
	public BigInteger getUnActiveCount() {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select count(*) from Account account where account.enabled = false ");
		return findCount(qt);
	}
	
	/**
	 * 获取所有用户的数量
	 */
	@Override
	public BigInteger getTotalUser() {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select count(*) from Account account ");
		return findCount(qt);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.security.service.AccountService#getSubAcc()
	 */
	@Override
	public List<Object[]> getSubAcc() {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select account.id, account.name, account.username from Account account ");
		
		return find(qt);
	}

	@Override
	@Transactional
	public void activateAccount(Password password) {
		Account account = dao.find(password.getId());
		account.setActive(true);//新用户激活成功
		account.setUpdatePassTime(new Date());
		account.setPassword(passwordEncoder.encode(password.getPassword()));
		//将上一条密码保存为历史密码
		historyService.persist(new HistoryPass(password.getId(), account.getPassword(), account.getUpdatePassTime()));
		dao.merge(account);
	}
	
	@Override
	public boolean checkPass(String accountId, String pass, int num) {
		List<HistoryPass> passwords = historyService.findHistory(Long.valueOf(accountId), num);
		for(HistoryPass password :passwords) {
			if(passwordEncoder.matches(pass, password.getPassword())) {
				return true;
			}
		}
		return false;
	}
}
