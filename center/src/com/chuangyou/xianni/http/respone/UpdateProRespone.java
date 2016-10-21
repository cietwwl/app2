package com.chuangyou.xianni.http.respone;

import java.util.Map;

import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResult;
import com.chuangyou.xianni.http.HttpResult.Code;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.word.WorldMgr;

@HttpCmd(command = "updatePro", desc = "添加属性")
public class UpdateProRespone implements BaseRespone {

	@Override
	public String getResult(Map<String, String> params) throws Exception {
		// TODO Auto-generated method stub
		long playerId = Long.valueOf(params.get("playerId"));
		int num = Integer.valueOf(params.get("num"));
		int type = Integer.valueOf(params.get("type"));

		GamePlayer player = WorldMgr.getPlayerFromCache(playerId);
		if (player != null && player.getPlayerState() == PlayerState.ONLINE) {
			if (type == EnumAttr.CUR_BLOOD.getValue()) {
				player.getBasePlayer().updateBlood(num);
				return HttpResult.getResult(Code.SUCCESS, "update BLOOD success");
			} else if (type == EnumAttr.CUR_SOUL.getValue()) {
				player.getBasePlayer().updateSoul(num);
				return HttpResult.getResult(Code.SUCCESS, "update SOUL success");
			}else if (type == EnumAttr.VipLevel.getValue()) {
				player.getBasePlayer().getPlayerInfo().setVipLevel((short)num);
				return HttpResult.getResult(Code.SUCCESS, "update Vip Level success");
			}
		}
		return HttpResult.getResult(Code.SUCCESS, "add fail");
	}

}
