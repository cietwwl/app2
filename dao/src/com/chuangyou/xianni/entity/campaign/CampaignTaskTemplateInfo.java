package com.chuangyou.xianni.entity.campaign;

/** 副本任务模板 */
public class CampaignTaskTemplateInfo {
	private int		taskId;			// 任务ID
	private int		conditionType;	// 条件类型
	private String	name;			// 任务名
	private String	description;	// 任务描述
	private int		point;			// 挑战点奖励
	private int		repair;			// 修为
	private int		param1;			// 条件参数1
	private int		param2;			// 条件参数2
	private int		param3;			// 条件参数3
	private int		param4;			// 条件参数4
	private String	strParam1;		// 条件参数5

	public int getTaskId() {
		return taskId;
	}

	public void setTaskId(int taskId) {
		this.taskId = taskId;
	}

	public int getConditionType() {
		return conditionType;
	}

	public void setConditionType(int conditionType) {
		this.conditionType = conditionType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPoint() {
		return point;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getRepair() {
		return repair;
	}

	public void setRepair(int repair) {
		this.repair = repair;
	}

	public int getParam1() {
		return param1;
	}

	public void setParam1(int param1) {
		this.param1 = param1;
	}

	public int getParam2() {
		return param2;
	}

	public void setParam2(int param2) {
		this.param2 = param2;
	}

	public int getParam3() {
		return param3;
	}

	public void setParam3(int param3) {
		this.param3 = param3;
	}

	public int getParam4() {
		return param4;
	}

	public void setParam4(int param4) {
		this.param4 = param4;
	}

	public String getStrParam1() {
		return strParam1;
	}

	public void setStrParam1(String strParam1) {
		this.strParam1 = strParam1;
	}

}
