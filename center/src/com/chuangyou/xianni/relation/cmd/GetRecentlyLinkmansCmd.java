package com.chuangyou.xianni.relation.cmd;

import com.chuangyou.common.protobuf.pb.friend.GetRecentlyLinkmansReqProto.GetRecentlyLinkmansReqMsg;
import com.chuangyou.common.protobuf.pb.friend.GetRecentlyLinkmansRespProto.GetRecentlyLinkmansRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.relation.manager.RelationProtoUtil;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.word.WorldMgr;

@Cmd(code = Protocol.C_REQ_GETRECENTLYLINKMANS, desc = "获取最近联系人")
public class GetRecentlyLinkmansCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		GetRecentlyLinkmansReqMsg req = GetRecentlyLinkmansReqMsg.parseFrom(packet.getBytes());
		
		GetRecentlyLinkmansRespMsg.Builder resp = GetRecentlyLinkmansRespMsg.newBuilder();
		int count = 0;
		for(long id: req.getPlayerIdList()){
			GamePlayer targetPlayer = WorldMgr.getPlayer(id);
			if(targetPlayer == null) continue;
			if(count > 30) break;
			resp.addPlayer(RelationProtoUtil.change(targetPlayer));
		}
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_GETRECENTLYLINKMANS, resp);
		player.sendPbMessage(pkg);
	}

}
