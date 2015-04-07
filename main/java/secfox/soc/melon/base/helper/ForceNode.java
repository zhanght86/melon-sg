/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base.helper;

import java.util.Arrays;

import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 1.0 2014年10月17日,下午2:53:50
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class ForceNode extends BaseDomain<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6424744088291305164L;
	
	private Long id;
	
	private String name;
	
	private Integer category;
	
	private Double value;
	
	private Integer depth;
	
	private int[] initial;
	
	private boolean fixY;
	
	private boolean fixX;
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void setPos(int x, int y) {
		this.initial = new int[]{x, y};
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.Identifiable#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.Identifiable#setId(java.io.Serializable)
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * 
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 
	 * @return the category
	 */
	public Integer getCategory() {
		return category;
	}

	/**
	 * 
	 * @param category the category to set
	 */
	public void setCategory(Integer category) {
		this.category = category;
	}

	/**
	 * 
	 * @return the value
	 */
	public Double getValue() {
		return value;
	}

	/**
	 * 
	 * @param value the value to set
	 */
	public void setValue(Double value) {
		this.value = value;
	}

	/**
	 * 
	 * @return the depth
	 */
	public Integer getDepth() {
		return depth;
	}

	/**
	 * 
	 * @param depth the depth to set
	 */
	public void setDepth(Integer depth) {
		this.depth = depth;
	}

	/**
	 * 
	 * @return the initial
	 */
	public int[] getInitial() {
		return initial;
	}

	/**
	 * 
	 * @param initial the initial to set
	 */
	public void setInitial(int[] initial) {
		this.initial = initial;
	}

	/**
	 * 
	 * @return the fixY
	 */
	public boolean isFixY() {
		return fixY;
	}

	/**
	 * 
	 * @param fixY the fixY to set
	 */
	public void setFixY(boolean fixY) {
		this.fixY = fixY;
	}

	/**
	 * 
	 * @return the fixX
	 */
	public boolean isFixX() {
		return fixX;
	}

	/**
	 * 
	 * @param fixX the fixX to set
	 */
	public void setFixX(boolean fixX) {
		this.fixX = fixX;
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
		ForceNode other = (ForceNode) obj;
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
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ForceNode [id=" + id + ", name=" + name + ", category="
				+ category + ", value=" + value + ", depth=" + depth
				+ ", initial=" + Arrays.toString(initial) + ", fixY=" + fixY
				+ ", fixX=" + fixX + "]";
	}

}
