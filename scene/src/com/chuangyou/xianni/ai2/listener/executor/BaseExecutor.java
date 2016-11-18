package com.chuangyou.xianni.ai2.listener.executor;

import com.chuangyou.xianni.ai2.proxy.MonsterAI;
import com.chuangyou.xianni.entity.spawn.AIEventTemplate;

public abstract class BaseExecutor {
	public abstract void exe(MonsterAI ai);

	public boolean coolDown() {
		return false;
	}

	public static BaseExecutor createExecutor(AIEventTemplate template) {
		switch (template.getExecutorType()) {
			case 1:
				break;
			default:
				break;
		}
		return null;
	}
}
