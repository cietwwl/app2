package com.chuangyou.xianni.task.cmd;

import com.chuangyou.common.protobuf.pb.task.GetTaskListRespProto.GetTaskListRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.task.TaskTriggerInfo;
import com.chuangyou.xianni.task.manager.TaskManager;

@Cmd(code=Protocol.C_REQ_TASKLIST,desc="获取任务列表")
public class GetTaskListReqCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		
		TaskManager.clearDayTask(player, false);	
		player.getTaskInventory().setReady(true);
		
		GetTaskListRespMsg.Builder resp = GetTaskListRespMsg.newBuilder();
		for (TaskTriggerInfo info : player.getTaskInventory().getTaskInfos().values()) {
			resp.addTasks(TaskManager.getTaskMsg(info.getInfo()));
		}
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_TASKLIST,resp);
		player.sendPbMessage(pkg);		
	}
	
	
	
}
