package secfox.soc.melon.asset.intergration.service.impl;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import secfox.soc.integration.sni.wsdl.client.service.SniClientService;
import secfox.soc.melon.asset.dao.IpAddressDao;
import secfox.soc.melon.asset.domain.IpAddress;
import secfox.soc.melon.asset.intergration.dao.LasDevicesDao;
import secfox.soc.melon.asset.intergration.domain.LasDevices;
import secfox.soc.melon.asset.intergration.query.LasDevicesPageQuery;
import secfox.soc.melon.asset.intergration.service.LasDevicesService;
import secfox.soc.melon.base.Pagination;
import secfox.soc.melon.base.SortOrder;
import secfox.soc.melon.persistence.GenericDao;
import secfox.soc.melon.persistence.PaginationBuilder;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;
import secfox.soc.melon.persistence.impl.GenericServiceImpl;

/**
 * 同步设备Service实现
 * 
 * @author lif
 * 
 */
@Service
public class LasDevicesServiceImpl extends GenericServiceImpl<LasDevices, Long>
		implements LasDevicesService {

	private LasDevicesDao dao;
	private IpAddressDao ipAddressDao;

	@Inject
	public LasDevicesServiceImpl(IpAddressDao ipAddressDao, LasDevicesDao dao) {
		super();
		this.dao = dao;
		this.ipAddressDao = ipAddressDao;
	}

	@Override
	protected GenericDao<LasDevices, Long> getDao() {
		return dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see secfox.soc.melon.asset.intergration.service.LasDevicesService#
	 * saveAsLasDevices()
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = false)
	public void saveAsLasDevices() {
		SniClientService sn = new SniClientService();
		List<Map<String, Object>> saFl = sn.syncAssetFromLas();// 获取数据源
		// 转换为lasDevices格式
		for (int i = 0; i < saFl.size(); i++) {
			Map<String, Object> map = saFl.get(i);
			Long lasdeviceId = Long.valueOf(String.valueOf(map.get("lasDevicePK")));
			LasDevices findByLasPk = findByLasPk(lasdeviceId);
			// 如果此数据已同步过，则不再同步
			if (findByLasPk != null) {
				continue;
			}
			LasDevices ld = new LasDevices();
			ld.setLasDevicePK(Long.parseLong(String.valueOf(map.get("lasDevicePK"))));
			ld.setModel(String.valueOf(map.get("model")));
			ld.setName(String.valueOf(map.get("name")));
			ld.setProducer(String.valueOf(map.get("producer")));
			ld.setMac(String.valueOf(map.get("mac")));
			ld.setIps((List<IpAddress>) map.get("ipAddrList"));
			dao.persist(ld);// 保存同步设备
			// 关联ip
			if (ld.getIps() != null) {
				ipAddressDao.persist(ld.getIps(), ld.getId()); // 保存ip
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * secfox.soc.melon.asset.intergration.service.LasDevicesService#findPages
	 * (secfox.soc.melon.asset.intergration.query.LasDevicesPageQuery)
	 */
	@Override
	public Pagination<LasDevices> findPages(LasDevicesPageQuery query) {
		return findDomainPage(QueryType.JQL, query,
				new PaginationBuilder<LasDevices, LasDevicesPageQuery>() {

					@Override
					public void buildSelect(QueryTemplate qt) {
						qt.append("select a from LasDevices a ");
					}

					@Override
					public void buildWhere(LasDevices s, QueryTemplate qt) {
						
						//按照name查找
						if(StringUtils.isNotBlank(s.getName())){
							qt.append(" and a.name like :name");
							qt.addParameter("name", QueryTemplate.buildAllLike(s.getName()));
						}
					}

					@Override
					public void buildBys(String column, SortOrder order,
							QueryTemplate qt) {
						if (StringUtils.isBlank(column)) {
							column = "id";
						}
						qt.append(QueryTemplate
								.buildOrderBy("a", column, order));
					}

					@Override
					public void buildCount(QueryTemplate qt) {
						qt.append("select count(a.id) from LasDevices a ");
					}
				});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see secfox.soc.melon.persistence.impl.GenericServiceImpl#find(java.io.
	 * Serializable)
	 */
	@Override
	public LasDevices find(Long pk) {
		LasDevices lasDevices = dao.find(pk);
		// 在IP表中查询设备相关联的的Ip
		List<IpAddress> ipz = ipAddressDao.findByDeviceId(pk);
		lasDevices.setIps(ipz);
		return lasDevices;
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.intergration.service.LasDevicesService#findByLasPk(java.lang.Long)
	 */
	@Override
	public LasDevices findByLasPk(Long lasPk) {
		QueryTemplate qt = QueryTemplate.create(QueryType.NamedQuery,
				"LasDevices.findByLasPk");
		qt.addParameter("lasDevicePK", lasPk);
		return findFirstDomain(qt);
	}

	/*
	 * (non-Javadoc)
	 * @see secfox.soc.melon.asset.intergration.service.LasDevicesService#findAll()
	 */
	@Override
	public List<LasDevices> findAll() {
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL,
				"select a from LasDevices a");
		return findDomains(qt);
	}

}
