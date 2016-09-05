package com.chuangyou.xianni.relation.cmd;

import com.chuangyou.common.protobuf.pb.friend.AddFriendReqProto.AddFriendReqMsg;
import com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.chat.manager.ChatManager;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.constant.ChatConstant;
import com.chuangyou.xianni.constant.PlayerRelationConstant;
import com.chuangyou.xianni.entity.relation.RelationInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.relation.manager.RelationProtoUtil;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.word.WorldMgr;

@Cmd(code = Protocol.C_REQ_ADDFRIEND, desc = "添加好友")
public class AddFriendCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		AddFriendReqMsg req = AddFriendReqMsg.parseFrom(packet.getBytes());
		
		if(player.getRelationInventory() == null) return;
		
		GamePlayer addRole = WorldMgr.getPlayer(req.getFriendRoleId());
		if(addRole == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.ROLE_NOT_EXISTS, packet.getCode());
			return;
		}
		if(req.getFriendRoleId() == player.getPlayerId()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Friend_IsError_AddSelf, packet.getCode());
			return;
		}
		RelationInfo relation = player.getRelationInventory().getRelation(req.getFriendRoleId());
		if(relation != null && relation.getRelationType(player.getPlayerId()) == PlayerRelationConstant.FRIEND){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Friend_IS_EXISTS, packet.getCode());
			return;
		}
		
		if(relation == null){
			player.getRelationInventory().addRelation(req.getFriendRoleId(), PlayerRelationConstant.FRIEND);
		}else{
			player.getRelationInventory().updateRelation(req.getFriendRoleId(), PlayerRelationConstant.FRIEND);
		}
		
		AddFriendRespMsg.Builder message = AddFriendRespMsg.newBuilder();
		message.setFriendInfo(RelationProtoUtil.change(addRole));
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_ADDFRIEND, message);
		player.sendPbMessage(pkg);
		
		//通知对方
//		NotifyAddFriendRespMsg.Builder notify = NotifyAddFriendRespMsg.newBuilder();
//		notify.setFriendIndo(RelationProtoUtil.change(player));
//		pkg = MessageUtil.buildMessage(Protocol.U_RESP_NOTIFYADDFRIEND, notify);
//		addRole.sendPbMessage(pkg);
		ChatManager.sendPromptMsg(player, addRole.getPlayerId(), ChatConstant.PromptType.ADDFRIEND, "");
	}

}
