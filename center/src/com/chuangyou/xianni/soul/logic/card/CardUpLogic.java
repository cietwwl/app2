package com.chuangyou.xianni.soul.logic.card;

import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.soul.SoulCardInfo;
import com.chuangyou.xianni.protocol.Protocol;

public class CardUpLogic extends AbstractCardLogic {

	public CardUpLogic(int skillIndex) {
		super(skillIndex);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCardLogic() {
		// TODO Auto-generated method stub
		if (this.cardInfo.getIsPutOn() == SoulCardInfo.ON) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_PIECE_COMBO, "已经是穿上状态");
			return;
		}
		if (check() == false) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_PIECE_COMBO, "没有位置可穿");
			return;
		}
		
		upSoulCard();
		this.soulInfo.setOp(Option.Update);
		
		this.cardInfo.setIsPutOn(SoulCardInfo.ON);
		this.cardInfo.setOp(Option.Update);
		this.player.getSoulInventory().updateProperty();
		this.sendResultMsg();
	}

	private boolean check() {
		if (this.soulInfo.getCard1() == 0) {
			return true;
		}
		if (this.soulInfo.getCard2() == 0) {
			return true;
		}
		if (this.soulInfo.getCard3() == 0) {
			return true;
		}

		if (this.soulInfo.getCard4() == 0) {
			return true;
		}
		if (this.soulInfo.getCard5() == 0) {
			return true;
		}

		if (this.soulInfo.getCard6() == 0) {
			return true;
		}
		if (this.soulInfo.getCard7() == 0) {
			return true;
		}
		if (this.soulInfo.getCard8() == 0) {
			return true;
		}
		return false;

	}

	private void upSoulCard() {
		int cardId = this.cardInfo.getCardId();
		if (this.soulInfo.getCard1() == 0) {
			this.soulInfo.setCard1(cardId);
			return;
		}
		if (this.soulInfo.getCard2() == 0) {
			this.soulInfo.setCard2(cardId);
			return;
		}

		if (this.soulInfo.getCard3() == 0) {
			this.soulInfo.setCard3(cardId);
			return;
		}
		if (this.soulInfo.getCard4() == 0) {
			this.soulInfo.setCard4(cardId);
			return;
		}
		if (this.soulInfo.getCard5() == 0) {
			this.soulInfo.setCard5(cardId);
			return;
		}
		if (this.soulInfo.getCard6() == 0) {
			this.soulInfo.setCard6(cardId);
			return;
		}
		if (this.soulInfo.getCard7() == 0) {
			this.soulInfo.setCard7(cardId);
			return;
		}
		if (this.soulInfo.getCard8() == 0) {
			this.soulInfo.setCard8(cardId);
			return;
		}
	}

}
