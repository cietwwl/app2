package com.chuangyou.xianni.soul.logic.make;

import java.util.Date;

import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.soul.SoulMake;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.soul.template.SoulTemplateMgr;

public class QTEMakeLogic extends BaseSoulMakeLogic implements ISoulMakeLogic {

	protected int index;
	protected int qte;

	public QTEMakeLogic(int op, GamePlayer player, int index, int qte) {
		super(op, player);
		this.index = index;
		this.qte = qte;
	}


	@Override
	public void doProcess() {
		// TODO Auto-generated method stub
		Date now = new Date();
		if(now.getTime() - this.soulMake.getLastQteTime()<SoulTemplateMgr.QTE_CD){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_MAKE,"QTE_CD");		
			return;	
		}
		if(index<1 || index>4 ||qte<1||qte>3){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_MAKE,"数据不合法");		
			return;
		}
		if(this.soulMake.getState() == SoulMake.STATE_ING){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_MAKE,"状态不对:"+this.op);		
			return;
		}
		
		this.soulMake.setState(SoulMake.STATE_QTE);
		this.soulMake.setLastQteTime(now.getTime());
		this.soulMake.setQteIndex(qte);
		this.soulMake.setItemId(this.index);
		this.soulMake.setOp(Option.Update);
		
		this.sendResultMsg();
		
	}

}
