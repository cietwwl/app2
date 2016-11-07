package com.chuangyou.xianni.http.respone;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.xianni.entity.player.PlayerInfo;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResResult;
import com.chuangyou.xianni.http.HttpResResult.Code;
import com.chuangyou.xianni.http.util.CalPet;
import com.chuangyou.xianni.sql.dao.DBManager;

@HttpCmd(command = "getPlayerPet", desc = "查询玩家宠物")
public class GetPlayerPetResponse implements BaseRespone {

	@Override
	public String getResult(Map<String, String> params) throws Exception {
		String nickName = params.get("nickName");
		Map<String, Object> data = new HashMap<>();
		if (nickName != null && !nickName.equals("")) {
			PlayerInfo info = DBManager.getPlayerInfoDao().getPlayerInfo(nickName);
			if (info != null) {
				long playerId = info.getPlayerId();
				Map<String, Object> att = CalPet.computePetAtt(playerId);
				data.put("totalPro", att);
				List<Object> petListInfo = CalPet.getPetListInfo(playerId);
				data.put("petListInfo", petListInfo);

				Map<String, Object> getPetSoulInfo = CalPet.getPetSoulInfo(playerId);
				data.put("petSoulInfo", getPetSoulInfo);

			}
		}
		return HttpResResult.getResult(Code.SUCCESS, data);
	}

}
