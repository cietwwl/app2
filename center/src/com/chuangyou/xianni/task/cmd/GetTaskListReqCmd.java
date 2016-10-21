package com.chuangyou.xianni.task.cmd;

import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.player.GamePlayer;
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


}
