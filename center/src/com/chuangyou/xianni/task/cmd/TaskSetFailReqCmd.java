package com.chuangyou.xianni.task.cmd;

import java.util.Date;

import com.chuangyou.common.protobuf.pb.task.TaskSetFailReqProto.TaskSetFailReqMsg;
import com.chuangyou.common.protobuf.pb.task.TaskSetFailRespProto.TaskSetFailRespMsg;
import com.chuangyou.common.protobuf.pb.task.TaskUpdateRespProto.TaskUpdateRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.task.TaskCfg;
import com.chuangyou.xianni.entity.task.TaskInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.task.manager.TaskManager;
import com.chuangyou.xianni.task.template.TaskTemplateMgr;

@Cmd(code = Protocol.C_REQ_SETTASKFAIL, desc = "设置任务超时失败")
public class TaskSetFailReqCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		TaskSetFailReqMsg req = TaskSetFailReqMsg.parseFrom(packet.getBytes());
		int taskId = req.getTaskId();
		Date current = new Date();
		TaskInfo info = player.getTaskInventory().getTaskInfos().get(taskId).getInfo();
		TaskCfg cfg = TaskTemplateMgr.getTaskCfg(taskId);
		if (cfg.getTaskTime() > 0) {
			if (current.getTime() - info.getCreateTime().getTime() >= cfg.getTaskTime() * 1000) {
				info.setCreateTime(current);
				info.setUpdateTime(current);
				info.setProcess(0);
				info.setState(TaskInfo.UN_ACCEPT);
				info.setOp(Option.Update);

				TaskSetFailRespMsg.Builder resp = TaskSetFailRespMsg.newBuilder();
				resp.setTaskId(taskId);
				PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_SETTASKFAIL, resp);
				player.sendPbMessage(pkg);

				TaskUpdateRespMsg.Builder notify = TaskUpdateRespMsg.newBuilder();
				notify.setInfo(TaskManager.getTaskMsg(info));
				pkg = MessageUtil.buildMessage(Protocol.U_RESP_TASKUPDATE, notify);
				player.sendPbMessage(pkg);
			} else {
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SETTASKFAIL, "时间未到：" + taskId);
			}
		} else {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SETTASKFAIL, "非定时任务：" + taskId);
		}
	}

}
