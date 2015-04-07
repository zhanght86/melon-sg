/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import com.google.common.base.Objects;

/**
 * @since 1.0 2014年9月19日,下午1:42:29
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Embeddable
@Access(AccessType.FIELD)
public class UserInfo {
	
	//用户的员工编号：employeeId对应的ORGAN_ID
	@Column(name="USER_ID")
	private Long userId;
	
	//用户的员工姓名：employeeName
	@Column(name="USER_NAME", length=20)
	private String username;
	
	//用户的单位ID：Organization Id
	@Column(name="ORGAN_ID")
	private Long organId;
	
	//用户的单位名称：
	@Column(name="ORGAN_NAME", length=20)
	private String organName;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getOrganId() {
		return organId;
	}

	public void setOrganId(Long organId) {
		this.organId = organId;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}
	
	/**
	 * 
	 */
	public UserInfo() {
		super();
	}

	/**
	 * @param userId
	 * @param username
	 * @param organId
	 * @param organName
	 */
	public UserInfo(Long userId, String username, Long organId, String organName) {
		super();
		this.userId = userId;
		this.username = username;
		this.organId = organId;
		this.organName = organName;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((organId == null) ? 0 : organId.hashCode());
		result = prime * result
				+ ((organName == null) ? 0 : organName.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
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
		UserInfo other = (UserInfo) obj;
		if (organId == null) {
			if (other.organId != null)
				return false;
		} else if (!organId.equals(other.organId))
			return false;
		if (organName == null) {
			if (other.organName != null)
				return false;
		} else if (!organName.equals(other.organName))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
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
			      .add("userId", this.userId)
			      .add("username", this.username)
			      .add("organId", this.organId)
			      .add("organName", this.organName)
			      .toString();
	}
	
}
