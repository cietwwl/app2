package com.chuangyou.xianni.entity.guild;

public class GuildSystemCfg {

	private int guildId;
	private String name;
	private int level;
	private int mainBuildLevel;
	private int skillShopLevel;
	private int shopLevel;
	private int warehouseLevel;
	private String notice;
	
	public int getGuildId() {
		return guildId;
	}
	public void setGuildId(int guildId) {
		this.guildId = guildId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getMainBuildLevel() {
		return mainBuildLevel;
	}
	public void setMainBuildLevel(int mainBuildLevel) {
		this.mainBuildLevel = mainBuildLevel;
	}
	public int getSkillShopLevel() {
		return skillShopLevel;
	}
	public void setSkillShopLevel(int skillShopLevel) {
		this.skillShopLevel = skillShopLevel;
	}
	public int getShopLevel() {
		return shopLevel;
	}
	public void setShopLevel(int shopLevel) {
		this.shopLevel = shopLevel;
	}
	public int getWarehouseLevel() {
		return warehouseLevel;
	}
	public void setWarehouseLevel(int warehouseLevel) {
		this.warehouseLevel = warehouseLevel;
	}
	public String getNotice() {
		return notice;
	}
	public void setNotice(String notice) {
		this.notice = notice;
	}
}
