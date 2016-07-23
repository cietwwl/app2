package com.chuangyou.xianni.ai.behavior;

import com.chuangyou.xianni.ai.AIState;
import com.chuangyou.xianni.role.objects.Monster;

public class RunBack extends BaseBehavior {

	public RunBack(Monster m) {
		super(AIState.RUNBACK, m);
	}

	@Override
	public void exe() {
		// todo:速归对象加上无敌BUFF，不可被攻击
		getMonster().addBuffer(getMonster().getInvincibleBuffer());
		// 寻路
		getMonster().stop(true);
		getMonster().navigateto(getMonster().getInitPosition());
		// System.out.println("RunBack： "+getMonster().getInitPosition().toString());

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
