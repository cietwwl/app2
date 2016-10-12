package com.chuangyou.xianni.soul.logic.make;

import java.util.Date;

import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.soul.SoulMake;
import com.chuangyou.xianni.player.GamePlayer;

/**
 * 接受制作任务
 * @author laofan
 *
 */
public class AcceptMakeTaskLogic extends BaseSoulMakeLogic implements ISoulMakeLogic{

	public AcceptMakeTaskLogic(int op, int index, GamePlayer player) {
		super(op, index, player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doProcess() {
		// TODO Auto-generated method stub
		this.player.getSoulInventory().initListener();
		this.soulMake.setState(SoulMake.STATE_TASK_ING);
		this.soulMake.setStartTime(new Date());
		this.soulMake.setOp(Option.Update);
		this.soulInfo.setOp(Option.Update);
		this.sendResultMsg();
	}
	
	

}
