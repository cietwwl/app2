package com.chuangyou.xianni.http.respone;

import java.util.Map;

import com.chuangyou.xianni.bag.BagInventory;
import com.chuangyou.xianni.constant.PlayerState;
import com.chuangyou.xianni.entity.item.ItemAddType;
import com.chuangyou.xianni.entity.player.PlayerInfo;
import com.chuangyou.xianni.entity.player.PlayerJoinInfo;
import com.chuangyou.xianni.entity.player.PlayerPositionInfo;
import com.chuangyou.xianni.entity.player.PlayerTimeInfo;
import com.chuangyou.xianni.http.BaseRespone;
import com.chuangyou.xianni.http.HttpCmd;
import com.chuangyou.xianni.http.HttpResResult;
import com.chuangyou.xianni.http.HttpResResult.Code;
import com.chuangyou.xianni.player.BasePlayer;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.word.WorldMgr;

@HttpCmd(command = "addPlayerItem", desc = "给玩家添加物品")
public class AddPlayerItemResponse implements BaseRespone {
	@Override
	public String getResult(Map<String, String> params) throws Exception {
		String nickName = params.get("nickName");
		String itemInfo = params.get("itemInfo");// 格式
													// itemId:num,itemId:num,itemId:num
		short addType = ItemAddType.TEST_ADD;
		if (nickName != null && !nickName.equals("")) {
			PlayerInfo info = DBManager.getPlayerInfoDao().getPlayerInfo(nickName);
			if (info == null) {
				return HttpResResult.getResult(Code.SUCCESS, "用户不存在");
			}
			long playerId = info.getPlayerId();
			GamePlayer player = WorldMgr.getPlayerFromCache(playerId);
			if (player == null || player.getPlayerState() != PlayerState.ONLINE) {
				player = new GamePlayer();
				PlayerInfo playerInfo = DBManager.getPlayerInfoDao().getPlayerInfo(playerId);
				PlayerJoinInfo playerJoinInfo = DBManager.getPlayerInfoDao().getJoinInfo(playerId);
				PlayerTimeInfo playerTimeInfo = DBManager.getPlayerInfoDao().getTimeInfo(playerId);
				PlayerPositionInfo playerPositionInfo = DBManager.getPlayerPositionInfoDao().get(playerId);
				BasePlayer basePlayer = new BasePlayer(playerInfo, playerJoinInfo, playerTimeInfo, playerPositionInfo);
				player.setBasePlayer(basePlayer);
				BagInventory bagInventory = new BagInventory(player);
				bagInventory.loadFromDataBase();
				player.setBagInventory(bagInventory);
			}
			String[] itemInfos = itemInfo.split(",");
			for (String string : itemInfos) {
				String[] item = string.split(":");
				int itemTempId = Integer.valueOf(item[0]);
				int count = Integer.valueOf(item[1]);
				player.getBagInventory().addItem(itemTempId, count, addType, true);
			}
			player.getBagInventory().saveToDatabase();
			return HttpResResult.getResult(Code.SUCCESS, "物品添加成功");
		}
		return HttpResResult.getResult(Code.FAIL, "物品添加失败");
	}
}
