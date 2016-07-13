package com.chuangyou.xianni.entity.magicwp;

import com.chuangyou.xianni.entity.DataObject;

public class MagicwpBanInfo extends DataObject {

	/** 角色ID */
	private long playerId;
	
	/** 禁制ID */
	private int banId;
	
	/** 禁制位置，0表示未装备，大于0时表示装备位置 */
	private byte position;
	
	/** 等级，未激活为0级 */
	private int level;
	
	/** 当前经验 */
	private int exp;
	
	/** 是否自动吃碎片升级 */
	private byte autoUpLevel;
	
	/** 已激活的碎片索引(例：1_3_4) */
	private String fragmentStr;

	public MagicwpBanInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MagicwpBanInfo(long playerId, int banId) {
		super();
		this.playerId = playerId;
		this.banId = banId;
		this.position = 0;
		this.level = 0;
		this.exp = 0;
		this.autoUpLevel = 0;
		this.fragmentStr = "";
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public int getBanId() {
		return banId;
	}

	public void setBanId(int banId) {
		this.banId = banId;
	}

	public byte getPosition() {
		return position;
	}

	public void setPosition(byte position) {
		this.position = position;
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

	public byte getAutoUpLevel() {
		return autoUpLevel;
	}

	public void setAutoUpLevel(byte autoUpLevel) {
		this.autoUpLevel = autoUpLevel;
	}

	public String getFragmentStr() {
		return fragmentStr;
	}

	public void setFragmentStr(String fragmentStr) {
		this.fragmentStr = fragmentStr;
	}

	@Override
	public String toString() {
		return "MagicwpBanInfo [roleId=" + playerId + ", banId=" + banId + ", position=" + position + ", level=" + level
				+ ", exp=" + exp + ", autoUpLevel=" + autoUpLevel + ", fragmentStr=" + fragmentStr + "]";
	}
}
