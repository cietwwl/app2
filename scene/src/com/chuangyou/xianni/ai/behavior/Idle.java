package com.chuangyou.xianni.ai.behavior;

import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.ai.AIState;
import com.chuangyou.xianni.config.SceneGlobal;
import com.chuangyou.xianni.cooldown.CoolDownTypes;
import com.chuangyou.xianni.manager.SceneManagers;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Monster;

public class Idle extends BaseBehavior {

	public Idle(Monster m) {
		super(AIState.IDLE, m);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void exe() {
		// TODO Auto-generated method stub
		if (!getMonster().isCooldowning(CoolDownTypes.IDLE, null)) // SceneGlobal.AI_MONSTER_IDEL
		{
			getMonster().addCooldown(CoolDownTypes.IDLE, null, SceneGlobal.AI_MONSTER_IDEL);
		}
	}

	@Override
	public AIState next() {
		// 空闲CD中
		if (checkCooldown(CoolDownTypes.IDLE) || checkCooldown(CoolDownTypes.BE_ATTACK))
			return AIState.INVALID;
		// 存在仇恨列表
		if (getMonster().getHatreds().size() > 0) {
			// 获取进攻目标
			Long targetID = getMonster().getAttackTarget();
			Living tmpTarget = getMonster().getField().getLiving(targetID);
			if (!isValidTarget(tmpTarget)) {
				getMonster().removeHatred(targetID);
				return AIState.INVALID;
			}
			getMonster().setTarget(targetID);
			// 计算距离， 根据出生点判断
			float distance = Vector3.distance(getMonster().getInitPosition(), tmpTarget.getPostion());
			// 脱离追击范围
			// System.out.println("----怪物出生点：" + getMonster().getInitPosition() + "怪物目标位置：" + tmpTarget.getPostion() + "距离：" + distance);
			if (distance > getMonster().getMonsterInfo().getFollowUpDistance()) {// SceneGlobal.AI_MONSTER_OUTCHASE
				// 清除所有仇恨
				getMonster().cleanHatreds();
				float leaveBornDistance = Vector3.distance(getMonster().getInitPosition(), getMonster().getPostion());
				if (leaveBornDistance < getMonster().getMonsterInfo().getSeekEnemyRange()) {// SceneGlobal.AI_MONSTER_PATROLRANGE
					return AIState.PATROL;
				} else {
					return AIState.RUNBACK;
				}
			} else {
				// 在追击范围内，切换到攻击状态
				if (checkCooldown(CoolDownTypes.SKILL))
					return AIState.INVALID;
				if (Vector3.distance(getMonster().getPostion(), tmpTarget.getPostion()) > getMonster().getMonsterInfo().getAttackRange())// SceneGlobal.AI_MONSTER_ATTACK_RANGE
					return AIState.CHASE;
				return AIState.ATTACK;
			}
		}
		float leaveBornDistance = Vector3.distance(getMonster().getInitPosition(), getMonster().getPostion());
		if (leaveBornDistance < getMonster().getMonsterInfo().getSeekEnemyRange()) {// SceneGlobal.AI_MONSTER_PATROLRANGE
			return AIState.PATROL;
		} else {
			return AIState.RUNBACK;
		}
	}

}
