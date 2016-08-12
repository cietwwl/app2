package com.chuangyou.xianni.inverseBead;

import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.inverseBead.PlayerBeadTimeInfo;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.inverseBead.manager.InverseBeadManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.sql.dao.DBManager;

public class InverseBeadRefreshInventory extends AbstractEvent implements IInventory {

	private GamePlayer player;
	private PlayerBeadTimeInfo playerBeadTimeInfo;

	public InverseBeadRefreshInventory(GamePlayer player) {
		this.player = player;
	}

	@Override
	public boolean loadFromDataBase() {
		playerBeadTimeInfo = DBManager.getPlayerBeadRefreshTimeDao().get(player.getPlayerId());
		if (playerBeadTimeInfo == null) {
			playerBeadTimeInfo = new PlayerBeadTimeInfo();
			playerBeadTimeInfo.setPlayerId(player.getPlayerId());
			playerBeadTimeInfo.setBeadRefreshId(InverseBeadInventory.spawnId + "");
			playerBeadTimeInfo.setCurrRefreshId(InverseBeadInventory.spawnId);
			playerBeadTimeInfo.setOp(Option.Insert);
		}
//		InverseBeadManager.syncSpawn(this.player, playerBeadTimeInfo);
		return true;
	}

	@Override
	public boolean unloadData() {
		playerBeadTimeInfo = null;
		return true;
	}

	@Override
	public boolean saveToDatabase() {
		if (playerBeadTimeInfo != null) {
			short option = playerBeadTimeInfo.getOp();
			if (option == Option.Insert) {
				DBManager.getPlayerBeadRefreshTimeDao().add(playerBeadTimeInfo);
			} else if (option == Option.Update) {
				DBManager.getPlayerBeadRefreshTimeDao().update(playerBeadTimeInfo);
			}
		}
		return true;
	}

	/**
	 * 更新
	 * 
	 * @param
	 * @return
	 */
	public boolean update(PlayerBeadTimeInfo playerBeadTimeInfo) {
		if (playerBeadTimeInfo.getPlayerId() != player.getPlayerId())
			return false;
		playerBeadTimeInfo.setOp(Option.Update);
		return true;
	}
	
	public PlayerBeadTimeInfo getplayerBeadTimeInfo() {
		return playerBeadTimeInfo;
	}
}
