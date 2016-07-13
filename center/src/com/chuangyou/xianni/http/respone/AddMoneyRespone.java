package com.chuangyou.xianni.http.respone;

import java.util.Map;

import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResult;
import com.chuangyou.xianni.http.HttpResult.Code;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerState;
import com.chuangyou.xianni.word.WorldMgr;


@HttpCmd(command="addMoney",desc="添加金币")
public class AddMoneyRespone implements BaseRespone {

	@Override
	public String getResult(Map<String, String> params) throws Exception {
		// TODO Auto-generated method stub
		long playerId = Long.valueOf(params.get("playerId"));
		int num = Integer.valueOf(params.get("num"));
		int type = Integer.valueOf(params.get("type"));
		
		GamePlayer player = WorldMgr.getPlayerFromCache(playerId);
		if (player != null && player.getPlayerState() == PlayerState.ONLINE) {
			if(type==EnumAttr.MONEY.getValue()){
				player.getBasePlayer().addMoney(num);
				return HttpResult.getResult(Code.SUCCESS, "add money success");
			}else if(type == EnumAttr.CASH.getValue()){
				player.getBasePlayer().addCash(num);
				return HttpResult.getResult(Code.SUCCESS, "add cash success");
			}else if(type == EnumAttr.CASH_BIND.getValue()){
				player.getBasePlayer().addBindCash(num);
				return HttpResult.getResult(Code.SUCCESS, "add bindCash success");
			}else if(type == EnumAttr.REPAIR.getValue()){
				player.getBasePlayer().addRepair(num);
				return HttpResult.getResult(Code.SUCCESS, "add bindCash success");
			}
			
		}
		return HttpResult.getResult(Code.SUCCESS, "add money fail");
	}

}
