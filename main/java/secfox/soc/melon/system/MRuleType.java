/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system;

/**
 * @since 1.0 2013-3-20,下午7:59:38
 *
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public enum MRuleType {
	
	CONDITION(0),    //条件表达式
	
	JOIN(1), //逻辑算法符
	
	RULE(2);//可以成为单独的规则器
	
private int value = 0;
	
	private MRuleType(int value) {
		this.value = value;
	}
	
	public int value() {
		return this.value;
	}
}
