/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base.template;

import java.io.IOException;

import org.mvel2.templates.TemplateCompiler;
import org.springframework.core.io.Resource;

import secfox.soc.melon.base.template.StrTemplates.Template;
import secfox.soc.melon.base.util.ApplicationContextUtils;

import com.google.common.collect.ImmutableMap.Builder;

/**
 * @since 1.0 2014年10月2日,下午3:34:14
 * 字符模板实现类
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class StrTemplateManager extends AbstractTemplateManager<Template> {
	
	/**
	 * 通过id获取编译过的字符模板
	 * @param id
	 * @return
	 */
	public static final Template find(String id) {
		return ApplicationContextUtils.getBean(StrTemplateManager.class).get(id);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.base.AbstractTemplateManager#addToCache(com.google.common.collect.ImmutableMap.Builder, org.springframework.core.io.Resource)
	 */
	@Override
	protected void addToCache(Builder<String, Template> builder, Resource resource) {
		StrTemplates templates;
		try {
			templates = (StrTemplates)getMarshaller().getXStream().fromXML(resource.getInputStream());
			for(Template tmpl : templates.getTemplates()) {
				//将内容预先编译
				tmpl.setCompiledContent(TemplateCompiler.compileTemplate(tmpl.getContent()));
				builder.put(tmpl.getId(), tmpl);
			}
		} catch (IOException e) {
			//出错的文件将被忽略
			e.printStackTrace();
		}
	}

}
