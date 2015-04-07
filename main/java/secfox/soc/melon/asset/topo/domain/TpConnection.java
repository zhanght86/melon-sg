/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.topo.domain;

import javax.persistence.Column;
import javax.persistence.Transient;

import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 1.0 2014年10月21日,下午8:46:10
 * 连接关系
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class TpConnection extends BaseDomain<Long> {
	
	private Long id;
	
	/**
	 * 
	 */
	@Column(name="TARGET_ID")
	private Long target;
	
	@Column(name="TP_TEXT")
	private String text;
	
	@Column(name="SOURCE_ID")
	private Long source;
	
	@Transient
	private String props;
	
	@Column(name="WEIGHT")
	private int weight;

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.Identifiable#getId()
	 */
	@Override
	public Long getId() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.Identifiable#setId(java.io.Serializable)
	 */
	@Override
	public void setId(Long id) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.BaseDomain#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.BaseDomain#hashCode()
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.BaseDomain#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

}
