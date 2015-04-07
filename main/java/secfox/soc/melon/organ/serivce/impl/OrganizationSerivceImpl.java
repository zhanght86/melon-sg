/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.organ.serivce.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.SortOrder;
import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.organ.OrganType;
import secfox.soc.melon.organ.dao.OrganDao;
import secfox.soc.melon.organ.domain.Organization;
import secfox.soc.melon.organ.query.OrganizationPageQuery;
import secfox.soc.melon.organ.serivce.OrganizationService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.JqlQueryTemplate;
import secfox.soc.melon.persistence.PaginationBuilder;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.persistence.templates.QueryTemplateManager;

import com.google.common.collect.Lists;

/**
 * @since 2014-9-17,下午7:44:35
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Service
public class OrganizationSerivceImpl extends GenericServiceImpl<Organization, Long> implements OrganizationService{
	
	private OrganDao dao;
	
	@Inject
	public OrganizationSerivceImpl(OrganDao dao){
		super();
		this.dao = dao;
	}
	
	@Override
	protected GenericDao<Organization, Long> getDao() {
		return dao;
	}
	

	/* (non-Javadoc)
	 * @see secfox.soc.melon.organ.serivce.OrganizationService#create(java.lang.String, java.lang.String)
	 */
	@Override
	public Organization create(String parentId, String type) {
		Organization organ = new Organization();
		try {
			organ.setParentId(Long.parseLong(parentId));
			organ.setType(Integer.parseInt(type));
		} catch(NumberFormatException ex) {
			organ.setParentId(BaseConstants.ROOT_ID);//默认父节点为根节点
			organ.setType(OrganType.GROUP.ordinal());//设置类型为行政区域
		}
		return organ;
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.organ.serivce.OrganizationService#createRoot()
	 */
	@Override
	public Organization createRoot() {
		Organization organ = new Organization();
		//设置虚拟根节点
		organ.setName(MessageSourceUtils.getMessage("organ.organization.root"));
		organ.setParentId(null);
		organ.setId(BaseConstants.ROOT_ID);//设置为根节点
		organ.setLevel(0);//设置为顶部节点
		organ.setType(OrganType.GROUP.ordinal());//设置为行政区域
		organ.getState().setOpened(true);//默认为打开
		return organ;
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.organ.serivce.OrganizationService#findTree(java.lang.Long, boolean, boolean)
	 */
	@Override
	@Transactional(readOnly=true)
	public List<Organization> findTree(Long rootId, boolean includeDepartment,	boolean full) {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select organ from Organization organ where 1=1 ");
		List<Integer> types = Lists.newArrayList(0, 1);//默认包含行政区域与单位
		Organization root = createRoot();
		String path = String.valueOf(BaseConstants.ROOT_ID);
		//添加部门
		if(includeDepartment) {
			types.add(2);//添加部门
		}
		//如果根节点是默认节点
		if(rootId == BaseConstants.ROOT_ID) {
			//如果不是全部组织机构类型
			if(!full) {
				qt.append(" and organ.level in (0,1) ");//总部只解析省与总部
			}
		} else {
			if(!full) {
				qt.append(" and organ.level in (1,2) ");//省只解析省与地市
			}
			root = find(rootId);//从数据库中获得根节点
			path = root.getPath();
		}
		//查询其下的所有子孙节点
		qt.append(" and organ.path like :path");
		qt.addParameter("path", QueryTemplate.buildLeftLike(path));
		//查询相关的类型
		qt.appendIn(" and organ.type ", types);//拼接in语句查询
		qt.append(" order by organ.order desc");
		//添加根节点
		List<Organization> organs = findDomains(qt);
		if(organs.contains(root)) {
			Organization organRoot = organs.get(organs.indexOf(root));
			organRoot.setAsRoot(true);//设置根节点
			organRoot.getState().setOpened(true);//默认展开此节点
		} else {
			organs.add(root);
		}
		return organs;
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.organ.serivce.OrganizationService#findCompany()
	 */
	@Override
	@Transactional(readOnly=false)
	public void remove(Long id) {
		//查找子节点及本身
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select organ from Organization organ where organ.path like :path ");
		qt.addParameter("path", QueryTemplate.buildAllLike(String.valueOf(id)));
		List<Organization> organs = findDomains(qt);
		removeBatch(organs);
		
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.organ.serivce.OrganizationService#findOrgans(int, java.lang.Long)
	 */
	@Override
	public List<Organization> findOrgans(int type, Long parentId) {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select organ from Organization organ where organ.path like :path and organ.type = :type order by organ.id ");
		qt.addParameter("path", QueryTemplate.buildAllLike(String.valueOf(parentId)));
		qt.addParameter("type", type);
		return findDomains(qt);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.organ.serivce.OrganizationService#findPages(secfox.soc.melon.organ.query.OrganizationPageQuery)
	 */
	@Override
	public Pagination<Organization> findPages(OrganizationPageQuery pageQuery) {
		return findDomainPage(QueryType.JQL,pageQuery,new PaginationBuilder<Organization, OrganizationPageQuery>() {

			@Override
			public void buildSelect(QueryTemplate qt) {
				qt.append("select a from Organization a ");
			}

			@Override
			public void buildWhere(Organization s, QueryTemplate qt) {
				//TODO
				if(StringUtils.isNotBlank(s.getName())){
					qt.append(" and a.name like :name");
					qt.addParameter("name", QueryTemplate.buildAllLike(StringUtils.strip(s.getName())));
				}
			}

			@Override
			public void buildBys(String column, SortOrder order,
					QueryTemplate qt) {
				if(StringUtils.isBlank(column)) {
					column = "id";
				}
				qt.append(QueryTemplate.buildOrderBy("a", column, order));
			}

			@Override
			public void buildCount(QueryTemplate qt) {
				qt.append("select count(a.id) from Organization a ");
			}
		});
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.organ.serivce.OrganizationService#findByUsername(java.lang.String)
	 */
	@Override
	public boolean findByName(String name, String id, String type) {
		//部门不需要唯一性验证
		if(type.equals("2")) {
			return true;
		}else {
			QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select organ from Organization organ where organ.name = :name ");
	        qt.addParameter("name", name);
	        Organization cur = findFirstDomain(qt);
			if(StringUtils.isBlank(id)) {
		        return cur == null;
			}else {
				Organization pre = find(Long.valueOf(id));
				if(cur != null) {
					if(cur.getName() == pre.getName()) {
						return true;
					}else {
						return false;
					}
				}else {
					return true;
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.organ.serivce.OrganizationService#findByCode(java.lang.String)
	 */
	@Override
	public boolean findByCode(String code, String id) {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select organ from Organization organ where organ.code = :code ");
        qt.addParameter("code", code);
        Organization cur = findFirstDomain(qt);
        if(StringUtils.isBlank(id)) {
	        return cur == null;
		}else {
			Organization pre = find(Long.valueOf(id));
			if(cur != null) {
				if(cur.getCode().equals(pre.getCode())) {
					return true;
				}else {
					return false;
				}
			}else {
				return true;
			}
		}
	}


	/* (non-Javadoc)
	 * @see secfox.soc.melon.organ.serivce.OrganizationService#countAcc()
	 */
	@Override
	public List<Organization> countAcc() {
		//分组统计单位人数
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "organization.count");
		List<Object[]> result = find(qt);
		List<Organization> list = Lists.newArrayList();
		for(Object[] organ : result) {
			Organization organization = new Organization();
			organization.setId(Long.valueOf(String.valueOf(organ[0])));
			organization.setName(String.valueOf(organ[1]));
			organization.setPrincipal(String.valueOf(organ[2]));
			organization.setPrinTel(String.valueOf(organ[3]));
			organization.setCount(Integer.parseInt(String.valueOf(organ[4])));
			list.add(organization);
		}
		return list;
	}
	
	/**
	 * 获取直属单位、省级单位的数量
	 */
	@Override
	public List<Object[]> getProvinceCount() {
		QueryTemplate qt = QueryTemplateManager.find("organization.groupBylevel");
		return find(qt);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.organ.serivce.OrganizationService#findByName(java.lang.String)
	 */
	@Override
	public Organization findByName(String name) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "organization.findByName");
		qt.addParameter("name", StringUtils.trim(name));
		return findFirstDomain(qt);
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.organ.serivce.OrganizationService#getSubOrgan(java.lang.Long, boolean)
	 */
	@Override
	public List<Organization> getSubOrgan(Long organId, boolean selfOrgan) {
		//获取单位
		List<Organization> result = Lists.newArrayList();
		Organization organ = find(organId);
		if(organ != null) {
			Long provinceId = organ.getParentId();//获取单位所属省份
			//查询节点
			QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select organ from Organization organ where organ.path like :path and organ.type = 1");
			qt.addParameter("path", QueryTemplate.buildAllLike(String.valueOf(provinceId)));
			result = findDomains(qt);
			if(!selfOrgan) {
				result.remove(organ);
				return result;
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.organ.serivce.OrganizationService#getSubOrgan(java.lang.Long)
	 */
	@Override
	public List<Organization> getSubOrgan(Long organId) {
		//获取单位
		List<Organization> result = Lists.newArrayList();
		Organization organ = find(organId);
		if(organ != null) {
			Long provinceId = organ.getParentId();//获取单位所属省份
			Organization province = find(provinceId);
			//查询节点
			QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select organ from Organization organ where organ.path like :path and organ.type != 2");
			if(province == null) {
				//上级节点为根节点 1l
				qt.addParameter("path", QueryTemplate.buildAllLike(String.valueOf(provinceId)));
				result = findDomains(qt);
				Organization root = createRoot();
				result.add(root);
			} else {
				qt.addParameter("path", QueryTemplate.buildAllLike(String.valueOf(provinceId)));
				result = findDomains(qt);
				if(result.contains(province)) {
					int index = result.indexOf(province);
					result.get(index).setAsRoot(true);//设为根节点
					result.get(index).getState().setOpened(true);//默认展开此节点
				}

			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.organ.serivce.OrganizationService#customTree(java.lang.Long, int)
	 */
	@Override
	public List<Organization> customTree(Long rootId, int type) {
		QueryTemplate qt = new JqlQueryTemplate();
		Organization root = createRoot();
		if(rootId.longValue() != BaseConstants.ROOT_ID.longValue()) {
			root = find(rootId);
		}
		switch(type) {
		case 0:
			qt.setQuery("select organ from Organization organ where organ.path like :path and organ.type = 0");
			break;
		case 1:
			qt.setQuery("select organ from Organization organ where organ.path like :path and organ.type in (0,1)");
			break;
		case 2:
			qt.setQuery("select organ from Organization organ where organ.path like :path");
			break;
		default:
			qt.setQuery("select organ from Organization organ where organ.path like :path");
			break;
		}
		qt.addParameter("path", QueryTemplate.buildAllLike(String.valueOf(rootId)));
		List<Organization> result = findDomains(qt);
		if(result.contains(root)) {
			Organization organRoot = result.get(result.indexOf(root));
			organRoot.setAsRoot(true);//设置根节点
			organRoot.getState().setOpened(true);//默认展开此节点
		}else{
			result.add(root);
		}
		return result;
	}
		
}
