package com.chuangyou.xianni.http.respone;

import java.util.Map;

import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.entity.item.ItemAddType;
import com.chuangyou.xianni.entity.player.PlayerInfo;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResult;
import com.chuangyou.xianni.http.HttpResult.Code;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerState;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.word.WorldMgr;

@HttpCmd(command = "addItem", desc = "测试")
public class AddItemResPonse implements BaseRespone {

	@Override
	public String getResult(Map<String, String> params) throws Exception {
		String nickName = params.get("nickName");
		int itemTempId = Integer.valueOf(params.get("tempId"));
		int count = Integer.valueOf(params.get("count"));
		short addType = ItemAddType.TEST_ADD;
		String pId = params.get("playerId");

		long playerId = 0;
		if (nickName != null && !nickName.equals("")) {
			PlayerInfo info = DBManager.getPlayerInfoDao().getPlayerInfo(nickName);
			if (info == null) {
				return HttpResult.getResult(Code.ERROR, "用户不存在");
			}
			playerId = info.getPlayerId();
		}
		if(playerId==0){
			playerId = Integer.valueOf(pId);
		}

		GamePlayer player = WorldMgr.getPlayerFromCache(playerId);
		if (player != null && player.getPlayerState() == PlayerState.ONLINE) {
			player.getBagInventory().addItem(itemTempId, count, addType, true);
			return HttpResult.getResult(Code.SUCCESS, "物品添加成功");
		}

		return HttpResult.getResult(Code.SUCCESS, "物品添加失败");
	}

}
