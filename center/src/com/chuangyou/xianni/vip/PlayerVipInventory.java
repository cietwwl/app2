package com.chuangyou.xianni.vip;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import com.chuangyou.common.util.DateTimeUtil;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.vip.PlayerVipReceive;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.sql.dao.DBManager;

public class PlayerVipInventory extends AbstractEvent implements IInventory {
	/** 购买礼包类型 */
	public static final int	type_buy		= 1;
	/** 周奖励类型 */
	public static final int	type_receive	= 2;

	private GamePlayer		player;

	public PlayerVipInventory(GamePlayer player) {
		this.player = player;
	}

	HashMap<Integer, List<PlayerVipReceive>> playerVipReceiveData = new HashMap<>();

	@Override
	public boolean loadFromDataBase() {
		List<PlayerVipReceive> data = DBManager.getPlayerVipReceiveImplDao().getAll(player.getPlayerId());
		for (PlayerVipReceive playerVipReceive : data) {
			int type = playerVipReceive.getVipType();
			if (!playerVipReceiveData.containsKey(type)) {
				List<PlayerVipReceive> playerVip = new ArrayList<>();
				playerVipReceiveData.put(type, playerVip);
			}
			playerVipReceiveData.get(type).add(playerVipReceive);
		}
		return true;
	}

	@Override
	public boolean unloadData() {
		playerVipReceiveData.clear();
		return true;
	}

	@Override
	public boolean saveToDatabase() {
		if (playerVipReceiveData != null && playerVipReceiveData.size() > 0) {
			for (Entry<Integer, List<PlayerVipReceive>> iterable : playerVipReceiveData.entrySet()) {
				for (PlayerVipReceive info : iterable.getValue()) {
					short option = info.getOp();
					if (option == Option.Insert) {
						DBManager.getPlayerVipReceiveImplDao().add(info);
					} else if (option == Option.Update) {
						DBManager.getPlayerVipReceiveImplDao().update(info);
					}
				}
			}
		}
		return true;
	}

	/**
	 * 
	 * 
	 * @param
	 * @return
	 */
	public boolean add(PlayerVipReceive playerVipReceive) {
		if (playerVipReceive.getPlayerId() != player.getPlayerId())
			return false;
		playerVipReceive.setOp(Option.Insert);
		if (!playerVipReceiveData.containsKey(playerVipReceive.getVipType())) {
			List<PlayerVipReceive> playerVip = new ArrayList<>();
			playerVipReceiveData.put(playerVipReceive.getVipType(), playerVip);
		}
		this.playerVipReceiveData.get(playerVipReceive.getVipType()).add(playerVipReceive);
		return true;
	}

	public List<PlayerVipReceive> getPlayerVipReceive(int type) {
		return playerVipReceiveData.get(type);
	}

	/**
	 * 是否已经购买过
	 * 
	 * @param type
	 * @param id
	 * @return
	 */
	public boolean isAlreadyBuy(int type, int id) {
		List<PlayerVipReceive> data = this.playerVipReceiveData.get(type);
		if (data != null) {
			for (PlayerVipReceive playerVipReceive : data) {
				if (playerVipReceive.getVipId() == id)
					return true;
			}
		}
		return false;
	}

	/**
	 * 这周是否购买过
	 * 
	 * @return
	 */
	public boolean isAlreadyBuyWeek(int type, int id) {
		List<PlayerVipReceive> data = this.playerVipReceiveData.get(type);
		if (data != null) {
			for (PlayerVipReceive playerVipReceive : data) {
				if (playerVipReceive.getVipId() == id && DateTimeUtil.isSameWeek(new Date(), playerVipReceive.getReceiveTime()))
					return true;
			}
		}
		return false;
	}

	/**
	 * 是否领取了周奖励
	 * 
	 * @param type
	 * @return
	 */
	public boolean isAlreadyReceiveWeek() {
		List<PlayerVipReceive> data = this.playerVipReceiveData.get(type_receive);
		if (data != null) {
			for (PlayerVipReceive playerVipReceive : data) {
				if (DateTimeUtil.isSameWeek(new Date(), playerVipReceive.getReceiveTime()))
					return true;
			}
		}
		return false;
	}

	public HashMap<Integer, List<PlayerVipReceive>> getPlayerVipReceiveData() {
		return playerVipReceiveData;
	}

}
