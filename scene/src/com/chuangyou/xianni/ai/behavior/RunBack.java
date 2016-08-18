package com.chuangyou.xianni.ai.behavior;

import com.chuangyou.xianni.ai.AIState;
import com.chuangyou.xianni.role.objects.Monster;

public class RunBack extends MonsterBaseBehavior {

	public RunBack(Monster m) {
		super(AIState.RUNBACK, m);
	}

	@Override
	public void exe() {
		// todo:速归对象加上无敌BUFF，不可被攻击
		getMonster().addBuffer(getMonster().getInvincibleBuffer());
		// 寻路
		if (getMonster().isNavFail()) {
			getMonster().moveto(getMonster().getInitPosition());
		} else {
			getMonster().stop(true);
			getMonster().navigateto(getMonster().getInitPosition());
		}
	}

	@Override
	public AIState next() {
		if (getMonster().isNavWaiting())
			return AIState.INVALID;
		if (!getMonster().isArrial())
			return AIState.INVALID;

		this.getMonster().removeBuffer(getMonster().getInvincibleBuffer());
		return AIState.IDLE;
	}

}
