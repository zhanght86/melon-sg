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
import javax.persistence.Transient;

import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.base.json.Dictionary;
import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 1.0 2014年10月20日,下午2:18:46
 * 快速注解
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name="T_QUICK_NOTE")
@NamedQueries({
	//查自己批注
	@NamedQuery(name = "qnote.findNoteUsers", query = "select a from QuickNote a where a.type = :type and a.businessId = :businessId and a.creator.userId =:userId"),
	//查看资产批注列表
	@NamedQuery(name = "qnote.ListNotes", query = "select a from QuickNote a where a.type = :type and a.businessId = :businessId order by a.level desc"),
	//查看用户批注列表
	@NamedQuery(name = "qnote.findByUserId", query = "select a from QuickNote a where a.creator.userId =:userId order by a.level desc"),
	//查看用户批注设备列表
	@NamedQuery(name = "qnote.findNotedDevices", query = "select new QuickNote(a.businessId, a.createTime, a.remarks,b.name) from QuickNote a, Device b where a.businessId = b.id and a.creator.userId =:userId"),
	//查看用户批注信息系统列表
	@NamedQuery(name = "qnote.findNotedInfoSystems", query = "select new QuickNote(a.businessId, a.createTime, a.remarks,b.name) from QuickNote a, InfoSystem b where a.businessId = b.id and a.creator.userId =:userId")
})
public class QuickNote extends BaseDomain<Long> {
	
	
	public QuickNote() {
		super();
	}
	/**
	 * @param businessId
	 * @param createTime
	 * @param remarks
	 * @param name
	 */
	public QuickNote(Long businessId, Date createTime, String remarks,
			String name) {
		super();
		this.businessId = businessId;
		this.createTime = createTime;
		this.remarks = remarks;
		this.name = name;
	}

	private static final long serialVersionUID = -8822763147048907704L;

	@Id
	@Column(name = "PK")
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;
	
	//关注的类型,1:设备,2:信息系统,3:告警,
	@Column(name="QU_TYPE")
	private Long type;
	
	//重要性：一般,重要,紧急
	@Column(name="Q_LEVEL")
	private int level;
	
	@Column(name="BUSS_ID")
	private Long businessId;
	
	@Embedded
	private UserInfo creator;
	
	//创建时间
	@Column(name="CREATE_TIME")
	@Temporal(TemporalType.TIMESTAMP)
	private Date createTime;
	
	//标注,最多可填150字
	@Column(name="REMARKS", length=300)
	private String remarks;
	
	//设备或信息系统的name
	@Transient
	private String name;
	
	@Override
	public Long getId() {
		return id;
	}

	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getType() {
		return type;
	}
	
	public void setType(Long type) {
		this.type = type;
	}

	@Dictionary("qlevel")
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public UserInfo getCreator() {
		return creator;
	}

	public void setCreator(UserInfo creator) {
		this.creator = creator;
	}
	
	public String getRemarks() {
		return remarks;
	}
	
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
		QuickNote other = (QuickNote) obj;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((businessId == null) ? 0 : businessId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "QuickNote [id=" + id + ", type=" + type + ", level=" + level
				+ ", businessId=" + businessId + ", creator=" + creator
				+ ", createTime=" + createTime + ", remarks=" + remarks + "]";
	}


}
