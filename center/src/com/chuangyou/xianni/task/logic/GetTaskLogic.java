package com.chuangyou.xianni.task.logic;

import java.util.Date;

import com.chuangyou.common.protobuf.pb.task.GetTaskListRespProto.GetTaskListRespMsg;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.task.TaskCfg;
import com.chuangyou.xianni.entity.task.TaskInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.retask.vo.RealTask;
import com.chuangyou.xianni.task.manager.TaskManager;

public class GetTaskLogic {
	
	public void process(GamePlayer player){
		if(player.getTaskInventory()==null)return;
		TaskManager.clearDayTask(player, false);
		player.getTaskInventory().setReady(true);
		GetTaskListRespMsg.Builder resp = GetTaskListRespMsg.newBuilder();
		for (RealTask info : player.getTaskInventory().getTaskInfos().values()) {
			checkLimitTimeTask(info.getInfo(),info.getConfig());
			resp.addTasks(TaskManager.getTaskMsg(info.getInfo()));
		}
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_TASKLIST,resp);
		player.sendPbMessage(pkg);		
	}
	

	/**
	 * 检测限时任务
	 */
	private void checkLimitTimeTask(TaskInfo info,TaskCfg cfg){
		Date current = new Date();
		if(cfg.getTaskTime()>0){
			if(current.getTime() - info.getCreateTime().getTime()>=cfg.getTaskTime()*1000){
				info.setCreateTime(current);
				info.setUpdateTime(current);
				info.setProcess(0);
				info.setState(TaskInfo.UN_ACCEPT);
				info.setOp(Option.Update);
			}
		}
	}
}
