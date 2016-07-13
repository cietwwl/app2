package com.chuangyou.xianni.entity.pet;

import com.chuangyou.xianni.entity.DataObject;

public class PetAtt extends DataObject {

	
	private long playerId;
	
	private int soulLv;
	
	private int soulExp;
	
	private int skillSlotNum;
	
	private int fightPetId;
	
	public PetAtt() {
		// TODO Auto-generated constructor stub
	}
	
	public PetAtt(long playerId){
		this.playerId = playerId;
		this.soulLv = 0;
		this.soulExp = 0;
		this.skillSlotNum = 1;
		this.fightPetId = 0;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public int getSoulLv() {
		return soulLv;
	}

	public void setSoulLv(int soulLv) {
		this.soulLv = soulLv;
	}

	public int getSoulExp() {
		return soulExp;
	}

	public void setSoulExp(int soulExp) {
		this.soulExp = soulExp;
	}

	public int getSkillSlotNum() {
		return skillSlotNum;
	}

	public void setSkillSlotNum(int skillSlotNum) {
		this.skillSlotNum = skillSlotNum;
	}

	public int getFightPetId() {
		return fightPetId;
	}

	public void setFightPetId(int fightPetId) {
		this.fightPetId = fightPetId;
	}

	@Override
	public String toString() {
		return "PetAtt [playerId=" + playerId + ", soulLv=" + soulLv + ", soulExp=" + soulExp + ", skillSlotNum=" + skillSlotNum
				+ ", fightPetId=" + fightPetId + "]";
	}
}
