package com.chuangyou.xianni.chat.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.protobuf.pb.chat.ChatReceiveProto.ChatReceiveMsg;
import com.chuangyou.common.protobuf.pb.chat.ChatSendProto.ChatSendMsg;
import com.chuangyou.xianni.chat.manager.action.ChatBaseAction;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.protocol.Protocol;

public class ChatManager {
	private static List<ChatReceiveMsg> worldHistory = new ArrayList<>();
	
	private static Map<Integer, Map<Long, Long>> lastTimeMap = new HashMap<>();
	
	/**
	 * 发送聊天消息
	 * @param player
	 * @param sendMsg
	 */
	public static void sendChatMsg(GamePlayer player, ChatSendMsg sendMsg){
		ChatBaseAction action = ChatSenderFactory.getIns().getAction(sendMsg.getChannel());
		if(action == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.CHAT_CHANNEL_NOT_EXIST, Protocol.C_CHAT_SEND, "频道不存在");
			return;
		}
		
		action.sendChatMsg(player, sendMsg);
	}
	
	/**
	 * 世界频道添加历史记录
	 * @param chatMsg
	 */
	public static void addWorldHistory(ChatReceiveMsg chatMsg){
		if(worldHistory.size() >= 100){
			worldHistory.remove(0);
		}
		worldHistory.add(chatMsg);
	}
	
	/**
	 * 获取世界频道历史记录
	 * @return
	 */
	public static List<ChatReceiveMsg> getWorldHistory(){
		return worldHistory;
	}
	
	/**
	 * 设置上次发言时间，做CD判断使用
	 * @param channel
	 * @param playerId
	 * @param time
	 */
	public static void setLastTime(int channel, long playerId, long time){
		Map<Long, Long> channelLastTimeMap =  lastTimeMap.get(channel);
		if(channelLastTimeMap == null){
			channelLastTimeMap = new HashMap<>();
			lastTimeMap.put(channel, channelLastTimeMap);
		}
		channelLastTimeMap.put(playerId, time);
	}
	
	/**
	 * 获取上次发言时间，如果未发言，返回-1
	 * @param channel
	 * @param playerId
	 * @return
	 */
	public static long getLastTime(int channel, long playerId){
		Map<Long, Long> channelLastTimeMap =  lastTimeMap.get(channel);
		if(channelLastTimeMap == null){
			return -1;
		}
		Long time =  channelLastTimeMap.get(playerId);
		if(time == null){
			return -1;
		}
		return time;
	}
}
