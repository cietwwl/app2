package com.chuangyou.xianni.inverseBead;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.chuangyou.xianni.army.Living;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.inverseBead.PlayerInverseBead;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.inverseBead.action.InverseBeadLoopAction;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.skill.SkillUtil;
import com.chuangyou.xianni.sql.dao.DBManager;

public class InverseBeadInventory extends AbstractEvent implements IInventory {
	/** 金 */
	public static final int					gold					= 1;
	/** 木 */
	public static final int					wood					= 2;
	/** 水 */
	public static final int					water					= 3;
	/** 火 */
	public static final int					fire					= 4;
	/** 土 */
	public static final int					earth					= 5;
	/** 刷怪初始id **/
	public static final int					spawnId					= 30201001;
	public static final int					campaignId				= 30201;					// 天逆珠副本id
	public static final int					auraId					= 6130011;					// 灵气液id

	private GamePlayer						player;
	private Map<String, PlayerInverseBead>	playerInverseBeadList	= null;
	private static List<Integer>			beadRefreshIdList		= new ArrayList<Integer>();	// 怪物池

	public InverseBeadInventory(GamePlayer player) {
		this.player = player;

		InverseBeadLoopAction action = new InverseBeadLoopAction(player, player.getActionQueue(), beadRefreshIdList, false);
		player.getActionQueue().enqueue(action);
	}

	@Override
	public boolean loadFromDataBase() {
		playerInverseBeadList = DBManager.getPlayerInverseBeadDao().getAll(player.getPlayerId());
		return true;
	}

	@Override
	public boolean unloadData() {
		playerInverseBeadList.clear();
		return true;
	}

	@Override
	public boolean saveToDatabase() {
		if (playerInverseBeadList != null && playerInverseBeadList.size() > 0) {
			for (Entry<String, PlayerInverseBead> iterable : playerInverseBeadList.entrySet()) {
				PlayerInverseBead info = iterable.getValue();
				short option = info.getOp();
				if (option == Option.Insert) {
					DBManager.getPlayerInverseBeadDao().add(info);
				} else if (option == Option.Update) {
					DBManager.getPlayerInverseBeadDao().update(info);
				}
			}
		}
		return true;
	}

	public Map<String, PlayerInverseBead> getInverseBead() {
		return playerInverseBeadList;
	}

	/**
	 * 更新
	 * 
	 * @param
	 * @return
	 */
	public boolean update(PlayerInverseBead playerInverseBead) {
		if (playerInverseBead.getPlayerId() != player.getPlayerId())
			return false;
		playerInverseBead.setOp(Option.Update);
		return true;
	}

	/**
	 * 
	 * 
	 * @param
	 * @return
	 */
	public boolean addOrUpdate(PlayerInverseBead playerInverseBead) {
		if (playerInverseBead.getPlayerId() != player.getPlayerId())
			return false;
		String key = playerInverseBead.getFiveElements() + "_" + playerInverseBead.getMarking();
		if (playerInverseBeadList.containsKey(key)) {
			playerInverseBead.setOp(Option.Update);
		} else {
			playerInverseBead.setOp(Option.Insert);
		}
		this.playerInverseBeadList.put(key, playerInverseBead);
		return true;
	}

	public PlayerInverseBead get(int fiveElements, int marking) {
		String key = fiveElements + "_" + marking;
		PlayerInverseBead playerInverseBead = null;
		playerInverseBead = this.playerInverseBeadList.get(key);
		if (playerInverseBead == null) {
			playerInverseBead = new PlayerInverseBead();
			playerInverseBead.setPlayerId(player.getPlayerId());
			playerInverseBead.setFiveElements(fiveElements);
			playerInverseBead.setMarking(marking);
			playerInverseBead.setStage(0);
			playerInverseBead.setVal(0);
			playerInverseBead.setAttVal(0);
			playerInverseBead.setAttVal2(0);
		}
		return playerInverseBead;
	}

	public void updataProperty() {
		if (player.getArmyInventory() != null) {
			BaseProperty proData = new BaseProperty();
			BaseProperty proPer = new BaseProperty();
			// 加入技能属性
			getTotalPro(proData, proPer);
			player.getArmyInventory().getHero().addInverseBead(proData, proPer);
			player.getArmyInventory().updateProperty();
		}
	}

	// 获取天逆珠总属性
	public void getTotalPro(BaseProperty proData, BaseProperty proPer) {
		for (Entry<String, PlayerInverseBead> entry : playerInverseBeadList.entrySet()) {
			PlayerInverseBead inverseBead = entry.getValue();
			int fiveElements = inverseBead.getFiveElements();
			int attVal = inverseBead.getAttVal();
			int attVal2 = inverseBead.getAttVal2();
			// 属性计算
			switch (fiveElements) {
				case InverseBeadInventory.gold:
					SkillUtil.joinPro(proData, Living.METAL + 1, attVal);
					SkillUtil.joinPro(proData, Living.METAL_DEFENCE + 1, attVal2);
					break;
				case InverseBeadInventory.wood:
					SkillUtil.joinPro(proData, Living.WOOD + 1, attVal);
					SkillUtil.joinPro(proData, Living.WOOD_DEFENCE + 1, attVal2);
					break;
				case InverseBeadInventory.water:
					SkillUtil.joinPro(proData, Living.WATER + 1, attVal);
					SkillUtil.joinPro(proData, Living.WATER_DEFENCE + 1, attVal2);
					break;
				case InverseBeadInventory.fire:
					SkillUtil.joinPro(proData, Living.FIRE + 1, attVal);
					SkillUtil.joinPro(proData, Living.FIRE_DEFENCE + 1, attVal2);
					break;
				case InverseBeadInventory.earth:
					SkillUtil.joinPro(proData, Living.EARTH + 1, attVal);
					SkillUtil.joinPro(proData, Living.EARTH_DEFENCE + 1, attVal2);
					break;
			}

		}
	}

	public static List<Integer> getBeadRefreshIdList() {
		return beadRefreshIdList;
	}

}
