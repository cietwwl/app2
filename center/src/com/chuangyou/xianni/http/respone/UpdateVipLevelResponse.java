package com.chuangyou.xianni.http.respone;

import java.util.Map;

import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.word.WorldMgr;

@HttpCmd(command = "updateVipLevel", desc = "设置VIP等级")
public class UpdateVipLevelResponse implements BaseRespone {

	@Override
	public String getResult(Map<String, String> params) throws Exception {
		// TODO Auto-generated method stub
		GamePlayer player = WorldMgr.getPlayer(Long.parseLong(params.get("playerId")));
		short vipLevel = Short.parseShort(params.get("vipLevel"));
		player.getBasePlayer().getPlayerInfo().setVipLevel(vipLevel);
		player.getBasePlayer().getPlayerInfo().setOp(Option.Update);
		return null;
	}

}
