package com.chuangyou.xianni.soul.logic.make;


import com.chuangyou.xianni.bag.BaseItem;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.item.ItemAddType;
import com.chuangyou.xianni.entity.soul.SoulMake;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.protocol.Protocol;

public class CompleteMakeLogic extends BaseSoulMakeLogic implements ISoulMakeLogic {

	public CompleteMakeLogic(int op, int index, GamePlayer player) {
		super(op, index, player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doProcess() {
		// TODO Auto-generated method stub
		if (this.soulMake.getState() != SoulMake.STATE_MAKE_ING && this.soulMake.getState()!=SoulMake.STATE_TASK_ING) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_MAKE, "状态不对:" + this.op);
			return;
		}
		if (player.getBagInventory().getEmptyCount() < 1) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.BAG_IS_FULL, Protocol.C_REQ_SOUL_MAKE, "背包已满：" + this.op);
			return;
		}

		// 完成任务。
		// 1: 给物品
		int profic = this.soulInfo.getProficiency(index);
//		JEP jep = new JEP();
//		jep.addVariable("value", 12);
//		
//		try {
////			jep.parse(str);
//			double result =  (double) jep.evaluate(jep.parse(str));
//			System.out.println(result);
//		} catch (ParseException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		int count = profic * 1000 +10000 + this.soulMake.getKillNum() * 5000;
		System.out.println("count:"+count);
		BaseItem item = player.getBagInventory().addDynItem(this.soulMake.getItemId(), 1, ItemAddType.SOUL, false, count);
		if (item == null) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_MAKE, "添加物品失败" + this.op);
			return;
		}

		// 3：加熟练度
		this.soulInfo.setProficiency(index, profic + 5);
		this.soulInfo.setOp(Option.Update);
		
		player.notifyListeners(new ObjectEvent(this, index, EventNameType.SOUL_PRO));
		// 2:改变状态
		this.soulMake.setState(SoulMake.STATE_COMPLETE);
		// this.soulMake.setKillNum(0);
		// this.soulMake.setItemId(0);
		this.soulMake.setLastQteTime(0);
		// this.soulMake.setQteIndex(0);
		this.soulMake.setOp(Option.Update);

		this.player.getSoulInventory().removeListener();

		this.sendResultMsg();
	}

}
