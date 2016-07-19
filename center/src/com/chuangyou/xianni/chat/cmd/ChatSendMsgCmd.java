package com.chuangyou.xianni.chat.cmd;

import com.chuangyou.common.protobuf.pb.chat.ChatSendProto.ChatSendMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.chat.manager.ChatManager;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.constant.ChatConstant;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_CHAT_SEND, desc = "请求发送聊天消息")
public class ChatSendMsgCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		ChatSendMsg req = ChatSendMsg.parseFrom(packet.getBytes());
		
		if(req.getChannel() == ChatConstant.Channel.SYSTEM){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.CHAT_CHANNEL_NOT_SPEAK, packet.getCode(), "该频道不可发言");
			return;
		}
		ChatManager.sendChatMsg(player, req);
	}

}
