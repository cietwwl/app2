package com.chuangyou.xianni.soul.logic.piece;

import com.chuangyou.common.protobuf.pb.soul.DrawCardRespProto.DrawCardRespMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.soul.CardRateConfig;
import com.chuangyou.xianni.entity.soul.SoulCardConfig;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.soul.logic.calc.weight.CardTypeWeight;
import com.chuangyou.xianni.soul.template.SoulTemplateMgr;

/**
 * 花钱抽卡一次
 * @author laofan
 *
 */
public class MoneyDrawOneLogic extends DrawCardLogic{

	public MoneyDrawOneLogic(int type, GamePlayer player) {
		super(type, player);
		// TODO Auto-generated constructor stub
	}

	
	@Override
	protected CardRateConfig getRateConfig() {
		// TODO Auto-generated method stub
		return SoulTemplateMgr.getCardRateCofig().get(2);
	}

	@Override
	protected boolean consumeMoney() {
		// TODO Auto-generated method stub
		
		CardRateConfig config = this.getRateConfig();
		int count = (config.getOneSpend()*config.getDiscount())/100;
		if(!player.getBasePlayer().consumeCommonCash(count,ItemRemoveType.DRAW_CARD)){
			Log.error("金钱不够：playerId"+player.getPlayerId()+"===>config:"+config.toString());
			return false;
		}
		
		return true;
	}

	@Override
	protected void sendResult() {
		// TODO Auto-generated method stub
		DrawCardRespMsg.Builder resp = getBaseResultMsg();
		resp.setTime(player.getBasePlayer().getPlayerTimeInfo().getPersonalLuckCardMoneyTime());
		player.sendPbMessage(MessageUtil.buildMessage(Protocol.U_RESP_SOUL_DRAW_CARD,resp));
	}


	@Override
	protected void addDrawTime() {
		// TODO Auto-generated method stub
		int t = player.getBasePlayer().getPlayerTimeInfo().getPersonalLuckCardMoneyTime();
		player.getBasePlayer().getPlayerTimeInfo().setPersonalLuckCardMoneyTime(t+1);
	}


	@Override
	protected int getCurDrawTime() {
		// TODO Auto-generated method stub
		return player.getBasePlayer().getPlayerTimeInfo().getPersonalLuckCardMoneyTime();
	}


	/**
	 * 花钱抽卡每10次必出紫卡
	 */
	@Override
	protected CardTypeWeight mustGetCardId() {
		// TODO Auto-generated method stub
		if(this.getCurDrawTime()%10==0){
			CardTypeWeight cardTypeWeight = getRandomCard(4);
			if(cardTypeWeight!=null){
				SoulCardConfig config = SoulTemplateMgr.getCardConfig(cardTypeWeight.getCardId());
				if(config!=null){
					cardTypeWeight.setNum(config.getNeedClip());
					return cardTypeWeight;
				}
			}
		}
		return null;
	}

}
