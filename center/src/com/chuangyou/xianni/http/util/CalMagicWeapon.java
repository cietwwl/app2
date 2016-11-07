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
import com.chuangyou.xianni.entity.magicwp.MagicwpAtt;
import com.chuangyou.xianni.entity.magicwp.MagicwpBanCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpBanInfo;
import com.chuangyou.xianni.entity.magicwp.MagicwpBanLevelCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpInfo;
import com.chuangyou.xianni.entity.magicwp.MagicwpLevelCfg;
import com.chuangyou.xianni.entity.property.BaseProperty;
import com.chuangyou.xianni.magicwp.manager.MagicwpRefineManager;
import com.chuangyou.xianni.magicwp.template.MagicwpTemplateMgr;
import com.chuangyou.xianni.skill.SkillUtil;
import com.chuangyou.xianni.sql.dao.DBManager;

public class CalMagicWeapon {

	/**
	 * 总属性
	 * 
	 * @param playerId
	 * @return
	 */
	public static Map<String, Object> computeMagicwpTotalAtt(long playerId) {
		Map<String, Object> data = new HashMap<>();
		Map<Integer, Integer> attMap = new HashMap<Integer, Integer>();

		// MagicwpAtt magicwpAtt = player.getMagicwpInventory().getMagicwpAtt();
		MagicwpAtt magicwpAtt = DBManager.getMagicwpAttDao().get(playerId);
		// 属性丹加成
		int danId = SystemConfigTemplateMgr.getIntValue("magicwp.dan.prop.id");
		ItemTemplateInfo itemTemplate = ItemManager.findItemTempInfo(danId);
		List<Integer> attList = new ArrayList<Integer>();
		attList.add(itemTemplate.getStatistics1());
		attList.add(itemTemplate.getStatistics2());
		attList.add(itemTemplate.getStatistics3());
		attList.add(itemTemplate.getStatistics4());
		for (Integer att : attList) {
			int attType = (int) (att / 1000000);
			int attValue = (att % 1000000) * magicwpAtt.getUseDanNum();

			if (attMap.get(attType) == null) {
				attMap.put(attType, 0);
			}
			attMap.put(attType, attMap.get(attType) + attValue);
		}

		// 法宝属性加成
		// Map<Integer, MagicwpInfo> roleMagicwpMap =
		// player.getMagicwpInventory().getMagicwpInfoMap();
		Map<Integer, MagicwpInfo> roleMagicwpMap = DBManager.getMagicwpInfoDao().getAll(playerId);
		for (MagicwpInfo magicwp : roleMagicwpMap.values()) {
			// 等级加成
			MagicwpLevelCfg magicwpLevelCfg = MagicwpTemplateMgr.getLevelTemps().get(magicwp.getMagicwpId() * 1000 + magicwp.getLevel());
			AttributeUtil.putAttToMap(attMap, magicwpLevelCfg.getAttMap());
			// 洗炼加成
			AttributeUtil.putAttToMap(attMap, MagicwpRefineManager.getRefineAttMap(magicwp.getRefineAtts()));
		}

		// 禁制属性加成
		// Map<Integer, MagicwpBanInfo> roleBanMap =
		// player.getMagicwpInventory().getBanInfoMap();
		Map<Integer, MagicwpBanInfo> roleBanMap = DBManager.getMagicwpBanInfoDao().getAll(playerId);
		for (MagicwpBanInfo ban : roleBanMap.values()) {
			MagicwpBanLevelCfg banLevCfg = null;
			if (ban.getLevel() > 0) {
				banLevCfg = MagicwpTemplateMgr.getBanLevelTemps().get(ban.getBanId() * 1000 + ban.getLevel());
				AttributeUtil.putAttNumToMap(attMap, banLevCfg.getAtt1());
				AttributeUtil.putAttNumToMap(attMap, banLevCfg.getAtt2());
				AttributeUtil.putAttNumToMap(attMap, banLevCfg.getAtt3());
				AttributeUtil.putAttNumToMap(attMap, banLevCfg.getAtt4());
			} else {
				banLevCfg = MagicwpTemplateMgr.getBanLevelTemps().get(ban.getBanId() * 1000 + 1);
				String[] idxArr = ban.getFragmentStr().split("_");
				for (String indexStr : idxArr) {
					if (indexStr.equals(""))
						continue;
					int index = Integer.parseInt(indexStr);
					switch (index) {
						case 1:
							AttributeUtil.putAttNumToMap(attMap, banLevCfg.getAtt1());
							break;
						case 2:
							AttributeUtil.putAttNumToMap(attMap, banLevCfg.getAtt2());
							break;
						case 3:
							AttributeUtil.putAttNumToMap(attMap, banLevCfg.getAtt3());
							break;
						case 4:
							AttributeUtil.putAttNumToMap(attMap, banLevCfg.getAtt4());
							break;
					}
				}
			}
		}
		BaseProperty property = new BaseProperty();
		Map<String, Integer> attMap2 = new HashMap<String, Integer>();
		for (Entry<Integer, Integer> integer : attMap.entrySet()) {
			attMap2.put(integer.getKey() + "", integer.getValue());
			SkillUtil.joinPro(property, integer.getKey(), integer.getValue());
		}
		CalFighting calFighting = new CalFighting();
		calFighting.addBag(property, property);
		long fighting = calFighting.getFighting();
		data.put("pro", attMap2);
		data.put("fighting", fighting);
		return data;
	}

	/**
	 * 丹属性
	 * 
	 * @param playerId
	 * @return
	 */
	public static Map<String, Object> computeMagicDanAtt(long playerId) {
		Map<String, Object> data = new HashMap<>();
		Map<Integer, Integer> attMap = new HashMap<Integer, Integer>();

		// MagicwpAtt magicwpAtt = player.getMagicwpInventory().getMagicwpAtt();
		MagicwpAtt magicwpAtt = DBManager.getMagicwpAttDao().get(playerId);
		// 属性丹加成
		int danId = SystemConfigTemplateMgr.getIntValue("magicwp.dan.prop.id");
		ItemTemplateInfo itemTemplate = ItemManager.findItemTempInfo(danId);
		List<Integer> attList = new ArrayList<Integer>();
		attList.add(itemTemplate.getStatistics1());
		attList.add(itemTemplate.getStatistics2());
		attList.add(itemTemplate.getStatistics3());
		attList.add(itemTemplate.getStatistics4());
		for (Integer att : attList) {
			int attType = (int) (att / 1000000);
			int attValue = (att % 1000000) * magicwpAtt.getUseDanNum();

			if (attMap.get(attType) == null) {
				attMap.put(attType, 0);
			}
			attMap.put(attType, attMap.get(attType) + attValue);
		}

		BaseProperty property = new BaseProperty();
		Map<String, Integer> attMap2 = new HashMap<String, Integer>();
		for (Entry<Integer, Integer> integer : attMap.entrySet()) {
			attMap2.put(integer.getKey() + "", integer.getValue());
			SkillUtil.joinPro(property, integer.getKey(), integer.getValue());
		}
		CalFighting calFighting = new CalFighting();
		calFighting.addBag(property, property);
		long fighting = calFighting.getFighting();
		data.put("pro", attMap2);
		data.put("fighting", fighting);
		return data;
	}

	/**
	 * 各法宝属性
	 * 
	 * @param playerId
	 * @return
	 */
	public static List<Object> computeMagicAtt(long playerId) {
		List<Object> data = new ArrayList<>();
		Map<Integer, MagicwpInfo> roleMagicwpMap = DBManager.getMagicwpInfoDao().getAll(playerId);
		for (MagicwpInfo magicwp : roleMagicwpMap.values()) {
			Map<Integer, Integer> attMap = new HashMap<Integer, Integer>();
			// 等级加成
			MagicwpLevelCfg magicwpLevelCfg = MagicwpTemplateMgr.getLevelTemps().get(magicwp.getMagicwpId() * 1000 + magicwp.getLevel());
			AttributeUtil.putAttToMap(attMap, magicwpLevelCfg.getAttMap());
			// 洗炼加成
			AttributeUtil.putAttToMap(attMap, MagicwpRefineManager.getRefineAttMap(magicwp.getRefineAtts()));

			BaseProperty property = new BaseProperty();
			Map<String, Integer> attMap2 = new HashMap<String, Integer>();
			for (Entry<Integer, Integer> integer : attMap.entrySet()) {
				attMap2.put(integer.getKey() + "", integer.getValue());
				SkillUtil.joinPro(property, integer.getKey(), integer.getValue());
			}
			CalFighting calFighting = new CalFighting();
			calFighting.addBag(property, property);
			long fighting = calFighting.getFighting();
			Map<String, Object> m = new HashMap<>();
			m.put("pro", attMap2);
			m.put("fighting", fighting);
			m.put("name", MagicwpTemplateMgr.getBanTemps().get(magicwp.getMagicwpId()));
			m.put("lv", magicwp.getLevel());
			m.put("grade", magicwp.getGrade());
			data.add(m);
		}
		return data;
	}

	/**
	 * 各法宝禁制信息
	 * 
	 * @param playerId
	 * @return
	 */
	public static Map<String, Object> computeMagicBanAtt(long playerId) {
		Map<String, Object> run = new HashMap<>();
		

		// 禁制属性加成
		// Map<Integer, MagicwpBanInfo> roleBanMap =
		// player.getMagicwpInventory().getBanInfoMap();
		List<Object> list = new ArrayList<>();
		Map<Integer, MagicwpBanInfo> roleBanMap = DBManager.getMagicwpBanInfoDao().getAll(playerId);
		for (MagicwpBanInfo ban : roleBanMap.values()) {
			Map<Integer, Integer> attMap = new HashMap<Integer, Integer>();
			
			Map<String, Object> data = new HashMap<>();
			MagicwpBanCfg magicwpBanCfg = MagicwpTemplateMgr.getBanTemps().get(ban.getBanId());
			List<Integer> activeItemIds = new ArrayList<>();

			MagicwpBanLevelCfg banLevCfg = null;
			if (ban.getLevel() > 0) {
				banLevCfg = MagicwpTemplateMgr.getBanLevelTemps().get(ban.getBanId() * 1000 + ban.getLevel());
				AttributeUtil.putAttNumToMap(attMap, banLevCfg.getAtt1());
				AttributeUtil.putAttNumToMap(attMap, banLevCfg.getAtt2());
				AttributeUtil.putAttNumToMap(attMap, banLevCfg.getAtt3());
				AttributeUtil.putAttNumToMap(attMap, banLevCfg.getAtt4());
			} else {
				banLevCfg = MagicwpTemplateMgr.getBanLevelTemps().get(ban.getBanId() * 1000 + 1);
				MagicwpTemplateMgr.getBanTemps().get(ban.getBanId());
				String[] idxArr = ban.getFragmentStr().split("_");

				for (String indexStr : idxArr) {
					if (indexStr.equals(""))
						continue;
					int index = Integer.parseInt(indexStr);
					switch (index) {
						case 1:
							AttributeUtil.putAttNumToMap(attMap, banLevCfg.getAtt1());
							activeItemIds.add(magicwpBanCfg.getActiveItem1());
							break;
						case 2:
							AttributeUtil.putAttNumToMap(attMap, banLevCfg.getAtt2());
							activeItemIds.add(magicwpBanCfg.getActiveItem2());
							break;
						case 3:
							AttributeUtil.putAttNumToMap(attMap, banLevCfg.getAtt3());
							activeItemIds.add(magicwpBanCfg.getActiveItem3());
							break;
						case 4:
							AttributeUtil.putAttNumToMap(attMap, banLevCfg.getAtt4());
							activeItemIds.add(magicwpBanCfg.getActiveItem4());
							break;
					}
				}
			}
			data.put("name", magicwpBanCfg.getName());
			data.put("lv", ban.getLevel());
			List<Object> item = new ArrayList<>();
			for (Integer integer : activeItemIds) {
				ItemTemplateInfo info = ItemManager.findItemTempInfo(integer);
				Map<String, Object> obj = new HashMap<>();
				obj.put("activeName", info.getName());
				obj.put("num", 1);
				item.add(obj);
			}
			data.put("activeList", item);// 激活的碎片
			
			BaseProperty property = new BaseProperty();
			Map<String, Integer> attMap2 = new HashMap<String, Integer>();
			for (Entry<Integer, Integer> integer : attMap.entrySet()) {
				attMap2.put(integer.getKey() + "", integer.getValue());
				SkillUtil.joinPro(property, integer.getKey(), integer.getValue());
			}
			CalFighting calFighting = new CalFighting();
			calFighting.addBag(property, property);
			long fighting = calFighting.getFighting();
			data.put("pro", attMap2);
			//data.put("fighting", fighting);
			list.add(data);
		}
		run.put("list", list);
		return run;
	}

}
