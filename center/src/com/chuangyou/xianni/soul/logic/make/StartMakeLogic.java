package com.chuangyou.xianni.soul.logic.make;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.soul.FuseItemConfig;
import com.chuangyou.xianni.entity.soul.SoulMake;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.soul.template.SoulTemplateMgr;

public class StartMakeLogic extends BaseSoulMakeLogic implements ISoulMakeLogic {

	public StartMakeLogic(int op, int index, GamePlayer player) {
		super(op, index, player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doProcess() {
		// TODO Auto-generated method stub
		if (this.soulMake.getState() != SoulMake.STATE_QTE) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_MAKE, "状态不对:" + this.op);
			return;
		}

		
		String items = SystemConfigTemplateMgr.getMakeConfig()[this.soulMake.getQteIndex() - 1];
		String strs[] = items.split(",");
		Map<Integer, Integer> tempMap = new HashMap<>();
		for (String str : strs) {
			String it[] = str.split("\\*");
			if (it.length == 2) {
				int templateId = Integer.parseInt(it[0]);
				int count = Integer.parseInt(it[1]);
				if (player.getBagInventory().getItemCount(templateId) < count) {
					ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Use_Error, Protocol.C_REQ_SOUL_MAKE, " 数量不够：");
					return;
				}
				tempMap.put(templateId, count);
			}
		}

		Iterator<Entry<Integer, Integer>> it = tempMap.entrySet().iterator();
		while (it.hasNext()) {
			Entry<Integer, Integer> entry = it.next();
			if (!player.getBagInventory().removeItem(entry.getKey(), entry.getValue(), ItemRemoveType.SOUL)) {
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_MAKE, " 扣物品出错：");
				return;
			}
		}
		int itemId = getItemID();
		if (itemId == 0) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_MAKE, "失败：");
			return;
		}
		
		this.soulMake.setItemId(itemId);
		this.soulMake.setStartTime(new Date());
		this.soulMake.setKillNum(0);
		this.soulMake.setTotalTime(getTaskTotalTime());
		this.soulMake.setState(SoulMake.STATE_MAKE_ING);
		this.soulMake.setOp(Option.Update);

//		this.player.getSoulInventory().initListener();

		sendResultMsg();
	}

	/**
	 * 获取制作出来的物品ID 根据颜色和位置找到对应的物品ID
	 * 
	 * @return
	 */
	private int getItemID() {
		int index = this.soulMake.getMakeIndex();
		FuseItemConfig config = SoulTemplateMgr.getFuseItemConfigMap().get(index);
		if (config == null) {
			Log.error("FuseItemConfig配置表错误：" + index);
			return 0;
		}
		int color = getItemColor();
		if (color == 2) {
			return config.getNeedItem1();
		}
		if (color == 3) {
			return config.getNeedItem2();
		}
		if (color == 4) {
			return config.getNeedItem3();
		}
		if (color == 5) {
			return config.getNeedItem4();
		}
		return 0;
	}

	/**
	 * 获取任务总时间 任意随机(5--30分钟)
	 * 
	 * @return
	 */
	private int getTaskTotalTime() {
		int m = new Random().nextInt(25) + 5;
		return m * 60;
	}

	/**
	 * 获取制作出物品的颜色 根据熟练度查表来获取生成的物品的颜色
	 * 
	 * @return
	 */
	private int getItemColor() {
		return 2;
	}

}
