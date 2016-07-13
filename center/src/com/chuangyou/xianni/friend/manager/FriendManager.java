package com.chuangyou.xianni.friend.manager;

import com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.FriendInfoMsg;
import com.chuangyou.xianni.player.GamePlayer;

public class FriendManager {

	/**
	 * 转换为PB对象
	 * 
	 * @param player
	 * @return
	 */
	public static FriendInfoMsg.Builder change(GamePlayer player) {

		FriendInfoMsg.Builder msg = FriendInfoMsg.newBuilder();
		msg.setRoleId(player.getPlayerId());
		msg.setRoleName(player.getNickName());
		msg.setOnlineStatus(player.getPlayerState());
		msg.setVipLevel(player.getBasePlayer().getPlayerInfo().getVipLevel());
		msg.setSkinId(player.getBasePlayer().getPlayerInfo().getSkinId());
		msg.setLevel(player.getBasePlayer().getPlayerInfo().getLevel());
		msg.setFight(player.getBasePlayer().getPlayerInfo().getFight());
		return msg;

	}
}
