/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base.template;

import java.util.List;

import org.mvel2.optimizers.OptimizerFactory;
import org.springframework.core.io.Resource;
import org.springframework.oxm.xstream.XStreamMarshaller;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

/**
 * @since 1.0 2014年10月2日,下午3:22:44
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public abstract class AbstractTemplateManager<S> implements TemplateManager<S> {
	
	
	private List<Resource> locations;
	
	private XStreamMarshaller marshaller;
	
	private ImmutableMap<String, S> caches;

	/* (non-Javadoc)
	 * @see secfox.soc.melon.base.TemplateManager#setLocations(java.util.List)
	 */
	@Override
	public void setLocations(List<Resource> resources) {
		this.locations = resources;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.base.TemplateManager#setMarshaller(org.springframework.oxm.xstream.XStreamMarshaller)
	 */
	@Override
	public void setMarshaller(XStreamMarshaller marshaller) {
		this.marshaller = marshaller;
	}

	/**
	 * 
	 * @return the locations
	 */
	public List<Resource> getLocations() {
		return locations;
	}

	/**
	 * 
	 * @return the marshaller
	 */
	public XStreamMarshaller getMarshaller() {
		return marshaller;
	}

	/* (non-Javadoc)
	 * @see org.springframework.beans.factory.InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		Builder<String, S> builder = new Builder<String, S>();
		for(Resource resource : locations) {
			addToCache(builder, resource);
		}
		caches = builder.build();
	}

	/**
	 * 将每一个配置文件解析为具体的对象
	 * @param builder
	 * @param resource
	 */
	protected abstract void addToCache(Builder<String, S> builder, Resource resource);

	/* (non-Javadoc)
	 * @see secfox.soc.melon.base.TemplateManager#get(java.lang.String)
	 */
	@Override
	public S get(String id) {
		return caches.get(id);
	}
	
}
