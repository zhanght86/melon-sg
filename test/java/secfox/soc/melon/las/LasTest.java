package secfox.soc.melon.las;

import java.io.File;

import javax.annotation.Resource;

import org.junit.Test;

import secfox.soc.las.query.AlertPageQuery;
import secfox.soc.las.service.AlertService;
import secfox.soc.melon.BaseTest;
import secfox.soc.melon.base.UmsInfoUtils;

public class LasTest extends BaseTest {

	@Resource
	private UmsInfoUtils umsInfoUtils;
	
	@Test
	public void test() {
		System.out.println(umsInfoUtils.getWindowsSystemMonitor("cpu"));
		/*File f = new File(this.getClass().getResource("/").getPath());
		System.out.println(f.getAbsolutePath()+"\\las-config"); */
	}
}
