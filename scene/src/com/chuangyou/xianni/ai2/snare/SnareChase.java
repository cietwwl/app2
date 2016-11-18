package com.chuangyou.xianni.ai2.snare;

import com.chuangyou.common.util.Vector3;
import com.chuangyou.xianni.ai.AIState;
import com.chuangyou.xianni.role.objects.Living;
import com.chuangyou.xianni.role.objects.Snare;
import com.chuangyou.xianni.warfield.field.Field;

public class SnareChase extends SnareBaseBehavior {

	public SnareChase(Snare m) {
		super(AIState.CHASE, m);
	}

	@Override
	public void exe() {
		Field f = getSnare().getField();
		if (f == null) {
			return;
		}
		Living l = getSnare().getLocking();
		if (l == null) {
			return;
		}
		if (!checkAttackTarget(l)) {
			return;
		}
		getSnare().stop(false);
		getSnare().moveto(l.getPostion());
	}

	@Override
	public AIState next() {
		return AIState.CHASE;
	}

	/**
	 * 是否需要向目标移动
	 * 
	 * @return
	 */
	private boolean checkAttackTarget(Living l) {
		Field f = getSnare().getField();
		if (f == null) {
			return false;
		}
		if (l.getField() != f) {
			return false;
		}
		if (getSnare().getPostion() != null && l.getPostion() != null) {
			float distance = Vector3.distance(getSnare().getPostion(), l.getPostion());
			if (distance <= 1) {
				return false;
			}
		}
		return true;
	}

}
