package com.chuangyou.xianni.entity.pet;

import com.chuangyou.xianni.entity.DataObject;

public class PetSkill extends DataObject {
	/** 角色编号 */
	private long playerId;
	/** 技能编号 */
	private int skillId;
	/** 状态：状态：1已获得待激活，2已激活待解封，3已解封待装备，4已装备 */
	private byte state;
	/** 装备格索引 */
	private byte slotIndex;
	/** 等级 */
	private int level;
	public PetSkill() {
		this(0, 0);
	}
	public PetSkill(long playerId, int skillId) {
		this.playerId = playerId;
		this.skillId = skillId;
		this.state = 1;
		this.slotIndex = 0;
		this.level = 1;
	}
	public long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	public int getSkillId() {
		return skillId;
	}
	public void setSkillId(int skillId) {
		this.skillId = skillId;
	}
	/** 状态：状态：1已获得待激活，2已激活待解封，3已解封待装备，4已装备 */
	public byte getState() {
		return state;
	}
	/** 状态：状态：1已获得待激活，2已激活待解封，3已解封待装备，4已装备 */
	public void setState(int state) {
		this.state = (byte)state;
	}
	public byte getSlotIndex() {
		return slotIndex;
	}
	public void setSlotIndex(int slotIndex) {
		this.slotIndex = (byte)slotIndex;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return "PetSkill [playerId=" + playerId + ", skillId=" + skillId + ", state=" + state + ", slotIndex="
				+ slotIndex + ", level=" + level + "]";
	}
	
}
