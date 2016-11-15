package com.chuangyou.xianni.task.logic;

import java.util.Date;

import com.chuangyou.common.protobuf.pb.task.TaskOperateRespProto.TaskOperateRespMsg;
import com.chuangyou.common.protobuf.pb.task.TaskUpdateRespProto.TaskUpdateRespMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.task.TaskCfg;
import com.chuangyou.xianni.entity.task.TaskInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.retask.vo.RealTask;
import com.chuangyou.xianni.task.manager.TaskManager;

/**
 * 接受任务逻辑
 * @author laofan
 *
 */
public class AcceptTaskLogic {

	/**
	 * 处理
	 * @param cfg
	 * @param player
	 * @param task
	 */
	public void process(TaskCfg cfg,GamePlayer player,RealTask task){
		// todo等级不够，不能接任务
		if (cfg.getTaskLv() > player.getBasePlayer().getPlayerInfo().getLevel()) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_TASKOPERATE, cfg.getTaskId() + "等级不够。不能接这个任务");
			return;
		}
		Date current = new Date();
		if (task != null) {
			if(task.getInfo().getState() != TaskInfo.UN_ACCEPT){
				Log.warn("重复接任务：taskId = " + task.getInfo().getTaskId() + "  playerId = " + task.getInfo().getPlayerId());
				return;
			}
			task.doAccept();
		} else {  //如果任务列表中没有要接的任务数据。就新建
			// 检查是否有前置任务，如果有。前缀任务必须为完成状态
			if (cfg.getTaskPre() > 0) {
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_TASKOPERATE, cfg.getTaskId() + "任务数据错乱");
				return;
			}
			TaskInfo subinfo = new TaskInfo();
			subinfo.setPlayerId(player.getPlayerId());
			subinfo.setTaskId(cfg.getTaskId());
			subinfo.setCreateTime(current);
			subinfo.setState(TaskInfo.ACCEPT);
			subinfo.setOp(Option.Insert);
			task = new RealTask(cfg, subinfo, player);
			task.doAccept();
			player.getTaskInventory().add(task);	
		}
		sendMsg(player, task.getInfo(), 1);
	}
	
	
	/**
	 * 发送消息
	 * 
	 * @param player
	 * @param info
	 */
	protected void sendMsg(GamePlayer player, TaskInfo info, int type) {
		TaskOperateRespMsg.Builder resp = TaskOperateRespMsg.newBuilder();
		resp.setTaskId(info.getTaskId());
		resp.setOperate(type);
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_TASKOPERATE, resp);
		player.sendPbMessage(pkg);

		TaskUpdateRespMsg.Builder notify = TaskUpdateRespMsg.newBuilder();
		notify.setInfo(TaskManager.getTaskMsg(info));
		pkg = MessageUtil.buildMessage(Protocol.U_RESP_TASKUPDATE, notify);
		player.sendPbMessage(pkg);
	}
}
