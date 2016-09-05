package com.chuangyou.xianni.soul.logic.card;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.soul.template.SoulTemplateMgr;

public class CardSkillLogic extends AbstractCardLogic {

	public CardSkillLogic(int skillIndex) {
		super(skillIndex);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void doCardLogic() {
		// TODO Auto-generated method stub
		if(this.skillIndex<2 || this.skillIndex>4){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_PIECE_COMBO,"技能位置不对");		
			return;
		}
		
		int needItemId = SoulTemplateMgr.getCardSkillCofig(getOldSkillID()).getId();
		
		int count = this.cardInfo.getRemainTime()+player.getBagInventory().getItemCount(needItemId);
		if(count==0){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_PIECE_COMBO,"技能没有洗练次数+道具也没有");		
			return;
		}
		if(this.cardInfo.getRemainTime()>0){			
			this.cardInfo.setRemainTime(count-1);
		}else{
			if(!player.getBagInventory().removeItem(needItemId, 1, ItemRemoveType.SOUL)){
				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_PIECE_COMBO,"洗炼技能扣除物品时出错");		
				return;
			}
		}
		int skillId = this.getRandomSkillID();
		if(skillId>0){
			if(this.skillIndex == 2){
				this.cardInfo.setSkill2(skillId);
			}
			if(this.skillIndex == 3){
				this.cardInfo.setSkill3(skillId);
			}
			if(this.skillIndex == 4){
				this.cardInfo.setSkill4(skillId);
			}
			this.sendResultMsg();
		}else{
			Log.error("随机技能ID出错");
		}
		
	}
	
	private int getOldSkillID(){
		if(this.skillIndex == 2){
			return this.cardInfo.getSkill2();
		}
		if(this.skillIndex == 3){
			return this.cardInfo.getSkill3();
		}
		if(this.skillIndex == 4){
			return this.cardInfo.getSkill4();
		}
		return 0;
	}

}
