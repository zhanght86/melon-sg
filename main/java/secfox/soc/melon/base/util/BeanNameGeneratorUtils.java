/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base.util;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.AnnotationBeanNameGenerator;

/**
 * @since 1.0 2013-3-12,下午1:24:55
 * 删除Bean名称后的"Impl"
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class BeanNameGeneratorUtils extends AnnotationBeanNameGenerator {
	
	/*
	 * (non-Javadoc)
	 * @see org.springframework.context.annotation.AnnotationBeanNameGenerator#generateBeanName(org.springframework.beans.factory.config.BeanDefinition, org.springframework.beans.factory.support.BeanDefinitionRegistry)
	 */
	@Override
	public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        String beanName = super.generateBeanName(definition, registry);
        if (!beanName.endsWith("Impl")) {
            return beanName;
        }
        return beanName.substring(0, beanName.length() - 4);
    }
}
