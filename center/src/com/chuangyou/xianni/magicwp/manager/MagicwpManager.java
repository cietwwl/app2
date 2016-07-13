package com.chuangyou.xianni.magicwp.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.util.AttributeUtil;
import com.chuangyou.xianni.bag.ItemManager;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.entity.item.ItemTemplateInfo;
import com.chuangyou.xianni.entity.magicwp.MagicwpAtt;
import com.chuangyou.xianni.entity.magicwp.MagicwpBanInfo;
import com.chuangyou.xianni.entity.magicwp.MagicwpBanLevelCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpInfo;
import com.chuangyou.xianni.entity.magicwp.MagicwpLevelCfg;
import com.chuangyou.xianni.magicwp.template.MagicwpTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;

public class MagicwpManager {
	
	public static Map<Integer, Integer> computeMagicwpAtt(GamePlayer player){
		Map<Integer, Integer> attMap = new HashMap<Integer, Integer>();

		MagicwpAtt magicwpAtt = player.getMagicwpInventory().getMagicwpAtt();

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
		Map<Integer, MagicwpInfo> roleMagicwpMap = player.getMagicwpInventory().getMagicwpInfoMap();
		for (MagicwpInfo magicwp : roleMagicwpMap.values()) {

			// 等级加成
			MagicwpLevelCfg magicwpLevelCfg = MagicwpTemplateMgr.getLevelTemps().get(magicwp.getMagicwpId() * 1000 + magicwp.getLevel());
			AttributeUtil.putAttToMap(attMap, magicwpLevelCfg.getAttMap());

			// 洗炼加成
			AttributeUtil.putAttToMap(attMap, MagicwpRefineManager.getRefineAttMap(magicwp.getRefineAtts()));
		}

		// 禁制属性加成
		Map<Integer, MagicwpBanInfo> roleBanMap = player.getMagicwpInventory().getBanInfoMap();
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
					if(indexStr.equals("")) continue;
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
		
		return attMap;
	}
}
