/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.persistence.templates;

import java.util.Set;

import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;

import com.google.common.base.Objects;
import com.google.common.collect.Sets;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

/**
 * @since 1.0 2014年10月17日,下午1:31:46
 * 查询文件管理器
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@XStreamAlias("querys")
public class QueryTemplates {
	
	@XStreamImplicit
	public Set<Query> querys;
	
	/**
	 * 
	 * @param query
	 */
	public void addQuery(Query query) {
		if(querys == null) {
			querys = Sets.newHashSet(query);
		} else {
			querys.add(query);
		}
	}
	
	/**
	 * 
	 * @return the querys
	 */
	public Set<Query> getQuerys() {
		return querys;
	}

	/**
	 * 
	 * @param querys the querys to set
	 */
	public void setQuerys(Set<Query> querys) {
		this.querys = querys;
	}



	/**
	 * 查询语句
	 * @since 1.0 2014年10月17日,下午1:37:11
	 * 
	 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
	 * @version  1.0
	 */
	public static abstract class Query {
		
		/**
		 * SQL的ID
		 */
		@XStreamAsAttribute
		private String id;
		
		/**
		 * 查询的描述
		 */
		private String description;
		
		
		/**
		 * 查询的语句
		 */
		private String query;


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
		 * @return the query
		 */
		public String getQuery() {
			return query;
		}


		/**
		 * 
		 * @param query the query to set
		 */
		public void setQuery(String query) {
			this.query = query;
		}
		
		/**
		 * 
		 * @return
		 */
		public abstract QueryTemplate toQueryTemplate();
		
		/* (non-Javadoc)
		 * @see java.lang.Object#hashCode()
		 */
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((id == null) ? 0 : id.hashCode());
			return result;
		}

		/* (non-Javadoc)
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			Query other = (Query) obj;
			if (id == null) {
				if (other.id != null)
					return false;
			} else if (!id.equals(other.id))
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
						  .add("id", id)
						  .add("description", description)
						  .add("query", query)
						  .toString();
		}
	}
	
	/**
	 * 
	 * @since 1.0 2014年10月17日,下午1:38:02
	 * 
	 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
	 * @version  1.0
	 */
	@XStreamAlias("sql")
	public static class SqlQuery extends Query {

		/* (non-Javadoc)
		 * @see secfox.soc.melon.persistence.QueryTemplates.Query#toQueryTemplate()
		 */
		@Override
		public QueryTemplate toQueryTemplate() {
			return QueryTemplate.create(QueryType.SQL, this.getQuery());
		}
		
	}
	
	/**
	 * 
	 * @since 1.0 2014年10月17日,下午1:42:46
	 * 面向对象查询语言
	 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
	 * @version  1.0
	 */
	@XStreamAlias("java")
	public static class JqlQuery extends Query {
		
		/* (non-Javadoc)
		 * @see secfox.soc.melon.persistence.QueryTemplates.Query#toQueryTemplate()
		 */
		@Override
		public QueryTemplate toQueryTemplate() {
			return QueryTemplate.create(QueryType.JQL, this.getQuery());
		}
		
	}

}
