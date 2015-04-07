/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.home.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.collect.Lists;

import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 1.0 2014年10月22日,下午7:55:21
 * Portal布局,与具体的业务分类相关,如代办任务布局、安全对象布局
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name="T_HOME_LAYOUT")
@NamedQueries({
	@NamedQuery(name="portal.findLayout", query="select layout from PortalLayout layout where layout.userId = :userId order by layout.order "),
	@NamedQuery(name="portal.findViews", query="select layout from PortalLayout layout where layout.id = :id "),
	@NamedQuery(name="portal.setDefaultHome", query="update PortalLayout layout set layout.defaultHome = false where layout.userId = :userId and layout.defaultHome = true"),
	@NamedQuery(name="portal.findPubViews", query="select layout from PortalLayout layout where layout.allUser = true "),
	@NamedQuery(name="portal.findSys", query="select layout from PortalLayout layout "
			+ "where layout.userId = :userId or layout.allUser is true "
			+ "order by layout.order asc")
})
public class PortalLayout extends BaseDomain<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5956013322899193880L;
	
	@Id
	@Column(name = "PK")
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;
	
	//是否作为默认主页,每个人只能有一个默认主页
	@Column(name = "DEFAULT_HOME")
	private boolean defaultHome;
	
	//分类
	//1:主页,2:代办任务,3:安全对象,4:监控视图,5:审计视图,6:工作总结,7:工作计划,8:等级保护,9:安全检查,100:用户自定义
	//如果type值等于100时(即用户自定义时),可以自定义名字,否则与分类名称保存一致
	@Column(name = "TYPE_NAME", length = 50)
	private String typeName;
	
	//顺序 按从小到大排列 ,默认20
	@Column(name = "L_ORDER")
	private int order = 20;
	
	//如果UserInfo等于安全平台,如果修改则需要复制一份,全局配置需要提供系统平台用户的提供方式
	//布局视图所有人
	@Column(name="USER_ID")
	private Long userId;
	
	//Portal的对齐方式,从左自右排,否则,则从右至左排
	@Column(name="LTR")
	private boolean leftToRight = true;
	
	//所有人可看
	//所有人可见时,为系统内置
	@Column(name="ALL_USER")
	private boolean allUser;
	
	//是否处于活动状态,页面专用
	@Transient
	private boolean active;
	
	//布局说明
	@JsonIgnore
	@Column(name="L_REMARK", length=1000)
	private String remarks;
	
	//view
	@OneToMany(fetch = FetchType.LAZY, targetEntity=PortalView.class, cascade=CascadeType.ALL, mappedBy="layout")
	@OrderBy("xpos asc")
	@JsonIgnore
	private List<PortalView> views;

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
	 * 
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * 
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * 
	 * @return the defaultHome
	 */
	public boolean isDefaultHome() {
		return defaultHome;
	}

	/**
	 * 
	 * @param defaultHome the defaultHome to set
	 */
	public void setDefaultHome(boolean defaultHome) {
		this.defaultHome = defaultHome;
	}

	/**
	 * 
	 * @return the allUser
	 */
	public boolean isAllUser() {
		return allUser;
	}

	/**
	 * 
	 * @param allUser the allUser to set
	 */
	public void setAllUser(boolean allUser) {
		this.allUser = allUser;
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
	 * 
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * 
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
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
	 * @return the leftToRight
	 */
	public boolean isLeftToRight() {
		return leftToRight;
	}

	/**
	 * @param leftToRight the leftToRight to set
	 */
	public void setLeftToRight(boolean leftToRight) {
		this.leftToRight = leftToRight;
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
	 * @return the views
	 */
	public List<PortalView> getViews() {
		return views;
	}

	/**
	 * @param views the views to set
	 */
	public void setViews(List<PortalView> views) {
		this.views = views;
	}
	
	/**
	 * 
	 * @param view
	 */
	public void addView(PortalView view) {
		if(views == null) {
			views = Lists.newArrayList();
		}
		views.add(view);
	}
	
	/**
	 * 
	 * @param view
	 */
	public void remove(PortalView view) {
		views.remove(view);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + order;
		result = prime * result
				+ ((typeName == null) ? 0 : typeName.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		PortalLayout other = (PortalLayout) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (order != other.order)
			return false;
		if (typeName == null) {
			if (other.typeName != null)
				return false;
		} else if (!typeName.equals(other.typeName))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PortalLayout [id=" + id + ", typeName="
				+ typeName + ", userId=" + userId + "]";
	}
	
}
