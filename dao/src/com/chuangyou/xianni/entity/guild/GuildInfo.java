package com.chuangyou.xianni.entity.guild;

import com.chuangyou.xianni.entity.DataObject;
import com.chuangyou.xianni.entity.Option;

public class GuildInfo extends DataObject {

	/** 门派id */
	private int guildId;
	/** 门派类型 1玩家门派 2系统门派 */
	private int type;
	/** 门派名字 */
	private String name;
	/** 创建时间 */
	private long createTime;
	/** 等级 */
	private int level;
	/** 建设值 */
	private long buildExp;
	/** 帮派总建设值 */
	private long totalBuildExp;
	/** 物资 */
	private long supply;
	/** 主殿等级 */
	private int mainBuildLevel;
	/** 蒧经阁等级 */
	private int skillShopLevel;
	/** 商店等级 */
	private int shopLevel;
	/** 仓库等级 */
	private int warehouseLevel;
	/** 帮派公告 */
	private String notice;
	/** 帮主ID */
	private long leaderId;
	/** 加入类型 */
	private short joinType;
	/** 等级限制 */
	private int levelLimit;
	/** 战力限制 */
	private int fightLimit;
	
	/** 物资清理检测时间 */
	private long supplyCheckTime;
	
	/** 帮派战力 */
	private long fight;
	
	public GuildInfo() {
		// TODO Auto-generated constructor stub
	}
	public GuildInfo(long playerId, int guildId, int type, String name, int level){
		this.leaderId = playerId;
		this.guildId = guildId;
		this.type = type;
		this.name = name;
		this.level = level;
		this.createTime = System.currentTimeMillis();
		this.buildExp = 0;
		this.supply = 0;
		this.mainBuildLevel = 1;
		this.skillShopLevel = 1;
		this.shopLevel = 1;
		this.warehouseLevel = 1;
		this.notice = "";
		this.joinType = 1;
		this.levelLimit = 0;
		this.fightLimit = 0;
		this.supplyCheckTime = System.currentTimeMillis();
		this.fight = 0;
	}
	
	public int getGuildId() {
		return guildId;
	}
	public void setGuildId(int guildId) {
		this.guildId = guildId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.setOp(Option.Update);
		this.name = name;
	}
	public long getCreateTime() {
		return createTime;
	}
	public void setCreateTime(long createTime) {
		this.setOp(Option.Update);
		this.createTime = createTime;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.setOp(Option.Update);
		this.level = level;
	}
	public long getBuildExp() {
		return buildExp;
	}
	public void setBuildExp(long buildExp) {
		this.setOp(Option.Update);
		this.buildExp = buildExp;
	}
	public long getTotalBuildExp() {
		return totalBuildExp;
	}
	public void setTotalBuildExp(long totalBuildExp) {
		this.setOp(Option.Update);
		this.totalBuildExp = totalBuildExp;
	}
	public long getSupply() {
		return supply;
	}
	public void setSupply(long supply) {
		this.setOp(Option.Update);
		this.supply = supply;
	}
	public int getMainBuildLevel() {
		return mainBuildLevel;
	}
	public void setMainBuildLevel(int mainBuildLevel) {
		this.setOp(Option.Update);
		this.mainBuildLevel = mainBuildLevel;
	}
	public int getSkillShopLevel() {
		return skillShopLevel;
	}
	public void setSkillShopLevel(int skillShopLevel) {
		this.setOp(Option.Update);
		this.skillShopLevel = skillShopLevel;
	}
	public int getShopLevel() {
		return shopLevel;
	}
	public void setShopLevel(int shopLevel) {
		this.setOp(Option.Update);
		this.shopLevel = shopLevel;
	}
	public int getWarehouseLevel() {
		return warehouseLevel;
	}
	public void setWarehouseLevel(int warehouseLevel) {
		this.setOp(Option.Update);
		this.warehouseLevel = warehouseLevel;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.setOp(Option.Update);
		this.notice = notice;
	}
	public long getLeaderId() {
		return leaderId;
	}
	public void setLeaderId(long leaderId) {
		this.setOp(Option.Update);
		this.leaderId = leaderId;
	}
	public short getJoinType() {
		return joinType;
	}
	public void setJoinType(short joinType) {
		this.setOp(Option.Update);
		this.joinType = joinType;
	}
	public int getLevelLimit() {
		return levelLimit;
	}
	public void setLevelLimit(int levelLimit) {
		this.setOp(Option.Update);
		this.levelLimit = levelLimit;
	}
	public int getFightLimit() {
		return fightLimit;
	}
	public void setFightLimit(int fightLimit) {
		this.setOp(Option.Update);
		this.fightLimit = fightLimit;
	}
	public long getSupplyCheckTime() {
		return supplyCheckTime;
	}
	public void setSupplyCheckTime(long supplyCheckTime) {
		this.setOp(Option.Update);
		this.supplyCheckTime = supplyCheckTime;
	}
	public long getFight() {
		return fight;
	}
	public void setFight(long fight) {
		this.setOp(Option.Update);
		this.fight = fight;
	}
}
