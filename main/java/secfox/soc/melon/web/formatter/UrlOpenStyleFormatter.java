package secfox.soc.melon.web.formatter;

import java.text.ParseException;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import secfox.soc.melon.web.UrlOpenStyle;

/**
 * 
 * @since 1.0 2014年10月4日,下午3:06:06
 * URL 打开方式格式化器
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0
 */
@Component("openStyleFormatter")
public class UrlOpenStyleFormatter implements Formatter<Enum<UrlOpenStyle>> {
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.format.Printer#print(java.lang.Object, java.util.Locale)
	 */
	@Override
	public String print(Enum<UrlOpenStyle> en, Locale locale) {
		if(en != null) {
			return String.valueOf(en.ordinal());
		}
		return "";
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.format.Parser#parse(java.lang.String, java.util.Locale)
	 */
	@Override
	public Enum<UrlOpenStyle> parse(String text, Locale locale) throws ParseException {
		if(StringUtils.isNotBlank(text)) {
			int order = Integer.parseInt(text);
			return UrlOpenStyle.get(order);
		}
		return null;
	}

}
