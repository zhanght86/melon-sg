package secfox.soc.melon.system;

import java.io.File;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import secfox.soc.melon.base.UmsInfoUtils;

@Component
public class SystemState {
	
	private UmsInfoUtils umsUtils;
	
	@Inject
	public SystemState(UmsInfoUtils umsUtils) {
		this.umsUtils = umsUtils;
	}
	
	/**
	 * 读取操作系统名称
	 * 
	 * @return String
	 */
	private static String getOsName() {
		return System.getProperty("os.name");
	}
	
	
	/**
	 * 获取CPU值
	 * @return
	 */
	public List<Map<String, Object>> getCpu() {
		List<Map<String, Object>> result = Lists.newArrayList();
		Properties cpuList = new Properties();
		//通过treeMap对数据进行排序
		Map<String, Object> value = Maps.newTreeMap(new Comparator() {
			@Override
			public int compare(Object o1, Object o2) {
				if(o1 == null || o2 == null) {
					return 0;
				} else {
					//获取名称
					String o1Str = String.valueOf(o1);
					String o2Str = String.valueOf(o2);
					String regEx="[^0-9]";   
					Pattern p = Pattern.compile(regEx);
					Matcher o1m = p.matcher(o1Str);  
					Matcher o2m = p.matcher(o2Str);
					//截取名称后的数字
					Integer num1 = Integer.valueOf(o1m.replaceAll("").trim());
					Integer num2 = Integer.valueOf(o2m.replaceAll("").trim());
					return num1.compareTo(num2);
				}
			}
			
		});
		if(getOsName().toLowerCase().contains("window")) {
			cpuList = umsUtils.getWindowsSystemMonitor("cpu");
		}else {
			cpuList = umsUtils.getLinuxSystemMonitor("cpu");
		}
		//获取key值
		Iterator<Object> keys = cpuList.keySet().iterator();
		while(keys.hasNext()) {
			Object key = keys.next();
			//将获得的值装换为格式化数据
			if(cpuList.get(key) instanceof String) {
				//windows环境下
				value.put(String.valueOf(key), Double.parseDouble(String.valueOf(cpuList.get(key))));
			} else {
				//linux环境下
				DecimalFormat   fnum   =   new   DecimalFormat("0.0");  
				double data = Double.parseDouble(String.valueOf(cpuList.get(key)));
				value.put(String.valueOf(key), fnum.format(data*100));
			}
			
		}
		/*for(int i=1;i<25;i++) {
			value.put("cpu"+i, Math.random()*20);
		}*/
		result.add(value);
		return result;
	}
	
	/**
	 * 获取内存值
	 * @return
	 */
	public List<Map<String, Object>> getMemory() {
		List<Map<String, Object>> result = Lists.newArrayList();
		Properties memoryList = new Properties();
		Map<String, Object> value = Maps.newTreeMap();
		if(getOsName().toLowerCase().contains("window")) {
			memoryList = umsUtils.getWindowsSystemMonitor("memory");
		}else {
			memoryList = umsUtils.getLinuxSystemMonitor("memory");
		}
		DecimalFormat df = new DecimalFormat("0.000");
		Iterator<Object> keys = memoryList.keySet().iterator();
		while(keys.hasNext()) {
			Object key = keys.next();
			DecimalFormat   fnum   =   new   DecimalFormat("0.0");  
			double data = Double.parseDouble(String.valueOf(memoryList.get(key)));
			value.put(String.valueOf(key), fnum.format(data*100));
			
		}
		//value.put("memory", Math.random()*45);
		result.add(value);
		return result;
		
	}
	
	/**
	 * 获取硬盘值
	 * @return
	 */
	public List<Map<String, Object>> getDisk() {
		List<Map<String, Object>> result = Lists.newArrayList();
		Properties diskList = new Properties();
		if(getOsName().toLowerCase().contains("window")) {
			File[] roots = File.listRoots();
			for (File file : roots)
			{
				long total = file.getTotalSpace();
				if (total > 0)
				{
					Map<String, Object> value = new TreeMap<String, Object>();
					long free = file.getFreeSpace();
					long used = total-free;
					value.put("name", file.getPath());
					value.put("total", total/1024/1024);
					value.put("used", used/1024/1024);
					value.put("free", free/1024/1024);
					result.add(value);
				}
			}
		}else {
			diskList = umsUtils.getLinuxSystemMonitor("disk");
			Iterator<Object> keys = diskList.keySet().iterator();
			while(keys.hasNext()) {
				Object key = keys.next();
				Object item = diskList.get(key);
				Map<String, Object> value = new TreeMap<String, Object>();
				List<Object> tmp = (List<Object>)item;
				value.put("name", tmp.get(0));
				value.put("total", Long.valueOf(String.valueOf(tmp.get(1)))/1024/1024);
				value.put("used", Long.valueOf(String.valueOf(tmp.get(2)))/1024/1024);
				value.put("free", Long.valueOf(String.valueOf(tmp.get(3)))/1024/1024);
				result.add(value);
			}
		}
		
		/*for(int i=0;i<8;i++) {
			Map<String, Object> value = new TreeMap<String, Object>();
			value.put("name", "硬盘"+i);
			value.put("total", 1000);
			value.put("used", 110);
			value.put("free", 890);
			result.add(value);
		}*/
		return result;
	}
	
	public static void main(String[] args) {
		String a="love23next234csdn3423javaeye";
		String regEx="[^0-9]";   
		Pattern p = Pattern.compile(regEx);   
		Matcher m = p.matcher(a);   
		System.out.println( m.replaceAll("").trim());	
	}
}
