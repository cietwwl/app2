package com.chuangyou.xianni.entity.pet;

import com.chuangyou.xianni.entity.DataObject;

public class PetInfo extends DataObject {

	/** 角色ID */
	private long playerId;
	
	/** 宠物ID */
	private int petId;
	
	/** 资质 */
	private int talent;
	
	/** 等级 */
	private int level;
	
	/** 当前等级经验 */
	private int levelExp;
	
	/** 炼体等级 */
	private int physique;
	
	/** 品质 */
	private int quality;
	
	/** 品质祝福值 */
	private int qualityBless;
	
	/** 阶级 */
	private int grade;
	
	/** 阶级祝福值 */
	private int gradeBless;

	public PetInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PetInfo(long playerId, int petId) {
		super();
		this.playerId = playerId;
		this.petId = petId;
		
		this.talent = 1;
		this.level = 1;
		this.levelExp = 0;
		this.physique = 0;
		this.quality = 1;
		this.qualityBless = 0;
		this.grade = 0;
		this.gradeBless = 0;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public int getPetId() {
		return petId;
	}

	public void setPetId(int petId) {
		this.petId = petId;
	}

	public int getTalent() {
		return talent;
	}

	public void setTalent(int talent) {
		this.talent = talent;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getLevelExp() {
		return levelExp;
	}

	public void setLevelExp(int levelExp) {
		this.levelExp = levelExp;
	}

	public int getPhysique() {
		return physique;
	}

	public void setPhysique(int physique) {
		this.physique = physique;
	}

	public int getQuality() {
		return quality;
	}

	public void setQuality(int quality) {
		this.quality = quality;
	}

	public int getQualityBless() {
		return qualityBless;
	}

	public void setQualityBless(int qualityBless) {
		this.qualityBless = qualityBless;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public int getGradeBless() {
		return gradeBless;
	}

	public void setGradeBless(int gradeBless) {
		this.gradeBless = gradeBless;
	}

	@Override
	public String toString() {
		return "PetInfo [playerId=" + playerId + ", petId=" + petId + ", talent=" + talent + ", level=" + level
				+ ", levelExp=" + levelExp + ", physique=" + physique + ", quality=" + quality + ", qualityBless="
				+ qualityBless + ", grade=" + grade + ", gradeBless=" + gradeBless + "]";
	}
}
