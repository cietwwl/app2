package com.chuangyou.xianni.ai2.behavior;

import java.util.Set;

import com.chuangyou.common.util.MathUtils;
import com.chuangyou.xianni.ai2.AIState2;
import com.chuangyou.xianni.config.SceneGlobal;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Monster;
import com.chuangyou.xianni.warfield.helper.selectors.PlayerSelectorHelper;

public class Idle extends MonsterBaseBehavior {
	private static final int PatrolProbability = 30;

	public Idle(Monster m) {
		super(AIState2.IDLE, m);
	}

	@Override
	public AIState2 exe() {
		if (getMonster().getInitPosition() == null || getMonster().getPostion() == null) {
			return AIState2.INVALID;
		}
		// 是否有攻击目标
		boolean hasTarget = true;
		long targetID = getMonster().getTarget();
		if (targetID == 0) {
			hasTarget = false;
		}
		Living tmpTarget = getMonster().getField().getLiving(targetID);
		if (hasTarget && !isValidTarget(tmpTarget)) {
			getMonster().removeHatred(targetID);
			getMonster().setTarget(0);
			hasTarget = false;
		}

		if (hasTarget) {
			return AIState2.ATTACK;
		}

		// 当怪物有索敌范围 && 怪物不在移动中 && 符合随机几率
		if (getMonster().getMonsterInfo().getSeekEnemyRange() > 0 && getMonster().isArrial() && MathUtils.GetProbability(PatrolProbability)) {
			return AIState2.PATROL;
		}

		if (System.currentTimeMillis() - monster.getFindEnemyTime() > SceneGlobal.AI_MONSTER_PATROL) {
			Set<Long> nearPlayer = getMonster().getNears(new PlayerSelectorHelper(getMonster()));
			if (nearPlayer.size() == 0) {
				return AIState2.INVALID;
			} else {
				return AIState2.IDLE;
			}
		}
		return AIState2.INVALID;
	}

}
