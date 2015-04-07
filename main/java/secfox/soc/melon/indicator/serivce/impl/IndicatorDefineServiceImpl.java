package secfox.soc.melon.indicator.serivce.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.indicator.dao.IndicatorDefineDao;
import secfox.soc.melon.indicator.domain.IndicatorDefine;
import secfox.soc.melon.indicator.serivce.IndicatorDefineService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.security.SecurityConstants;

/**
 * @since 1.0 2014-10-15,下午3:38:59
 * 
 * @author  <a href="mailto:liubing@legendsec.com">刘冰</a>
 * @version  1.0 
 */
@Service
public class IndicatorDefineServiceImpl extends	GenericServiceImpl<IndicatorDefine, Long> implements
		IndicatorDefineService {

		private IndicatorDefineDao dao;
			
			@Inject
			public IndicatorDefineServiceImpl(IndicatorDefineDao dao){
				super();
				this.dao = dao;
			}
			
			@Override
			protected GenericDao<IndicatorDefine, Long> getDao() {
				return dao;
			}
	
			/**
			 * 创建根节点
			 * @return
			 */
			public IndicatorDefine createRoot() {
				IndicatorDefine  indicator = new IndicatorDefine();
				indicator.setName(MessageSourceUtils.getMessage("indicator.defined.root"));
				indicator.setParentId(null);
				indicator.setId(BaseConstants.ROOT_ID);
				indicator.setOrder(0);
				indicator.getState().setOpened(true);
				return indicator;
			}
			
			@Override
			public List<IndicatorDefine> findTree(Long rootId, boolean choosen) {
				QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select a from IndicatorDefine a where 1=1 ");
				IndicatorDefine indicator = new IndicatorDefine();
				String path = String.valueOf(BaseConstants.ROOT_ID);
				//如果是默认节点
				if(rootId==BaseConstants.ROOT_ID){
					indicator = createRoot();
				} else {
					indicator = find(rootId); //根据id获取
					path = indicator.getPath();
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
				List<IndicatorDefine> types = findDomains(qt);//获取孙子节点
				//如果包含根节点
				if(types.contains(indicator)){ 
					IndicatorDefine indiRoot = types.get(types.indexOf(indicator)); //获取这个对象的位置
					indiRoot.setAsRoot(true); //设置为根节点
					indiRoot.getState().setOpened(true); //默认打开节点
				}else{
					//不包含,就加进去
					types.add(indicator);
				}
				return types;
			}

			/* (non-Javadoc)
			 * @see secfox.soc.melon.indicator.serivce.IndicatorDefineService#findAll()
			 */
			@Override
			public List<IndicatorDefine> findAll() {
				QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"indicator.findAll");
				return findDomains(qt);
			}
			
			@Override
			public List<IndicatorDefine> findByParentId(Long parentId) {
				QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,"indicator.findCountByParentId");
				qt.addParameter("parentId", parentId);
				return findDomains(qt);
			}

}

