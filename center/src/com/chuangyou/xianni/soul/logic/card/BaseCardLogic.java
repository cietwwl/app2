package com.chuangyou.xianni.soul.logic.card;

import com.chuangyou.common.protobuf.pb.soul.SoulCardOpRespProto.SoulCardOpRespMsg;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.soul.SoulCardConfig;
import com.chuangyou.xianni.entity.soul.SoulCardInfo;
import com.chuangyou.xianni.entity.soul.SoulCardPiece;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.soul.template.SoulTemplateMgr;

public abstract class BaseCardLogic implements ICardLogic{

	protected SoulCardInfo cardInfo;
	protected SoulCardPiece piece;
	protected SoulCardConfig cardConfig;
	protected GamePlayer player;
	protected int op;
	protected int skillIndex;
	
	
	
	public BaseCardLogic(int skillIndex) {
		super();
		this.skillIndex = skillIndex;
	}

	@Override
	public void doProcess(GamePlayer player, int cardId,int op) {
		// TODO Auto-generated method stub
		this.player = player;
		this.op = op;
		cardInfo = player.getSoulInventory().getCards().get(cardId);
		piece = player.getSoulInventory().getPieces().get(cardId);
		cardConfig = SoulTemplateMgr.getCardConfig(cardId);
		if(cardConfig == null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_PIECE_COMBO,"无效的卡："+cardId);		
			return;
		}
		doCardExe();
	}
	
	public abstract void doCardExe();
		
	protected void sendResultMsg(){
		SoulCardOpRespMsg.Builder resp = SoulCardOpRespMsg.newBuilder();
		resp.setOp(op);
		
		resp.setCard(cardInfo.getMsg());
		if(piece!=null){
			resp.setCardPiece(piece.getMsg());
		}
		resp.addAllAtts(player.getSoulInventory().getList());
		
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_SOUL_PIECE_COMBO,resp);
		player.sendPbMessage(pkg);
		
	}
}
