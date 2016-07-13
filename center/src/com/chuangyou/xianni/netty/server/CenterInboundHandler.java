package com.chuangyou.xianni.netty.server;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.common.CommandSet;
import com.chuangyou.xianni.exec.CmdTask;
import com.chuangyou.xianni.exec.ThreadManager;
import com.chuangyou.xianni.netty.handler.server.ServerInboundHandler;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.word.WorldMgr;
import io.netty.channel.ChannelHandlerContext;

public class CenterInboundHandler extends ServerInboundHandler {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		PBMessage packet = (PBMessage) msg;
		short code = packet.getCode();
		Command cmd = CommandSet.getCommand(code);
		if (cmd == null) {
			Log.error("not found cmd , code : " + code + " , playerId : " + packet.getPlayerId());
			return;
		}
		long playerId = packet.getPlayerId();
		if (playerId > 0) {
			GamePlayer player = null;
			try {
				if (!WorldMgr.isExist(playerId)) {
					Log.warn("code " + packet.getCode() + " not found player " + playerId + ",can not continue execute.");
					return;
				}
				player = WorldMgr.getPlayer(playerId);
				if (player != null) {
					player.enqueue(new CmdTask(cmd, ctx.channel(), packet, player.getCmdTaskQueue()));
				}
			} catch (Exception e) {
				if (player != null) {
					Log.debug("packet has exception. player is null. " + packet.headerToStr(), e);
				} else {
					Log.debug("packet has exception. " + packet.headerToStr(), e);
				}
			}
		} else {
			ThreadManager.cmdExecutor.enDefaultQueue(
					new CmdTask(cmd, ctx.channel(), packet, ThreadManager.cmdExecutor.getDefaultQueue()));
		}

	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		try {
			
			ctx.close();
			Log.error("服务器异常", cause);
			System.err.println("服务器出现异常" + cause);
		} catch (Exception e) {

		}
	}
}
