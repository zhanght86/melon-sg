/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.security.service;

import java.math.BigInteger;
import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.organ.domain.Organization;
import secfox.soc.melon.persistence.GenericService;
import secfox.soc.melon.security.domain.Account;
import secfox.soc.melon.security.domain.Password;
import secfox.soc.melon.security.query.AccountPageQuery;

/**
 * 用户管理接口
 * @since 2014-9-23,上午19:34:09
 * @author <a href="mailto:liubing@legendsec.com>刘冰</a>
 * @version  1.0
 */
public interface AccountService extends GenericService<Account, Long>, UserDetailsService {

	/**
	 * 获取角色/岗位的分页查询
	 * @param pageQuery
	 * @return
	 */
	Pagination<Account> findPage(AccountPageQuery pageQuery);
	
	/**
	 * 重置密码
	 * @param id
	 * @param password
	 * @param active
	 */
	void resetPassword(Password password, boolean active);
	
	/**
	 * 重置口令管理验证密码
	 * @param password
	 */
	void resetSecPassword(Password password);
	
	/**
	 * 判断原密码一致性
	 * @param pass
	 * @param id
	 * @return
	 */
	boolean checkOriPass(String pass, Long id);
	
	/**
	 * 新用户首次登陆激活密码
	 * @param password
	 */
	void activateAccount(Password password);
	
	/**
	 * 账号组织机构树
	 * @param rootId
	 * @return
	 */
	List<Organization> orgAccTree(Long rootId);
	
	/**
	 * 查看指定单位下所有用户
	 * @return
	 */
	List<Account> findUsersUnderCompany(Long organId);
	
	/**
	 * 获取在线用户
	 * @return
	 */
	List<Account> findOnlineAccounts();
	
	/**
	 * 获取各个单位的人员列表
	 * @return
	 */
	List<Object[]> listOrganWithCount();
	
	/**
	 * 按岗位统计人员
	 */
	List<Object[]> listRoleWithCount();
	
	/**
	 * 按照指定单位获取人员列表
	 * @param organId
	 * @return
	 */
	List<Account> findAccountByOrgan(Long organId);
	
	/**
	 * 按用户账号查找
	 * @param username
	 * @return
	 */
	Account findByUsername(String username);

	/**
	 * 按用户姓名查找
	 * @param username
	 * @return
	 */
	Account findByName(String name);

	/**
	 * 查看所有用户
	 * @return
	 */
	List<Account> findUsers(Long organId);
	
	/**
	 * 获取未激活账户的数量
	 * @return
	 */
	BigInteger getUnActiveCount();
	
	/**
	 * 获取所有用户的数量
	 * @return
	 */
	BigInteger getTotalUser();
	
	/**
	 * 子表查询测试
	 * @return
	 */
	List<Object[]> getSubAcc();
	
	/**
	 * 查询密码是否与历史密码重复
	 * @param accountId
	 * @param pass
	 * @param num
	 * @return
	 */
	public boolean checkPass(String accountId, String pass, int num);

}
