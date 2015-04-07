/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import secfox.soc.melon.asset.dao.DeviceFilterDao;
import secfox.soc.melon.asset.domain.DeviceFilter;
import secfox.soc.melon.asset.query.DeviceFilterPageQuery;
import secfox.soc.melon.asset.service.DeviceFilterService;
import secfox.soc.melon.asset.service.DeviceService;
import secfox.soc.melon.base.BaseConstants;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.SortOrder;
import secfox.soc.melon.base.util.MessageSourceUtils;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.PaginationBuilder;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;
import secfox.soc.melon.work.myWork.domain.MyWork;

import com.google.common.collect.Lists;

/**
 *
 * @since 1.0 2014年11月18日下午7:51:41
 * @author <a href="mailto:huangxm@legendsec.com">黄晓梅</a>
 * @version 1.0
 */
@Service
public class DeviceFilterServiceImpl extends
		GenericServiceImpl<DeviceFilter, Long> implements DeviceFilterService {
	
	private DeviceFilterDao deviceFilterDao;
	
	private DeviceService deviceService;
	
	@Inject
	public DeviceFilterServiceImpl(DeviceFilterDao deviceFilterDao, DeviceService deviceService){
		this.deviceFilterDao = deviceFilterDao;
		this.deviceService = deviceService;
	}
	
	@Override
	protected GenericDao<DeviceFilter, Long> getDao() {
		return deviceFilterDao;
	}
	
	@Override
	public Pagination<DeviceFilter> findPages(DeviceFilterPageQuery pageQuery) {

		return findDomainPage(QueryType.JQL, pageQuery,
				new PaginationBuilder<DeviceFilter, DeviceFilterPageQuery>() {

					@Override
					public void buildSelect(QueryTemplate qt) {
						qt.append("select deviceFilter from DeviceFilter deviceFilter ");
					}

					@Override
					public void buildWhere(DeviceFilter s, QueryTemplate qt) {
						// 标题
						if (StringUtils.isNotBlank(s.getOrderName())) {
							qt.append(" and deviceFilter.orderName like :orderName ");
							qt.addParameter("orderName", QueryTemplate.buildAllLike(StringUtils.strip(s.getOrderName())));
						}

					}

					@Override
					public void buildBys(String column, SortOrder order,
							QueryTemplate qt) {
						
					}

					@Override
					public void buildCount(QueryTemplate qt) {
						qt.append("select count(deviceFilter) from DeviceFilter deviceFilter ");
					}
				});

	}
	
	/* (non-Javadoc)
	 * @see secfox.soc.melon.system.service.LoggerService#findALLFunctionById(java.lang.Long)
	 */
	@Override
	public List<DeviceFilter> findList() {
		//在melon-asset-orm.xml配置文件中，写 查询语句
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "asset.findList");
		return findDomains(qt);
	}

	@Override
	public List<MyWork> findTask() {
		
		//在melon-asset-orm.xml配置文件中，写 查询语句
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "asset.findTask");
		
		//处理时间，查找当天到期提醒的任务
		Calendar now = Calendar.getInstance();
		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);
		qt.addParameter("remindTime",now.getTime());
		
		//查找到的当前要提醒的工作任务
		List<Object[]> deviceFilters = find(qt);
		
		//存放生成的工作任务
		List<MyWork> myWorks = Lists.newArrayList();
		//工作标题
		String title = "";
		// 工作来源1内部2外部3提醒
		int resource = 3;
		//工作类型1下发2待办
		int type = 2;
		//执行人id
		String execute = "";
		//组织机构id
		Long companyId = null;
		//组织机构Name
		String companyName = "";
		//截止时间
		Date endTime = null;
		//工作内容
		String content = "";
		//执行链接
		String url = "asset/deviceFilter/home";
		// 完成状态 0:未下发;1:待完成;2:已完成
		int state = 1;
		
		for(Object object[]  : deviceFilters){
			DeviceFilter filter = find(Long.valueOf(String.valueOf(object[0])));
			title = filter.getOrderName();
			Object[] param = {deviceService.findRemindDevice(filter).size()};
			content =  MessageSourceUtils.getMessage("asset.deviceFilter.content", param);
			execute = Long.toString(filter.getCreator().getUserId())+BaseConstants.SPLITER_FLAG;
			companyId = filter.getCreator().getOrganId();
			companyName = filter.getCreator().getOrganName();
			endTime = filter.getRemindTime();
			MyWork work = new MyWork(title,resource,type,execute,companyId,companyName, 
					endTime,content,url,state);
			myWorks.add(work);
		}
		return myWorks;
	}
}

