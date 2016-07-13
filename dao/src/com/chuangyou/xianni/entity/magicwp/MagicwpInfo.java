package com.chuangyou.xianni.entity.magicwp;

import com.chuangyou.xianni.entity.DataObject;

public class MagicwpInfo extends DataObject {

	/** 角色ID */
	private long playerId;
	
	/** 法宝ID */
	private int magicwpId;
	
	/** 等级 */
	private int level;
	
	/** 升级冷却时间 */
	private long upLevelCd;
	
	/** 阶级 */
	private int grade;
	
	/** 洗炼属性值，下划线隔开 */
	private String refineAtts;
	
	/** 是否有未保存的临时洗炼属性 0无 1有 */
	private byte unSaveAtt;
	
	/** 洗炼临时属性 */
	private String tempAtt;

	public MagicwpInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public MagicwpInfo(long playerId, int magicwpId) {
		super();
		this.playerId = playerId;
		this.magicwpId = magicwpId;
		this.level = 0;
		this.upLevelCd = 0;
		this.grade = 0;
		this.refineAtts = "";
		this.unSaveAtt = 0;
		this.tempAtt = "";
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public int getMagicwpId() {
		return magicwpId;
	}

	public void setMagicwpId(int magicwpId) {
		this.magicwpId = magicwpId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getUpLevelCd() {
		return upLevelCd;
	}

	public void setUpLevelCd(long upLevelCd) {
		this.upLevelCd = upLevelCd;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public String getRefineAtts() {
		return refineAtts;
	}

	public void setRefineAtts(String refineAtts) {
		this.refineAtts = refineAtts;
	}

	public byte getUnSaveAtt() {
		return unSaveAtt;
	}

	public void setUnSaveAtt(byte unSaveAtt) {
		this.unSaveAtt = unSaveAtt;
	}

	public String getTempAtt() {
		return tempAtt;
	}

	public void setTempAtt(String tempAtt) {
		this.tempAtt = tempAtt;
	}

	@Override
	public String toString() {
		return "MagicwpInfo [roleId=" + playerId + ", magicwpId=" + magicwpId + ", level=" + level + ", upLevelCd="
				+ upLevelCd + ", grade=" + grade + ", refineAtts=" + refineAtts + ", unSaveAtt=" + unSaveAtt
				+ ", tempAtt=" + tempAtt + "]";
	}
}
