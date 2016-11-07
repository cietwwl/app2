package com.chuangyou.xianni.soul.logic.piece;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.item.ItemRemoveType;
import com.chuangyou.xianni.entity.soul.CardRateConfig;
import com.chuangyou.xianni.player.GamePlayer;

/**
 * 花钱十连抽
 * @author laofan
 *
 */
public class MoneyDrawTenLogic extends MoneyDrawOneLogic {

	public MoneyDrawTenLogic(int type, GamePlayer player) {
		super(type, player);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected boolean consumeMoney() {
		// TODO Auto-generated method stub
		CardRateConfig config = this.getRateConfig();
		int count = (config.getTenSpend()*config.getDiscount())/100;
		if(!player.getBasePlayer().consumeCommonCash(count,ItemRemoveType.DRAW_CARD)){
			Log.error("MoneyDrawTenLogic金钱不够：playerId"+player.getPlayerId()+"===>config:"+config.toString());
			return false;
		}	
		return true;
	}

	@Override
	public void process() {
		// TODO Auto-generated method stub
		if(consumeMoney()){
			for (int i = 0; i < 10; i++) {
				if(!randomAndAddCard()){
					return;
				}
			}
			this.sendResult();
		}
	}
	
	

}
