package com.chuangyou.xianni.friend.cmd;

import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.friend.logic.FriendLogic;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerState;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

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
		if(player.getPlayerState() == PlayerState.OFFLINE)return;
		new FriendLogic().doGetFirends(player);
		
	}

}
