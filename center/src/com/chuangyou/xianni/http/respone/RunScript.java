package com.chuangyou.xianni.http.respone;

import java.util.Map;

import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResult;
import com.chuangyou.xianni.http.HttpResult.Code;
import com.chuangyou.xianni.npcDialog.script.INpcDialogTrigger;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.script.manager.ScriptManager;
import com.chuangyou.xianni.word.WorldMgr;

@HttpCmd(command = "runScript", desc = "切换地图")
public class RunScript implements BaseRespone {

	@Override
	public String getResult(Map<String, String> params) throws Exception {
		long playerId = Long.parseLong(params.get("playerId")); // 角色ID
		int scriptId = Integer.valueOf(params.get("scriptId"));
		GamePlayer player = WorldMgr.getPlayer(playerId);
		if (player != null && player.getPlayerState() == PlayerState.ONLINE) {
			return HttpResult.getResult(Code.ERROR, "参数错误");
		}
		INpcDialogTrigger script = (INpcDialogTrigger) ScriptManager.getScriptById(scriptId);

		if (script == null) {
			return HttpResult.getResult(Code.ERROR, "脚本不存在");
		} else {
			//script.showDialog(player.getPlayerId(), 1);
		}
		return HttpResult.getResult(Code.SUCCESS, "run SUCCESS");
	}

}
