package com.chuangyou.xianni.friend.cmd;

import java.util.List;

import com.chuangyou.common.protobuf.pb.friend.GetRecentlyLinkmansRespProto.GetRecentlyLinkmansRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.friend.Friend;
import com.chuangyou.xianni.friend.manager.FriendManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.word.WorldMgr;

@Cmd(code=Protocol.C_REQ_GETRECENTLYLINKMANS,desc="获取最近联系人")
public class GetRecentlyLinkmansCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		Friend friend = player.getFriendInventory().getFriend();
				
		List<Long> list = friend.toRecentlyLinkmansIdList();
		
		GetRecentlyLinkmansRespMsg.Builder resp = GetRecentlyLinkmansRespMsg.newBuilder();
		
		for (GamePlayer role : WorldMgr.getAllPlayers()) {
//			GamePlayer role = WorldMgr.getPlayer(roleId);
			if(role!=null){
				resp.addRoles(FriendManager.change(role));
			}
		}
		//Collections.reverse(resp.getRolesBuilderList());
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_GETRECENTLYLINKMANS, resp);
		player.sendPbMessage(pkg);
	}

}
