package com.chuangyou.xianni.chat.cmd;

import com.chuangyou.common.protobuf.pb.chat.ChatHistoryReqProto.ChatHistoryReqMsg;
import com.chuangyou.common.protobuf.pb.chat.ChatHistoryRespProto.ChatHistoryRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.chat.manager.ChatManager;
import com.chuangyou.xianni.constant.ChatConstant;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class ChatHistoryReqCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub

		ChatHistoryReqMsg req = ChatHistoryReqMsg.parseFrom(packet.getBytes());
		
		if(req.getChannel() == ChatConstant.Channel.WORLD){
			ChatHistoryRespMsg.Builder historyMsg = ChatHistoryRespMsg.newBuilder();
			historyMsg.setChannel(req.getChannel());
			
			historyMsg.addAllChatMsg(ChatManager.getWorldHistory());
			PBMessage p = MessageUtil.buildMessage(Protocol.U_CHAT_HISTORY_RESP, historyMsg);
			player.sendPbMessage(p);
		}
	}

}
