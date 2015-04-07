/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.persistence;

import java.io.Serializable;

import javax.persistence.Transient;

/**
 * @since 1.0 2014年9月21日,下午2:04:10
 * 树形业务对象
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public abstract class TreeDomain<ID extends Serializable> extends BaseDomain<ID> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3904712407928829082L;
	
	/**
	 * 获取节点需要显示的文字
	 * @return
	 */
	public abstract String getText();
	
	
	/**
	 * 在AJAX模式下,只有此值返回true,才会表明此节点是展开的
	 * 但是如果子节点与父节点在同一批数据中,父节点的此属性必须为false,一次加载完成,则所有节点的此属性必须为false
	 * @return
	 */
	public boolean getChildren() {
		return false;
	}
	
	/**
	 * 获取父节点显示的编号
	 * 根节点的父节点编号必须是#
	 * @return
	 */
	public abstract Object getParent();
	
	/**
	 * 节点状态
	 */
	@Transient
	private NodeState state = new NodeState();
	
	@Transient
	private boolean asRoot = false;

	/**
	 * @return the state
	 */
	public NodeState getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(NodeState state) {
		this.state = state;
	}
	
	public boolean isAsRoot() {
		return asRoot;
	}

	public void setAsRoot(boolean asRoot) {
		this.asRoot = asRoot;
	}

	/**
	 * 
	 * @since 1.0 2014年10月8日,下午2:34:59
	 * 
	 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
	 * @version  1.0
	 */
	public static class NodeState implements Serializable{
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -8069316353255345687L;

		/**
		 * 节点是否在加载时展开,默认展开
		 */
		private boolean opened = false;
		
		/**
		 * 节点是否禁用
		 */
		private boolean disabled = false;
		
		/**
		 * 节点是否被聚焦
		 */
		private boolean selected = false;
		
		/**
		 * 节点是否被选中
		 */
		private boolean checked = false;
		
		/**
		 * 当前节点是否被选中了
		 * @return
		 */
		public boolean isChecked() {
			return checked;
		}

		public void setChecked(boolean checked) {
			this.checked = checked;
		}

		/**
		 * @return the opened
		 */
		public boolean isOpened() {
			return opened;
		}

		/**
		 * @param opened the opened to set
		 */
		public void setOpened(boolean opened) {
			this.opened = opened;
		}

		/**
		 * @return the disabled
		 */
		public boolean isDisabled() {
			return disabled;
		}

		/**
		 * @param disabled the disabled to set
		 */
		public void setDisabled(boolean disabled) {
			this.disabled = disabled;
		}

		/**
		 * @return the selected
		 */
		public boolean isSelected() {
			return selected;
		}

		/**
		 * @param selected the selected to set
		 */
		public void setSelected(boolean selected) {
			this.selected = selected;
		}
		
	}
}
