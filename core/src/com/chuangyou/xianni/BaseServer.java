package com.chuangyou.xianni;

import com.chuangyou.common.util.Config;
import com.chuangyou.common.util.LanguageSet;
import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.NetConfigSet;
import com.chuangyou.xianni.common.CommandSet;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.HttpCommandSet;

public abstract class BaseServer {
	private long			lastInitMillis;		// 模块加载时间
	protected static String	configPath;
	public static boolean	isCross		= false;

	private int				id;					// 服务器ID
	private boolean			terminate	= true;

	public boolean start() throws Exception {
		if (!initComponent(Config.initConfig(BaseServer.class.getResource("/").getPath(), configPath), "初始化文件加载")) {
			return false;
		}

		id = Config.getInt("server_id");

		if (!initComponent(NetConfigSet.init(), "初始化网络连接信息")) {
			return false;
		}

		if (!initComponent(CommandSet.load(), "化命令加载")) {
			return false;
		}

		if (!initComponent(HttpCommandSet.load(), "HTTP命令加载")) {
			return false;
		}

		if (!initComponent(LanguageSet.loadLanguage(Config.getValue("language")), "初始化语言包")) {
			return false;
		}
		if (!initComponent(ErrorCode.checkErrorCode(), "检查错误码")) {
			return false;
		}

		// TODO 语言包测试
		// System.err.println(LanguageSet.getResource("CenterServer.test.testAlert"));
		return true;
	}

	/**
	 * 初始化相关模块
	 * 
	 * @param initResult
	 * @param componentName
	 * @return
	 */
	public boolean initComponent(boolean initResult, String componentName) {
		if (!initResult) {
			Log.error(componentName + "错误");
		} else {
			Log.info(componentName + "加载完成.耗时 : " + (System.currentTimeMillis() - lastInitMillis) + " ms");
			System.err.println(componentName + "加载完成.耗时 : " + (System.currentTimeMillis() - lastInitMillis) + " ms");
		}
		lastInitMillis = System.currentTimeMillis();
		return initResult;
	}

	public boolean isTerminate() {
		return terminate;
	}

	public void setTerminate(boolean terminate) {
		this.terminate = terminate;
	}

	public int getId() {
		return id;
	}

}
