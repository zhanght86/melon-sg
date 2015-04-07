/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.melon.asset.dao.AssetGroupDao;
import secfox.soc.melon.asset.dao.AssetTypeDao;
import secfox.soc.melon.asset.dao.DeviceDao;
import secfox.soc.melon.asset.dao.InfoSystemDao;
import secfox.soc.melon.asset.dao.PhyEnvironmentDao;
import secfox.soc.melon.asset.domain.AssetGroup;
import secfox.soc.melon.asset.domain.AssetType;
import secfox.soc.melon.asset.domain.Device;
import secfox.soc.melon.asset.domain.InfoSystem;
import secfox.soc.melon.asset.domain.PhyEnvironment;
import secfox.soc.melon.asset.service.AssetTypeService;
import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.security.SecurityConstants;


/**
 * 安全对象类型服务接口实现类
 * @since @2014-9-22,@上午11:07:46
 * @author <a href="mailto:wangwy@legendsec.com>王文越</a>
 * @version 1.0
 */
@Service
public class AssetTypeServiceImpl extends GenericServiceImpl<AssetType, Long> implements AssetTypeService {
	
	private AssetTypeDao dao;
	private AssetGroupDao assetGroupDao;
	private PhyEnvironmentDao enviDao;
	private InfoSystemDao infoSystemDao;
	private DeviceDao deviceDao;
	
	@Inject
	public AssetTypeServiceImpl(DeviceDao deviceDao,InfoSystemDao infoSystemDao,PhyEnvironmentDao enviDao,AssetGroupDao assetGroupDao,AssetTypeDao dao) {
		super();
		this.dao = dao;
		this.assetGroupDao = assetGroupDao;
		this.enviDao = enviDao;
		this.infoSystemDao= infoSystemDao;
		this.deviceDao = deviceDao;
	}

	@Override
	protected GenericDao<AssetType, Long> getDao() {
		return dao;
	}
	
	/**
	 * 创建根节点
	 * @return
	 */
	public AssetType createRoot() {
		AssetType atype = new AssetType();
		atype.setName(MessageSourceUtils.getMessage("asset.assetType.genjiedian"));
		atype.setParentId(null);
		atype.setId(BaseConstants.ROOT_ID);
		atype.setOrder(0);
		atype.getState().setOpened(true);
		return atype;
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.AssetTypeService#findTree(java.lang.Long)
	 */
	@Override
	public List<AssetType> findTree(Long rootId, boolean choosen) {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select a from AssetType a where 1=1 ");
		AssetType assetType = new AssetType();
		String path = String.valueOf(BaseConstants.ROOT_ID);
		//如果是默认节点
		if(rootId==BaseConstants.ROOT_ID){
			assetType = createRoot();
		} else {
			assetType = find(rootId); //根据id获取
			path = assetType.getPath();
		}
		//路径匹配
		qt.append(" and a.path like :path");
		qt.addParameter("path", QueryTemplate.buildLeftLike(path));
		//是否包含通用路径
		if(choosen) {
			qt.append(" and a.path not like :notPath ");
			qt.addParameter("notPath", QueryTemplate.buildLeftLike(SecurityConstants.PATH_COMMON));
		}
		//排序
		qt.append(" order by a.order desc");
		List<AssetType> types = findDomains(qt);//获取孙子节点
		//如果包含根节点
		if(types.contains(assetType)){ 
			AssetType typeRoot = types.get(types.indexOf(assetType)); //获取这个对象的位置
			typeRoot.setAsRoot(true); //设置为根节点
			typeRoot.getState().setOpened(true); //默认打开节点
		}else{
			//不包含,就加进去
			types.add(assetType);
		}
		return types;
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.AssetTypeService#findType()
	 */
	@Override
	public List<AssetType> findAll() {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"assetType.findAll");
		return findDomains(qt);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.AssetTypeService#findCustomTree(int)
	 */
	@Override
	public List<AssetType> findCustomTree(int type) {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select a from AssetType a where 1=1 ");
		AssetType root = createRoot();
		//路径匹配
		switch (type) {
		case 0:
			qt.append(" and ( a.path like :path1 ");
			qt.addParameter("path1",QueryTemplate.buildAllLike(","+BaseConstants.TYPE_INFO));
			qt.append(" or a.path like :path2 )");
			qt.addParameter("path2", QueryTemplate.buildRightLike(BaseConstants.TYPE_INFO.toString()));
			break;
		case 1:
			qt.append(" and a.path not like :path11 ");
			qt.addParameter("path11",QueryTemplate.buildAllLike(","+BaseConstants.TYPE_INFO+","));
			qt.append(" and a.path not like :path12 ");
			qt.addParameter("path12", QueryTemplate.buildRightLike(BaseConstants.TYPE_INFO+","));
			
			qt.append(" and a.path not like :path21 ");
			qt.addParameter("path21",QueryTemplate.buildAllLike(","+BaseConstants.TYPE_GROUP+","));
			qt.append(" and a.path not like :path22 ");
			qt.addParameter("path22", QueryTemplate.buildRightLike(BaseConstants.TYPE_GROUP+","));
			
			qt.append(" and a.path not like :path31 ");
			qt.addParameter("path31",QueryTemplate.buildAllLike(","+BaseConstants.TYPE_WLH+","));
			qt.append(" and a.path not like :path32 ");
			qt.addParameter("path32", QueryTemplate.buildRightLike(BaseConstants.TYPE_WLH+","));
			
			
			break;
		case 2:
			qt.append(" and ( a.path like :path1 ");
			qt.addParameter("path1",QueryTemplate.buildAllLike(","+BaseConstants.TYPE_GROUP));
			qt.append(" or a.path like :path2 )");
			qt.addParameter("path2", QueryTemplate.buildRightLike(BaseConstants.TYPE_GROUP.toString()));
			break;
		default:
			break;
		}
		//排序
		qt.append(" order by a.order desc");
		List<AssetType> types = findDomains(qt);//获取孙子节点
		types.add(root);//加入根节点
		return types;
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.service.AssetTypeService#removeMe(java.lang.Long)
	 */
	@Override
	@Transactional(readOnly = false)
	public String removeMe(Long pk) {
		
		String typePath = find(pk).getPath();
		String reslInfo = ""; //1:安全域,2:信息系统,3:设备,4:物理环境
		
		//1.查安全域
		List<AssetGroup> agTy = assetGroupDao.findByTypePath(typePath);
		if(agTy != null && agTy.size()>0){
			reslInfo +="AssetGroup,";
		}
		
		//2.查信息系统
		List<InfoSystem> isTy = infoSystemDao.findByTypePath(typePath);
		if(isTy != null && isTy.size()>0){
			reslInfo +="InfoSystem,";
		}
		
		//3.查设备
		List<Device> dvTy = deviceDao.findByTypePath(typePath);
		if(dvTy != null && dvTy.size()>0){
			reslInfo +="Device,";
		}
		
		
		//4.查物理环境
		List<PhyEnvironment> enviTy = enviDao.findByTypePath(pk);
		if(enviTy != null && enviTy.size()>0){
			reslInfo +="PhyEnvironment,";
		}
		
		//如果返回错误的值为空则可以删除
		if(StringUtils.isBlank(reslInfo)){
			dao.remove(pk);
		}
		
		return reslInfo;
	}
}


