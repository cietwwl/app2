package com.chuangyou.xianni.entity.campaign;

/**
 * 副本模型ID
 * 
 * @author Administrator
 *
 */
public class CampaignTemplateInfo {
	private int		templateId;		// 模型ID
	private String	campaignName;	// 副本名字
	private int		storyId;		// 副本所属章节标记(讲故事)
	private int		type;			// 副本类型
	private int		difficulty;		// 挑战难度
	private int		joinType;		// 进入类型
	private int		minLevel;		// 进入最小等级
	private int		maxLevel;		// 进入最大等级
	private int		preCampaignId;	// 前置副本
	private String	nextCampaignId;	// 后置副本
	private int		capacity;		// 副本容量
	private String	rewardItems;	// 副本奖励
	private String	description;	// 副本描述
	private int		openType;		// 开启类型 1 玩家创建 2 定时开启 3 每周N开启 4
									// 每月X号开启，等等，参数，待约定
	private String	openParams;		// 开启参数
	private int		openTime;		// 开启时间 (分)
	private int		startScriptId;	// 开始时触发脚本ID
	private int		endScriptId;	// 结束时触发脚本ID
	private String	tasks;			// 副本任务，以逗号分隔
	private int[]	attrTaskIds;	// 任务ID组

	public int getTemplateId() {
		return templateId;
	}

	public void setTemplateId(int templateId) {
		this.templateId = templateId;
	}

	public String getCampaignName() {
		return campaignName;
	}

	public void setCampaignName(String campaignName) {
		this.campaignName = campaignName;
	}

	public int getStoryId() {
		return storyId;
	}

	public void setStoryId(int storyId) {
		this.storyId = storyId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getMinLevel() {
		return minLevel;
	}

	public void setMinLevel(int minLevel) {
		this.minLevel = minLevel;
	}

	public int getMaxLevel() {
		return maxLevel;
	}

	public void setMaxLevel(int maxLevel) {
		this.maxLevel = maxLevel;
	}

	public int getPreCampaignId() {
		return preCampaignId;
	}

	public void setPreCampaignId(int preCampaignId) {
		this.preCampaignId = preCampaignId;
	}

	public String getNextCampaignId() {
		return nextCampaignId;
	}

	public void setNextCampaignId(String nextCampaignId) {
		this.nextCampaignId = nextCampaignId;
	}

	public int getCapacity() {
		return capacity;
	}

	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	public String getRewardItems() {
		return rewardItems;
	}

	public void setRewardItems(String rewardItems) {
		this.rewardItems = rewardItems;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getOpenType() {
		return openType;
	}

	public void setOpenType(int openType) {
		this.openType = openType;
	}

	public String getOpenParams() {
		return openParams;
	}

	public void setOpenParams(String openParams) {
		this.openParams = openParams;
	}

	public int getOpenTime() {
		return openTime;
	}

	public void setOpenTime(int openTime) {
		this.openTime = openTime;
	}

	public int getStartScriptId() {
		return startScriptId;
	}

	public void setStartScriptId(int startScriptId) {
		this.startScriptId = startScriptId;
	}

	public int getEndScriptId() {
		return endScriptId;
	}

	public void setEndScriptId(int endScriptId) {
		this.endScriptId = endScriptId;
	}

	public int getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}

	public int getJoinType() {
		return joinType;
	}

	public void setJoinType(int joinType) {
		this.joinType = joinType;
	}

	public String getTasks() {
		return tasks;
	}

	public void setTasks(String tasks) {
		this.tasks = tasks;
		if (tasks != null && !tasks.equals("")) {
			String[] attrTID = tasks.split(",");
			attrTaskIds = new int[attrTID.length];
			for (int i = 0; i < attrTID.length; i++) {
				attrTaskIds[i] = Integer.valueOf(attrTID[i]);
			}
		}
	}

	public int[] getAttrTaskIds() {
		return attrTaskIds;
	}

	public void setAttrTaskIds(int[] attrTaskIds) {
		this.attrTaskIds = attrTaskIds;
	}

}
