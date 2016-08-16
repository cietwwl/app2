package com.chuangyou.xianni.soul.cmd;

import com.chuangyou.common.protobuf.pb.soul.SoulCardOpReqProto.SoulCardOpReqMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.soul.logic.SoulCardFactory;
import com.chuangyou.xianni.soul.logic.card.ICardLogic;

@Cmd(code=Protocol.C_REQ_SOUL_PIECE_COMBO,desc="针对卡牌的操作")
public class SoulCardOpCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		SoulCardOpReqMsg req = SoulCardOpReqMsg.parseFrom(packet.getBytes());
		int op = req.getOp();
		int cardId = req.getCardId();
		int skillId = req.getSkillIndex();
		ICardLogic logic = SoulCardFactory.getCardLogic(op,skillId);
		if(logic!=null){
			logic.doProcess(player, cardId,op);
		}else{
			Log.error("不能处理的操作："+req.getOp()+"protcol:"+Protocol.C_REQ_SOUL_PIECE_COMBO);
			return;
		}
	}

}
