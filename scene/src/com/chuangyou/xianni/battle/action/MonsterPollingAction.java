package com.chuangyou.xianni.battle.action;

import com.chuangyou.xianni.ai.proxy.MonsterAI;
import com.chuangyou.xianni.role.action.UpdatePositionAction;
import com.chuangyou.xianni.role.objects.Monster;

public class MonsterPollingAction extends PollingAction {
	private MonsterAI				ai;
	private UpdatePositionAction	upPos;
	static int						delay	= 200;

	public MonsterPollingAction(Monster monster, MonsterAI ai, UpdatePositionAction upPos) {
		super(monster, delay);
		this.ai = ai;
		this.upPos = upPos;
	}

	@Override
	public void exec() {
		ai.exe();
		upPos.exe();
	}

}
