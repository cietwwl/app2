package com.chuangyou.xianni.http.respone;

import java.util.Map;

import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResult;
import com.chuangyou.xianni.http.HttpResult.Code;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.word.WorldMgr;

@HttpCmd(command = "playerAddBuffer", desc = "添加buffer")
public class PlayerAddBufferRespone implements BaseRespone {

	@Override
	public String getResult(Map<String, String> params) throws Exception {
		long playerId = Long.valueOf(params.get("playerId"));
		int bufferId = Integer.valueOf(params.get("bufferId"));
		GamePlayer player = WorldMgr.getPlayerFromCache(playerId);
		if (player != null && player.getPlayerState() == PlayerState.ONLINE) {
			player.getArmyInventory().addBuff(bufferId);
			return HttpResult.getResult(Code.SUCCESS, "add buffer success!");
		}
		return HttpResult.getResult(Code.ERROR, "add buffer error! player not online");
	}

}
