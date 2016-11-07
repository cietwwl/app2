package com.chuangyou.xianni.soul.logic.piece;

import com.chuangyou.common.protobuf.pb.soul.GetSoulLuckInfoRespProto.GetSoulLuckInfoRespMsg;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.protocol.Protocol;

/**
 * 卡牌碎片相关逻辑
 * @author laofan
 *
 */
public class CardPieceLogic {

	/**
	 * 发送抽卡信息
	 * @param player
	 */
	public void sendCardLuckInfo(GamePlayer player){
		GetSoulLuckInfoRespMsg.Builder resp = GetSoulLuckInfoRespMsg.newBuilder();
		resp.setFreeTime(player.getBasePlayer().getPlayerTimeInfo().getPersonalLuckCardFreeTime());
		resp.setMoneyTime(player.getBasePlayer().getPlayerTimeInfo().getPersonalLuckCardMoneyTime());
		
		player.sendPbMessage(MessageUtil.buildMessage(Protocol.U_RESP_SOUL_GET_LUCK_INFO,resp));
		
	}
	
	
	
	
}
