package com.chuangyou.xianni.http.respone;

import java.util.HashMap;
import java.util.Map;

import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.entity.player.PlayerInfo;
import com.chuangyou.xianni.entity.player.PlayerJoinInfo;
import com.chuangyou.xianni.entity.player.PlayerPositionInfo;
import com.chuangyou.xianni.entity.player.PlayerTimeInfo;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResResult;
import com.chuangyou.xianni.http.HttpResResult.Code;
import com.chuangyou.xianni.http.util.CalSoul;
import com.chuangyou.xianni.player.BasePlayer;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.soul.SoulInventory;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.word.WorldMgr;

@HttpCmd(command = "getPlayerSoul", desc = "查询玩家魂帆")
public class GetPlayerSoul implements BaseRespone {

	@Override
	public String getResult(Map<String, String> params) throws Exception {
		String nickName = params.get("nickName");
		Map<String, Object> data = new HashMap<>();
		if (nickName != null && !nickName.equals("")) {
			PlayerInfo info = DBManager.getPlayerInfoDao().getPlayerInfo(nickName);
			if (info != null) {
				long playerId = info.getPlayerId();
				GamePlayer player = WorldMgr.getPlayerFromCache(playerId);
				if (player != null && player.getPlayerState() == PlayerState.ONLINE) {
					data = CalSoul.computeSoulAtt(player);
				} else {
					player = new GamePlayer();
					PlayerInfo playerInfo = DBManager.getPlayerInfoDao().getPlayerInfo(playerId);
					PlayerJoinInfo playerJoinInfo = DBManager.getPlayerInfoDao().getJoinInfo(playerId);
					PlayerTimeInfo playerTimeInfo = DBManager.getPlayerInfoDao().getTimeInfo(playerId);
					PlayerPositionInfo playerPositionInfo = DBManager.getPlayerPositionInfoDao().get(playerId);
					// player.loadShareData(playerInfo, playerJoinInfo,
					// playerTimeInfo, playerPositionInfo);
					BasePlayer basePlayer = new BasePlayer(playerInfo, playerJoinInfo, playerTimeInfo, playerPositionInfo);
					player.setBasePlayer(basePlayer);
					SoulInventory soulInventory = new SoulInventory(player);
					player.setSoulInventory(soulInventory);
					soulInventory.loadFromDataBase();
					data = CalSoul.computeSoulAtt(player);
				}
			}
		}
		return HttpResResult.getResult(Code.SUCCESS, data);
	}
}
