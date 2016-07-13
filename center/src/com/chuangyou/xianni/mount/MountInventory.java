package com.chuangyou.xianni.mount;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.mount.MountEquipInfo;
import com.chuangyou.xianni.entity.mount.MountGradeCfg;
import com.chuangyou.xianni.entity.mount.MountInfo;
import com.chuangyou.xianni.entity.mount.MountSpecialGet;
import com.chuangyou.xianni.event.AbstractEvent;
import com.chuangyou.xianni.interfaces.IInventory;
import com.chuangyou.xianni.mount.manager.MountManager;
import com.chuangyou.xianni.mount.template.MountTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.sql.dao.DBManager;

public class MountInventory extends AbstractEvent implements IInventory {

	/**
	 * 玩家数据
	 */
	private GamePlayer						player;

	/**
	 * 玩家坐骑信息
	 */
	private MountInfo						mountInfo;

	/**
	 * 玩家坐骑装备信息
	 */
	private Map<Integer, MountEquipInfo>	mountEquipMap;

	/**
	 * 已获得的特殊坐骑
	 */
	private Map<Integer, MountSpecialGet>	mountSpecialMap;

	public MountInventory(GamePlayer player) {
		// TODO Auto-generated constructor stub
		this.player = player;
	}

	/**
	 * 获取玩家坐骑信息
	 * 
	 * @return
	 */
	public MountInfo getMount() {
		if (this.mountInfo == null) {
			int initMountId = 0;
			Map<Integer, MountGradeCfg> gradeCfgMap = MountTemplateMgr.getGradeTemps();
			for (MountGradeCfg grade : gradeCfgMap.values()) {
				if (grade.getGrade() == 1) {
					initMountId = grade.getId();
					break;
				}
			}
			mountInfo = new MountInfo(player.getPlayerId(), initMountId);
			mountInfo.setOp(Option.Insert);
		}
		return this.mountInfo;
	}

	/**
	 * 更新玩家坐骑信息
	 * 
	 * @param info
	 */
	public boolean updateMount(MountInfo info) {
		if (info.getPlayerId() != player.getPlayerId())
			return false;
		info.setOp(Option.Update);
		return true;
	}

	/**
	 * 获取玩家坐骑装备信息
	 * 
	 * @return
	 */
	public Map<Integer, MountEquipInfo> getMountEquip() {
		if (this.mountEquipMap == null)
			this.mountEquipMap = new HashMap<Integer, MountEquipInfo>();
		if (this.mountEquipMap.size() <= 0) {
			Set<Integer> idSet = MountTemplateMgr.getEquipTemps().keySet();
			for (int equipId : idSet) {
				MountEquipInfo info = new MountEquipInfo(player.getPlayerId(), equipId);
				info.setOp(Option.Insert);
				this.mountEquipMap.put(info.getEquipId(), info);
			}
		}
		return this.mountEquipMap;
	}

	/**
	 * 更新玩家装备信息
	 * 
	 * @param info
	 * @return
	 */
	public boolean updateMountEquip(MountEquipInfo info) {
		if (info.getPlayerId() != player.getPlayerId())
			return false;
		info.setOp(Option.Update);
		return true;
	}

	/**
	 * 获取玩家已经获得的特殊坐骑
	 * 
	 * @return
	 */
	public Map<Integer, MountSpecialGet> getMountSpecialMap() {
		if (this.mountSpecialMap == null)
			this.mountSpecialMap = new HashMap<Integer, MountSpecialGet>();
		return mountSpecialMap;
	}

	/**
	 * 玩家获得特殊坐骑
	 * 
	 * @param info
	 * @return
	 */
	public boolean addMountSpecial(MountSpecialGet info) {
		if (info.getPlayerId() != player.getPlayerId())
			return false;
		if (this.mountSpecialMap == null)
			this.mountSpecialMap = new HashMap<Integer, MountSpecialGet>();
		if (this.mountSpecialMap.get(info.getMountId()) == null) {
			this.mountSpecialMap.put(info.getMountId(), info);
			info.setOp(Option.Insert);
			
			//影响人物属性变更
			player.getArmyInventory().getArmy().getHero().addMount(MountManager.computeMountAtt(player));
			player.getArmyInventory().updateProperty();
		}
		return true;
	}

	public boolean loadFromDataBase() {
		mountInfo = DBManager.getMountInfoDao().get(player.getPlayerId());
		mountEquipMap = DBManager.getMountEquipDao().getAll(player.getPlayerId());
		mountSpecialMap = DBManager.getMountSpecialDao().getAll(player.getPlayerId());
		return true;
	}

	public boolean unloadData() {
		player = null;

		mountInfo = null;
		if (mountEquipMap != null) {
			mountEquipMap.clear();
		}
		mountEquipMap = null;

		if(mountSpecialMap != null){
			mountSpecialMap.clear();
		}
		mountSpecialMap = null;

		return true;
	}

	public boolean saveToDatabase() {
		boolean result = false;

		if (mountInfo != null) {
			short option = mountInfo.getOp();
			if (option == Option.Update) {
				result = DBManager.getMountInfoDao().update(mountInfo);
			} else if (option == Option.Insert) {
				result = DBManager.getMountInfoDao().add(mountInfo);
			}
		}

		if (mountEquipMap != null && mountEquipMap.size() > 0) {
			for (MountEquipInfo info : mountEquipMap.values()) {
				short option = info.getOp();
				if (option == Option.Update) {
					result = DBManager.getMountEquipDao().update(info);
				} else if (option == Option.Insert) {
					result = DBManager.getMountEquipDao().add(info);
				}
			}
		}

		if (mountSpecialMap != null && mountSpecialMap.size() > 0) {
			for (MountSpecialGet info : mountSpecialMap.values()) {
				short option = info.getOp();
				if (option == Option.Insert) {
					result = DBManager.getMountSpecialDao().add(info);
				} else if (option == Option.Delete) {
					result = DBManager.getMountSpecialDao().delete(info.getPlayerId(), info.getMountId());
				}
			}
		}

		return true;
	}
}
