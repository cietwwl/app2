package com.chuangyou.xianni.ai2.behavior;

import com.chuangyou.common.util.MathUtils;
import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.ai2.AIState2;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Monster;
import com.chuangyou.xianni.warfield.field.Field;

public class Chase extends MonsterBaseBehavior {
	public Chase(Monster m) {
		super(AIState2.CHASE, m);
	}

	@Override
	public AIState2 exe() {
		Field f = getMonster().getField();
		if (f == null) {
			return AIState2.IDLE;
		}
		Living l = f.getLiving(getMonster().getTarget());
		if (l == null) {
			getMonster().setTarget(0);
			getMonster().setTargetPos(Vector3.Invalid);
			return AIState2.IDLE;
		}
		// 当追击范围在攻击距离内，则直接攻击
		if (Vector3.planeDistance(getMonster().getPostion(), l.getPostion()) <= getMonster().getMonsterInfo().getAttackRange()) {
			getMonster().setTargetPos(Vector3.Invalid);
			return AIState2.ATTACK;
		}
		// 不在攻击范围内，并且追击距离为0，则回到空闲状态
		int followUpDistance = getMonster().getMonsterInfo().getFollowUpDistance();
		if (followUpDistance == 0) { // 不追击
			return AIState2.IDLE;
		}

		// 当怪物追击位置目标位置不变，则继续朝目标移动
		if (!Vector3.IsInvalid(getMonster().getGoal()) && Vector3.Equal(getMonster().getGoal(), l.getPostion())) {
			return AIState2.CHASE;
		}
		// 当追击位置发生变化，则重新修正目标

		// 第一步 ： 当前人物超过索敌范围，则怪物继续巡逻，不预理会
		float leaveBornDistance = Vector3.distance(getMonster().getInitPosition(), l.getPostion());
		if (leaveBornDistance > getMonster().getMonsterInfo().getFollowUpDistance()) {
			if (monster.getAiConfig().isRunBack()) {
				return AIState2.RUNBACK;
			}
			return AIState2.IDLE;
		}
		// 继续追击 ： 修正追击目标
		int range = Math.min(getMonster().getMonsterInfo().getAttackRange(), 1);
		Vector3 tmpChase = MathUtils.GetRandomVector3ByCenter(l.getPostion(), range * 0.95f, false);
		getMonster().setTargetPos(l.getPostion());
		getMonster().stop(false);
		getMonster().moveto(tmpChase);
		return AIState2.CHASE;

	}
}
