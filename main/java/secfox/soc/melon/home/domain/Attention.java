/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.home.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.persistence.BaseDomain;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @since 1.0 2014年10月21日,下午3:20:22
 * 我的关注
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name="T_HOME_ATTENT")
@NamedQueries({
	//查有多少人关注
	@NamedQuery(name = "attent.findAttentUsers", query = "select a from Attention a where a.type = :type and a.businessId = :businessId"),
	//查当前用户是否关注
	@NamedQuery(name = "attent.findAttentOwern", query = "select a from Attention a where a.type = :type and a.businessId = :businessId and a.user.userId = :userId "),
	//分类查询改用户所有关注的内容
	@NamedQuery(name = "attent.findMyAtten", query = "select a from Attention a where a.user.userId = :userId order by a.type desc")
})
public class Attention extends BaseDomain<Long> {
	
	private static final long serialVersionUID = 4192806310172215390L;

	@Id
	@Column(name = "PK")
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;
	
	//关注的类型,1:设备,2:信息系统,3:告警,
	@Column(name="AT_TYPE")
	private Long type;
	
	//业务主键
	@Column(name="BUSI_ID")
	private Long businessId;
	
	@Embedded
	private UserInfo user;
	
	//创建时间
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CREATE_TIME")
	@JsonIgnore
	public Date createTime;

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.Identifiable#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.Identifiable#setId(java.io.Serializable)
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return the type
	 */
	public Long getType() {
		return type;
	}

	/**
	 * 
	 * @param type the type to set
	 */
	public void setType(Long type) {
		this.type = type;
	}

	/**
	 * 
	 * @return the businessId
	 */
	public Long getBusinessId() {
		return businessId;
	}

	/**
	 * 
	 * @param businessId the businessId to set
	 */
	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	

	public UserInfo getUser() {
		return user;
	}

	public void setUser(UserInfo user) {
		this.user = user;
	}

	/**
	 * 
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	/**
	 * 
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((businessId == null) ? 0 : businessId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		Attention other = (Attention) obj;
		if (businessId == null) {
			if (other.businessId != null)
				return false;
		} else if (!businessId.equals(other.businessId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Attention [id=" + id + ", type=" + type + ", businessId="
				+ businessId + ", user=" + user + ", createTime=" + createTime
				+ "]";
	}

}
