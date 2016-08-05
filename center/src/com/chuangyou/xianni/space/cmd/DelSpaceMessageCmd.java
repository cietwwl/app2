package com.chuangyou.xianni.space.cmd;

import com.chuangyou.common.protobuf.pb.space.DelSpaceMessageReqProto.DelSpaceMessageReqMsg;
import com.chuangyou.common.protobuf.pb.space.DelSpaceMessageRespProto.DelSpaceMessageRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code=Protocol.C_REQ_SPACE_DEL_MSG,desc="删除贴子")
public class DelSpaceMessageCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		
		DelSpaceMessageReqMsg req = DelSpaceMessageReqMsg.parseFrom(packet.getBytes());
		int op = req.getOp();
		if(op == 1){
			player.getSpaceInventory().delMsg(req.getId());
		}else if(op == 2){
			player.getSpaceInventory().delPlayerMsg(req.getPlayerID());
		}else if(op == 3){
			player.getSpaceInventory().delAllMsg(false);
		}else if(op == 4){
			player.getSpaceInventory().delAllMsg(true);
		}
		
		DelSpaceMessageRespMsg.Builder resp = DelSpaceMessageRespMsg.newBuilder();
		resp.setOp(op);
		if(req.hasId()){			
			resp.setId(req.getId());
		}
		if(req.hasPlayerID()){
			resp.setPlayerId(resp.getPlayerId());
		}
		
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_SPACE_DEL_MSG,resp);
		player.sendPbMessage(pkg);
	}

}
