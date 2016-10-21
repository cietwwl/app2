package com.chuangyou.xianni.chat.manager.action;

import java.util.ArrayList;
import java.util.List;

import com.chuangyou.common.protobuf.pb.chat.ChatReceiveProto.ChatReceiveMsg;
import com.chuangyou.xianni.chat.manager.ChatManager;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.constant.ChatConstant;
import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.entity.chat.ChatMsgInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.BroadcastUtil;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.word.WorldMgr;

public class ChatPrivateAction extends ChatBaseAction {
	
	@Override
	public boolean sendChatMsg(GamePlayer sender, ChatMsgInfo sendMsg) {
		// TODO Auto-generated method stub
		if(checkCd(sender, sendMsg, true) == false){
			return false;
		}
		GamePlayer receiver = WorldMgr.getPlayer(sendMsg.getReceiverId());
		if(receiver == null){
			ErrorMsgUtil.sendErrorMsg(sender, ErrorCode.CHAT_PRIVATE_TARGET_NOT_EXIST, (short)0, "目标不存在");
			return false;
		}
		if(receiver.getPlayerState() == PlayerState.OFFLINE){
			ChatMsgInfo chatMsgInfo = new ChatMsgInfo();
			chatMsgInfo.setChannel(sendMsg.getChannel());
			chatMsgInfo.setSendTime(System.currentTimeMillis());
			chatMsgInfo.setSenderId(sender.getPlayerId());
			chatMsgInfo.setReceiverId(sendMsg.getReceiverId());
			chatMsgInfo.setChatContent(sendMsg.getChatContent());
			ChatManager.addOfflineMsg(chatMsgInfo);
			
			ChatReceiveMsg msg = this.buildReceiveProto(sender, sendMsg);
			PBMessage p = MessageUtil.buildMessage(Protocol.U_CHAT_RECEIVE, msg);
			sender.sendPbMessage(p);
			return true;
		}
		ChatReceiveMsg msg = this.buildReceiveProto(sender, sendMsg);
		List<Long> receivers = getReceivers(sender, sendMsg);
		if(receivers == null || receivers.size() <= 0) return false;
		BroadcastUtil.sendBroadcastPacket(receivers, Protocol.U_CHAT_RECEIVE, msg);
		return true;
	}

	@Override
	protected List<Long> getReceivers(GamePlayer sender, ChatMsgInfo sendMsg) {
		// TODO Auto-generated method stub
		List<Long> receivers = new ArrayList<>();
		receivers.add(sender.getPlayerId());
		receivers.add(sendMsg.getReceiverId());
		return receivers;
	}

	@Override
	protected int getCoolingTime() {
		// TODO Auto-generated method stub
		return ChatConstant.CoolingTime.PRIVATE;
	}

}
