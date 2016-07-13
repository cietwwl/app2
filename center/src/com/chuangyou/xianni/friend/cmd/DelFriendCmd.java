package com.chuangyou.xianni.friend.cmd;

import com.chuangyou.common.protobuf.pb.friend.DelFriendReqProto.DelFriendReqMsg;
import com.chuangyou.common.protobuf.pb.friend.DelFriendRespProto.DelFriendRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.friend.Friend;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code=Protocol.C_REQ_DELFRIEND,desc="删除好友")
public class DelFriendCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		
		DelFriendReqMsg msg = DelFriendReqMsg.parseFrom(packet.getBytes());
		
		Friend friend = player.getFriendInventory().getFriend();
		friend.delFriend(msg.getFriendRoleId());
		friend.setOp(Option.Update);
		
		
		DelFriendRespMsg.Builder resp = DelFriendRespMsg.newBuilder();
		resp.setFriendRoleId(msg.getFriendRoleId());
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_DELFRIEND, resp);
		player.sendPbMessage(pkg);
	}

}
