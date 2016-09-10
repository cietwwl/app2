package com.chuangyou.xianni.soul.logic.make;

import java.util.Date;

import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.soul.SoulMake;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.soul.template.SoulTemplateMgr;

/**
 * 重新制作。重置一下状态
 * 
 * @author laofan
 *
 */
public class ReMakeLogic extends BaseSoulMakeLogic implements ISoulMakeLogic {

	public ReMakeLogic(int op, int index, GamePlayer player) {
		super(op, index, player);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doProcess() {
		// TODO Auto-generated method stub
		if (this.soulMake.getState() != SoulMake.STATE_QTE && this.soulMake.getState() != SoulMake.STATE_COMPLETE) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_MAKE, "状态不对:" + this.op);
			return;
		}

		Date now = new Date();
		if (now.getTime() - this.soulMake.getLastQteTime() < SoulTemplateMgr.QTE_CD) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_MAKE, "QTE_CD" + this.op);
			return;
		}

		this.soulMake.setState(SoulMake.STATE_INIT);
		this.soulMake.setItemId(0);
		this.soulMake.setQteIndex(0);
		this.soulMake.setKillNum(0);
		this.soulMake.setOp(Option.Update);

		this.sendResultMsg();
	}

}
