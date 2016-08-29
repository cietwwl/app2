package com.chuangyou.xianni.http.respone;

import java.util.Map;

import com.chuangyou.xianni.entity.player.PlayerInfo;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResult;
import com.chuangyou.xianni.http.HttpResult.Code;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerState;
import com.chuangyou.xianni.soul.SoulManager;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.word.WorldMgr;

@HttpCmd(command = "addCardPiece", desc = "添加碎片")
public class AddCardPiece implements BaseRespone {

	@Override
	public String getResult(Map<String, String> params) throws Exception {
		// TODO Auto-generated method stub
		String nickName = params.get("nickName");
		int cardId = Integer.valueOf(params.get("cardId"));
		int count = Integer.valueOf(params.get("count"));
		String pId = params.get("playerId");

		long playerId = 0;
		if (nickName != null && !nickName.equals("")) {
			PlayerInfo info = DBManager.getPlayerInfoDao().getPlayerInfo(nickName);
			if (info == null) {
				return HttpResult.getResult(Code.ERROR, "user not exist");
			}
			playerId = info.getPlayerId();
		}
		if (playerId == 0) {
			playerId = Long.valueOf(pId);
		}

		GamePlayer player = WorldMgr.getPlayerFromCache(playerId);
		if (player != null && player.getPlayerState() == PlayerState.ONLINE) {
			if (SoulManager.addCardPiece(cardId, player, count)) {
				return HttpResult.getResult(Code.SUCCESS, "success");
			}
		}
		return HttpResult.getResult(Code.ERROR, " fail");

	}

}
