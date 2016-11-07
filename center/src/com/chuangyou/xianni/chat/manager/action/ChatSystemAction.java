package com.chuangyou.xianni.chat.manager.action;

import java.util.List;

import com.chuangyou.common.protobuf.pb.chat.ChatReceiveProto.ChatReceiveMsg;
import com.chuangyou.xianni.constant.ChatConstant;
import com.chuangyou.xianni.entity.chat.ChatMsgInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.BroadcastUtil;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.word.WorldMgr;

public class ChatSystemAction extends ChatBaseAction {
	
	@Override
	public boolean sendChatMsg(GamePlayer sender, ChatMsgInfo sendMsg) {
		// TODO Auto-generated method stub
		ChatReceiveMsg.Builder msg = ChatReceiveMsg.newBuilder();
		msg.setChannel(sendMsg.getChannel());
		msg.setSendTime(System.currentTimeMillis());
		msg.setChatContent(sendMsg.getChatContent());
		if(sendMsg.getSenderId() > 0 && sendMsg.getParam1() > 0){
			msg.setSenderId(sendMsg.getSenderId());
			msg.setParam1(sendMsg.getParam1());
			
			sender = WorldMgr.getPlayerFromCache(sendMsg.getSenderId());
			if(sender != null){
				PBMessage p = MessageUtil.buildMessage(Protocol.S_CHAT_INNER_SEND, msg);
				sender.sendPbMessage(p);
			}
		}else{
			BroadcastUtil.sendBroadcasePacketToAll(Protocol.U_CHAT_RECEIVE, msg.build());
		}
		return true;
	}
	
	@Override
	protected List<Long> getReceivers(GamePlayer sender, ChatMsgInfo sendMsg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int getCoolingTime() {
		// TODO Auto-generated method stub
		return ChatConstant.CoolingTime.SYSTEM;
	}

}
