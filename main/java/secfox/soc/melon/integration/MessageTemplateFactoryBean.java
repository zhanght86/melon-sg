/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.integration;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.integration.core.MessagingTemplate;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.core.DestinationResolutionException;
import org.springframework.messaging.core.DestinationResolver;

import secfox.soc.melon.base.util.ApplicationContextUtils;

/**
 * @since 1.0 2014年9月28日,下午5:34:14
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class MessageTemplateFactoryBean implements FactoryBean<MessagingTemplate>{

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#getObject()
	 */
	@Override
	public MessagingTemplate getObject() throws Exception {
		MessagingTemplate template = new MessagingTemplate();
		template.setDestinationResolver(new DestinationResolver<MessageChannel>() {
			
			@Override
			public MessageChannel resolveDestination(String name) throws DestinationResolutionException {
				return ApplicationContextUtils.getBean(name, MessageChannel.class);
			}
			
		});
		return template;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#getObjectType()
	 */
	@Override
	public Class<MessagingTemplate> getObjectType() {
		return MessagingTemplate.class;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.FactoryBean#isSingleton()
	 */
	@Override
	public boolean isSingleton() {
		return true;
	}

}
