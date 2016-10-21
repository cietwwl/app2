package com.chuangyou.xianni.http.respone;

import java.util.Map;

import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResult;
import com.chuangyou.xianni.http.HttpResult.Code;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.word.WorldMgr;

@HttpCmd(command = "updateNickname", desc = "修改玩家昵称")
public class UpdateNicknameResponse implements BaseRespone {

	@Override
	public String getResult(Map<String, String> params) throws Exception {
		long playerId = Long.valueOf(params.get("playerId"));
		String nickname = params.get("nickname");
		GamePlayer player = WorldMgr.getPlayerFromCache(playerId);

		if (player != null && player.getPlayerState() == PlayerState.ONLINE) {
			player.getBasePlayer().getPlayerInfo().setNickName(nickname);
			return HttpResult.getResult(Code.SUCCESS, "update Nickname success *_*");
		}
		return HttpResult.getResult(Code.ERROR, "update Nickname fail *_*");
	}
}
