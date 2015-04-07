/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.business.db.domain;

import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 1.0 2014年10月20日,下午3:34:01
 * 第一次使用等保测试时触发
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class FirstUseDb extends BaseDomain<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4170268651891637677L;
	
	private Long id;
	
	//启动等保时,首先判断此单位是否首次进行测评
	//每个单位的配置都尽可能一致
	private Long organId;
	
	private String chName;
	
	private String chDuty;
	
	private String chTel;
	
	private String chMail;
	
	//06	责任部门
	private String departName;
	
	private String deName;
	
	private String deDuty;
	
	private String deTel;
	
	private String dePhone;
	
	private String deMail;

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
