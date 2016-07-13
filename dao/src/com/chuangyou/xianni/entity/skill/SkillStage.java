package com.chuangyou.xianni.entity.skill;

/** 技能阶段 */
public class SkillStage {
	private int gradeLevel = 0; // 等级
	private String levelName;// 阶段名称
	private String stageName;// 阶段名称
	private String condition;// 条件
//	private int addTemplateId;// 属性模板id
	private int soul = 0; // 元魂
	private int blood = 0; // 气血
	private int attack = 0; // 攻击
	private int defence = 0; // 防御
	private int soulAttack = 0; // 魂攻
	private int soulDefence = 0; // 魂防
	private int accurate = 0; // 命中
	private int dodge = 0; // 闪避
	private int crit = 0; // 暴击
	private int critDefence = 0; // 抗暴

	public int getGradeLevel() {
		return gradeLevel;
	}

	public void setGradeLevel(int gradeLevel) {
		this.gradeLevel = gradeLevel;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getStageName() {
		return stageName;
	}

	public void setStageName(String stageName) {
		this.stageName = stageName;
	}

	public String getCondition() {
		return condition;
	}

	public void setCondition(String condition) {
		this.condition = condition;
	}

//	public int getAddTemplateId() {
//		return addTemplateId;
//	}
//
//	public void setAddTemplateId(int addTemplateId) {
//		this.addTemplateId = addTemplateId;
//	}

	public int getSoul() {
		return soul;
	}

	public void setSoul(int soul) {
		this.soul = soul;
	}

	public int getBlood() {
		return blood;
	}

	public void setBlood(int blood) {
		this.blood = blood;
	}

	public int getAttack() {
		return attack;
	}

	public void setAttack(int attack) {
		this.attack = attack;
	}

	public int getDefence() {
		return defence;
	}

	public void setDefence(int defence) {
		this.defence = defence;
	}

	public int getSoulAttack() {
		return soulAttack;
	}

	public void setSoulAttack(int soulAttack) {
		this.soulAttack = soulAttack;
	}

	public int getSoulDefence() {
		return soulDefence;
	}

	public void setSoulDefence(int soulDefence) {
		this.soulDefence = soulDefence;
	}

	public int getAccurate() {
		return accurate;
	}

	public void setAccurate(int accurate) {
		this.accurate = accurate;
	}

	public int getDodge() {
		return dodge;
	}

	public void setDodge(int dodge) {
		this.dodge = dodge;
	}

	public int getCrit() {
		return crit;
	}

	public void setCrit(int crit) {
		this.crit = crit;
	}

	public int getCritDefence() {
		return critDefence;
	}

	public void setCritDefence(int critDefence) {
		this.critDefence = critDefence;
	}

}
