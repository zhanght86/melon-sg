/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import secfox.soc.melon.base.DateTimeType;

/**
 * @since 1.0 2013-4-11,上午10:26:02
 * 日期格式化注解,用于格式化类的Date属性字段
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Target( {ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DateFormatter {
	
    /**
     * 事件的格式化器
     * @return
     */
	DateTimeType value() default DateTimeType.SHORT_DATE;
}
