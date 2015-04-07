/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base.template;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.oxm.xstream.XStreamMarshaller;

/**
 * @since 1.0 2014年8月29日,上午10:42:54
 * 模板管理器
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public interface TemplateManager<S> extends InitializingBean{
	
	/**
	 * 根据模板id获取模板内容
	 * @param id 模板id
	 * @return 模板内容
	 */
	public S get(String id);
	
	/**
	 * 设置模板文件所在的位置
	 * @param resources
	 */
	public void setLocations(List<Resource> resources);
	
	/**
	 * 将XML转换为JAVA对象
	 * @param marshaller
	 */
	public void setMarshaller(XStreamMarshaller marshaller);
	
}
