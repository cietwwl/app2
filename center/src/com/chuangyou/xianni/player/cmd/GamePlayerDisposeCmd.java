package com.chuangyou.xianni.player.cmd;

import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.word.WorldMgr;

/**
 * <pre>
 * 
 * </pre>
 */
public class GamePlayerDisposeCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		if (player.getPlayerState() == PlayerState.OFFLINE) {
			WorldMgr.unLoadPlayer(player);
		}
	}
}
