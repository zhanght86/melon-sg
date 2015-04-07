/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.business.db.domain;

import java.util.Date;

import secfox.soc.melon.base.UserInfo;
import secfox.soc.melon.persistence.BaseDomain;

/**
 * @since 1.0 2014年10月29日,下午2:51:07
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class DbAttach extends BaseDomain<Long> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6099412661566881019L;
	
	private Long id;
	
	//应用SystemInfo中的主键
	private Long sysId;
	
	//01系统拓扑结构及说明  true:有 false:无
	private boolean hasTopo;
	
	//01系统拓扑结构及说明  拓扑附件主键
	private String topoAtId;
	
	//02系统安全组织机构及管理制度  true:有 false:无
	private boolean hasRule;
	
	//02系统安全组织机构及管理制度  拓扑附件主键
	private String ruleAtId;
	
	//03 系统安全保护设施设计实施方案或改建实施方案 true:有 false:无
	private boolean hasPlan;
	
	//03 系统安全保护设施设计实施方案或改建实施方案  拓扑附件主键
	private String planAtId;
	
	//04 统使用的安全产品清单及认证、销售许可证明 true:有 false:无
	private boolean hasLicence;
	
	//04 统使用的安全产品清单及认证、销售许可证明  拓扑附件主键
	private String licenceAtId;
	
	//05 系统等级测评报告 true:有 false:无
	private boolean hasLevel;
	
	//05 系统等级测评报告 拓扑附件主键
	private String levelAtId;
	
	//06专家评审情况 true:有 false:无
	private boolean hasReview;
	
	//06专家评审情况  拓扑附件主键
	private String reviewAtId;
	
	//07上级主管部门审批意见 true:有 false:无
	private boolean hasExamine;
	
	//07上级主管部门审批意见  拓扑附件主键
	private String examineAtId;
	
	//填表人
	private UserInfo opertator;
	
	//填表日期
	private Date createTime;

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
