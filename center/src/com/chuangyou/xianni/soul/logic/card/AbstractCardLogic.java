package com.chuangyou.xianni.soul.logic.card;

import java.util.Random;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.soul.SoulInfo;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.soul.template.SoulTemplateMgr;

public abstract class AbstractCardLogic extends BaseCardLogic {

	public AbstractCardLogic(int skillIndex) {
		super(skillIndex);
		// TODO Auto-generated constructor stub
	}

	protected SoulInfo soulInfo;
	
	@Override
	public void doCardExe() {
		// TODO Auto-generated method stub
		if(this.cardInfo == null ){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_PIECE_COMBO,"无效的卡：");		
			return;
		}
		this.soulInfo = player.getSoulInventory().getSoulInfo();
		doCardLogic();
	}
	
	public abstract void doCardLogic();
	

	protected int getRandomSkillID(){
		int size = SoulTemplateMgr.skillPool.size();
		if(size<4){
			Log.error("卡牌技能池中技能数量不足===严重错误");
			return 0;
		}
		int count = 0;
		while(count<500){
			int index = new Random().nextInt(size);
			int skillID = SoulTemplateMgr.skillPool.get(index);
			if(skillID>0){
				if(skillID!=this.cardInfo.getSkill2() && skillID!=this.cardInfo.getSkill3() && skillID!=this.cardInfo.getSkill4()){
					return skillID;
				}
			}else{
				return 0;
			}
			count ++;
		}
		return 0;
	}
	
}
