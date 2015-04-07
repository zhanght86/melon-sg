/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.datas.library;

import javax.persistence.Column;
import javax.persistence.Lob;

import com.fasterxml.jackson.annotation.JsonIgnore;

import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 1.0 2014年11月10日,下午7:41:24
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class Wiki extends BaseDomain<Long> {
	
	private static final long serialVersionUID = -3903239436160830811L;

	private Long id;
	
	//与菜单相关联
	private Long menuId;
	
	@Column(name="TITLE")
	private String title;
	
	@Lob
	@Column(name="ANSWER")
	@JsonIgnore
	private String answer;

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
