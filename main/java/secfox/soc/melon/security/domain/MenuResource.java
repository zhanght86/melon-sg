/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.security.domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.Objects;
import com.google.common.collect.Lists;

import secfox.soc.melon.base.CopyTo;
import secfox.soc.melon.persistence.TreeDomain;
import secfox.soc.melon.web.UrlOpenStyle;

/**
 * @since 1.0 2014年9月22日,上午10:57:55
 * 菜单资源统一管理器,
 * 最多支持3层菜单,每个菜单下都可添加按钮
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name="T_SECURITY_RESOURCE")
@NamedQueries({
	@NamedQuery(name="menuResource.findAll", query="select mr from MenuResource mr order by mr.path, mr.order asc"),
	@NamedQuery(name="menuResource.findCommons", query="select mr from MenuResource mr where mr.path like :path"),
	@NamedQuery(name="menuResource.findUrlEqual", query="select mr from MenuResource mr where mr.url = :url"),
	@NamedQuery(name="menuResource.findChildren", query="select mr from MenuResource mr where mr.path like :path order by id desc"),
	@NamedQuery(name="menuResource.findByType", query="select mr from MenuResource mr where mr.type = :type order by mr.path, mr.order asc")
})

public class MenuResource extends TreeDomain<Long> implements  CopyTo<MenuResource> {

	private static final long serialVersionUID = 7231935211015327930L;

	@Id@Column(name="PK")
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;
	
	//菜单\按钮简称,将显示在菜单或按钮上,最多只能5个字
	@Column(name="SHORT_NAME", length=10)
	private String shortName;
	
	//标题,在滑过菜单或按钮时,将会显示此信息
	@Column(name="M_TITLE",length=100)
	private String title;
	
	//地址
	@Column(name="M_URL",length=128,unique=true)
	private String url;
	
	@Column(name="M_PATH", length=256)
	private String path;
	
	//深度,辅助字段
	@Column(name="M_DEEP")
	private int deep = 1;
	
	//父节点主键
	@Column(name="PARENT_ID")
	private Long parentId;
	
	//优先级 0:高,1:中,2:低
	@Column(name="M_ORDER")
	private int order = 10;
	
	//是否禁用
	@Column(name="M_ENABLED")
	private boolean enabled = true;
	
	//菜单的类别,0:菜单,1:按钮,2:仪表盘
	@Column(name="M_TYPE")
	private short type = 0;
	
	//当作为仪表盘使用时,必须提供最小宽度与最小高度,默认为3
	@Column(name="MIN_HEIGHT")
	private short minHeight = 3;
	
	@Column(name="MIN_WIDTH")
	private short minWidth = 6;
	
	//打开方式
	@Column(name="OPEN_STYLE")
	@Enumerated(EnumType.ORDINAL)
	private UrlOpenStyle openStyle;
	
	//图标样式
	@Column(name="ICON_CSS")
	private String iconCss;
	
	//资源对应的角色,放弃对主端的维护
	@ManyToMany(targetEntity=Role.class,fetch=FetchType.EAGER, mappedBy="resources")
	@Fetch(FetchMode.SUBSELECT)
	@JsonIgnore
	private Set<Role> roles;
	
	//访问路径
	@JsonIgnore
	@Transient
	private List<MenuResource> locations;
	
	//子节点
	@JsonIgnore
	@Transient
	private List<MenuResource> items;
	
	//当前节点是否被激活了
	@JsonIgnore
	@Transient
	private boolean active;
	
	/**
	 * 
	 */
	public void sort() {
		if(this.items != null) {
			//对自身排序
			Collections.sort(this.items, new MenuResourceComparator());
			//对子节点排序
			for(MenuResource item : items) {
				item.sort();
			}
		}
	}
	
	/**
	 * @return the active
	 */
	public boolean isActive() {
		return active;
	}


	/**
	 * @param active the active to set
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * 
	 * @param item
	 */
	public void addItem(MenuResource item) {
		if(items == null) {
			items = Lists.newArrayList();
		}
		if(!items.contains(item)) {
			items.add(item);
		}
	}
	
	/**
	 * 
	 * @param item
	 * @return
	 */
	public boolean contains(MenuResource item) {
		if(items == null) {
			return false;
		}
		return items.contains(item);
	}
	
	
	/**
	 * @return the items
	 */
	public List<MenuResource> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<MenuResource> items) {
		this.items = items;
	}

	/**
	 * @return the locations
	 */
	public List<MenuResource> getLocations() {
		return locations;
	}

	/**
	 * @param locations the locations to set
	 */
	public void setLocations(List<MenuResource> locations) {
		this.locations = locations;
	}
	
	/**
	 * @return the deep
	 */
	public int getDeep() {
		return deep;
	}

	/**
	 * @param deep the deep to set
	 */
	public void setDeep(int deep) {
		this.deep = deep;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.Identifiable#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.framework.persistence.Identifiable#setId(java.io.Serializable)
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return the roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * 
	 * @param roles the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	/**
	 * 
	 * @return
	 */
	public short getType() {
		return type;
	}

	/**
	 * 
	 * @param type
	 */
	public void setType(short type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.commons.TreeDomain#getText()
	 */
	@Override
	public String getText() {
		return shortName;
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
	 * 
	 * @return
	 */
	public short getMinHeight() {
		return minHeight;
	}

	/**
	 * 
	 * @param minHeight
	 */
	public void setMinHeight(short minHeight) {
		this.minHeight = minHeight;
	}

	/**
	 * 
	 * @return
	 */
	public short getMinWidth() {
		return minWidth;
	}

	/**
	 * 
	 * @param minWidth
	 */
	public void setMinWidth(short minWidth) {
		this.minWidth = minWidth;
	}

	/**
	 * 
	 * @return
	 */
	public String getShortName() {
		return shortName;
	}

	/**
	 * @param shortName
	 */
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	/**
	 * 
	 * @return
	 */
	public String getPath() {
		return path;
	}
	
	/**
	 * 
	 * @param path
	 */
	public void setPath(String path) {
		this.path = path;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * 
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * 
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
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
	 * @return the enabled
	 */
	public boolean isEnabled() {
		return enabled;
	}

	/**
	 * 
	 * @param enabled the enabled to set
	 */
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	/**
	 * 
	 * @return the openStyle
	 */
	public UrlOpenStyle getOpenStyle() {
		return openStyle;
	}

	/**
	 * 
	 * @param openStyle the openStyle to set
	 */
	public void setOpenStyle(UrlOpenStyle openStyle) {
		this.openStyle = openStyle;
	}

	/**
	 * 
	 * @return the iconCss
	 */
	public String getIconCss() {
		return iconCss;
	}

	/**
	 * 
	 * @param iconCss the iconCss to set
	 */
	public void setIconCss(String iconCss) {
		this.iconCss = iconCss;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((shortName == null) ? 0 : shortName.hashCode());
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
		MenuResource other = (MenuResource) obj;
		if (enabled != other.enabled)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (shortName == null) {
			if (other.shortName != null)
				return false;
		} else if (!shortName.equals(other.shortName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return Objects.toStringHelper(this)
					  .add("id", id)
					  .add("shortName", shortName)
					  .add("title", title)
					  .add("enabled", enabled)
					  .add("url", url)
					  .add("order", order)
					  .add("path", path)
					  .add("parentId", parentId)
					  .add("type", type)
					  .add("openStyle", openStyle)
					  .toString();
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.base.CopyTo#copy()
	 */
	@Override
	public MenuResource copy() {
		MenuResource resource = new MenuResource();
		resource.setId(id);
		resource.setShortName(shortName);
		resource.setTitle(title);
		resource.setUrl(url);
		resource.setPath(path);
		resource.setType(type);
		resource.setParentId(parentId);
		return resource;
	}

}
