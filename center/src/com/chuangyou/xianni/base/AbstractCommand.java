package com.chuangyou.xianni.base;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.socket.Command;
import com.chuangyou.xianni.word.WorldMgr;

import io.netty.channel.Channel;

/**
 * 命令基类
 */
public abstract class AbstractCommand implements Command {

	@Override
	public void execute(Channel channel, PBMessage packet) {
		GamePlayer player = null;
		try {
			long userId = packet.getPlayerId();
			player = WorldMgr.getPlayerFromCache(userId);
			if (player == null) {
				Log.warn("code " + packet.getCode() + " not found player " + userId + ",can not continue execute.");
				return;
			}
			execute(player, packet);
		} catch (Exception e) {
			if (player != null) {
				Log.error("player" + "code = " + packet.getCode() + ", has exception :", e);
			} else {
				Log.error("code = " + packet.getCode() + ", has exception :", e);
			}
		}
	}

	/**
	 * 子类处理逻辑
	 * 
	 * @param player
	 * @param packet
	 * @throws Exception
	 */
	public abstract void execute(GamePlayer player, PBMessage packet) throws Exception;
}
