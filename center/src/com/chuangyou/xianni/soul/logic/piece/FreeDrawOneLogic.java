package com.chuangyou.xianni.soul.logic.piece;

import com.chuangyou.common.protobuf.pb.soul.DrawCardRespProto.DrawCardRespMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.entity.soul.CardRateConfig;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.soul.logic.calc.weight.CardTypeWeight;
import com.chuangyou.xianni.soul.template.SoulTemplateMgr;

/**
 * 免费抽卡一次
 * @author laofan
 *
 */
public class FreeDrawOneLogic extends DrawCardLogic{

	
	public FreeDrawOneLogic(int type, GamePlayer player) {
		super(type, player);
		// TODO Auto-generated constructor stub
	}


	@Override
	protected CardRateConfig getRateConfig() {
		// TODO Auto-generated method stub
		return SoulTemplateMgr.getCardRateCofig().get(1);
	}


	@Override
	protected void sendResult() {
		// TODO Auto-generated method stub
		DrawCardRespMsg.Builder resp = getBaseResultMsg();
		resp.setTime(player.getBasePlayer().getPlayerTimeInfo().getPersonalLuckCardFreeTime());
		player.sendPbMessage(MessageUtil.buildMessage(Protocol.U_RESP_SOUL_DRAW_CARD,resp));
	}



	@Override
	protected boolean consumeMoney() {
		// TODO Auto-generated method stub
		int t = player.getBasePlayer().getPlayerTimeInfo().getPersonalLuckCardFreeTime();
		if(t>=SoulTemplateMgr.FREE_DRAW_CARD_TIME_DAY){
			Log.error("type:"+type+"playerId:"+player.getPlayerId()+"====>免费抽卡次数已经用完");
			return false;
		}
		return true;
	}


	@Override
	protected void addDrawTime() {
		// TODO Auto-generated method stub
		int t = player.getBasePlayer().getPlayerTimeInfo().getPersonalLuckCardFreeTime();
		player.getBasePlayer().getPlayerTimeInfo().setPersonalLuckCardFreeTime(t+1);
	}


	@Override
	protected int getCurDrawTime() {
		// TODO Auto-generated method stub
		return player.getBasePlayer().getPlayerTimeInfo().getPersonalLuckCardFreeTime();
	}


	@Override
	protected CardTypeWeight mustGetCardId() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
	
}
