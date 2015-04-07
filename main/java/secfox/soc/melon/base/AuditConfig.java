/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;
import com.thoughtworks.xstream.annotations.XStreamOmitField;

/**
 * @since 1.0 2014年9月30日,上午11:50:58
 * 系统审计配置类,用于将用户请求的方法源转换为可识别的中文操作行为
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@XStreamAlias("auditConfig")
public class AuditConfig {
	
	@XStreamImplicit
	private List<AuditModule> modules;
	
	@XStreamAlias("defaults")
	private List<AuditItem> defaults;
	
	private ImmutableMap<String, AuditClass> classCache;
	
	/**
	 * 初始化
	 */
	public void init() {
		ImmutableMap.Builder<String, AuditClass> builder = ImmutableMap.builder();
		for(AuditModule module : modules) {
			for(AuditClass clazz : module.getClasses()) {
				clazz.setModule(module);
				builder.put(clazz.getName(), clazz);
			}
		}
		this.classCache = builder.build();
	}
	
	/**
	 * 
	 * @param name
	 * @return
	 */
	public AuditClass find(String name) {
		return this.classCache.get(name);
	}
	
	/**
	 * 添加模块
	 * @param module
	 */
	public void addModule(AuditModule module) {
		if(modules == null) {
			this.modules = Lists.newArrayList();
		}
		this.modules.add(module);
	}
	
	/**
	 * 添加日志配置对象
	 * @param auditConfig
	 */
	public void addAuditConfig(AuditConfig auditConfig) {
		this.defaults.addAll(auditConfig.getDefaults());
		this.modules.addAll(auditConfig.getModules());
	}

	/**
	 * 
	 * @return
	 */
	public List<AuditModule> getModules() {
		return modules;
	}

	/**
	 * 
	 * @param modules
	 */
	public void setModules(List<AuditModule> modules) {
		this.modules = modules;
	}
	
	/**
	 * 
	 * @param item
	 */
	public void addDefaultMethod(AuditItem item) {
		if(defaults == null) {
			this.defaults = Lists.newArrayList();
		}
		this.defaults.add(item);
	}

	/**
	 * 
	 * @return
	 */
	public List<AuditItem> getDefaults() {
		return defaults;
	}

	/**
	 * 
	 * @param defaults
	 */
	public void setDefaults(List<AuditItem> defaults) {
		this.defaults = defaults;
	}
	
	/**
	 * 方法审计
	 * @since 1.0 2014年9月30日,上午11:55:48
	 * 默认的识别对象,以及审计对象最小元
	 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
	 * @version  1.0
	 */
	@XStreamAlias("method")
	public static class AuditItem {
		
		@XStreamAsAttribute
		private String name;
		
		@XStreamAsAttribute
		private String title;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((name == null) ? 0 : name.hashCode());
			result = prime * result + ((title == null) ? 0 : title.hashCode());
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			AuditItem other = (AuditItem) obj;
			if (name == null) {
				if (other.name != null)
					return false;
			} else if (!name.equals(other.name))
				return false;
			if (title == null) {
				if (other.title != null)
					return false;
			} else if (!title.equals(other.title))
				return false;
			return true;
		}
		
		/*
		 * (non-Javadoc)
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString() {
			return Objects.toStringHelper(this)
						  .add("name", this.name)
						  .add("title", this.title)
						  .toString();
		}
		
	}

	/**
	 * 模块审计
	 * @since 1.0 2014年9月30日,上午11:57:20
	 * 
	 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
	 * @version  1.0
	 */
	@XStreamAlias("module")
	public static class AuditModule extends AuditItem{
		
		@XStreamImplicit
		private List<AuditClass> classes;

		public List<AuditClass> getClasses() {
			return classes;
		}

		public void setClasses(List<AuditClass> classes) {
			this.classes = classes;
		}
		
		/**
		 * 
		 * @param auditClass
		 */
		public void addClass(AuditClass auditClass) {
			if(this.classes == null) {
				this.classes = Lists.newArrayList();
			}
			this.classes.add(auditClass);
		}
		
	}

	/**
	 * 类审计
	 * @since 1.0 2014年9月30日,上午11:56:49
	 * 
	 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
	 * @version  1.0
	 */
	@XStreamAlias("class")
	public static class AuditClass extends AuditItem {
		
		/**
		 * 拥有的方法
		 */
		@XStreamImplicit
		private List<AuditItem> methods;
		
		/**
		 * 
		 */
		@XStreamOmitField
		private AuditModule module;

		/**
		 * 
		 * @return
		 */
		public List<AuditItem> getMethods() {
			return methods;
		}

		/**
		 * 
		 * @param methods
		 */
		public void setMethods(List<AuditItem> methods) {
			this.methods = methods;
		}
		
		/**
		 * 
		 * @return the module
		 */
		public AuditModule getModule() {
			return module;
		}

		/**
		 * 
		 * @param module the module to set
		 */
		public void setModule(AuditModule module) {
			this.module = module;
		}

		/**
		 * 添加方法
		 * @param item
		 */
		public void addMethod(AuditItem item) {
			if(this.methods == null) {
				this.methods = Lists.newArrayList();
			}
			this.methods.add(item);
		}
		
		/**
		 * 
		 * @param method
		 */
		public AuditItem getMethod(String method) {
			for(AuditItem item : methods) {
				if(StringUtils.equals(item.getName(), method)) {
					return item;
				}
			}
			return null;
		}
		

		@Override
		public String toString() {
			return "AuditClass [methods=" + methods + ", getName()=" + getName()
					+ ", getTitle()=" + getTitle() + "]";
		}
		
	}
	
}
