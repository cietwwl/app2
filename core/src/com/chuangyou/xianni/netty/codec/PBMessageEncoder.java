package com.chuangyou.xianni.netty.codec;

import java.io.IOException;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.proto.PBMessage;
import com.google.protobuf.Message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 数据包编码器
 */
public class PBMessageEncoder extends MessageToByteEncoder<PBMessage> {

	public PBMessageEncoder() {

	}

	@Override
	protected void encode(ChannelHandlerContext ctx, PBMessage msg, ByteBuf out) throws Exception {
		if (msg.getMessage() != null) {
			encodeMessage(msg, out);
		} else {
			encodeByte(msg, out);
		}
	}

	/**
	 * 使用PB对象
	 */
	private void encodeMessage(PBMessage pbMessage, ByteBuf out) throws IOException {
		Message msg = pbMessage.getMessage();
		int size = PBMessage.HDR_SIZE;
		if (msg != null) {
			size += msg.getSerializedSize(); // 数据长度
		}

		if (size > Short.MAX_VALUE) {
			Log.error("Packet error length");
			return;
		}
		pbMessage.writeHeader(size, out); // 协议头部
		if (msg != null) {
			out.writeBytes(msg.toByteArray());
		}
	}

	private void encodeByte(PBMessage pbMessage, ByteBuf out) throws IOException {
		byte[] bytes = pbMessage.getBytes();
		int size = PBMessage.HDR_SIZE;
		if (bytes != null) {
			size += bytes.length;
		}
		if (size > Short.MAX_VALUE) {
			return;
		}
		pbMessage.writeHeader(size, out); // 协议头部
		if (bytes != null) {
			out.writeBytes(bytes);
		}
	}

}