package com.chuangyou.xianni.chat.cmd;

import java.util.List;

import com.chuangyou.common.protobuf.pb.chat.ChatHistoryReqProto.ChatHistoryReqMsg;
import com.chuangyou.common.protobuf.pb.chat.ChatHistoryRespProto.ChatHistoryRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.chat.manager.ChatManager;
import com.chuangyou.xianni.constant.ChatConstant;
import com.chuangyou.xianni.entity.chat.ChatMsgInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_CHAT_HISTORY_REQ, desc = "请求聊天记录")
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
		}else if(req.getChannel() == ChatConstant.Channel.PRIVATE){
			ChatHistoryRespMsg.Builder offlineMsg = ChatHistoryRespMsg.newBuilder();
			offlineMsg.setChannel(req.getChannel());
			
			List<ChatMsgInfo> msgList = ChatManager.getPrivateOfflineMsgs(player.getPlayerId());
			for(ChatMsgInfo msgInfo: msgList){
				offlineMsg.addChatMsg(ChatManager.buildChatReceiveProto(msgInfo));
			}
			PBMessage p = MessageUtil.buildMessage(Protocol.U_CHAT_HISTORY_RESP, offlineMsg);
			player.sendPbMessage(p);
		}
	}

}
