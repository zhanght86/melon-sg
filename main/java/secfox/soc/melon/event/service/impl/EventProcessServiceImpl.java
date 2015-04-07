package secfox.soc.melon.event.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import secfox.soc.melon.event.dao.EventJdbcDao;
import secfox.soc.melon.event.service.EventProcessService;

/**
 *
 * @since 1.0 2014年12月4日下午2:11:45
 * @author <a href="mailto:xugfa@legendsec.com">徐广飞</a>
 * @version 1.0
 */
@Component
public class EventProcessServiceImpl implements EventProcessService{

	private EventJdbcDao eventJdbcDao;

	@Inject
	public EventProcessServiceImpl(EventJdbcDao eventJdbcDao) {
		super();
		this.eventJdbcDao = eventJdbcDao;
	}
	
	public List<Map<String,Object>> findSourceAdd(String ip){
		StringBuffer buffer = new StringBuffer("ed_");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		buffer.append(dateFormat.format(new Date()));
		List<Map<String,Object>> list = eventJdbcDao.findSourceAdd(ip,buffer.toString(), null);
		list.addAll(eventJdbcDao.findTargetAdd(ip,buffer.toString(), null));
		return list;
	}
	
	public List<Map<String,Object>> findTarAndsourAdd(String ip){
		StringBuffer buffer = new StringBuffer("ed_");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
		buffer.append(dateFormat.format(new Date()));
		return eventJdbcDao.findTarAndsourAdd(ip,buffer.toString(), null);
	}
}
