/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.workflow.service.impl;


import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.SortOrder;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.PaginationBuilder;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.workflow.dao.BpmProcessDao;
import secfox.soc.melon.workflow.domain.BpmProcess;
import secfox.soc.melon.workflow.query.BpmProcessPageQuery;
import secfox.soc.melon.workflow.service.BpmProcessService;

import com.google.common.collect.Maps;

/**
 * 帐户管理服务实现类
 * @since 2014-9-23,上午19:34:09
 * @author <a href="mailto:liubing@legendsec.com>刘冰</a>
 * @version  1.0
 */
@Service
public class BpmProcessServiceImpl extends GenericServiceImpl<BpmProcess, Long> implements BpmProcessService {

	private BpmProcessDao dao;
	
	
	@Inject
	public BpmProcessServiceImpl(BpmProcessDao dao){
		super();
		this.dao = dao;
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<BpmProcess, Long> getDao() {
		return dao;
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.security.service.AccountService#findPage(secfox.soc.melon.security.query.AccountPageQuery)
	 */
	@Override
	public Pagination<BpmProcess> findPage(BpmProcessPageQuery pageQuery) {
		return findDomainPage(QueryType.JQL, pageQuery, new PaginationBuilder<BpmProcess, BpmProcessPageQuery>() {

			/*
			 * (non-Javadoc)
			 * @see secfox.soc.melon.persistence.QueryBuilder#buildSelect(secfox.soc.melon.persistence.QueryTemplate)
			 */
			@Override
			public void buildSelect(QueryTemplate qt) {
				qt.append("select bpm from BpmProcess bpm");
			}
			
			/*
			 * (non-Javadoc)
			 * @see secfox.soc.melon.persistence.QueryBuilder#buildWhere(java.lang.Object, secfox.soc.melon.persistence.QueryTemplate)
			 */
			@Override
			public void buildWhere(BpmProcess s, QueryTemplate qt) {
				//按用户名查询
				String name = s.getName();
				if(StringUtils.isNotBlank(name)) {
					qt.append(" and bpm.name like :name ");
					qt.addParameter("name", QueryTemplate.buildAllLike(StringUtils.trim(name)));
				}
			
			}
			
		
			@Override
			public void buildBys(String column, SortOrder order, QueryTemplate qt) {
				//默认按序数进行排序
				if(StringUtils.isBlank(column) || StringUtils.equals(column, "id")) {
					column = "id";
					order = SortOrder.asc;
				}
				qt.append(QueryTemplate.buildOrderBy("bpm", column, order));
			}

			@Override
			public void buildCount(QueryTemplate qt) {
				qt.append("select count(bpm) from BpmProcess bpm");
				
			}
		});
	}
	
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.workflow.service.BpmProcessService#update(secfox.soc.melon.workflow.domain.BpmProcess, javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public BpmProcess drawProps(BpmProcess bpm,HttpServletRequest request){ 
	        //获取附加属性的key值
			String keys[] = request.getParameterValues("propKey");
			//获取附件属性的value值
			String values[] =  request.getParameterValues("propValue");
			Map<String, String> props = Maps.newHashMap();
			//判断是添加附加属性
			if(keys!=null){
				for(int i = 0; i<keys.length; i++){
					//如果key值是否为空
					if(StringUtils.isNotBlank(keys[i])){
						props.put(keys[i], values[i]);
					}
				}
			}
			bpm.setProps(props);
			return bpm;
	}
}
