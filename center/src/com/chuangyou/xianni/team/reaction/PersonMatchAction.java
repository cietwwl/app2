package com.chuangyou.xianni.team.reaction;

import com.chuangyou.common.protobuf.pb.team.MatchTargetReqProto.MatchTargetReqMsg;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.team.pool.TeamMatchPool;
import com.chuangyou.xianni.team.reaction.abstractAction.TeamNoAction;
import com.chuangyou.xianni.team.struct.Team;

/**
 *  个人自动匹配
 * @author laofan
 *
 */
public class PersonMatchAction extends TeamNoAction {

	private int targetId;
	
	public PersonMatchAction(GamePlayer player, PBMessage packet,short p) {
		super(player, packet);
		this.protocol = p;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void teamExec(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		MatchTargetReqMsg msg = MatchTargetReqMsg.parseFrom(packet.getBytes());
		targetId = msg.getTargetId();
		
		TeamMgr.removePersonTarget(targetId, player.getPlayerId());
		player.getBasePlayer().setTeamTarget(targetId);
		
		//todo查找符符合条件的队伍
		TeamMatchPool pools = TeamMgr.getTeamTargetMatch().get(targetId);
		if(pools!=null && pools.getPools().size()>0){
			int teamId = pools.peekFirst();
			Team t = TeamMgr.getAllTeams().get(teamId);
			t.addMember(player.getPlayerId());
		}else{
			TeamMgr.addPersonTarget(targetId, player.getPlayerId());
		}
//		
//		PersonMatchAction action = new PersonMatchAction(player, msg.getTargetId());
//		action.getActionQueue().enqueue(action);
	}

}
