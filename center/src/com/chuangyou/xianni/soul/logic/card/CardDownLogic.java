package com.chuangyou.xianni.soul.logic.card;

import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.soul.SoulCardInfo;
import com.chuangyou.xianni.protocol.Protocol;

public class CardDownLogic extends AbstractCardLogic {

	public CardDownLogic(int skillIndex) {
		super(skillIndex);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCardLogic() {
		// TODO Auto-generated method stub
		if(this.cardInfo.getIsPutOn() == SoulCardInfo.OFF){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_PIECE_COMBO,"已经是脱下状态");		
			return;
		}
		
		if(check(this.cardInfo.getCardId()) == false){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_PIECE_COMBO,"身上没这个卡");		
			return;
		}
		
		downSoulCard();
		this.soulInfo.setOp(Option.Update);
		this.cardInfo.setIsPutOn(SoulCardInfo.OFF);
		this.cardInfo.setOp(Option.Update);
		this.player.getSoulInventory().updateProperty();
		this.sendResultMsg();
	}
	
	private boolean check(int cardId){
		if(this.soulInfo.getCard1() == cardId){
			return true;
		}
		if(this.soulInfo.getCard2() == cardId){
			return true;
		}
		if(this.soulInfo.getCard3() == cardId){
			return true;
		}
		
		if(this.soulInfo.getCard4() == cardId){
			return true;
		}
		if(this.soulInfo.getCard5() == cardId){
			return true;
		}
		
		if(this.soulInfo.getCard6() == cardId){
			return true;
		}
		if(this.soulInfo.getCard7() == cardId){
			return true;
		}
		if(this.soulInfo.getCard8() == cardId){
			return true;
		}
		return false;
		
	}
	
	
	private void downSoulCard(){
		int cardId = this.cardInfo.getCardId();
		if(this.soulInfo.getCard1() == cardId){
			this.soulInfo.setCard1(0);
		}
		if(this.soulInfo.getCard2() == cardId){
			this.soulInfo.setCard2(0);
		}
		
		if(this.soulInfo.getCard3() == cardId){
			this.soulInfo.setCard3(0);
		}
		if(this.soulInfo.getCard4() == cardId){
			this.soulInfo.setCard4(0);
		}
		if(this.soulInfo.getCard5() == cardId){
			this.soulInfo.setCard5(0);
		}
		if(this.soulInfo.getCard6() == cardId){
			this.soulInfo.setCard6(0);
		}
		if(this.soulInfo.getCard7() == cardId){
			this.soulInfo.setCard7(0);
		}
		if(this.soulInfo.getCard8() == cardId){
			this.soulInfo.setCard8(0);
		}
	}

	
}
