package com.chuangyou.xianni.friend.cmd;

import com.chuangyou.common.protobuf.pb.friend.AddFriendReqProto.AddFriendReqMsg;
import com.chuangyou.common.protobuf.pb.friend.AddFriendRespProto.AddFriendRespMsg;
import com.chuangyou.common.protobuf.pb.friend.NotifyAddFriendRespProto.NotifyAddFriendRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.friend.Friend;
import com.chuangyou.xianni.friend.manager.FriendManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.word.WorldMgr;

/**
 * 添加好友
 * @author laofan
 *
 */
@Cmd(code=Protocol.C_REQ_ADDFRIEND,desc="添加好友")
public class AddFriendCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		
		AddFriendReqMsg msg = AddFriendReqMsg.parseFrom(packet.getBytes());
		
		GamePlayer addRole = WorldMgr.getPlayer(msg.getFriendRoleId());
		if(addRole == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.ROLE_NOT_EXISTS, packet.getCode());
			return;
		}
		Friend friend = player.getFriendInventory().getFriend();
		if(friend.getFriendIdAndLinkTimeMap().containsKey(msg.getFriendRoleId())){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Friend_IS_EXISTS, packet.getCode());
			return;
		}
		if(msg.getFriendRoleId() == player.getPlayerId()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Friend_IsError_AddSelf, packet.getCode());
			return;
		}		
		
		friend.addFriend(msg.getFriendRoleId());
		friend.setOp(Option.Update);
		
		AddFriendRespMsg.Builder message = AddFriendRespMsg.newBuilder();
		message.setFriendInfo(FriendManager.change(addRole));
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_ADDFRIEND, message);
		player.sendPbMessage(pkg);
		
		//通知对方
		NotifyAddFriendRespMsg.Builder notify = NotifyAddFriendRespMsg.newBuilder();
		notify.setFriendIndo(FriendManager.change(player));
		pkg = MessageUtil.buildMessage(Protocol.U_RESP_NOTIFYADDFRIEND, notify);
		addRole.sendPbMessage(pkg);

	}

}
