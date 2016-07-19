package com.chuangyou.xianni.entity.chat;

import com.chuangyou.xianni.entity.DataObject;

public class ChatMsgInfo extends DataObject {

	/** 频道ID */
	private int channel;
	
	/** 发送时间 */
	private long sendTime;
	
	/** 发送者ID */
	private int senderId;
	
	/** 接收者ID(仅一对一聊天时生效) */
	private int receiverId;
	
	/** 聊天内容 */
	private String chatContent;

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public long getSendTime() {
		return sendTime;
	}

	public void setSendTime(long sendTime) {
		this.sendTime = sendTime;
	}

	public int getSenderId() {
		return senderId;
	}

	public void setSenderId(int senderId) {
		this.senderId = senderId;
	}

	public int getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(int receiverId) {
		this.receiverId = receiverId;
	}

	public String getChatContent() {
		return chatContent;
	}

	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}
}
