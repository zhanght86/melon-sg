/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.security.domain;

import java.util.Collection;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;

import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.base.json.Dictionary;
import secfox.soc.melon.persistence.BaseDomain;
import secfox.soc.melon.security.SecurityConstants;
import secfox.soc.melon.security.domain.listener.RoleLoadListener;
import secfox.soc.melon.security.domain.listener.RolePersistListener;
import secfox.soc.melon.security.domain.listener.RoleUpdateListener;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import com.google.common.collect.Sets;

/**
 * @since 1.0 2014年9月17日,下午8:13:59
 * 系统角色对象,也是用户的岗位,将角色与岗位合二为一
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name="T_SECURITY_ROLE")
@EntityListeners(value= {RolePersistListener.class, RoleUpdateListener.class, RoleLoadListener.class})
@NamedQueries({
	@NamedQuery(name="Role.findAll", query="select role from Role role order by role.order asc"),
	@NamedQuery(name="Role.findByCode", query="select role from Role role where role.code = :code")
})

public class Role extends BaseDomain<Long> implements GrantedAuthority {
	
	private static final long serialVersionUID = 5102655661218055974L;
	
	//主键
	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;
	
	//角色编码
	@Column(name="ROLE_CODE", length=20, unique=true)
	@NotBlank
	private String code;
	
	//角色名称
	@Column(name="ROLE_NAME", length=40)
	@NotBlank
	private String name;
	
	//角色适用的范围,总局,省局,地区
	@Transient
	private Integer[] level;
	
	//辅助记录角色适用的范围
	@Column(name="ROLE_LEVELS", length=10)
	@JsonIgnore
	private String levels;
	
	//角色顺序
	@Column(name="ROLE_ORDER")
	private int order = 0;
	
	//对应的账户
	@ManyToMany(targetEntity=Account.class,fetch=FetchType.LAZY, mappedBy="roles")
	@JsonIgnore
	private Collection<Account> accounts;
	
	//对应的资源
	@ManyToMany(fetch = FetchType.LAZY, targetEntity=MenuResource.class)
	@JoinTable(name = "T_SECURITY_CONN_RR", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = @JoinColumn(name = "RESOURCE_ID"))
	@JsonIgnore
	private Set<MenuResource> resources;
		
	//是否是互斥角色,互斥角色不能同时分配,支持三权分立
	@Column(name="ROLE_MUTEX")
	private boolean mutex = false;
	
	/*创建时间*/
	@Column(name="CREATE_TIME")
	@DateFormatter(DateTimeType.LONG_DATE)
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	//角色描述信息
	@Column(name="ROLE_REMARKS", length=1000)
	@Length(max=500)
	@JsonIgnore
	private String remarks;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.core.GrantedAuthority#getAuthority()
	 * 获取系统内置的验证编码
	 */
	@Override
	@Transient
	public String getAuthority() {
		if(StringUtils.startsWith(code, SecurityConstants.ROLE_PREFIX)) {
			return code;
		}
		return SecurityConstants.ROLE_PREFIX + code;
	}
	
	/**
	 * 
	 */
	public Role() {
		super();
	}

	/**
	 * @param code
	 */
	public Role(String code) {
		super();
		this.code = code;
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.Identifiable#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}
	
	/**
	 * 添加资源
	 * @param resource
	 */
	public void addResource(MenuResource resource) {
		if(resources == null) {
			resources = Sets.newHashSet();
		}
		resources.add(resource);
	}
	
	/**
	 * 删除对应的资源
	 * @param resource
	 */
	public void removeResource(MenuResource resource) {
		if(resources != null) {
			resources.remove(resource);
		}
	}
	
	/**
	 * 
	 * @return the resources
	 */
	public Set<MenuResource> getResources() {
		return resources;
	}

	/**
	 * 
	 * @param resources the resources to set
	 */
	public void setResources(Set<MenuResource> resources) {
		this.resources = resources;
	}

	/**
	 * 
	 * @return
	 */
	public String getLevels() {
		return levels;
	}
	
	/**
	 * 
	 * @param levels
	 */
	public void setLevels(String levels) {
		this.levels = levels;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.Identifiable#setId(java.io.Serializable)
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return
	 */
	@Dictionary("organLevel")
	public Integer[] getLevel() {
		return level;
	}
	
	/**
	 * 
	 * @param level
	 */
	public void setLevel(Integer[] level) {
		this.level = level;
	}

	/**
	 * 
	 * @return
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * 
	 * @param order
	 */
	public void setOrder(int order) {
		this.order = order;
	}

	/**
	 * 
	 * @return
	 */
	public Collection<Account> getAccounts() {
		return accounts;
	}
	
	/**
	 * 
	 * @param accounts
	 */
	public void setAccounts(Collection<Account> accounts) {
		this.accounts = accounts;
	}

	/**
	 * 
	 * @return
	 */
	@Dictionary("bool")
	public boolean isMutex() {
		return mutex;
	}
	
	/**
	 * 
	 * @param mutex
	 */
	public void setMutex(boolean mutex) {
		this.mutex = mutex;
	}

	/**
	 * 
	 * @return
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 
	 * @param createTime
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * 
	 * @return
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * 
	 * @param remarks
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Role other = (Role) obj;
		if (code == null) {
			if (other.code != null)
				return false;
		} else if (!code.equals(other.code))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (mutex != other.mutex)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + (mutex ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
					  .add("id", id)
					  .add("code", code)
					  .add("name", name)
					  .add("order", order)
					  .add("mutex", mutex)
					  .add("createTime", createTime)
					  .add("remarks", remarks)
					  .toString();
	}

}
