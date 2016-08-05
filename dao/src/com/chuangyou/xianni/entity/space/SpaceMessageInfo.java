package com.chuangyou.xianni.entity.space;

import java.util.Date;

import com.chuangyou.common.protobuf.pb.space.MessageInfoProto.MessageInfoMsg;
import com.chuangyou.xianni.entity.DataObject;


/**
 * 空间留言记录
 * @author laofan
 *
 */
public class SpaceMessageInfo extends DataObject {
	
	/**
	 * 未收藏
	 */
	public static final int NO_COLLECTION = 0;
	/**
	 * 已收藏 
	 */
	public static final int COLLECTIONED   = 1;
	
	//========================================================
	private int id;
	private long sendPlayerId;
	private long receivePlayerId;
	private Date createDate;
	private String message;
	private int isCollection;
	
	
	public MessageInfoMsg.Builder getMessageMsg(){
		MessageInfoMsg.Builder msg = MessageInfoMsg.newBuilder();
		msg.setId(id);
		msg.setCreateDate(createDate.getTime());
		msg.setSendPlayerId(sendPlayerId);
		msg.setIsCollection(isCollection);
		msg.setMessage(message);
		return msg;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getSendPlayerId() {
		return sendPlayerId;
	}
	public void setSendPlayerId(long sendPlayerId) {
		this.sendPlayerId = sendPlayerId;
	}
	public long getReceivePlayerId() {
		return receivePlayerId;
	}
	public void setReceivePlayerId(long receivePlayerId) {
		this.receivePlayerId = receivePlayerId;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getIsCollection() {
		return isCollection;
	}
	public void setIsCollection(int isCollection) {
		this.isCollection = isCollection;
	}
	
	
}
