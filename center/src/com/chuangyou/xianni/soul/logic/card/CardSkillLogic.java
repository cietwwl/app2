package com.chuangyou.xianni.soul.logic.card;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.protocol.Protocol;

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
		int count = this.cardInfo.getRemainTime();
		if(count==0){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_PIECE_COMBO,"技能没有洗练次数");		
			return;
		}
		this.cardInfo.setRemainTime(count-1);
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

}
