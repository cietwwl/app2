package com.chuangyou.xianni.team.reaction;

import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.team.reaction.abstractAction.TeamNoAction;

/**
 * 个人下线
 * @author laofan
 *
 */
public class PersonChangeLineAction extends TeamNoAction {

	private boolean isLine;

	public PersonChangeLineAction(GamePlayer player, PBMessage packet, boolean isLine) {
		super(player, packet);
		this.isLine = isLine;
	}

	@Override
	public void teamExec(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		if(isLine == false){
			TeamMgr.removePersonTarget(player.getBasePlayer().getTeamTarget(), player.getPlayerId());
		}
	}

}
