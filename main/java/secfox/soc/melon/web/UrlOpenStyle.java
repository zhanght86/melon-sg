/*
 * (c) Copyright 2012 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.web;

/**
 * @since 0.1 2012-12-17,下午2:53:05下午
 *
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  0.1 
 */
public enum UrlOpenStyle {
	
	NONE,//不执行操作
	
	AJAX,//通过ajax请求
	
	NEW,//重新打开新窗口
	
	MODEL,//模式窗口
	
	SELF;//当前页打开
	
	public static UrlOpenStyle get(int order) {
		for(UrlOpenStyle style : UrlOpenStyle.values()) {
			if(order == style.ordinal()) {
				return style;
			}
		}
		return null;
	}
	
}
