package com.chuangyou.xianni.netty.client;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.common.CommandSet;
import com.chuangyou.xianni.conn.ClientSet;
import com.chuangyou.xianni.exec.CmdTask;
import com.chuangyou.xianni.exec.ThreadManager;
import com.chuangyou.xianni.netty.AttributeKeySet;
import com.chuangyou.xianni.netty.handler.server.ServerInboundHandler;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.user.UserMgr;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

//与客户端连接处理
public class GateWay2CilentInboundHandler extends ServerInboundHandler {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		PBMessage packet = (PBMessage) msg;
		// 数据包按协议号转发
		int code = packet.getCode();
		if (code > 0 && code < 10000) {
			ClientSet.routeClient(packet);
		} else if (code >= 10001 && code < 20000) {
			ClientSet.routeCenter(packet);
		} else if (code >= 20001 && code < 25000) {
			ClientSet.routeSences(packet);
		} else if (code >= 25001 && code < 28000) {
			handle(ctx.channel(), packet);
		} else {
			Log.info("Illegal Packet. packet header : " + packet.headerToStr());
		}

	}

	/**
	 * <pre>
	 * 网关上需要解包协议处理
	 * </pre>
	 * 
	 * @param session
	 * @param packet
	 */
	protected void handle(Channel channle, PBMessage packet) {
		short code = packet.getCode();
		Command cmd = CommandSet.getCommand(code);
		if (cmd != null) {
			try {
				ThreadManager.cmdExecutor.enDefaultQueue(new CmdTask(cmd, channle, packet, ThreadManager.cmdExecutor.getDefaultQueue()));
			} catch (Exception e) {
				Log.error("code = " + code + " has exception:", e);
			}
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		try {
			// Log.error("客户端异常", cause);
			ctx.close();
			// System.err.println("客户端异常" + cause);
		} catch (Exception e) {
			Log.error("Con2CilentInboundHandler", e);
		}
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("-------------客户端断开连接-------------");
		// 从在线玩家列表中,将该user移除
		if (ctx.channel().attr(AttributeKeySet.PLAYER_ID) != null && ctx.channel().attr(AttributeKeySet.PLAYER_ID).get() != null) {
			long userId = ctx.channel().attr(AttributeKeySet.PLAYER_ID).get();
			if (userId > 0) {
				UserMgr.removeOnline(userId, ctx.channel());
			}
		}
		// 从临时玩家channel列表中，将该channel移除
		if (ctx.channel().attr(AttributeKeySet.TEMP_USER_ID) != null && ctx.channel().attr(AttributeKeySet.TEMP_USER_ID).get() != null) {
			long temp_userId = ctx.channel().attr(AttributeKeySet.TEMP_USER_ID).get();
			if (temp_userId != 0) {
				UserMgr.removeTempSession(temp_userId, ctx.channel());
			}
		}

	}
}
