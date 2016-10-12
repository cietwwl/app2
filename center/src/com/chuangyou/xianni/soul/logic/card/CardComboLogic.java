package com.chuangyou.xianni.soul.logic.card;

import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.item.BagType;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.soul.SoulCardInfo;
import com.chuangyou.xianni.protocol.Protocol;

/**
 *  卡牌合成
 * @author laofan
 *
 */
public class CardComboLogic extends BaseCardLogic {

	public CardComboLogic(int skillIndex) {
		super(skillIndex);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCardExe() {
		// TODO Auto-generated method stub
		if(this.cardInfo!=null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_PIECE_COMBO,"已经有该种卡啦"+cardInfo.getCardId());		
			return;
		}
//		if(this.piece.getCount()<this.cardConfig.getNeedClip()){
//			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_PIECE_COMBO,"数量不够："+cardConfig.getId());		
//			return;
//		}
		
		//消耗碎片
		if(!player.getBagInventory().removeItem(BagType.VirtualValue, this.cardId, cardConfig.getNeedClip(), ItemRemoveType.CARD_PIECE)){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_PIECE_COMBO,"数量不够："+cardConfig.getId());		
			return;
		}
		
//		piece.setCount(piece.getCount() - cardConfig.getNeedClip());
//		piece.setOp(Option.Update);
		
		this.cardInfo = new SoulCardInfo();
		this.cardInfo.setPlayerId(player.getPlayerId());
		this.cardInfo.setCardId(this.cardId);
		this.cardInfo.setSkill1(this.cardConfig.getSkill());
		player.getSoulInventory().addSoulCard(cardInfo);
		
		player.getSoulInventory().updateProperty();
		
		sendResultMsg();
	}

}
