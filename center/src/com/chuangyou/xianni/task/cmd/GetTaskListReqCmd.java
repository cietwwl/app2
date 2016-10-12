package com.chuangyou.xianni.task.cmd;

import java.util.Date;

import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.task.TaskCfg;
import com.chuangyou.xianni.entity.task.TaskInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerState;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.task.logic.GetTaskLogic;

@Cmd(code = Protocol.C_REQ_TASKLIST, desc = "获取任务列表")
public class GetTaskListReqCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		if (player.getPlayerState() == PlayerState.OFFLINE)
			return;
		if (player.getTaskInventory() == null)
			return;
		new GetTaskLogic().process(player);
	}

	/**
	 * 检测限时任务
	 */
	private void checkLimitTimeTask(TaskInfo info, TaskCfg cfg) {
		Date current = new Date();
		if (cfg.getTaskTime() > 0) {
			if (current.getTime() - info.getCreateTime().getTime() >= cfg.getTaskTime() * 1000) {
				info.setCreateTime(current);
				info.setUpdateTime(current);
				info.setProcess(0);
				info.setState(TaskInfo.UN_ACCEPT);
				info.setOp(Option.Update);
			}
		}
	}

}
