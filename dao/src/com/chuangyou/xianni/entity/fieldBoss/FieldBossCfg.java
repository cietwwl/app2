package com.chuangyou.xianni.entity.fieldBoss;

import java.util.ArrayList;
import java.util.List;

public class FieldBossCfg {
	
	public static final short ELITE_BOSS = 1;
	public static final short WORLD_BOSS = 2;

	/** 怪物ID */
	private int monsterId;
	
	/** 刷怪表节点ID */
	private int tagId;
	
	/** 世界BOSS对应多个节点，表示节点结束ID */
	private int tagIdEnd;
	
	/** BOSS类型 1精英 2世界BOSS */
	private int type;
	
	/** 刷新时间列表 */
	private String createTimes;
	
	/** 死亡触发副本几率 */
	private int campaignChance;
	
	/** 死亡触发开启副本的模板ID */
	private int openCampaignId;
	
	/** 触发副本进入等级下限 */
	private int minLevel;
	
	/** 触发副本进入等级上限 */
	private int maxLevel;
	
	/** 触发的单人副本刷怪点tag */
	private int singleTag;
	
	/** 触发的多人副本刷怪点tag */
	private int multiTag;
	
	/** 刷新公告 */
	private int bornNotice;
	
	/** 击杀公告 */
	private int deadNotice;
	
	/** 触发单人副本的死亡公告 */
	private int singleNotice;
	
	/** 触发多人副本的死亡公告 */
	private int multiNotice;
	
	/** 对boss第一击玩家奖励物品id */
	private int firstAwardItem;
	/** 对boss第一击玩家奖励数量 */
	private int firstAwardNum;
	
	/** 对boss造成伤害玩家奖励物品id */
	private int normalAwardItem;
	/** 对boss造成伤害玩家奖励数量 */
	private int normalAwardNum;
	
	/** 对boss伤害最高玩家奖励物品id */
	private int dmgAwardItem;
	/** 对boss伤害最高玩家奖励数量 */
	private int dmgAwardNum;
	
	/** 击破boss护盾玩家奖励物品id */
	private int shieldAwardItem;
	/** 击破boss护盾玩家奖励数量 */
	private int shieldAwardNum;
	
	/** 传送阵模型id */
	private int transferNpcId;
	
	private List<String> timeList = null;

	public int getMonsterId() {
		return monsterId;
	}

	public void setMonsterId(int monsterId) {
		this.monsterId = monsterId;
	}

	public int getTagId() {
		return tagId;
	}

	public void setTagId(int tagId) {
		this.tagId = tagId;
	}

	public int getTagIdEnd() {
		return tagIdEnd;
	}

	public void setTagIdEnd(int tagIdEnd) {
		this.tagIdEnd = tagIdEnd;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getCreateTimes() {
		return createTimes;
	}

	public void setCreateTimes(String createTimes) {
		this.createTimes = createTimes;
	}
	
	public int getCampaignChance() {
		return campaignChance;
	}

	public void setCampaignChance(int campaignChance) {
		this.campaignChance = campaignChance;
	}

	public void setTimeList(){
		timeList = new ArrayList<>();
		String[] times = createTimes.split(",");
//		Arrays.sort(times);
		
		for(int i = 0; i < times.length; i++){
			while(times[i].length() < 6){
				times[i] = "0" + times[i];
			}
			timeList.add(times[i]);
		}
	}

	public List<String> getTimeList() {
		return timeList;
	}

	public int getOpenCampaignId() {
		return openCampaignId;
	}

	public void setOpenCampaignId(int openCampaignId) {
		this.openCampaignId = openCampaignId;
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

	public int getSingleTag() {
		return singleTag;
	}

	public void setSingleTag(int singleTag) {
		this.singleTag = singleTag;
	}

	public int getMultiTag() {
		return multiTag;
	}

	public void setMultiTag(int multiTag) {
		this.multiTag = multiTag;
	}

	public int getBornNotice() {
		return bornNotice;
	}

	public void setBornNotice(int bornNotice) {
		this.bornNotice = bornNotice;
	}

	public int getDeadNotice() {
		return deadNotice;
	}

	public void setDeadNotice(int deadNotice) {
		this.deadNotice = deadNotice;
	}

	public int getSingleNotice() {
		return singleNotice;
	}

	public void setSingleNotice(int singleNotice) {
		this.singleNotice = singleNotice;
	}

	public int getMultiNotice() {
		return multiNotice;
	}

	public void setMultiNotice(int multiNotice) {
		this.multiNotice = multiNotice;
	}

	public int getFirstAwardItem() {
		return firstAwardItem;
	}

	public void setFirstAwardItem(int firstAwardItem) {
		this.firstAwardItem = firstAwardItem;
	}

	public int getFirstAwardNum() {
		return firstAwardNum;
	}

	public void setFirstAwardNum(int firstAwardNum) {
		this.firstAwardNum = firstAwardNum;
	}

	public int getNormalAwardItem() {
		return normalAwardItem;
	}

	public void setNormalAwardItem(int normalAwardItem) {
		this.normalAwardItem = normalAwardItem;
	}

	public int getNormalAwardNum() {
		return normalAwardNum;
	}

	public void setNormalAwardNum(int normalAwardNum) {
		this.normalAwardNum = normalAwardNum;
	}

	public int getDmgAwardItem() {
		return dmgAwardItem;
	}

	public void setDmgAwardItem(int dmgAwardItem) {
		this.dmgAwardItem = dmgAwardItem;
	}

	public int getDmgAwardNum() {
		return dmgAwardNum;
	}

	public void setDmgAwardNum(int dmgAwardNum) {
		this.dmgAwardNum = dmgAwardNum;
	}

	public int getShieldAwardItem() {
		return shieldAwardItem;
	}

	public void setShieldAwardItem(int shieldAwardItem) {
		this.shieldAwardItem = shieldAwardItem;
	}

	public int getShieldAwardNum() {
		return shieldAwardNum;
	}

	public void setShieldAwardNum(int shieldAwardNum) {
		this.shieldAwardNum = shieldAwardNum;
	}

	public int getTransferNpcId() {
		return transferNpcId;
	}

	public void setTransferNpcId(int transferNpcId) {
		this.transferNpcId = transferNpcId;
	}

	public void setTimeList(List<String> timeList) {
		this.timeList = timeList;
	}
}
