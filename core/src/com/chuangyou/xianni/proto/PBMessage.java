package com.chuangyou.xianni.proto;

import java.io.Serializable;

import com.google.protobuf.Message;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;

/**
 * 服务器和客户端,服务器和服务器直接数据传输的对象
 **/
public class PBMessage implements Serializable {

	private static final long	serialVersionUID	= 1L;
	public static final short	HDR_SIZE			= 14;
	public static final short	HEADER				= 0x71ab;

	private short				header				= HEADER;	// 包头
	private short				len;							// 数据包长度
	private short				code;							// 协议号
	private long				playerId;						// 玩家ID
	private byte[]				bytes;							// 数据体
	private Message				message;						// Proto

	private PBMessage() {

	}

	public PBMessage(short code) {
		this(code, -1);
	}

	public PBMessage(short code, long playerId) {
		this.code = code;
		this.playerId = playerId;
	}

	public short getHeader() {
		return header;
	}

	public short getLen() {
		return len;
	}

	public void setLen(short len) {
		this.len = len;
	}

	public short getCode() {
		return code;
	}

	public void setCode(short code) {
		this.code = code;
	}

	public long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}

	public byte[] getBytes() {
		return bytes;
	}

	public void setBytes(byte[] bytes) {
		this.bytes = bytes;
	}

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public void readHeader(ByteBuf in) {
		in.readShortLE();
		len = in.readShortLE();
		code = in.readShortLE();
		playerId = in.readLongLE();
	}

	public void writeHeader(int len, ByteBuf out) {
		out.writeShortLE(PBMessage.HEADER);
		out.writeShortLE((short) len);
		out.writeShortLE(code);
		out.writeLongLE(playerId);
//		System.out.println("--------code: "+code+" len: "+len);
	}

	/**
	 * 只设置数据体
	 * 
	 * @param bodyBytes
	 */
	public void writeBodyBytes(byte[] bodyBytes, int len) {
		ByteBuf out = Unpooled.buffer(len + PBMessage.HEADER);
		writeHeader(len + HDR_SIZE, out);
		out.writeBytes(bodyBytes, 0, len);
		bytes = out.array();
	}

	/**
	 * 转换为字节数组
	 * 
	 * @return
	 */
	public byte[] toByteArray() {
		return bytes;
	}

	/**
	 * 创建空消息(避免外部实例化)
	 * 
	 * @return
	 */
	public static PBMessage buildPBMessage() {
		return new PBMessage();
	}

	public String headerToStr() {
		StringBuilder sb = new StringBuilder();
		sb.append("playerId : ").append(playerId);
		sb.append(", code : ").append(Integer.toHexString(code));
		sb.append(", len : ").append(len);
		return sb.toString();
	}

	public String detailToStr() {
		if (bytes == null) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(b + ", ");
		}
		return headerToStr() + ", content : [" + sb.toString() + "]";
	}
}
