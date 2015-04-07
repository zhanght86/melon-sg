/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.plan.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;

import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 1.0 2014年10月23日,下午7:37:36
 * 计划,
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class Plan extends BaseDomain<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4726034321397856168L;
	
	//计划开始时间
	private Date start;
	
	//计划结束时间
	private Date end;
	
	//级别 1:单位,2:部门,3:个人
	private int level;
	
	//计划类型,1:安全检查,2:设备巡检,3:信息系统巡检,100:其他
	private int sysType;
	
	//当计划类型选择其他时,让用户手动输入
	private String otherType;
	
	//创建时间
	private Date createTime;
	
	//创建人
	@Embedded
	private UserInfo creator;
	
	//计划名称
	@Column(name="TITLE", length=100)
	private String title;
	
	//计划内容
	@Column(name="CON_TENT", length=1000)
	private String content;
	
	

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
