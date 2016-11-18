package com.chuangyou.xianni.ai2.behavior;

import java.util.ArrayList;
import java.util.List;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.ai2.AIState2;
import com.chuangyou.xianni.battle.skill.Skill;
import com.chuangyou.xianni.constant.SkillConstant.SkillMainType;
import com.chuangyou.xianni.entity.buffer.LivingState;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Monster;

public class Attack extends MonsterBaseBehavior {
	public Attack(Monster m) {
		super(AIState2.ATTACK, m);
	}

	@Override
	public AIState2 exe() {
		int attackSpeed = Math.max(1, monster.getMonsterInfo().getAttackSpeed());
		// 判断怪物攻击频率
		if (System.currentTimeMillis() - monster.getAttackTime() <= attackSpeed * 1000) {
			return AIState2.ATTACK;
		}
		// 获取进攻目标
		long targetID = getMonster().getTarget();
		Living target = getMonster().getField().getLiving(targetID);
		// 当前目标是无效，移除当前仇恨目标
		if (!isValidTarget(target)) {
			getMonster().removeHatred(targetID);
			getMonster().setTarget(0);
			return AIState2.IDLE;
		}
		// 攻击范围
		int attackRange = getMonster().getMonsterInfo().getAttackRange();
		// 是否超出攻击范围
		if (Vector3.planeDistance(getMonster().getPostion(), target.getPostion()) > attackRange) {
			// 超出攻击范围，并且没有追击距离，则空闲
			if (getMonster().getMonsterInfo().getFollowUpDistance() == 0) {
				return AIState2.IDLE;
			}
			// 超出攻击范围，并且有追击距离，则追击
			return AIState2.CHASE;
		}

		// 获取可使用技能
		Skill skill = getMonster().getAttackSkill();
		if (skill == null) {
			return AIState2.IDLE;
		}
		// 判断技能是否被冻结
		int type = skill.getSkillTempateInfo().getMasterType();
		if (type == SkillMainType.COMMON_ATTACK && !getMonster().checkStatus(LivingState.NORMAL_ATTACK)) {
			return AIState2.IDLE;
		}
		if (type == SkillMainType.ACTIVE && !getMonster().checkStatus(LivingState.SKILL_ATTAK)) {
			return AIState2.IDLE;
		}
		if (type == SkillMainType.PASSIVE && !getMonster().checkStatus(LivingState.PERKS)) {
			return AIState2.IDLE;
		}

		// 攻击目标
		List<Living> targets = new ArrayList<>();
		targets.add(target);
		getMonster().stop(true);
		AttackOrderControler.attackOrder(getMonster(), skill.getActionId(), targets, getMonster().getPostion(), target.getPostion());
		monster.setAttackTime(System.currentTimeMillis());
		return AIState2.IDLE;
	}
}
