package com.chuangyou.xianni.soul.logic.make;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.common.template.SystemConfigTemplateMgr;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.soul.SoulMake;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.protocol.Protocol;

public class StartMakeLogic extends BaseSoulMakeLogic implements ISoulMakeLogic{

	private final int HEAD = 9010;

	public StartMakeLogic(int op, GamePlayer player) {
		super(op, player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doProcess() {
		// TODO Auto-generated method stub
		if(this.soulMake.getState()!=SoulMake.STATE_QTE){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_MAKE,"状态不对:"+this.op);		
			return;
		}
		
		String items = SystemConfigTemplateMgr.getMakeConfig()[this.soulMake.getQteIndex()];
		String strs[] = items.split(",");
		Map<Integer, Integer> tempMap = new HashMap<>();
		for (String str : strs) {
			String it[] = str.split("*");
			if(it.length == 2){
				int templateId = Integer.parseInt(it[0]);
				int count = Integer.parseInt(it[1]);
				if(player.getBagInventory().getItemCount(templateId)<count){
					ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Use_Error, Protocol.C_REQ_SOUL_MAKE," 数量不够：");		
					return;
				}
				tempMap.put(templateId, count);
			}
		}
		
		Iterator<Entry<Integer, Integer>> it = tempMap.entrySet().iterator();
		while(it.hasNext()){
			Entry<Integer, Integer> entry = it.next();
			if(!player.getBagInventory().removeItem(entry.getKey(), entry.getValue(), ItemRemoveType.SOUL)){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_MAKE," 扣物品出错：");		
				return;
			}
		}
		
		int itemId = HEAD + this.soulMake.getItemId();
		itemId = itemId*1000 + this.soulMake.getQteIndex();
		this.soulMake.setItemId(itemId);
		this.soulMake.setStartTime(new Date());
		this.soulMake.setKillNum(0);
		this.soulMake.setTotalTime(this.soulMake.getQteIndex()*60*5);
		this.soulMake.setState(SoulMake.STATE_ING);
		this.soulMake.setOp(Option.Update);
		
		this.player.getSoulInventory().initListener();
		
		sendResultMsg();
	}

}
