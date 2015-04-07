/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.work.task.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import secfox.soc.melon.persistence.TreeDomain;

/**
 * @since 1.0 2014年11月6日,下午3:33:02
 * 任务
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name="T_WORK_TASK")
@NamedQueries({
	@NamedQuery(name="task.findChildren", query="select task from Task task where task.path like :path order by id desc")

})
public class Task extends TreeDomain<Long> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3580716319599364478L;
	
	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;
	
	//编码,工作编码
	@Column(name="T_CODE")
	private Long code;
	
	//工作名称,如等级保护,
	//1:等级保护,2:安全检查
	@Column(name="T_NAME")
	private String name;
	
	//任务地址,启动任务的地址
	@Column(name="T_URL")
	private String url;
	
	//重要性,1-5
	@Column(name="T_WEIGHT")
	private int weight;
	
	//相关附件
	@Column(name="T_ATTACHID")
	private String attachId;
	
	//工作说明
	@Column(name="T_DESC")
	private String desc;
	
	//顺序
	@Column(name="M_ORDER")
	private int order;
	
	//访问路径
	@Column(name="M_PATH", length=256)
	private String path;
	
	//父节点主键
	@Column(name="PARENT_ID")
	private Long parentId;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCode() {
		return code;
	}

	public void setCode(Long code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAttachId() {
		return attachId;
	}

	public void setAttachId(String attachId) {
		this.attachId = attachId;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
	
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the weight
	 */
	public int getWeight() {
		return weight;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((code == null) ? 0 : code.hashCode());
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
		Task other = (Task) obj;
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
		return true;
	}

	

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Task [id=" + id + ", code=" + code + ", name=" + name
				+ ", url=" + url + ", weight=" + weight + ", attachId="
				+ attachId + ", desc=" + desc + ", order=" + order + ", path="
				+ path + ", parentId=" + parentId + "]";
	}

	@Override
	public String getText() {
		return this.name;
	}
	
	public Object getParent() {
		if(this.isAsRoot()) {
			return "#";
		}
		return parentId == null ? "#" : parentId;
	}
	
}
