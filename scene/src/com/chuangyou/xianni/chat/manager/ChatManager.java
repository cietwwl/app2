package com.chuangyou.xianni.chat.manager;

import com.chuangyou.common.protobuf.pb.chat.ChatReceiveProto.ChatReceiveMsg;
import com.chuangyou.common.protobuf.pb.chat.ChatSendProto.ChatSendMsg;
import com.chuangyou.xianni.constant.ChatConstant;
import com.chuangyou.xianni.netty.GatewayLinkedSet;
import com.chuangyou.xianni.proto.BroadcastUtil;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.warfield.field.Field;

public class ChatManager {

	public static void sendSceneChatMsg(Field field, ChatReceiveMsg chatMsg){
		if(field == null){
			return;
		}
		ChatReceiveMsg.Builder sendMsg = chatMsg.toBuilder();
		if(chatMsg.getParam1() > 0){
			sendMsg.setSenderId(0);
			sendMsg.setParam1(0);
		}
		BroadcastUtil.sendBroadcastPacket(field.getLivings(), Protocol.U_CHAT_RECEIVE, sendMsg.build());
	}
	
	public static void sendSceneNotice(int channel, Field field, String content){
		ChatReceiveMsg noticeMsg = buildChatNoticeMsg(channel, content);
		sendSceneChatMsg(field, noticeMsg);
	}
	
	public static void sendWorldNotice(int channel, String content){
		ChatSendMsg.Builder req = ChatSendMsg.newBuilder();
		req.setChannel(channel);
		req.setChatContent(content);
		PBMessage pkg = MessageUtil.buildMessage(Protocol.C_CHAT_NOTICE_SEND, req);
		GatewayLinkedSet.send2Server(pkg);
	}
	
	public static ChatReceiveMsg buildChatNoticeMsg(int channel, String content){
		ChatReceiveMsg.Builder noticeMsg = ChatReceiveMsg.newBuilder();
		noticeMsg.setChannel(channel);
		noticeMsg.setSendTime(System.currentTimeMillis());
		noticeMsg.setChatContent(content);
		return noticeMsg.build();
	}
	
	public static void sendNotice(int channel, int notifyRange, Field field, String content){
		if(notifyRange == ChatConstant.NoticeReceiveArea.WORLD){
			sendWorldNotice(channel, content);
		}else if(notifyRange == ChatConstant.NoticeReceiveArea.SCENE){
			sendSceneNotice(channel, field, content);
		}
	}
}
