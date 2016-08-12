package com.chuangyou.xianni.inverseBead.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.chuangyou.common.protobuf.pb.inverseBead.SyncMonsterPoolMsgProto.SyncMonsterPoolMsg;
import com.chuangyou.common.util.MathUtils;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.entity.inverseBead.InverseBeadTem;
import com.chuangyou.xianni.entity.inverseBead.PlayerBeadTimeInfo;
import com.chuangyou.xianni.entity.inverseBead.PlayerInverseBead;
import com.chuangyou.xianni.entity.item.BindType;
import com.chuangyou.xianni.entity.item.ItemAddType;
import com.chuangyou.xianni.inverseBead.InverseBeadInventory;
import com.chuangyou.xianni.inverseBead.InverseBeadRefreshInventory;
import com.chuangyou.xianni.inverseBead.template.InverseBeadTemMgr;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;

public class InverseBeadManager {

	public static boolean up(GamePlayer player, int fiveElements, int marking, short code) {
		PlayerInverseBead playerInverseBead = player.getInverseBeadInventory().get(fiveElements, marking);
		int stage = playerInverseBead.getStage();
		int needGoodsId = 0;
		int needGoodsNum = 0;
		if (fiveElements == InverseBeadInventory.gold) {
			needGoodsId = SystemConfigTemplateMgr.getIntValue("fiveElements.needGoods.gold");
		} else if (fiveElements == InverseBeadInventory.wood) {
			needGoodsId = SystemConfigTemplateMgr.getIntValue("fiveElements.needGoods.wood");
		} else if (fiveElements == InverseBeadInventory.water) {
			needGoodsId = SystemConfigTemplateMgr.getIntValue("fiveElements.needGoods.water");
		} else if (fiveElements == InverseBeadInventory.fire) {
			needGoodsId = SystemConfigTemplateMgr.getIntValue("fiveElements.needGoods.fire");
		} else if (fiveElements == InverseBeadInventory.earth) {
			needGoodsId = SystemConfigTemplateMgr.getIntValue("fiveElements.needGoods.earth");
		}
		if (needGoodsId == 0) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.INVERSEBEAD_ERROR, code);
			return false;
		}
		if (fiveElements > 10) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.INVERSEBEAD_ERROR, code);
			return false;
		}
		if (stage >= 10) {// 已经至最高级
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.INVERSEBEAD_ERROR2, code);
			return false;
		}
		if (marking - 1 > 0) {
			PlayerInverseBead prePlayerInverseBead = player.getInverseBeadInventory().get(fiveElements, marking - 1);
			if (prePlayerInverseBead == null || prePlayerInverseBead.getStage() < 10) {// 上一个印记没有升满
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.INVERSEBEAD_ERROR3, code);
				return false;
			}
		}

		InverseBeadTem tem = InverseBeadTemMgr.getTemp(stage);
		if (tem == null) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.INVERSEBEAD_ERROR, code);
			return false;
		}
		needGoodsNum = tem.getNeedGoodsNum();
		if (player.getBagInventory().getPlayerBagItemCount(needGoodsId) < needGoodsNum) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Is_Not_Enougth, code);
			return false;
		}
		player.getBagInventory().removeItemFromPlayerBag(needGoodsId, needGoodsNum, BindType.ALL);

		int maxAddVal = tem.getAddVal();
		int getVal = MathUtils.randomClamp(1, maxAddVal);
		playerInverseBead.setVal(playerInverseBead.getVal() + getVal);
		// 升级了
		if (playerInverseBead.getVal() >= 100) {
			stage++;
			int fiveElementsVal = SystemConfigTemplateMgr.getIntValue("fiveElements");// 五行属性增量
			int fiveElementsResist = SystemConfigTemplateMgr.getIntValue("fiveElements.resist");// 五行抗性增量
			int attVal = playerInverseBead.getAttVal() + fiveElementsVal;
			int attVal2 = playerInverseBead.getAttVal2() + fiveElementsResist;

			playerInverseBead.setAttVal(attVal);
			playerInverseBead.setAttVal2(attVal2);
			playerInverseBead.setVal(0);
			playerInverseBead.setStage(stage);
			// 属性计算
			player.getInverseBeadInventory().updataProperty();
		}
		player.getInverseBeadInventory().addOrUpdate(playerInverseBead);

		return true;
	}

	public static boolean moveMonster(GamePlayer player, List<Integer> list) {
		// PlayerTimeInfo playerTimeInfo = player.getBasePlayer().getPlayerTimeInfo();
		PlayerBeadTimeInfo playerTimeInfo = player.getInverseBeadRefreshInventory().getplayerBeadTimeInfo();
		synchronized (playerTimeInfo) {
			String beadRefreshId = playerTimeInfo.getBeadRefreshId();
			List<Integer> list2 = InverseBeadManager.getBeadRefreshId(beadRefreshId);
			list2.removeAll(list);
			String str = InverseBeadManager.getBeadRefreshIdStr(list2);
			playerTimeInfo.setBeadRefreshId(str);
		}
		return false;
	}

	public static boolean reset(GamePlayer player, short code) {
		// PlayerTimeInfo playerTimeInfo = player.getBasePlayer().getPlayerTimeInfo();
		PlayerBeadTimeInfo playerTimeInfo = player.getInverseBeadRefreshInventory().getplayerBeadTimeInfo();
		int needGoods = SystemConfigTemplateMgr.getIntValue("fiveElements.reset.needGoods");
		if (needGoods > 0) {
			if (player.getBagInventory().getPlayerBagItemCount(needGoods) < 1) {
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Is_Not_Enougth, code);
				return false;
			}
			player.getBagInventory().removeItemFromPlayerBag(needGoods, 1, BindType.ALL);
		}
		synchronized (playerTimeInfo) {
			playerTimeInfo.setBeadRefreshId(InverseBeadInventory.spawnId + "");
			playerTimeInfo.setCurrRefreshId(InverseBeadInventory.spawnId);
			playerTimeInfo.setBeadRefreshDateTime(new Date());
		}
		// 同步
		SyncMonsterPoolMsg.Builder msg = SyncMonsterPoolMsg.newBuilder();
		List<Integer> list = new ArrayList<>();
		list.add(InverseBeadInventory.spawnId);
		msg.addAllMonsterRefreshId(list);
		PBMessage c2s = MessageUtil.buildMessage(Protocol.S_CREATE_INVERSE_SYNC_MONSTER, msg);
		player.sendPbMessage(c2s);

		return true;
	}

	/**
	 * 领取灵气液
	 * 
	 * @param player
	 * @param code
	 * @return
	 */
	public static int receiveAura(GamePlayer player, short code) {
		// PlayerTimeInfo playerTimeInfo = player.getBasePlayer().getPlayerTimeInfo();
		PlayerBeadTimeInfo playerTimeInfo = player.getInverseBeadRefreshInventory().getplayerBeadTimeInfo();
		int num = 0;
		synchronized (playerTimeInfo) {
			num = playerTimeInfo.getAuraNum();
			playerTimeInfo.setAuraNum(0);
		}
		if (num > 0) {
			player.getBagInventory().addItemInBagOrEmail(InverseBeadInventory.auraId, num, ItemAddType.OVERLAY, false);
		}
		return num;
	}

	public static List<Integer> getBeadRefreshId(String str) {
		List<Integer> list = new ArrayList<>();
		if (str == null || str.isEmpty())
			return list;
		String[] arr = str.split(",");
		for (String string : arr) {
			list.add(Integer.valueOf(string));
		}
		return list;
	}

	public static String getBeadRefreshIdStr(List<Integer> list) {
		StringBuffer sb = new StringBuffer();
		for (Integer integer : list) {
			sb.append(integer);
			sb.append(",");
		}
		if (sb.length() > 0)
			return sb.substring(0, sb.length() - 1);
		return "";
	}

	public static void syncSpawn(GamePlayer player) {
		// System.out.println("-----syncSpawn-----syncSpawn-----");
		PlayerBeadTimeInfo playerBeadTimeInfo = player.getInverseBeadRefreshInventory().getplayerBeadTimeInfo();
		List<Integer> list = InverseBeadManager.getBeadRefreshId(playerBeadTimeInfo.getBeadRefreshId());
		SyncMonsterPoolMsg.Builder msg = SyncMonsterPoolMsg.newBuilder();
		msg.addAllMonsterRefreshId(list);
		PBMessage c2s = MessageUtil.buildMessage(Protocol.S_CREATE_INVERSE_SYNC_MONSTER, msg);
		player.sendPbMessage(c2s);
	}

	public static void main(String[] args) {
		List<Integer> list = getBeadRefreshId("");
		System.out.println(list.size());
	}

}
