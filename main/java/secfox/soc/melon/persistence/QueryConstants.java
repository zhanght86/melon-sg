/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.persistence;

/**
 * @since 1.0 2014年10月2日,下午12:19:48
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public interface QueryConstants {
	
	/**
     * 每次查询的最大记录数，默认100
     */
    public static final int MAX_RESULTS = 100;
    
    /**
     * 查询条件辅助语句
     */
    public static final String WHERE = " where 1=1 ";
    
    /**
     * 模糊匹配辅助符
     */
    public static final String LIKE_FLAG = "%";
    
    /**
     * 排序字段辅助语句
     */
    public static final String ORDER_BY = " order by {0}.{1} {2} ";
    
    /**
     * in查询辅助语句
     */
    public static final String IN = " {0} in ({1}) ";
    
    /**
     * 默认的entityManager名称
     */
    public static final String ENTITY_MANAGER_NAME = "MELON_ENTITY_MANAGER";
    
    /**
     * 
     */
    public static final String PERSIST_UNIT_NAME = "MELON_PERSIST_UNIT";
}
