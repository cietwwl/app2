package com.chuangyou.xianni.ai.behavior;

import com.chuangyou.common.util.MathUtils;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.ai.AIState;
import com.chuangyou.xianni.cooldown.CoolDownTypes;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Monster;
import com.chuangyou.xianni.warfield.field.Field;

public class Chase extends BaseBehavior {

	// 可以巡逻
	private boolean needChase = false;
	private Vector3 chaseTarget;

	public Chase(Monster m) {
		super(AIState.CHASE, m);
	}

	@Override
	public void exe() {
		needChase = true;
		Field f = getMonster().getField();
		if (f == null) {
			needChase = false;
			return;
		}
		Living l = f.getLiving(getMonster().getTarget());
		if (l == null) {
			needChase = false;
			return;
		}
		if (checkAttackTarget()) {
			return;
		}
		int followUpDistance = getMonster().getAiConfig().getFollowUpDistance();
		if (followUpDistance == 0) { // 不追击
			return;
		}

		chaseTarget = MathUtils.GetRandomVector3ByCenter(l.getPostion(), 1, false);// l.getPostion();
		if (!isValidPoint(chaseTarget))
			chaseTarget = l.getPostion();

		// System.out.println("自己位置：" + getMonster().getPostion() + " 追击到点：" + chaseTarget+" 人物位置： "+l.getPostion());
		getMonster().stop(false);
		getMonster().moveto(chaseTarget);
	}

	@Override
	public AIState next() {
		if (!needChase)
			return AIState.IDLE;
		if (getMonster().isCooldowning(CoolDownTypes.BE_ATTACK, null))
			return AIState.BEATTACK;
		if (checkAttackTarget())
			return AIState.ATTACK;

		Field f = getMonster().getField();
		Living l = f.getLiving(getMonster().getTarget());
		// 当前目标已经无效
		// 移除目标仇恨
		if (!isValidTarget(l)) {
			getMonster().removeHatred(getMonster().getTarget());
			return AIState.IDLE;
		}
		if (getMonster().getAiConfig().getFollowUpDistance() == 0)// 不追击
			return AIState.IDLE;

		// 获取当前离目标的距离
		chaseTarget = MathUtils.GetRandomVector3ByCenter(l.getPostion(), 1, false);
		float distance = Vector3.distance(getMonster().getInitPosition(), chaseTarget);// 出生点与目标的距离
		// System.out.println(getMonster().getId() + "---追击怪物位置：" + getMonster().getPostion() + "怪物目标位置：" + l.getPostion());
		// 脱离追击范围
		if (distance > getMonster().getAiConfig().getFollowUpDistance()) {
			float leaveBornDistance = Vector3.distance(getMonster().getInitPosition(), getMonster().getPostion());// 当前位置与出生点的距离
			// 在巡逻范围，找其他目标，移除当前的最大仇恨
			if (leaveBornDistance < getMonster().getMonsterInfo().getSeekEnemyRange()) {
				getMonster().removeHatred(l.getId());
				return AIState.IDLE;
			} else {
				// 清除所有的仇恨，速归
				getMonster().cleanHatreds();
				return AIState.RUNBACK;
			}
		} else {
			// System.err.println("isArrial = " + getMonster().isArrial());
			if (getMonster().isArrial()) {
				getMonster().stop(false);
				getMonster().moveto(chaseTarget);
			} else {// 继续追
				getMonster().stop(false);
				getMonster().moveto(chaseTarget);
				return AIState.INVALID;
			}
		}
		return AIState.ATTACK;
	}

	/**
	 * 检查目标能力可以攻击
	 * 
	 * @return
	 */
	private boolean checkAttackTarget() {
		Field f = getMonster().getField();
		Living l = f.getLiving(getMonster().getTarget());
		if (l == null)
			return false;
		// System.err.println("distance = " + Vector3.distance(getMonster().getPostion(), l.getPostion()));
		if (Vector3.distance(getMonster().getPostion(), l.getPostion()) <= getMonster().getMonsterInfo().getAttackRange())
			return true;
		return false;
	}

}
