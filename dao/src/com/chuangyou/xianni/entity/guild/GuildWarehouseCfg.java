package com.chuangyou.xianni.entity.guild;

public class GuildWarehouseCfg {

	/** 仓库等级 */
	private int level;
	/** 帮派物资上限 */
	private long guildMaxSupply;
	/** 帮派仓库容量 */
	private int warehouseSize;
	
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public long getGuildMaxSupply() {
		return guildMaxSupply;
	}
	public void setGuildMaxSupply(long guildMaxSupply) {
		this.guildMaxSupply = guildMaxSupply;
	}
	public int getWarehouseSize() {
		return warehouseSize;
	}
	public void setWarehouseSize(int warehouseSize) {
		this.warehouseSize = warehouseSize;
	}
}
