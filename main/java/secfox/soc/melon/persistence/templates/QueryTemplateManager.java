/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.persistence.templates;

import java.io.IOException;

import org.springframework.core.io.Resource;

import com.google.common.collect.ImmutableMap.Builder;

import secfox.soc.melon.base.template.AbstractTemplateManager;
import secfox.soc.melon.base.util.ApplicationContextUtils;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.templates.QueryTemplates.Query;

/**
 * @since 1.0 2014年10月17日,下午1:51:16
 * 查询语句模板管理器
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class QueryTemplateManager extends AbstractTemplateManager<QueryTemplate> {

	/* (non-Javadoc)
	 * @see secfox.soc.melon.base.template.AbstractTemplateManager#addToCache(com.google.common.collect.ImmutableMap.Builder, org.springframework.core.io.Resource)
	 */
	@Override
	protected void addToCache(Builder<String, QueryTemplate> builder, Resource resource) {
		QueryTemplates querys;
		try {
			querys = (QueryTemplates)getMarshaller().getXStream().fromXML(resource.getInputStream());
			for(Query query : querys.getQuerys()) {
				//将查询语句转换为查询模板
				builder.put(query.getId(), query.toQueryTemplate());
			}
		} catch (IOException e) {
			//出错的文件将被忽略
			e.printStackTrace();
		}
	}
	
	/**
	 * 通过id获取编译过的字符模板
	 * @param id
	 * @return
	 */
	public static final QueryTemplate find(String id) {
		return ApplicationContextUtils.getBean(QueryTemplateManager.class).get(id);
	}

}
