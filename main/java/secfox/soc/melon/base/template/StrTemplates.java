/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base.template;

import java.util.List;
import java.util.Map;

import org.mvel2.optimizers.OptimizerFactory;
import org.mvel2.templates.CompiledTemplate;
import org.mvel2.templates.TemplateRuntime;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * @since 1.0 2014年10月2日,下午2:57:51
 * 字符模板
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@XStreamAlias("templates")
public class StrTemplates {
	
	@XStreamImplicit
	public List<Template> templates;
	
	/**
	 * 
	 * @return the templates
	 */
	public List<Template> getTemplates() {
		return templates;
	}

	/**
	 * 
	 * @param templates the templates to set
	 */
	public void setTemplates(List<Template> templates) {
		this.templates = templates;
	}

	/**
	 * 添加模板
	 * @param tmpl
	 */
	public void addTemplate(Template tmpl) {
		if(templates == null) {
			templates = Lists.newArrayList();
		}
		templates.add(tmpl);
	}

	/**
	 * 
	 * @since 1.0 2014年10月2日,下午3:11:52
	 * String 模板
	 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
	 * @version  1.0
	 */
	@XStreamAlias("template")
	public static final class Template {
		
		/**
		 * id标示,通过此标示获取响应的模板
		 */
		@XStreamAsAttribute
		private String id;
		
		/**
		 * 默认的描述信息
		 */
		private String description;
		
		/**
		 * 模板的内容
		 */
		private String content;
		
		/**
		 * 编译过的模板内容
		 */
		@XStreamOmitField
		private CompiledTemplate compiledContent;

		/**
		 * 
		 * @return the compiledContent
		 */
		public CompiledTemplate getCompiledContent() {
			return compiledContent;
		}

		/**
		 * 
		 * @param compiledContent the compiledContent to set
		 */
		public void setCompiledContent(CompiledTemplate compiledContent) {
			this.compiledContent = compiledContent;
		}

		/**
		 * 
		 * @return the id
		 */
		public String getId() {
			return id;
		}

		/**
		 * 
		 * @param id the id to set
		 */
		public void setId(String id) {
			this.id = id;
		}

		/**
		 * 
		 * @return the description
		 */
		public String getDescription() {
			return description;
		}

		/**
		 * 
		 * @param description the description to set
		 */
		public void setDescription(String description) {
			this.description = description;
		}

		/**
		 * 
		 * @return the content
		 */
		public String getContent() {
			return content;
		}

		/**
		 * 
		 * @param content the content to set
		 */
		public void setContent(String content) {
			this.content = content;
		}
		
		/**
		 * 
		 * @param args 业务对象
		 * @return 利用业务对象格式化后的字符内容
		 */
		public String format(Object args) {
			return (String)TemplateRuntime.execute(compiledContent, args);
		}
		
		/**
		 * 格式化字符串
		 * @param args 包含格式化内容的Map
		 * @return 利用Map格式化后的字符内容
		 */
		public String format(Map<String, Object> args) {
			return (String)TemplateRuntime.execute(compiledContent, args);
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return Objects.toStringHelper(this)
						  .add("id", this.id)
						  .add("description", this.description)
						  .add("content", this.content)
						  .toString();
		}
		
	}
	
}
