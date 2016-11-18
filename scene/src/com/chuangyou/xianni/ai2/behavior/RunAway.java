package com.chuangyou.xianni.ai2.behavior;

import com.chuangyou.common.util.MathUtils;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.ai2.AIState2;
import com.chuangyou.xianni.role.action.UpdatePositionActionUtil;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Monster;

public class RunAway extends MonsterBaseBehavior {

	public RunAway(Monster m) {
		super(AIState2.RUNAWAY, m);
	}

	@Override
	public AIState2 exe() {
		long attacker = getMonster().getAttacker();
		if (attacker == 0) {
			return AIState2.IDLE;
		}
		Living living = getMonster().getField().getLiving(attacker);
		if (living == null)
			return AIState2.IDLE;
		Vector3 target = MathUtils.GetVector3InDistance2(living.getPostion(), getMonster().getPostion(), 2);
		if (!isValidPoint(target)) { // 该点不能达到
			target = MathUtils.GetVector3InDistance2(getMonster().getPostion(), living.getPostion(), 2);
			if (!isValidPoint(target))
				return AIState2.IDLE;
		}
		// System.out.println("postion: " + living.getPostion() + " target: " +
		// target);
		getMonster().stop(false);
		getMonster().moveto(target);
		getMonster().setAttacker(0);
		if (!getMonster().isArrial()) {
			return AIState2.RUNAWAY;
		}
		return AIState2.IDLE;
	}
}
