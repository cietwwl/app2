package com.chuangyou.xianni.netty.server;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.common.CommandSet;
import com.chuangyou.xianni.conn.ClientSet;
import com.chuangyou.xianni.conn.ClientTryer;
import com.chuangyou.xianni.exec.CmdTask;
import com.chuangyou.xianni.exec.ThreadManager;
import com.chuangyou.xianni.netty.LinkedClient;
import com.chuangyou.xianni.netty.handler.server.ServerInboundHandler;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.socket.Command;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

public class GateWay2CoreInboundHandler extends ServerInboundHandler {

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
		//System.err.println("==========code=========" + code);
		if (cmd != null) {
			try {
				ThreadManager.cmdExecutor
						.enDefaultQueue(new CmdTask(cmd, channle, packet, ThreadManager.cmdExecutor.getDefaultQueue()));
			} catch (Exception e) {
				Log.error("code = " + code + " has exception:", e);
			}
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		try {
			System.err.println("服务器出现异常" + cause);
			ctx.close();
			Log.error("服务器异常", cause);
		} catch (Exception e) {
			Log.error("Con2CilentInboundHandler", e);
		}
	}

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		System.out.println("-------------服务器断开连接-------------");
		LinkedClient client = ClientSet.getLinkedClient(ctx.channel());
		if (client != null && client.isTry()) {
			ClientTryer.getInstance().ctry(client, 10, -1);
			client.setTry(false);
		}
	}
}
