/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base.formatter;

import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

import secfox.soc.melon.base.annotation.DateFormatter;

import com.google.common.collect.Sets;

/**
 * @since 1.0 2014年9月13日,下午4:35:09
 * Date时间注解格式化工厂,用于将有Date时间注解的属性字段转换为String
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class DateFormatterAnnotationFormatterFactory implements	AnnotationFormatterFactory<DateFormatter> {

	/* (non-Javadoc)
	 * @see org.springframework.format.AnnotationFormatterFactory#getFieldTypes()
	 */
	@Override
	public Set<Class<?>> getFieldTypes() {
		Set<Class<?>> clazzs = Sets.newHashSet();
		clazzs.add(Date.class);
		return clazzs;
	}

	/* (non-Javadoc)
	 * @see org.springframework.format.AnnotationFormatterFactory#getParser(java.lang.annotation.Annotation, java.lang.Class)
	 */
	@Override
	public Parser<?> getParser(final DateFormatter dateFormatter, Class<?> arg1) {
		//解析字符串,用于将字符串转换为Date
		return new Parser<Date>() {

			@Override
			public Date parse(String value, Locale locale) throws ParseException {
				if(StringUtils.isNotBlank(value)) {
					DateFormat format = dateFormatter.value().getFormatter();
					return format.parse(value);
				}
				return null;
			}
			
		};
	}

	/* (non-Javadoc)
	 * @see org.springframework.format.AnnotationFormatterFactory#getPrinter(java.lang.annotation.Annotation, java.lang.Class)
	 */
	@Override
	public Printer<?> getPrinter(final DateFormatter dateFormatter, Class<?> arg1) {
		//打印Date
		return new Printer<Date>() {

			@Override
			public String print(Date date, Locale arg1) {
				if(date != null) {
					DateFormat dateFormat = dateFormatter.value().getFormatter();
					return dateFormat.format(date);
				}
				return "";
			}
			
		};
	}

}
