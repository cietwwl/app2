package com.chuangyou.xianni.task.cmd;

import com.chuangyou.common.protobuf.pb.task.TaskOperateReqProto.TaskOperateReqMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.task.TaskCfg;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.retask.vo.RealTask;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.task.logic.AcceptTaskLogic;
import com.chuangyou.xianni.task.logic.CommitTaskLogic;
import com.chuangyou.xianni.task.template.TaskTemplateMgr;

@Cmd(code = Protocol.C_REQ_TASKOPERATE, desc = "任务操作")
public class TaskOperateReqCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		TaskOperateReqMsg req = TaskOperateReqMsg.parseFrom(packet.getBytes());
		int taskId = req.getTaskId();
		int operate = req.getOperate();
		TaskCfg cfg = TaskTemplateMgr.getTaskCfg(taskId);
		if (cfg == null) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_TASKOPERATE, "找不到此任务模板：" + taskId);
			return;
		}
		RealTask task = player.getTaskInventory().getTaskInfos().get(taskId);
		if (operate == 1) {
			System.out.println("接收任务：taskId:"+taskId+"===>"+player.getPlayerId());
			new AcceptTaskLogic().process(cfg, player, task);
		} else if (operate == 2) { // 提交任务
			System.out.println("提交任务：taskId:"+taskId+"===>"+player.getPlayerId());
			new CommitTaskLogic().process(cfg, player, task);
		}
	}


}
