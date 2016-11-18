package com.chuangyou.xianni.entity.spawn;

public class MonsterInfo {
	private int		monsterId;

	private String	name;				// 怪物名

	private int		level;				// 怪物等级

	private int		skin;				// skinID

	private int		monsterType;		// 怪物种类

	private int		dynamic;			// 动态怪物，会根据玩家等级来刷新不同怪物

	private int		alertRange;			// 警戒范围 (进入范围内玩家纳入仇恨)

	private int		seekEnemyRange;		// 巡逻范围（仅判断是否巡逻）

	private int		attackRange;		// 攻击范围(普通攻击距离)

	private int		followUpDistance;	// 追击距离(追击仇恨距离玩家)

	private int		moveSpeed;			// 移动速度

	private int		attackSpeed;		// 攻击速度

	private long	soulHpValue;		// 魂血

	private long	hp;					// 生命

	private int		hurtValue;			// 伤害

	private int		armorValue;			// 护甲值

	private int		soulHurtValue;		// 魂攻击

	private int		soulArmorValue;		// 魂防御

	private int		hitRateValue;		// 命中

	private int		dodgeValue;			// 闪避

	private int		critValue;			// 暴击

	private int		toughnessValue;		// 抗暴

	private int		hurtType;			// 伤害类型

	private int		beKilledExp;		// 击杀经验

	/*
	 * 1 击杀归属 经验 队伍成员杀死怪物后，本地图所有成员均单独获得经验和怪物掉落 击杀脚本 掉落 参与奖励 任务进度
	 * 任何人杀死怪物后，本地图参与战斗并造成伤害的玩家均可获得此怪物的任务进度 2 参与奖励 经验
	 * 任何人杀死怪物后，本地图参与战斗并造成伤害的玩家均可获得此怪物的击杀经验、掉落、任务进度 掉落 任务进度
	 */
	private int		dropType;			// 怪物掉落类型

	/** 死亡掉落 */
	private int		drop1;

	/** 死亡掉落 */
	private int		drop2;

	/** 死亡掉落 */
	private int		drop3;

	/** 死亡掉落 */
	private int		drop4;
	/** 技能 */
	private String	skillIds;
	private int		defaultState;		// 默认状态
	/** ai **/
	private int		aiId;

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

	public int getAlertRange() {
		return alertRange;
	}

	public void setAlertRange(int alertRange) {
		this.alertRange = alertRange;
	}

	public int getSeekEnemyRange() {
		return seekEnemyRange;
	}

	public void setSeekEnemyRange(int seekEnemyRange) {
		this.seekEnemyRange = seekEnemyRange;
	}

	public int getAttackRange() {
		return attackRange;
	}

	public void setAttackRange(int attackRange) {
		this.attackRange = attackRange;
	}

	public int getFollowUpDistance() {
		return followUpDistance;
	}

	public void setFollowUpDistance(int followUpDistance) {
		this.followUpDistance = followUpDistance;
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

	public int getDropType() {
		return dropType;
	}

	public void setDropType(int dropType) {
		this.dropType = dropType;
	}

	public int getDynamic() {
		return dynamic;
	}

	public void setDynamic(int dynamic) {
		this.dynamic = dynamic;
	}

	public int getDefaultState() {
		return defaultState;
	}

	public void setDefaultState(int defaultState) {
		this.defaultState = defaultState;
	}

}
