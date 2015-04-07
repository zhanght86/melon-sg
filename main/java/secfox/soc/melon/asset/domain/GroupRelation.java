/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 1.0 2014年10月20日,下午8:01:46
 * 安全域连接关系
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name="T_ASSET_CONN_GDI")
@NamedQueries({
	@NamedQuery(name="relation.findByDI", query="select a from GroupRelation a where a.type =:type and a.assetId =:assetId")
})
public class GroupRelation extends BaseDomain<Long> {
	
	private static final long serialVersionUID = -2306484250000579133L;

	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_ASSET")
	private Long id;
	
	@Column(name = "GROUP_ID")
	private Long groupId;
	
	@Column(name = "ASSET_ID")
	private Long assetId;
	
	@Column(name = "GROUP_PATH")
	private String path;

	//0:代表安全域与设备, 1:代表安全域与信息系统
	@Column(name = "RELATION_TYPE", length = 1)
	private int type;
	
	/**
	 * 保存设备与安全域关系
	 * @param deviceId 设备id
	 * @param groupId 安全域id
	 * @return
	 */
	public static GroupRelation createDeviceConnection(Long deviceId, Long groupId,String path) {
		GroupRelation conn = new GroupRelation();
		conn.setType(0);
		conn.setAssetId(deviceId);
		conn.setGroupId(groupId);
		conn.setPath(path);
		return conn;
	}
	
	/**
	 * 保存信息系统与安全域关系
	 * @param sysId 信息系统id
	 * @param groupId 安全域id
	 * @return
	 */
	public static GroupRelation createSysConnection(Long sysId, Long groupId,String path) {
		GroupRelation conn = createDeviceConnection(sysId, groupId, path);
		conn.setType(1);
		return conn;
	}
	
	
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * 
	 * @return the groupId
	 */
	public Long getGroupId() {
		return groupId;
	}

	/**
	 * 
	 * @param groupId the groupId to set
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	/**
	 * 
	 * @return the assetId
	 */
	public Long getAssetId() {
		return assetId;
	}

	/**
	 * 
	 * @param assetId the assetId to set
	 */
	public void setAssetId(Long assetId) {
		this.assetId = assetId;
	}

	/**
	 * 
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * 
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "GroupRelation [id=" + id + ", groupId=" + groupId
				+ ", assetId=" + assetId + ", type=" + type + "]";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assetId == null) ? 0 : assetId.hashCode());
		result = prime * result + ((groupId == null) ? 0 : groupId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + type;
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
		GroupRelation other = (GroupRelation) obj;
		if (assetId == null) {
			if (other.assetId != null)
				return false;
		} else if (!assetId.equals(other.assetId))
			return false;
		if (groupId == null) {
			if (other.groupId != null)
				return false;
		} else if (!groupId.equals(other.groupId))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (type != other.type)
			return false;
		return true;
	}



	

}
