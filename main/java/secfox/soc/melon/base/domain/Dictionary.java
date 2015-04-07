/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import secfox.soc.melon.persistence.TreeDomain;

/**
 * @since 1.0 2014年10月9日,下午12:30:07
 * 数据字典的数据库实现
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name="T_BASE_DICTIONARY")
@NamedQueries({
	@NamedQuery(name="dictionary.remove", query="delete from Dictionary where path like :path"),
	@NamedQuery(name="dictionary.findDict", query="select dictionary from Dictionary dictionary where dictionary.parentId = :parentId")
})
public class Dictionary extends TreeDomain<Long> {
	
	private static final long serialVersionUID = -1391330299624190612L;
	
	@Id@Column(name = "PK")
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;
	
	//字典编码：用于统一字典规范，一般用整数、字母表示
	@Column(name="D_VALUE", length=256)
	private String value;
	
	//字典标签：用于描述字典值得含义
	@Column(name="D_LABLE",length=256)
	private String label;
	
	//顺序
	@Column(name="D_ORDER")
	private int order = 0;
	
	//附加值
	@Column(name="D_ADD", length=40)
	private String addition;
	
	//描述信息
	@Column(name="D_REMARKS", length=400)
	private String remarks;
	
	//父节点主键
	@Column(name="PARENT_ID")
	private Long parentId;
		
	@Column(name="D_PATH", length=200)
	private String path;
	

	@Override
	public String getText() {
		return this.label;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.commons.TreeDomain#getParent()
	 */
	@Override
	public Object getParent() {
		if(this.isAsRoot()) {
			return "#";
		}
		return parentId == null ? "#" : parentId;
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
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
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
	 * @return the addition
	 */
	public String getAddition() {
		return addition;
	}

	/**
	 * @param addition the addition to set
	 */
	public void setAddition(String addition) {
		this.addition = addition;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
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
		Dictionary other = (Dictionary) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Dictionary [id=" + id + ", value=" + value + ", label=" + label
				+ ", order=" + order + ", addition=" + addition + ", remarks=" + remarks
				+ "]";
	}
	
}
