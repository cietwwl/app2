package com.chuangyou.xianni.http.respone;

import java.util.Map;

import com.chuangyou.xianni.entity.item.BagType;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResult;
import com.chuangyou.xianni.http.HttpResult.Code;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.word.WorldMgr;

@HttpCmd(command = "clearBag", desc = "清空背包")
public class ClearBagRespone implements BaseRespone {

	@Override
	public String getResult(Map<String, String> params) throws Exception {
		long playerId = Long.valueOf(params.get("playerId"));
		GamePlayer player = WorldMgr.getPlayerFromCache(playerId);
		if (player == null) {
			return HttpResult.getResult(Code.ERROR, "清空物品失败");
		}
		player.getBagInventory().getBag(BagType.Play).clearAllItems();
		return HttpResult.getResult(Code.SUCCESS, "清空物品成功");
	}

}
