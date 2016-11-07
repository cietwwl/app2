package com.chuangyou.xianni.soul.logic.piece;

import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.soul.template.SoulTemplateMgr;

/**
 * 免费抽卡10次
 * @author laofan
 *
 */
public class FreeDrawTenLogic extends FreeDrawOneLogic {

	public FreeDrawTenLogic(int type, GamePlayer player) {
		super(type, player);
		// TODO Auto-generated constructor stub
	}

	
	
	@Override
	protected boolean consumeMoney() {
		// TODO Auto-generated method stub
		int t = player.getBasePlayer().getPlayerTimeInfo().getPersonalLuckCardFreeTime();
		if(t+10>SoulTemplateMgr.FREE_DRAW_CARD_TIME_DAY){
			Log.error("type:"+type+"playerId:"+player.getPlayerId()+"====>免费抽卡次数已经用完");
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
