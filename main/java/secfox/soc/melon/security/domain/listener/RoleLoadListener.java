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
public class RoleLoadListener extends DomainListenerAdapter<Role> {
	
	/**
	 * 载入岗位完成后,将levels转换为level
	 */
	@PostLoad
	public void afterLoad(Role role) {
		//获取到岗位的适用范围
		String levelStr = role.getLevels();
		if(StringUtils.isNotBlank(levelStr)) {
			Iterable<String> levelItems= Splitter.on(BaseConstants.SPLITER_FLAG)
											     .trimResults()
											     .omitEmptyStrings()
											     .split(levelStr);
			//将字符串转换为整数
			role.setLevel(Iterables.toArray(Ints.stringConverter().convertAll(levelItems), Integer.class));
		}
	}

}
