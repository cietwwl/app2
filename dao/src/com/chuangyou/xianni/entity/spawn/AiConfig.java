package com.chuangyou.xianni.entity.spawn;

import java.util.ArrayList;
import java.util.List;

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
	private int activeAttack;// 主动攻击 0 不攻击 1 攻击玩家 2 攻击怪物

	private boolean isOpenRunBack;// 是否逃跑
	private int rewardExp;// 每次被攻击奖励经验
	private String dropSet;// 每次被攻击 掉落集合
	private String script;// 死亡后的脚本
	private List<Integer> drop = new ArrayList<>();

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

	public int getRewardExp() {
		return rewardExp;
	}

	public void setRewardExp(int rewardExp) {
		this.rewardExp = rewardExp;
	}

	public String getDropSet() {
		return dropSet;
	}

	public void setDropSet(String dropSet) {
		this.dropSet = dropSet;
		if (dropSet != null) {
			for (String str : dropSet.split(",")) {
				if (!str.isEmpty())
					drop.add(Integer.valueOf(str));
			}
		}
	}

	public List<Integer> getDrop() {
		return drop;
	}

	public String getScript() {
		return script;
	}

	public void setScript(String script) {
		this.script = script;
	}

	public boolean isOpenRunBack() {
		return isOpenRunBack;
	}

	public void setOpenRunBack(boolean isOpenRunBack) {
		this.isOpenRunBack = isOpenRunBack;
	}

	public int getActiveAttack() {
		return activeAttack;
	}

	public void setActiveAttack(int activeAttack) {
		this.activeAttack = activeAttack;
	}

}
