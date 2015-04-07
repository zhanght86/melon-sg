package secfox.soc.sim.commons;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import secfox.soc.commons.adaptor.ComponentProxy;
import secfox.soc.commons.adaptor.ComponentProxyPool;
import secfox.soc.commons.adaptor.ProxyException;
import secfox.soc.commons.common.Category;
import secfox.soc.data.audit.DicTable;
import secfox.soc.data.audit.EventDicTable;
import secfox.soc.data.authority.UserObject;
public class LoadParmServlet {

	//用户登录 encrypted 密码是否加密
	public static UserObject getUserByCondition(String username,String password,boolean encrypted,String ip) throws Exception
	{
		UserObject userObject = null;
		ComponentProxy proxy = null;
		try {
//			Map env = new HashMap();
//			proxy = ComponentProxyPool.getComponentProxy("Local", env);
			Map env = InvokeHelper.createEnvForInit();
			env.put("uname", username);
			env.put("source", ip);
			proxy = ComponentProxyPool.getComponentProxy("Local", env);
			String[] name = new String[] {"java.lang.String","java.lang.String","boolean"};
			Object[] value = new Object[] {username,password,encrypted};
			userObject = (UserObject)proxy.invoke("LogonMBean",
					"logonSys", value, name);			
		}finally {
			if (proxy != null)
				proxy.close();
		}
		return userObject;
	}

	
	public static void init(){
		String ip = "10.70.63.201";
		String port = "8099";
		ComponentProxyPool.setProperty("protocol", "soap");
		ComponentProxyPool.setProperty("Local", ip+":"+port);
		ComponentProxy proxy = null;
    	EventDicTable dic = null;
    	try
    	{
    		UserObject user = getUserByCondition("admin","admin123",false,"127.0.0.1");
    		System.out.println(user);
    		/*
    		 * 例子
    		 * Map env = InvokeHelper.createEnv(user);
    		String[] name = new String[] {};
    		Object[] value = new Object[] {};
    		
    		proxy = ComponentProxyPool.getComponentProxy("Local", env);
    		name = new String[] {"long"};
			value = new Object[] {4L};
    		System.out.println(proxy.invoke("NodeManagerProxy", "getDeviceObjectList", null, null)+"sdfasdf");*/
    	}
    	catch (Exception e)
    	{//ProxyException, PermissionException
    		e.printStackTrace();
    	}
    	finally
    	{
    		if (proxy != null)
				try
				{
					proxy.close();
				}
				catch (ProxyException e)
				{
					//
				}
    	}
	}
	public static void main(String[] args){
		init();
	}
}
