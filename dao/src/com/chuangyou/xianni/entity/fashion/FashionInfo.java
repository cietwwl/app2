package com.chuangyou.xianni.entity.fashion;

import com.chuangyou.xianni.entity.DataObject;

public class FashionInfo extends DataObject {
	public static final byte IS_EQUIPED_TRUE = 1;
	public static final byte IS_EQUIPED_FALSE = 2;
	/** 角色编号 */
	private long playerId;
	/** 模板编号 */
	private int fashionId;
	/** 品质：1=凡品 */
	private int quality;
	/** 等级 */
	private int level;
	/** 经验值 */
	private int exp;
	/** 1:已装备  2：未装备 */
	private byte isEquiped;

	public long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	public int getFashionId() {
		return fashionId;
	}
	public void setFashionId(int fashionId) {
		this.fashionId = fashionId;
	}
	public int getQuality() {
		return quality;
	}
	public void setQuality(int quality) {
		this.quality = quality;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getExp() {
		return exp;
	}
	public void setExp(int exp) {
		this.exp = exp;
	}
	public byte getIsEquiped() {
		return isEquiped;
	}
	public void setIsEquiped(byte isEquiped) {
		this.isEquiped = isEquiped;
	}
	
	public FashionInfo() {
		// TODO Auto-generated constructor stub
	}
	public FashionInfo(long roleId, int fashionId) {
		this.playerId = roleId;
		this.fashionId = fashionId;
		this.quality = 1;
		this.level = 0;//品阶从0开始
		this.exp = 0;
		this.isEquiped = IS_EQUIPED_FALSE;
	}
	public boolean isEquiped() {
		return this.isEquiped==IS_EQUIPED_TRUE;
	}
	public void setEquiped(boolean isEquiped) {
		this.isEquiped = isEquiped?IS_EQUIPED_TRUE:IS_EQUIPED_FALSE;
	}
	@Override
	public String toString() {
		return "FashionInfo [playerId=" + playerId + ", fashionId=" + fashionId + ", quality=" + quality + ", level="
				+ level + ", exp=" + exp + ", isEquiped=" + isEquiped + "]";
	}
}
