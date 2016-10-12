package com.chuangyou.xianni.entity.robot;


import com.chuangyou.xianni.constant.EnumAttr;

public class RobotTemplate {
	private int		id;				// 机器人ID段 10001-20001
	private int		type;			// 机器人类型
	private String	nickName;
	private int		fluctuate;		// 战力浮动
	private int 	modeId;			// 模型ID
	private int		job;
	private int		level;
	private int		magicWeaponId;
	private int		fashionId;
	private int		weaponId;
	private int		wingId;
	private int		moveSpeed;		// 移动速度
	private int		attackSpeed;	// 攻击速度
	private long	soulHpValue;	// 魂血
	private long	hp;				// 生命
	private int		hurtValue;		// 伤害
	private int		armorValue;		// 护甲值
	private int		soulHurtValue;	// 魂攻击
	private int		soulArmorValue;	// 魂防御
	private int		hitRateValue;	// 命中
	private int		dodgeValue;		// 闪避
	private int		critValue;		// 暴击
	private int		toughnessValue;	// 抗暴
	
	private int		guildId; // 帮派ID
	private int		guildJob; // 帮派内职位
	
	private String	skillIds; // 技能列表

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getJob() {
		return job;
	}

	public void setJob(int job) {
		this.job = job;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getMagicWeaponId() {
		return magicWeaponId;
	}

	public void setMagicWeaponId(int magicWeaponId) {
		this.magicWeaponId = magicWeaponId;
	}

	public int getFashionId() {
		return fashionId;
	}

	public void setFashionId(int fashionId) {
		this.fashionId = fashionId;
	}

	public int getWeaponId() {
		return weaponId;
	}

	public void setWeaponId(int weaponId) {
		this.weaponId = weaponId;
	}

	public int getWingId() {
		return wingId;
	}

	public void setWingId(int wingId) {
		this.wingId = wingId;
	}

	public int getMoveSpeed() {
		return moveSpeed;
	}

	public void setMoveSpeed(int moveSpeed) {
		this.moveSpeed = moveSpeed;
	}

	public int getAttackSpeed() {
		return attackSpeed;
	}

	public void setAttackSpeed(int attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	public long getSoulHpValue() {
		return soulHpValue;
	}

	public void setSoulHpValue(long soulHpValue) {
		this.soulHpValue = soulHpValue;
	}

	public long getHp() {
		return hp;
	}

	public void setHp(long hp) {
		this.hp = hp;
	}

	public int getHurtValue() {
		return hurtValue;
	}

	public void setHurtValue(int hurtValue) {
		this.hurtValue = hurtValue;
	}

	public int getArmorValue() {
		return armorValue;
	}

	public void setArmorValue(int armorValue) {
		this.armorValue = armorValue;
	}

	public int getSoulHurtValue() {
		return soulHurtValue;
	}

	public void setSoulHurtValue(int soulHurtValue) {
		this.soulHurtValue = soulHurtValue;
	}

	public int getSoulArmorValue() {
		return soulArmorValue;
	}

	public void setSoulArmorValue(int soulArmorValue) {
		this.soulArmorValue = soulArmorValue;
	}

	public int getHitRateValue() {
		return hitRateValue;
	}

	public void setHitRateValue(int hitRateValue) {
		this.hitRateValue = hitRateValue;
	}

	public int getDodgeValue() {
		return dodgeValue;
	}

	public void setDodgeValue(int dodgeValue) {
		this.dodgeValue = dodgeValue;
	}

	public int getCritValue() {
		return critValue;
	}

	public void setCritValue(int critValue) {
		this.critValue = critValue;
	}

	public int getToughnessValue() {
		return toughnessValue;
	}

	public void setToughnessValue(int toughnessValue) {
		this.toughnessValue = toughnessValue;
	}
	
	public int getFluctuate() {
		return fluctuate;
	}

	public void setFluctuate(int fluctuate) {
		this.fluctuate = fluctuate;
	}

	public long getValue(EnumAttr attr) {
		switch (attr) {
			case BLOOD:
				return getHp();
			case CUR_BLOOD:
				return getHp();
			case SOUL:
				return getSoulHpValue();
			case CUR_SOUL:
				return getSoulHpValue();
			case ATTACK:
				return getHurtValue();
			case DEFENCE:
				return getArmorValue();
			case SOUL_ATTACK:
				return getSoulHurtValue();
			case SOUL_DEFENCE:
				return getSoulArmorValue();
			case ACCURATE:
				return getHitRateValue();
			case DODGE:
				return getDodgeValue();
			case CRIT:
				return getCritValue();
			case CRIT_DEFENCE:
				return getToughnessValue();
			case SPEED:
				return getMoveSpeed();
			default:
				return 0;
		}
	}

	public int getGuildId() {
		return guildId;
	}

	public void setGuildId(int guildId) {
		this.guildId = guildId;
	}

	public int getGuildJob() {
		return guildJob;
	}

	public void setGuildJob(int guildJob) {
		this.guildJob = guildJob;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getModeId() {
		return modeId;
	}

	public void setModeId(int modeId) {
		this.modeId = modeId;
	}

	public String getSkillIds() {
		return skillIds;
	}

	public void setSkillIds(String skillIds) {
		this.skillIds = skillIds;
	}
}
