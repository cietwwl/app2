package com.chuangyou.xianni.activeSystem.logic;

import java.util.Date;

import com.chuangyou.common.protobuf.pb.activeSystem.GetAllActiveInfoRespProto.GetAllActiveInfoRespMsg;
import com.chuangyou.common.util.TimeUtil;
import com.chuangyou.xianni.entity.active.ActiveConfig;
import com.chuangyou.xianni.entity.active.ActiveInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.retask.vo.ActiveTask;

/**
 * 获取所有活跃任务逻辑辅助类
 * @author laofan
 *
 */
public class GetAllActiveLogic {
	
	public void process(GamePlayer player){
		GetAllActiveInfoRespMsg.Builder resp = GetAllActiveInfoRespMsg.newBuilder();
		for(ActiveTask stateTask:player.getActiveInventory().getActiveTasks().values()){
			stateTask = check(stateTask, player);
			resp.addInfos(((ActiveInfo)stateTask.getTaskInfo()).getMsg());
		}
		player.sendPbMessage(MessageUtil.buildMessage(Protocol.U_RESP_ACTIVE_GET_ALL,resp));
		player.getActiveInventory().setReady(true);
	}
	
	/**
	 * 检查跨天/跨周
	 * @param task
	 * @param player
	 * @return
	 */
	private ActiveTask check(ActiveTask task,GamePlayer player){
		if(task.getConfig().getType() == ActiveConfig.DAY || task.getConfig().getType() == ActiveConfig.TOTAL_DAY){			
			if(!TimeUtil.dataCompare5(task.getInfo().getCreateTime())){//跨天
				player.getActiveInventory().resetTask(task);
			}
		}
		if(task.getConfig().getType() == ActiveConfig.WEEK || task.getConfig().getType() == ActiveConfig.TOTAL_WEEK){
			if(!TimeUtil.isSameWeek5(task.getInfo().getCreateTime(),new Date())){//跨天
				player.getActiveInventory().resetTask(task);
			}
		}
		return task;
	}
	
	
	
	
	
}
