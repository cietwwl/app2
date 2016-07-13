package com.chuangyou.xianni.entity.mount;

import com.chuangyou.xianni.entity.DataObject;

public class MountEquipInfo extends DataObject {

	/**	角色ID */
	private long playerId;
	/** 装备位置ID */
	private int equipId;
	/** 装备等级 */
	private int equipLevel;
	
	public MountEquipInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MountEquipInfo(long playerId, int equipId) {
		super();
		this.playerId = playerId;
		this.equipId = equipId;
		this.equipLevel = 1;
	}
	public long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	public int getEquipId() {
		return equipId;
	}
	public void setEquipId(int equipId) {
		this.equipId = equipId;
	}
	public int getEquipLevel() {
		return equipLevel;
	}
	public void setEquipLevel(int equipLevel) {
		this.equipLevel = equipLevel;
	}
	@Override
	public String toString() {
		return "MountEquipInfo [roleId=" + playerId + ", equipId=" + equipId + ", equipLevel=" + equipLevel + "]";
	}
}
