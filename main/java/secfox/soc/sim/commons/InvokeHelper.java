package secfox.soc.sim.commons;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import secfox.soc.commons.adaptor.ProxyException;
import secfox.soc.data.authority.UserObject;

/**
 * MBean服务器调用辅助类
 * @author liunan
 *
 */
public class InvokeHelper
{
	/**
	 * 创建MBean调用的环境变量
	 * @param user
	 * @return
	 */
	public static final Map createEnv(UserObject user) throws ProxyException
	{
		if(user == null)
			throw new ProxyException("errors.notlogin");
		Map env = new HashMap();
		env.put("uid", user.getUserID());
		env.put("uname", user.getUserName());
		env.put("source", user.getProperties().get("source"));
		
		return env;
	}
	/**
	 * 创建初始化的环境变量
	 * @return
	 */
	public static final Map createEnvForInit()
	{
		Map env = new HashMap();		
		try
		{
			env.put("uname", "");
			env.put("source",InetAddress.getLocalHost().getHostAddress());
		}
		catch (UnknownHostException e)
		{
			env.put("source", "");
		}
		return env;
	}
}
