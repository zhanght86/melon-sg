/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.melon.security.domain.listener;

import javax.persistence.PostLoad;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.apache.commons.lang3.StringUtils;

import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.persistence.DomainListenerAdapter;
import secfox.soc.melon.security.domain.Role;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.primitives.Ints;

/**
 * 监听器用于协助对象属性的补全与解析,切勿进行数据访问操作,如添加\删除\更新与查询操作
 * @since 2014-9-18,上午11:22:46
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
public class RoleUpdateListener extends DomainListenerAdapter<Role> {
	
	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.persistence.DomainListenerAdapter#preUpdate(java.lang.Object)
	 */
	@PreUpdate
	public void preUpdate(Role role) {
		//将适用范围转换为字符串,然后存储到数据库
		Integer[] level2 = role.getLevel();
		if(level2 != null) {
			role.setLevels(Joiner.on(BaseConstants.SPLITER_FLAG).skipNulls().join(level2));
		}
	}

}
