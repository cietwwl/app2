package com.chuangyou.xianni.chat.manager.action;

import java.util.List;

import com.chuangyou.common.protobuf.pb.chat.ChatReceiveProto.ChatReceiveMsg;
import com.chuangyou.common.protobuf.pb.chat.ChatSendProto.ChatSendMsg;
import com.chuangyou.xianni.constant.ChatConstant;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class ChatSceneAction extends ChatBaseAction {
	
	@Override
	public boolean sendChatMsg(GamePlayer sender, ChatSendMsg sendMsg) {
		// TODO Auto-generated method stub
		if(this.checkCd(sender, sendMsg, true) == false){
			return false;
		}
		ChatReceiveMsg msg = this.buildReceiveProto(sender, sendMsg);
		PBMessage p = MessageUtil.buildMessage(Protocol.S_CHAT_INNER_SEND, msg);
		sender.sendPbMessage(p);
		return true;
	}

	@Override
	public List<Long> getReceivers(GamePlayer sender, ChatSendMsg sendMsg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int getCoolingTime() {
		// TODO Auto-generated method stub
		return ChatConstant.CoolingTime.SCENE;
	}
}
