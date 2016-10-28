package com.chuangyou.xianni.chat.cmd;

import com.chuangyou.common.protobuf.pb.chat.ChatSendProto.ChatSendMsg;
import com.chuangyou.xianni.chat.manager.ChatManager;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.socket.Command;

import io.netty.channel.Channel;

@Cmd(code = Protocol.C_CHAT_NOTICE_SEND, desc = "scene服请求发公告")
public class ChatNoticeSendCmd implements Command {

	@Override
	public void execute(Channel channel, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		ChatSendMsg req = ChatSendMsg.parseFrom(packet.getBytes());
		
		ChatManager.sendSystemChatMsg(req.getChannel(), req.getChatContent());
	}

}
