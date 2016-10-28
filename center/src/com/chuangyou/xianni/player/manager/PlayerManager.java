package com.chuangyou.xianni.player.manager;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.chuangyou.common.util.Log;
import com.chuangyou.common.util.SensitivewordFilterUtil;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.player.PlayerInfo;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.player.BasePlayer;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.NickNameCheckResult;
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

		if (basePlayer.getPlayerTimeInfo().getOp() == Option.Insert) {
			playerDao.addTimeInfo(basePlayer.getPlayerTimeInfo());
		}
		if (basePlayer.getPlayerTimeInfo().getOp() == Option.Update) {
			playerDao.updateTimeInfo(basePlayer.getPlayerTimeInfo());
		}
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
	 * 
	 * @param level
	 * @return
	 */
	/**
	 * @param level
	 * @return
	 */
	public static BaseProperty getPlayerBaseProperty(int level) {
		BaseProperty baseProperty = new BaseProperty();
		baseProperty.setSoul(20 + level * 50);
		baseProperty.setBlood(125 + level * 100);
		baseProperty.setAttack(15 + level * 5);
		baseProperty.setDefence(10 + level * 5);
		baseProperty.setSoulAttack(1 + level * 3);
		baseProperty.setSoulDefence(1 + level * 3);
		baseProperty.setRegainSoul(10 + level * 25);
		baseProperty.setRegainBlood(200 + level * 50);
		baseProperty.setSpeed(600);
		return baseProperty;
	}

	/**
	 * 
	 * @param nickName
	 * @return
	 */
	public static short nickNameCheck(String nickName) {
		// 长度判断
		try {
			int len = nickName.getBytes("utf-8").length;
			if (len > 21 || len == 0) {
				return NickNameCheckResult.LENGTH_LIMIT;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO: handle exception
			e.printStackTrace();
			Log.error(e);
		}

		// 非法字符判断
		String checkName = SensitivewordFilterUtil.getIntence().replaceSensitiveWord(nickName);
		if (!checkName.equals(nickName)) {
			return NickNameCheckResult.ILLEGAL_CHARACTER;
		}

		// 是否存在
		List<PlayerInfo> playerInfos = DBManager.getPlayerInfoDao().getByNickName(nickName);
		if (playerInfos != null && playerInfos.size() > 0) {
			return NickNameCheckResult.NAME_EXIST;
		}
		return 0;
	}
}
