package com.chuangyou.xianni.netty.server;

import io.netty.channel.ChannelHandlerContext;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.common.CommandSet;
import com.chuangyou.xianni.exec.CmdTask;
import com.chuangyou.xianni.exec.ThreadManager;
import com.chuangyou.xianni.netty.handler.server.ServerInboundHandler;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.world.ArmyProxy;
import com.chuangyou.xianni.world.WorldMgr;

public class SceneInboundHandler extends ServerInboundHandler {

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		// TODO Auto-generated method stub
		PBMessage packet = (PBMessage) msg;
		short code = packet.getCode();
		//System.out.println("SceneInboundHandler" + code + "  time:" + System.currentTimeMillis());
		Command cmd = CommandSet.getCommand(code);
		if (cmd == null) {
			Log.error("not found cmd , code : " + code + " , userId : " + packet.getPlayerId());
			return;
		}
		long playerId = packet.getPlayerId();
		if (playerId > 0) {
			ArmyProxy army = null;
			try {
				if (!WorldMgr.isExist(playerId)) {
					System.err.println("code " + packet.getCode() + " not found player " + playerId
							+ ",can not continue execute.");
					Log.warn("code " + packet.getCode() + " not found player " + playerId
							+ ",can not continue execute.");
					return;
				}
				army = WorldMgr.getArmy(playerId);
				if (army != null) {
					army.enqueue(new CmdTask(cmd, ctx.channel(), packet, army.getCmdTaskQueue()));
				}
			} catch (Exception e) {
				if (army != null) {
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
