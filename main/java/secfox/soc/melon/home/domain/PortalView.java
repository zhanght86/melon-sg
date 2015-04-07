/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.home.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import secfox.soc.melon.base.CopyTo;
import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 1.0 2014年10月22日,下午7:54:05
 * 用户自定义的Portal
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name="T_HOME_VIEW")
public class PortalView extends BaseDomain<Long> implements CopyTo<PortalView> {
	
	private static final long serialVersionUID = 1292384728926921812L;

	@Id
	@Column(name = "PK")
	@GeneratedValue(generator="GEN_SEQ_APP")
	private Long id;
	
	@Column(name = "X_POS")
	private int xpos = 20;
	
	@Column(name = "Y_POS")
	private int ypos;
	
	@Column(name = "P_WIDTH")
	private int width = 3;
	
	@Column(name = "P_HEIGHT")
	private int height = 3;
	
	//访问地址ID
	@Column(name = "MENU_ID")
	private Long menuId;
	
	@Transient
	private String title;
	
	@Transient
	private String url;
	
	//对应的Portal布局
	@ManyToOne(fetch=FetchType.LAZY, optional=true, targetEntity=PortalLayout.class)
	@JoinColumn(name="LAYOUT_ID", nullable=false, unique=false)
	@JsonIgnore
	private PortalLayout layout ;

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
	 * @return
	 */
	@JsonProperty(value="wp")
	public String getWidthPercent() {
		return Math.round(this.width * 100 / 12) + "%";
	}
	
	@JsonProperty(value="hp")
	public String getHeightPercent() {
		return Math.round(this.height * 100 / 12) + "%";
	}
	
	/**
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * 
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * 
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the xpos
	 */
	public int getXpos() {
		return xpos;
	}

	/**
	 * @param xpos the xpos to set
	 */
	public void setXpos(int xpos) {
		this.xpos = xpos;
	}

	/**
	 * @return the ypos
	 */
	public int getYpos() {
		return ypos;
	}

	/**
	 * @param ypos the ypos to set
	 */
	public void setYpos(int ypos) {
		this.ypos = ypos;
	}

	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(int width) {
		this.width = width;
	}
	
	/**
	 * 
	 * @return the menuId
	 */
	public Long getMenuId() {
		return menuId;
	}

	/**
	 * 
	 * @param menuId the menuId to set
	 */
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(int height) {
		this.height = height;
	}
	
	
	/**
	 * @return the layout
	 */
	public PortalLayout getLayout() {
		return layout;
	}

	/**
	 * @param layout the layout to set
	 */
	public void setLayout(PortalLayout layout) {
		this.layout = layout;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + height;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + width;
		result = prime * result + xpos;
		result = prime * result + ypos;
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
		PortalView other = (PortalView) obj;
		if (height != other.height)
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (width != other.width)
			return false;
		if (xpos != other.xpos)
			return false;
		if (ypos != other.ypos)
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "PortalView [id=" + id + ", xpos=" + xpos + ", ypos=" + ypos
				+ ", width=" + width + ", height=" + height + "]";
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.base.CopyTo#copy()
	 */
	@Override
	public PortalView copy() {
		PortalView result = new PortalView();
		result.setHeight(height);
		result.setMenuId(menuId);
		result.setTitle(title);
		result.setUrl(url);
		result.setWidth(width);
		result.setXpos(xpos);
		result.setYpos(ypos);
		return result;
	}
	
}
