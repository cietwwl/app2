package com.chuangyou.xianni.chat.manager;

import java.util.HashMap;
import java.util.Map;

import com.chuangyou.xianni.chat.manager.action.ChatBaseAction;
import com.chuangyou.xianni.chat.manager.action.ChatFactionAction;
import com.chuangyou.xianni.chat.manager.action.ChatSceneAction;
import com.chuangyou.xianni.chat.manager.action.ChatSearchTeamAction;
import com.chuangyou.xianni.chat.manager.action.ChatSystemAction;
import com.chuangyou.xianni.chat.manager.action.ChatTeamAction;
import com.chuangyou.xianni.chat.manager.action.ChatWorldAction;
import com.chuangyou.xianni.constant.ChatConstant;

public class ChatSenderFactory {

	private Map<Short, ChatBaseAction> _chatSenderMap = new HashMap<Short, ChatBaseAction>();
	
	private static ChatSenderFactory ins = new ChatSenderFactory();
	
	public ChatSenderFactory() {
		// TODO Auto-generated constructor stub
		this.init();
	}
	
	public static ChatSenderFactory getIns(){
		return ins;
	}
	
	private void init(){
		_chatSenderMap.put(ChatConstant.Channel.SYSTEM, new ChatSystemAction());
		_chatSenderMap.put(ChatConstant.Channel.WORLD, new ChatWorldAction());
		_chatSenderMap.put(ChatConstant.Channel.SCENE, new ChatSceneAction());
		_chatSenderMap.put(ChatConstant.Channel.FACTION, new ChatFactionAction());
		_chatSenderMap.put(ChatConstant.Channel.TEAM, new ChatTeamAction());
		_chatSenderMap.put(ChatConstant.Channel.SEARCH_TEAM, new ChatSearchTeamAction());
	}
	
	public ChatBaseAction getAction(short channel){
		return _chatSenderMap.get(channel);
	}
}
