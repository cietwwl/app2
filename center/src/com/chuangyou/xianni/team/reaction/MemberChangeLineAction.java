package com.chuangyou.xianni.team.reaction;

import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.team.reaction.abstractAction.TeamHasAction;

/**
 * 队伍成员上线离线
 * @author laofan
 *
 */
public class MemberChangeLineAction extends TeamHasAction {

	private boolean isLine;
	
	public MemberChangeLineAction(GamePlayer player, PBMessage packet, boolean isLine) {
		super(player, packet);
		this.isLine = isLine;
	}

	

	@Override
	public void teamExec(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		t.changeLine(isLine, member);
//		member.setOnline(isLine);
		
	}

}
