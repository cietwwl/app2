package com.chuangyou.xianni.http.respone;

import java.util.Map;

import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResult;
import com.chuangyou.xianni.http.HttpResult.Code;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.retask.vo.ActiveTask;
import com.chuangyou.xianni.word.WorldMgr;

@HttpCmd(command="active",desc="调整active任务GM命令")
public class ActiveGMRespone implements BaseRespone {

	@Override
	public String getResult(Map<String, String> params) throws Exception {
		// TODO Auto-generated method stub
		int stateId = Integer.parseInt(params.get("activeId")); //操作类型
		long playerId = Long.valueOf(params.get("playerId"));
		int targetNum = Integer.parseInt(params.get("num"));//ID
		//int num = Integer.parseInt(params.get("num"));//num
		
		GamePlayer player = WorldMgr.getPlayerFromCache(playerId);
		if (player != null && player.getPlayerState() == PlayerState.ONLINE) {
			ActiveTask trigger =  player.getActiveInventory().getActiveTasks().get(stateId);
			if(trigger!=null){
				trigger.updateProcess(targetNum);
				return HttpResult.getResult(Code.SUCCESS, "*_*activeGm exec success*_*");
			}		
		}
		return HttpResult.getResult(Code.SUCCESS, "*_*activeGm exec fail*_*");
	}

}
