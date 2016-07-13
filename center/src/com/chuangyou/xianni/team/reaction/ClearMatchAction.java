package com.chuangyou.xianni.team.reaction;

import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.team.reaction.abstractAction.TeamHasAction;
import com.chuangyou.xianni.team.reaction.abstractAction.TeamNoAction;
import com.chuangyou.xianni.team.reaction.abstractAction.TeamOrAction;

/**
 * 消除匹配
 * @author laofan
 *
 */
public class ClearMatchAction extends TeamOrAction {

	public ClearMatchAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public TeamHasAction getTeamHasAction() {
		// TODO Auto-generated method stub
		return new LeaderClearMatchAction(player, packet, protocol);
	}

	@Override
	public TeamNoAction getTeamNoAction() {
		// TODO Auto-generated method stub
		return new PersonClearMatchAction(player, packet, protocol);
	}

}
