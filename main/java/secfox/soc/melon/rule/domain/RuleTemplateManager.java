package secfox.soc.melon.rule.domain;

import java.io.IOException;
import java.util.Date;

import org.springframework.core.io.Resource;
import com.google.common.collect.ImmutableMap.Builder;

import secfox.soc.melon.base.template.AbstractTemplateManager;
import secfox.soc.melon.rule.service.EplValueService;

public class RuleTemplateManager extends AbstractTemplateManager<Rule> {
	
	private EplValueService eplService;
	
	@Override
	protected void addToCache(Builder<String, Rule> builder, Resource resource) {
		Rule rule;
		try {
			rule = (Rule)getMarshaller().getXStream().fromXML(resource.getInputStream());
			//从数据库查询xml文件
			EplValue value = eplService.findByName(rule.getName());
			//将模板配置文件转存到数据库
			if(value == null) {
				EplValue epl = new EplValue(rule.getName(), getMarshaller().getXStream().toXML(rule), new Date(), true);
				eplService.persist(epl);
			}
		} catch (IOException e) {
			//出错的文件将被忽略
			e.printStackTrace();
		}
		
	}

	public EplValueService getEplService() {
		return eplService;
	}

	public void setEplService(EplValueService eplService) {
		this.eplService = eplService;
	}
	
}
