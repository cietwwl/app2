package com.chuangyou.xianni.soul.cmd;

import com.chuangyou.common.protobuf.pb.soul.DrawCardReqProto.DrawCardReqMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.soul.logic.piece.FreeDrawOneLogic;
import com.chuangyou.xianni.soul.logic.piece.FreeDrawTenLogic;
import com.chuangyou.xianni.soul.logic.piece.MoneyDrawOneLogic;
import com.chuangyou.xianni.soul.logic.piece.MoneyDrawTenLogic;

@Cmd(code=Protocol.C_REQ_SOUL_DRAW_CARD,desc="抽卡")
public class DrawCardPieceCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		DrawCardReqMsg msg = DrawCardReqMsg.parseFrom(packet.getBytes());
		int type = msg.getType();
		if(type == 1){
			new FreeDrawOneLogic(type,player).process();
		}else if(type==2){
			new FreeDrawTenLogic(type, player).process();
		}else if(type == 3){
			new MoneyDrawOneLogic(type, player).process();
		}else if(type == 4){
			new MoneyDrawTenLogic(type, player).process();
		}
	}
		

}
