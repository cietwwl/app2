package com.chuangyou.xianni.http.respone;

import java.util.Map;

import com.chuangyou.xianni.entity.task.TaskCfg;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResult;
import com.chuangyou.xianni.http.HttpResult.Code;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerState;
import com.chuangyou.xianni.word.WorldMgr;

@HttpCmd(command="task",desc="调整任务GM命令")
public class TaskGMRespone implements BaseRespone {

	@Override
	public String getResult(Map<String, String> params) throws Exception {
		// TODO Auto-generated method stub
		byte type = Byte.parseByte(params.get("type")); //操作类型
		long playerId = Long.valueOf(params.get("playerId"));
		int id = Integer.parseInt(params.get("id"));//ID
		//int num = Integer.parseInt(params.get("num"));//num
		
		GamePlayer player = WorldMgr.getPlayerFromCache(playerId);
		if (player != null && player.getPlayerState() == PlayerState.ONLINE) {
			
			switch(type){
			case TaskCfg.KILL_MONST:
				player.notifyListeners(new ObjectEvent(this, id, EventNameType.TASK_KILL_MONSTER));
			//	TaskManager.calcKillMonster(player, id, num);
				break;
			case TaskCfg.NPC_DIALOG:
				break;
			case TaskCfg.COMMIT_ITEM:
				break;
			case TaskCfg.PASS_FB:
				player.notifyListeners(new ObjectEvent(this, id, EventNameType.TASK_PASS_FB));
				//TaskManager.calcPassFb(player, id);
				break;
			case TaskCfg.PATCH:
				
				//TaskManager.calcGather(player, id, num);
				break;
			case TaskCfg.GET_ITEM:
				break;
			case TaskCfg.T_SYSTEM:
				player.notifyListeners(new ObjectEvent(this, id, EventNameType.TASK_T_SYSTEM));
				break;
			default:
				return HttpResult.getResult(Code.SUCCESS, "*_*taskGm exec fail*_*");
			}
			return HttpResult.getResult(Code.SUCCESS, "*_*taskGm exec success*_*");
		}
		return HttpResult.getResult(Code.SUCCESS, "*_*taskGm exec fail*_*");
	}

}
