package com.chuangyou.xianni.team.reaction;

import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerState;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.team.reaction.abstractAction.AbstractTeamAction;
import com.chuangyou.xianni.team.struct.Team;
import com.chuangyou.xianni.team.struct.TeamMember;

/**
 * 获取队伍信息
 * @author laofan
 *
 */
public class GetTeamInfoAction extends AbstractTeamAction {

	public GetTeamInfoAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}


	@Override
	public void execute(GamePlayer player, PBMessage packet) {
		// TODO Auto-generated method stub
		if(TeamMgr.getPlayerTeamMap().containsKey(player.getPlayerId())){
			TeamMember member = TeamMgr.getPlayerTeamMap().get(player.getPlayerId());
			Team t = TeamMgr.getAllTeams().get(member.getTeamId());
			
			PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_TEAM_INFO, t.getTeamMsg());
			player.sendPbMessage(pkg);
		}
	}

}
