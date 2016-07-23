package com.chuangyou.xianni.entity.spawn;

public class AiConfig {
	private int id;
	private int alertRange;// 警戒范围
	private int patrolRange;// 巡逻范围
	private int attackDistance;// 攻击距离
	private int followUpDistance;// 追击距离
	private int moveSpeed;// 移动速度
	private int attackSpeed;// 攻击速度

	private boolean runBack;// 返回出生点
	private boolean fullState;// 恢复满状态
	private boolean activeAttackPlayers;// 主动攻击玩家
	private boolean activeAttackMonster;// 主动攻击怪物
	private boolean isOpenAttack;// 是否攻击
	private boolean isOpenMove;// 是否移动
	private boolean isOpenPatrol;// 是否巡逻
	private boolean isOpenRunBack;// 是否回归
	private boolean isRewardExp;// 是否奖励经验
	private boolean isRewardStone;// 是否奖励灵石
	private boolean isDropped;// 是否掉落
	private int script;// 脚本

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAlertRange() {
		return alertRange;
	}

	public void setAlertRange(int alertRange) {
		this.alertRange = alertRange;
	}

	public int getPatrolRange() {
		return patrolRange;
	}

	public void setPatrolRange(int patrolRange) {
		this.patrolRange = patrolRange;
	}

	public int getAttackDistance() {
		return attackDistance;
	}

	public void setAttackDistance(int attackDistance) {
		this.attackDistance = attackDistance;
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

	public boolean isRunBack() {
		return runBack;
	}

	public void setRunBack(boolean runBack) {
		this.runBack = runBack;
	}

	public boolean isFullState() {
		return fullState;
	}

	public void setFullState(boolean fullState) {
		this.fullState = fullState;
	}

	public boolean isActiveAttackPlayers() {
		return activeAttackPlayers;
	}

	public void setActiveAttackPlayers(boolean activeAttackPlayers) {
		this.activeAttackPlayers = activeAttackPlayers;
	}

	public boolean isActiveAttackMonster() {
		return activeAttackMonster;
	}

	public void setActiveAttackMonster(boolean activeAttackMonster) {
		this.activeAttackMonster = activeAttackMonster;
	}

	public boolean isOpenAttack() {
		return isOpenAttack;
	}

	public void setOpenAttack(boolean isOpenAttack) {
		this.isOpenAttack = isOpenAttack;
	}

	public boolean isOpenMove() {
		return isOpenMove;
	}

	public void setOpenMove(boolean isOpenMove) {
		this.isOpenMove = isOpenMove;
	}

	public boolean isOpenPatrol() {
		return isOpenPatrol;
	}

	public void setOpenPatrol(boolean isOpenPatrol) {
		this.isOpenPatrol = isOpenPatrol;
	}

	public boolean isOpenRunBack() {
		return isOpenRunBack;
	}

	public void setOpenRunBack(boolean isOpenRunBack) {
		this.isOpenRunBack = isOpenRunBack;
	}

	public boolean isRewardExp() {
		return isRewardExp;
	}

	public void setRewardExp(boolean isRewardExp) {
		this.isRewardExp = isRewardExp;
	}

	public boolean isRewardStone() {
		return isRewardStone;
	}

	public void setRewardStone(boolean isRewardStone) {
		this.isRewardStone = isRewardStone;
	}

	public boolean isDropped() {
		return isDropped;
	}

	public void setDropped(boolean isDropped) {
		this.isDropped = isDropped;
	}

	public int getScript() {
		return script;
	}

	public void setScript(int script) {
		this.script = script;
	}

}
