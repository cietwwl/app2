package com.chuangyou.xianni.soul.logic.make;

import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.soul.SoulMake;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.protocol.Protocol;

public class CompleteMakeLogic extends BaseSoulMakeLogic implements ISoulMakeLogic {

	public CompleteMakeLogic(int op, GamePlayer player) {
		super(op, player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doProcess() {
		// TODO Auto-generated method stub
		if(this.soulMake.getState() != SoulMake.STATE_ING){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_MAKE,"状态不对:"+this.op);		
			return;
		}
		
		
		
	}

}
