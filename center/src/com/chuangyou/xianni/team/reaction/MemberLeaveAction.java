package com.chuangyou.xianni.team.reaction;

import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.team.reaction.abstractAction.TeamHasAction;

/**
 * 成员主动离开队伍
 * @author laofan
 *
 */
public class MemberLeaveAction extends TeamHasAction {

	public MemberLeaveAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void teamExec(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		t.removeMember(member);
	}

}
