package com.chuangyou.xianni.netty.codec;

import java.util.List;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.proto.PBMessage;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

/**
 * 数据包解码器
 */
public class PBMessageDecoder extends ByteToMessageDecoder {

	public PBMessageDecoder() {

	}

	private enum State {
		READ_HEADER, READ_LENGTH, READ_CODE, READ_PLAYER_ID, READ_DATA
	}

	private State	currentState	= State.READ_HEADER;

	private short	headerFlag;
	private int		lenght;
	private short	code;
	private long	playerId;

	@Override
	protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
		if (in.readableBytes() < PBMessage.HDR_SIZE) {
			return;
		}
		PBMessage message = PBMessage.buildPBMessage();
		int readIndex = in.readerIndex();
		switch (currentState) {
			case READ_HEADER:
				headerFlag = in.readShortLE();
				if (PBMessage.HEADER != headerFlag) {
					Log.debug("Illegal client request,can not match header flag. drop a packet,close connection.");
					return;
				} else {
					currentState = State.READ_LENGTH;
				}
			case READ_LENGTH:
				// 长度
				lenght = in.readShortLE();
				if (lenght <= 0 || lenght >= Short.MAX_VALUE) {
					// 非法的数据长度
					Log.debug("Message Length Invalid Length = " + lenght + ", drop this Message.close connection");
					System.err.println(
							"Message Length Invalid Length = " + lenght + ", drop this Message.close connection");
					return;
				}
				message.setLen((short) lenght);
				currentState = State.READ_CODE;
				// 剩余长度，是否足够
				if (lenght - 4 > in.readableBytes()) {
					// 数据还不够读取,等待下一次读取
					Log.warn("Data not integrity. there is a lack of " + (lenght - in.readableBytes()) + " bytes.");
					// System.err.println(
					// "Data not integrity. there is a lack of " + (lenght -
					// in.readableBytes()) + " bytes.");
					in.readerIndex(readIndex);
					currentState = State.READ_HEADER;
					return;
				}
			case READ_CODE:
				// 获取协议号
				code = in.readShortLE();
				message.setCode(code);
				currentState = State.READ_PLAYER_ID;
			case READ_PLAYER_ID:
				// 获取用户ID
				playerId = in.readLongLE();
				message.setPlayerId(playerId);
				currentState = State.READ_PLAYER_ID;
			case READ_DATA:
				int pblen = message.getLen() - PBMessage.HDR_SIZE;
				byte[] bytes = new byte[pblen];
				in.readBytes(bytes, 0, pblen);
				message.setBytes(bytes);
				currentState = State.READ_HEADER;
				out.add(message);
				break;
		}
	}
}
