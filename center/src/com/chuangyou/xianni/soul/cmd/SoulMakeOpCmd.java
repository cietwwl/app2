package com.chuangyou.xianni.soul.cmd;

import com.chuangyou.common.protobuf.pb.soul.MakeSoulReqProto.MakeSoulReqMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.soul.logic.make.CompleteMakeLogic;
import com.chuangyou.xianni.soul.logic.make.ISoulMakeLogic;
import com.chuangyou.xianni.soul.logic.make.QTEMakeLogic;
import com.chuangyou.xianni.soul.logic.make.StartMakeLogic;

@Cmd(code=Protocol.C_REQ_SOUL_MAKE,desc="材料制作")
public class SoulMakeOpCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		MakeSoulReqMsg req = MakeSoulReqMsg.parseFrom(packet.getBytes());
		int op = req.getOp();
		int index = 0;
		int qte = 0;
		if(req.hasIndex()){	
			index = req.getIndex();
		}
		if(req.hasQte()){
			qte = req.getQte();
		}
		
		
		ISoulMakeLogic logic = null;
		switch (op) {
		case 1:
			logic = new QTEMakeLogic(op, player, index, qte);
			break;
		case 2:
			logic = new StartMakeLogic(op, player);
			break;
		case 3:
			logic = new CompleteMakeLogic(op, player);
			break;		
		}
		
		if(logic!=null){
			logic.doProcess();
		}else{
			Log.error("不能处理的操作："+req.getOp()+"protcol:"+Protocol.C_REQ_SOUL_MAKE);
			return;
		}
		
		
	}

}
