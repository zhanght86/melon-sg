package secfox.soc.las.asset.controller;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.commons.lang3.StringUtils;
import org.jdom.Attribute;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;

import secfox.soc.data.audit.model.T_deviceportModel;
import secfox.soc.data.audit.model.T_vulnModel;
import secfox.soc.data.audit.risk.DeviceVulnModel;
import secfox.soc.data.audit.risk.VulnExtensionMsgObject;
import secfox.soc.data.audit.risk.VulnImportRelationModel;
import secfox.soc.data.audit.util.VulnExtensionMsgTransformHelper;
import secfox.soc.las.asset.bean.T_DevicePort;
import secfox.soc.las.asset.bean.T_VulnFoundValue;
import secfox.soc.las.asset.bean.T_VulnImport;
import secfox.soc.las.asset.bean.T_VulnImportRelationBean;
import secfox.soc.las.asset.dao.VulnFoundStatDao;
import secfox.soc.las.asset.service.DevicePortService;
import secfox.soc.las.asset.service.VulnFoundStatService;
import secfox.soc.las.asset.service.VulnFoundValueService;
import secfox.soc.las.asset.service.VulnImportRelationService;
import secfox.soc.las.asset.service.VulnImportService;
import secfox.soc.las.asset.service.VulnService;
import secfox.soc.melon.asset.domain.Device;
import secfox.soc.melon.asset.service.DeviceService;
import secfox.soc.commons.util.XmlManager;

/**
 * 风险漏扫报告导入类
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/asset/vuln")
public class VulnController {

	private VulnService vulnService;
	private DeviceService deviceService;
	private VulnImportService vulnImportService;
	private VulnImportRelationService vulnImportRelationService;
	private VulnFoundStatService vulnFoundStatService;
	private VulnFoundValueService vulnFoundValueService;
	private DevicePortService devicePortService;
	private ObjectMapper objectMapper;

	private HashMap resultMap = null;
	private static int status = 0; // 记录当前是否处于导入状态,0-停止，1-运行，2-完成第一步导入
	long importTime;// 使用全局变量，统一导入时间
	private boolean stop = false;
	private String source;
	private static final int VULN_IFSYSTEM_SYSTEM = 0;
	private static final int VULN_IFSYSTEM_MANUAL = 1;
	private static SimpleDateFormat df = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm");// 解析XML文件的时间时使用
	private static SimpleDateFormat rjDF = new SimpleDateFormat(
			"yyyy年MM月dd日 E a KK:mm:ss", Locale.CHINA);
	long vulnimportID;
	@SuppressWarnings("unchecked")
	private static HashMap portTypeMap;// 获取XML文件中的端口配置信息
	//日志管理
	private final static Logger logger = LoggerFactory
			.getLogger(VulnController.class);

	@Inject
	public VulnController(VulnService vulnService, DeviceService deviceService,
			VulnImportService vulnImportService,
			VulnImportRelationService vulnImportRelationService,
			VulnFoundStatService vulnFoundStatService,
			VulnFoundValueService vulnFoundValueService,
			DevicePortService devicePortService,
			ObjectMapper objectMapper) {
		this.vulnService = vulnService;
		this.deviceService = deviceService;
		this.vulnImportService = vulnImportService;
		this.vulnImportRelationService = vulnImportRelationService;
		this.vulnFoundStatService = vulnFoundStatService;
		this.vulnFoundValueService = vulnFoundValueService;
		this.devicePortService = devicePortService;
		this.objectMapper = objectMapper;
	}

	/**
	 * 跳到漏扫结果导入页面
	 */
	@RequestMapping("/importModelPage")
	public String importModelPage() {
		return "asset.device.importModelPage";
	}

	/**
	 * 解析xml并持久,
	 * 第一步
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("/importModel")
	public String importModel(HttpServletRequest request,
			HttpServletResponse response, @RequestParam("files[]")
			MultipartFile file,Model model) {
		if (file != null) {

			ArrayList list = new ArrayList();
			ArrayList importList = new ArrayList();
			try {
				boolean sign = false;
				sign = file.getName().endsWith(".ss3");
				InputStream in = file.getInputStream();
				byte[] filebuf = file.getBytes();

				ByteArrayOutputStream out = new ByteArrayOutputStream();

				int result = -1;
				while ((result = in.read(filebuf)) != -1) {
					for (int i = 0; i < result; i++) {
						char tmp = (char) filebuf[i];
						switch (tmp) {
						case 0x9:
							break;
						case 0xA:
							break;
						case 0xD:
							break;
						default:
							if (!(tmp >= 0x20 && tmp <= 0xD7FF || tmp >= 0xE000
									&& tmp <= 0xFFFF || tmp >= 0x10000
									&& tmp <= 0x10FFFF)) {
								if (sign) {
									if (tmp != 0x02) {
										filebuf[i] = 0x20;
									}
								} else {
									filebuf[i] = 0x20;
								}
							}
						}
					}
					out.write(filebuf, 0, result);
				}

				byte[] fileData = out.toByteArray();

				out.close();
				if (sign) {
					list = importSS3VulnFirstStep(fileData);
				} else {

					list = importVulnFirstStep(fileData);
				}
				if (list != null) {
					for (int i = 0; i < list.size(); i++) {

						Object[] oImport = (Object[]) list.get(i);
						String ip = (String) oImport[0];
						long deviceId = Integer.parseInt(oImport[1].toString());
						String deviceName = "";
						if (deviceId != 0) {
							//获取资产名称
							//							deviceName = assetmanager.getDeviceName(deviceId);
						}
						String id = String.valueOf(deviceId);
						String[] information = new String[3];
						information[0] = id;
						information[1] = ip;
						information[2] = deviceName;
						importList.add(information);
					}
					List<Device> devList = new ArrayList<Device>();
					devList = deviceService.findAll();
					request.getSession().setAttribute("devList", devList);
					request.getSession().setAttribute("vulnimportlist",
							importList);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				model.addAttribute("errorException", "error");
			}
		}
		return "asset.device.importModelPage";
	}

	/**
	 * @return 执行xml第一步导入操作
	 * @throws JDOMException 
	 */
	@SuppressWarnings("unchecked")
	public ArrayList importVulnFirstStep(byte[] inputByte) throws JDOMException {
		status = 1;// 至当前导入状态为真
		importTime = System.currentTimeMillis();
		source = null;
		ArrayList resultList = null;
		HashMap<String, String> scanTaskMap = new HashMap<String, String>();
		ArrayList<Object[]> ipVulnList = new ArrayList<Object[]>();

		// 定义绿盟新版漏扫结果的标示符
		boolean isNSFOCUSflag = false;
		boolean isMOPSflag = false;
		try {

			XmlManager xmlTool = new XmlManager();
			xmlTool.read(new ByteArrayInputStream(inputByte));

			Element root = xmlTool.getRootElement();
			List<Element> children = root.getChildren();

			/**
			 * 判断是否为绿盟新版的xls格式漏扫结果，此种格式漏扫结果的根节点为Workbook 如果是绿盟新版漏扫结果，则退出此循环
			 */

			if (root.getName().equals("Workbook")) {
				isNSFOCUSflag = true;
			} else if (root.getName().equals("get_reports_response")) {
				isMOPSflag = true;

			} else {
				Element vulnlist = (Element) root.getChild("vuln-list");
				Element scanInfo = (Element) root.getChild("scan_info");

				if (vulnlist != null) {
					Iterator vulnList = vulnlist.getChildren("vuln").iterator();
					ArrayList vulnModelList = new ArrayList();
					HashMap ipVulnMap = new HashMap();
					while (vulnList.hasNext())// 首先组装启明星辰的t_vuln,resultList,以及ip--vulnid对应表
					{
						Element vuln = (Element) vulnList.next();
						HashMap vulnMap = new HashMap();
						Iterator vulnAttList = vuln.getAttributes().iterator();
						Iterator vulnInfoList = vuln.getChildren().iterator();
						while (vulnAttList.hasNext()) {
							Attribute vulnAtt = (Attribute) vulnAttList.next();
							vulnMap.put(vulnAtt.getName(), vulnAtt.getValue());
						}
						while (vulnInfoList.hasNext()) {
							Element vulnInfo = (Element) vulnInfoList.next();
							vulnMap
									.put(vulnInfo.getName(), vulnInfo
											.getValue());
						}
						vulnModelList.add(getVulnDicModel(vulnMap));
						ipVulnMap.put(vulnMap.get("asset-IP"), vulnMap
								.get("vuln-id"));
						ipVulnList.add(new Object[] { vulnMap.get("asset-IP"),
								vulnMap.get("vuln-id") });

					}
					saveDataToVuln(vulnModelList);
					resultList = getMatchDeviceList(ipVulnMap.keySet());
				} else if (scanInfo != null) {
					Map<String, String> hostIdIPMap = new HashMap<String, String>();
					List<Element> hosts = root.getChildren("host_info");
					for (Element ele : hosts) {
						hostIdIPMap.put(ele.getChild("host_id").getValue(), ele
								.getChild("ip").getValue());
					}

					List<Element> vulns = root.getChildren("vul_info");
					ArrayList<Object[]> vulndata = new ArrayList<Object[]>();
					for (Element ele : vulns) {
						Map<String, String> vuln = new HashMap<String, String>();
						for (Object attr : ele.getChildren())
							vuln.put(((Element) attr).getName(),
									((Element) attr).getValue());
						vulndata.add(getRJVulnDicModel(vuln));
					}
					saveDataToVuln(vulndata);
					source = "rongji";

					HashMap<String, String> taskMap = new HashMap<String, String>();
					taskMap.put("TASK_ID", String.valueOf(Long.valueOf(
							System.currentTimeMillis()).intValue()));
					taskMap
							.put("TASK_NAME", scanInfo
									.getChildText("scan_name"));
					taskMap.put("SCAN_COUNT", String.valueOf(scanInfo
							.getChildText("ip_scope").split(",").length));
					taskMap.put("START_TIME", scanInfo
							.getChildText("time_start"));
					taskMap.put("END_TIME", scanInfo.getChildText("time_end"));
					saveToVulnImport(taskMap);

					List<Element> flaws = root.getChildren("flaw_info");
					resultMap = new HashMap();
					for (Element ele : flaws) {
						String ip = hostIdIPMap
								.get(ele.getChildText("host_id"));
						ArrayList<HashMap> severityList = (ArrayList<HashMap>) resultMap
								.get(ip);
						if (severityList == null) {
							severityList = new ArrayList<HashMap>();
							resultMap.put(ip, severityList);
						}
						HashMap severity = new HashMap();
						severity.put("DOMAIN", ip);
						severity.put("VUL_ID", ele
								.getChildText("flaw_select_id"));
						severity.put("PORT", ele.getChildText("flaw_port")
								.split(" ")[0]);
						severity.put("PROTO", ele.getChildText("flaw_port")
								.indexOf(" ") > -1 ? ele.getChildText(
								"flaw_port").split(" ")[1] : ele.getChildText(
								"flaw_port").split(" ")[0]);
						severity
								.put("DESC", ele.getChildText("flaw_scan_info"));
						severityList.add(severity);
					}

					resultList = getMatchDeviceList(resultMap.keySet());
				}

				if (scanInfo == null) {
					List<Element> nodeList = children;

					for (Element element : nodeList) {
						if (element.getName().equals("Worksheet")) {
							/**
							 * 判断是否为绿盟新版的xls格式漏扫结果，此种格式漏扫结果root下的children只包含Worksheet节点
							 * 如果是绿盟新版漏扫结果，则退出此循环
							 */
							isNSFOCUSflag = true;
							break;
						}
						// 组装启明星辰有关t_vulnimport信息
						else if (element.getName().equals("data-source")
								|| element.getName().equals("scan-task-info")) {
							Iterator taskNodeList = element.getAttributes()
									.iterator();
							while (taskNodeList.hasNext()) {
								Attribute taskElement = (Attribute) taskNodeList
										.next();
								scanTaskMap.put(taskElement.getName(),
										taskElement.getValue());
							}
							source = "venustech";
						} else if (element.getName().equals("TASK")) { // 组装TASK信息
							Iterator taskNodeList = element.getChildren()
									.iterator();
							HashMap<String, String> taskMap = new HashMap<String, String>();
							while (taskNodeList.hasNext()) {
								Element taskElement = (Element) taskNodeList
										.next();
								taskMap.put(taskElement.getName(), taskElement
										.getValue());
							}
							saveToVulnImport(taskMap);// 保存Task信息到t_vulnimport表
							source = "nsfocus.aurora";
						}

						else if (element.getName().equals("RESULTS")) {
							resultMap = new HashMap();
							Iterator resultNodeList = element.getChildren()
									.iterator();// 包含RESULT节点
							while (resultNodeList.hasNext()) {
								String ip = "";
								ArrayList<HashMap> severityList = new ArrayList<HashMap>();
								Element resultElement = (Element) resultNodeList
										.next();
								Iterator baseElementList = resultElement
										.getChildren().iterator();// 包含TARGET_IP和SEVERITYS节点
								while (baseElementList.hasNext()) {
									Element baseElement = (Element) baseElementList
											.next();
									if (baseElement.getName().equals(
											"TARGET_IP")) {
										ip = baseElement.getValue();
									} else {
										Iterator severitysElementList = baseElement
												.getChildren().iterator();// 包含SEVERITY节点
										while (severitysElementList.hasNext()) {
											HashMap severityMap = new HashMap(4);
											Element severityElement = (Element) severitysElementList
													.next();
											Iterator severityElements = severityElement
													.getChildren().iterator();
											while (severityElements.hasNext()) {
												Element severity = (Element) severityElements
														.next();
												{
													severityMap
															.put(
																	severity
																			.getName(),
																	severity
																			.getValue());
												}
											}
											severityList.add(severityMap);
										}
									}
								}
								if (!ip.equals(""))
									resultMap.put(ip, severityList);
							}
							resultList = getMatchDeviceList(resultMap.keySet());

						} else if (element.getName().equals("host-list")) {
							resultMap = new HashMap();
							for (int i = 0; i < resultList.size(); i++) {

								Iterator hostList = element.getChildren(
										"hostip").iterator();

								Object[] ipResult = (Object[]) resultList
										.get(i);
								String ip = String.valueOf(ipResult[0]);
								while (hostList.hasNext()) {
									Element host = (Element) hostList.next();
									if (host.getAttributeValue("ip").equals(ip)) {
										ArrayList<HashMap> severityList = new ArrayList<HashMap>();
										for (int j = 0; j < ipVulnList.size(); j++) {
											Object[] ipVuln = (Object[]) ipVulnList
													.get(j);
											if (((String) ipVuln[0]).equals(ip)) {
												Element port = host
														.getChild("port-list");
												Iterator portList = port
														.getChildren()
														.iterator();
												int num = 0;

												while (portList.hasNext()) {

													Element portInfo = (Element) portList
															.next();
													// System.out.println("portList.hasNext()~~~~~~~~"+
													// num++);
													HashMap severityMap = new HashMap(
															4);
													severityMap
															.put(
																	"PROTO",
																	portInfo
																			.getAttribute(
																					"prototype")
																			.getValue());
													severityMap
															.put(
																	"PORT",
																	portInfo
																			.getAttribute(
																					"portno")
																			.getValue());
													severityMap.put("VUL_ID",
															ipVuln[1]);
													severityMap.put("DOMAIN",
															ip);
													severityList
															.add(severityMap);
												}
											}
										}

										resultMap.put(ip, severityList);
									}
								}
							}
						} else if (element.getName().equals("PLUGINS")) { // 绿盟极光的xml文件
							ArrayList pluginList = new ArrayList();
							HashMap pluginsMap = new HashMap(9);
							Iterator pluginsNodeList = element.getChildren()
									.iterator();// 包含PLUGIN节点
							while (pluginsNodeList.hasNext()) {
								Element pluginElement = (Element) pluginsNodeList
										.next();
								Iterator pluginNodeList = pluginElement
										.getChildren().iterator();// PLUGIN节点的子节点
								while (pluginNodeList.hasNext()) {
									Element plugin = (Element) pluginNodeList
											.next();
									pluginsMap.put(plugin.getName(), plugin
											.getValue());
								}
								pluginList
										.add(getVulnDicTableModel(pluginsMap));
							}
							saveDataToVuln(pluginList);
						}
						continue;
					}

				}

			}
			// 当为辽宁公安厅扫描结果时
			if (isMOPSflag) {

				DocumentBuilderFactory dbFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
				InputStream is = new ByteArrayInputStream(inputByte);
				Document doc = dbBuilder.parse(is);
				NodeList MOPSroot = doc
						.getElementsByTagName("get_reports_response");
				NodeList report = MOPSroot.item(0).getChildNodes();
				Node temp = report.item(0).getFirstChild();
				if (null != temp) {
					Node results = temp.getChildNodes().item(8);
					NodeList resultsList = results.getChildNodes();
					int x = resultsList.getLength();
					String ipAddress;
					String desc;
					String risk_factor = null;
					String nameNode = null;
					String cveDesc = null;
					ArrayList vulnModelList = new ArrayList();
					HashMap ipVulnMap = new HashMap();
					for (int i = 0; i < x; i++) {
						HashMap vulnMap = new HashMap();

						Node result = resultsList.item(i);
						NodeList rtList = result.getChildNodes();
						Node host = rtList.item(1);
						Node nvt = rtList.item(3);
						NodeList nvtList = nvt.getChildNodes();
						Node name = nvtList.item(0);
						Node risk = nvtList.item(2);
						Node cve = nvtList.item(3);
						if (risk.getFirstChild() == null
								|| risk.getFirstChild().getNodeValue() == null) {
							risk_factor = "none";
						} else {
							risk_factor = risk.getFirstChild().getNodeValue();
							if ("None".equals(risk_factor)) {
								risk_factor = "none";
							}
						}
						if (name.getFirstChild() == null
								|| name.getFirstChild().getNodeValue() == null) {
							nameNode = "none";
						} else {
							nameNode = name.getFirstChild().getNodeValue();
						}
						if (cve.getFirstChild() == null
								|| cve.getFirstChild().getNodeValue() == null) {
							cveDesc = "none";
						} else {
							cveDesc = cve.getFirstChild().getNodeValue();
						}

						Node description = rtList.item(5);
						ipAddress = host.getFirstChild().getNodeValue();
						if (description.getFirstChild().getNodeValue() == null) {
							desc = "none";
						} else {
							desc = description.getFirstChild().getNodeValue();
						}
						vulnMap.put("asset-IP", ipAddress);
						vulnMap.put("vuln-id", dataRandom() + importTime);
						vulnMap.put("vuln-severity", risk_factor);
						vulnMap.put("vuln-name", nameNode);
						vulnMap.put("vuln-cve", cveDesc);
						vulnMap.put("vuln-desc", desc);
						vulnModelList.add(getMOSPVulnDicModel(vulnMap));
						ipVulnMap.put(vulnMap.get("asset-IP"), vulnMap
								.get("vuln-id"));
						ipVulnList.add(new Object[] { vulnMap.get("asset-IP"),
								vulnMap.get("vuln-id") });
					}
					saveDataToVuln(vulnModelList);
					resultList = getMatchDeviceList(ipVulnMap.keySet());
					source = "MOSP.aurora";
					// 组装Task到scannTaskMap
					Node taskInfo = temp.getChildNodes().item(4);
					Node taskName = taskInfo.getFirstChild();
					String scanTaskName = taskName.getFirstChild()
							.getNodeValue();
					scanTaskMap.put("name", scanTaskName);
					if (scanTaskMap.size() != 0) {
						saveNSFOCUSTaskToVulnImport(scanTaskMap);
					}
					resultMap = new HashMap();
					for (int s = 0; s < resultList.size(); s++) {

						Object[] ipResult = (Object[]) resultList.get(s);
						String ip = String.valueOf(ipResult[0]);

						NodeList ipHost = temp.getChildNodes();
						int sum = ipHost.getLength();
						int num = 9;
						while (num < sum - 1) {
							Node ipName = ipHost.item(num);
							num = num + 2;
							Node hostIp = ipName.getChildNodes().item(1);
							String ipValue = hostIp.getFirstChild()
									.getNodeValue();
							if (ipValue.trim().equals(ip)) {
								ArrayList<HashMap> severityList = new ArrayList<HashMap>();
								for (int j = 0; j < ipVulnList.size(); j++) {
									Object[] ipVuln = (Object[]) ipVulnList
											.get(j);
									if (((String) ipVuln[0]).equals(ip)) {
										HashMap severityMap = new HashMap(4);
										severityMap.put("VUL_ID", ipVuln[1]);
										severityMap.put("DOMAIN", ip);
										severityMap.put("PORT", "0");
										severityMap.put("PROTO", "0");
										severityList.add(severityMap);
									}
								}
								resultMap.put(ipValue, severityList);
							}
						}

					}
				} else {
					return null;
				}

			}
			// 当为绿盟新版xls格式漏扫结果时
			else if (isNSFOCUSflag) {
				DocumentBuilderFactory dbFactory = DocumentBuilderFactory
						.newInstance();
				DocumentBuilder dbBuilder = dbFactory.newDocumentBuilder();
				InputStream is = new ByteArrayInputStream(inputByte);
				Document document = dbBuilder.parse(is);
				/**
				 * 获得文档属性 <DocumentProperties
				 * xmlns="urn:schemas-microsoft-com:office:office">
				 * <Author>yanhecun</Author> <LastAuthor>王小虎</LastAuthor>
				 * <Created>2007-06-04T01:05:25Z</Created>
				 * <LastSaved>2007-09-11T07:31:09Z</LastSaved> <Company>nsfocus</Company>
				 * <Version>12.00</Version> </DocumentProperties>
				 */
				NodeList docPro = document
						.getElementsByTagName("DocumentProperties");

				String author = ((org.w3c.dom.Element) docPro.item(0))
						.getElementsByTagName("Author").item(0).getFirstChild()
						.getNodeValue();
				String created = ((org.w3c.dom.Element) docPro.item(0))
						.getElementsByTagName("Created").item(0)
						.getFirstChild().getNodeValue();
				// 组装保存导入任务时用到的name字段，scanTaskMap
				String scanTaskName = author + "_" + created;
				scanTaskMap.put("name", scanTaskName);

				NodeList nodeList_NSFOCUS = document
						.getElementsByTagName("Worksheet");

				// for(int i=0;i<nodeList.getLength();i++){
				/**
				 * xls文件中，sheet的排序为：综述信息、主机列表、漏洞列表、脆弱账号
				 */
				// 1、先获得漏洞列表，有漏洞列表则进入下一步
				if (null != nodeList_NSFOCUS.item(2)) {
					org.w3c.dom.Element element = (org.w3c.dom.Element) nodeList_NSFOCUS
							.item(2);
					String name = element.getAttribute("ss:Name");
					if (name.equals("漏洞列表")) {
						ArrayList vulnModelList = new ArrayList();
						HashMap ipVulnMap = new HashMap();
						NodeList tablelist = element
								.getElementsByTagName("Table");
						// for (int r = 0; r < tablelist.getLength();
						// r++) {
						org.w3c.dom.Element table = null;
						if (null != tablelist && tablelist.getLength() > 0) {
							/**
							 * 每个漏洞列表sheet中只有一个Table，所以取tablelist.item(0)即可
							 */
							table = (org.w3c.dom.Element) tablelist.item(0);
						} else {
							return null;
						}

						NodeList rowList = table.getElementsByTagName("Row");
						/**
						 * 每个table中有多个Row，每个Row包含4个Cell
						 */
						for (int rl = 0; rl < rowList.getLength(); rl++) {
							/**
							 * 第一个Row为标题，需舍弃
							 */
							if (rl == 0) {
								continue;
							}
							String vulnId = null;
							String vulnServerity = null;
							String vulnName = null;
							String assionIpTmp = null;
							org.w3c.dom.Element row = (org.w3c.dom.Element) rowList
									.item(rl);
							NodeList cellList = row
									.getElementsByTagName("Cell");
							for (int j = 0; j < cellList.getLength(); j++) {
								org.w3c.dom.Element cell = (org.w3c.dom.Element) cellList
										.item(j);
								String result = cell.getElementsByTagName(
										"Data").item(0).getFirstChild()
										.getNodeValue();
								if (j == 0) {
									vulnServerity = result;
								}
								if (j == 1) {
									vulnName = result;
								}
								if (j == 2) {
									assionIpTmp = result;
								}
							}

							HashMap vulnMap = new HashMap();
							if (null != assionIpTmp
									&& assionIpTmp.indexOf(",") != -1) {
								String[] tmp = assionIpTmp.split(",");
								for (int s = 0; s < tmp.length; s++) {

									vulnMap.put("asset-IP", tmp[s]);
									vulnMap.put("vuln-id", dataRandom());
									vulnMap.put("vuln-severity", vulnServerity);
									vulnMap.put("vuln-name", vulnName);

									vulnModelList
											.add(getExVulnDicModel(vulnMap));
									ipVulnMap.put(vulnMap.get("asset-IP"),
											vulnMap.get("vuln-id"));
									ipVulnList.add(new Object[] {
											vulnMap.get("asset-IP"),
											vulnMap.get("vuln-id") });
								}
							} else {

								vulnMap.put("asset-IP", assionIpTmp);
								vulnMap.put("vuln-id", dataRandom());
								vulnMap.put("vuln-severity", vulnServerity);
								vulnMap.put("vuln-name", vulnName);
								vulnModelList.add(getExVulnDicModel(vulnMap));
								ipVulnMap.put(vulnMap.get("asset-IP"), vulnMap
										.get("vuln-id"));
								ipVulnList.add(new Object[] {
										vulnMap.get("asset-IP"),
										vulnMap.get("vuln-id") });
							}
						}

						// }
						saveDataToVuln(vulnModelList);
						resultList = getMatchDeviceList(ipVulnMap.keySet());
						source = "nsfocus.aurora";
					}
				} else {
					return null;
				}

				// 2、获得主机列表
				if (null != nodeList_NSFOCUS.item(1)) {
					org.w3c.dom.Element element = (org.w3c.dom.Element) nodeList_NSFOCUS
							.item(1);
					String name = element.getAttribute("ss:Name");
					if (name.equals("主机列表")) {
						NodeList tablelist = element
								.getElementsByTagName("Table");
						// for (int r = 0; r < tablelist.getLength();
						// r++) {
						org.w3c.dom.Element table = null;
						if (null != tablelist && tablelist.getLength() > 0) {
							/**
							 * 每个主机列表sheet中只有一个Table，所以取tablelist.item(0)即可
							 */
							table = (org.w3c.dom.Element) tablelist.item(0);
						} else {
							return null;
						}
						resultMap = new HashMap();
						for (int i = 0; i < resultList.size(); i++) {
							Object[] ipResult = (Object[]) resultList.get(i);
							String ip = String.valueOf(ipResult[0]);
							NodeList rowList = table
									.getElementsByTagName("Row");
							/**
							 * 每个table中有多个Row，每个Row包含8个Cell：IP地址、主机名、操作系统、高、中、低、风险值、风险等级
							 */
							for (int rl = 0; rl < rowList.getLength(); rl++) {
								/**
								 * 第一个Row为标题，需舍弃
								 */
								if (rl == 0) {
									continue;
								}
								String hostip = null;
								org.w3c.dom.Element row = (org.w3c.dom.Element) rowList
										.item(rl);
								NodeList cellList = row
										.getElementsByTagName("Cell");
								// 取Cell的第一个值为IP地址
								hostip = ((org.w3c.dom.Element) (cellList
										.item(0))).getElementsByTagName("Data")
										.item(0).getFirstChild().getNodeValue();
								if (hostip.trim().equals(ip)) {
									ArrayList<HashMap> severityList = new ArrayList<HashMap>();
									for (int j = 0; j < ipVulnList.size(); j++) {
										Object[] ipVuln = (Object[]) ipVulnList
												.get(j);
										if (((String) ipVuln[0]).equals(ip)) {
											HashMap severityMap = new HashMap(4);
											severityMap
													.put("VUL_ID", ipVuln[1]);
											severityMap.put("DOMAIN", ip);
											severityMap.put("PORT", "0");
											severityMap.put("PROTO", "0");
											severityList.add(severityMap);
										}
									}
									resultMap.put(ip, severityList);
								}

							}

						}
					} else {
						return null;
					}
				}
				if (scanTaskMap.size() != 0)
					saveNSFOCUSTaskToVulnImport(scanTaskMap);
			} else {
				if (scanTaskMap.size() != 0)
					saveTaskToVulnImport(scanTaskMap);
			}

		} catch (Exception e) {
			throw new JDOMException("import xml error");
		}
		if (source == null)
			return null;
		status = 2;// 完成第一步导入
		stop = false;
		return resultList;
	}

	public ArrayList importSS3VulnFirstStep(byte[] inputByte)
			throws IOException {
		status = 0;
		importTime = System.currentTimeMillis();// 使用全局变量，统一导入时间
		source = null;
		status = 1;// 至当前导入状态为真
		importTime = System.currentTimeMillis();
		source = null;
		ArrayList resultList = null;
		HashMap<String, String> scanTaskMap = new HashMap<String, String>();
		ArrayList<Object[]> ipVulnList = new ArrayList<Object[]>();
		String temp = null;
		String[] hostIp = null;
		String[] holesInfo = null;
		int num = 0;

		BufferedReader read = null;
		read = new BufferedReader(new InputStreamReader(
				new ByteArrayInputStream(inputByte)));
		ArrayList vulnModelList = new ArrayList();
		HashMap ipVulnMap = new HashMap();
		String tmpIp = "";
		String scanTaskName = "";
		while ((temp = read.readLine()) != null) {
			HashMap vulnMap = new HashMap();
			if (temp.indexOf("AllIP=") != -1) {
				String ipAddress = temp.substring(6);
				num = strSort(ipAddress, ";") + 1;
				hostIp = new String[num];
				hostIp = ipAddress.split(";");
				continue;
			}
			if (temp.indexOf("[Session]") != -1) {

				scanTaskName = read.readLine();
				if (scanTaskName.substring(5).equals("")
						|| scanTaskName.substring(5) == null) {

				} else {

					scanTaskMap.put("name", scanTaskName.substring(5));
					saveNSFOCUSTaskToVulnImport(scanTaskMap);
				}
			}
			if (temp.indexOf("[") != -1) {
				tmpIp = temp.substring(1, temp.length() - 1);
				continue;
			}
			if (temp.indexOf("HoleInfos") != -1) {
				String info = temp.substring(10);
				if (info.length() > 15) {
					Pattern pattern = Pattern.compile("\\d*(\\S)\\s*");
					Matcher matcher = pattern.matcher(info);
					if (matcher.find()) {

						String result = matcher.group(1);
						String infoChange = info.replaceAll(result, "%");
						holesInfo = infoChange.split("%");
						int s = strSort(infoChange, "%") + 1;
						holesInfo = infoChange.split("%");
						for (int j = 1; j < s;) {
							String vulnId = dataRandom() + importTime;
							vulnMap.put("asset-IP", tmpIp);
							vulnMap.put("vuln-id", vulnId);
							vulnMap.put("vuln-name", holesInfo[j]);
							j = j + 2;
							vulnModelList.add(getMOSPSS3VulnDicModel(vulnMap));
							ipVulnMap.put(vulnMap.get("asset-IP"), vulnMap
									.get("vuln-id"));
							ipVulnList.add(new Object[] {
									vulnMap.get("asset-IP"),
									vulnMap.get("vuln-id") });
						}

					}

				} else {
					continue;
				}
			}
			saveDataToVuln(vulnModelList);
			resultList = getMatchDeviceList(ipVulnMap.keySet());
			source = "MOSP.aurora";
		}
		resultMap = new HashMap();
		for (int s = 0; s < resultList.size(); s++) {

			Object[] ipResult = (Object[]) resultList.get(s);
			String ip = String.valueOf(ipResult[0]);
			for (int x = 0; x < num; x++) {
				String ipValue = hostIp[x];

				if (ipValue.trim().equals(ip)) {
					ArrayList<HashMap> severityList = new ArrayList<HashMap>();
					for (int j = 0; j < ipVulnList.size(); j++) {
						Object[] ipVuln = (Object[]) ipVulnList.get(j);
						if (((String) ipVuln[0]).equals(ip)) {
							HashMap severityMap = new HashMap(4);
							severityMap.put("VUL_ID", ipVuln[1]);
							severityMap.put("DOMAIN", ip);
							severityMap.put("PORT", "0");
							severityMap.put("PROTO", "0");
							severityList.add(severityMap);
						}
					}
					resultMap.put(ip, severityList);
				}
			}

		}

		if (source == null)
			return null;
		status = 2;// 完成第一步导入
		return resultList;
	}

	/**
	 * 
	 * @param vulnMap
	 * @return 组装哈希表中公安部ss3文件信息
	 */
	private Object[] getMOSPSS3VulnDicModel(Map<String, String> Map) {
		T_vulnModel model = new T_vulnModel();
		model.setVendor("VENUSTECH");
		model.setCreatedTime(importTime);
		model.setOriginalID((String) Map.get("vuln-id"));
		model.setVendorCode((String) Map.get("vuln-id"));
		model.setName((String) Map.get("vuln-name"));

		int temp = 0;
		if (Map.get("vuln-severity").trim().equals("High")
				|| Map.get("vuln-severity").trim().equals("Critical")) {
			temp = 3;
		} else if (Map.get("vuln-severity").trim().equals("Medium")) {
			temp = 2;
		} else if (Map.get("vuln-severity").trim().equals("Low")
				|| Map.get("vuln-severity").trim().equals("none")) {
			temp = 1;
		}
		model.setSeverity(temp);
		model.setOriSeverity("vuln-id");
		model.setVulnType(2);

		// model.setRemedy((String) Map.get("remedy"));
		// model.setDescription((String) Map.get("description"));
		model.setIfSystem(0);
		// 弱点导入后默认为内置弱点
		return new Object[] { (String) Map.get("vuln-id"), model };
	}

	/**
	 * 生成6位不重复的随机数
	 * 
	 * @return
	 */
	public String dataRandom() {
		Set<Integer> set = new HashSet<Integer>();
		Random r = new Random();
		StringBuffer s = new StringBuffer();
		while (set.size() < 6) {
			set.add(r.nextInt(10));
		}
		for (int v : set) {
			s.append(v);
		}
		String result = s.toString();
		return result;

	}

	/**
	 * @param pluginList
	 *            保存数据到t_vuln
	 */
	private void saveDataToVuln(ArrayList<Object[]> pluginList) {
		vulnService.addVulnObj(pluginList);
	}

	/**
	 * 保存绿盟Excel扫描结果Task信息到t_vulnimport表 Add by liyue 2011-10-17
	 * 
	 * @param Map
	 */
	private void saveNSFOCUSTaskToVulnImport(HashMap<String, String> Map) {

		T_VulnImport viModel = new T_VulnImport();
		viModel.setTaskName(Map.get("name"));
		viModel.setImportTime(importTime);
		viModel.setScanCount(2);
		viModel.setTaskID(importTime);
		viModel.setStartTime(importTime);
		viModel.setEndTime(importTime);

		vulnImportService.addVulnImport(viModel);
	}

	/**
	 * @param taskMap
	 *            保存极光扫描结果Task信息到t_vulnimport表
	 */
	private void saveToVulnImport(HashMap<String, String> taskMap) {

		T_VulnImport viModel = new T_VulnImport();
		viModel.setImportTime(importTime);
		viModel.setTaskID(Integer.valueOf((String) taskMap.get("TASK_ID")));
		viModel.setScanCount(Integer
				.valueOf((String) taskMap.get("SCAN_COUNT")));
		viModel.setTaskName((String) (String) taskMap.get("TASK_NAME"));

		try {
			String start = taskMap.get("START_TIME");
			Date startTime;
			if (start != null && start.indexOf("年") > -1)
				startTime = rjDF.parse(start.replaceAll("\\s+", " "));
			else
				startTime = df.parse(start);
			viModel.setStartTime(startTime.getTime());
		} catch (ParseException e) {
			viModel.setStartTime(importTime);
		}
		try {
			String end = taskMap.get("END_TIME");
			Date endTime;
			if (end != null && end.indexOf("年") > -1)

				endTime = rjDF.parse(end.replaceAll("\\s+", " "));

			else
				endTime = df.parse(end);
			viModel.setEndTime(endTime.getTime());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			viModel.setEndTime(importTime);
		}
		vulnImportService.addVulnImport(viModel);

	}

	/**
	 * 计算在一个字符串中指定字符的个数
	 * 
	 * @return
	 */
	public int strSort(String s1, String s2) {
		int num = 0;

		char[] c1 = new char[s1.length()];
		for (int a = 0; a < s1.length(); a++) {
			char c = s1.charAt(a);
			c1[a] = c;
		}

		char[] c2 = new char[s2.length()];
		for (int a = 0; a < s2.length(); a++) {
			char c = s2.charAt(a);
			c2[a] = c;
		}

		for (int a = 0; a < c1.length; a++) {
			// 如果当前字符与c2数组的第一个数相同
			// 则循环判断接下来的字符是否与c2数组相同
			if (c1[a] == c2[0]) {
				for (int b = 0; b < c2.length; b++) {
					if (c1[a + b] == c2[b]) {
						if (b == c2.length - 1) {
							// 如果接下来的几个字符与c2数组都相同，则加上1
							num++;
						}
					} else {
						// 如果接下来的几个字符有不相同的，则退出当前循环，继续外层循环
						break;
					}
				}
			}
		}
		return num;
	}

	/**
	 * @param set
	 * @return 根据IP获取设备ID，只获取发现的第一个设备
	 */
	@SuppressWarnings("unchecked")
	private ArrayList getMatchDeviceList(Set set) {
		ArrayList resultList = new ArrayList(); // 暂时不组装，等待前台确认
		HashMap map = new HashMap();
		for (Object cip : set) {
			Object cid = map.get(cip);
			if (cid != null)// 数据库中存在对应关系
			{
				resultList.add(new Object[] { cip, cid });
			} else // 数据库中没有对应的ip，则把ip的对应id置为0
			{
				resultList.add(new Object[] { cip, 0 });
			}
		}
		return resultList;
	}

	/**
	 * 
	 * @param Map
	 * @return 组装哈希表中启明星辰XML文件信息为特定的弱点字典表类型
	 */
	private Object[] getVulnDicModel(HashMap Map) {
		T_vulnModel model = new T_vulnModel();
		model.setVendor("VENUSTECH");
		model.setCreatedTime(importTime);
		model.setOriginalID((String) Map.get("vuln-id"));
		model.setVendorCode((String) Map.get("vuln-id"));
		model.setName((String) Map.get("vuln-name"));
		model.setSeverity(Integer.parseInt((String) Map.get("vuln-severity")));
		model.setOriSeverity((String) Map.get("vuln-severity"));
		model.setVulnType(2);
		model.setRemedy((String) Map.get("remedy"));
		model.setDescription((String) Map.get("description"));
		model.setIfSystem(VULN_IFSYSTEM_SYSTEM); // 2009-06-08 by zhuyan:
		// 弱点导入后默认为内置弱点
		return new Object[] { (String) Map.get("vuln-id"), model };
	}

	private Object[] getRJVulnDicModel(Map<String, String> vuln) {
		T_vulnModel model = new T_vulnModel();
		model.setOriginalID(vuln.get("vul_id"));
		model.setName(vuln.get("vul_name"));
		model.setOriSeverity(vuln.get("risk_level"));
		model.setSeverity("信息".equals(model.getOriSeverity())
				|| "低风险".equals(model.getOriSeverity()) ? 1 : ("高风险"
				.equals(model.getOriSeverity())
				|| "紧急风险".equals(model.getOriSeverity()) ? 3 : 2));
		model.setDescription(vuln.get("descript"));
		model.setRemedy(vuln.get("solution"));
		if (vuln.containsKey("cve_no"))
			model.setCVE(vuln.get("cve_no"));
		model.setVendor("RONGJI");
		model.setVendorCode(vuln.get("vul_id"));
		model.setIfSystem(VULN_IFSYSTEM_SYSTEM);
		model.setCreatedTime(importTime);
		model.setVulnType(2);

		return new Object[] { vuln.get("vul_id"), model };
	}

	/**
	 * @param pluginsMap
	 * @return 组装哈希表中极光XML文件信息为特定的弱点字典表类型
	 */
	@SuppressWarnings("unchecked")
	private Object[] getVulnDicTableModel(HashMap pluginsMap) {
		T_vulnModel model = new T_vulnModel();
		model.setOriginalID((String) pluginsMap.get("VUL_ID"));
		model.setVendor("NSFOCUS");
		model.setVendorCode((String) pluginsMap.get("NSFOCUS_ID"));
		model.setName((String) pluginsMap.get("NAME"));
		Integer oriSeverity = Integer.valueOf((String) pluginsMap.get("RISK"));
		model.setOriSeverity(oriSeverity.toString());
		model.setSeverity(1 <= oriSeverity && oriSeverity <= 4 ? 1
				: (5 <= oriSeverity && oriSeverity <= 7 ? 2 : 3));
		model.setDescription((String) pluginsMap.get("DESCRIPTION"));
		model.setRemedy((String) pluginsMap.get("SOLUTION"));
		model.setVulnType(2);
		model.setCVE((String) pluginsMap.get("CVE_ID"));
		model.setBUGTRAQ_ID((String) pluginsMap.get("BUGTRAQ_ID"));
		// model.setExtensionMsg("<PLUGIN_ID>" + pluginsMap.get("PLUGIN_ID")
		// + "</PLUGIN_ID>," + "<VUL_ID>" + pluginsMap.get("VUL_ID")
		// + "</VUL_ID>");
		VulnExtensionMsgObject extMsg = new VulnExtensionMsgObject();
		extMsg.addPlainElements("PLUGIN_ID", pluginsMap.get("PLUGIN_ID"));
		extMsg.addPlainElements("VUL_ID", pluginsMap.get("VUL_ID"));
		model.setExtensionMsg(VulnExtensionMsgTransformHelper
				.transformFromVulnExtensionMsgObjectToXML(extMsg));
		model.setCreatedTime(importTime);
		model.setIfSystem(VULN_IFSYSTEM_SYSTEM); // 2009-06-08 by zhuyan:
		// 弱点导入后默认为内置弱点
		return new Object[] { (String) pluginsMap.get("VUL_ID"), model };
	}

	/**
	 * 
	 * @param vulnMap
	 * @return 组装哈希表中公安部文件信息
	 */
	@SuppressWarnings("unused")
	private Object[] getMOSPVulnDicModel(Map<String, String> Map) {
		T_vulnModel model = new T_vulnModel();
		model.setVendor("VENUSTECH");
		model.setCreatedTime(importTime);
		model.setOriginalID((String) Map.get("vuln-id"));
		model.setVendorCode((String) Map.get("vuln-id"));
		model.setName((String) Map.get("vuln-name"));

		int temp = 0;
		if (Map.get("vuln-severity").trim().equals("High")
				|| Map.get("vuln-severity").trim().equals("Critical")) {
			temp = 3;
		} else if (Map.get("vuln-severity").trim().equals("Medium")) {
			temp = 2;
		} else if (Map.get("vuln-severity").trim().equals("Low")
				|| Map.get("vuln-severity").trim().equals("none")) {
			temp = 1;
		}
		model.setSeverity(temp);
		model.setOriSeverity("vuln-id");
		model.setVulnType(2);
		model.setCVE(Map.get("vuln-cve"));
		model.setDescription(Map.get("vuln-desc"));
		// model.setRemedy((String) Map.get("remedy"));
		// model.setDescription((String) Map.get("description"));
		model.setIfSystem(VULN_IFSYSTEM_SYSTEM);
		// 弱点导入后默认为内置弱点
		return new Object[] { (String) Map.get("vuln-id"), model };
	}

	/**
	 * 
	 * @param vulnMap
	 * @return 组装哈希表中绿盟excel文件信息
	 */
	@SuppressWarnings("unused")
	private Object[] getExVulnDicModel(Map<String, String> Map) {
		T_vulnModel model = new T_vulnModel();
		model.setVendor("VENUSTECH");
		model.setCreatedTime(importTime);
		model.setOriginalID((String) Map.get("vuln-id"));
		model.setVendorCode((String) Map.get("vuln-id"));
		model.setName((String) Map.get("vuln-name"));

		int temp = 0;
		if (Map.get("vuln-severity").trim().equals("[高]")) {
			temp = 3;
		} else if (Map.get("vuln-severity").trim().equals("[中]")) {
			temp = 2;
		} else if (Map.get("vuln-severity").trim().equals("[低]")) {
			temp = 1;
		}
		model.setSeverity(temp);
		model.setOriSeverity("vuln-id");
		model.setVulnType(2);
		// model.setRemedy((String) Map.get("remedy"));
		// model.setDescription((String) Map.get("description"));
		model.setIfSystem(VULN_IFSYSTEM_SYSTEM); // 2009-06-08 by zhuyan:
		// 弱点导入后默认为内置弱点
		return new Object[] { (String) Map.get("vuln-id"), model };
	}

	/**
	 * 保存启明星辰扫描结果Task信息到t_vulnimport表
	 * 
	 * @param Map
	 */
	private void saveTaskToVulnImport(HashMap<String, String> Map) {
		T_VulnImport viModel = new T_VulnImport();
		viModel.setTaskName(Map.get("name"));
		viModel.setImportTime(importTime);
		viModel.setScanCount(2);
		try {
			String createtime = Map.get("create-time").replace("/", "-");
			Date createTime = df.parse(createtime);
			viModel.setTaskID(createTime.getTime());
		} catch (ParseException e) {
			//			logger.error(e);
			viModel.setTaskID(importTime);
		}
		try {
			Date startTime = df.parse((String) Map.get("begin-time").replace(
					"/", "-"));
			viModel.setStartTime(startTime.getTime());
		} catch (ParseException e) {
			viModel.setStartTime(importTime);
		}
		try {
			Date endTime = df.parse((String) Map.get("end-time").replace("/",
					"-"));
			viModel.setEndTime(endTime.getTime());
		} catch (ParseException e) {
			viModel.setEndTime(importTime);
		}
		vulnImportService.addVulnImport(viModel);
	}

	/**
	 * 解析xml并持久,
	 * 第二步
	 */
	@RequestMapping(value = "/importSecondStep", method = RequestMethod.POST)
	public String importSecondStep(HttpServletRequest request,
			HttpServletResponse response) {
		HttpSession session = request.getSession();
		ArrayList list = new ArrayList();
		ArrayList al = new ArrayList();
		long deviceId = 0;
		//选择的弱点id
		String[] ids = null;//request.getParameterValues("objs");
		String[] objs = request.getParameterValues("objs");
		String[] values = null;
		String[] assets = null;
		HashMap<String, ArrayList<String>> idipListMap = new HashMap<String, ArrayList<String>>();

		for (String obj : objs) {
			if (!StringUtils.isBlank(obj)) {
				values = obj.split(",");//11.23.12.23,0000_name,11111_name
				Object[] temp = new Object[2];
				temp[0] = values[0];

				assets = values[1].split("_");
				temp[1] = assets[0];
				al.add(temp);

				ArrayList<String> ipList = null;
				if (idipListMap.containsKey(assets[0])) {
					ipList = idipListMap.get(assets[0]);
				} else {
					ipList = new ArrayList<String>();
					idipListMap.put(assets[0], ipList);
				}
				ipList.add(values[0]);
			}
		}

		saveDataToVulnFoundTable(al);

		HashMap<String, String> idipMap = new HashMap<String, String>();
		for (String id : idipListMap.keySet()) {
			StringBuilder strBuilder = new StringBuilder();
			ArrayList<String> ipList = idipListMap.get(id);
			int listSize = ipList.size();
			for (int i = 0; i < listSize; i++) {
				strBuilder.append(ipList.get(i));
				if (i != (listSize - 1)) {
					strBuilder.append(", ");
				}
			}
			idipMap.put(id, strBuilder.toString());
		}

		ArrayList<String> temp = new ArrayList<String>();

		for (String obj : objs) {
			if (!StringUtils.isBlank(obj)) {
				values = obj.split(",");//11.23.12.23,0000_name,11111_name
				Object[] tmp = new Object[6];
				assets = values[1].split("_");
				String tempId = assets[0];
				if (tempId != null && !"".equals(tempId)
						&& !temp.contains(tempId)) {
					temp.add(tempId);
					deviceId = Long.parseLong(tempId);
					int[] tempArray = vulnImportRelationService
							.getImportInfo(deviceId);
					if (tempArray != null) {
						String tmpCount = String.valueOf(tempArray[0]);
						String tmpSevCount = String.valueOf(tempArray[1]);
						tmp[3] = String.valueOf(tempArray[0]);
						tmp[2] = String.valueOf(tempArray[1]);
						tmp[5] = Math.round(Float.parseFloat(tmpSevCount)
								/ Float.parseFloat(tmpCount) * 100);
					}
					tmp[0] = deviceId;
					tmp[1] = idipMap.get(tempId);
					tmp[4] = assets[1];
					list.add(tmp);
				}
			}
		}
		session.setAttribute("vulnfoundlist", list);

		return "asset.device.vulnList";
	}

	/**
	 * 弱点导入第二步，将弱点关联到资产上
	 * 
	 * @param list
	 *            元素为Object[2]，Object[0]为IP，Object[1]为关联资产的ID，如果没有关联资产，则为0
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public int saveDataToVulnFoundTable(ArrayList list) {
		if (resultMap == null) {
			logger
					.warn("VulnImportMbean.saveDataToVulnFoundTable: resultMap is null!");
			return 3;
		}

		// 获取弱点原id与t_vuln中id的映射关系，key为原id，value为t_vuln表id
		HashMap<String, Long> vulnIDMap = vulnService.getRelationMap();

		// 从参数list中提取出ip与id的对应关系，id为0的项排除
		HashMap ipidMap = new HashMap();
		for (Object ipid : list) {
			Object[] temp = (Object[]) ipid;
			String ip = (String) temp[0];
			long id = (Long.parseLong(temp[1].toString()));
			if (id != 0) {
				ipidMap.put(ip, id);
			}
		}

		// 一个ip只关联一个id，而一个id可能被多个ip关联，因此需要再根据ip与id的对应关系获取id与ip的对应关系
		// 其中，key为id，value为与此id关联的ip列表
		HashMap<Long, ArrayList> idipMap = new HashMap<Long, ArrayList>();
		for (Object ip : ipidMap.keySet()) {
			Long id = (Long) ipidMap.get(ip);
			ArrayList ipList = null;
			if (idipMap.containsKey(id)) {
				ipList = idipMap.get(id);
			} else {
				ipList = new ArrayList();
				idipMap.put(id, ipList);
			}
			ipList.add(ip);
		}

		// 每个ip对应一组弱点，一个id可能关联几个ip，这些ip对应的弱点可能有重复，因此需将这些弱点整合
		// 整合后的HashMap，key为资产id，value为关联到该资产上的弱点集合
		HashMap<Long, Collection<HashMap>> idSeverityMap = new HashMap<Long, Collection<HashMap>>();
		for (Long id : idipMap.keySet()) {
			HashMap<Object, HashMap> severityMap = new HashMap<Object, HashMap>();
			ArrayList ipList = idipMap.get(id);
			for (Object ip : ipList) {
				ArrayList<HashMap> severityList = (ArrayList<HashMap>) resultMap
						.get(ip);
				for (HashMap severity : severityList) {
					Object vuln_id = severity.get("VUL_ID");
					if (!severityMap.containsKey(vuln_id)) {
						severityMap.put(vuln_id, severity);
					}
				}
			}
			idSeverityMap.put(id, severityMap.values());
		}

		// 针对每个资产，将关联的弱点导入
		for (Long id : idSeverityMap.keySet()) {
			if (stop)
				break;

			ArrayList<DeviceVulnModel> deviceVulnList = null;
			ArrayList<DeviceVulnModel> tempVulnList = null;
			// 查找该资产现有的弱点；如果数据库没有相应的表，则进行创建
			deviceVulnList = vulnImportRelationService.getVulnListByDevID(id
					.longValue(), importTime);
			tempVulnList = (ArrayList<DeviceVulnModel>) deviceVulnList.clone();

			Collection<HashMap> severities = idSeverityMap.get(id);
			ArrayList<DeviceVulnModel> rsList = new ArrayList<DeviceVulnModel>();
			ArrayList<String> templist = new ArrayList<String>();
			for (HashMap severity : severities) {
				String deviceID = (String) severity.get("DOMAIN");
				String vulnID = (String) severity.get("VUL_ID");
				if (vulnIDMap.get(vulnID) == null) {
					// 如果Result中存在而plugin中没有，则抛弃
					continue;
				}
				String distinct = deviceID + "_" + vulnID;
				if (!templist.contains(distinct)) {
					// deviceID及vulnID都相同的项，不进行添加
					templist.add(distinct);
					boolean exist = false;
					for (DeviceVulnModel model : tempVulnList) {
						if (model.getVulnID() == vulnIDMap.get(vulnID)) {
							// 弱点复现
							deviceVulnList.remove(model);
							int preStatus = model.getStatus();
							if (preStatus == 1) {
								model.setStatus(2);
							}
							model.setTag(2);
							model.setFoundTime(importTime);
							rsList.add(model);
							exist = true;
							break;
						}
					}
					if (!exist) {
						// 弱点新增
						DeviceVulnModel model = new DeviceVulnModel();
						model.setVulnID(vulnIDMap.get(vulnID));
						model.setDeviceID(id);
						model.setStatus(2);
						model.setFoundTime(importTime);
						model.setTag(1);
						model.setSource(source);
						if (severity.containsKey("DESC"))
							model.setDescription((String) severity.get("DESC"));
						rsList.add(model);
					}
				}
			}
			for (DeviceVulnModel model : deviceVulnList) {
				// 上次记录的弱点在本次没有记录，则未消除的弱点标记为遗留弱点
				if (model.getStatus() != 2)
					continue;

				model.setTag(3);
				model.setFoundTime(importTime);
				rsList.add(model);
			}
			saveDataToVulnFound(rsList); // 保存数据到VulnFound表中
			calcuVulnFoundValueAndSave(id); // 弱点计算中间数据计算和保存
		}

		// 针对每个id和每个ip，保存数据到弱点导入关系表和t_deviceport中
		for (Long id : idipMap.keySet()) {
			ArrayList ipList = idipMap.get(id);
			for (Object ip : ipList) {
				ArrayList<HashMap> severityList = (ArrayList<HashMap>) resultMap
						.get(ip);
				ArrayList<T_DevicePort> portList = new ArrayList<T_DevicePort>();
				int vulnSize = 0; // 弱点数量
				int portSize = 0; // 弱点相关的端口数量
				ArrayList<String> templist = new ArrayList<String>();
				for (HashMap severity : severityList) {
					String deviceID = (String) severity.get("DOMAIN");
					String vulnID = (String) severity.get("VUL_ID");
					if (vulnIDMap.get(vulnID) == null) {
						// 如果Result中存在而plugin中没有，则抛弃
						continue;
					}
					String distinct = deviceID + "_" + vulnID;
					if (!templist.contains(distinct)) {
						// deviceID及vulnID都相同的项，不进行添加
						templist.add(distinct);
						vulnSize++;
					}

					// 插入数据到t_deviceport
					String type = (String) severity.get("PROTO");
					String port = (String) severity.get("PORT");
					portSize++;
					T_DevicePort pdm = new T_DevicePort();
					pdm.setDeviceID(id);
					pdm.setIp(String.valueOf(ip));
					pdm.setPort(Integer.parseInt(port));
					pdm.setType(getPortTypeMap().get(type) == null ? type
							: String.valueOf(getPortTypeMap().get(type)));
					pdm.setFoundTime(importTime);
					pdm.setVulnID(vulnIDMap.get(vulnID));
					portList.add(pdm);
				}
				T_VulnImportRelationBean virm = getVulnImportRelationModel(
						String.valueOf(ip), id, vulnSize, portSize);
				saveDataToVulnImportRelation(virm); // 保存数据到弱点导入关系记录表
				saveDataToDevicePortTable(portList); // 保存数据到deviceport表中
			}
		}

		status = 0;
		return status;
	}

	/**
	 * 弱点计算中间数据计算和保存
	 * 
	 * @param long1
	 */
	private void calcuVulnFoundValueAndSave(long id) {
		T_VulnFoundValue model = new T_VulnFoundValue();
		model.setDeviceID(id);
		//		// 暂时先将VulnValue和CalRecords置为0，调用下面的statAndSaveDeviceCurrentVulnInfo方法时会更新这两个字段
		model.setCalTime(importTime);
		model.setVulnValue(0);
		model.setCalRecords(0);
		model.setExt("");
		vulnFoundValueService.addVulnFoundValue(model);
		vulnFoundValueService.refresVulnFoundValue(model);
	}

	/**
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private HashMap getPortTypeMap() {
		if (portTypeMap != null) {
			return portTypeMap;
		}
		portTypeMap = new HashMap();
		// TODO yangzq
		//		ArrayList protoList = (ArrayList) ApplicationContextService
		//				.getInstance().getBean("protocolList");
		//		for (Object dic : protoList) {
		//			DicTable dt = (DicTable) dic;
		//			portTypeMap.put(dt.getValue(), dt.getText());
		//
		//		}
		// List中的元素是secfox.soc.data.audit.DicTable类型
		// value属性保存的是协议的编号
		// text属性保存的是协议的名称
		return portTypeMap;

	}

	/**
	 * @param string
	 * @param integer
	 * @param i
	 * @param j
	 * @return
	 */
	private T_VulnImportRelationBean getVulnImportRelationModel(String ip,
			long deviceID, int vulnCount, int portCount) {
		T_VulnImportRelationBean model = new T_VulnImportRelationBean();
		model.setDeviceID(deviceID);
		model.setImportID(vulnimportID);
		model.setIp(ip);
		model.setVulnCount(vulnCount);
		model.setPortCount(portCount);
		return model;
	}

	/**
	 * @param ip
	 * @param integer
	 *            id 保存数据到弱点导入关系记录表
	 */
	private void saveDataToVulnImportRelation(T_VulnImportRelationBean model) {
		model.setImportTime(importTime);
		vulnImportRelationService.addVulnImportRelation(model);
	}

	/**
	 * @param rsList
	 *            保存数据到VulnFound表中
	 */
	private void saveDataToVulnFound(ArrayList<DeviceVulnModel> rsList) {
		vulnFoundValueService.saveDataToVulnFoundTable(rsList, importTime);
	}

	/**
	 * @param dvModel
	 *            importTime 更新VulnFound表中一条记录
	 * @param importTime
	 */
	public boolean updateVuln(DeviceVulnModel dvModel) {
		T_VulnFoundValue parm = vulnFoundValueService.updateVuln(dvModel);
		return vulnFoundValueService.refresVulnFoundValue(parm);

	}

	/**
	 * @param dvModel
	 *            importTime 添加VulnFound表中一条记录
	 * @param importTime
	 */
	public boolean insertVuln(DeviceVulnModel dvModel) {
		vulnFoundValueService.insertVuln(dvModel);
		return false;
	}

	/**
	 * @param portList
	 *            保存数据到deviceport表中
	 */
	private void saveDataToDevicePortTable(ArrayList<T_DevicePort> portList) {
		devicePortService.addDevicePort(portList);
	}
}
