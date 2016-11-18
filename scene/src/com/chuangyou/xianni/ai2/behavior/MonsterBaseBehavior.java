package com.chuangyou.xianni.ai2.behavior;

import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.ai2.AIState2;
import com.chuangyou.xianni.cooldown.CoolDownTypes;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Monster;
import com.chuangyou.xianni.warfield.FieldMgr;
import com.chuangyou.xianni.warfield.navi.seeker.NavmeshSeeker;
import com.chuangyou.xianni.warfield.navi.seeker.NavmeshTriangle;

public abstract class MonsterBaseBehavior {
	private AIState2		state;
	protected Monster	monster;
	
	protected MonsterBaseBehavior(AIState2 state, Monster m) {
		this.state = state;
		this.monster = m;
	}

	public static MonsterBaseBehavior BehaviorBuilder(AIState2 state, Monster monster) {
		switch (state) {
			case IDLE:
				return new Idle(monster);
			case PATROL:
				return new Patrol(monster);
			case ATTACK:
				return new Attack(monster);
			case CHASE:
				return new Chase(monster);
			case RUNBACK:
				return new RunBack(monster);
			case RUNAWAY:
				return new RunAway(monster);
			default:
				return null;
		}
	}

	public Monster getMonster() {
		return monster;
	}

	/**
	 * 当前状态
	 * 
	 * @return
	 */
	public AIState2 getState() {
		return state;
	}

	protected void runbackTo(Vector3 target) {
	}

	/**
	 * 执行
	 */
	public abstract AIState2 exe();

	// /**
	// * 下一个执行的状态
	// *
	// * @return
	// */
	// public abstract AIState22 next();

	/**
	 * 是否为有效的点
	 * 
	 * @param point
	 * @return
	 */
	protected boolean isValidPoint(Vector3 point) {
		NavmeshSeeker seeker = FieldMgr.getIns().GetSeekerTemp(getMonster().getField().getFieldInfo().getResName());
		NavmeshTriangle tri = seeker.getTriangle(point);
		return tri != null;
	}

	/**
	 * 检测当前仇恨目标的有效性
	 * 
	 * @param l
	 * @return
	 */
	protected boolean isValidTarget(Living l) {
		if (l == null || l.isDie()) {
			return false;
		}
		return true;
	}

	/**
	 * 检测CD
	 * 
	 * @return
	 */
	protected boolean checkCooldown(CoolDownTypes type) {
		return getMonster().isCooldowning(type, null);
	}
}
