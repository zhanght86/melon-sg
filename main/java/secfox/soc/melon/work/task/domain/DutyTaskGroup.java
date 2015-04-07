/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.work.task.domain;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import secfox.soc.melon.persistence.BaseDomain;
import secfox.soc.melon.security.domain.MenuResource;

/**
 * @since 1.0 2014年11月10日,下午2:27:38
 * 岗位职责
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name="T_WORK_TASK_GROUP")
@NamedQueries({
	@NamedQuery(name="taskGroup.findByRoleId", query="select taskGroup from DutyTaskGroup taskGroup where taskGroup.roleId = :roleId")

})
public class DutyTaskGroup extends BaseDomain<Long> {

	private static final long serialVersionUID = -1956349105202142810L;
	
	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;
	
	//角色ID,与角色相关联
	//每个角色对应一个或多个DutyTaskGroup
	@Column(name="T_ROLE_ID")
	private Long roleId;
	
	@Column(name="T_ROLE_NAME")
	private String roleName;
	
	//岗责名称,安全管理员日常运维任务
	@Column(name="T_NAME")
	private String name;
	
	//岗责说明
	@Column(name="T_REMARKS")
	private String remarks;
	
	@Column(name="T_ORDER")
	private int order;
	
	//参考资料,附件ID
	@Column(name="T_ADDITIONID")
	private String additionId;
	
	@ManyToMany(fetch = FetchType.LAZY, targetEntity=DutyTaskItem.class)
	@JoinTable(name = "T_WORK_CONN_GI", joinColumns = { @JoinColumn(name = "GROUP_ID") }, inverseJoinColumns = @JoinColumn(name = "ITEM_ID"))
	@JsonIgnore
	private List<DutyTaskItem> tasks;

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
	 * @return the roleId
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
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
	 * @return the additionId
	 */
	public String getAdditionId() {
		return additionId;
	}

	/**
	 * @param additionId the additionId to set
	 */
	public void setAdditionId(String additionId) {
		this.additionId = additionId;
	}

	/**
	 * @return the tasks
	 */
	public List<DutyTaskItem> getTasks() {
		return tasks;
	}

	/**
	 * @param tasks the tasks to set
	 */
	public void setTasks(List<DutyTaskItem> tasks) {
		this.tasks = tasks;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 * @author <a href="mailto:gaobg@legendsec.com">高保国</a>
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((additionId == null) ? 0 : additionId.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + order;
		result = prime * result + ((remarks == null) ? 0 : remarks.hashCode());
		result = prime * result + ((roleId == null) ? 0 : roleId.hashCode());
		result = prime * result
				+ ((roleName == null) ? 0 : roleName.hashCode());
		result = prime * result + ((tasks == null) ? 0 : tasks.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 * @author <a href="mailto:gaobg@legendsec.com">高保国</a>
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DutyTaskGroup other = (DutyTaskGroup) obj;
		if (additionId == null) {
			if (other.additionId != null)
				return false;
		} else if (!additionId.equals(other.additionId))
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
		if (order != other.order)
			return false;
		if (remarks == null) {
			if (other.remarks != null)
				return false;
		} else if (!remarks.equals(other.remarks))
			return false;
		if (roleId == null) {
			if (other.roleId != null)
				return false;
		} else if (!roleId.equals(other.roleId))
			return false;
		if (roleName == null) {
			if (other.roleName != null)
				return false;
		} else if (!roleName.equals(other.roleName))
			return false;
		if (tasks == null) {
			if (other.tasks != null)
				return false;
		} else if (!tasks.equals(other.tasks))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 * @author <a href="mailto:gaobg@legendsec.com">高保国</a>
	 */
	@Override
	public String toString() {
		return "DutyTaskGroup [id=" + id + ", roleId=" + roleId + ", roleName="
				+ roleName + ", name=" + name + ", remarks=" + remarks
				+ ", order=" + order + ", additionId=" + additionId + "]";
	}

	

}
