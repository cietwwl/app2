package com.chuangyou.xianni.team.reaction;

import com.chuangyou.common.protobuf.pb.team.SetTargetTeamReqProto.SetTargetTeamReqMsg;
import com.chuangyou.common.protobuf.pb.team.TeamTargetChangeRespProto.TeamTargetChangeRespMsg;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.BroadcastUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.team.reaction.abstractAction.TeamLeaderAction;

/**
 * 队长重新设置队伍目标
 * 
 * @author laofan
 *
 */
public class SetTargetAction extends TeamLeaderAction {

	protected int targetId;

	public SetTargetAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void teamLeaderExec(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		SetTargetTeamReqMsg msg = SetTargetTeamReqMsg.parseFrom(packet.getBytes());

		targetId = msg.getTargetId();

		if (t.getTargetId() != targetId) {
			// TeamMgr.removeTeamTargetMatch(t.getTargetId(), t.getId());
			// 如果匹配池中有这个队伍。哪么这个队伍也要换匹配目标
			if (TeamMgr.getTeamTargetMatch().containsKey(t.getTargetId())) {
				if (TeamMgr.getTeamTargetMatch().get(t.getTargetId()).getClonePools().contains(t.getId())) {
					TeamMgr.removeTeamTargetMatch(t.getTargetId(), t.getId());
					TeamMgr.addTeamTargetMatch(targetId, t.getId());
				}
			}
			TeamMgr.removeTeamTargetPool(t.getTargetId(), t.getId());
			t.setTargetId(targetId);
			TeamMgr.addTeamTargetPool(targetId, t.getId());
		}
		sendMsg();
		// SetTargetAction action = new SetTargetAction(player, t, targetid,
		// getProtocol());
		// action.getActionQueue().enqueue(action);
	}

	private void sendMsg() {
		// TODO Auto-generated method stub
		// todo广播新目标
		TeamTargetChangeRespMsg.Builder resp = TeamTargetChangeRespMsg.newBuilder();
		resp.setTargetId(targetId);
		BroadcastUtil.sendBroadcastPacket(t.getPlayers(0), Protocol.U_RESP_TEAM_SET_TARGET, resp.build());
	}

}
