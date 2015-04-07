/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.persistence;

/**
 * @since 1.0 2014年8月29日,下午2:06:38
 * 通过SQL映射创建SQL查询模板
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class SqlResultMappingQueryTemplate extends QueryTemplate {
	
	private String sqlResultMapping;

	/**
	 * 
	 */
	public SqlResultMappingQueryTemplate() {
		super();
	}
	
	/**
	 * @param query 指向SqlResultMapping定义的标识符
	 */
	public SqlResultMappingQueryTemplate(String query) {
		super(query);
	}
	
	/**
	 * 
	 * @param query
	 * @param sqlRString
	 */
	public SqlResultMappingQueryTemplate(String query, String sqlRString) {
		this(query);
		this.sqlResultMapping = sqlRString;
	}

	/**
	 * 
	 * @return
	 */
	public String getSqlResultMapping() {
		return sqlResultMapping;
	}

	/**
	 * 
	 * @param sqlResultMapping
	 */
	public void setSqlResultMapping(String sqlResultMapping) {
		this.sqlResultMapping = sqlResultMapping;
	}
	
}
