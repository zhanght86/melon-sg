package secfox.soc.melon.knowledge.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.knowledge.dao.KnowledgeTypeDao;
import secfox.soc.melon.knowledge.domain.KnowledgeType;
import secfox.soc.melon.knowledge.service.KnowledgeTypeService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.security.SecurityConstants;

/**
 * @since 1.0 2014-10-28
 * @author  <a href="mailto:fengxy@legendsec.com">冯夏彦</a>
 * @version  1.0 
 */
@Service
public class KnowledgeTypeServiceImpl extends GenericServiceImpl<KnowledgeType, Long>
		implements KnowledgeTypeService {

	private KnowledgeTypeDao dao;
	
	@Inject
	public KnowledgeTypeServiceImpl(KnowledgeTypeDao dao){
		super();
		this.dao = dao;
	}
	
	@Override
	protected GenericDao<KnowledgeType, Long> getDao() {
		return dao;
	}

	/**
	 * 创建根节点
	 * @return
	 */
	public KnowledgeType createRoot() {
		KnowledgeType  knowledgeType = new KnowledgeType();
		knowledgeType.setTitle(MessageSourceUtils.getMessage("knowledge.type.root"));
		knowledgeType.setParentId(null);
		knowledgeType.setId(BaseConstants.ROOT_ID);
		knowledgeType.setOrder(0);
		knowledgeType.getState().setOpened(true);
		return knowledgeType;
	}
	
	@Override
	public List<KnowledgeType> findTree(Long rootId, boolean choosen) {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select a from KnowledgeType a where 1=1 ");
		KnowledgeType knowledgeType = new KnowledgeType();
		String path = String.valueOf(BaseConstants.ROOT_ID);
		//如果是默认节点
		if(rootId==BaseConstants.ROOT_ID){
			knowledgeType = createRoot();
		} else {
			knowledgeType = find(rootId); //根据id获取
			path = knowledgeType.getPath();
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
		List<KnowledgeType> types = findDomains(qt);//获取孙子节点
		//如果包含根节点
		if(types.contains(knowledgeType)){ 
			KnowledgeType knowledgeTypeRoot = types.get(types.indexOf(knowledgeType)); //获取这个对象的位置
			knowledgeTypeRoot.setAsRoot(true); //设置为根节点
			knowledgeTypeRoot.getState().setOpened(true); //默认打开节点
		}else{
			//不包含,就加进去
			types.add(knowledgeType);
		}
		return types;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.alarm.serivce.AlarmTypeService#findAll()
	 */
	@Override
	public List<KnowledgeType> findAll() {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"knowledgeType.findAll");
		return findDomains(qt);
	}
	
	@Override
	public List<KnowledgeType> findByParentId(Long parentId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"knowledgeType.findCountByParentId");
		qt.addParameter("parentId", parentId);
		return findDomains(qt);
	}

}

