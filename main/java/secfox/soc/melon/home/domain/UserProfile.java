/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.home.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 1.0 2014年11月12日,上午11:18:32
 * 用户个人配置文件
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name="T_HOME_PROFILE")
public class UserProfile extends BaseDomain<Long>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -351259950754913870L;
	
	private Long id;
	
	private Long userId;
	
	//启动时显示向导
	private boolean showWizard = true;
	
	//提醒我修改密码
	private boolean changePass = false;
	
	//密码实效时间
	private int expireDays;
	
	

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
