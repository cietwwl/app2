package com.chuangyou.xianni.task.cmd;

import java.util.Date;
import java.util.Iterator;
import java.util.Map.Entry;

import com.chuangyou.common.protobuf.pb.task.TaskOperateReqProto.TaskOperateReqMsg;
import com.chuangyou.common.protobuf.pb.task.TaskOperateRespProto.TaskOperateRespMsg;
import com.chuangyou.common.protobuf.pb.task.TaskUpdateRespProto.TaskUpdateRespMsg;
import com.chuangyou.common.util.StringUtils;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.item.ItemAddType;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.task.TaskCfg;
import com.chuangyou.xianni.entity.task.TaskInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.task.TaskTriggerInfo;
import com.chuangyou.xianni.task.manager.TaskManager;
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
		Date current = new Date();
		if (cfg == null) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_TASKOPERATE, "找不到此任务模板：" + taskId);
			return;
		}
		TaskTriggerInfo info = player.getTaskInventory().getTaskInfos().get(taskId);
		if (operate == 1) {

			// todo等级不够，不能接任务
			if (cfg.getTaskLv() > player.getBasePlayer().getPlayerInfo().getLevel()) {
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_TASKOPERATE, taskId + "等级不够。不能接这个任务");
				return;
			}

			if (info != null) {
				info.getInfo().setState(TaskInfo.ACCEPT);
				info.getInfo().setCreateTime(current);
				info.getInfo().setProcess(0);
				info.getInfo().setUpdateTime(current);
				info.getInfo().setOp(Option.Update);
			} else {
				// 检查有前置任务没有。如果有。前缀任务必须为完成状态
				if (cfg.getTaskPre() > 0) {
					ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_TASKOPERATE, taskId + "任务数据错乱");
					return;
				}

				TaskInfo subinfo = new TaskInfo();
				subinfo.setPlayerId(player.getPlayerId());
				subinfo.setTaskId(taskId);
				subinfo.setProcess(0);
				subinfo.setCreateTime(current);
				subinfo.setState(TaskInfo.ACCEPT);
				subinfo.setUpdateTime(current);
				subinfo.setOp(Option.Insert);
				info = new TaskTriggerInfo(player, subinfo);
				player.getTaskInventory().add(info);
			}
			info.addTrigger();
			sendMsg(player, info.getInfo(), operate, taskId);
			info.doScript(cfg.getAcceptScriptId(), info.getInfo().getState());

			// info.getCondition().initProcess();
			info.getCondition().acceptProcess();
		} else if (operate == 2) { // 提交任务
			if (info == null || info.getInfo().getState() != TaskInfo.FINISH) {
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_TASKOPERATE, taskId + "任务数据错误");
				return;
			}
			if (info.getInfo().getProcess() < cfg.getTargetNum()) {
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.TASK_UN_FINISH, Protocol.C_REQ_TASKOPERATE, taskId + "任务未完成。不能提交");
				return;
			}
			if (cfg.getTaskTime() > 0) { // 定时任务
				if (current.getTime() - info.getInfo().getCreateTime().getTime() > cfg.getTaskTime() * 1000) {
					ErrorMsgUtil.sendErrorMsg(player, ErrorCode.TASK_OUT_OF_DATE, Protocol.C_REQ_TASKOPERATE, taskId + "任务超时啦");
					return;
				}
			}

			if (cfg.getTaskTarget() == TaskCfg.COMMIT_ITEM || cfg.getTaskTarget() == TaskCfg.PATCH) {
				player.getBagInventory().removeItem(cfg.getTargetId(), cfg.getTargetNum(), ItemRemoveType.TASK_COMMIT);
			}

			info.getInfo().setState(TaskInfo.COMMIT);
			info.getInfo().setUpdateTime(current);
			info.getInfo().setOp(Option.Update);
			// 发奖
			if (cfg.getExp() != 0) {
				// todo加经验
				player.getBasePlayer().addExp(cfg.getExp());
			}
			if (cfg.getXiu() != 0) {
				// todo加修为
				player.getBasePlayer().addRepair(cfg.getXiu());
			}
			if (cfg.getMoney() != 0) {
				player.getBasePlayer().addMoney((int) cfg.getMoney(), ItemAddType.TASK_ADD);
			}
			if (cfg.getBindCash() != 0) {
				player.getBasePlayer().addBindCash(cfg.getBindCash(), ItemAddType.TASK_ADD);
			}
			if (!StringUtils.isNullOrEmpty(cfg.getItems())) {
				Iterator<Entry<Integer, String[]>> it = cfg.toItems().entrySet().iterator();
				while (it.hasNext()) {
					String[] s = it.next().getValue();
					player.getBagInventory().addItemInBagOrEmail(Integer.parseInt(s[0]), Integer.parseInt(s[1]), ItemAddType.TASK_ADD, Boolean.parseBoolean(s[2]));
				}
			}

			if (cfg.getTaskNext() != 0) { // 将下一步任务发给客户端，当前步删除
				player.getTaskInventory().del(info.getInfo());
				TaskInfo subinfo = new TaskInfo();
				subinfo.setOp(Option.Insert);
				subinfo.setPlayerId(player.getPlayerId());
				subinfo.setProcess(0);
				subinfo.setState(TaskInfo.UN_ACCEPT);
				subinfo.setTaskId(cfg.getTaskNext());
				subinfo.setUpdateTime(current);
				subinfo.setCreateTime(current);
				info = new TaskTriggerInfo(player, subinfo);
				player.getTaskInventory().add(info);
				sendMsg(player, subinfo, operate, taskId);
			} else { // 将当前任务
				info.getInfo().setOp(Option.Update);
				info.getInfo().setUpdateTime(current);
				info.getInfo().setState(TaskInfo.COMMIT);
				sendMsg(player, info.getInfo(), operate, taskId);
			}
			info.doScript(cfg.getCommitScriptId(), info.getInfo().getState());
		}
	}

	/**
	 * 发送消息
	 * 
	 * @param player
	 * @param info
	 */
	private void sendMsg(GamePlayer player, TaskInfo info, int type, int taskId) {
		TaskOperateRespMsg.Builder resp = TaskOperateRespMsg.newBuilder();
		resp.setTaskId(taskId);
		resp.setOperate(type);
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_TASKOPERATE, resp);
		player.sendPbMessage(pkg);

		TaskUpdateRespMsg.Builder notify = TaskUpdateRespMsg.newBuilder();
		notify.setInfo(TaskManager.getTaskMsg(info));
		pkg = MessageUtil.buildMessage(Protocol.U_RESP_TASKUPDATE, notify);
		player.sendPbMessage(pkg);
	}

}
