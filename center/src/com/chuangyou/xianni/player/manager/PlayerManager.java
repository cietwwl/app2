package com.chuangyou.xianni.player.manager;

import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.player.BasePlayer;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.sql.dao.DBManager;
import com.chuangyou.xianni.sql.dao.PlayerInfoDao;

public class PlayerManager {
	public static boolean save(BasePlayer basePlayer) {
		PlayerInfoDao playerDao = DBManager.getPlayerInfoDao();
		if (basePlayer.getPlayerInfo().getOp() == Option.Insert) {
			playerDao.add(basePlayer.getPlayerInfo());
		}
		if (basePlayer.getPlayerInfo().getOp() == Option.Update) {
			playerDao.update(basePlayer.getPlayerInfo());
		}
		if (basePlayer.getPlayerPositionInfo().getOp() == Option.Update) {
			DBManager.getPlayerPositionInfoDao().save(basePlayer.getPlayerPositionInfo());
		}
		//
		if (basePlayer.getPlayerJoinInfo().getOp() == Option.Insert) {
			playerDao.addJoinInfo(basePlayer.getPlayerJoinInfo());
		}
		if (basePlayer.getPlayerJoinInfo().getOp() == Option.Update) {
			playerDao.updateJoinInfo(basePlayer.getPlayerJoinInfo());
		}
		//
		// if(basePlayer.getPlayerTimeInfo().getOp() == Option.Insert){
		// playerDao.addTimeInfo(basePlayer.getPlayerTimeInfo());
		// }
		// if(basePlayer.getPlayerTimeInfo().getOp() == Option.Update){
		// playerDao.updateTimeInfo(basePlayer.getPlayerTimeInfo());
		// }
		return true;
	}

	public static boolean logOut(BasePlayer basePlayer) {
		return true;
	}

	/**
	 * 计算玩家总属性
	 * 
	 * @param player
	 */
	public static BaseProperty getTempProperty(GamePlayer player) {
		BasePlayer basePlayer = player.getBasePlayer();
		
		return getPlayerBaseProperty(basePlayer.getPlayerInfo().getLevel());
	}
	/**
	 * 根据等级获取基本属性
	 * @param level
	 * @return
	 */
	public static BaseProperty getPlayerBaseProperty(int level){
		BaseProperty baseProperty = new BaseProperty();
		baseProperty.setSoul(20 + level * 50);
		baseProperty.setBlood(125 + level * 100);
		baseProperty.setAttack(15 + level * 5);
		baseProperty.setDefence(10 + level * 5);
		baseProperty.setSoulAttack(1 + level * 3);
		baseProperty.setSoulDefence(1 + level * 3);
		baseProperty.setSpeed(600);
		return baseProperty;
	}
}
