/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.organ;

/**
 * @since 1.0 2014年9月21日,下午2:33:12
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public enum OrganType {
	
	GROUP,//行政区域
	
	COMPANY,//单位
	
	DEPARTMENT;//部门
	
	/**
	 * 判断是否是行政区域
	 * @param index
	 * @return
	 */
	public static boolean isGroup(int index) {
		return GROUP.ordinal() == index;
	}
	
	/**
	 * 判断是否是单位
	 * @param index
	 * @return
	 */
	public static boolean isCompany(int index) {
		return COMPANY.ordinal() == index;
	}
	
	/**
	 * 判断是否是部门
	 * @param index
	 * @return
	 */
	public static boolean isDepartment(int index) {
		return DEPARTMENT.ordinal() == index;
	}
}
