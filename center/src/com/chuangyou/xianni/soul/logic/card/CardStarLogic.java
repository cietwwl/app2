package com.chuangyou.xianni.soul.logic.card;

import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.item.BagType;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.soul.SoulCardInfo;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.soul.template.SoulTemplateMgr;

/**
 * 升星
 * @author laofan
 *
 */
public class CardStarLogic extends AbstractCardLogic {

	public CardStarLogic(int skillIndex) {
		super(skillIndex);
		// TODO Auto-generated constructor stub
	}


//	private final int MAX_STAR = 6;
	
	@Override
	public void doCardLogic() {
		// TODO Auto-generated method stub
		int pieceCount = player.getBagInventory().getItemCount(BagType.VirtualValue,this.cardId);
		if(pieceCount == 0){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_PIECE_COMBO,"无碎片");		
			return;
		}

		int needClip = SoulTemplateMgr.getCardStarConfig(this.cardInfo.getStar()+1).getSpendNum();
		if(pieceCount < needClip){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_PIECE_COMBO,"碎片数量不够");		
			return;
		}
		if(this.cardInfo.getStar()>=this.cardConfig.getMaxStar()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_PIECE_COMBO,"星级不够高于品质");		
			return;
		}
		
		//TODO 扣碎片  BUG????????? 
		if(!player.getBagInventory().removeItem(BagType.VirtualValue, this.cardId, needClip, ItemRemoveType.CARD_PIECE)){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_PIECE_COMBO,"碎片数量不够");		
			return;
		}
		
		//加星
		this.cardInfo.setStar(cardInfo.getStar()+1);
		
		player.notifyListeners(new ObjectEvent(this, cardInfo, EventNameType.SOUL_STAR));
		
		//加技能
		addSkill(cardInfo);
		this.cardInfo.setOp(Option.Update);
		//更新属性
		player.getSoulInventory().updateProperty();
		//返回
		sendResultMsg();
	}
	
	
	/**
	 * 添加技能
	 * @param cardInfo
	 */
	private void addSkill(SoulCardInfo cardInfo){
		if(cardInfo.getStar()<=3){	
			int skillID = this.getRandomSkillID();
			if(skillID!=0){
				if(cardInfo.getStar()==1){
					this.cardInfo.setSkill2(skillID);
				}
				if(cardInfo.getStar()==2){
					this.cardInfo.setSkill3(skillID);
				}
				if(cardInfo.getStar()==3){
					this.cardInfo.setSkill4(skillID);
				}
			}
		}else{
			this.cardInfo.setRemainTime(this.cardInfo.getRemainTime()+1);
		}
	}
	
	
	

}
