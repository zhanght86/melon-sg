package secfox.soc.las.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.Maps;
import com.google.common.primitives.Ints;

import secfox.soc.las.dao.AlertDao;
import secfox.soc.las.domain.Alert;
import secfox.soc.las.domain.AlertRule;
import secfox.soc.las.query.AlertPageQuery;
import secfox.soc.las.service.AlertRuleService;
import secfox.soc.las.service.AlertService;
import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.util.DictionaryUtils;

@Service
public class AlertServiceImpl implements AlertService {
	
	private AlertDao dao;
	private AlertRuleService ruleService;
	
	@Inject
	public AlertServiceImpl(AlertDao dao, AlertRuleService ruleService) {
		this.dao = dao;
		this.ruleService = ruleService;
	}

	@Override
	public Pagination<Alert> list(AlertPageQuery pageQuery) {
		StringBuffer countSql = new StringBuffer("select * from t_filter where Parent_id = :parent");
		if(StringUtils.isNotBlank(pageQuery.getSearchForm().getName())) {
			countSql.append(" and Name like :name");
		}
		StringBuffer resultSql = countSql.append(" limit :start, :size order by id");
		Map<String, Object> param = Maps.newHashMap();
		int start = pageQuery.getFirstResult();
		int size = pageQuery.getPageSize();
		param.put("parent", 507);
		param.put("start", start);
		param.put("size", size);
		Pagination<Alert> pagination = new Pagination<Alert>();
		pagination.setCount(dao.findBySql(countSql.toString(), param).size());
		List<Alert> list = dao.findBySql(resultSql.toString(), param);
		List<AlertRule> listRule = ruleService.findAlertRuleList();
		for(Alert obj : list){
			for(AlertRule rule : listRule){
				if(obj.getId().equals(rule.getRuleId())){
					obj.setAction(rule.getTypes());
				}
			}
		}
		pagination.setResults(list);
		pagination.setCurrPage(pageQuery.getCurrPage());
		return pagination;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.las.service.AlertService#pagination(secfox.soc.las.query.AlertPageQuery)
	 */
	@Override
	public Pagination<Alert> pagination(AlertPageQuery pageQuery) {
		//查询语句
		StringBuffer countSql = new StringBuffer("select * from t_rule where 1=1 ");
		StringBuffer resultSql = new StringBuffer("select * from t_rule where 1=1 ");
		if(StringUtils.isNotBlank(pageQuery.getSearchForm().getName())) {
			countSql.append(" and Name like :name");
			resultSql.append(" and Name like :name");
		}
		resultSql.append(" order by id limit :start, :size ");
		Map<String, Object> param = Maps.newHashMap();
		//从pageQuey获取第一条记录
		int start = pageQuery.getFirstResult();
		//从pageQuery获取页面容量
		int size = pageQuery.getPageSize();
		//设置分页参数
		param.put("start", start);
		param.put("size", size);
		Pagination<Alert> pagination = new Pagination<Alert>();
		//设置数量
		pagination.setCount(dao.findBySql(countSql.toString(), param).size());
		//获取查询结果集
		List<Alert> list = dao.findBySql(resultSql.toString(), param);
		//从ums获取告警配置
		List<AlertRule> listRule = ruleService.findAlertRuleList();
		//结果集记录与告警配置比较判断是否ums有记录
		for(Alert obj : list){
			for(AlertRule rule : listRule){
				if(obj.getId().equals(rule.getRuleId())){
					//设置配置结果
					obj.setAction(rule.getTypes());
				}
			}
		}
		pagination.setResults(list);
		pagination.setCurrPage(pageQuery.getCurrPage());
		pagination.setPageSize(pageQuery.getPageSize());
		return pagination;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.las.service.AlertService#find(java.lang.String)
	 */
	@Override
	public AlertRule find(String id) {
		AlertRule rule = ruleService.findByRuleId(id);
		//首次配置
		if(rule == null) {
			String findSql = "select * from t_rule where id=:id";
			Map<String, Object> param = Maps.newHashMap();
			param.put("id", id);
			Alert alert = dao.findBySql(findSql.toString(), param).get(0);
			rule = new AlertRule(alert.getId(), alert.getName());
		} else {
			//从ums获取
			String typeStr = rule.getTypes();
			if(StringUtils.isNotBlank(typeStr)) {
				Iterable<String> typeItems= Splitter.on(BaseConstants.SPLITER_FLAG)
												     .trimResults()
												     .omitEmptyStrings()
												     .split(typeStr);
				//将字符串转换为整数
				rule.setType(Iterables.toArray(Ints.stringConverter().convertAll(typeItems), Integer.class));
			}
		}
		return rule;
	}

}
