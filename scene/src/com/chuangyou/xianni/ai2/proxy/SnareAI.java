package com.chuangyou.xianni.ai2.proxy;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.ai.behavior.snare.SnareBaseBehavior;
import com.chuangyou.xianni.ai.behavior.snare.SnareChase;
import com.chuangyou.xianni.ai.behavior.snare.SnareIdle;
import com.chuangyou.xianni.ai.behavior.snare.SnareMoveLine;
import com.chuangyou.xianni.ai.behavior.snare.SnareMoveRandom;
import com.chuangyou.xianni.battle.snare.SnareConstant.MoveType;
import com.chuangyou.xianni.constant.RoleConstants.RoleType;
import com.chuangyou.xianni.role.action.UpdatePositionActionUtil;
import com.chuangyou.xianni.role.objects.Snare;

public class SnareAI extends AI {
	private Snare				snare;
	private SnareBaseBehavior	behavior;

	public SnareAI(Snare snare) {
		super(snare);
		this.snare = snare;
		if (snare.getCreater().getType() != RoleType.player) {
			behavior = createBehavior();
		}
	}

	@Override
	public void exe() {
		try {
			if (!living.isArrial()) {
				UpdatePositionActionUtil.updataCurPos(living);
			}

			snare.exe();
			move();
		} catch (Exception e) {
			Log.error("SnareAI", e);
		}
	}

	public void move() {
		if (behavior == null || snare.isDie()) {
			return;
		}
		behavior.exe();
	}

	// 创建陷阱移动行为
	public SnareBaseBehavior createBehavior() {
		switch (snare.getSnareInfo().getMoveType()) {
			case MoveType.STANDING:
				return new SnareIdle(snare);
			case MoveType.MOVE_LINE:
				return new SnareMoveLine(snare);
			case MoveType.MOVE_LOCKING:
				return new SnareChase(snare);
			case MoveType.MOVE_RANDOM:
				return new SnareMoveRandom(snare);
			default:
				return new SnareIdle(snare);
		}
	}

	@Override
	public void exec() {

	}
}
