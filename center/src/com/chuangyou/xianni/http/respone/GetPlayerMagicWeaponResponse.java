package com.chuangyou.xianni.http.respone;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.player.PlayerInfo;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResResult;
import com.chuangyou.xianni.http.HttpResResult.Code;
import com.chuangyou.xianni.http.util.CalMagicWeapon;
import com.chuangyou.xianni.sql.dao.DBManager;

@HttpCmd(command = "getPlayerMagicWeapon", desc = "查询玩家法宝")
public class GetPlayerMagicWeaponResponse implements BaseRespone {

	@Override
	public String getResult(Map<String, String> params) throws Exception {
		String nickName = params.get("nickName");
		Map<String, Object> data = new HashMap<>();
		if (nickName != null && !nickName.equals("")) {
			PlayerInfo info = DBManager.getPlayerInfoDao().getPlayerInfo(nickName);
			if (info != null) {
				long playerId = info.getPlayerId();
				Map<String, Object> att = CalMagicWeapon.computeMagicwpTotalAtt(playerId);
				data.put("totalPro", att);
				Map<String, Object> danAtt = CalMagicWeapon.computeMagicDanAtt(playerId);
				data.put("danPro", danAtt);
				List<Object> magicList = CalMagicWeapon.computeMagicAtt(playerId);
				data.put("magicList", magicList);
				Map<String, Object> magicBan = CalMagicWeapon.computeMagicBanAtt(playerId);
				data.put("magicBan", magicBan);
			}
		}
		return HttpResResult.getResult(Code.SUCCESS, data);
	}
}
