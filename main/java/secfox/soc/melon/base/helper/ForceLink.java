/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base.helper;

/**
 * @since 1.0 2014年10月17日,下午3:01:50
 * 力导向图的链接关系
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class ForceLink {
	
	private String source;
	
	private String target;
	
	private double weight = 1;

	/**
	 * 
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * 
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * 
	 * @return the target
	 */
	public String getTarget() {
		return target;
	}

	/**
	 * 
	 * @param target the target to set
	 */
	public void setTarget(String target) {
		this.target = target;
	}

	/**
	 * 
	 * @return the weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * 
	 * @param weight the weight to set
	 */
	public void setWeight(double weight) {
		this.weight = weight;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ForceLink [source=" + source + ", target=" + target
				+ ", weight=" + weight + "]";
	}
	
}
