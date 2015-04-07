/*
  * (c) Copyright 2013 网神信息技术（北京）股份有限公司
  * http://www.legendsec.com
 */
package secfox.soc.las.listener;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.persistence.PostLoad;

import org.springframework.stereotype.Component;

import secfox.soc.las.domain.RuleCondition;
import secfox.soc.las.service.RuleConditionService;
import secfox.soc.melon.base.PageQuery;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.persistence.DomainListenerAdapter;
import secfox.soc.melon.persistence.PaginationBuilder;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;

/**
 * @since 2015-2-3,上午11:02:38
 * @author <a href="mailto:zhangdi@legendsec.com>张棣</a>
 * @version  1.0
 */
@Component
public class RuleConditionListener extends DomainListenerAdapter<RuleCondition> {
	
	private RuleConditionService service;
	
	
	
	/**
	 * @return the service
	 */
	public RuleConditionService getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */
	@Inject
	public void setService(RuleConditionService service) {
		this.service = service;
	}

	@PostLoad
	public void afterLoad(RuleCondition condition) {
		Long id = condition.getId();
		if(condition.getConditionType() == 0) {
			condition.setContent(service.assemContent(id));
		}
	}
}
