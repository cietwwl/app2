package com.chuangyou.xianni.activeSystem.cmd;

import com.chuangyou.xianni.activeSystem.logic.GetAllActiveLogic;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;

/**
 * 执行活跃系统日常与周常的清理
 * @author laofan
 *
 */
public class ClearDayAndWeekActive extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		if(player.getPlayerState() == PlayerState.ONLINE && player.getActiveInventory().isReady()){
			new GetAllActiveLogic().process(player);
		}
	}

}
