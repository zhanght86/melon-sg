/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.organ.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import secfox.soc.melon.organ.OrganType;
import secfox.soc.melon.persistence.TreeDomain;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @since 1.0 2014年9月3日,下午5:21:42
 * 组织机构,负责行政区域\单位\部门的管理
 * 规范：行政区域下面可以挂单位、行政区域，单位下面只能挂部门
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name = "T_ORGAN_ORGAN")
@NamedQueries({
	@NamedQuery(name="organization.findAll", query="select organ from Organization organ"),
	@NamedQuery(name="organization.remove", query="delete from Organization where path like :path"),
	@NamedQuery(name="organization.findByName", query="select organ from Organization organ where organ.name = :name"),
	@NamedQuery(name="organization.count", query="select organ.id, organ.name, organ.principal, organ.prinTel, count(acc) as count "
											+"from Organization organ, Account acc where organ.id = acc.companyId and organ.type = 1 "
											+"group by organ.id, organ.name, organ.principal, organ.prinTel order by organ.order")
})
public class Organization extends TreeDomain<Long> {
	
	private static final long serialVersionUID = 6295995262905490215L;
	
	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;
	
	//组织机构名称
	@Column(name="O_NAME", length=100)
	@NotBlank
	private String name;
	
	//是否是叶子节点
	@Column(name="O_LEAF")
	private boolean leaf = true;
	
	//编码
	@Column(name="O_CODE", length=20)
	@NotBlank
	private String code;
	
	//组织机构路径
	@Column(name="O_PATH", length=200)
	private String path;
	
	//顺序
	@Column(name="O_ORDER")
	private int order;
	
	//0:总部,1:省(直辖市),2:地市
	@Column(name="O_LEVEL")
	private int level;
	
	//类型标示 0:行政区域,1:单位,2:部门
	@Column(name="O_TYPE")
	private int type;
	
	//单位类型标示 1：总局，2：国税，3：地税
	@Column(name="O_CATEGORY")
	private int category = 0;
	
	//地址
	@Column(name="O_ADDRESS", length=200)
	@Length(max=100)
	private String address;
	
	//父节点id
	@Column(name="PARENT_ID")
	private Long parentId;
	
	//备注
	@Column(name="ORGAN_REMARK", length=1000)
	@Length(max=500)
	private String remark;
	
	//节点深度
	@Column(name="O_DEPTH")
	private int depth;
	
	//联系人
	@Column(name="O_CONTACTER", length=100)
	private String contacter;
	
	//负责人
	@Column(name="O_PRINCIPAL", length=100)
	private String principal;
	
	//联系人电话
	@Column(name="O_CONTEL", length=100)
	private String conTel;
	
	//负责人电话
	@Column(name="O_PRINTEL", length=100)
	private String prinTel;
	
	//统计每个单位的人数
	@Column(name="O_COUNT")
	private int count = 0;
	
	@Transient
	private List<Organization> items;
	
	/**
	 * 默认的构造函数
	 */
	public Organization(){
		super();
	}
	
	public Organization(Long id, String text, Long parentId, int type){
		super();
		this.id = id;
		this.name = text;
		this.parentId = parentId;
		this.type = type;
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.commons.TreeDomain#getText()
	 */
	@Override
	public String getText() {
		return this.name;
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

	@Transient
    @JsonIgnore
    public boolean isArea() {
        return OrganType.isGroup(type);
    }
    
    @Transient
    @JsonIgnore
    public boolean isCompany() {
        return OrganType.isCompany(type);
    }
    
    @Transient
    @JsonIgnore
    public boolean isDepartMent() {
        return OrganType.isDepartment(type);
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
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the category
	 */
	public int getCategory() {
		return category;
	}

	/**
	 * @param category the category to set
	 */
	public void setCategory(int category) {
		this.category = category;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
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
	 * @return the depth
	 */
	public int getDepth() {
		return depth;
	}

	/**
	 * @param depth the depth to set
	 */
	public void setDepth(int depth) {
		this.depth = depth;
	}

	/**
	 * @return the contacter
	 */
	public String getContacter() {
		return contacter;
	}

	/**
	 * @param contacter the contacter to set
	 */
	public void setContacter(String contacter) {
		this.contacter = contacter;
	}

	/**
	 * @return the principal
	 */
	public String getPrincipal() {
		return principal;
	}

	/**
	 * @param principal the principal to set
	 */
	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	/**
	 * @return the conTel
	 */
	public String getConTel() {
		return conTel;
	}

	/**
	 * @param conTel the conTel to set
	 */
	public void setConTel(String conTel) {
		this.conTel = conTel;
	}

	/**
	 * @return the prinTel
	 */
	public String getPrinTel() {
		return prinTel;
	}

	/**
	 * @param prinTel the prinTel to set
	 */
	public void setPrinTel(String prinTel) {
		this.prinTel = prinTel;
	}

	/**
	 * @return the children
	 */
	public List<Organization> getItems() {
		return items;
	}

	/**
	 * @param children the children to set
	 */
	public void setItems(List<Organization> children) {
		this.items = children;
	}

	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * @param count the count to set
	 */
	public void setCount(int count) {
		this.count = count;
	}
	
	/**
	 * @return the leaf
	 */
	public boolean isLeaf() {
		return leaf;
	}

	/**
	 * @param leaf the leaf to set
	 */
	public void setLeaf(boolean leaf) {
		this.leaf = leaf;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Organization other = (Organization) obj;
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
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Organization [id=" + id + ", name=" + name + ", code=" + code
				+ ", path=" + path + ", order=" + order + ", level=" + level
				+ ", type=" + type + ", category=" + category + ", address="
				+ address + ", parentId=" + parentId + ", remark=" + remark
				+ ", depth=" + depth + ", contacter=" + contacter
				+ ", principal=" + principal + ", conTel=" + conTel
				+ ", prinTel=" + prinTel + ", count=" + count;
	}

}
