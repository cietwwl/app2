package com.chuangyou.xianni.proto;

import com.chuangyou.common.util.Log;
import com.google.protobuf.AbstractMessage.Builder;
import com.google.protobuf.Message;

/**
 * 辅助类
 * 
 */
public class MessageUtil {

	/**
	 * 消息创建
	 * 
	 * @param code
	 * @param messageBuilder
	 * @return
	 */
	public static PBMessage buildMessage(short code, Builder<?> messageBuilder) {
		// Log.debug("发送消息code："+code+"\ncontent:\n"+messageBuilder);
		// System.err.println("发送消息code："+code+"\ncontent:\n"+messageBuilder);
		return buildMessage(code, messageBuilder.build());
	}

	/**
	 * 消息创建
	 * 
	 * @param code
	 * @param messageBuilder
	 * @return
	 */
	public static PBMessage buildMessage(short code, Message message) {
	//	Log.error("发送消息：code：" + code + "\ncontent:\n" + message);
		// System.err.println("发送消息code："+code+"\ncontent:\n"+message);
		PBMessage response = new PBMessage(code, -1);
		response.setMessage(message);
		return response;
	}

	/**
	 * 消息创建
	 * 
	 * @param code
	 * @param messageBuilder
	 * @return
	 */
	public static PBMessage buildMessage(short code, long playerId, Message message) {
		PBMessage response = new PBMessage(code, playerId);
		response.setMessage(message);
		return response;
	}

	/**
	 * 消息创建
	 * 
	 * @param code
	 * @param client
	 * @param messageBuilder
	 * @return
	 */
	public static PBMessage buildMessage(short code, long playerId, Builder<?> messageBuilder) {
		PBMessage response = new PBMessage(code, playerId);
		response.setMessage(messageBuilder.build());
		return response;
	}

	/**
	 * 消息创建
	 * 
	 * @param code
	 * @param client
	 * @return
	 */
	public static PBMessage buildMessage(short code, long playerId) {
		PBMessage message = new PBMessage(code, playerId);
		return message;
	}

	/**
	 * 消息创建
	 * 
	 * @param code
	 * @return
	 */
	public static PBMessage buildMessage(short code) {
		PBMessage message = new PBMessage(code);
		return message;
	}
}
