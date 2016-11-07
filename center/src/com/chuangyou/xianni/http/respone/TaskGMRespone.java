package com.chuangyou.xianni.http.respone;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.protobuf.pb.task.TaskOperateRespProto.TaskOperateRespMsg;
import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.task.TaskCfg;
import com.chuangyou.xianni.entity.task.TaskInfo;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResult;
import com.chuangyou.xianni.http.HttpResult.Code;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.retask.vo.RealTask;
import com.chuangyou.xianni.task.template.TaskTemplateMgr;
import com.chuangyou.xianni.word.WorldMgr;

@HttpCmd(command="task",desc="调整任务GM命令")
public class TaskGMRespone implements BaseRespone {

	@Override
	public String getResult(Map<String, String> params) throws Exception {
		// TODO Auto-generated method stub
		long playerId = Long.valueOf(params.get("playerId"));
		int taskId = Integer.parseInt(params.get("taskId"));//ID
		int process = Integer.parseInt(params.get("process"));
		
		GamePlayer player = WorldMgr.getPlayerFromCache(playerId);
		if (player != null && player.getPlayerState() == PlayerState.ONLINE) {
			RealTask task = player.getTaskInventory().getTaskInfos().get(taskId);
			if(task!=null){
				task.updateProcess(process);
			}else{
				TaskCfg cfg = TaskTemplateMgr.getTaskCfg(taskId);
				if(cfg!=null){
					List<Integer> ids = new ArrayList<>();
					for (RealTask t: player.getTaskInventory().getTaskInfos().values()) {
						
						if(t.getConfig().getTaskLink() == cfg.getTaskLink()){
							ids.add(t.getInfo().getTaskId());
						}
					}
					for (int id : ids) {
						player.getTaskInventory().del(id);
						TaskOperateRespMsg.Builder resp = TaskOperateRespMsg.newBuilder();
						resp.setTaskId(id);
						resp.setOperate(3);
						PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_TASKOPERATE, resp);
						player.sendPbMessage(pkg);
					}
				}
				TaskInfo updateT = new TaskInfo();
				updateT.setPlayerId(player.getPlayerId());
				updateT.setProcess(0);
				updateT.setTaskId(cfg.getTaskId());
				updateT.setState(TaskInfo.UN_ACCEPT);
				updateT.setUpdateTime(new Date());
				updateT.setCreateTime(new Date());
				updateT.setOp(Option.Insert);
				RealTask newTask = new RealTask(cfg, updateT, player);
				player.getTaskInventory().add(newTask);
				newTask.doAccept();
				newTask.updateProcess(process);
			}
			
		}
		
		return HttpResult.getResult(Code.SUCCESS, "*_*taskGm exec fail*_*");
	}

}
