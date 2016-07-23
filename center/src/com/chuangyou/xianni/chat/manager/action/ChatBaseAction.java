package com.chuangyou.xianni.chat.manager.action;

import java.util.List;

import com.chuangyou.common.protobuf.pb.chat.ChatReceiveProto.ChatReceiveMsg;
import com.chuangyou.common.protobuf.pb.chat.ChatSendProto.ChatSendMsg;
import com.chuangyou.xianni.chat.manager.ChatManager;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.player.PlayerInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.BroadcastUtil;
import com.chuangyou.xianni.protocol.Protocol;

public abstract class ChatBaseAction {

	/**
	 * 发送聊天消息
	 * @param sender 发送者
	 * @param sendMsg 发送消息
	 * @return 是否成功
	 */
	public boolean sendChatMsg(GamePlayer sender, ChatSendMsg sendMsg){
		if(checkCd(sender, sendMsg, true) == false){
			return false;
		}
		ChatReceiveMsg msg = this.buildReceiveProto(sender, sendMsg);
		List<Long> receivers = getReceivers(sender, sendMsg);
		if(receivers == null || receivers.size() <= 0) return false;
		BroadcastUtil.sendBroadcastPacket(receivers, Protocol.U_CHAT_RECEIVE, msg);
		return true;
	}
	
	/**
	 * 判断是否过了发言时间间隔
	 * @param sender 发送者
	 * @param sendMsg 发送消息
	 * @return 是否可以发送
	 */
	public boolean checkCd(GamePlayer sender, ChatSendMsg sendMsg, boolean errorTip){
		boolean result = true;
		long lastTime = ChatManager.getLastTime(sendMsg.getChannel(), sender.getPlayerId());
		if(lastTime > 0){
			int cdTime = this.getCoolingTime();
			if(System.currentTimeMillis() - lastTime < cdTime){
				if(errorTip == true){
					ErrorMsgUtil.sendErrorMsg(sender, ErrorCode.CHAT_COOLING, (short)0, "发言过于频繁");
				}
				result = false;
			}
		}
		if(result == true){
			ChatManager.setLastTime(sendMsg.getChannel(), sender.getPlayerId(), System.currentTimeMillis());
		}
		return result;
	}
	
	/**
	 * 创建聊天消息接收包
	 * @param sender 发送者
	 * @param sendMsg 发送消息
	 * @return 接收协议包
	 */
	public ChatReceiveMsg buildReceiveProto(GamePlayer sender, ChatSendMsg sendMsg){
		ChatReceiveMsg.Builder msg = ChatReceiveMsg.newBuilder();
		msg.setChannel(sendMsg.getChannel());
		msg.setSendTime(System.currentTimeMillis());
		msg.setSenderId(sender.getPlayerId());
		msg.setSenderName(sender.getNickName());
		PlayerInfo senderInfo = sender.getBasePlayer().getPlayerInfo();
		msg.setSkinId(senderInfo.getSkinId());
		msg.setLevel(senderInfo.getLevel());
		msg.setVip(senderInfo.getVipLevel());
		msg.setChatContent(sendMsg.getChatContent());
		return msg.build();
	}
	
	/**
	 * 获取目标玩家ID
	 * @param sender 发送者
	 * @param sendMsg 发送消息
	 * @return 接收者ID集合
	 */
	protected abstract List<Long> getReceivers(GamePlayer sender, ChatSendMsg sendMsg);
	
	/**
	 * 获取CD时间
	 * @return 冷却时间
	 */
	protected abstract int getCoolingTime();
}
