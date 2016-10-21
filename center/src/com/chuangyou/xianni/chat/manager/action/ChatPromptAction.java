package com.chuangyou.xianni.chat.manager.action;

import java.util.List;

import com.chuangyou.common.protobuf.pb.chat.ChatReceiveProto.ChatReceiveMsg;
import com.chuangyou.xianni.chat.manager.ChatManager;
import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.entity.chat.ChatMsgInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.word.WorldMgr;

public class ChatPromptAction extends ChatBaseAction {

	@Override
	public boolean sendChatMsg(GamePlayer sender, ChatMsgInfo sendMsg) {
		GamePlayer receiver = WorldMgr.getPlayer(sendMsg.getReceiverId());
		if(receiver == null) return false;
		if(receiver.getPlayerState() == PlayerState.OFFLINE){
			sendMsg.setSendTime(System.currentTimeMillis());
			sendMsg.setSenderId(sender.getPlayerId());
			ChatManager.addOfflineMsg(sendMsg);
			return true;
		}
		ChatReceiveMsg msg = this.buildReceiveProto(sender, sendMsg);
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_CHAT_RECEIVE, msg);
		receiver.sendPbMessage(pkg);
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
		return 0;
	}

}
