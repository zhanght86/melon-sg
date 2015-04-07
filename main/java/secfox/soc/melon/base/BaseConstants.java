/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package secfox.soc.melon.base;

/**
 * @since 1.0 2014年10月2日,上午10:28:55
 * 每个架包都可以提供单独的常量声明，为本架包提供常量服务，命名必须为包名[首字母大写]+Constants
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public interface BaseConstants {
	
	/**
	 * 长日期时间格式，精确到秒
	 */
	static final String LONG_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	
	/**
	 * 短日期时间格式，只精确到天
	 */
	static final String SHORT_DATE_FORMAT = "yyyy-MM-dd";
	
	/**
	 * 时间格式，精确到分钟
	 */
	static final String TIME_FORMAT = "HH:mm";
	
	/**
	 * 默认的字符分割符,逗号
	 */
	static final String SPLITER_FLAG = ",";
	
	/**
	 * 空白字符串
	 */
	static final String BLANK_STRING = "";
	
	/**
	 * 空格字符串
	 */
	static final String SPACING_STRING = " ";
	
	/**
	 * 键值分割符,冒号
	 */
	static final String VALUE_LABEL_SPLITER = ":";
	
	/**
	 * 键值分割符,分号
	 */
	static final String SEMI_SPLITER = ";";
	
	/**
	 * 
	 */
	final static String ENUM_PREFIX = "enums."; 
	
	/**
	 * 平台默认的根节点ID
	 */
	final static Long ROOT_ID = 1L;
	
	/**
	 * 加密key值
	 */
	static final String ENCRYPT = "melon";
	
	/**
	 * 初始口令表验证密码
	 */
	static final String DEFAULTSECPASS = "123456";
	
	static final String DEFAULTORDER = "0000";
	
	/**
	 * 内部人员编码
	 */
	static final String INTERSTAFF = "NB";
	
	/**
	 * 外部人员编码
	 */
	static final String OUTERSTAFF = "WB";
	
	/**
	 * 外部人员编码
	 */
	static final String OUTSTAFF = "WB";
	
	/**
	 * url参数
	 */
	final static String URL_PARA = "?";
	
	/**
	 * url参数连接
	 */
	final static String URL_CONN = "&";
	
	/**
	 * CDATA
	 */
	static final String PREFIX_CDATA = "<![CDATA[";
	
	/**
	 * 
	 */
	static final String SUFFIX_CDATA = "]]>";
	
	/**
	 * bracket
	 */
	static final String PREFIX_BRACKET = "(";
	
	/**
	 * 
	 */
	static final String SUFFIX_BRACKET = ")";
	
	//安全人员序号
	static final String ORDER_FIR = "000";
	static final String ORDER_SEC = "00";
	static final String ORDER_TRD = "0";
	
	//yb 类型Id
	static final Long TYPE_WLH=81413L;	//物理环境
	static final Long TYPE_INFO=230L;	//信息系统
	static final Long TYPE_GROUP=231L;	//安全域
	static final Long TYPE_SERVICE=30211L;	//业务系
	static final Long TYPE_ENVI=1L;		//机房
	static final Long TYPE_NETDEVICE=1150L;		//网络设备
	static final Long TYPE_SAFE=1196L;		//安全系统
	static final Long TYPE_MAIN=30212L;		//主机
	static final Long TYPE_MIDDLE=30216L;	//中间件
	static final Long TYPE_APP=30214L;		//应用软件
	static final Long TYPE_DB=1219L;		//数据库服务器
	static final Long TYPE_SAVE=1218L;	//存储设备
	static final Long TYPE_TERMINAL=1223L;	//终端
	
	static final Long PIE_MONITOR=2L;	//被监控
	static final Long PIE_ANALYSE=4L;	//被审计
}
