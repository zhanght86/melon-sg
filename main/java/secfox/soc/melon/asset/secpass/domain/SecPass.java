/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.asset.secpass.domain;

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
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import secfox.soc.melon.asset.secpass.encrypt.Encrypt;
import secfox.soc.melon.base.CopyTo;
import secfox.soc.melon.base.DateTimeType;
import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.base.annotation.DateFormatter;
import secfox.soc.melon.base.json.deserializer.ShortDateDeserializer;
import secfox.soc.melon.base.json.serializer.ShortDateSerializer;
import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 2014-11-17,下午3:38:04
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Entity
@Table(name="T_ASSET_SECPASS")
@NamedQueries({
	@NamedQuery(name="secpass.findAll", query="select secpass from SecPass secpass where secpass.state = 1 order by secpass.assetName"),
	@NamedQuery(name="secpass.findHistory", query="select secpass from SecPass secpass where secpass.mainId = :mainId and secpass.state != 1"),
	@NamedQuery(name="secpass.findDuePass", query="select secpass from SecPass secpass where secpass.dueDate = :dueDate and secpass.user.userId = :userId and secpass.state = 1"),
	@NamedQuery(name="secpass.getTask", query="select secpass.user.userId, count(secpass.user.userId) from SecPass secpass where secpass.dueDate = :dueDate and secpass.state = 1 group by secpass.user.userId")
})

public class SecPass extends BaseDomain<Long> implements CopyTo<SecPass> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;
	
	//关联最新信息id
	@Column(name="S_MAINID")
	private Long mainId;

	//设备名称
	@Column(name="S_ASSETNAME",length=100)
    private String assetName;
	
	//ip
	@Column(name="S_IP",length=100)
	private String ip;
	
	//账户信息
	@Column(name="S_ACCOUNT",length=50)
	private String account;
	
	//密码 加密
	@Column(name="S_PASS",length=128)
	private String passWord;
	
	@Transient
	private String desPassWord;
	
	//创建时间
	@Column(name="S_CREATE")
	@DateFormatter(DateTimeType.SHORT_DATE)
	@Temporal(TemporalType.DATE)
	@JsonDeserialize(using=ShortDateDeserializer.class)
	@JsonSerialize(using=ShortDateSerializer.class)
	private Date createDate;

	//口令更新时间
	@Column(name="S_DATE")
	@DateFormatter(DateTimeType.SHORT_DATE)
	@Temporal(TemporalType.DATE)
	@JsonDeserialize(using=ShortDateDeserializer.class)
	@JsonSerialize(using=ShortDateSerializer.class)
	private Date updateDate;
	
	//口令到期日期
	@Column(name="S_DUEDATE")
	@DateFormatter(DateTimeType.SHORT_DATE)
	@Temporal(TemporalType.DATE)
	@JsonDeserialize(using=ShortDateDeserializer.class)
	@JsonSerialize(using=ShortDateSerializer.class)
	private Date dueDate;
	
	//到期提醒天数
	@Column(name="S_ALERT")
	private int alert;
	
	//备注
	@Column(name="S_REMARK", length=256)
	private String remark;
	
	@Embedded
	private UserInfo user;
	
	//口令状态 0:废除；1:最新；2：历史
	@Column(name="S_STATE")
	private int state;
	

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * @return the mainId
	 */
	public Long getMainId() {
		return mainId;
	}

	/**
	 * @param mainId the mainId to set
	 */
	public void setMainId(Long mainId) {
		this.mainId = mainId;
	}

	/**
	 * @return the assetName
	 */
	public String getAssetName() {
		return assetName;
	}

	/**
	 * @param assetName the assetName to set
	 */
	public void setAssetName(String assetName) {
		this.assetName = assetName;
	}

	/**
	 * @return the ip
	 */
	public String getIp() {
		return ip;
	}

	/**
	 * @param ip the ip to set
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}

	/**
	 * @return the account
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * @return the passWord
	 */
	public String getPassWord() {
		return passWord;
	}
	
	/**
	 * @param passWord the passWord to set
	 */
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	/**
	 * 获取明文密码
	 * @return
	 */
	public String getDesPassWord() {
		Encrypt encrypt = new Encrypt();
		if(StringUtils.isNotBlank(passWord)) {
			return encrypt.getDesString(passWord);
		}
		return desPassWord;
	}

	/**
	 * @param desPassWord the desPassWord to set
	 */
	public void setDesPassWord(String desPassWord) {
		this.desPassWord = desPassWord;
	}

	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}

	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}

	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	/**
	 * @return the dueDate
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * @param dueDate the dueDate to set
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * @return the alert
	 */
	public int getAlert() {
		return alert;
	}

	/**
	 * @param alert the alert to set
	 */
	public void setAlert(int alert) {
		this.alert = alert;
	}

	/**
	 * @return the remark
	 */
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

	/**
	 * @return the user
	 */
	public UserInfo getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(UserInfo user) {
		this.user = user;
	}

	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((account == null) ? 0 : account.hashCode());
		result = prime * result + alert;
		result = prime * result
				+ ((assetName == null) ? 0 : assetName.hashCode());
		result = prime * result + ((dueDate == null) ? 0 : dueDate.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result
				+ ((passWord == null) ? 0 : passWord.hashCode());
		result = prime * result + state;
		result = prime * result
				+ ((updateDate == null) ? 0 : updateDate.hashCode());
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
		SecPass other = (SecPass) obj;
		if (account == null) {
			if (other.account != null)
				return false;
		} else if (!account.equals(other.account))
			return false;
		if (alert != other.alert)
			return false;
		if (assetName == null) {
			if (other.assetName != null)
				return false;
		} else if (!assetName.equals(other.assetName))
			return false;
		if (dueDate == null) {
			if (other.dueDate != null)
				return false;
		} else if (!dueDate.equals(other.dueDate))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (passWord == null) {
			if (other.passWord != null)
				return false;
		} else if (!passWord.equals(other.passWord))
			return false;
		if (state != other.state)
			return false;
		if (updateDate == null) {
			if (other.updateDate != null)
				return false;
		} else if (!updateDate.equals(other.updateDate))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SecPass [id=" + id + ", assetName=" + assetName + ", ip=" + ip
				+ ", account=" + account + ", passWord=" + passWord
				+ ", updateDate=" + updateDate + ", dueDate=" + dueDate
				+ ", alert=" + alert + ", state=" + state + "]";
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.base.CopyTo#copy()
	 */
	@Override
	public SecPass copy() {
		SecPass secPass = new SecPass();
		secPass.setAccount(account);
		secPass.setAlert(alert);
		secPass.setAssetName(assetName);
		secPass.setDueDate(dueDate);
		secPass.setIp(ip);
		secPass.setRemark(remark);
		secPass.setPassWord(passWord);
		secPass.setUser(user);
		secPass.setUpdateDate(updateDate);
		secPass.setCreateDate(createDate);
		return secPass;
	}
}
