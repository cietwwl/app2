package com.chuangyou.xianni.relation.cmd;

import com.chuangyou.common.protobuf.pb.friend.DelFriendReqProto.DelFriendReqMsg;
import com.chuangyou.common.protobuf.pb.friend.DelFriendRespProto.DelFriendRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.constant.PlayerRelationConstant;
import com.chuangyou.xianni.entity.relation.RelationInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_REQ_DELFRIEND, desc = "删除好友")
public class DelFriendCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		DelFriendReqMsg req = DelFriendReqMsg.parseFrom(packet.getBytes());
		
		if(player.getRelationInventory() == null) return;
		
		RelationInfo relation = player.getRelationInventory().getRelation(req.getFriendRoleId());
		
		if(relation == null || relation.getRelationType(player.getPlayerId()) != PlayerRelationConstant.FRIEND){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.FRIEND_NOT, packet.getCode());
			return;
		}
		player.getRelationInventory().updateRelation(req.getFriendRoleId(), PlayerRelationConstant.NONE);
		
		DelFriendRespMsg.Builder resp = DelFriendRespMsg.newBuilder();
		resp.setFriendRoleId(req.getFriendRoleId());
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_DELFRIEND, resp);
		player.sendPbMessage(pkg);
	}

}
