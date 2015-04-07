/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base.domain;

import java.io.Serializable;

import javax.persistence.Transient;

import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 1.0 2014年10月28日,下午6:07:31
 * 流程图状态
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public abstract class FlowState<ID extends Serializable> extends BaseDomain<ID>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4498119014157566742L;

	/**
	 * 是否选中
	 */
	@Transient
	private boolean selected;
	
	/**
	 * 是否活跃
	 */
	@Transient
	private boolean actived;
	
	/**
	 * 是否激活
	 */
	@Transient
	private boolean enabled;

	/**
	 * 
	 * @return the selected
	 */
	public boolean isSelected() {
		return selected;
	}

	/**
	 * 
	 * @param selected the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	 * 
	 * @return the actived
	 */
	public boolean isActived() {
		return actived;
	}

	/**
	 * 
	 * @param actived the actived to set
	 */
	public void setActived(boolean actived) {
		this.actived = actived;
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
	
}
