package com.chuangyou.xianni.ai.behavior;

import java.util.ArrayList;
import java.util.List;

import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.ai.AIState;
import com.chuangyou.xianni.role.objects.Monster;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.warfield.navi.seeker.NavmeshSeeker;
import com.chuangyou.xianni.warfield.navi.seeker.NavmeshSeekerStatuCode;

public class RunBack extends BaseBehavior {

	public RunBack(Monster m) {
		super(AIState.RUNBACK, m);
	}

	@Override
	public void exe() {
		// 寻路
		// todo:速归对象加上无敌BUFF，不可被攻击
		getMonster().stop(true);
		getMonster().navigateto(getMonster().getInitPosition());
		
//		System.out.println("RunBack：    "+getMonster().getInitPosition().toString());
	}

	@Override
	public AIState next() {
		if (getMonster().isNavWaiting())
			return AIState.INVALID;
		if (!getMonster().isArrial())
			return AIState.INVALID;
		return AIState.IDLE;
	}

}
