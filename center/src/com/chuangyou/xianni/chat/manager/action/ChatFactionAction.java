package com.chuangyou.xianni.chat.manager.action;

import java.util.List;

import com.chuangyou.xianni.constant.ChatConstant;
import com.chuangyou.xianni.entity.chat.ChatMsgInfo;
import com.chuangyou.xianni.player.GamePlayer;

public class ChatFactionAction extends ChatBaseAction {

	@Override
	public List<Long> getReceivers(GamePlayer sender, ChatMsgInfo sendMsg) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected int getCoolingTime() {
		// TODO Auto-generated method stub
		return ChatConstant.CoolingTime.FACTION;
	}
}
