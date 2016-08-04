package com.chuangyou.xianni.entity.spawn;

import java.util.ArrayList;
import java.util.List;

public class AiConfig {
	private int id;
	// private int alertRange;// 警戒范围
	// private int patrolRange;// 巡逻范围
	// private int attackDistance;// 攻击距离
	// private int followUpDistance;// 追击距离
	// private int moveSpeed;// 移动速度
	// private int attackSpeed;// 攻击速度

	private boolean runBack;// 返回出生点
	private boolean fullState;// 恢复满状态
	private boolean activeAttackPlayer;// 是否主动攻击玩家
	private boolean activeAttackSameMonster;// 是否主动攻击同种怪
	private boolean activeAttackNotSameMonster;// 是否主动攻击不同种怪物
	private boolean runAway;// 是否逃跑
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

	public boolean isActiveAttackPlayer() {
		return activeAttackPlayer;
	}

	public void setActiveAttackPlayer(boolean activeAttackPlayer) {
		this.activeAttackPlayer = activeAttackPlayer;
	}

	public boolean isActiveAttackSameMonster() {
		return activeAttackSameMonster;
	}

	public void setActiveAttackSameMonster(boolean activeAttackSameMonster) {
		this.activeAttackSameMonster = activeAttackSameMonster;
	}

	public boolean isActiveAttackNotSameMonster() {
		return activeAttackNotSameMonster;
	}

	public void setActiveAttackNotSameMonster(boolean activeAttackNotSameMonster) {
		this.activeAttackNotSameMonster = activeAttackNotSameMonster;
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

	public boolean isRunAway() {
		return runAway;
	}

	public void setRunAway(boolean runAway) {
		this.runAway = runAway;
	}

}
