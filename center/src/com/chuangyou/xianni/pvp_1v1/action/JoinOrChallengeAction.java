package com.chuangyou.xianni.pvp_1v1.action;

import com.chuangyou.xianni.exec.Action;
import com.chuangyou.xianni.exec.ActionQueue;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.pvp_1v1.PvP1v1Manager;

public class JoinOrChallengeAction extends Action {
	private GamePlayer joiner;

	public JoinOrChallengeAction(GamePlayer joiner, ActionQueue queue) {
		super(queue);
		this.joiner = joiner;
	}

	@Override
	public void execute() {
		PvP1v1Manager.joinOrChallenge(joiner);
	}

}


