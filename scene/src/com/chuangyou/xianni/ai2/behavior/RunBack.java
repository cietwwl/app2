package com.chuangyou.xianni.ai2.behavior;

import com.chuangyou.xianni.ai2.AIState2;
import com.chuangyou.xianni.battle.buffer.Buffer;
import com.chuangyou.xianni.battle.buffer.BufferFactory;
import com.chuangyou.xianni.battle.mgr.BattleTempMgr;
import com.chuangyou.xianni.entity.buffer.SkillBufferTemplateInfo;
import com.chuangyou.xianni.role.objects.Monster;

public class RunBack extends MonsterBaseBehavior {
	private static final int RUNBACK_BUFF_ID = 99999999; // 无敌buffer id

	public RunBack(Monster m) {
		super(AIState2.RUNBACK, m);
	}

	public AIState2 exe() {
		getMonster().moveto(getMonster().getInitPosition());
		getMonster().cleanHatreds();
		addRunBackBuff();
		return AIState2.INVALID;
	}

	public void addRunBackBuff() {
		SkillBufferTemplateInfo sbinfo = BattleTempMgr.getBufferInfo(RUNBACK_BUFF_ID);
		if (sbinfo != null) {
			Buffer buffer = BufferFactory.createBuffer(this.getMonster(), this.getMonster(), sbinfo);
			getMonster().addInvincibleBuffer(buffer);
		}
	}

	public void removeRunBackBuff() {
		getMonster().removeInvincibleBuffer();
	}
}
