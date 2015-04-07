/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.web.tag;

import java.io.IOException;

import org.springframework.web.servlet.tags.ThemeTag;

import secfox.soc.melon.web.util.RequestUtils;

/**
 * @since 1.0 2014年8月25日,下午5:49:17
 * 解析主题文件中配置的资源请求地址,并将资源请求地址转换为绝对请求地址
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class ThemeUrlTag extends ThemeTag{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6005828009673210183L;

	/*
	 * (non-Javadoc)
	 * @see org.springframework.web.servlet.tags.MessageTag#writeMessage(java.lang.String)
	 */
	@Override
	protected void writeMessage(String msg) throws IOException {
		String result = String.valueOf(msg);
		//添加上WEB上下文请求路径
		result = RequestUtils.getContextPath(pageContext) + result;
		super.writeMessage(result);
	}
	
}
