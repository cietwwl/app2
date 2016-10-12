package com.chuangyou.xianni.entity.guild;

public class GuildSkillCfg {

	/** 帮派蒧经阁技能ID */
	private int id;
	
	/** 等级 */
	private int level;
	
	/** 对应玩家技能表的技能ID */
	private int skillId;
	
	/** 升级需要的帮派物资 */
	private long needSupply;
	
	/** 升级需要的帮派贡献 */
	private long needContribution;
	
	/** 要求蒧经阁等级 */
	private int skillShopLevel;
	
	/** 要求玩家境界等级 */
	private int playerStateLevel;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getSkillId() {
		return skillId;
	}

	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}

	public long getNeedSupply() {
		return needSupply;
	}

	public void setNeedSupply(long needSupply) {
		this.needSupply = needSupply;
	}

	public long getNeedContribution() {
		return needContribution;
	}

	public void setNeedContribution(long needContribution) {
		this.needContribution = needContribution;
	}

	public int getSkillShopLevel() {
		return skillShopLevel;
	}

	public void setSkillShopLevel(int skillShopLevel) {
		this.skillShopLevel = skillShopLevel;
	}

	public int getPlayerStateLevel() {
		return playerStateLevel;
	}

	public void setPlayerStateLevel(int playerStateLevel) {
		this.playerStateLevel = playerStateLevel;
	}
}
