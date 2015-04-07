/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.domain;

import java.util.List;

import javax.persistence.Cacheable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import secfox.soc.melon.base.json.Dictionary;
import secfox.soc.melon.persistence.BaseDomain;
import secfox.soc.melon.system.FieldType;
import secfox.soc.melon.system.JoinType;
import secfox.soc.melon.system.MRuleType;
import secfox.soc.melon.system.util.FieldTypeDeserializer;
import secfox.soc.melon.system.util.JoinTypeJsonDeserializer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

/**
 * @since 1.0 2013-3-20,下午7:56:50 规则
 * @author <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version 1.0
 */
@Entity
@Table(name = "T_MRULE")
@Cacheable
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MRule extends BaseDomain<Long> {

	private static final long serialVersionUID = 7351220449555803011L;

	@Id
	@Column(name = "PK")
	@GeneratedValue(generator = "GEN_SEQ_APP")
	private Long id;

	// 节点类型
	@Column(name = "R_TYPE")
	// @JsonDeserialize(using = MRuleTypeDeserializer.class)
	private MRuleType type = MRuleType.CONDITION;

	// 是否已启用
	@Column(name = "R_ENABLED")
	private boolean enabled;

	// 执行动作
	@Column(name = "R_ACTION")
	private int action;

	// 字段名
	@Column(name = "R_FIELD")
	private String field;

	// 是否阻断（当前规则匹配后，是否阻断接下来的规则匹配）
	@Column(name = "R_LAST")
	private boolean last = true;

	// 逻辑符类型
	@Column(name = "JOIN_TYPE")
	@JsonDeserialize(using = JoinTypeJsonDeserializer.class)
	private JoinType joinType = JoinType.AND;

	// 运算符
	@Column(name = "R_OPERATOR")
	private String operator;

	// 需要过滤的实体类
	@Column(name = "FILTER_ENTITY")
	private String filterEntity;

	// 字段匹配值
	@Column(name = "R_VALUE")
	private String value;

	// 字段描述信息
	@Column(name = "R_DESC", length = 100)
	private String desc;

	// 字段类型,默认String类型
	@Column(name = "FIELD_TYPE")
	@Enumerated(EnumType.ORDINAL)
	@JsonDeserialize(using = FieldTypeDeserializer.class)
	private FieldType fieldType = FieldType.STRING;//

	// 父节点
	@ManyToOne(fetch = FetchType.LAZY, optional = true, targetEntity = MRule.class)
	@JoinColumn(name = "PARENT_ID", nullable = true, unique = false)
	@JsonIgnore
	private MRule parent;

	// 规则的重要性，能显著地优化性能，结合last一起使用，能取得更好的效果
	@Column(name = "R_ORDER")
	private int order;

	// 子节点
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "parent", targetEntity = MRule.class, cascade = CascadeType.ALL)
	@OrderBy("order asc")
	private List<MRule> children;

	// 临时变量，存储规则表达式
	@Column(name = "R_EXP")
	private String expression;

	/**
	 * @return the expression
	 */
	public String getExpression() {
		return expression;
	}

	/**
	 * @param expression
	 *            the expression to set
	 */
	public void setExpression(String expression) {
		this.expression = expression;
	}

	// 字段名
	@Column(name = "R_DEAL_ORGANID")
	private String dealOrganId;

	/**
	 * @return the dealOrganId
	 */
	public String getDealOrganId() {
		return dealOrganId;
	}

	/**
	 * @param dealOrganId
	 *            the dealOrganId to set
	 */
	public void setDealOrganId(String dealOrganId) {
		this.dealOrganId = dealOrganId;
	}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}

	/**
	 * @return the filterEntity
	 */
	public String getFilterEntity() {
		return filterEntity;
	}

	/**
	 * @param filterEntity
	 *            the filterEntity to set
	 */
	public void setFilterEntity(String filterEntity) {
		this.filterEntity = filterEntity;
	}

	public StringBuilder buildTerm() {
		StringBuilder builder = new StringBuilder();
		builder.append(this.expression);
/*		
		if (type == MRuleType.CONDITION) {
			String value = this.value;
			// 判断节点类型
			switch (fieldType) {
			case STRING:
				value = "'" + value + "'";
				break;
			case DATETIME:
				value = "DTUtils.format('" + value + "')";
				break;
			default:
				break;
			}
			builder.append(this.field + " " + this.operator + " " + value);
		} else {
			// 添加字节点
			if (children != null && children.size() > 0) {
				int size = children.size();
				String oper = " " + this.joinType.getOper() + " ";
				// 添加括号
				builder.append("(");
				for (int i = 0; i < size; i++) {
					MRule child = children.get(i);
					builder.append(child.buildTerm());
					if (i < size - 1) {
						builder.append(oper);
					}
				}

				builder.append(")");
			}
		}
*/		
		return builder;
	}

	/**
	 * @return the enabled
	 */
	@Dictionary("bool")
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * @param enabled
	 *            the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * @return the last
	 */
	@Dictionary("bool")
	public boolean isLast() {
		return last;
	}

	/**
	 * @param last
	 *            the last to set
	 */
	public void setLast(boolean last) {
		this.last = last;
	}

	/**
	 * @return the joinType
	 */
	public JoinType getJoinType() {
		return joinType;
	}

	/**
	 * @param joinType
	 *            the joinType to set
	 */
	public void setJoinType(JoinType joinType) {
		this.joinType = joinType;
	}

	/**
	 * @return the fieldType
	 */
	public FieldType getFieldType() {
		return fieldType;
	}

	/**
	 * @param fieldType
	 *            the fieldType to set
	 */
	public void setFieldType(FieldType fieldType) {
		this.fieldType = fieldType;
	}

	/**
	 * @return the order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * @param order
	 *            the order to set
	 */
	public void setOrder(int order) {
		this.order = order;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the type
	 */
	public MRuleType getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(MRuleType type) {
		this.type = type;
	}

	/**
	 * @return the field
	 */
	public String getField() {
		return field;
	}

	/**
	 * @param field
	 *            the field to set
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
	 * @param operator
	 *            the operator to set
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
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the desc
	 */
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc
	 *            the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}

	/**
	 * @return the parent
	 */
	public MRule getParent() {
		return parent;
	}

	/**
	 * @param parent
	 *            the parent to set
	 */
	public void setParent(MRule parent) {
		this.parent = parent;
	}

	/**
	 * @return the children
	 */
	public List<MRule> getChildren() {
		return children;
	}

	/**
	 * @param children
	 *            the children to set
	 */
	public void setChildren(List<MRule> children) {
		this.children = children;
	}



	/**
	 * 决定
	 * <p>
	 * Title: getName
	 * </p>
	 * <p>
	 * Description: TODO
	 * </p>
	 * <p>
	 * &emsp;(这里用一句话描述这个方法的作用)
	 * </p>
	 * <p>
	 * &emsp;适用条件：TODO(可选)
	 * </p>
	 * <p>
	 * &emsp;执行流程：TODO(可选)
	 * </p>
	 * <p>
	 * &emsp;使用方法：TODO(可选)
	 * </p>
	 * <p>
	 * &emsp;注意事项：TODO(可选)
	 * </p>
	 * 
	 * @return
	 */
	public String getName() {
		if (this.type == MRuleType.JOIN) {
			return this.joinType.getOper();
		} else if (this.type == MRuleType.CONDITION) {
			// return this.field +this.operator + this.value;
			return this.desc;
		} else if (this.type == MRuleType.RULE) {
			return this.desc;
		}
		return "";
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + action;
		result = prime * result
				+ ((dealOrganId == null) ? 0 : dealOrganId.hashCode());
		result = prime * result + ((desc == null) ? 0 : desc.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result
				+ ((expression == null) ? 0 : expression.hashCode());
		result = prime * result + ((field == null) ? 0 : field.hashCode());
		result = prime * result
				+ ((fieldType == null) ? 0 : fieldType.hashCode());
		result = prime * result
				+ ((filterEntity == null) ? 0 : filterEntity.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((joinType == null) ? 0 : joinType.hashCode());
		result = prime * result + (last ? 1231 : 1237);
		result = prime * result
				+ ((operator == null) ? 0 : operator.hashCode());
		result = prime * result + order;
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		MRule other = (MRule) obj;
		if (action != other.action)
			return false;
		if (dealOrganId == null) {
			if (other.dealOrganId != null)
				return false;
		} else if (!dealOrganId.equals(other.dealOrganId))
			return false;
		if (desc == null) {
			if (other.desc != null)
				return false;
		} else if (!desc.equals(other.desc))
			return false;
		if (enabled != other.enabled)
			return false;
		if (expression == null) {
			if (other.expression != null)
				return false;
		} else if (!expression.equals(other.expression))
			return false;
		if (field == null) {
			if (other.field != null)
				return false;
		} else if (!field.equals(other.field))
			return false;
		if (fieldType != other.fieldType)
			return false;
		if (filterEntity == null) {
			if (other.filterEntity != null)
				return false;
		} else if (!filterEntity.equals(other.filterEntity))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (joinType != other.joinType)
			return false;
		if (last != other.last)
			return false;
		if (operator == null) {
			if (other.operator != null)
				return false;
		} else if (!operator.equals(other.operator))
			return false;
		if (order != other.order)
			return false;
		if (type != other.type)
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
		return "MRule [id=" + id + ", type=" + type + ", enabled=" + enabled
				+ ", action=" + action + ", field=" + field + ", last=" + last
				+ ", joinType=" + joinType + ", operator=" + operator
				+ ", filterEntity=" + filterEntity + ", value=" + value
				+ ", desc=" + desc + ", fieldType=" + fieldType + ", order="
				+ order + ", expression=" + expression + ", dealOrganId="
				+ dealOrganId + "]";
	}

}
