/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.security.domain;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.persistence.BaseDomain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;

/**
 * @since 1.0 2014年9月22日,上午11:40:41
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name="T_SECURITY_ACCOUNT")
@NamedQueries({
	//查询所有单位下所有用户按单位和用户顺序排序
	@NamedQuery(name="account.findAllUsers",
			query="select user from Account user, Organization organ where user.companyId = organ.id order by organ.id, user.departId"),
	//按照用户账号查找用户用于判断唯一性	
	@NamedQuery(name="account.loadUserByUsername",
			query="select user from Account user where user.username = :username"),
	//按照用户姓名查找用户用于判断唯一性	
	@NamedQuery(name="account.findByName",
			query="select user from Account user where user.name = :name"),			
	//查找指定单位下的所有负责人
	@NamedQuery(name="account.accountByOrgan",
	query="select user from Account user where user.companyId = :organId order by user.order")
})
public class Account extends BaseDomain<Long> implements UserDetails{

	private static final long serialVersionUID = -8132664238306124175L;
	
	//主键
	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;
	
	/*帐号*/
	@Column(name="USER_NAME", length=20, unique=true)
	@NotBlank
	private String username;
	
	/**
	 * 姓名
	 */
	@Column(name="A_NAME", length=100)
	@Length(max=50)
	private String name;
	
	
	//性别
	@Column(name="A_SEX")
	private int sex=0;
	
	//生日
	@Column(name="A_BIRTH")
	@Temporal(TemporalType.DATE)
	@DateFormatter(DateTimeType.SHORT_DATE)
	private Date birthday;
	
	//员工编号
    @Column(name="A_CODE", length=50)
    private String code;
    
    //顺序
    @Column(name="A_ORDER")
    private int order;
    
 	//Email
    @Column(name="EMAIL",length=50)
	private String email;
    
	//电话
    @Column(name="TEL",length=50)
	private String tel;
    
	//手机
    @Column(name="MOBILE",length=50)
	private String mobile;
    
    //备注
    @Column(name="REMARKS",length=1000)
    @Length(max=500)
	private String remarks;
    
    //所属单位
    @Column(name="COM_ID")
    private Long companyId;
    
    //所属单位
    @Column(name="COM_NAME", length=100)
    private String companyName;
    
    //所属部门
    @Column(name="DEPART_ID")
    private Long departId;
    
    //所属部门
    @Column(name="DEPART_NAME", length=100)
    private String departName;
	
    //职务
    @Column(name="A_DUTY", length=100)
    private String duty;
    
    //密码
	@Column(name="A_PASS", length=128)
	private String password;
	
	//验证口令密码
	@Column(name="A_SECPASS", length=128)
	private String secPassWord = BaseConstants.DEFAULTSECPASS;
	
	//确认密码
	@Transient
	private String confirmPassword;
	
	//修改密码时间
	@Column(name="RESET_TIME")
	@Temporal(TemporalType.DATE)
	@DateFormatter(DateTimeType.SHORT_DATE)
	private Date updatePassTime;
	
	//临时帐户
	@Column(name="ACCOUNT_TEMP")
	private boolean temporary = false;
	
	//帐户过期时间,只有临时账户才需要设置帐户过期时间
	@Column(name="EXPIRED_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	@DateFormatter(DateTimeType.LONG_DATE)
	private Date expiredTime;
	
	//连续登陆失败的次数,超过一定的次数则锁定
	@Column(name="LOGIN_FAIL")
	private int loginFails = 0;
	
	/*创建时间*/
	@Column(name="CREATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	@DateFormatter(DateTimeType.LONG_DATE)
	private Date createTime;
	
	/* 帐户是否已激活 */
	@Column(name="ACCOUNT_ENABLED")
	private boolean enabled = false;
	
	//首次登陆或被管理员重置
	@Column(name="ACCOUNT_ACTIVED")
	private boolean active = false;

	/* 帐户是否已过期 */
	@Column(name="ACCOUNT_EXPIRED")
	private boolean accountExpired = false;

	/* 帐户是否已锁定 */
	@Column(name="ACCOUNT_LOCKED")
	private boolean accountLocked = false;

	/* 密码是否已过期 */
	@Column(name="CREDENTIALS_EXPIRED")
	private boolean credentialsExpired = false;
	
	/* 帐户所拥有的角色 */
	@ManyToMany(fetch = FetchType.EAGER, targetEntity=Role.class)
	@JoinTable(name = "T_SECURITY_CONN_AR", joinColumns = { @JoinColumn(name = "ACCOUNT_ID") }, inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	@OrderBy("order asc")
	@Fetch(FetchMode.SELECT)
	@BatchSize(size=15)
	@JsonIgnore
	private List<Role> roles;
	
	//版本,用于控制多线程访问
	@Version
	private int version;
	
	//用户该拥有的菜单
	@JsonIgnore
	@Transient
	private List<MenuResource> menus;
	
	/**
	 * @return the menus
	 */
	public List<MenuResource> getMenus() {
		return menus;
	}

	/**
	 * @param menus the menus to set
	 */
	public void setMenus(List<MenuResource> menus) {
		this.menus = menus;
	}

	/**
	 * 添加角色
	 * @param role
	 */
	public void addRole(Role role) {
		if(roles == null) {
			roles = Lists.newArrayList();
		}
		roles.add(role);
	}
	
	/**
	 * 获取用户信息
	 * @return
	 */
	@JsonIgnore
	public UserInfo getUserInfo() {
		return new UserInfo(id, name, companyId, companyName);
	}
	
	/**
	 * 删除角色
	 * @param role
	 */
	public void removeRole(Role role) {
		if(roles != null) {
			roles.remove(role);
		}
	}
	
	/**
	 * 用于导出
	 * @return
	 */
	@JsonIgnore
	public String getRoleNames() {
        String roleNames = "";
		for(int i=0;i<roles.size();i++){
			roleNames += roles.get(i).getName();
		}
		return roleNames;
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.Identifiable#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.Identifiable#setId(java.io.Serializable)
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getText() {
		return this.name;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the duty
	 */
	public String getDuty() {
		return duty;
	}

	/**
	 * @param duty the duty to set
	 */
	public void setDuty(String duty) {
		this.duty = duty;
	}

	
	
	/**
	 * @return the sex
	 */
	public int getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(int sex) {
		this.sex = sex;
	}

	/**
	 * @return the birthday
	 */
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the updatePassTime
	 */
	public Date getUpdatePassTime() {
		return updatePassTime;
	}

	/**
	 * @param updatePassTime the updatePassTime to set
	 */
	public void setUpdatePassTime(Date updatePassTime) {
		this.updatePassTime = updatePassTime;
	}

	/**
	 * @return the temporary
	 */
	public boolean isTemporary() {
		return temporary;
	}

	/**
	 * @param temporary the temporary to set
	 */
	public void setTemporary(boolean temporary) {
		this.temporary = temporary;
	}

	/**
	 * @return the expiredTime
	 */
	public Date getExpiredTime() {
		return expiredTime;
	}

	/**
	 * @param expiredTime the expiredTime to set
	 */
	public void setExpiredTime(Date expiredTime) {
		this.expiredTime = expiredTime;
	}

	/**
	 * @return the loginFails
	 */
	public int getLoginFails() {
		return loginFails;
	}

	/**
	 * @param loginFails the loginFails to set
	 */
	public void setLoginFails(int loginFails) {
		this.loginFails = loginFails;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the tel
	 */
	public String getTel() {
		return tel;
	}

	/**
	 * @param tel the tel to set
	 */
	public void setTel(String tel) {
		this.tel = tel;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	/**
	 * @return the companyId
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}

	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	/**
	 * @return the departId
	 */
	public Long getDepartId() {
		return departId;
	}

	/**
	 * @param departId the departId to set
	 */
	public void setDepartId(Long departId) {
		this.departId = departId;
	}

	/**
	 * @return the departName
	 */
	public String getDepartName() {
		return departName;
	}

	/**
	 * @param departName the departName to set
	 */
	public void setDepartName(String departName) {
		this.departName = departName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the secPassWord
	 */
	public String getSecPassWord() {
		return secPassWord;
	}

	/**
	 * @param secPassWord the secPassWord to set
	 */
	public void setSecPassWord(String secPassWord) {
		this.secPassWord = secPassWord;
	}

	/**
	 * @return the confirmPassword
	 */
	public String getConfirmPassword() {
		return confirmPassword;
	}

	/**
	 * @param confirmPassword the confirmPassword to set
	 */
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the accountExpired
	 */
	public boolean isAccountExpired() {
		return accountExpired;
	}

	/**
	 * @param accountExpired the accountExpired to set
	 */
	public void setAccountExpired(boolean accountExpired) {
		this.accountExpired = accountExpired;
	}

	/**
	 * @return the accountLocked
	 */
	public boolean isAccountLocked() {
		return accountLocked;
	}

	/**
	 * @param accountLocked the accountLocked to set
	 */
	public void setAccountLocked(boolean accountLocked) {
		this.accountLocked = accountLocked;
	}

	/**
	 * @return the credentialsExpired
	 */
	public boolean isCredentialsExpired() {
		return credentialsExpired;
	}

	/**
	 * @param credentialsExpired the credentialsExpired to set
	 */
	public void setCredentialsExpired(boolean credentialsExpired) {
		this.credentialsExpired = credentialsExpired;
	}

	/**
	 * @return the roles
	 */
	public List<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	/**
	 * @return the version
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	 */
	@Override
	@Transient
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roles;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
	 */
	@Override
	@Transient
	public boolean isAccountNonExpired() {
		return !this.accountExpired;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
	 */
	@Override
	@Transient
	public boolean isAccountNonLocked() {
		return !this.accountLocked;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	@Override
	@Transient
	public boolean isCredentialsNonExpired() {
		return !this.credentialsExpired;
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.BaseDomain#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (version != other.version)
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.BaseDomain#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		result = prime * result + version;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.BaseDomain#toString()
	 */
	@Override
	public String toString() {
		return Objects.toStringHelper(this)
				      .add("id", id)
				      .add("username", username)
				      .add("companyName", companyName)
				      .add("departName", departName)
				      .add("version", version)
				      .add("name", name)
				      .add("roles", roles)
					  .toString();
	}

}
