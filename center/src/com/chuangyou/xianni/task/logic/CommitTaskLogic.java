package com.chuangyou.xianni.task.logic;

import java.util.Date;

import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.task.TaskCfg;
import com.chuangyou.xianni.entity.task.TaskInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.retask.constant.ConditionType;
import com.chuangyou.xianni.retask.vo.RealTask;
import com.chuangyou.xianni.task.template.TaskTemplateMgr;

public class CommitTaskLogic extends AcceptTaskLogic {

	@Override
	public void process(TaskCfg cfg, GamePlayer player, RealTask task) {
		// TODO Auto-generated method stub
		Date current = new Date();
		if (task == null || task.getInfo().getState() != TaskInfo.FINISH) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_TASKOPERATE, cfg.getTaskId() + "任务数据错误");
			return;
		}
		if (task.getInfo().getProcess() < cfg.getTargetNum()) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.TASK_UN_FINISH, Protocol.C_REQ_TASKOPERATE, cfg.getTaskId() + "任务未完成。不能提交");
			return;
		}
		if (cfg.getTaskTime() > 0) { // 定时任务
			if (current.getTime() - task.getInfo().getCreateTime().getTime() > cfg.getTaskTime() * 1000) {
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.TASK_OUT_OF_DATE, Protocol.C_REQ_TASKOPERATE,  cfg.getTaskId() + "任务超时啦");
				return;
			}
		}

		if (cfg.getTaskTarget() == ConditionType.COMMIT_ITEM || cfg.getTaskTarget() == ConditionType.GET_ITEM ||cfg.getTaskTarget() == ConditionType.PATCH) {
			player.getBagInventory().removeItem(cfg.getTargetId(), cfg.getTargetNum(), ItemRemoveType.TASK_COMMIT);
		}

		task.doTaskCommit();

		if (cfg.getTaskNext() != 0) { // 将下一步任务发给客户端，当前步删除
			player.getTaskInventory().del(task.getInfo().getTaskId());
			TaskInfo subinfo = new TaskInfo();
			subinfo.setPlayerId(player.getPlayerId());
			subinfo.setProcess(0);
			subinfo.setState(TaskInfo.UN_ACCEPT);
			subinfo.setTaskId(cfg.getTaskNext());
			subinfo.setUpdateTime(current);
			subinfo.setCreateTime(current);
			subinfo.setOp(Option.Insert);
			cfg = TaskTemplateMgr.getTaskCfg(subinfo.getTaskId());
			task = new RealTask(cfg,subinfo,player);
			player.getTaskInventory().add(task);
		}
		sendMsg(player, task.getInfo(), 2);
		
	}
	
}
