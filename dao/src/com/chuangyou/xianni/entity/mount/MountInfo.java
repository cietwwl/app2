package com.chuangyou.xianni.entity.mount;

import com.chuangyou.xianni.entity.DataObject;

public class MountInfo extends DataObject {

	/** 角色ID */
	private long playerId;
	/** 当前选择使用的坐骑ID */
	private int mountId;
	/** 坐骑等级 */
	private int level;
	/** 坐骑升级CD */
	private long upLevCd;
	/** 坐骑进阶等级 */
	private int grade;
	/** 进阶祝福值 */
	private int bless;
	/** 使用属性丹总数量 */
	private int useDanNum;
	/** 骑兵进阶等级 */
	private int weaponGrade;
	/** 骑兵进阶祝福值 */
	private int weaponBless;

	public MountInfo() {
		super();
	}

	public MountInfo(long playerId, int mountId) {
		super();
		this.playerId = playerId;
		this.mountId = mountId;
		this.level = 1;
		this.upLevCd = 0;
		this.grade = 1;
		this.bless = 0;
		this.useDanNum = 0;
		this.weaponGrade = 1;
		this.weaponBless = 0;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public int getMountId() {
		return mountId;
	}

	public void setMountId(int mountId) {
		this.mountId = mountId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getUpLevCd() {
		return upLevCd;
	}

	public void setUpLevCd(long upLevCd) {
		this.upLevCd = upLevCd;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getBless() {
		return bless;
	}

	public void setBless(int bless) {
		this.bless = bless;
	}

	public int getUseDanNum() {
		return useDanNum;
	}

	public void setUseDanNum(int useDanNum) {
		this.useDanNum = useDanNum;
	}

	public int getWeaponGrade() {
		return weaponGrade;
	}

	public void setWeaponGrade(int weaponGrade) {
		this.weaponGrade = weaponGrade;
	}

	public int getWeaponBless() {
		return weaponBless;
	}

	public void setWeaponBless(int weaponBless) {
		this.weaponBless = weaponBless;
	}

	@Override
	public String toString() {
		return "MountInfo [playerId=" + playerId + ", mountId=" + mountId + ", level=" + level + ", upLevCd=" + upLevCd
				+ ", grade=" + grade + ", bless=" + bless + ", useDanNum=" + useDanNum + ", weaponGrade=" + weaponGrade
				+ ", weaponBless=" + weaponBless + "]";
	}
}
