package com.chuangyou.xianni.magicwp.manager;

import java.util.List;

import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.magicwp.MagicwpBanCfg;
import com.chuangyou.xianni.entity.magicwp.MagicwpBanInfo;
import com.chuangyou.xianni.entity.magicwp.MagicwpBanLevelCfg;
import com.chuangyou.xianni.magicwp.template.MagicwpTemplateMgr;
import com.chuangyou.xianni.player.GamePlayer;

public class MagicwpBanLevelManager {

	/**
	 * 禁制自动升级，背包物品发生改变时调用
	 * @param player
	 */
	public static void banAutoUp(GamePlayer player){
		
		List<Integer> autoUpBanList = player.getMagicwpInventory().getAutoUpBanList();
		boolean hasBanFragment = false;
		for(int banId:autoUpBanList){
			hasBanFragment = false;
			MagicwpBanCfg banCfg = MagicwpTemplateMgr.getBanTemps().get(banId);
			
			
//			for(Grid grid:list){
//				if(grid.getProp().getPublicId() == banCfg.getActiveItem1() || grid.getProp().getPublicId() == banCfg.getActiveItem2() ||
//						grid.getProp().getPublicId() == banCfg.getActiveItem3() || grid.getProp().getPublicId() == banCfg.getActiveItem4()){
//					hasBanFragment = true;
//					break;
//				}
//			}
//			if(hasBanFragment) MagicwpBanLevelManager.useAllFragment(roleId, ActiveMagicwpBanInfo.getIns().get(roleId, banId), channel);
		}
	}
	public static boolean useFragmentByIndex(GamePlayer player, MagicwpBanInfo ban, int index, short protoclCode) {
		int curLevel = ban.getLevel();
		MagicwpBanLevelCfg targetCfg = MagicwpTemplateMgr.getBanLevelTemps().get(ban.getBanId() * 1000 + curLevel + 1);
		if (targetCfg == null) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Magicwp_Ban_Level_Max, protoclCode);
			return false;
		}
		MagicwpBanLevelCfg curLevelCfg = MagicwpTemplateMgr.getBanLevelTemps().get(ban.getBanId() * 1000 + curLevel);

		// 计算出升满级所需要的经验
		int curLevExp = ban.getExp();
		int maxLevelNeedExp = 0;
		while (targetCfg != null) {
			maxLevelNeedExp += curLevelCfg.getExp() - curLevExp;
			curLevel++;
			curLevExp = 0;
			curLevelCfg = MagicwpTemplateMgr.getBanLevelTemps().get(ban.getBanId() * 1000 + curLevel);
			targetCfg = MagicwpTemplateMgr.getBanLevelTemps().get(ban.getBanId() * 1000 + curLevel + 1);
		}

		MagicwpBanCfg banCfg = MagicwpTemplateMgr.getBanTemps().get(ban.getBanId());
		int needItem = 0;
		switch (index) {
		case 1:
			needItem = banCfg.getActiveItem1();
			break;
		case 2:
			needItem = banCfg.getActiveItem2();
			break;
		case 3:
			needItem = banCfg.getActiveItem3();
			break;
		case 4:
			needItem = banCfg.getActiveItem4();
			break;
		default:
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Magicwp_Ban_FragmentIndex_Invalid, protoclCode);
			return false;
		}

		//升到最终等级
		int resultLevel = ban.getLevel();

		//背包里碎片数量
		int hasItemNum = player.getBagInventory().getPlayerBagItemCount(needItem);
		//升满级需要的碎片数量
		int needItemNum = (int) Math.ceil(maxLevelNeedExp / banCfg.getExp());
		//升级后的经验
		int leftExp = 0;

		//可升到满经
		if (hasItemNum >= needItemNum) {
			if(!player.getBagInventory().removeItemFromPlayerBag(needItem, needItemNum, ItemRemoveType.USE)) return false;
			resultLevel = curLevel;
		} else {
			//不可升到满级
			if(!player.getBagInventory().removeItemFromPlayerBag(needItem, hasItemNum, ItemRemoveType.USE)) return false;
			// 计算当前物品经验可以升到多少级，剩余多少经验
			int hasExp = banCfg.getExp() * hasItemNum;

			//计算可以升到的等级和升级后的经验
			curLevel = ban.getLevel();
			curLevelCfg = MagicwpTemplateMgr.getBanLevelTemps().get(ban.getBanId() * 1000 + curLevel);
			curLevExp = ban.getExp();

			while (hasExp >= (curLevelCfg.getExp() - curLevExp)) {
				hasExp = hasExp - (curLevelCfg.getExp() - curLevExp);
				curLevel++;
				curLevExp = 0;
				curLevelCfg = MagicwpTemplateMgr.getBanLevelTemps().get(ban.getBanId() * 1000 + curLevel);
			}
			resultLevel = curLevel;
			leftExp = curLevExp + hasExp;
		}
		ban.setLevel(resultLevel);
		ban.setExp(leftExp);
		return player.getMagicwpInventory().updateBan(ban);
	}

	public static boolean useAllFragment(GamePlayer player, MagicwpBanInfo ban, short protoclCode)  {
		if (ban.getLevel() < 1) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Magicwp_Ban_UnGet, protoclCode);
			return false;
		}

		int curLevel = ban.getLevel();
		
		MagicwpBanLevelCfg targetCfg = MagicwpTemplateMgr.getBanLevelTemps().get(ban.getBanId() * 1000 + curLevel + 1);
		if (targetCfg == null) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Magicwp_Ban_Level_Max, protoclCode);
			return false;
		}

		MagicwpBanLevelCfg curLevelCfg = MagicwpTemplateMgr.getBanLevelTemps().get(ban.getBanId() * 1000 + curLevel);
		// 计算出升满级所需要的经验
		int curLevExp = ban.getExp();
		int maxLevelNeedExp = 0;
		while (targetCfg != null) {
			maxLevelNeedExp += curLevelCfg.getExp() - curLevExp;
			curLevel++;
			curLevExp = 0;
			curLevelCfg = MagicwpTemplateMgr.getBanLevelTemps().get(ban.getBanId() * 1000 + curLevel);
			targetCfg = MagicwpTemplateMgr.getBanLevelTemps().get(ban.getBanId() * 1000 + curLevel + 1);
		}

		// 计算升满级需要的道具数量
		MagicwpBanCfg banCfg = MagicwpTemplateMgr.getBanTemps().get(ban.getBanId());
		int needItemNum = (int) Math.ceil(maxLevelNeedExp / banCfg.getExp());

		// 消耗道具
		int itemNum1 = player.getBagInventory().getPlayerBagItemCount(banCfg.getActiveItem1());
		int itemNum2 = player.getBagInventory().getPlayerBagItemCount(banCfg.getActiveItem2());
		int itemNum3 = player.getBagInventory().getPlayerBagItemCount(banCfg.getActiveItem3());;
		int itemNum4 = player.getBagInventory().getPlayerBagItemCount(banCfg.getActiveItem4());

		int resultLevel = ban.getLevel();
		int leftExp = 0;

		if (itemNum1 >= needItemNum) {
			if(!player.getBagInventory().removeItemFromPlayerBag(banCfg.getActiveItem1(), needItemNum, ItemRemoveType.USE)) return false;
			
			resultLevel = curLevel;
		} else if (itemNum1 + itemNum2 >= needItemNum) {
			if(!player.getBagInventory().removeItemFromPlayerBag(banCfg.getActiveItem1(), itemNum1, ItemRemoveType.USE)) return false;
			if(!player.getBagInventory().removeItemFromPlayerBag(banCfg.getActiveItem2(), needItemNum - itemNum1, ItemRemoveType.USE)) return false;
			
			resultLevel = curLevel;
		} else if (itemNum1 + itemNum2 + itemNum3 >= needItemNum) {
			if(!player.getBagInventory().removeItemFromPlayerBag(banCfg.getActiveItem1(), itemNum1, ItemRemoveType.USE)) return false;
			if(!player.getBagInventory().removeItemFromPlayerBag(banCfg.getActiveItem2(), itemNum2, ItemRemoveType.USE)) return false;
			if(!player.getBagInventory().removeItemFromPlayerBag(banCfg.getActiveItem3(), needItemNum - itemNum1 - itemNum2, ItemRemoveType.USE)) return false;
			
			resultLevel = curLevel;
		} else if (itemNum1 + itemNum2 + itemNum3 + itemNum4 >= needItemNum) {
			if(!player.getBagInventory().removeItemFromPlayerBag(banCfg.getActiveItem1(), itemNum1, ItemRemoveType.USE)) return false;
			if(!player.getBagInventory().removeItemFromPlayerBag(banCfg.getActiveItem2(), itemNum2, ItemRemoveType.USE)) return false;
			if(!player.getBagInventory().removeItemFromPlayerBag(banCfg.getActiveItem3(), itemNum3, ItemRemoveType.USE)) return false;
			if(!player.getBagInventory().removeItemFromPlayerBag(banCfg.getActiveItem4(), needItemNum - itemNum1 - itemNum2 - itemNum3, ItemRemoveType.USE)) return false;
			
			resultLevel = curLevel;
		} else {
			if(!player.getBagInventory().removeItemFromPlayerBag(banCfg.getActiveItem1(), itemNum1, ItemRemoveType.USE)) return false;
			if(!player.getBagInventory().removeItemFromPlayerBag(banCfg.getActiveItem2(), itemNum2, ItemRemoveType.USE)) return false;
			if(!player.getBagInventory().removeItemFromPlayerBag(banCfg.getActiveItem3(), itemNum3, ItemRemoveType.USE)) return false;
			if(!player.getBagInventory().removeItemFromPlayerBag(banCfg.getActiveItem4(), itemNum4, ItemRemoveType.USE)) return false;

			// 计算当前物品经验可以升到多少级，剩余多少经验
			int hasExp = banCfg.getExp() * (itemNum1 + itemNum2 + itemNum3 + itemNum4);

			//计算可升到的等级和升级后的经验
			curLevel = ban.getLevel();
			curLevelCfg = MagicwpTemplateMgr.getBanLevelTemps().get(ban.getBanId() * 1000 + curLevel);
			curLevExp = ban.getExp();

			while (hasExp >= (curLevelCfg.getExp() - curLevExp)) {
				hasExp = hasExp - (curLevelCfg.getExp() - curLevExp);
				curLevel++;
				curLevExp = 0;
				curLevelCfg = MagicwpTemplateMgr.getBanLevelTemps().get(ban.getBanId() * 1000 + curLevel);
			}
			resultLevel = curLevel;
			leftExp = curLevExp + hasExp;
		}
		ban.setLevel(resultLevel);
		ban.setExp(leftExp);
		return player.getMagicwpInventory().updateBan(ban);
		
	}
}
