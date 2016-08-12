package com.chuangyou.xianni.vip.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chuangyou.common.util.StringUtils;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.item.ItemAddType;
import com.chuangyou.xianni.entity.player.PlayerInfo;
import com.chuangyou.xianni.entity.vip.VipBagTemplate;
import com.chuangyou.xianni.entity.vip.VipTemplate;
import com.chuangyou.xianni.inverseBead.InverseBeadInventory;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.vip.templete.VipTemplateMgr;

import io.netty.util.internal.StringUtil;

public class VipManager {
	/**
	 * 购买vip
	 * 
	 * @param vipId
	 * @return
	 */
	public static boolean buyVip(GamePlayer player, int vipId) {
		VipTemplate temp = VipTemplateMgr.getVipTemplate(vipId);
		if (temp == null)
			return false;

		int vipDuration = temp.getVipDuration();

		PlayerInfo playerInfo = player.getBasePlayer().getPlayerInfo();
		Date vipInterimTimeLimit = playerInfo.getVipInterimTimeLimit();// 临时到期时间
		Date vipTimeLimit = playerInfo.getVipTimeLimit();

		long timeLength = vipTimeLimit.getTime() - System.currentTimeMillis();
		long timeLength2 = vipInterimTimeLimit.getTime() - System.currentTimeMillis();
		long time = 0;
		if (timeLength > 0) {
			time += timeLength;
		}
		if (timeLength2 > 0) {
			time += timeLength2;
		}
		playerInfo.setVipTimeLimit(new Date(System.currentTimeMillis() + vipDuration * 24 * 60 * 60 + time));
		playerInfo.setVipInterimTimeLimit(new Date());

		return true;
	}

	public static boolean buyPackage(GamePlayer player, int type, int id, short code) {

		String rec = player.getBasePlayer().getPlayerInfo().getVipReceiveRecording();
		Map<String, List<Object>> map = StringUtils.strToMap(rec);// 领取记录
		if (map.containsKey(type + "") && map.get(type + "").contains(id)) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.VIPBAG_ERROR4, code);// 已经购买过了
			return false;
		}

		VipBagTemplate bag = VipTemplateMgr.getvipBagTemplate(type, id);
		if (bag == null) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.VIPBAG_ERROR, code);// vip 礼包不存在
			return false;
		}
		PlayerInfo playerInfo = player.getBasePlayer().getPlayerInfo();
		if (bag.getType() == 1) {// vip购买礼包
			if (!isInterimVip(player) && !isVip(player)) {
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.VIPBAG_ERROR3, code);// 您不是 vip
				return false;
			}

			int buyNeedStone = bag.getBuyNeedStone();
			int condition = bag.getCondition();
			if (playerInfo.getVipLevel() < condition) {
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.VIPBAG_ERROR2, code);// vip等级不足
				return false;
			}
			// 检测仙玉
			if (buyNeedStone > 0 && playerInfo.getCash() < buyNeedStone) {
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Cash_UnEnough, code, "仙玉不足");
				return false;
			}
			if (buyNeedStone > 0)
				player.getBasePlayer().consumeCash(buyNeedStone);

			// 记录购买
			if (!map.containsKey(type + "")) {
				List<Object> obj = new ArrayList<>();
				map.put(type + "", obj);
			}
			map.get(type + "").add(id);
			player.getBasePlayer().getPlayerInfo().setVipReceiveRecording(StringUtils.mapToStr(map));

		} else if (bag.getType() == 2) {// vip周奖励
			if (!isVip(player)) {
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.VIPBAG_ERROR3, code);// 您不是 vip
				return false;
			}
			int vipLv = playerInfo.getVipLevel();
			int condition = bag.getCondition();
			if (vipLv != condition) {
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.VIPBAG_ERROR5, code);// 只能领取对应礼包
				return false;
			}
			// 记录领取
			
			
		}

		// 发物品
		int item1 = bag.getItem1();
		int item2 = bag.getItem2();
		int item3 = bag.getItem3();
		int item4 = bag.getItem4();
		int item5 = bag.getItem5();
		if (item1 > 0)
			player.getBagInventory().addItemInBagOrEmail(item1, bag.getNum1(), ItemAddType.OVERLAY, false);
		if (item2 > 0)
			player.getBagInventory().addItemInBagOrEmail(item2, bag.getNum2(), ItemAddType.OVERLAY, false);
		if (item3 > 0)
			player.getBagInventory().addItemInBagOrEmail(item3, bag.getNum3(), ItemAddType.OVERLAY, false);
		if (item4 > 0)
			player.getBagInventory().addItemInBagOrEmail(item4, bag.getNum4(), ItemAddType.OVERLAY, false);
		if (item5 > 0)
			player.getBagInventory().addItemInBagOrEmail(item5, bag.getNum5(), ItemAddType.OVERLAY, false);

		return true;
	}

	private static boolean isInterimVip(GamePlayer player) {
		Date vipInterimTimeLimit = player.getBasePlayer().getPlayerInfo().getVipInterimTimeLimit();
		if (vipInterimTimeLimit != null && vipInterimTimeLimit.getTime() - System.currentTimeMillis() > 0) {
			return true;
		}
		return false;
	}

	private static boolean isVip(GamePlayer player) {
		Date vipTimeLimit = player.getBasePlayer().getPlayerInfo().getVipTimeLimit();
		if (vipTimeLimit != null && vipTimeLimit.getTime() - System.currentTimeMillis() > 0) {
			return true;
		}
		return false;
	}
}
