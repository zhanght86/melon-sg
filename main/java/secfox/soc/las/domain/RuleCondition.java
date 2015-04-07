/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.las.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.persistence.TreeDomain;

/**
 * @since 2015-2-2,下午3:11:51
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Entity
@Table(name = "T_RULE_CONDITION")
@NamedQueries({
	@NamedQuery(name="condition.remove", query="delete from RuleCondition where path like :path")
}
)
public class RuleCondition extends TreeDomain<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9125716505416362929L;

	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;
	
	//规则id 只有事件需要关联
	@Column(name="C_RULEID")
	private Long ruleId;
	
	//条件名称
	@Column(name="C_NAME", length=100)
	private String name;
	
	//规则类型
	@Column(name="C_RULETYPE")
	private int ruleType;
	
	//条件类型 0：条件；1：逻辑运算；2：条件属性
	@Column(name="C_CONDTYPE")
	private int conditionType;
	
	//逻辑操作符
	@Column(name="C_LOGICALOP", length=20)
	private String logicalOper;
	
	//字段
	@Column(name="C_FIELD", length=50)
	private String field;
	
	//操作符
	@Column(name="C_OPERATOR", length=20)
	private String operator;
	
	//值
	@Column(name="C_VALUE")
	private String value;
	
	//父节点
	@Column(name="C_PARENTID")
	private Long parentId;
	
	//节点路径
	@Column(name="C_PATH", length=200)
	private String path;
	
	//节点内容
	@Transient
	private String content;
	
	//备注
	@Column(name="C_REMARK", length=500)
	private String remark;

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
	 * @return the ruleId
	 */
	public Long getRuleId() {
		return ruleId;
	}

	/**
	 * @param ruleId the ruleId to set
	 */
	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
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
	 * @return the ruleType
	 */
	public int getRuleType() {
		return ruleType;
	}

	/**
	 * @param ruleType the ruleType to set
	 */
	public void setRuleType(int ruleType) {
		this.ruleType = ruleType;
	}

	/**
	 * @return the conditionType
	 */
	public int getConditionType() {
		return conditionType;
	}

	/**
	 * @param conditionType the conditionType to set
	 */
	public void setConditionType(int conditionType) {
		this.conditionType = conditionType;
	}

	/**
	 * @return the logicalOper
	 */
	public String getLogicalOper() {
		return logicalOper;
	}

	/**
	 * @param logicalOper the logicalOper to set
	 */
	public void setLogicalOper(String logicalOper) {
		this.logicalOper = logicalOper;
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field the field to set
	 */
	public void setField(String field) {
		this.field = field;
	}

	/**
	 * @return the operator
	 */
	public String getOperator() {
		return operator;
	}

	/**
	 * @param operator the operator to set
	 */
	public void setOperator(String operator) {
		this.operator = operator;
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
	 * @return the content
	 */
	public String getContent() {
		//获取条件节点内容信息
		if(conditionType == 2) {
			return field+BaseConstants.SPACING_STRING+operator+BaseConstants.SPACING_STRING+value;
		}
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
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

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.TreeDomain#getText()
	 */
	@Override
	public String getText() {
		return this.name;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.TreeDomain#getParent()
	 */
	@Override
	public Object getParent() {
		if(this.isAsRoot()) {
			return "#";
		}
		return parentId == null ? "#" : parentId;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + conditionType;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((parentId == null) ? 0 : parentId.hashCode());
		result = prime * result + ruleType;
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
		RuleCondition other = (RuleCondition) obj;
		if (conditionType != other.conditionType)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (parentId == null) {
			if (other.parentId != null)
				return false;
		} else if (!parentId.equals(other.parentId))
			return false;
		if (ruleType != other.ruleType)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RuleCondition [id=" + id + ", name=" + name + ", ruleType="
				+ ruleType + ", conditionType=" + conditionType + ", parentId="
				+ parentId + "]";
	}

	
}
