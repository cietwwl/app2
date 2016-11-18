package com.chuangyou.xianni.entity.spawn;

/** 怪物AI事件配置 */
public class AIEventTemplate {
	// AI ID
	private int	aiId;
	// 触发事件类型
	private int	conditionType;
	private int	conditionParam1;
	private int	conditionParam2;
	private int	conditionParam3;
	// 执行事件类型
	private int	executorType;
	private int	executorParam1;
	private int	executorParam2;
	private int	executorParam3;

	// 执行次数
	private int	exeCount;
	// 执行CD
	private int	coolDown;

	public int getAiId() {
		return aiId;
	}

	public void setAiId(int aiId) {
		this.aiId = aiId;
	}

	public int getConditionType() {
		return conditionType;
	}

	public void setConditionType(int conditionType) {
		this.conditionType = conditionType;
	}

	public int getConditionParam1() {
		return conditionParam1;
	}

	public void setConditionParam1(int conditionParam1) {
		this.conditionParam1 = conditionParam1;
	}

	public int getConditionParam2() {
		return conditionParam2;
	}

	public void setConditionParam2(int conditionParam2) {
		this.conditionParam2 = conditionParam2;
	}

	public int getConditionParam3() {
		return conditionParam3;
	}

	public void setConditionParam3(int conditionParam3) {
		this.conditionParam3 = conditionParam3;
	}

	public int getExeCount() {
		return exeCount;
	}

	public void setExeCount(int exeCount) {
		this.exeCount = exeCount;
	}

	public int getCoolDown() {
		return coolDown;
	}

	public void setCoolDown(int coolDown) {
		this.coolDown = coolDown;
	}

	public int getExecutorType() {
		return executorType;
	}

	public void setExecutorType(int executorType) {
		this.executorType = executorType;
	}

	public int getExecutorParam1() {
		return executorParam1;
	}

	public void setExecutorParam1(int executorParam1) {
		this.executorParam1 = executorParam1;
	}

	public int getExecutorParam2() {
		return executorParam2;
	}

	public void setExecutorParam2(int executorParam2) {
		this.executorParam2 = executorParam2;
	}

	public int getExecutorParam3() {
		return executorParam3;
	}

	public void setExecutorParam3(int executorParam3) {
		this.executorParam3 = executorParam3;
	}

}
