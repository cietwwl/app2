package com.chuangyou.xianni.sql.dao;

import java.util.List;

import com.chuangyou.xianni.entity.chat.ChatMsgInfo;

public interface ChatPrivateOfflineMsgDao {

	public List<ChatMsgInfo> getPlayerMsg(long playerId);
	
	public List<ChatMsgInfo> getPlayerMsg(long playerId, int channel);
	
	public Integer getMsgCount(long playerId, int channel);
	
	public boolean addMsg(ChatMsgInfo info);
	
	public boolean deletePlayerMsg(long playerId);
	
	public boolean deletePlayerMsg(long playerId, int channel);
	
	public boolean deletePlayerMsg(long playerId, int channel, int count);
}
