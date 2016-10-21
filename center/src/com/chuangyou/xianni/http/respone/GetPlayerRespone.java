package com.chuangyou.xianni.http.respone;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResult;
import com.chuangyou.xianni.http.HttpResult.Code;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.word.WorldMgr;

@HttpCmd(command = "getPlayers", desc = "获取玩家")
public class GetPlayerRespone implements BaseRespone {
	@Override
	public String getResult(Map<String, String> params) throws Exception {
		List<GamePlayer> players = WorldMgr.getOnLinePlayers();

		List<HashMap<String, String>> playerMap = new ArrayList<>();
		for (GamePlayer gamePlayer : players) {
			HashMap<String, String> p = new HashMap<>();
			p.put("roleId", gamePlayer.getPlayerId() + "");
			p.put("roleName", gamePlayer.getBasePlayer().getPlayerInfo().getNickName() + "");
			playerMap.add(p);
		}

		return HttpResult.getResult(Code.SUCCESS, playerMap);
	}
}
