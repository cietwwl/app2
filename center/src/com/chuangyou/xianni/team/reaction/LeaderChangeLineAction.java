package com.chuangyou.xianni.team.reaction;

import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;

/**
 *  队长改变自己在线/或离线状态
 * @author laofan
 *
 */
public class LeaderChangeLineAction extends LeaderLeaveTeamAction {

	private boolean isLine;
	
	public LeaderChangeLineAction(GamePlayer player, PBMessage packet, boolean isLine) {
		super(player, packet);
		this.isLine = isLine;
	}


	
	@Override
	public void teamLeaderExec(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		if(isLine==false){			
			changeLeader();
		}
		t.synchronizedchangeLine(isLine, member);
//		member.setOnline(isLine);
	
	}

}
