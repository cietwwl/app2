package com.chuangyou.xianni.ai2.behavior;

import com.chuangyou.common.util.MathUtils;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.ai2.AIState2;
import com.chuangyou.xianni.role.objects.Monster;

public class Patrol extends MonsterBaseBehavior {
	public Patrol(Monster m) {
		super(AIState2.PATROL, m);
	}

	@Override
	public AIState2 exe() {
		int patrolRange = getMonster().getMonsterInfo().getSeekEnemyRange();
		Vector3 patrolTarget = MathUtils.GetRandomVector3ByCenter(getMonster().getInitPosition(), patrolRange);
		if (!isValidPoint(patrolTarget)) { // 该点不能达到
			return AIState2.IDLE;
		}
		getMonster().stop(false);
		getMonster().moveto(patrolTarget);
		return AIState2.IDLE;
	}
}
