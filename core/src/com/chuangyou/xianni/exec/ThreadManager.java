package com.chuangyou.xianni.exec;

import java.util.Random;

/**
 * 线程管理类
 */
public class ThreadManager {

	static Random					r	= new Random();
	// 用户响应动作执行线程池
	public static ActionExecutor	actionExecutor;

	static {
		int corePoolSize = 8;
		int maxPoolSize = 32;
		int keepAliveTime = 5;
		int cacheSize = 64;
		actionExecutor = new ActionExecutor(corePoolSize, maxPoolSize, keepAliveTime, cacheSize, "WORD_ACTION_EXECUTOR");
	}

	// 用户请求动作执行线程池
	public static CmdExecutor cmdExecutor;

	static {
		int corePoolSize = 8;
		int maxPoolSize = 32;
		int keepAliveTime = 5;
		int cacheSize = 64;
		cmdExecutor = new CmdExecutor(corePoolSize, maxPoolSize, keepAliveTime, cacheSize, "WORD_CMD_TASK_EXECUTOR");
	}

	// 执行战斗线程池
	public static ActionExecutor battleExecutor;
	static {
		int corePoolSize = 8;
		int maxPoolSize = 32;
		int keepAliveTime = 5;
		int cacheSize = 64;
		battleExecutor = new ActionExecutor(corePoolSize, maxPoolSize, keepAliveTime, cacheSize, "WORD_BATTLE_EXECUTOR");
	}
}
