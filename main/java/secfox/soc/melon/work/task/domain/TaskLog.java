/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.work.task.domain;

import java.util.Date;

import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 1.0 2014年10月31日,下午6:07:54
 * 工作日志
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class TaskLog extends BaseDomain<Long> {
	
	private static final long serialVersionUID = -174229312154391122L;

	private Long id;
	
	//任务编码
	private Long taskCode;
	
	//业务主键
	private Long businessId;
	
	//操作人
	private UserInfo creator;
	
	//操作时间
	private Date createTime;
	
	//日志描述信息,如对ERP信息系统进行了等级测评
	private String desc;
	
	//操作结果访问地址,/system/flow/log/12,操作后生成
	private String url;
	
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
