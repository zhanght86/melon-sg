/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.system.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.xml.transform.stream.StreamSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.oxm.XmlMappingException;
import org.springframework.oxm.xstream.XStreamMarshaller;

import secfox.soc.melon.base.AuditConfig;
import secfox.soc.melon.base.AuditConfig.AuditClass;
import secfox.soc.melon.base.AuditConfig.AuditItem;
import secfox.soc.melon.system.service.AuditConfigService;

/** 
 * 获取melon-autdit.xml文件配置的操作日志信息
 * @since 1.0 2014年10月10日,上午10:22:47
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class AuditConfigServiceImpl implements AuditConfigService {
	
	private AuditConfig auditConfig;
	
	private final Logger logger = LoggerFactory.getLogger(LoggerServiceImpl.class);
	
	/**
	 * 
	 * @param resource
	 * @param marshaller
	 */
	public AuditConfigServiceImpl(List<Resource> resources, XStreamMarshaller marshaller) {
		boolean failure = false;
		Exception ex = null;
		try {
			if(resources.size() > 0) {
				for(Resource resource : resources) {
					if(this.auditConfig == null) {
						//获取全局配置
						this.auditConfig = (AuditConfig) marshaller.unmarshal(new StreamSource(new FileInputStream(resource.getFile())));
					} else {
						AuditConfig config = (AuditConfig) marshaller.unmarshal(new StreamSource(new FileInputStream(resource.getFile())));
						this.auditConfig.addAuditConfig(config);
					}
				}
				auditConfig.init();
			}
		} catch (XmlMappingException e) {
			failure = true;
			e.printStackTrace();
			ex = e;
		} catch (FileNotFoundException e) {
			failure = true;
			ex = e;
			e.printStackTrace();
		} catch (IOException e) {
			failure = true;
			ex = e;
			e.printStackTrace();
		}
		//如果启动失败,则创建空的
		if(failure) {
			this.auditConfig = new AuditConfig();
			logger.warn("System Logger Service Start Failed, {}", ex);
		} else {
			logger.warn("System Logger Service Start SUCCESS!");
		}
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.system.service.AuditConfigService#get()
	 */
	@Override
	public AuditConfig get() {
		return auditConfig;
	}

	/* (non-Javadoc)
	 * @see secfox.soc.melon.system.service.AuditConfigService#enhance(secfox.soc.melon.system.domain.Logger)
	 */
	@Override
	public secfox.soc.melon.system.domain.Logger enhance(secfox.soc.melon.system.domain.Logger logger) {
		String clazz = logger.getClazz();
		String method = logger.getFunction();
		AuditClass auditClazz = auditConfig.find(clazz);
		//将类与方法转换为中文
		if(auditClazz != null) {
			AuditItem function = auditClazz.getMethod(method);
			if(function != null) {
				logger.setModule(auditClazz.getModule().getTitle());
				logger.setClazz(auditClazz.getTitle());
				logger.setFunction(function.getTitle());
				return logger;
			}
		}
		return null;
	}

}
