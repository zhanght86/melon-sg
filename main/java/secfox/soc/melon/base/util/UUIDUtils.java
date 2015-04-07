/*
 * (c) Copyright 2012 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base.util;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.id.UUIDGenerator;
import org.hibernate.internal.SessionImpl;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


/**
 * @since 0.1 2013-3-13,下午9:03:57
 * UUID随机数生成器
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  0.1 
 */
@Component
public class UUIDUtils {

	@PersistenceContext
	private EntityManager entityManager;
	
	private UUIDGenerator generator;
	
	public UUIDUtils() {
		generator = UUIDGenerator.buildSessionFactoryUniqueIdentifierGenerator();
	}
	
	/**
	 * 根据业务对象创建随机的UUID字符串
	 * @param obj 业务对象
	 * @return 随机的UUID字符串
	 */
	@Transactional(readOnly=true)
	public String generate(Object obj) {
		SessionImpl session = entityManager.unwrap(SessionImpl.class);
		return (String)generator.generate(session, obj);
	}
	
	/**
	 * 创建UUID字符串
	 * @param obj
	 * @return
	 */
	public static String createUUID(Object obj) {
	    return ApplicationContextUtils.getBean(UUIDUtils.class).generate(obj);
	}
}
