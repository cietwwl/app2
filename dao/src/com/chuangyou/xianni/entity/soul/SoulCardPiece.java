package com.chuangyou.xianni.entity.soul;

import com.chuangyou.common.protobuf.pb.soul.CardPieceInfoProto.CardPieceInfoMsg;
import com.chuangyou.xianni.entity.DataObject;

public class SoulCardPiece extends DataObject {
	private long playerId;
	private int cardId;
	private int count;
	
	public CardPieceInfoMsg.Builder getMsg(){
		CardPieceInfoMsg.Builder msg = CardPieceInfoMsg.newBuilder();
		msg.setCardId(cardId);
		msg.setCount(count);
		return msg;
	}
	
	
	public long getPlayerId() {
		return playerId;
	}
	public void setPlayerId(long playerId) {
		this.playerId = playerId;
	}
	public int getCardId() {
		return cardId;
	}
	public void setCardId(int cardId) {
		this.cardId = cardId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
}
