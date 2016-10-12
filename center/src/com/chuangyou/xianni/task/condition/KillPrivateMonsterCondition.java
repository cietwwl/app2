package com.chuangyou.xianni.task.condition;

import java.util.ArrayList;

import com.chuangyou.common.protobuf.pb.gather.SearchPrivateMonsterInnerProto.SearchPrivateMonsterInnerMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.task.TaskCfg;
import com.chuangyou.xianni.entity.task.TaskInfo;
import com.chuangyou.xianni.netty.GatewayLinkedSet;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.script.manager.ScriptInterfaceManager;

/**
 * 杀私有怪物类型
 * @author laofan
 *
 */
public class KillPrivateMonsterCondition extends KillMonsterTaskCondition {

	public KillPrivateMonsterCondition(TaskInfo info, TaskCfg cfg, GamePlayer player) {
		super(info, cfg, player);
		// TODO Auto-generated constructor stub
		
	}


	@Override
	public void acceptProcess() {
		ArrayList<Integer> posList = cfg.toMapPos();
		if(posList.size()==4){
			if(cfg.getTaskTime()<=0){
				Log.error("任务 ID:"+cfg.getTaskId()+"刷私有怪任务任务时间不能填0");
				return;
			}
			for(int i=0;i<cfg.getTargetNum();i++){				
				ScriptInterfaceManager.createPrivateMonster(player.getPlayerId(),cfg.getTargetId(),posList.get(1),
						posList.get(2),
						posList.get(3),
						cfg.getTaskTime()*1000, posList.get(0));
			}
		}
	}

	@Override
	public void initProcess() {
		// TODO Auto-generated method stub
		if(info.getState() == TaskInfo.ACCEPT){
			//todo查询是否有私有任务怪
			SearchPrivateMonsterInnerMsg.Builder msg = SearchPrivateMonsterInnerMsg.newBuilder();
			msg.setPlayerId(player.getPlayerId());
			msg.setMonsterId(cfg.getTargetId());
			msg.setTaskId(info.getTaskId());
			PBMessage pkg = MessageUtil.buildMessage(Protocol.S_SEARCH_PRIVATE_MONSTER,msg);
			GatewayLinkedSet.send2Server(pkg);
		}
	}
	
	

}
