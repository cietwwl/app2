package com.chuangyou.xianni.task.cmd;

import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerState;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.task.manager.TaskManager;

/**
 * 清除日常任务
 * @author laofan
 *
 */
public class ClearDayTaskCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("==================执行任务清理==========================");
		if(player.getPlayerState() == PlayerState.ONLINE && player.getTaskInventory().isReady()){
			TaskManager.clearDayTask(player, true);
		}
	}

}
