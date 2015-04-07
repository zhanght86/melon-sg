/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.asset;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import secfox.soc.melon.BaseTest;
import secfox.soc.melon.asset.field.domain.AssetField;
import secfox.soc.melon.asset.field.service.AssetFieldService;
import secfox.soc.melon.persistence.QueryTemplate;
import secfox.soc.melon.persistence.QueryType;

/**
 * @since 1.0 2014年10月25日,上午9:16:23
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public class AssetFieldTest extends BaseTest {

	@Resource
	private AssetFieldService assetFieldService;
	
	@Test
	public void test() {
		QueryTemplate qt1 = QueryTemplate.create(QueryType.NamedQuery, "assetType.findPath");
		qt1.addParameter("typeId", 237L);
		List<Object[]> paths = assetFieldService.find(qt1);
		String path = (String)paths.get(0)[0];
		//
		QueryTemplate qt = QueryTemplate.create(QueryType.JQL, "select field from AssetField field ");
		qt.append(" where field.deviceType in(")
		  .append(path)
		  .append(") order by field.order asc ");
		//qt.addParameter("types", path);
		List<AssetField> fields = assetFieldService.findDomains(qt);
		for(AssetField field : fields) {
			System.out.println(field);
		}
	}

}
