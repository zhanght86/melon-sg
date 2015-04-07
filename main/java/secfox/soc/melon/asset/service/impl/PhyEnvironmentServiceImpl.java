/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.asset.service.impl;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.melon.asset.dao.PhyEnvironmentDao;
import secfox.soc.melon.asset.domain.Device;
import secfox.soc.melon.asset.domain.PhyEnvironment;
import secfox.soc.melon.asset.service.DeviceService;
import secfox.soc.melon.asset.service.PhyEnvironmentService;
import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.security.util.SecurityContextUtils;

/**
 * @since 2014-4-18,上午10:23:56
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Service
public class PhyEnvironmentServiceImpl extends GenericServiceImpl<PhyEnvironment, Long>
		implements PhyEnvironmentService {
	
	private PhyEnvironmentDao dao;
	private DeviceService deviceService;
	
	@Inject
	public PhyEnvironmentServiceImpl(DeviceService deviceService,PhyEnvironmentDao dao){
		super();
		this.deviceService =deviceService;
		this.dao = dao;
	}
	
	  /* (non-Javadoc)
     * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#getDao()
     */
    @Override
    protected GenericDao<PhyEnvironment, Long> getDao() {
        return dao;
    }

    /*
     * (non-Javadoc)
     * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#merge(secfox.soc.melon.persistence.Identifiable)
     */
    @Override
    @Transactional(readOnly = false)
    public PhyEnvironment merge(PhyEnvironment entity) {
    	entity.setCreateTime(new Date());
    	PhyEnvironment envi = super.merge(entity);
    	//找到数据此物理环境的设备
    	List<Device> findByEvi = deviceService.findByEvi(envi.getId());
    	//循环修改物理环境名称
    	if(findByEvi !=null && findByEvi.size()>0){
    		for (int i = 0; i < findByEvi.size(); i++) {
    			findByEvi.get(i).setEnviName(envi.getName());
			}
    	}
    	return envi;
    }
    /**
	 * 创建根节点
	 * @return
	 */
	public PhyEnvironment createRoot() {
		PhyEnvironment envi = new PhyEnvironment();
		envi.setName(MessageSourceUtils.getMessage("asset.envi.root"));
		envi.setParentId(null);
		envi.setId(BaseConstants.ROOT_ID);
		envi.setOrder(0);
		envi.getState().setOpened(true);
		envi.setUserInfo(SecurityContextUtils.getCurrentUserInfo());
		return envi;
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.AssetTypeService#findTree(java.lang.Long)
	 */
	@Override
	public List<PhyEnvironment> findTree(Long rootId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select a from PhyEnvironment a where 1=1 ");
		PhyEnvironment envi = new PhyEnvironment();
		String path = String.valueOf(BaseConstants.ROOT_ID);
		//如果是默认节点
		if(rootId==BaseConstants.ROOT_ID){
			envi = createRoot();
		} else {
			envi = find(rootId); //根据id获取
			path = envi.getPath();
		}
		
		//登录人单位的数据
		qt.append(" and a.userInfo.organId =:organId");
		qt.addParameter("organId", SecurityContextUtils.getCurrentAccount().getCompanyId());
		
		//路径匹配
		qt.append(" and a.path like :path");
		qt.addParameter("path", QueryTemplate.buildLeftLike(path));
		
		//排序
		qt.append(" order by a.order desc");
		List<PhyEnvironment> types = findDomains(qt);//获取孙子节点
		//如果包含根节点
		if(types.contains(envi)){ 
			PhyEnvironment enviRoot = types.get(types.indexOf(envi)); //获取这个对象的位置
			enviRoot.setAsRoot(true); //设置为根节点
			enviRoot.getState().setOpened(true); //默认打开节点
		}else{
			//不包含,就加进去
			types.add(envi);
		}
		return types;
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.AssetTypeService#findType()
	 */
	@Override
	public List<PhyEnvironment> findAll() {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"phyEnvironment.findAll");
		return findDomains(qt);
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#remove(java.io.Serializable)
	 */
	@Override
	@Transactional(readOnly = false)
	public boolean deleteEnvi(Long pk) {
		List<Device> findByEvi = deviceService.findByEvi(pk);
		if(findByEvi !=null && findByEvi.size()>0){
			return false;
		}else{
			dao.remove(pk);
			return true;
		}
	}

}
