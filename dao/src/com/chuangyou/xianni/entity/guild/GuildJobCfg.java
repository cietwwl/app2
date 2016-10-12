package com.chuangyou.xianni.entity.guild;

public class GuildJobCfg {

	/** 职位 */
	private int jobId;
	/** 名字 */
	private String name;
	
	/** 系统门派职位要求修为 */
	private int repair;
	
	/** 夺权消耗物品 */
	private int costItem;
	
	/** 夺权消耗物品数量 */
	private int costCount;
	
	/** 夺权要求掌门离线天数 */
	private int seizeDay;
	
	public int getJobId() {
		return jobId;
	}
	public void setJobId(int jobId) {
		this.jobId = jobId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getRepair() {
		return repair;
	}
	public void setRepair(int repair) {
		this.repair = repair;
	}
	public int getCostItem() {
		return costItem;
	}
	public void setCostItem(int costItem) {
		this.costItem = costItem;
	}
	public int getCostCount() {
		return costCount;
	}
	public void setCostCount(int costCount) {
		this.costCount = costCount;
	}
	public int getSeizeDay() {
		return seizeDay;
	}
	public void setSeizeDay(int seizeDay) {
		this.seizeDay = seizeDay;
	}
	
}
