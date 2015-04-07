/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base;

import java.lang.annotation.Annotation;
import java.util.Set;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;

/**
 * @since 1.0 2014年9月13日,下午4:14:52
 * Annotation自定义格式化类 注册管理器
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class FormatterRegisterFactory implements BeanPostProcessor, FormatterRegistrar {
	
	private Set<AnnotationFormatterFactory<? extends Annotation>> formatterFactorys;
	
	private FormatterRegistry registry;

	/* (non-Javadoc)
	 * @see org.springframework.format.FormatterRegistrar#registerFormatters(org.springframework.format.FormatterRegistry)
	 */
	@Override
	public void registerFormatters(FormatterRegistry formatterRegistry) {
		this.registry = formatterRegistry;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessBeforeInitialization(java.lang.Object, java.lang.String)
	 */
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName)	throws BeansException {
		return bean;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization(java.lang.Object, java.lang.String)
	 */
	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName)	throws BeansException {
		if(registry != null) {
			//逐步添加属性字段格式化器
			for(AnnotationFormatterFactory<? extends Annotation> formatterFactory : formatterFactorys) {
				registry.addFormatterForFieldAnnotation(formatterFactory);
			}
		}
		return bean;
	}

	public void setFormatterFactorys(Set<AnnotationFormatterFactory<? extends Annotation>> formatterFactorys) {
		this.formatterFactorys = formatterFactorys;
	}
	
}
