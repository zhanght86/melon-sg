/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.integration;

import org.springframework.integration.core.MessagingTemplate;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import secfox.soc.melon.base.util.ApplicationContextUtils;

/**
 * @since 1.0 2014年9月28日,下午5:23:39
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class MessageChannelUtils {
	
	private MessageChannelUtils() {
		
	}
	
	/**
	 * 将消息发送到指定通道
	 * @param channel 通道名称
	 * @param message 消息
	 */
	public static void send(String channel, Message<?> message) {
		ApplicationContextUtils.getBean("messageTemplate", MessagingTemplate.class)
							   .send(channel, message);
	}
	
	/**
	 * 
	 * @param channel
	 * @param messageBody
	 */
	public static void send(String channel, Object messageBody) {
		Message<Object> message = MessageBuilder.withPayload(messageBody).build();
		send(channel, message);
	}
	
	
}
