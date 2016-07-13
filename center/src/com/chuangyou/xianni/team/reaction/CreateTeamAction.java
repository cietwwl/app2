package com.chuangyou.xianni.team.reaction;

import com.chuangyou.common.protobuf.pb.team.CreateTeamReqProto.CreateTeamReqMsg;
import com.chuangyou.common.protobuf.pb.team.CreateTeamRespProto.CreateTeamRespMsg;
import com.chuangyou.common.protobuf.pb.team.TeamInfoRespProto.TeamInfoRespMsg;
import com.chuangyou.xianni.netty.GatewayLinkedSet;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.team.TeamMgr;
import com.chuangyou.xianni.team.reaction.abstractAction.TeamNoAction;
import com.chuangyou.xianni.team.struct.Team;
/**
 * 创建队伍
 * @author laofan
 *
 */
public class CreateTeamAction extends TeamNoAction {

	private Team t;
	private int targetId;
	
	public CreateTeamAction(GamePlayer player, PBMessage packet) {
		super(player, packet);
	}


	@Override
	public void teamExec(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		CreateTeamReqMsg msg = CreateTeamReqMsg.parseFrom(packet.getBytes());
		if(msg.hasTargetId()){
			targetId = msg.getTargetId();
		}
		
		t = TeamMgr.createTeam(player,targetId);
		TeamMgr.addTeamTargetPool(targetId, t.getId());
		sendMsg();
		
//		CreateTeamAction action = new CreateTeamAction(player,targetId);
//		action.getActionQueue().enqueue(action);
	}
	
	
	private void sendMsg() {
		// TODO Auto-generated method stub
		TeamInfoRespMsg.Builder msg = getTeamMsg(t);
		PBMessage pkg =MessageUtil.buildMessage(Protocol.U_RESP_TEAM_INFO,msg);
		player.sendPbMessage(pkg);
		
		//todo通知scene服务器
		pkg = MessageUtil.buildMessage(Protocol.S_TEAM_INFO,msg);
		GatewayLinkedSet.send2Server(pkg);
		
		//通知成功
		CreateTeamRespMsg.Builder resp = CreateTeamRespMsg.newBuilder();
		pkg = MessageUtil.buildMessage(Protocol.U_RESP_CREATE_TEAM,resp);
		player.sendPbMessage(pkg);
		
	}


}
