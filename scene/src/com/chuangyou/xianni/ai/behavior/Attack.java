package com.chuangyou.xianni.ai.behavior;

import java.util.ArrayList;
import java.util.List;

import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.ai.AIState;
import com.chuangyou.xianni.battle.skill.Skill;
import com.chuangyou.xianni.constant.SkillConstant.SkillMainType;
import com.chuangyou.xianni.cooldown.CoolDownTypes;
import com.chuangyou.xianni.entity.buffer.LivingState;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Monster;

public class Attack extends MonsterBaseBehavior {
	// 需要追击
	private boolean needChase = false;
	// 本次攻击处理无效，切换到IDLE走下一次轮询
	private boolean needIdle = false;

	public Attack(Monster m) {
		super(AIState.ATTACK, m);
		// getMonster().setCurSkillID(1001);
	}

	@Override
	public void exe() {
		// 攻击目标
		List<Living> targets = new ArrayList<Living>();
		
		needChase = false;
		needIdle = false;
		// 获取进攻目标
		Long targetID = getMonster().getAttackTarget();
		Living tmpTarget = getMonster().getField().getLiving(targetID);
		// 当前目标是无效，移除当前仇恨目标，走下一次IDLE
		if (!isValidTarget(tmpTarget)) {
			getMonster().removeHatred(targetID);
			needIdle = true;
			return;
		}
		// 判断是否需要追击
		// System.out.println(getMonster().getId() + "攻击怪物位置：" + getMonster().getPostion() + "怪物目标位置：" + tmpTarget.getPostion() + "距离："
		// + Vector3.distance(getMonster().getPostion(), tmpTarget.getPostion()));

		int attackRange = getMonster().getMonsterInfo().getAttackRange();
		// if (getMonster().getId() == 1000000000033L)
		// System.out.println("getMonster().getPostion(): "+getMonster().getPostion().toString()+" tmpTarget.getPostion(): "+tmpTarget.getPostion().toString()+"
		// 距离："+Vector3.distance(getMonster().getPostion(), tmpTarget.getPostion()));

		if (Vector3.distance(getMonster().getPostion(), tmpTarget.getPostion()) > attackRange) {// 查询攻击距离，这里暂时写死 = 2
			if (getMonster().getMonsterInfo().getFollowUpDistance() == 0) {
				needChase = false;
				return;
			}
			needChase = true;
			return;
		}

		// 依次判断所有技能是否使用
		// 1. 判断是否在施法范围
		// true : 直接处理攻击
		// false : 追击
		// 挂攻击CD
		// getMonster().stop(true);
		// 攻击处理
		// 添加攻击cd
		// System.err.println("doAttack");
		// getMonster().setCurSkillID(1001);

		// Skill test = new Skill(BattleTempMgr.getActionInfo(1001));
		// getMonster().addSkill(test);
		// targets.clear();
		targets.add(tmpTarget);
		// System.out.println(tmpTarget.getPostion());
		// AttackOrderControler.attackOrder(getMonster(), getMonster().getCurSkillID(), targets, getMonster().getPostion(), tmpTarget.getPostion());
		Skill skill = getMonster().getAttackSkill();
		if(skill==null)
			return;
		// 判断技能是否被冻结
		int type = skill.getSkillTempateInfo().getMasterType();
		if (type == SkillMainType.COMMON_ATTACK && !getMonster().checkStatus(LivingState.NORMAL_ATTACK)) {
			return;
		}
		if (type == SkillMainType.ACTIVE && !getMonster().checkStatus(LivingState.SKILL_ATTAK)) {
			return;
		}
		if (type == SkillMainType.PASSIVE && !getMonster().checkStatus(LivingState.PERKS)) {
			return;
		}
		getMonster().stop(true);
		AttackOrderControler.attackOrder(getMonster(), skill.getActionId(), targets, getMonster().getPostion(), tmpTarget.getPostion());
		// SceneManagers.cooldownManager.addCooldown(getMonster(), CoolDownTypes.SKILL, null, SceneGlobal.AI_MONSTER_ATTACK_COOL_DOWN);
		getMonster().addCooldown(CoolDownTypes.SKILL, null, getMonster().getMonsterInfo().getAttackSpeed() * 1000);

		getMonster().addCooldown(CoolDownTypes.SKILL, skill.getActionId() + "", skill.getTemplateInfo().getCooldown() * 1000);
	}

	@Override
	public AIState next() {
		if (needIdle)
			return AIState.IDLE;
		if (needChase)
			return AIState.CHASE;
		if (getMonster().isCooldowning(CoolDownTypes.SKILL, null)) {
			return AIState.INVALID;
		}
		return AIState.IDLE;
	}

}
