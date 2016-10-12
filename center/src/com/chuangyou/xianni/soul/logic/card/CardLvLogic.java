package com.chuangyou.xianni.soul.logic.card;

import java.util.Random;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.soul.CardLvConfig;
import com.chuangyou.xianni.event.EventNameType;
import com.chuangyou.xianni.event.ObjectEvent;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.soul.template.SoulTemplateMgr;

/**
 *  升级
 * @author laofan
 *
 */
public class CardLvLogic extends AbstractCardLogic {

	public CardLvLogic(int skillIndex) {
		super(skillIndex);
		// TODO Auto-generated constructor stub
	}


	private CardLvConfig cardLvConfig; 
	@Override
	public void doCardLogic() {
		// TODO Auto-generated method stub
		cardLvConfig = SoulTemplateMgr.getCardLvConfig(this.cardInfo.getLv());
		if(cardLvConfig == null){
			Log.error("cardLvConfig配置表有问题:"+this.cardInfo.getLv());
			return;
		}
		if(this.cardInfo.getLv()>=SoulTemplateMgr.MAX_CARD_LV){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_PIECE_COMBO," 最大等级。不能再升：op"+this.op);		
			return;	
		}
		
		int count = player.getBagInventory().getItemCount(cardLvConfig.getSpendItem());
		if(count == 0){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Prop_Use_Error, Protocol.C_REQ_SOUL_PIECE_COMBO," 数量不够：op"+this.op);		
			return;
		}
		
		//扣物品
		if(player.getBagInventory().removeItem(cardLvConfig.getSpendItem(), 1, ItemRemoveType.SOUL)){
			
			int lv = this.cardInfo.getLv();
			if(this.cardInfo.getLv()%10==0){
				this.cardInfo.setExp(this.cardInfo.getExp()+new Random().nextInt(cardLvConfig.getAddExp()));
			}else{				
				this.cardInfo.setExp(this.cardInfo.getExp()+cardLvConfig.getAddExp());
			}	
			upLv();
			this.cardInfo.setOp(Option.Update);
			
			if(lv<this.cardInfo.getLv()){
				player.getSoulInventory().updateProperty();
				player.notifyListeners(new ObjectEvent(this, this.cardInfo, EventNameType.SOUL_LV));
			}
			
			this.sendResultMsg();
		}else{
			Log.error("删除道具出错",new Exception());
		}
	}
	
	
	private void upLv(){
		int lv = this.cardInfo.getLv();
		long nextExp = SoulTemplateMgr.getCardLvConfig(lv).getExp();
		while(this.cardInfo.getExp()>nextExp){
			//扣经验
			if(lv%10==9 || lv%10 == 0){
				this.cardInfo.setExp(0);
			}else{
				this.cardInfo.setExp(this.cardInfo.getExp() - nextExp);
			}
			//升等级
			this.cardInfo.setLv(this.cardInfo.getLv()+1);
			lv = this.cardInfo.getLv();
			if(lv>=SoulTemplateMgr.MAX_CARD_LV){
				break;
			}
			nextExp = SoulTemplateMgr.getCardLvConfig(lv).getExp();
		}
	}

}
