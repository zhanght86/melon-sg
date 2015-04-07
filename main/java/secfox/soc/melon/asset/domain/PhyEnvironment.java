/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.asset.domain;

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
import secfox.soc.melon.persistence.TreeDomain;

/**
 * 物理环境
 * @since 2014-4-17,下午6:20:51
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Entity
@Table(name="T_ASSET_PHYENVIRONMENT") 
@NamedQueries({
	@NamedQuery(name="phyEnvironment.findAll", query="select a from PhyEnvironment a  order by a.order"),
	@NamedQuery(name="phyEnvironment.remove", query="delete from PhyEnvironment a where a.path like :path"),
	@NamedQuery(name="phyEnvironment.findByTypePath", query = "select a from PhyEnvironment a where a.typePath like :path or a.typeId = :typeId"),
})
public class PhyEnvironment extends TreeDomain<Long> {


	private static final long serialVersionUID = -5182888019612368418L;

	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_ASSET")
	private Long id;
	
	//编码
	@Column(name="CODE", length=20)
	private String code;
	
	//名称
	@Column(name="NAME", length=100)
	private String name;
	
	//父节点
	@Column(name="TYPE_PARENTID")
	private Long parentId;
	
	//顺序
	@Column(name="E_ORDER")
	private int order = 0;
	
	@Column(name="PATH")
	private String path;
	
	//类型id
	@Column(name="TYPE_ID")
	private Long typeId;
//	类型名称
	@Column(name="TYPE_NAME")
	private String typeName;
//	类型路径
	@Column(name="TYPE_PATH")
	private String typePath;
	
		
	//修改时间
	@Column(name = "CREATETIME")
	@Temporal(TemporalType.DATE)
	private Date createTime;
	
	//创建人
	@Embedded
	private UserInfo userInfo;

	//备注
	@Column(name="TYPE_REMARKS", length=1000)
	private String remarks;
	
	
	
	/**
	 * @return the createTime
	 */
	public Date getCreateTime() {
		return createTime;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

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
	 * @return the parentId
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
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
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}



	/**
	 * @return the typeId
	 */
	public Long getTypeId() {
		return typeId;
	}

	/**
	 * @param typeId the typeId to set
	 */
	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}

	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @return the typePath
	 */
	public String getTypePath() {
		return typePath;
	}

	/**
	 * @param typePath the typePath to set
	 */
	public void setTypePath(String typePath) {
		this.typePath = typePath;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PhyEnvironment [id=" + id + ", code=" + code + ", name=" + name
				+ ", parentId=" + parentId + ", order=" + order + ", path="
				+ path + ", remarks=" + remarks + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result
				+ ((parentId == null) ? 0 : parentId.hashCode());
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
		PhyEnvironment other = (PhyEnvironment) obj;
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
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		return true;
	}

	@Override
	public String getText() {
		return this.name;
	}

	@Override
	public Object getParent() {
		if(this.isAsRoot()) {
			return "#";
		}
		return parentId == null ? "#" : parentId;
	}
	


}
