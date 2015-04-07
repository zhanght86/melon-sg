package secfox.soc.melon.security.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.persistence.BaseDomain;

@Entity
@Table(name="T_SECURITY_RECORD")
@NamedQueries({
	@NamedQuery(name="loginRecord.findByAcc", query="select record from LoginRecord record where record.account = :account")
})
public class LoginRecord extends BaseDomain<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_APP")
	public Long id;
	
	//账号名称
	@Column(name="ACCOUNT_NAME")
	public String account;
	
	@Column(name="SESSIONID")
	public String sessionId;
	
	//错误登录次数
	@Column(name="COUNT")
	public int count;
	
	@Column(name="UPDATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	@DateFormatter(DateTimeType.LONG_DATE)
	public Date updateTime;
	
	public LoginRecord() {
		super();
	}
	
	public LoginRecord(String account, int count, Date date) {
		super();
		this.account = account;
		this.count = count;
		this.updateTime = date;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getSessionId() {
		return sessionId;
	}

	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + count;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		LoginRecord other = (LoginRecord) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (count != other.count)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "LoginRecord [id=" + id + ", account=" + account + ", count="
				+ count + "]";
	}
	
}
