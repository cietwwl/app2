package com.chuangyou.xianni.common;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.chuangyou.common.util.ClassUtil;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.BaseServer;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;

/**
 * 命令管理器
 * 
 */
public final class CommandSet {

	/**
	 * 缓存命令对象
	 **/
	private static final Map<Short, Command> cmdCache = new HashMap<Short, Command>();

	private CommandSet() {
	}

	/**
	 * 加载命令集合
	 * 
	 * @param configFile
	 */
	public static boolean load() {
		try {
			if (!checkCommand()) {
				return false;
			}

			Package pack = BaseServer.class.getPackage();
			Set<Class<?>> allClasses = ClassUtil.getClasses(pack);

			for (Class<?> clazz : allClasses) {
				try {
					Cmd cmd = clazz.getAnnotation(Cmd.class);
					if (cmd != null) {
						if (cmdCache.get(cmd.code()) != null) {
							String name = cmdCache.get(cmd.code()).getClass().getName();
							Log.error("cmd code error, code : 0x" + Integer.toHexString((int) cmd.code()).toUpperCase()
									+ " exist :" + name + ", new : " + clazz.getName());
							return false;
						}
						cmdCache.put(cmd.code(), (Command) clazz.newInstance());
						continue;
					}

				} catch (Exception e) {
					Log.error("load command fail, command name : " + clazz.getName(), e);
					e.printStackTrace();
				}
			}

			Log.info("cmdCache size : " + cmdCache.size());
			return true;
		} catch (Exception e) {
			Log.error("命令管理器解析错误", e);
			return false;
		}
	}

	/**
	 * 缓存中获取命令
	 * 
	 * @param code
	 * @return
	 */
	public static Command getCommand(short code) {
		return cmdCache.get(code);
	}

	private static boolean checkCommand() {
		try {
			Class<?> clazz = Class.forName("com.chuangyou.xianni.protocol.Protocol");
			Map<Short, String> checkedFields = new HashMap<Short, String>();
			Field[] fields = clazz.getFields();
			for (Field f : fields) {
				short key = f.getShort(f.getType());
				if (checkedFields.containsKey(key)) {
					Log.error("协议冲突，类：" + checkedFields.get(key) + "	协议："
							+key);
					return false;
				} else {
					checkedFields.put(key, f.getDeclaringClass().getName());
				}
			}
		} catch (Exception e) {
			Log.error("检测协议时出错", e);
			return false;
		}
		return true;
	}
}
