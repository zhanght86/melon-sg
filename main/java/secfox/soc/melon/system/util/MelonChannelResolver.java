/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.util;

import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.core.DestinationResolver;
import org.springframework.stereotype.Component;

import secfox.soc.melon.base.util.ApplicationContextUtils;

/**
 * @since 1.0 2013-5-9,上午10:03:46
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Component("channelResolver")
public class MelonChannelResolver implements DestinationResolver<MessageChannel> {
    
    /* (non-Javadoc)
     * @see org.springframework.integration.support.channel.ChannelResolver#resolveChannelName(java.lang.String)
     */
    @Override
    public MessageChannel resolveDestination(String channelName) {
        return ApplicationContextUtils.getBean(channelName, MessageChannel.class);
    }

}
