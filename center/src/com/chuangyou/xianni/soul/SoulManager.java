package com.chuangyou.xianni.soul;

import com.chuangyou.xianni.entity.Option;
import com.chuangyou.xianni.entity.soul.SoulCardPiece;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.player.PlayerState;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.soul.template.SoulTemplateMgr;

public class SoulManager {
	
	/**
	 * 添加碎片
	 * @param cardId
	 * @param player
	 * @param count
	 */
	public static boolean addCardPiece(int cardId,GamePlayer player,int count){
		if(player.getPlayerState() == PlayerState.OFFLINE)return false;
		if(SoulTemplateMgr.getCardConfig(cardId)==null)return false;
		SoulCardPiece piece = player.getSoulInventory().getPieces().get(cardId);
		if(piece == null){
			piece = new SoulCardPiece();
			piece.setCardId(cardId);
			piece.setPlayerId(player.getPlayerId());
			player.getSoulInventory().addCardPiece(piece);
		}
		piece.setCount(piece.getCount()+count);
		piece.setOp(Option.Update);
		
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_NOTIFY_CARDPIECE,piece.getMsg());
		player.sendPbMessage(pkg);
		return true;
	}
}
