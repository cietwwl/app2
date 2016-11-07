package com.chuangyou.xianni.http.respone;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.player.PlayerInfo;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResResult;
import com.chuangyou.xianni.http.HttpResResult.Code;
import com.chuangyou.xianni.http.util.CalMount;
import com.chuangyou.xianni.sql.dao.DBManager;

@HttpCmd(command = "getPlayerMount", desc = "查询玩家坐骑")
public class GetPlayerMountResponse implements BaseRespone {

	@Override
	public String getResult(Map<String, String> params) throws Exception {
		String nickName = params.get("nickName");
		Map<String, Object> data = new HashMap<>();
		if (nickName != null && !nickName.equals("")) {
			PlayerInfo info = DBManager.getPlayerInfoDao().getPlayerInfo(nickName);
			if (info != null) {
				long playerId = info.getPlayerId();
				Map<String, Object> att = CalMount.computeMountAtt(playerId);
				data.put("totalData", att);
				Map<String, Object> att1 = CalMount.computeMountWeaponAtt(playerId);
				data.put("weaponData", att1);
				List<Object> att2 = CalMount.computeMountEquipAtt(playerId);
				data.put("equipData", att2);
			}
		}
		return HttpResResult.getResult(Code.SUCCESS, data);
	}
}
