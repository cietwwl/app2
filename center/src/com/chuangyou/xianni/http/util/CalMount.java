package com.chuangyou.xianni.http.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.chuangyou.common.util.AttributeUtil;
import com.chuangyou.xianni.bag.ItemManager;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.entity.item.ItemTemplateInfo;
import com.chuangyou.xianni.entity.mount.MountEquipCfg;
import com.chuangyou.xianni.entity.mount.MountEquipInfo;
import com.chuangyou.xianni.entity.mount.MountGradeCfg;
import com.chuangyou.xianni.entity.mount.MountInfo;
import com.chuangyou.xianni.entity.mount.MountLevelCfg;
import com.chuangyou.xianni.entity.mount.MountSpecialGet;
import com.chuangyou.xianni.entity.mount.MountWeaponCfg;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.mount.template.MountTemplateMgr;
import com.chuangyou.xianni.skill.SkillUtil;
import com.chuangyou.xianni.sql.dao.DBManager;

public class CalMount {

	/**
	 * 计算坐骑属性值
	 * 
	 * @param roleId
	 */
	public static Map<String, Object> computeMountAtt(long playerId) {
		Map<String, Object> data = new HashMap<>();
		Map<Integer, Integer> attMap = new HashMap<Integer, Integer>();
		// MountInfo mount = player.getMountInventory().getMount();
		MountInfo mount = DBManager.getMountInfoDao().get(playerId);
		// 等级加成
		MountLevelCfg mountLevelCfg = MountTemplateMgr.getLevelTemps().get(mount.getLevel());
		List<Integer> attList = mountLevelCfg.getAttList();
		AttributeUtil.putAttListToMap(attMap, attList);

		// 进阶加成
		MountGradeCfg mountGradeCfg = MountTemplateMgr.getGradeTemps().get(mount.getGrade());
		attList = mountGradeCfg.getAttList();
		AttributeUtil.putAttListToMap(attMap, attList);

		// 装备加成
		// Map<Integer, MountEquipInfo> equips =
		// player.getMountInventory().getMountEquip();
		Map<Integer, MountEquipInfo> equips = DBManager.getMountEquipDao().getAll(playerId);
		for (MountEquipInfo mountEquip : equips.values()) {
			if (MountTemplateMgr.getEquipTemps().get(mountEquip.getEquipId()) != null) {
				MountEquipCfg equipCfg = MountTemplateMgr.getEquipTemps().get(mountEquip.getEquipId()).get(mountEquip.getEquipLevel());
				attList = equipCfg.getAttList();
				AttributeUtil.putAttListToMap(attMap, attList);
			}
		}

		// 属性丹加成
		int danId = SystemConfigTemplateMgr.getIntValue("mount.dan.prop.Id");
		ItemTemplateInfo itemTemplate = ItemManager.findItemTempInfo(danId);
		if (itemTemplate != null) {
			attList = new ArrayList<Integer>();
			attList.add(itemTemplate.getStatistics1());
			attList.add(itemTemplate.getStatistics2());
			attList.add(itemTemplate.getStatistics3());
			attList.add(itemTemplate.getStatistics4());
			for (Integer att : attList) {
				int attType = (int) (att / 1000000);
				int attValue = (att % 1000000) * mount.getUseDanNum();

				if (attMap.get(attType) == null) {
					attMap.put(attType, 0);
				}
				attMap.put(attType, attMap.get(attType) + attValue);
			}
		}

		// 特殊坐骑属性加成
		// Map<Integer, MountSpecialGet> roleMountGet =
		// player.getMountInventory().getMountSpecialMap();
		Map<Integer, MountSpecialGet> roleMountGet = DBManager.getMountSpecialDao().getAll(playerId);
		if (roleMountGet != null) {
			for (MountSpecialGet mountGet : roleMountGet.values()) {
				mountGradeCfg = MountTemplateMgr.getMountTemps().get(mountGet.getMountId());
				attList = mountGradeCfg.getAttList();
				AttributeUtil.putAttListToMap(attMap, attList);
			}
		}

		// 骑兵加成
		MountWeaponCfg mountWeaponCfg = MountTemplateMgr.getWeaponTemps().get(mount.getWeaponGrade());
		attList = mountWeaponCfg.getAttList();
		AttributeUtil.putAttListToMap(attMap, attList);

		/////
		BaseProperty property = new BaseProperty();
		Map<String, Integer> attMap2 = new HashMap<String, Integer>();
		for (Entry<Integer, Integer> integer : attMap.entrySet()) {
			attMap2.put(integer.getKey() + "", integer.getValue());
			SkillUtil.joinPro(property, integer.getKey(), integer.getValue());
		}

		CalFighting calFighting = new CalFighting();
		calFighting.addBag(property, property);
		long fighting = calFighting.getFighting();
		data.put("totalAtt", attMap2);
		data.put("lv", mount.getLevel());
		data.put("grade", mount.getGrade());
		data.put("fighting", fighting);
		data.put("gradeBless", mount.getBless());// 进阶祝福值
		// data.put("weaponBless", mount.getWeaponBless());// 骑兵进阶祝福值
		return data;
	}

	/**
	 * 骑兵加成属性值
	 * 
	 * @param roleId
	 */
	public static Map<String, Object> computeMountWeaponAtt(long playerId) {
		Map<String, Object> data = new HashMap<>();
		Map<Integer, Integer> attMap = new HashMap<Integer, Integer>();
		// MountInfo mount = player.getMountInventory().getMount();
		MountInfo mount = DBManager.getMountInfoDao().get(playerId);
		MountLevelCfg mountLevelCfg = MountTemplateMgr.getLevelTemps().get(mount.getLevel());
		List<Integer> attList = mountLevelCfg.getAttList();

		// 骑兵加成
		MountWeaponCfg mountWeaponCfg = MountTemplateMgr.getWeaponTemps().get(mount.getWeaponGrade());
		attList = mountWeaponCfg.getAttList();
		AttributeUtil.putAttListToMap(attMap, attList);

		Map<String, Integer> attMap2 = new HashMap<String, Integer>();
		BaseProperty property = new BaseProperty();
		for (Entry<Integer, Integer> integer : attMap.entrySet()) {
			attMap2.put(integer.getKey() + "", integer.getValue());
			SkillUtil.joinPro(property, integer.getKey(), integer.getValue());
		}
		CalFighting calFighting = new CalFighting();
		calFighting.addBag(property, property);
		long fighting = calFighting.getFighting();
		data.put("weaponAtt", attMap2);
		data.put("fighting", fighting);
		data.put("lv", mount.getLevel());
		data.put("weaponGrade", mount.getWeaponGrade());
		data.put("weaponBless", mount.getWeaponBless());// 骑兵进阶祝福值
		return data;
	}

	/**
	 * 装备属性值
	 * 
	 * @param roleId
	 */
	public static List<Object> computeMountEquipAtt(long playerId) {
		// MountInfo mount = player.getMountInventory().getMount();
		MountInfo mount = DBManager.getMountInfoDao().get(playerId);
		MountLevelCfg mountLevelCfg = MountTemplateMgr.getLevelTemps().get(mount.getLevel());
		List<Integer> attList = mountLevelCfg.getAttList();

		// 装备加成
		// Map<Integer, MountEquipInfo> equips =
		// player.getMountInventory().getMountEquip();
		Map<Integer, MountEquipInfo> equips = DBManager.getMountEquipDao().getAll(playerId);
		List<Object> list = new ArrayList<>();

		for (MountEquipInfo mountEquip : equips.values()) {
			if (MountTemplateMgr.getEquipTemps().get(mountEquip.getEquipId()) != null) {
				MountEquipCfg equipCfg = MountTemplateMgr.getEquipTemps().get(mountEquip.getEquipId()).get(mountEquip.getEquipLevel());
				Map<String, Object> m = new HashMap<String, Object>();
				m.put("id", equipCfg.getId());
				m.put("lv", equipCfg.getLevel());
				attList = equipCfg.getAttList();
				Map<Integer, Integer> attMap = new HashMap<Integer, Integer>();
				AttributeUtil.putAttListToMap(attMap, attList);

				Map<String, Integer> attMap2 = new HashMap<String, Integer>();
				for (Entry<Integer, Integer> integer : attMap.entrySet()) {
					attMap2.put(integer.getKey() + "", integer.getValue());
				}
				m.put("att", attMap2);
				list.add(m);
			}
		}
		return list;
	}
}
