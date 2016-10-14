package com.chuangyou.xianni.task.cmd;

import com.chuangyou.common.protobuf.pb.task.TaskSetNpcDialogReqProto.TaskSetNpcDialogReqMsg;
import com.chuangyou.common.protobuf.pb.task.TaskSetNpcDialogRespProto.TaskSetNpcDialogRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code=Protocol.C_REQ_SETNPCDIALOG,desc="设置NPC对话+QTE任务完成")
public class TaskSetNpcDialogReqCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		TaskSetNpcDialogReqMsg req = TaskSetNpcDialogReqMsg.parseFrom(packet.getBytes());
		int taskId = req.getTaskId();
		player.notifyListeners(new ObjectEvent(this, taskId, EventNameType.TASK_NPC_DIALOG));
		player.notifyListeners(new ObjectEvent(this, taskId, EventNameType.TASK_QTE));
		TaskSetNpcDialogRespMsg.Builder resp = TaskSetNpcDialogRespMsg.newBuilder();
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_SETNPCDIALOG,resp);
		player.sendPbMessage(pkg);
	}

}
