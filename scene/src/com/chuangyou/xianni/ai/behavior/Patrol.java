package com.chuangyou.xianni.ai.behavior;

import com.chuangyou.common.util.MathUtils;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.ai.AIState;
import com.chuangyou.xianni.cooldown.CoolDownTypes;
import com.chuangyou.xianni.entity.spawn.AiConfig;
import com.chuangyou.xianni.role.objects.Monster;

public class Patrol extends BaseBehavior {

	private final int PatrolProbability = 50;
	private Vector3 patrolTarget;
	// 可以巡逻
	private boolean needPatrol = false;
	// private Selector playerSelector;// = new PlayerSelectorHelper();

	public Patrol(Monster m) {
		super(AIState.PATROL, m);
		// playerSelector = new PlayerSelectorHelper(getMonster());
	}

	@Override
	public void exe() {
		needPatrol = true;

		AiConfig aiConfig = getMonster().getAiConfig();
		int patrolRange = aiConfig.getPatrolRange();
		if (patrolRange == 0) {
			needPatrol = false;
			return;
		}
		if (patrolRange > 0) {
			// 判断是否能巡逻
			if (!MathUtils.GetProbability(PatrolProbability)) {
				needPatrol = false;
				return;
			}
		}

		patrolTarget = getMonster().getPostion();
		patrolTarget = MathUtils.GetRandomVector3ByCenter(getMonster().getInitPosition(), patrolRange);
		if (!isValidPoint(patrolTarget)) {	// 该点不能达到
			needPatrol = false;
			return;
		}

		getMonster().stop(false);
		getMonster().moveto(patrolTarget);

	}

	@Override
	public AIState next() {
		// if (!needPatrol)
		// return AIState.IDLE; // 距离太短，不处理
		if (getMonster().isCooldowning(CoolDownTypes.BE_ATTACK, null))
			return AIState.BEATTACK;
		if (!getMonster().isArrial()) {
			return AIState.INVALID;
		}
		// float distance = Vector3.distance(getMonster().getInitPosition(), getMonster().getPostion());// 出生点与目标的距离
		// if (distance > getMonster().getMonsterInfo().getSeekEnemyRange())
		// return AIState.RUNBACK;

		return AIState.IDLE;
	}

}
