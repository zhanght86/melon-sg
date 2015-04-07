/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.knowledge.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import secfox.soc.melon.persistence.TreeDomain;

/**
 * @since 1.0 2014年10月28日,下午2:18:20
 * 知识库的分类
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name = "T_KNOW_TYPE")
@NamedQueries({
	@NamedQuery(name="knowledgeType.findAll", query="select a from KnowledgeType a  order by a.order"),
	@NamedQuery(name="knowledgeType.remove", query="delete from KnowledgeType a where a.path like :path"),
	@NamedQuery(name="knowledgeType.findCountByParentId", query="select a from KnowledgeType a where a.parentId =:parentId")
})
public class KnowledgeType extends TreeDomain<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -948780600665868705L;
	
	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_KN")
	private Long id;
	
	//名称
	@Column(name="KN_TITLE", length=200)
	private String title;
	
	//编码
	@Column(name="KN_CODE")
	private Long code;
	
	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	//组织机构路径
	@Column(name="O_PATH", length=200)
	private String path;
	
	//顺序
	@Column(name="O_ORDER")
	private int order;
	
	//父节点
	@Column(name="PARENT_ID")
	private Long parentId;
	
    //备注
    @Column(name="REMARKS",length=1000)
    private String remarks;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		KnowledgeType other = (KnowledgeType) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
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
	public String toString() {
		return "KnowledgeType [id=" + id + ", title=" + title + ", path="
				+ path + "]";
	}

	@Override
	public String getText() {
		return this.title;
	}

	@Override
	public Object getParent() {
		if(this.isAsRoot()) {
			return "#";
		}
		return parentId == null ? "#" : parentId;
	}

}
