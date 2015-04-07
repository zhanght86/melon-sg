
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

import secfox.soc.melon.persistence.TreeDomain;

/**
 * @since 1.0 2014年9月22日,上午10:20:31
 * 安全对象类型 类
 * @author <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version 1.0
 */
@Entity
@Table(name = "T_ASSET_TYPE")
@NamedQueries({
	@NamedQuery(name="assetType.findAll", query="select a from AssetType a  order by a.order"),
	@NamedQuery(name="assetType.remove", query="delete  from AssetType a where a.path like :path"),
	@NamedQuery(name = "assetType.findByName", query = "select a from AssetType a where a.name = :name ")
})
public class AssetType extends TreeDomain<Long> {
	
	private static final long serialVersionUID = -5729556028997338426L;

	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_ASSET")
	private Long id;
	
	//编码
	@Column(name="TYPE_CODE", length=100, unique=true)
	private String code;

	//名称
	@Column(name="TYPE_NAME",length=100)
	private String name;

	//父节点
	@Column(name="TYPE_PARENTID")
	private Long parentId;
	
	//顺序
	@Column(name = "TYPE_ORDER")
	private int order = 0;
	
	//路径
	@Column(name="TYPE_PATH",length=50)
	private String path;
	
	//图片路径
	@Column(name="IMG_NAME", length=50)
	private String imgName="default.png";
	
	//备注
	@Column(name="TYPE_REMARKS", length=1000)
	private String remarks;
	
	/**
	 * 
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * 
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * 
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return the parentId
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * 
	 * @param parentId the parentId to set
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * 
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * 
	 * @param order the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}

	/**
	 * 
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * 
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

	/**
	 * 
	 * @return the imgName
	 */
	public String getImgName() {
		return imgName;
	}

	/**
	 * 
	 * @param imgName the imgName to set
	 */
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	/**
	 * 
	 * @return the remarks
	 */
	public String getRemarks() {
		return remarks;
	}

	/**
	 * 
	 * @param remarks the remarks to set
	 */
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "AssetType [id=" + id + ", code=" + code + ", name=" + name
				+ ", parentId=" + parentId + ", path=" + path + ", remarks="
				+ remarks + ", order=" + order + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + order;
		result = prime * result
				+ ((parentId == null) ? 0 : parentId.hashCode());
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
		AssetType other = (AssetType) obj;
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
		if (order != other.order)
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
	
	/**
	 * 
	 */
	public Object getParent() {
		if(this.isAsRoot()) {
			return "#";
		}
		return parentId == null ? "#" : parentId;
	}

}
