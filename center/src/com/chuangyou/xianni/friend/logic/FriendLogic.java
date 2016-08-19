package com.chuangyou.xianni.friend.logic;

import java.util.List;

import com.chuangyou.common.protobuf.pb.friend.GetFriendsRespProto.GetFriendsRespMsg;
import com.chuangyou.xianni.entity.friend.Friend;
import com.chuangyou.xianni.friend.manager.FriendManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.word.WorldMgr;

public class FriendLogic {

	public void doGetFirends(GamePlayer player){
		if(player.getFriendInventory()==null)return;
		Friend friend = player.getFriendInventory().getFriend();
		List<Long> list = friend.toFriendIdList();
		
		GetFriendsRespMsg.Builder msg = GetFriendsRespMsg.newBuilder();
		
		for (Long roleId : list) {
			GamePlayer role = WorldMgr.getPlayer(roleId);
			if(role!=null){
				msg.addFriends(FriendManager.change(role));
			}
		}
		
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_GETFRIENDS, msg);
		player.sendPbMessage(pkg);
	}
}
