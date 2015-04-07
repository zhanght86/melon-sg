/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
/**
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.datas.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.SortOrder;
import secfox.soc.melon.datas.dao.DataShareLoggerDao;
import secfox.soc.melon.datas.domain.DataShareLogger;
import secfox.soc.melon.datas.query.DataShareLoggerPageQuery;
import secfox.soc.melon.datas.query.DataShareLoggerSearch;
import secfox.soc.melon.datas.service.DataShareLoggerService;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.PaginationBuilder;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;

import com.google.common.collect.Lists;

/**
 *
 * @since 1.0 2014-11-10
 * @author <a href="mailto:huangxm@legendsec.com">黄晓梅</a>
 * @version 1.0
 */
@Service
public class DataShareLoggerServiceImpl extends
		GenericServiceImpl<DataShareLogger, Long> implements
		DataShareLoggerService {
	
	//注入Dao
	private DataShareLoggerDao dataShareLoggerDao;
	
	@Inject
	public DataShareLoggerServiceImpl(DataShareLoggerDao dataShareLoggerDao){
		this.dataShareLoggerDao = dataShareLoggerDao;
	}
	/* (non-Javadoc)
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<DataShareLogger, Long> getDao() {
		return dataShareLoggerDao;
	}
	
	/**
	 * 查出各单位所对应的业务类型的数量
	 * @return
	 */
	public List<Object[]> queryDataByOrgan(){
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery, "datas.shareDatas");
		return find(qt);
	}
	
	/**
	 * 数据处理，最终处理后的数据形式为{单位名称，数量，数量，数量，数量，数量，数量，数量，数量}
	 */
	public List<Object[]> shareDatas() {
		List<Object[]> result = Lists.newArrayList();
		//Integer[] type = {0,1,2,10,20,30,40,50};
		//数据
		List<Object[]> datas = queryDataByOrgan();
		for(Object[] data : datas){
			int tmp = exits(result, String.valueOf(data[1]));
			if(tmp!=-1) {
				Object[] b = result.get(tmp);
				setData(data, b);
		} else {
			Object[] o = {data[0],data[1], 0, 0, 0, 0, 0, 0, 0, 0};
			setData(data, o);
			result.add(o);
		}
		
		}
		return result;
	}
	
	/**
	 * 判断单位名称是否已经存在
	 * @param list 要判断的集合
	 * @param name 单位名称
	 * @return 数据所在位置
	 */
	protected int exits(List<Object[]> list, String name) {
		for(int i =0;i<list.size();i++) {
			if(name.equals(list.get(i)[1])) {
				return i;
			}
		}
		return -1;
	}
	
	/**
	 * 将数据设置到集合中
	 * @param data 获取到数据的count(type)值
	 * @param o 将count(type)值保存到该数组中
	 */
	protected void setData(Object[] data, Object[] o) {
		
		switch(Integer.parseInt(String.valueOf(data[2]))) {
		case 0 :
			o[1] = data[3];
			break;
		case 1 :
			o[2] = data[3];
			break;
		case 2 :
			o[3] = data[3];
			break;
		case 10 :
			o[4] = data[3];
			break;
		case 20 :
			o[5] = data[3];
			break;
		case 30 :
			o[6] = data[3];
			break;
		case 40 :
			o[7] = data[3];
			break;
		case 50 :
			o[8] = data[3];
			break;
			
		}
	}
	/* (non-Javadoc)
	 * @see secfox.soc.melon.datas.service.DataShareLoggerService#findPage(secfox.soc.melon.datas.query.DataShareLoggerPageQuery)
	 */
	@Override
	public Pagination<DataShareLogger> findPage(
			DataShareLoggerPageQuery pageQuery) {
		return this.findDomainPage(QueryType.JQL, pageQuery, new PaginationBuilder<DataShareLoggerSearch, DataShareLoggerPageQuery>() {

			@Override
			public void buildSelect(QueryTemplate qt) {
				qt.append("select dataShareLogger from DataShareLogger dataShareLogger where");
				
			}

			@Override
			public void buildWhere(DataShareLoggerSearch s, QueryTemplate qt) {
				qt.append(" dataShareLogger.organId = :organId");
				qt.addParameter("organId", s.getOrganId());
				qt.append(" and dataShareLogger.type = :type");
				qt.addParameter("type", s.getType());
				
			}

			@Override
			public void buildBys(String column, SortOrder order,
					QueryTemplate qt) {
					qt.append(" order by dataShareLogger.id");
			}

			@Override
			public void buildCount(QueryTemplate qt) {
				qt.append("select count(dataShareLogger) from DataShareLogger dataShareLogger");
			}
		});
	}
	
}
