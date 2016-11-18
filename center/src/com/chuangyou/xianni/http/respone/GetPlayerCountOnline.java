package com.chuangyou.xianni.http.respone;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResResult;
import com.chuangyou.xianni.http.HttpResResult.Code;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.word.WorldMgr;

@HttpCmd(command = "getPlayerCountOnline", desc = "查询玩家在线数量")
public class GetPlayerCountOnline implements BaseRespone {

	@Override
	public String getResult(Map<String, String> params) throws Exception {
		Map<String, Object> data = new HashMap<>();
		List<GamePlayer> players = WorldMgr.getOnLinePlayers();
		data.put("count", players.size());
		return HttpResResult.getResult(Code.SUCCESS, data);
	}
}
