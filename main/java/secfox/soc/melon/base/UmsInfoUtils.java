package secfox.soc.melon.base;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.lang.management.ManagementFactory;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Properties;
import java.util.StringTokenizer;

import javax.inject.Inject;
import javax.inject.Named;

//import sun.management.ManagementFactory;


import com.sun.management.OperatingSystemMXBean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UmsInfoUtils {

	//private final static Logger logger = LoggerFactory.getLogger(UmsInfoUtils.class);
	private static final int KB = 1024;// 1K
	private static int CPUTIME = 1000;// cpu计算间隔
	private static String version = "version 2.0";
	private String osName;

	public static String getVersion() {
		return version;
	}
	
	public UmsInfoUtils() {
		File file = new File(this.getClass().getResource("/").getPath());
		//System.setProperty("manageserver.dir","D:\\workspace\\shuili\\sgconfig");
		System.setProperty("manageserver.dir", file.getPath()+"\\las-config");
		osName = getOsName();
	}
	
	/**
	 * 获取CPU使用率
	 * @return
	 * @throws Exception
	 */
	public String getCpuRatio() throws Exception {
		if(osName.toLowerCase().contains(("window"))) {
			return String.valueOf(getCpuRatioForWindows());
		} else {
			//logger.info("-------------linux cpu: {}", getCpuUsage_Linux());
			return String.valueOf(getCpuUsage_Linux());
		}
		
	}

	/**
	 * 获取内存使用率
	 * @return
	 * @throws IOException
	 */
	public String getMemoryRatio() throws IOException {
		if(osName.toLowerCase().contains(("window"))) {
			return String.valueOf(getMemeoryRatio_win());
		} else {
			//logger.info("---------linux memory: {}", getMemoryRatio_linux());
			return String.valueOf(getMemoryRatio_linux());
		}
	}

	/**
	 * 获取硬盘使用率
	 * @return
	 * @throws Exception
	 */
	public String getDiskRatio() throws Exception {
		if(osName.toLowerCase().contains(("window"))) {
			return String.valueOf(getDiskRatio_win());
		} else {
			//logger.info("------------linux disk: {}", getDiskRatio_linux());
			return String.valueOf(getDiskRatio_linux());
		}
	}

	/**
	 * 可使用内存
	 * 
	 * @return long
	 */
	private long getRuntimeTotleMemory() {
		long totalMemory = Runtime.getRuntime().totalMemory() / UmsInfoUtils.KB;
		return totalMemory;
	}

	/**
	 * 可用剩余内存
	 * 
	 * @return long
	 */
	private long getRuntimeFreeMemory() {
		long freeMemory = Runtime.getRuntime().freeMemory() / UmsInfoUtils.KB;
		return freeMemory;
	}

	/**
	 * 最大可使用内存
	 * 
	 * @return long
	 */
	private long getRuntimeMaxMemory() {
		long maxMemory = Runtime.getRuntime().maxMemory() / UmsInfoUtils.KB;
		return maxMemory;
	}

	/**
	 * 总的物理内存
	 * 
	 * @return long
	 */
	private long getTotalPhysicalMemorySize() {
		OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory
		.getOperatingSystemMXBean();
		long totalMemorySize = osmxb.getTotalPhysicalMemorySize()
		/ UmsInfoUtils.KB;
		return totalMemorySize;
	}

	/**
	 * 剩余的物理内存
	 * 
	 * @return long
	 */
	private long getFreePhysicalMemorySize() {
		OperatingSystemMXBean osmxb = (OperatingSystemMXBean) ManagementFactory
				.getOperatingSystemMXBean();
		long freePhysicalMemorySize = osmxb.getFreePhysicalMemorySize()
				/ UmsInfoUtils.KB;
		return freePhysicalMemorySize;
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
	 * 获得CPU使用率.
	 * 
	 * @return 返回cpu使用率
	 * @author amg * Creation date: 2008-4-25 - 下午06:05:11
	 */
	private double getCpuRatioForWindows() {
		try {
			
			String procCmd = System.getenv("windir")
					+ "\\system32\\wbem\\wmic.exe process get Caption,CommandLine,KernelModeTime,ReadOperationCount,ThreadCount,UserModeTime,WriteOperationCount";
			// 取进程信息
			long[] c0 = readCpu(Runtime.getRuntime().exec(procCmd));
			Thread.sleep(CPUTIME);
			long[] c1 = readCpu(Runtime.getRuntime().exec(procCmd));
			if (c0 != null && c1 != null) {
				long idletime = c1[0] - c0[0];
				long busytime = c1[1] - c0[1];
				return Double.valueOf(100 * (busytime) / (busytime + idletime))
						.doubleValue();
			} else {
				return 0.0;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return 0.0;
		}

	}

	/**
	 * 读取CPU信息.
	 * 
	 * @param proc
	 * @return
	 * @author amg * Creation date: 2008-4-25 - 下午06:10:14
	 */

	private long[] readCpu(final Process proc) {
		long[] retn = new long[2];
		try {
			proc.getOutputStream().close();
			InputStreamReader ir = new InputStreamReader(proc.getInputStream());
			LineNumberReader input = new LineNumberReader(ir);
			String line = input.readLine();

			if (line == null) {
				return null;
			}
			int capidx = line.indexOf("Caption");
			int cmdidx = line.indexOf("CommandLine");
			int rocidx = line.indexOf("ReadOperationCount");
			int umtidx = line.indexOf("UserModeTime");
			int kmtidx = line.indexOf("KernelModeTime");
			int wocidx = line.indexOf("WriteOperationCount");
			long idletime = 0;
			long kneltime = 0;
			long usertime = 0;
			while ((line = input.readLine()) != null) {
				if (line.length() < wocidx) {
					continue;
				}
				// 字段出现顺序：Caption,CommandLine,KernelModeTime,ReadOperationCount,
				// ThreadCount,UserModeTime,WriteOperation
				String caption = substring(line, capidx, cmdidx - 1)
						.trim();
				String cmd = substring(line, cmdidx, kmtidx - 1).trim();
				if (cmd.indexOf("wmic.exe") >= 0) {
					continue;
				}
				String m=substring(line, kmtidx, rocidx - 1).trim();
				String n=substring(line, umtidx, wocidx - 1).trim();
				
				if (caption.equals("System Idle Process")
						|| caption.equals("System")) {
					
					if(!m.equals("") && m!=null){
						idletime += Long.valueOf(m).longValue();
					}
					
					if(!n.equals("") && n!=null){
						idletime += Long.valueOf(n).longValue();
					}
					
					continue;
				}
				if(!m.equals("") && m!=null){
					kneltime += Long.valueOf(m).longValue();
				}
				if(!n.equals("")&& n!=null){
					usertime += Long.valueOf(n)
							.longValue();
				}
				
			}
			retn[0] = idletime;
			retn[1] = kneltime + usertime;
			return retn;
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				proc.getInputStream().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 获取windows内存使用率
	 * @return
	 * @throws IOException
	 */
	private String getMemeoryRatio_win() {
		StringBuffer suf = new StringBuffer("");
		long totalPhysicalMemory=0;
		long freePhysicalMemory=0;
		long usedPhysicalMemorySize=0;
		totalPhysicalMemory=getTotalPhysicalMemorySize();
		freePhysicalMemory=getFreePhysicalMemorySize();
		usedPhysicalMemorySize = totalPhysicalMemory-freePhysicalMemory;
		suf.append(formatFloatNum(((double) usedPhysicalMemorySize
				/ (double) totalPhysicalMemory)*100));
		return suf.toString();
	}

	/**
	 * 获取内存使用率
	 * @throws IOException 
	 * */
	private String getMemoryRatio_linux() throws IOException {
		java.text.DecimalFormat memdf = new java.text.DecimalFormat("0.00");
		StringBuffer suf = new StringBuffer("");
		long totalPhysicalMemory=0;
		long usedPhysicalMemorySize=0;
		try {
				Runtime rt = Runtime.getRuntime();
				Process p = rt.exec("free");// 调用获取系统内在命令"free"
				BufferedReader in = null;
				try {
					in = new BufferedReader(new InputStreamReader(p.getInputStream()));
					String str = null;
					ArrayList<String> strArray = new ArrayList<String>();
					int i=0;
					while ((str = in.readLine()) != null) {
						strArray.add(str);}
					if(strArray.size()>=2){
						String[] temp=strArray.get(1).split(" ");
						ArrayList<String> temps = new ArrayList<String>();
						for(int j=0;j<temp.length;j++){
							if(temp[j]!= null && !temp[j].trim().equals("")){
								temps.add(temp[j]);
							}
						}
						if(temps.size()>=3) {
							totalPhysicalMemory=Long.parseLong(temps.get(1).trim());
							usedPhysicalMemorySize=Long.parseLong(temps.get(2).trim());
							}
						}else{
						}
					}catch (Exception e) {
						e.printStackTrace();
					} finally {
						in.close();
					}
		//logger.info("##########usedPhysicalMemorySize :{}", usedPhysicalMemorySize);
		//logger.info("##########totalPhysicalMemory :{}", totalPhysicalMemory);
		//logger.info("##########memoryRatio :{}", (double) usedPhysicalMemorySize/ (double) totalPhysicalMemory);
		System.out.println("##########usedPhysicalMemorySize"+usedPhysicalMemorySize);
		System.out.println("##########totalPhysicalMemory"+totalPhysicalMemory);
		System.out.println("##########memoryRatio"+(double) usedPhysicalMemorySize/ (double) totalPhysicalMemory);
		suf.append(memdf.format(((double) usedPhysicalMemorySize
					/ (double) totalPhysicalMemory)*100));

		} catch (ArithmeticException ex) {
			ex.printStackTrace();
		}

		return suf.toString();
	}

	// 获取本机IP的工具方法
	private String getLocalHostIP() {
		InetAddress locAdd = null;
		String str[] = null;
		try {
			locAdd = InetAddress.getLocalHost(); // 获取本机IP
			str = locAdd.toString().split("/");

		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		return str[1]; // 返回当前的IP地地址
	}

	// 获取磁盘空间的工具方法只限于1.6
	private String getHostFreeSpace() {

		File f = new File(this.getClass().getResource("").getPath().replace(
				"%20", " ")); // 有空格

		while (f.isDirectory() && f.getParent() != null) {
			File temp = new File(f.getParent());
			f = temp;
			// System.out.println(f.getAbsolutePath());
		}

		StringBuffer suf = new StringBuffer();
		java.text.DecimalFormat df = new java.text.DecimalFormat("###.00%");
		try {
			suf.append(df.format((float) f.getFreeSpace()
					/ (float) f.getTotalSpace()));
		} catch (ArithmeticException ex) {
			ex.printStackTrace();
		}

		return suf.toString();
	}

	/**
	 * 获取磁盘大小
	 * 
	 * @return
	 */
	private float getTotalSpace() {
		
		File f = new File(this.getClass().getResource("").getPath().replace(
				"%20", " ")); // 有空格

		while (f.isDirectory() && f.getParent() != null) {
			File temp = new File(f.getParent());
			f = temp;
		}
		DecimalFormat df = new DecimalFormat("#.0");
		float totalSpace = 0;
		if (df.format(f.getTotalSpace() / (float) (1024 * 1024 * 1024)) != null) {
			totalSpace = Float.parseFloat(df.format(f.getTotalSpace()
					/ (float) (1024 * 1024 * 1024)));
		}
		return totalSpace;
	}

	/**
	 * 获取磁盘剩余空间大小
	 * 
	 * @return
	 */
	private float getFreeSpace() {
		File f = new File(this.getClass().getResource("").getPath().replace(
				"%20", " ")); // 有空格

		while (f.isDirectory() && f.getParent() != null) {
			File temp = new File(f.getParent());
			f = temp;
		}
		DecimalFormat df = new DecimalFormat("#.0");
		float freeSpace = 0;
		if (df.format(f.getFreeSpace() / (float) (1024 * 1024 * 1024)) != null) {
			freeSpace = Float.parseFloat(df.format(f.getFreeSpace()
					/ (float) (1024 * 1024 * 1024)));
		}
		return freeSpace;
	}

	/**
	 * 获取已使用磁盘空间
	 * 
	 * @return
	 */
	private float getUsedSpace() {
		float result = 0f;
		float total = getTotalSpace();
		float free = getFreeSpace();
		DecimalFormat df = new DecimalFormat("#.0");
		result = Float.parseFloat(df.format(total - free));
		return result;
	}
	
	/**
	 * 获取windows磁盘使用率
	 * @return
	 */
	private String getDiskRatio_win() {
		float total = getTotalSpace();
		float used = getUsedSpace();
		String result = String.valueOf((used/total)*100);
		return result;
	}

	/**
	 * 获取Linux使用率
	 * 
	 * @return
	 */
	
	// 新获取Linux使用率
	private double getCpuUsage_Linux() throws Exception {
		double cpuUsed = 0;
		Runtime rt = Runtime.getRuntime();
		Process p = rt.exec("top -b -n 1");// 调用系统的“top"命令
		BufferedReader in = null;
		try {
			in = new BufferedReader(new InputStreamReader(p.getInputStream()));
			String str = null;
			String[] strArray = null;
			while ((str = in.readLine()) != null) {
				int m = 0;
				if (str.indexOf(" R ") != -1 && str.indexOf("top") == -1) {// 只分析正在运行的进程，top进程本身除外
					strArray = str.split(" ");
					for (String tmp : strArray) {
						if (tmp.trim().length() == 0)
							continue;
						if (++m == 9) {// 第9列为CPU的使用百分比(RedHat 9)
							cpuUsed += Double.parseDouble(tmp);
						}
					}
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			in.close();
		}
		return cpuUsed;
	}
	
	/**
	 * linux cpu使用情况
	 * @return
	 * @throws Exception
	 */
	private double getLinuxCpuUsage() throws Exception {
		   double cpuUsed = 0;
		   double idleUsed = 0.0;
		   Runtime rt = Runtime.getRuntime();
		   Process p = rt.exec("top -b -n 1");// call "top" command in linux
		   BufferedReader in = null;
		   {
		    in = new BufferedReader(new InputStreamReader(p.getInputStream()));
		    String str = null;
		    int linecount = 0;

		    while ((str = in.readLine()) != null) {
		     linecount++;
		     if (linecount == 3) {
		      String[] s = str.split("%");
		      String idlestr = s[3];
		      String idlestr1[] = idlestr.split(" ");
		      idleUsed = Double.parseDouble(idlestr1[idlestr1.length-1]);
		      cpuUsed = 100-idleUsed;
		      break;
		     }

		    }
		   }
		   return cpuUsed;
		}
		
	
	
	/**  
	 * Linux内存监控  
	 * @return  
	 * @throws Exception  
	 */  
	    private double getMemUsage() throws Exception {   
	  
	        double menUsed = 0;   
	        Runtime rt = Runtime.getRuntime();   
	        Process p = rt.exec("top -b -n 1");// 调用系统的“top"命令   
	        BufferedReader in = null;   
	        try {   
	            in = new BufferedReader(new InputStreamReader(p.getInputStream()));   
	            String str = null;   
	            String[] strArray = null;   
	           
	            while ((str = in.readLine()) != null) {   
	                int m = 0;   
	                if (str.indexOf(" R ") != -1) {// 只分析正在运行的进程，top进程本身除外 &&   
	                    strArray = str.split(" ");   
	                    for (String tmp : strArray) {   
	                        if (tmp.trim().length() == 0)   
	                            continue;   
	  
	                        if (++m == 10) {   
	  
	                            menUsed += Double.parseDouble(tmp);   
	  
	                        }   
	                    }   
	  
	                }   
	            }   
	        } catch (Exception e) {   
	            e.printStackTrace();   
	        } finally {   
	            in.close();   
	        }   
	        return menUsed;   
	    }   
	  
	    /**  
	     * 获取Linux磁盘空间大小  
	     *   
	     * @return  
	     * @throws Exception  
	     */  
	    private double getDeskUsage() throws Exception {   
	        double totalHD = 0;   
	        double usedHD = 0;   
	        Runtime rt = Runtime.getRuntime();   
	        Process p = rt.exec("df -hl");//df -hl 查看硬盘空间   
	        BufferedReader in = null;   
	        try {   
	            in = new BufferedReader(new InputStreamReader(p.getInputStream()));   
	            String str = null;   
	            String[] strArray = null;   
	            int flag = 0;   
	            while ((str = in.readLine()) != null) {   
	                int m = 0;   
	                    strArray = str.split(" ");   
	                    for (String tmp : strArray) {   
	                        if (tmp.trim().length() == 0)   
	                            continue;   
	                        ++m;   
	                        if (tmp.indexOf("G") != -1) {   
	                            if (m == 2) {   
	                                if (!tmp.equals("") && !tmp.equals("0"))   
	                                    totalHD += Double.parseDouble(tmp   
	                                            .substring(0, tmp.length() - 1)) * 1024;   
	  
	                            }   
	                            if (m == 3) {   
	                                if (!tmp.equals("none") && !tmp.equals("0"))   
	                                    usedHD += Double.parseDouble(tmp.substring(   
	                                            0, tmp.length() - 1)) * 1024;   
	  
	                            }   
	                        }   
	                        if (tmp.indexOf("M") != -1) {   
	                            if (m == 2) {   
	                                if (!tmp.equals("") && !tmp.equals("0"))   
	                                    totalHD += Double.parseDouble(tmp   
	                                            .substring(0, tmp.length() - 1));   
	  
	                            }   
	                            if (m == 3) {   
	                                if (!tmp.equals("none") && !tmp.equals("0"))   
	                                    usedHD += Double.parseDouble(tmp.substring(   
	                                            0, tmp.length() - 1));   
	                            }   
	                        }   
	                           
	                    }   
	  
	            }   
	        } catch (Exception e) {   
	            e.printStackTrace();   
	        } finally {   
	            in.close();   
	        }   
	        return (usedHD / totalHD) * 100; 
	    }
	
	
	
	    /**  
	     * 获取Linux磁盘空间大小  
	     *   
	     * @return  
	     * @throws Exception  
	     */  
	    private double getLinuxTotalDesk1() throws Exception {   
	        double totalHD = 0;   
	        double usedHD = 0;   
	        Runtime rt = Runtime.getRuntime();   
	        Process p = rt.exec("df -hl");//df -hl 查看硬盘空间   
	        BufferedReader in = null;   
	        try {   
	            in = new BufferedReader(new InputStreamReader(p.getInputStream()));   
	            String str = null;   
	            String[] strArray = null;   
	            int flag = 0;   
	            while ((str = in.readLine()) != null) { 
	            	
	                int m = 0;   
	                    strArray = str.split(" ");   
	                    for (String tmp : strArray) {   
	                        if (tmp.trim().length() == 0)   
	                            continue;   
	                        ++m;   
	                        if (tmp.indexOf("G") != -1) {   
	                            if (m == 2) {   
	                                if (!tmp.equals("") && !tmp.equals("0"))   
	                                    totalHD += Double.parseDouble(tmp   
	                                            .substring(0, tmp.length() - 1)) * 1024;   
	  
	                            }   
	                            if (m == 3) {   
	                                if (!tmp.equals("none") && !tmp.equals("0"))   
	                                    usedHD += Double.parseDouble(tmp.substring(   
	                                            0, tmp.length() - 1)) * 1024;   
	  
	                            }   
	                        }   
	                        if (tmp.indexOf("M") != -1) {   
	                            if (m == 2) {   
	                                if (!tmp.equals("") && !tmp.equals("0"))   
	                                    totalHD += Double.parseDouble(tmp   
	                                            .substring(0, tmp.length() - 1));   
	  
	                            }   
	                            if (m == 3) {   
	                                if (!tmp.equals("none") && !tmp.equals("0"))   
	                                    usedHD += Double.parseDouble(tmp.substring(   
	                                            0, tmp.length() - 1));   
	                            }   
	                        }   
	                           
	                    }   
	  
	            }   
	        } catch (Exception e) {   
	            e.printStackTrace();   
	        } finally {   
	            in.close();   
	        }   
	        
	        return  totalHD;   
	    }
	    
	    
	    /**  
	     * 获取Linux磁盘空间大小  
	     *   
	     * @return  
	     * @throws Exception  
	     */  
	    private double getLinuxTotalDesk() throws Exception {   
			double totalHD = 0;   
			double usedHD = 0;  
			double totalH = 0; 
			double usedH = 0;   		
			Runtime rt = Runtime.getRuntime();   
			Process p = rt.exec("df -hl");//df -hl 查看硬盘空间   
			BufferedReader in = null;   
			try {   
				in = new BufferedReader(new InputStreamReader(p.getInputStream()));   
				String str = null;   
				String[] strArray = null;   
				int flag = 0;   
				while ((str = in.readLine()) != null) {   
					int m = 0;   
					strArray = str.split(" ");   
					for (String tmp : strArray) {
						if (tmp.trim().length() == 0) 	continue;   
						++m;   
						if (tmp.indexOf("G") != -1) {   
							if (m == 2) {  
								if (!tmp.equals("") && !tmp.equals("0"))   
									totalH = Double.parseDouble(tmp   
											.substring(0, tmp.length() - 1)) * 1024;   
							}   
							if (m == 3) { 
								if (!tmp.equals("none") && !tmp.equals("0"))   
									usedH = Double.parseDouble(tmp.substring(   
											0, tmp.length() - 1)) * 1024;   

							}   
						}   
						if (tmp.indexOf("M") != -1) {   
							if (m == 2) {   
								if (!tmp.equals("") && !tmp.equals("0"))   
									totalH = Double.parseDouble(tmp   
											.substring(0, tmp.length() - 1));  
							}   
							if (m == 3) {
								if (!tmp.equals("none") && !tmp.equals("0"))   
									usedH = Double.parseDouble(tmp.substring(   
											0, tmp.length() - 1)); 
							}   
						} 
						if(m ==6 && tmp.equals("/home/leadsec")){
							totalHD=Double.parseDouble(formatFloatNum(totalH/1024));
							break;
						}			   
					}   
				}   
			} catch (Exception e) {   
				e.printStackTrace();   
			} finally {   
				in.close();   
			} 
	  		
			return totalHD;
		}
	    
	    /**  
	     * 获取Linux磁盘剩余空间大小  
	     *   
	     * @return  
	     * @throws Exception  
	     */  
	    private double getLinuxFreeDesk() throws Exception {   
			double totalHD = 0;   
			double usedHD = 0;  
			double totalH = 0; 
			double usedH = 0;   
			double freeHD=0;
			Runtime rt = Runtime.getRuntime();   
			Process p = rt.exec("df -hl");//df -hl 查看硬盘空间   
			BufferedReader in = null;   
			try {   
				in = new BufferedReader(new InputStreamReader(p.getInputStream()));   
				String str = null;   
				String[] strArray = null;   
				while ((str = in.readLine()) != null) {   
					int m = 0;   
					strArray = str.split(" ");   
					for (String tmp : strArray) {
						if (tmp.trim().length() == 0) 	continue;   
						++m;   
						if (tmp.indexOf("G") != -1) {   
							if (m == 2) {  
								if (!tmp.equals("") && !tmp.equals("0"))   
									totalH = Double.parseDouble(tmp   
											.substring(0, tmp.length() - 1))* 1024;   
							}   
							if (m == 3) { 
								if (!tmp.equals("none") && !tmp.equals("0"))   
									usedH = Double.parseDouble(tmp.substring(   
											0, tmp.length() - 1))* 1024;   

							}   
						}   
						if (tmp.indexOf("M") != -1) {   
							if (m == 2) {   
								if (!tmp.equals("") && !tmp.equals("0"))   
									totalH = Double.parseDouble(tmp   
											.substring(0, tmp.length() - 1));  
							}   
							if (m == 3) {
								if (!tmp.equals("none") && !tmp.equals("0"))   
									usedH = Double.parseDouble(tmp.substring(   
											0, tmp.length() - 1)); 
							}   
						} 
						if(m ==6 && tmp.equals("/home/leadsec")){
							totalHD=totalH;
							usedHD=usedH;
							freeHD=Double.parseDouble(this.formatFloatNum((totalHD-usedHD)/1024));
							break;
						}			   
					}   
				}   
			} catch (Exception e) {   
				e.printStackTrace();   
			} finally {   
				in.close();   
			} 
			return freeHD;
		}
	    
	    /**  
	     * 获取Linux磁盘使用空间大小  
	     *   
	     * @return  
	     * @throws Exception  
	     */  
	    private double getLinuxUsedDesk() throws Exception {   
			double usedHD = 0;  
			double totalH = 0; 
			double usedH = 0;   		
			Runtime rt = Runtime.getRuntime();   
			Process p = rt.exec("df -hl");//df -hl 查看硬盘空间   
			BufferedReader in = null;   
			try {   
				in = new BufferedReader(new InputStreamReader(p.getInputStream()));   
				String str = null;   
				String[] strArray = null;   
				while ((str = in.readLine()) != null) {   
					int m = 0;   
					strArray = str.split(" ");   
					for (String tmp : strArray) {
						if (tmp.trim().length() == 0) 	continue;   
						++m;   
						if (tmp.indexOf("G") != -1) {   
							if (m == 2) {  
								if (!tmp.equals("") && !tmp.equals("0"))   
									totalH = Double.parseDouble(tmp   
											.substring(0, tmp.length() - 1))* 1024 ;   
							}   
							if (m == 3) { 
								if (!tmp.equals("none") && !tmp.equals("0"))   
									usedH = Double.parseDouble(tmp.substring(   
											0, tmp.length() - 1))* 1024;   

							}   
						}   
						if (tmp.indexOf("M") != -1) {   
							if (m == 2) {   
								if (!tmp.equals("") && !tmp.equals("0"))   
									totalH = Double.parseDouble(tmp   
											.substring(0, tmp.length() - 1));  
							}   
							if (m == 3) {
								if (!tmp.equals("none") && !tmp.equals("0"))   
									usedH = Double.parseDouble(tmp.substring(   
											0, tmp.length() - 1)); 
							}   
						} 
						if(m ==6 && tmp.equals("/home/leadsec")){
							usedHD=Double.parseDouble(formatFloatNum(usedH/1024));
							break;
						}			   
					}   
				}   
			} catch (Exception e) {   
				e.printStackTrace();   
			} finally {   
				in.close();   
			} 
	  		
			return usedHD;
		}
	    
	private String getDiskRatio_linux() throws Exception {
		DecimalFormat myFormat= new DecimalFormat("0.00");
		double total = getLinuxTotalDesk();
		double used = getLinuxUsedDesk();
		//logger.info("##########totaldesk:{}",total);
		//logger.info("##########useddesk:{}",used);
		//logger.info("##########deskRatio:{}",used/total);
		System.out.println("##########totaldesk:{}"+total);
		System.out.println("##########useddesk:{}"+used);
		System.out.println("##########deskRatio:{}"+used/total);
		String result = myFormat.format((used/total)*100);
		return result;
	}
	
	private String formatFloatNum(double num){
		DecimalFormat myFormat= new DecimalFormat("0.00");
		String reNum=myFormat.format(num);
		return reNum;
	}
	
	private String substring(String src, int start_idx, int end_idx){    
        byte[] b = src.getBytes();    
        String tgt = "";    
        for(int i=start_idx; i<=end_idx; i++){    
            tgt +=(char)b[i];    
        }    
        return tgt;    
	 }
	
	/**
	 * 获取linux下的系统监控信息
	 * 
	 * @param key
	 * @return
	 */

	public Properties getLinuxSystemMonitor(String key) {
		try {
			if ("cpu".equalsIgnoreCase(key)) {

				return this.getLinuxCPU();
			}
			//修改内存计算方式2010-08-24 by Peizl
			if ("memory".equalsIgnoreCase(key)) {
				File file = new File("/proc/meminfo");
				BufferedReader br = new BufferedReader(new InputStreamReader(
						new FileInputStream(file)));
				String str = null;
				StringTokenizer token = null;
				long total = 0;
				long cached = 0;
				long buffers = 0;
				long memFree = 0;
				float pre = 0L;
				Properties prop = new Properties();
				while ((str = br.readLine()) != null) {
					token = new StringTokenizer(str);
					if (!token.hasMoreTokens())
						continue;
					str = token.nextToken();
					if (!token.hasMoreTokens())
						continue;
					if (str.equalsIgnoreCase("MemTotal:"))
						total = Long.parseLong(token.nextToken());// 总的物理内存大小(kb)
					else if (str.equalsIgnoreCase("Cached:"))
						cached = Long.parseLong(token.nextToken());// 缓存大小，对应用程序来讲是可用内存(kb)
					else if (str.equalsIgnoreCase("Buffers:"))
						buffers = Long.parseLong(token.nextToken());// 缓冲区大小，对应用程序来讲是可用内存(kb)
					else if (str.equalsIgnoreCase("MemFree:"))
						memFree = Long.parseLong(token.nextToken());// 可用内存大小(kb)
					
				}

				pre = (float) (total - cached - buffers - memFree) / total;
				prop.put("MemFree", pre);
				br.close();
				return prop;
			}
			if ("disk".equalsIgnoreCase(key)) {
				StringTokenizer token = null;
				Process p = Runtime.getRuntime().exec("df");
				InputStream stream = p.getInputStream();
				Properties prop = new Properties();
				String diskName = "";
				long total = 0;
				long used = 0;
				long free = 0;
				String usedPer = "";
				String root = "";
				int line = 0;
				int index = 0;
				BufferedReader br = new BufferedReader(new InputStreamReader(
						stream));
				String str = "";
				HashMap<String, LinkedList> map = new HashMap<String, LinkedList>();

				boolean combine_flag = false;
				String str_lastline = "";
				while (null != (str = br.readLine())) {
					if (combine_flag)
						str = str_lastline + "\t" + str;

					line++;
					if (line == 1)
						continue;
					token = new StringTokenizer(str);

					int token_nums = token.countTokens();
					if (token_nums < 6) {
						combine_flag = true;
						str_lastline = str;
						continue;
					}

					diskName = token.nextToken();
					total = Long.parseLong(token.nextToken());
					used = Long.parseLong(token.nextToken());
					free = Long.parseLong(token.nextToken());
					usedPer = token.nextToken();
					root = token.nextToken();
					LinkedList list = new LinkedList();
					list.addLast(diskName);
					list.addLast(total);
					list.addLast(used);
					list.addLast(free);
					list.addLast(root);
					list.addLast(usedPer);
					index++;
					prop.put("VolumeName(" + index + ")", list);
				}
				br.close();
				//p.waitFor();
				return prop;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * CPU利用率 通过两次读取/proc/stat的信息，将第二次的值减去第一次的值而获得
	 * 公式：((use2+nice2+sys2)-(use1+nice1+sys1))/((use2+nice2+sys2+idle2)-(use1+nice1+sys1+idle1))
	 * Modify by liyue 2013-12-4
	 * 1、由于接收日志时使用文件缓存技术，因此计算CPU利用率时，需要在总时间中加入iowait、hi、si、st的时间
	 * 新公式：((use2+nice2+sys2)-(use1+nice1+sys1))/((use2+nice2+sys2+idle2+iowait2+hi2+si2+st2)-(use1+nice1+sys1+idle1+iowait1+hi1+si1+st1))
	 * 2、将原来的各个数值由float型转为long型，以减少误差，以免出现cpu使用率大于100%的情况。
	 * @return
	 */
	public Properties getLinuxCPU()
	{
		Properties prop = new Properties();
		HashMap map1 = new HashMap();
		HashMap map2 = new HashMap();
		int num1 = 0;
		File file1 = new File("/proc/stat");
		try
		{
			BufferedReader br1 = new BufferedReader(new InputStreamReader(
					new FileInputStream(file1)));
			StringTokenizer token1 = new StringTokenizer(br1.readLine());
			String str1 = null;

			long user1 = 0;
			long nice1 = 0;
			long sys1 = 0;
			long idle1 = 0;
			long iowait1 = 0;	// Add by liyue 2013-12-4 增加IO等待时间
			long hi1 = 0;
			long si1 = 0;
			long st1 = 0;
			// float pre1 = 0;

			while ((str1 = br1.readLine()) != null)
			{
				token1 = new StringTokenizer(str1);
				if (!token1.hasMoreTokens())
					continue;
				str1 = token1.nextToken();
				if (!token1.hasMoreTokens())
					continue;
				if (str1.startsWith("cpu" + num1))
				{
					user1 = Integer.parseInt(token1.nextToken());
					nice1 = Integer.parseInt(token1.nextToken());
					sys1 = Integer.parseInt(token1.nextToken());
					idle1 = Integer.parseInt(token1.nextToken());
					iowait1 = Integer.parseInt(token1.nextToken());
					hi1 = Integer.parseInt(token1.nextToken());
					si1 = Integer.parseInt(token1.nextToken());
					st1 = Integer.parseInt(token1.nextToken());
					long used1 = (long) (user1 + nice1 + sys1);
					long total1 = (long) (user1 + nice1 + sys1 + idle1
							+ iowait1 + hi1 + si1 + st1);
					// prop.put("cpu" + num1, pre1);
					map1.put("cpu" + num1, used1 + ":" + total1);
					num1++;
				}
			}

			br1.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		try
		{
			Thread.sleep(1000);
		}
		catch (InterruptedException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try
		{
			BufferedReader br2 = new BufferedReader(new InputStreamReader(
					new FileInputStream(file1)));
			StringTokenizer token2 = new StringTokenizer(br2.readLine());
			String str2 = null;
			int num2 = 0;
			long user2 = 0;
			long nice2 = 0;
			long sys2 = 0;
			long idle2 = 0;
			long iowait2 = 0;	// Add by liyue 2013-12-4 增加IO等待时间
			long hi2 = 0;
			long si2 = 0;
			long st2 = 0;
			// float pre2 = 0;

			while ((str2 = br2.readLine()) != null)
			{
				token2 = new StringTokenizer(str2);
				if (!token2.hasMoreTokens())
					continue;
				str2 = token2.nextToken();
				if (!token2.hasMoreTokens())
					continue;
				if (str2.startsWith("cpu" + num2))
				{
					user2 = Integer.parseInt(token2.nextToken());
					nice2 = Integer.parseInt(token2.nextToken());
					sys2 = Integer.parseInt(token2.nextToken());
					idle2 = Integer.parseInt(token2.nextToken());
					iowait2 = Integer.parseInt(token2.nextToken());
					hi2 = Integer.parseInt(token2.nextToken());
					si2 = Integer.parseInt(token2.nextToken());
					st2 = Integer.parseInt(token2.nextToken());
					long used2 = (long) (user2 + nice2 + sys2);
					long total2 = (long) (user2 + nice2 + sys2 + idle2
							+ iowait2 + hi2 + si2 + st2);
					// prop.put("cpu" + num1, pre1);
					map2.put("cpu" + num2, used2 + ":" + total2);
					num2++;
				}
			}
			br2.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		for (int i = 0; i < num1; i++)
		{
			String tmpUsed2 = ((String) (map2.get("cpu" + i))).split(":")[0];
			String tmpTotal2 = ((String) (map2.get("cpu" + i))).split(":")[1];
			String tmpUsed1 = ((String) (map1.get("cpu" + i))).split(":")[0];
			String tmpTotal1 = ((String) (map1.get("cpu" + i))).split(":")[1];
			float used = Long.parseLong(tmpUsed2)
					- Long.parseLong(tmpUsed1);
			float total = Long.parseLong(tmpTotal2)
					- Long.parseLong(tmpTotal1);
			prop.put("cpu" + i, used / total);

		}
		return prop;
	}
	
	public static String command = System.getProperty("manageserver.dir")
			+ "/ext/monitor_new/self-monitoring";

	/**
	 * 通过传入的参数，获取不同的性能指标 modified by bilei
	 * 
	 * @param key
	 *            ：取值cpu、memory、disk、database
	 * @return
	 */
	public Properties getWindowsSystemMonitor(String key)
	{
		if (null == key)
		{
			return null;
		}
		else
		{
			if (key.equalsIgnoreCase("memory"))
			{
				return getMemoryInfo();
			}
			if (key.equalsIgnoreCase("disk"))
			{
				return getDiskInfo();
			}
			if (key.equalsIgnoreCase("cpu"))
			{
				return getCPUInfo();
			}
			else
			{
				return null;
			}
		}
	}

	/**
	 * 获取CPU信息
	 * 
	 * @return
	 */
	private Properties getCPUInfo()
	{
		String comLine = "cscript //Nologo " + System.getProperty("manageserver.dir") + "/ext/monitor_new/self-monitoring/cpuMonitor.vbs ";
		Properties prop = new Properties();
		try
		{
			Process p = Runtime.getRuntime().exec(comLine);
			InputStream stream = p.getInputStream();

			BufferedReader br = new BufferedReader(
					new InputStreamReader(stream));

			prop.load(stream);
			br.close();
			p.waitFor();
			return prop;
		}
		catch (Exception e)
		{
		}

		return prop;
	}

	/**
	 * 获取内存信息，包含空闲内存和总内存
	 * 
	 * @return
	 */
	private Properties getMemoryInfo() {
		Properties prop = new Properties();
		OperatingSystemMXBean mxbean = (OperatingSystemMXBean) ManagementFactory
				.getOperatingSystemMXBean();
		long freesize = mxbean
				.getFreePhysicalMemorySize();
		long totalsize = mxbean
				.getTotalPhysicalMemorySize();
		long usedsize = totalsize - freesize;

		if (freesize == totalsize && freesize == 2147483647L && totalsize == 2147483647L) {
			String memOutput = "";
			String[] commandPath = {System.getProperty("manageserver.dir") + File.separator + "mem.exe"};
		    try {
		    	Process process = Runtime.getRuntime().exec(commandPath); 
			    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			    String outputLine = "";
			    
			    while ((outputLine = bufferedReader.readLine()) != null) { 
			    	if (outputLine != null && outputLine.trim().length() > 0) {
			    		memOutput = outputLine;
			    	}
			    }
			    process.waitFor();

			    if (memOutput.contains(";")) {
			    	String[] splitOutput = memOutput.split(";");
			    	
			    	totalsize = Long.parseLong(splitOutput[0]);
			    	freesize = Long.parseLong(splitOutput[1]);
			    	usedsize = totalsize - freesize;
			    }
		    }
		    catch(Exception e) {
		    	e.printStackTrace();
		    }
		}
		prop.put("memory", (double)usedsize / (double)totalsize);
		return prop;
	}

	/**
	 * 获取磁盘信息
	 * 
	 * @return
	 */
	private Properties getDiskInfo()
	{
		Properties disk = new Properties();
		File[] roots = File.listRoots();
		int index = 1;
		for (File file : roots)
		{
			long total = file.getTotalSpace();
			if (total > 0)
			{
				disk.put("DeviceID" + "(" + index + ")", file.getPath());
				disk.put("VolumeName" + "(" + index + ")", file.getName());
				disk.put("intTotalSpace" + "(" + index + ")", total);
				disk.put("intFreeSpace" + "(" + index + ")", file
						.getFreeSpace());
				index++;
			}
		}
		return disk;
	}
	
	public static void main(String[] args) throws Exception {
		/*/UmsInfoUtils ums = new UmsInfoUtils();
		Properties cpuList = ums.getLinuxSystemMonitor("memory");
		System.out.println(cpuList);*/
	}

}