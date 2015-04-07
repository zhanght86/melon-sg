/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.SortOrder;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.PaginationBuilder;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.system.dao.LoggerDao;
import secfox.soc.melon.system.domain.Logger;
import secfox.soc.melon.system.query.LoggerPageQuery;
import secfox.soc.melon.system.query.LoggerSearch;
import secfox.soc.melon.system.service.LoggerService;

/**
 *
 * @since 1.0 2014-10-8 下午8:32:05
 * @author <a href="mailto:huangxm@legendsec.com">黄晓梅</a>
 * @version 1.0
 */
@Service
public class LoggerServiceImpl extends GenericServiceImpl<Logger, Long> implements	LoggerService {
	
	private LoggerDao loggerDao;
	
	//注入Dao
	@Inject
	public LoggerServiceImpl(LoggerDao loggerDao){
		this.loggerDao = loggerDao;
	}
	
	@Override
	protected GenericDao<Logger, Long> getDao() {
		return loggerDao;
	}
	
	/**
	 * 分页查询
	 */
	@Override
	public Pagination<Logger> findPage(LoggerPageQuery pageQuery) {
		return findDomainPage(QueryType.JQL, pageQuery, new PaginationBuilder<LoggerSearch, LoggerPageQuery>() {

			@Override
			public void buildSelect(QueryTemplate qt) {
				qt.append("select logger from Logger logger");
			}

			@Override
			public void buildWhere(LoggerSearch s, QueryTemplate qt) {
				//按操作人查询
				String operator = s.getOperator().getUsername();
				if(StringUtils.isNotBlank(operator)){
					qt.append(" and logger.operator.username like :operator ");
					qt.addParameter("operator", QueryTemplate.buildAllLike(operator));
				}
				//按功能查询
				String clazz = s.getClazz();
				if(StringUtils.isNotBlank(clazz)) {
					qt.append(" and logger.clazz like :clazz ");
					qt.addParameter("module", QueryTemplate.buildAllLike(clazz));
				}
				//按具体操作查询
				String function = s.getFunction();
				if(StringUtils.isNotBlank(function)){
					qt.append(" and logger.function like :function ");
					qt.addParameter("function", QueryTemplate.buildAllLike(function));
				}
				//按IP查询
				String ip = s.getIp();
				if(StringUtils.isNotBlank(ip)){
					qt.append(" and logger.ip = :ip " );
					qt.addParameter("ip", ip);
				}
				
				//按操作日期进行查询
				//高级查询的开始时间
				Date start = s.getStart();
				if(start != null){
					qt.append(" and logger.occurTime >= :start ");
					qt.addParameter("start", start);
				}
				//高级查询的结束时间
				Date end = s.getEnd();
				if(end != null){
					Calendar cal = Calendar.getInstance();
					cal.setTime(end);
					cal.add(Calendar.DAY_OF_WEEK, 1);
					qt.append(" and logger.occurTime <= :end ");
					qt.addParameter("end",cal.getTime());
				}
				qt.append(" and logger.operator.username != ' ' ");
			}

			@Override
			public void buildBys(String column, SortOrder order, QueryTemplate qt) {
				if(StringUtils.isBlank(column)) {
					column = "id";
				}
				qt.append(QueryTemplate.buildOrderBy("logger", column, order));
				
			}

			@Override
			public void buildCount(QueryTemplate qt) {
				qt.append("select count(logger) from Logger logger");
			}
			
		});
	}
	/**
	 * 按用户分组查询
	 * @param pageQuery
	 * @return
	 */
	public Pagination<Object[]> findPageByUserName(LoggerPageQuery pageQuery){
		return findArrayPage(QueryType.JQL, pageQuery,new PaginationBuilder<LoggerSearch, LoggerPageQuery>(){

			@Override
			public void buildSelect(QueryTemplate qt) {
				qt.append(" select logger.operator.userId, logger.operator.username, "
						+ "count(id), max(logger.occurTime), min(logger.occurTime) "
						+ "from Logger logger ");
				
			}
			
			/*
			 * (non-Javadoc)
			 * @see secfox.soc.melon.persistence.QueryBuilder#buildWhere(java.lang.Object, secfox.soc.melon.persistence.QueryTemplate)
			 */
			@Override
			public void buildWhere(LoggerSearch s, QueryTemplate qt) {
				//按操作人查询
				String operator = s.getOperator().getUsername();
				if(StringUtils.isNotBlank(operator)){
					qt.append(" and logger.operator.username like :operator ");
					qt.addParameter("operator", QueryTemplate.buildAllLike(operator));
				}
				//查询的开始时间
				Date startTime = s.getStartTime();
				if(startTime != null) {
					qt.append(" and logger.occurTime >= :startTime");
					qt.addParameter("startTime", startTime);
				}
				//查询的结束时间
				Date endTime = s.getEndTime();
				if(endTime != null) {
					qt.append(" and logger.occurTime <= :endTime");
					qt.addParameter("endTime", endTime);
				}
				qt.append(" and logger.operator.username != ' ' ");
			}
			
			/*
			 * (non-Javadoc)
			 * @see secfox.soc.melon.persistence.QueryBuilder#buildBys(java.lang.String, secfox.soc.melon.base.SortOrder, secfox.soc.melon.persistence.QueryTemplate)
			 */
			@Override
			public void buildBys(String column, SortOrder order, QueryTemplate qt) {
				//按用户ID，用户名分组
				qt.append(" group by logger.operator.userId, logger.operator.username");
			}

			@Override
			public void buildCount(QueryTemplate qt) {
			}
			
		});
	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.system.service.LoggerService#findALLFunctionById(java.lang.Long)
	 */
	@Override
	public List<Logger> findAllFunctionByUserId(Long id) {
		LoggerSearch logger = (new LoggerPageQuery()).getSearchForm();
		//在melon-orm.xml配置文件中，写 查询语句
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "logger.findByUserId");
		qt.addParameter("id", id);
		qt.addParameter("startTime", logger.getStartTime());
		qt.addParameter("endTime", logger.getEndTime());
		return findDomains(qt);
	}
	
	/* 
	 * 查找系统当前的活跃用户
	 * (non-Javadoc)
	 * @see secfox.soc.melon.system.service.LoggerService#findActiveUser()
	 */
	@Override
	public Pagination<Object[]> findActiveUser(LoggerPageQuery pageQuery) {
		return findArrayPage(QueryType.JQL, pageQuery,new PaginationBuilder<LoggerSearch, LoggerPageQuery>(){

			@Override
			public void buildSelect(QueryTemplate qt) {
				qt.append("select logger.operator.userId,logger.operator.username,logger.function,max(logger.occurTime) from Logger logger ");
				
			}

			@Override
			public void buildWhere(LoggerSearch s, QueryTemplate qt) {
				//按操作人查询
				String operator = s.getOperator().getUsername();
				if(StringUtils.isNotBlank(operator)){
					qt.append(" and logger.operator.username like :operator ");
					qt.addParameter("operator", QueryTemplate.buildAllLike(operator));
				}
				
				qt.append(" and logger.operator.username != ' ' ");
			}

			@Override
			public void buildBys(String column, SortOrder order,
					QueryTemplate qt) {
				qt.append(" group by logger.operator.userId,logger.operator.username,logger.function");
				qt.append(" order by max(logger.occurTime) desc");
			}

			@Override
			public void buildCount(QueryTemplate qt) {
				qt.append(" select count(logger.operator.username) from Logger logger");
			}

		});
	}
	
}
