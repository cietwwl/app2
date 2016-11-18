package com.chuangyou.xianni.ai2.listener;

import java.util.Map;

import com.chuangyou.xianni.ai2.listener.executor.BaseExecutor;
import com.chuangyou.xianni.entity.spawn.AIEventTemplate;

public abstract class AiCondition {
	protected Map<Integer, BaseExecutor> executors;

	public abstract void onEvent();

	public abstract boolean check();

	public void add(int executorType, BaseExecutor executor) {
		executors.put(executorType, executor);
	}

	public static AiCondition createAiCondition(AIEventTemplate template) {
		switch (template.getConditionType()) {
			
		}
		return null;
	}
}
