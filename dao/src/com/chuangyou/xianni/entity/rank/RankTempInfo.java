package com.chuangyou.xianni.entity.rank;

import com.chuangyou.xianni.entity.DataObject;

public class RankTempInfo extends DataObject {
	
	private long playerId;
	private long equip;
	private long magicwp;
	private long mount;
	private long pet;
	private long soul;
	private long avatar;
	private long state;
	private long artifact;
	
	
	public long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	public long getEquip() {
		return equip;
	}
	public void setEquip(long equip) {
		this.equip = equip;
	}
	public long getMagicwp() {
		return magicwp;
	}
	public void setMagicwp(long magicwp) {
		this.magicwp = magicwp;
	}
	public long getMount() {
		return mount;
	}
	public void setMount(long mount) {
		this.mount = mount;
	}
	public long getPet() {
		return pet;
	}
	public void setPet(long pet) {
		this.pet = pet;
	}
	public long getSoul() {
		return soul;
	}
	public void setSoul(long soul) {
		this.soul = soul;
	}
	public long getAvatar() {
		return avatar;
	}
	public void setAvatar(long avatar) {
		this.avatar = avatar;
	}
	public long getState() {
		return state;
	}
	public void setState(long state) {
		this.state = state;
	}
	public long getArtifact() {
		return artifact;
	}
	public void setArtifact(long artifact) {
		this.artifact = artifact;
	}
	
	
	
}
