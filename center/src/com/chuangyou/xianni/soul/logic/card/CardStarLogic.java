package com.chuangyou.xianni.soul.logic.card;

import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.soul.SoulCardInfo;
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
		if(this.piece == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_PIECE_COMBO,"无碎片");		
			return;
		}
//		if(this.cardInfo.getStar() == MAX_STAR){
//			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_PIECE_COMBO,"星级已满");		
//			return;
//		}
		int needClip = SoulTemplateMgr.getCardStarConfig(this.cardInfo.getStar()+1).getSpendNum();
		if(this.piece.getCount() < needClip){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_PIECE_COMBO,"碎片数量不够");		
			return;
		}
		if(this.cardInfo.getStar()>=this.cardConfig.getMaxStar()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_PIECE_COMBO,"星级不够高于品质");		
			return;
		}
		
		//TODO 扣碎片  BUG????????? 
		this.piece.setCardId(this.piece.getCount()-needClip);
		this.piece.setOp(Option.Update);
		//加星
		this.cardInfo.setStar(cardInfo.getStar()+1);
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
