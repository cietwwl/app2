package com.chuangyou.xianni.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.chuangyou.common.util.ClassUtil;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.BaseServer;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;

/**
 * HTTP命令集合
 * @author laofan
 *
 */
public final class HttpCommandSet {
	/**
	 * 缓存命令对象
	 **/
	private static final Map<String, BaseRespone> httpCmdCache = new HashMap<String, BaseRespone>();
	

	private HttpCommandSet() {
		
	}
	
	
	/**
	 * 缓存中获取命令
	 * 
	 * @param code
	 * @return
	 */
	public static BaseRespone getHttpRespone(String command) {
		return httpCmdCache.get(command);
	}
	
	public static boolean load(){
		Package pack = BaseServer.class.getPackage();
		Set<Class<?>> allClasses = ClassUtil.getClasses(pack);
		for (Class<?> clazz : allClasses) {
			try {
				 HttpCmd cmd = clazz.getAnnotation(HttpCmd.class);
				if (cmd != null) {
					if (httpCmdCache.get(cmd.command()) != null) {
						String name = httpCmdCache.get(cmd.command()).getClass().getName();
						Log.error("HttpCmd command error, command : " + cmd.command()
								+ " exist :" + name + ", new : " + clazz.getName());
						return false;
					}
					httpCmdCache.put(cmd.command(), (BaseRespone) clazz.newInstance());
					continue;
				}

			} catch (Exception e) {
				Log.error("load command fail, command name : " + clazz.getName(), e);
				e.printStackTrace();
			}
		}
		Log.info("httpCmdCache size : " + httpCmdCache.size());
		return true;
	}
	
	
	
	
}
