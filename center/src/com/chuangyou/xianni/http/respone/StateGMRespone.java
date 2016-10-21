package com.chuangyou.xianni.http.respone;

import java.util.Map;

import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResult;
import com.chuangyou.xianni.http.HttpResult.Code;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.state.trigger.StateTrigger;
import com.chuangyou.xianni.word.WorldMgr;

@HttpCmd(command="state",desc="调整境界任务GM命令")
public class StateGMRespone implements BaseRespone {

	@Override
	public String getResult(Map<String, String> params) throws Exception {
		// TODO Auto-generated method stub
		int stateId = Integer.parseInt(params.get("stateId")); //操作类型
		long playerId = Long.valueOf(params.get("playerId"));
		int targetNum = Integer.parseInt(params.get("num"));//ID
		//int num = Integer.parseInt(params.get("num"));//num
		
		GamePlayer player = WorldMgr.getPlayerFromCache(playerId);
		if (player != null && player.getPlayerState() == PlayerState.ONLINE) {
			StateTrigger trigger =  player.getStateInventory().getStateTriggers().get(stateId);
			if(trigger!=null){
				trigger.getInfo().setProcess(targetNum);
				trigger.getCondition().doNotifyUpdate();
				return HttpResult.getResult(Code.SUCCESS, "*_*stateGm exec success*_*");
			}		
		}
		return HttpResult.getResult(Code.SUCCESS, "*_*StateGm exec fail*_*");
	}

}
