package com.chuangyou.xianni.proto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.chuangyou.common.protobuf.pb.common.BroadcastMsgProto.BroadcastMsg;
import com.chuangyou.xianni.netty.GatewayLinkedSet;
import com.chuangyou.xianni.protocol.Protocol;
import com.google.protobuf.Message;

public class BroadcastUtil {

	public static boolean sendBroadcastPacket(Set<Long> players, short protocol, Message message) {
		if (players == null || players.size() == 0 || message == null) {
			return false;
		}
		BroadcastMsg.Builder builder = BroadcastMsg.newBuilder();
		builder.addAllPlayers(players);
		builder.setProtocol(protocol);
		builder.setPacket(message.toByteString());
		PBMessage broadCastPb = MessageUtil.buildMessage(Protocol.G_BROADCAST_PACKET, builder.build());
		GatewayLinkedSet.sendAll(broadCastPb);
		return true;
	}

	public static boolean sendBroadcastPacket(List<Long> players, short protocol, Message message) {
		if (players == null || players.size() == 0 || message == null) {
			return false;
		}
		Set<Long> set = new HashSet<>();
		set.addAll(players);
		return sendBroadcastPacket(set, protocol, message);

	}
	
	/**
	 * 全服广播消息
	 * @param protocol
	 * @param message
	 * @return
	 */
	public static boolean sendBroadcasePacketToAll(short protocol, Message message){
		if(message == null){
			return false;
		}
		BroadcastMsg.Builder builder = BroadcastMsg.newBuilder();
		builder.setProtocol(protocol);
		builder.setPacket(message.toByteString());
		
		PBMessage broadcastPb = MessageUtil.buildMessage(Protocol.G_BROADCAST_SERVER, builder.build());
		GatewayLinkedSet.sendAll(broadcastPb);
		return true;
	}
}
