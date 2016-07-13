package com.chuangyou.xianni.friend.cmd;

import java.util.List;

import com.chuangyou.common.protobuf.pb.friend.GetFriendsRespProto.GetFriendsRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.friend.Friend;
import com.chuangyou.xianni.friend.manager.FriendManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.word.WorldMgr;

/** 
 *  获取玩家的好友
 * @author laofan
 *
 */
@Cmd(code=Protocol.C_REQ_GETFRIENDS,desc="获取玩家的好友")
public class GetFriendsCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
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
