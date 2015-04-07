/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.security.formatter;

import java.text.ParseException;
import java.util.Locale;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import secfox.soc.melon.security.domain.MenuResource;
import secfox.soc.melon.security.service.MenuResourceService;

/**
 * @since 1.0 2014年10月4日,下午4:34:10
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Component("menuFormatter")
public class MenuResourceFormatter implements Formatter<MenuResource> {
	
	private MenuResourceService menuService;
	
	/**
	 * @param menuService
	 */
	@Inject
	public MenuResourceFormatter(MenuResourceService menuService) {
		super();
		this.menuService = menuService;
	}

	/* (non-Javadoc)
	 * @see org.springframework.format.Printer#print(java.lang.Object, java.util.Locale)
	 */
	@Override
	public String print(MenuResource menu, Locale locale) {
		if(menu != null) {
			return String.valueOf(menu.getId());
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.format.Parser#parse(java.lang.String, java.util.Locale)
	 */
	@Override
	public MenuResource parse(String menuId, Locale locale) throws ParseException {
		if (StringUtils.isNotBlank(menuId)) {
			try {
				return menuService.find(Long.parseLong(menuId));
			} catch (NumberFormatException ex) {
				return null;
			}
		}
		return null;
	}

}
