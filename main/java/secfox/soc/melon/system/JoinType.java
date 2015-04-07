/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system;

/**
 * @since 1.0 2013-3-20,下午8:46:25
 * 规则匹配器联接类型
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public enum JoinType {
	AND(" && "),
	OR(" || "),
	NO("!"),
	XOR("");
	
	private String oper;
	
	private JoinType(final String oper) {
		this.oper = oper;
	}
	
	public String getOper() {
		return oper;
	}
}
