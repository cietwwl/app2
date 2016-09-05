package com.chuangyou.xianni.chat.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.chuangyou.common.protobuf.pb.chat.ChatReceiveProto.ChatReceiveMsg;
import com.chuangyou.common.protobuf.pb.chat.ChatSendProto.ChatSendMsg;
import com.chuangyou.common.util.SensitivewordFilterUtil;
import com.chuangyou.xianni.chat.manager.action.ChatBaseAction;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.constant.ChatConstant;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.chat.ChatMsgInfo;
import com.chuangyou.xianni.entity.player.PlayerInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.word.WorldMgr;

public class ChatManager {
	/**
	 * 世界频道聊天记录
	 */
	private static List<ChatReceiveMsg> worldHistory = new ArrayList<>();
	
	/**
	 * 上次发言时间
	 */
	private static ConcurrentHashMap<Integer, ConcurrentHashMap<Long, Long>> lastTimeMap = new ConcurrentHashMap<>();
	
//	/**
//	 * 私聊频道离线消息
//	 */
//	private static ConcurrentHashMap<Long, List<ChatMsgInfo>> privateOfflineMsgMap = new ConcurrentHashMap<>();
	
	/**
	 * 聊天频道离线消息
	 */
	private static ConcurrentHashMap<Long, ConcurrentHashMap<Integer, List<ChatMsgInfo>>> offlineMsgMap = new ConcurrentHashMap<>();
	
	/**
	 * 离线消息条数限制
	 */
	public static Map<Integer, Integer> offlineCountLimit = new HashMap<>();
	static {
//		offlineCountLimit.put(ChatConstant.Channel.PRIVATE, 200);
//		offlineCountLimit.put(ChatConstant.Channel.PROMPT, 100);
		offlineCountLimit.put(ChatConstant.Channel.PRIVATE, 3);
		offlineCountLimit.put(ChatConstant.Channel.PROMPT, 3);
	}
	
	/**
	 * 玩家发送聊天消息
	 * @param player
	 * @param sendMsg
	 */
	public static void sendChatMsg(GamePlayer player, ChatSendMsg sendMsg){
		ChatBaseAction action = ChatSenderFactory.getIns().getAction((short)sendMsg.getChannel());
		if(action == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.CHAT_CHANNEL_NOT_EXIST, Protocol.C_CHAT_SEND, "频道不存在");
			return;
		}
		
		ChatMsgInfo msg = new ChatMsgInfo();
		msg.setChannel(sendMsg.getChannel());
		msg.setReceiverId(sendMsg.getReceiverId());
		String resultContent = SensitivewordFilterUtil.getIntence().replaceSensitiveWord(sendMsg.getChatContent());
		msg.setChatContent(resultContent);
		
		action.sendChatMsg(player, msg);
	}
	
	/**
	 * 发送系统消息，暂时只有系统频道，以后会有滚屏公告等
	 * 
	 * @param channel
	 * @param content
	 */
	public static void sendSystemChatMsg(int channel, String content) {
		ChatSendMsg.Builder chatSendMsg = ChatSendMsg.newBuilder();
		chatSendMsg.setChannel(channel);
		chatSendMsg.setChatContent(content);
		sendChatMsg(null, chatSendMsg.build());
	}
	
	/**
	 * 发送系统提示
	 * @param senderId
	 * @param receiverId
	 * @param promptType 类型在ChatConstant.PromptType中配置
	 * @param content
	 */
	public static void sendPromptMsg(GamePlayer player, long receiverId, short promptType, String content){
		ChatBaseAction action = ChatSenderFactory.getIns().getAction(ChatConstant.Channel.PROMPT);
		if(action == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.CHAT_CHANNEL_NOT_EXIST, Protocol.C_CHAT_SEND, "频道不存在");
			return;
		}
		
		ChatMsgInfo chatSendMsg = new ChatMsgInfo();
		chatSendMsg.setChannel(ChatConstant.Channel.PROMPT);
		chatSendMsg.setReceiverId(receiverId);
		chatSendMsg.setChatContent(content);
		chatSendMsg.setParam1(promptType);
		
		action.sendChatMsg(player, chatSendMsg);
	}
	
	/**
	 * 世界频道添加历史记录
	 * @param chatMsg
	 */
	public static void addWorldHistory(ChatReceiveMsg chatMsg){
		synchronized (worldHistory) {
			if(worldHistory.size() >= 100){
				worldHistory.remove(0);
			}
			worldHistory.add(chatMsg);
		}
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
		ConcurrentHashMap<Long, Long> channelLastTimeMap =  lastTimeMap.get(channel);
		if(channelLastTimeMap == null){
			channelLastTimeMap = new ConcurrentHashMap<>();
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
	
//	/**
//	 * 离线消息入库
//	 */
//	public static void savePrivateOfflineMsg(){
//		Map<Long, List<ChatMsgInfo>> msgMap = privateOfflineMsgMap;
//		privateOfflineMsgMap = new ConcurrentHashMap<>();
//		
//		Iterator<Entry<Long, List<ChatMsgInfo>>> iterator = msgMap.entrySet().iterator();
//		while(iterator.hasNext()){
//			Entry<Long, List<ChatMsgInfo>> entry = iterator.next();
//			List<ChatMsgInfo> playerMsgs = new ArrayList<>();
//			playerMsgs.addAll(entry.getValue());
//			
//			for(ChatMsgInfo msg: playerMsgs){
//				if(msg.getOp() == Option.Insert){
//					DBManager.getChatPrivateOfflineMsgDao().addMsg(msg);
//				}
//			}
//		}
//	}
	
//	/**
//	 * 添加离线消息
//	 * @param msg
//	 */
//	public static void addPrivateOfflineMsg(ChatMsgInfo msg){
//		msg.setOp(Option.Insert);
//		
//		List<ChatMsgInfo> msgList = privateOfflineMsgMap.get(msg.getReceiverId());
//		if(msgList == null){
//			msgList = new ArrayList<>();
//			privateOfflineMsgMap.put(msg.getReceiverId(), msgList);
//		}
//		
//		synchronized (msgList) {
//			msgList.add(msg);
//		}
//	}
	
	/**
	 * 离线消息入库
	 */
	public static void saveOfflineMsg(){
		ConcurrentHashMap<Long, ConcurrentHashMap<Integer, List<ChatMsgInfo>>> msgMap = offlineMsgMap;
		offlineMsgMap = new ConcurrentHashMap<>();
		
		Iterator<Entry<Long, ConcurrentHashMap<Integer, List<ChatMsgInfo>>>> iterator = msgMap.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<Long, ConcurrentHashMap<Integer, List<ChatMsgInfo>>> entry = iterator.next();
			ConcurrentHashMap<Integer, List<ChatMsgInfo>> playerMap = entry.getValue();
			
			Iterator<Entry<Integer, List<ChatMsgInfo>>> channelMsgIterator = playerMap.entrySet().iterator();
			while(channelMsgIterator.hasNext()){
				Entry<Integer, List<ChatMsgInfo>> msgsEntry = channelMsgIterator.next();
				List<ChatMsgInfo> msgList = new ArrayList<>();
				if(msgsEntry.getValue() != null){
					msgList.addAll(msgsEntry.getValue());
					
					//获取离线消息条数限制
					if(offlineCountLimit.get(msgsEntry.getKey()) == null){
						DBManager.getChatPrivateOfflineMsgDao().deletePlayerMsg(entry.getKey(), msgsEntry.getKey());
					}else{
						int limitCount = offlineCountLimit.get(msgsEntry.getKey());
						//如果缓存中达到限制条数，删除库中的所有该玩家该频道的记录
						if(msgList.size() >= limitCount){
							DBManager.getChatPrivateOfflineMsgDao().deletePlayerMsg(entry.getKey(), msgsEntry.getKey());
						}else{//如果禀中的记录加上缓存中的记录大于限制条数，删除库中超出限制的条数
							int msgCount = DBManager.getChatPrivateOfflineMsgDao().getMsgCount(entry.getKey(), msgsEntry.getKey());
							if(msgCount + msgList.size() > limitCount){
								DBManager.getChatPrivateOfflineMsgDao().deletePlayerMsg(entry.getKey(), msgsEntry.getKey(), msgCount + msgList.size() - limitCount);
							}
						}
					}
					
					for(ChatMsgInfo msg: msgList){
						if(msg.getOp() == Option.Insert){
							DBManager.getChatPrivateOfflineMsgDao().addMsg(msg);
						}
					}
				}
			}
		}
	}
	
	/**
	 * 添加离线消息
	 * @param msg
	 */
	public static void addOfflineMsg(ChatMsgInfo msg){
		msg.setOp(Option.Insert);
		
		ConcurrentHashMap<Integer, List<ChatMsgInfo>> playerOfflineMsg = offlineMsgMap.get(msg.getReceiverId());
		if(playerOfflineMsg == null){
			playerOfflineMsg = new ConcurrentHashMap<>();
			offlineMsgMap.put(msg.getReceiverId(), playerOfflineMsg);
		}
		List<ChatMsgInfo> msgList = playerOfflineMsg.get(msg.getChannel());
		if(msgList == null){
			msgList = new ArrayList<>();
			playerOfflineMsg.put(msg.getChannel(), msgList);
		}
		
		synchronized (msgList) {
			if(offlineCountLimit.get(msg.getChannel()) == null) return;
			int limitCount = offlineCountLimit.get(msg.getChannel());
			if(msgList.size() >= limitCount){
				msgList.remove(0);
			}
			msgList.add(msg);
		}
	}
	
	/**
	 * 获取收到的离线消息
	 * @param playerId
	 * @param channel
	 * @return
	 */
	public static List<ChatMsgInfo> getOfflineMsgs(long playerId, int channel){
		List<ChatMsgInfo> offlineMsgList = DBManager.getChatPrivateOfflineMsgDao().getPlayerMsg(playerId, channel);
		List<ChatMsgInfo> offlineMsgCache = null;
		ConcurrentHashMap<Integer, List<ChatMsgInfo>> playerOfflineMsg = offlineMsgMap.get(playerId);
		if(playerOfflineMsg != null){
			offlineMsgCache = playerOfflineMsg.remove(channel);
		}
		
		DBManager.getChatPrivateOfflineMsgDao().deletePlayerMsg(playerId, channel);
		
		if(offlineMsgList == null) offlineMsgList = new ArrayList<>();
		if(offlineMsgCache != null){
			offlineMsgList.addAll(offlineMsgCache);
		}
		return offlineMsgList;
	}
	
//	/**
//	 * 获取收到的离线消息
//	 * @param playerId
//	 * @return
//	 */
//	public static List<ChatMsgInfo> getPrivateOfflineMsgs(long playerId){
//		List<ChatMsgInfo> offlineMsgList = DBManager.getChatPrivateOfflineMsgDao().getPlayerMsg(playerId);
//		List<ChatMsgInfo> offlineMsgCache = privateOfflineMsgMap.remove(playerId);
//		
//		DBManager.getChatPrivateOfflineMsgDao().deletePlayerMsg(playerId);
//		
//		if(offlineMsgList == null) offlineMsgList = new ArrayList<>();
//		if(offlineMsgCache != null){
//			offlineMsgList.addAll(offlineMsgCache);
//		}
//		return offlineMsgList;
//	}
	
	/**
	 * 创建接收聊天消息的协议包
	 * @param msgInfo
	 * @return
	 */
	public static ChatReceiveMsg buildChatReceiveProto(ChatMsgInfo msgInfo){
		GamePlayer sender = WorldMgr.getPlayer(msgInfo.getSenderId());
		if(sender == null){
			return null;
		}
		
		ChatReceiveMsg.Builder receiveMsg = ChatReceiveMsg.newBuilder();
		receiveMsg.setChannel(msgInfo.getChannel());
		receiveMsg.setSendTime(msgInfo.getSendTime());
		receiveMsg.setSenderId(msgInfo.getSenderId());
		receiveMsg.setSenderName(sender.getNickName());
		PlayerInfo senderInfo = sender.getBasePlayer().getPlayerInfo();
		receiveMsg.setSkinId(senderInfo.getSkinId());
		receiveMsg.setLevel(senderInfo.getLevel());
		receiveMsg.setVip(senderInfo.getVipLevel());
		receiveMsg.setChatContent(msgInfo.getChatContent());
		receiveMsg.setReceiver(msgInfo.getReceiverId());
		receiveMsg.setParam1(msgInfo.getParam1());
		return receiveMsg.build();
	}
}
