package com.chuangyou.xianni.state.cmd;

import com.chuangyou.common.protobuf.pb.state.StateOpReqProto.StateOpReqMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.state.logic.EnterCampaignLogic;

@Cmd(code=Protocol.C_REQ_STATE_OP,desc="操作")
public class StateOpCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		StateOpReqMsg msg = StateOpReqMsg.parseFrom(packet.getBytes());
		int op = msg.getOp();
		if(op==1){ //进入副本
			new EnterCampaignLogic().enter(player);
		}else if(op == 2){  //加Buff
			
		}
		
	}
	


}
