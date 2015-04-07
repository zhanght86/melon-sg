/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.security.domain;

import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 1.0 2014年9月27日,下午3:41:30
 * 密码更改
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class Password extends BaseDomain<Long> {

	private static final long serialVersionUID = -5561294646290035437L;
	
	//主键,与帐户对应的主键相同
	private Long id;
	
	//密码
	private String password;
	
	//确认密码
	private String confirmPassword;
	
	/**
	 * 
	 */
	public Password() {
		super();
	}

	/**
	 * @param id
	 */
	public Password(Long id) {
		super();
		this.id = id;
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
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
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
		Password other = (Password) obj;
		if (confirmPassword == null) {
			if (other.confirmPassword != null)
				return false;
		} else if (!confirmPassword.equals(other.confirmPassword))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
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
		result = prime * result
				+ ((confirmPassword == null) ? 0 : confirmPassword.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.BaseDomain#toString()
	 */
	@Override
	public String toString() {
		return "Password [id=" + id + ", password=" + password
				+ ", confirmPassword=" + confirmPassword + "]";
	}

}
