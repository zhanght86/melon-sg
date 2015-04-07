/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
*/
package secfox.soc.melon.security.dao.impl;

import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericDaoImpl;
import secfox.soc.melon.security.dao.MenuResourceDao;
import secfox.soc.melon.security.domain.MenuResource;
import secfox.soc.melon.security.domain.Role;

/**
 * 
 * @since 1.0 2014年10月4日,下午4:10:19
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0
 */
@Repository
public class MenuResourceDaoImpl extends GenericDaoImpl<MenuResource, Long> implements MenuResourceDao {

	public MenuResourceDaoImpl() {
		super(MenuResource.class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericDaoImpl#persist(secfox.soc.melon.persistence.Identifiable)
	 */
	@Override
	public void persist(MenuResource entity) {
		// 1.保存entity
		super.persist(entity);
		Long parentId = entity.getParentId();
		// 如果父节点不为根节点
		if (parentId != BaseConstants.ROOT_ID) {
			// 2.保存path路径
			MenuResource parent = super.getReference(entity.getParentId());
			entity.setDeep(parent.getDeep() + 1);
			if (parent != null) {
				String parPath = parent.getPath();
				//如果path不为空就说明不是在根节点下建的
				if (StringUtils.isNotBlank(parPath)) {
					entity.setPath(entity.getId() + BaseConstants.SPLITER_FLAG + parPath);
				} else {
					entity.setPath(entity.getId().toString());
				}
			}
		} else {
			// 2.保存path路径
			entity.setPath(entity.getId() + BaseConstants.SPLITER_FLAG + parentId.toString());
		}
		//super.merge(entity);
	}
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericDaoImpl#remove(java.io.Serializable)
	 */
	@Override
	public void remove(Long pk) {
		MenuResource current = getReference(pk);
		//先找到所有的子孙节点
		QueryTemplate childrenQt = QueryTemplate.create(QueryType.NamedQuery, "menuResource.findChildren");
		childrenQt.addParameter("path", QueryTemplate.buildLeftLike(current.getPath()));
		List<MenuResource> resources = findDomains(childrenQt);
		//对节点依次迭代
		for(MenuResource resource : resources) {
			//首先删除关联的角色信息
			Set<Role> roles = resource.getRoles();
			for(Role role : roles) {
				//删除资源
				role.removeResource(resource);
			}
			//删除自身
			super.remove(resource);
		}
	}
	
}
