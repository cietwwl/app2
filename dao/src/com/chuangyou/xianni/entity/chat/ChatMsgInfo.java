package com.chuangyou.xianni.entity.chat;

import com.chuangyou.xianni.entity.DataObject;

public class ChatMsgInfo extends DataObject {

	/** 频道ID */
	private int channel;
	
	/** 发送时间 */
	private long sendTime;
	
	/** 发送者ID */
	private long senderId;
	
	/** 接收者ID(仅一对一聊天时生效) */
	private long receiverId;
	
	/** 聊天内容 */
	private String chatContent;
	
	/** 备用参数(在系统提示中用作提示类型) */
	private int param1;

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

	public long getSenderId() {
		return senderId;
	}

	public void setSenderId(long senderId) {
		this.senderId = senderId;
	}

	public long getReceiverId() {
		return receiverId;
	}

	public void setReceiverId(long receiverId) {
		this.receiverId = receiverId;
	}

	public String getChatContent() {
		return chatContent;
	}

	public void setChatContent(String chatContent) {
		this.chatContent = chatContent;
	}

	public int getParam1() {
		return param1;
	}

	public void setParam1(int param1) {
		this.param1 = param1;
	}
}
