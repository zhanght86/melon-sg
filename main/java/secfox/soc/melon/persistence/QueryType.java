/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.persistence;

/**
 * @since 1.0 2014年8月29日,下午4:11:59
 * 查询分类,不同的查询分类可以导致不同的查询结果,并且对应的服务差别也很大,比如原生SQL就不支持查询到业务对象列表
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public enum QueryType {
	
	/**
	 * Java 对象查询语言, JPA规范(JQL)
	 * 也支持HQL(Hibernate查询语言)
	 */
	JQL,
	
	/*Java ORM 对象映射*/
	/**
	 * ORM文件或业务对象类注册的查询语句(查询条件是固定的)
	 */
	NamedQuery,
	
	/**
	 * 原生SQL查询，语法依赖于不同的数据库
	 */
	SQL,
	
	/**
	 * 将原生SQL映射为对象，详细请参考JPA规范手册
	 */
	SQLResultMapping,
}
