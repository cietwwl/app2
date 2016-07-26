package com.chuangyou.xianni.entity.spawn;

public class MonsterInfo {
	private int monsterId;

	private String name;				// 怪物名

	private int level;				// 怪物等级

	private int skin;				// skinID

	private int monsterType;		// 怪物种类

	// private int seekEnemyRange; // 索敌范围
	//
	// private int attackRange; // 攻击范围
	//
	// private int followUpDistance; // 追击距离
	//
	// private int moveSpeed; // 移动速度
	//
	// private int attackSpeed; // 攻击速度

	private long soulHpValue;		// 魂血

	private long hp;				// 生命

	private int hurtValue;			// 伤害

	private int armorValue;			// 护甲值

	private int soulHurtValue;		// 魂攻击

	private int soulArmorValue;		// 魂防御

	private int hitRateValue;		// 命中

	private int dodgeValue;			// 闪避

	private int critValue;			// 暴击

	private int toughnessValue;		// 抗暴

	private int hurtType;			// 伤害类型

	private int beKilledExp;		// 击杀经验
	/** 以怪物出生点为圆心的半径范围，-1全图巡逻，0不巡逻 */
	private int patrolRange;
	/** 失去目标后是否返回 */
	private byte isReturn;
	/** 失去目标后是否恢复满状态 */
	private byte isRelive;

	/** 死亡掉落 */
	private int drop1;

	/** 死亡掉落 */
	private int drop2;

	/** 死亡掉落 */
	private int drop3;

	/** 死亡掉落 */
	private int drop4;
	/** 技能 */
	private String skillIds;
	/** ai **/
	private int aiId;

	public MonsterInfo() {
		super();
	}

	public int getMonsterId() {
		return monsterId;
	}

	public void setMonsterId(int monsterId) {
		this.monsterId = monsterId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getMonsterType() {
		return monsterType;
	}

	public void setMonsterType(int monsterType) {
		this.monsterType = monsterType;
	}

	// public int getSeekEnemyRange() {
	// return seekEnemyRange;
	// }
	//
	// public void setSeekEnemyRange(int seekEnemyRange) {
	// this.seekEnemyRange = seekEnemyRange;
	// }
	//
	// public int getAttackRange() {
	// return attackRange;
	// }
	//
	// public void setAttackRange(int attackRange) {
	// this.attackRange = attackRange;
	// }
	//
	// public int getFollowUpDistance() {
	// return followUpDistance;
	// }
	//
	// public void setFollowUpDistance(int followUpDistance) {
	// this.followUpDistance = followUpDistance;
	// }
	//
	// public int getMoveSpeed() {
	// return moveSpeed;
	// }
	//
	// public void setMoveSpeed(int moveSpeed) {
	// this.moveSpeed = moveSpeed;
	// }
	//
	// public int getAttackSpeed() {
	// return attackSpeed;
	// }
	//
	// public void setAttackSpeed(int attackSpeed) {
	// this.attackSpeed = attackSpeed;
	// }

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

	public int getHurtValue() {
		return hurtValue;
	}

	public void setHurtValue(int hurtValue) {
		this.hurtValue = hurtValue;
	}

	public int getHurtType() {
		return hurtType;
	}

	public void setHurtType(int hurtType) {
		this.hurtType = hurtType;
	}

	public int getArmorValue() {
		return armorValue;
	}

	public void setArmorValue(int armorValue) {
		this.armorValue = armorValue;
	}

	public int getBeKilledExp() {
		return beKilledExp;
	}

	public void setBeKilledExp(int beKilledExp) {
		this.beKilledExp = beKilledExp;
	}

	public int getSkin() {
		return skin;
	}

	public void setSkin(int skin) {
		this.skin = skin;
	}

	public int getPatrolRange() {
		return patrolRange;
	}

	public void setPatrolRange(int patrolRange) {
		this.patrolRange = patrolRange;
	}

	public byte getIsReturn() {
		return isReturn;
	}

	public void setIsReturn(byte isReturn) {
		this.isReturn = isReturn;
	}

	public byte getIsRelive() {
		return isRelive;
	}

	public void setIsRelive(byte isRelive) {
		this.isRelive = isRelive;
	}

	public boolean isReturnOnLostObj() {
		return this.isReturn == 1;
	}

	public boolean isReliveOnLostObj() {
		return this.isRelive == 1;
	}

	public int getDrop1() {
		return drop1;
	}

	public void setDrop1(int drop1) {
		this.drop1 = drop1;
	}

	public int getDrop2() {
		return drop2;
	}

	public void setDrop2(int drop2) {
		this.drop2 = drop2;
	}

	public int getDrop3() {
		return drop3;
	}

	public void setDrop3(int drop3) {
		this.drop3 = drop3;
	}

	public int getDrop4() {
		return drop4;
	}

	public void setDrop4(int drop4) {
		this.drop4 = drop4;
	}

	public String getSkillIds() {
		return skillIds;
	}

	public void setSkillIds(String skillIds) {
		this.skillIds = skillIds;
	}

	public int getAiId() {
		return aiId;
	}

	public void setAiId(int aiId) {
		this.aiId = aiId;
	}

}
