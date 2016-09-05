package com.chuangyou.xianni.relation.manager;

import com.chuangyou.common.protobuf.pb.friend.FriendInfoProto.FriendInfoMsg;
import com.chuangyou.xianni.entity.player.PlayerInfo;
import com.chuangyou.xianni.player.GamePlayer;

public class RelationProtoUtil {

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
	
	public static FriendInfoMsg.Builder change(PlayerInfo playerInfo, short onlineState){
		FriendInfoMsg.Builder msg = FriendInfoMsg.newBuilder();
		msg.setRoleId(playerInfo.getPlayerId());
		msg.setRoleName(playerInfo.getNickName());
		msg.setOnlineStatus(onlineState);
		msg.setVipLevel(playerInfo.getVipLevel());
		msg.setSkinId(playerInfo.getSkinId());
		msg.setLevel(playerInfo.getLevel());
		msg.setFight(playerInfo.getFight());
		return msg;
	}
}
