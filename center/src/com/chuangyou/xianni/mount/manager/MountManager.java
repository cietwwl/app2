package com.chuangyou.xianni.mount.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.chuangyou.xianni.mount.template.MountTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;

public class MountManager {
	/**
	 * 计算坐骑属性值
	 * 
	 * @param roleId
	 */
	public static Map<Integer, Integer> computeMountAtt(GamePlayer player) {
		Map<Integer, Integer> attMap = new HashMap<Integer, Integer>();

		MountInfo mount = player.getMountInventory().getMount();

		// 等级加成
		MountLevelCfg mountLevelCfg = MountTemplateMgr.getLevelTemps().get(mount.getLevel());
		List<Integer> attList = mountLevelCfg.getAttList();
		AttributeUtil.putAttListToMap(attMap, attList);

		// 进阶加成
		MountGradeCfg mountGradeCfg = MountTemplateMgr.getGradeTemps().get(mount.getGrade());
		attList = mountGradeCfg.getAttList();
		AttributeUtil.putAttListToMap(attMap, attList);

		// 装备加成
		Map<Integer, MountEquipInfo> equips = player.getMountInventory().getMountEquip();
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
		Map<Integer, MountSpecialGet> roleMountGet = player.getMountInventory().getMountSpecialMap();
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

		return attMap;
	}
}
