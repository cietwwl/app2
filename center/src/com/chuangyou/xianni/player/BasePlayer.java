package com.chuangyou.xianni.player;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import com.chuangyou.common.util.LockData;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.common.template.LevelUpTempleteMgr;
import com.chuangyou.xianni.constant.EnumAttr;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.level.LevelUp;
import com.chuangyou.xianni.entity.player.PlayerInfo;
import com.chuangyou.xianni.entity.player.PlayerJoinInfo;
import com.chuangyou.xianni.entity.player.PlayerPositionInfo;
import com.chuangyou.xianni.entity.player.PlayerTimeInfo;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.player.event.PlayerProperEvent;
import com.chuangyou.xianni.player.event.PlayerPropertyUpdateEvent;
import com.chuangyou.xianni.player.event.PlayerSceneAttEvent;

/**
 * 玩家临时数据,只作临时记录和使用，不入库
 *
 */
public class BasePlayer extends AbstractEvent {

	/** 玩家详细信息 */
	private PlayerInfo playerInfo;

	/** 玩家加成属性信息 */
	private PlayerJoinInfo playerJoinInfo;

	/** 玩家时间、硽码相关信息 */
	private PlayerTimeInfo playerTimeInfo;

	/** 玩家临时数据，不入库 */
	private short onLineStatus = PlayerState.OFFLINE;
	/** 期望组队目标 */
	private int teamTarget = 0;

	/** 玩家移动位置信息 */
	private PlayerPositionInfo playerPositionInfo;

	private LockData moneyLock = new LockData();
	/*-----------------------更新数据---------------------------*/
	private AtomicInteger changeCount = new AtomicInteger(0);

	public BasePlayer(PlayerInfo playerInfo, PlayerJoinInfo playerJoinInfo, PlayerTimeInfo playerTimeInfo, PlayerPositionInfo playerPositionInfo) {
		this.playerInfo = playerInfo;
		this.playerJoinInfo = playerJoinInfo;
		this.playerTimeInfo = playerTimeInfo;
		this.playerPositionInfo = playerPositionInfo;
	}

	/**
	 * 添加金币
	 * 
	 * @param count
	 * @return
	 */
	public boolean addMoney(int count) {
		beginChanges();
		try {
			if (moneyLock.beginLock()) {
				this.playerInfo.setMoney(playerInfo.getMoney() + count);
				this.playerInfo.setOp(Option.Update);
			} else {
				Log.error("playerId : " + getPlayerInfo().getPlayerId() + " addMoney Lock");
				return false;
			}
		} catch (Exception e) {
			Log.error("playerId : " + getPlayerInfo().getPlayerId() + " addMoney", e);
			return false;
		} finally {
			commitChages(EnumAttr.MONEY.getValue(), playerInfo.getMoney());
			moneyLock.commitLock();
		}
		return true;
	}

	/**
	 * 消耗金币
	 * 
	 * @param count
	 * @return
	 */
	public boolean consumeMoney(int count) {
		if (playerInfo.getMoney() < count)
			return false;
		beginChanges();
		try {
			if (moneyLock.beginLock()) {
				this.playerInfo.setMoney(playerInfo.getMoney() - count);
				this.playerInfo.setOp(Option.Update);
			} else {
				Log.error("playerId : " + getPlayerInfo().getPlayerId() + " consumeMoney Lock");
				return false;
			}
		} catch (Exception e) {
			Log.error("playerId : " + getPlayerInfo().getPlayerId() + " consumeMoney", e);
			return false;
		} finally {
			commitChages(EnumAttr.MONEY.getValue(), playerInfo.getMoney());
			moneyLock.commitLock();
		}
		return true;
	}

	/**
	 * 添加仙玉
	 * 
	 * @param count
	 * @return
	 */
	public boolean addCash(int count) {
		beginChanges();
		try {
			if (moneyLock.beginLock()) {
				this.playerInfo.setCash(playerInfo.getCash() + count);
				this.playerInfo.setOp(Option.Update);
			} else {
				Log.error("playerId : " + getPlayerInfo().getPlayerId() + " addCash Lock");
				return false;
			}
		} catch (Exception e) {
			Log.error("playerId : " + getPlayerInfo().getPlayerId() + " addCash", e);
			return false;
		} finally {
			commitChages(EnumAttr.CASH.getValue(), this.getPlayerInfo().getCash());
			moneyLock.commitLock();
		}
		return true;
	}

	/**
	 * 消耗仙玉
	 * 
	 * @param count
	 * @return
	 */
	public boolean consumeCash(int count) {
		if (playerInfo.getCash() < count)
			return false;
		beginChanges();
		try {
			if (moneyLock.beginLock()) {
				this.playerInfo.setCash(playerInfo.getCash() - count);
				this.playerInfo.setOp(Option.Update);
			} else {
				Log.error("playerId : " + getPlayerInfo().getPlayerId() + " consumeCash Lock");
				return false;
			}
		} catch (Exception e) {
			Log.error("playerId : " + getPlayerInfo().getPlayerId() + " consumeMoney", e);
			return false;
		} finally {
			commitChages(EnumAttr.CASH.getValue(), this.getPlayerInfo().getCash());
			moneyLock.commitLock();
		}
		return true;
	}

	/**
	 * 消耗修为
	 * 
	 * @param count
	 * @return
	 */
	public boolean consumeRepair(int count) {
		if (playerInfo.getRepair() < count)
			return false;
		beginChanges();
		try {
			if (moneyLock.beginLock()) {
				this.playerInfo.setRepair(playerInfo.getRepair() - count);
				this.playerInfo.setOp(Option.Update);
			} else {
				Log.error("playerId : " + getPlayerInfo().getPlayerId() + " consumeCash Lock");
				return false;
			}
		} catch (Exception e) {
			Log.error("playerId : " + getPlayerInfo().getPlayerId() + " consumeMoney", e);
			return false;
		} finally {
			commitChages(EnumAttr.REPAIR.getValue(), this.getPlayerInfo().getRepair());
			moneyLock.commitLock();
		}
		return true;
	}

	/**
	 * 添加修为
	 * 
	 * @param count
	 * @return
	 */
	public boolean addRepair(int count) {
		beginChanges();
		try {
			if (moneyLock.beginLock()) {
				this.playerInfo.setRepair(playerInfo.getRepair() + count);
				this.playerInfo.setOp(Option.Update);
			} else {
				Log.error("playerId : " + getPlayerInfo().getPlayerId() + " addCash Lock");
				return false;
			}
		} catch (Exception e) {
			Log.error("playerId : " + getPlayerInfo().getPlayerId() + " addRepair", e);
			return false;
		} finally {
			commitChages(EnumAttr.REPAIR.getValue(), this.getPlayerInfo().getRepair());
			moneyLock.commitLock();
		}
		return true;
	}

	/**
	 * 添加仙玉(绑定)
	 * 
	 * @param count
	 * @return
	 */
	public boolean addBindCash(int count) {
		beginChanges();
		try {
			if (moneyLock.beginLock()) {
				this.playerInfo.setBindCash(playerInfo.getBindCash() + count);
				this.playerInfo.setOp(Option.Update);
			} else {
				Log.error("playerId : " + getPlayerInfo().getPlayerId() + " addBindCash Lock");
				return false;
			}
		} catch (Exception e) {
			Log.error("playerId : " + getPlayerInfo().getPlayerId() + " addBindCash", e);
			return false;
		} finally {
			commitChages(EnumAttr.CASH_BIND.getValue(), playerInfo.getBindCash());
			moneyLock.commitLock();
		}
		return true;
	}

	/**
	 * 消耗仙玉
	 * 
	 * @param count
	 * @return
	 */
	public boolean consumeBindCach(int count) {
		if (playerInfo.getBindCash() < count)
			return false;
		beginChanges();
		try {
			if (moneyLock.beginLock()) {
				this.playerInfo.setBindCash(playerInfo.getBindCash() - count);
				this.playerInfo.setOp(Option.Update);
			} else {
				Log.error("playerId : " + getPlayerInfo().getPlayerId() + " consumeBindCash Lock");
				return false;
			}
		} catch (Exception e) {
			Log.error("playerId : " + getPlayerInfo().getPlayerId() + " consumeBindMoney", e);
			return false;
		} finally {
			commitChages(EnumAttr.CASH_BIND.getValue(), playerInfo.getBindCash());
			moneyLock.commitLock();
		}
		return true;
	}

	/**
	 * @param quest 单条提交
	 */
	public void onChanged() {
		try {
			if (changeCount.intValue() <= 0) {
				updateProperties();
			}
		} catch (Exception e) {
			Log.error("更新属性异常 , nickName : " + playerInfo.getNickName(), e);
		}

	}

	/**
	 * 多条执行：提交打开
	 */
	public void beginChanges() {
		changeCount.incrementAndGet();
	}

	/**
	 * 多条执行：提交数据
	 */
	public void commitChages() {
		int changes = changeCount.decrementAndGet();
		if (changes < 0) {
			Log.error("用户消息列表溢出,需检查BeginChange相关位置");
			changeCount.set(0);
		}
		if (changes <= 0)
			updateProperties();
	}

	/**
	 * 提交指定属性的更新
	 * 
	 * @param type
	 */
	public void commitChages(int type, long value) {
		int changes = changeCount.decrementAndGet();
		if (changes < 0) {
			Log.error("用户消息列表溢出,需检查BeginChange相关位置");
			changeCount.set(0);
		}
		if (changes <= 0) {
			Map<Integer, Long> changeMap = new HashMap<Integer, Long>();
			changeMap.put(type, value);
			PlayerPropertyUpdateEvent event = new PlayerPropertyUpdateEvent(this, changeMap);
			notifyListeners(event);
		}
	}

	/**
	 * 玩家属性更新提交
	 * 
	 * @param changeMap
	 */
	public void commitChages(Map<Integer, Long> changeMap) {
		int changes = changeCount.decrementAndGet();
		if (changes < 0) {
			Log.error("用户消息列表溢出,需检查BeginChange相关位置");
			changeCount.set(0);
		}
		if (changes <= 0) {
			PlayerPropertyUpdateEvent event = new PlayerPropertyUpdateEvent(this, changeMap);
			notifyListeners(event);
		}
	}

	private void updateProperties() {
		PlayerProperEvent event = new PlayerProperEvent(this);
		notifyListeners(event);
	}

	/**
	 * 更换当前坐骑
	 * 
	 * @param mountId
	 * @return
	 */
	public boolean updateMountId(int mountId) {
		if (playerInfo.getMountId() == mountId)
			return false;
		beginChanges();
		try {
			this.playerInfo.setMountId(mountId);
			this.playerInfo.setOp(Option.Update);
		} catch (Exception e) {
			Log.error("playerId : " + getPlayerInfo().getPlayerId() + " updateMountId", e);
			return false;
		} finally {
			commitSceneChange(EnumAttr.Mount.getValue(), playerInfo.getMountId());
		}
		return true;
	}

	/**
	 * 更换当前出战法宝
	 * 
	 * @param magicwpId
	 * @return
	 */
	public boolean updateMagicwpId(int magicwpId) {
		if (playerInfo.getMagicWeaponId() == magicwpId)
			return false;
		beginChanges();
		try {
			this.playerInfo.setMagicWeaponId(magicwpId);
			this.playerInfo.setOp(Option.Update);
		} catch (Exception e) {
			// TODO: handle exception
			Log.error("playerId : " + getPlayerInfo().getPlayerId() + " updateMagicwpId", e);
			return false;
		} finally {
			commitSceneChange(EnumAttr.FaBao.getValue(), playerInfo.getMagicWeaponId());
		}
		return true;
	}

	/**
	 * 更新场景可见的属性
	 * 
	 * @param type
	 * @param value
	 */
	public void commitSceneChange(int type, int value) {
		int changes = changeCount.decrementAndGet();
		if (changes < 0) {
			Log.error("用户消息列表溢出,需检查BeginChange相关位置");
			changeCount.set(0);
		}
		if (changes <= 0) {
			PlayerSceneAttEvent event = new PlayerSceneAttEvent(this, type, value);
			notifyListeners(event);
		}
	}

	/**
	 * 玩家经验更新
	 * 
	 * @param addValue
	 * @return
	 */
	public boolean addExp(long addValue) {

		if (addValue == 0)
			return false;
		beginChanges();

		Map<Integer, Long> changeMap = new HashMap<>();
		boolean hasLevelUp = false;
		try {
			this.playerInfo.setTotalExp(playerInfo.getTotalExp() + addValue);
			this.playerInfo.setExp(this.playerInfo.getExp() + addValue);

			LevelUp curLevelTemp = LevelUpTempleteMgr.getPlayerLevelUp(playerInfo.getLevel());

			if (this.playerInfo.getExp() >= curLevelTemp.getExp()) {
				int level = LevelUpTempleteMgr.getPlayerLevel(playerInfo.getTotalExp());
				hasLevelUp = true;

				playerInfo.setLevel(level);

				long needTotalExp = LevelUpTempleteMgr.getPlayerLevelNeedExp(playerInfo.getLevel());
				playerInfo.setExp(playerInfo.getTotalExp() - needTotalExp);
			}

			changeMap.put(EnumAttr.Exp.getValue(), playerInfo.getExp());
			changeMap.put(EnumAttr.TOTALEXP.getValue(), playerInfo.getTotalExp());

			this.playerInfo.setOp(Option.Update);
		} catch (Exception e) {
			Log.error("playerId : " + getPlayerInfo().getPlayerId() + " updateExp", e);
			return false;
		} finally {
			commitChages(changeMap);

			if (hasLevelUp) {
				beginChanges();
				commitSceneChange(EnumAttr.Level.getValue(), playerInfo.getLevel());
			}
		}
		return true;
	}

	public PlayerInfo getPlayerInfo() {
		return playerInfo;
	}

	public PlayerJoinInfo getPlayerJoinInfo() {
		return playerJoinInfo;
	}

	public PlayerTimeInfo getPlayerTimeInfo() {
		return playerTimeInfo;
	}

	public short getOnLineStatus() {
		return onLineStatus;
	}

	public void setOnLineStatus(short onLineStatus) {
		this.onLineStatus = onLineStatus;
	}

	public PlayerPositionInfo getPlayerPositionInfo() {
		return playerPositionInfo;
	}

	public void setPlayerPositionInfo(PlayerPositionInfo playerPositionInfo) {
		this.playerPositionInfo = playerPositionInfo;
	}

	public void setPlayerInfo(PlayerInfo playerInfo) {
		this.playerInfo = playerInfo;
	}

	public void setPlayerJoinInfo(PlayerJoinInfo playerJoinInfo) {
		this.playerJoinInfo = playerJoinInfo;
	}

	public void setPlayerTimeInfo(PlayerTimeInfo playerTimeInfo) {
		this.playerTimeInfo = playerTimeInfo;
	}

	public int getTeamTarget() {
		return teamTarget;
	}

	public void setTeamTarget(int teamTarget) {
		this.teamTarget = teamTarget;
	}
}
