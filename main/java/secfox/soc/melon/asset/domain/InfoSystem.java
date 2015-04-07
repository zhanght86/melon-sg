/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.domain;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import secfox.soc.melon.persistence.TreeDomain.NodeState;

/**
 * @since 1.0 2014年9月24日,下午3:48:54
 * 信息系统类
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Entity
@Table(name = "T_ASSET_INFO")
@NamedQueries({
	//通过登录人获取批注的信息系统
	@NamedQuery(name = "infoSystem.findByQuickNote", query = "select a from QuickNote quickNote, InfoSystem a where quickNote.businessId = a.id and quickNote.creator.userId = :userId "),
	//获取全部信息系统
	@NamedQuery(name = "infoSystem.findAll" ,query="select a from InfoSystem a"),
	//根据编码查询
	@NamedQuery(name = "infoSystem.findByCode" ,query="select a from InfoSystem a where a.code =:code "),
	//根据访问地址查询
	@NamedQuery(name = "infoSystem.findByUrl" ,query="select a from InfoSystem a where a.url =:url "),
	//单位视图
	@NamedQuery(name = "infoSystem.groupByOrgan" ,query="select a.organName,count(a.id) from InfoSystem a group by a.organName "),
	//根据单位查询
	@NamedQuery(name = "infoSystem.findByOrgan" ,query="select a from InfoSystem a where a.organId =:organId "),
	//根据当前用户查询关注的信息系统
	@NamedQuery(name = "infoSystem.findByUserBusiness", query = "select infoSystem from Attention attention, InfoSystem infoSystem where attention.businessId = infoSystem.id and attention.user.userId = :userId "),
	//获取到最大的numbersum
	@NamedQuery(name = "infoSystem.findNumberSum", query = "select a from InfoSystem a where a.organId = :organId order by a.numberSum desc "),
	//yb 根据typePath查询信息系统
	@NamedQuery(name = "infoSystem.findByTypePath", query = "select a from InfoSystem a where (a.typePath like :path or a.typePath like :rightPath) and (a.organPath like :organPath or a.organId = :organId )"),
	//根据设备id获取信息系统
	@NamedQuery(name = "infoSystem.findByDeviceId", query = "select a from InfoSystem a,DeviceRoles d where a.id = d.infoId and d.deviceId = :deviceId"),
	//根据单位id获取本单位及下级单位的信息系统
	@NamedQuery(name = "infoSystem.findByOrganPath", query = "select a from InfoSystem a where a.organPath like :path or a.organPath like :rightPath ")
	
})
public class InfoSystem extends AbstractDevice {

	private static final long serialVersionUID = -6721777489402384850L;
	
	@Transient
	private Long[] deviceIds;
	
	@Transient
	private String[] deviceNames;
	
	@Transient
	private List<DeviceRoles> devices;
	
	
	
	@Column(name="PARENTID")
	private Long parentId = 1L;
	//访问地址
	@Column(name="INFO_URL")
	private String url;
	
	//上线时间
	@Column(name="ONLINE_TIME")
	@Temporal(TemporalType.DATE)
	private Date onlineTime;

	//业务类型 
	@Column(name="SYS_TYPE", length=2)
	private int sysType = 1;
	
	//业务描述
	@Column(name="SYS_DESC", length=200)
	private String sysDesc;
	
	//服务范围
	@Column(name="SER_SCOPE", length=2)
	private int serScope = 1;
	
	//跨省（区、市）、跨地（市、区）个数
	@Column(name="SER_COUNT")
	private int serCount = 1;
	
	//服务对象 1:单位内部人员,2:社会公众人员,3:两者均包括
	@Column(name="SER_OBJECT", length=2)
	private int serObject = 1;
	
	//覆盖范围 1:局域网,2:城域网,3:广域网
	@Column(name="NET_SCOPE", length=2)
	private int netScope = 1;

	//网络性质 
	@Column(name="NET_TYPE", length=2)
	private int netType = 0;
	
	//网络性质-其他
	@Column(name="NET_OTHER")
	private String netOther;
	
	//系统互联情况
	@Column(name="CONN_TYPE", length=2)
	private int connType = 1;
	
	//是否关键
	@Column(name="ISHINGE")
	private boolean ishinge = false;
	
	//信息系统类别 1:业务类,2:行政办公类,3:安全类,4:其他，当选“其它”时，需填写其它内容
	@Column(name="INFOTYPE" , length=2)
	private int infoType = 1;
	
	//备份系统
	@Column(name="INFO_ID")
	private Long infoId;
	
	//安全等级1:一级,2:二级,3:三级,4:四级,5:五级
	@Column(name="SAFELEVEN", length=2)
	private int safeLeven = 1;
	
	
	public Long[] getDeviceIds() {
		return deviceIds;
	}

	public void setDeviceIds(Long[] deviceIds) {
		this.deviceIds = deviceIds;
	}

	public String[] getDeviceNames() {
		return deviceNames;
	}

	public void setDeviceNames(String[] deviceNames) {
		this.deviceNames = deviceNames;
	}

	public List<DeviceRoles> getDevices() {
		return devices;
	}

	public void setDevices(List<DeviceRoles> devices) {
		this.devices = devices;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Date getOnlineTime() {
		return onlineTime;
	}

	public void setOnlineTime(Date onlineTime) {
		this.onlineTime = onlineTime;
	}
	public int getSysType() {
		return sysType;
	}

	public void setSysType(int sysType) {
		this.sysType = sysType;
	}

	public String getSysDesc() {
		return sysDesc;
	}

	public void setSysDesc(String sysDesc) {
		this.sysDesc = sysDesc;
	}

	public int getSerScope() {
		return serScope;
	}

	public void setSerScope(int serScope) {
		this.serScope = serScope;
	}

	public int getSerCount() {
		return serCount;
	}

	public void setSerCount(int serCount) {
		this.serCount = serCount;
	}

	public int getSerObject() {
		return serObject;
	}

	public void setSerObject(int serObject) {
		this.serObject = serObject;
	}

	public int getNetScope() {
		return netScope;
	}

	public void setNetScope(int netScope) {
		this.netScope = netScope;
	}

	public int getNetType() {
		return netType;
	}

	public void setNetType(int netType) {
		this.netType = netType;
	}

	public String getNetOther() {
		return netOther;
	}

	public void setNetOther(String netOther) {
		this.netOther = netOther;
	}

	public int getConnType() {
		return connType;
	}

	public void setConnType(int connType) {
		this.connType = connType;
	}

	public boolean isIshinge() {
		return ishinge;
	}

	public void setIshinge(boolean ishinge) {
		this.ishinge = ishinge;
	}

	public int getInfoType() {
		return infoType;
	}

	public void setInfoType(int infoType) {
		this.infoType = infoType;
	}


	public int getSafeLeven() {
		return safeLeven;
	}

	public void setSafeLeven(int safeLeven) {
		this.safeLeven = safeLeven;
	}

	public Long getInfoId() {
		return infoId;
	}

	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + connType;
		result = prime * result + Arrays.hashCode(deviceIds);
		result = prime * result + Arrays.hashCode(deviceNames);
		result = prime * result + ((devices == null) ? 0 : devices.hashCode());
		result = prime * result + ((infoId == null) ? 0 : infoId.hashCode());
		result = prime * result + infoType;
		result = prime * result + (ishinge ? 1231 : 1237);
		result = prime * result
				+ ((netOther == null) ? 0 : netOther.hashCode());
		result = prime * result + netScope;
		result = prime * result + netType;
		result = prime * result
				+ ((onlineTime == null) ? 0 : onlineTime.hashCode());
		result = prime * result + safeLeven;
		result = prime * result + serCount;
		result = prime * result + serObject;
		result = prime * result + serScope;
		result = prime * result + ((sysDesc == null) ? 0 : sysDesc.hashCode());
		result = prime * result + sysType;
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		InfoSystem other = (InfoSystem) obj;
		if (connType != other.connType)
			return false;
		if (!Arrays.equals(deviceIds, other.deviceIds))
			return false;
		if (!Arrays.equals(deviceNames, other.deviceNames))
			return false;
		if (devices == null) {
			if (other.devices != null)
				return false;
		} else if (!devices.equals(other.devices))
			return false;
		if (infoId == null) {
			if (other.infoId != null)
				return false;
		} else if (!infoId.equals(other.infoId))
			return false;
		if (infoType != other.infoType)
			return false;
		if (ishinge != other.ishinge)
			return false;
		if (netOther == null) {
			if (other.netOther != null)
				return false;
		} else if (!netOther.equals(other.netOther))
			return false;
		if (netScope != other.netScope)
			return false;
		if (netType != other.netType)
			return false;
		if (onlineTime == null) {
			if (other.onlineTime != null)
				return false;
		} else if (!onlineTime.equals(other.onlineTime))
			return false;
		if (safeLeven != other.safeLeven)
			return false;
		if (serCount != other.serCount)
			return false;
		if (serObject != other.serObject)
			return false;
		if (serScope != other.serScope)
			return false;
		if (sysDesc == null) {
			if (other.sysDesc != null)
				return false;
		} else if (!sysDesc.equals(other.sysDesc))
			return false;
		if (sysType != other.sysType)
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "InfoSystem [deviceIds=" + Arrays.toString(deviceIds)
				+ ", deviceNames=" + Arrays.toString(deviceNames)
				+ ", devices=" + devices + ", url=" + url + ", onlineTime="
				+ onlineTime + ", sysType=" + sysType + ", sysDesc=" + sysDesc
				+ ", serScope=" + serScope + ", serCount=" + serCount
				+ ", serObject=" + serObject + ", netScope=" + netScope
				+ ", netType=" + netType + ", netOther=" + netOther
				+ ", connType=" + connType + ", ishinge=" + ishinge
				+ ", infoType=" + infoType + ", infoId=" + infoId
				+ ", safeLeven=" + safeLeven + "]";
	}

	public String getText() {
		return super.getName();
	}
	
	/**
	 * 
	 */
	public Object getParent() {
		/*if(this.isAsRoot()) {
			return "#";
		}*/
		return parentId == null ? "#" : parentId;
	}


	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
	
	@Transient
	private NodeState state = new NodeState();
	
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
}
