package com.chuangyou.xianni.entity.avatar;

/** 宠物默契表 */
public class AvatarCorrespondTemplateInfo {
	private int	avatarTempId;	// 分身模板
	private int	level;			// 默契等级
	private int	needItem;		// 需要物品
	private int	needNum;		// 需要数量
	private int	maxPro;			// 进度上限
	private int	rate;			// 成功率
	private int	blood;			// 血量
	private int	soul;			// 元魂
	private int	attack;			// 攻击
	private int	defence;		// 防御
	private int	accurate;		// 命中
	private int	dodge;			// 闪避
	private int	crit;			// 暴击
	private int	critDefence;	// 抗暴
	private int	fitTime;		// 合体时间
	private int	energyLimit;	// 灵力上限

	public int getAvatarTempId() {
		return avatarTempId;
	}

	public void setAvatarTempId(int avatarTempId) {
		this.avatarTempId = avatarTempId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getNeedItem() {
		return needItem;
	}

	public void setNeedItem(int needItem) {
		this.needItem = needItem;
	}

	public int getNeedNum() {
		return needNum;
	}

	public void setNeedNum(int needNum) {
		this.needNum = needNum;
	}

	public int getBlood() {
		return blood;
	}

	public void setBlood(int blood) {
		this.blood = blood;
	}

	public int getSoul() {
		return soul;
	}

	public void setSoul(int soul) {
		this.soul = soul;
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

	public int getMaxPro() {
		return maxPro;
	}

	public void setMaxPro(int maxPro) {
		this.maxPro = maxPro;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public int getFitTime() {
		return fitTime;
	}

	public void setFitTime(int fitTime) {
		this.fitTime = fitTime;
	}

	public int getEnergyLimit() {
		return energyLimit;
	}

	public void setEnergyLimit(int energyLimit) {
		this.energyLimit = energyLimit;
	}

}
