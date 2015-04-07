package secfox.soc.melon.event.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Component;

import secfox.soc.melon.event.dao.FluxEventDao;
import secfox.soc.melon.event.service.FluxEventService;

/**
 *
 * @since 1.0 2014年12月6日上午11:02:17
 * @author <a href="mailto:xugfa@legendsec.com">徐广飞</a>
 * @version 1.0
 */
@Component
public class FluxEventServiceImpl implements FluxEventService {

	private FluxEventDao fluxEventDao;

	@Inject
	public FluxEventServiceImpl(FluxEventDao fluxEventDao) {
		super();
		this.fluxEventDao = fluxEventDao;
	}
	
	public List<Object[]> findInAndOutFlux(String ip){
		List<Object[]> list = new ArrayList<Object[]>();
		List<Object[]> inList1 = fluxEventDao.findInFlux("172.16.8.21", null);
		List<Object[]> inList2 = fluxEventDao.findInFlux("172.16.8.22", null);
		List<Object[]> inList3 = fluxEventDao.findInFlux("172.16.8.23", null);
		List<Object[]> inList4 = fluxEventDao.findInFlux("172.16.8.24", null);
//		List<Object[]> outList = fluxEventDao.findOutFlux(ip, null);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		for(int i=0;i<inList1.size();i++){
			Object[] obs= new Object[5];
			obs[0] = dateFormat.format(inList1.get(i)[0]);
			obs[1] = (Long)inList1.get(i)[1];
			obs[2] = (Long)inList2.get(i)[1];
			obs[3] = (Long)inList3.get(i)[1];
			obs[4] = (Long)inList4.get(i)[1];
//			obs[2] = outList.get(i)[1];
			list.add(obs);
		}
		return list;
		
	}
	
}
