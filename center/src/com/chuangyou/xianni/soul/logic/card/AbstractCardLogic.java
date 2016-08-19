package com.chuangyou.xianni.soul.logic.card;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.soul.CardSkillConfig;
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
		if (this.cardInfo == null) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_PIECE_COMBO, "无效的卡：");
			return;
		}
		this.soulInfo = player.getSoulInventory().getSoulInfo();
		doCardLogic();
	}

	public abstract void doCardLogic();

	
	protected int getRandomSkillID() {
		int size = SoulTemplateMgr.skillPool.size();
		if (size < 4) {
			Log.error("卡牌技能池中技能数量不足===严重错误");
			return 0;
		}

		List<CardSkillConfig> list = new ArrayList<>();
		int count = 0;
		for (CardSkillConfig info : SoulTemplateMgr.skillPool) {
			int skillID = info.getId();
			if (skillID == 0)
				continue;
			if (skillID != this.cardInfo.getSkill2() && skillID != this.cardInfo.getSkill3() && skillID != this.cardInfo.getSkill4()) {
				list.add(info);
				count = count + info.getWeight();
			}
		}
		if (count == 0)
			return 0;
		int random = new Random().nextInt(count);
		int indexCount = 0;
		for (CardSkillConfig cardSkillConfig : list) {
			if(indexCount+cardSkillConfig.getWeight()>random){
				return cardSkillConfig.getId();
			}else{
				indexCount+=cardSkillConfig.getWeight();
			}
		}
		return 0;
	}

}
