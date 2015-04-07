package secfox.soc.melon.alarm.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import secfox.soc.melon.alarm.dao.AlarmTypeDao;
import secfox.soc.melon.alarm.domain.AlarmType;
import secfox.soc.melon.alarm.service.AlarmTypeService;
import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.security.SecurityConstants;

/**
 * @since 1.0 2014-10-21,下午3:23:51
 * 
 * @author  <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0 
 */
@Service
public class AlarmTypeServiceImpl extends GenericServiceImpl<AlarmType, Long>
		implements AlarmTypeService {

	private AlarmTypeDao dao;
	
	@Inject
	public AlarmTypeServiceImpl(AlarmTypeDao dao){
		super();
		this.dao = dao;
	}
	
	@Override
	protected GenericDao<AlarmType, Long> getDao() {
		return dao;
	}

	/**
	 * 创建根节点
	 * @return
	 */
	public AlarmType createRoot() {
		AlarmType  alarm = new AlarmType();
		alarm.setName(MessageSourceUtils.getMessage("alarm.type.root"));
		alarm.setParentId(null);
		alarm.setId(BaseConstants.ROOT_ID);
		alarm.setOrder(0);
		alarm.getState().setOpened(true);
		return alarm;
	}
	
	@Override
	public List<AlarmType> findTree(Long rootId, boolean choosen) {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select a from AlarmType a where 1=1 ");
		AlarmType alarm = new AlarmType();
		String path = String.valueOf(BaseConstants.ROOT_ID);
		//如果是默认节点
		if(rootId==BaseConstants.ROOT_ID){
			alarm = createRoot();
		} else {
			alarm = find(rootId); //根据id获取
			path = alarm.getPath();
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
		List<AlarmType> types = findDomains(qt);//获取孙子节点
		//如果包含根节点
		if(types.contains(alarm)){ 
			AlarmType alarmRoot = types.get(types.indexOf(alarm)); //获取这个对象的位置
			alarmRoot.setAsRoot(true); //设置为根节点
			alarmRoot.getState().setOpened(true); //默认打开节点
		}else{
			//不包含,就加进去
			types.add(alarm);
		}
		return types;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.alarm.serivce.AlarmTypeService#findAll()
	 */
	@Override
	public List<AlarmType> findAll() {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"alarm.findAll");
		return findDomains(qt);
	}
	
	@Override
	public List<AlarmType> findByParentId(Long parentId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"alarm.findCountByParentId");
		qt.addParameter("parentId", parentId);
		return findDomains(qt);
	}

	
}

