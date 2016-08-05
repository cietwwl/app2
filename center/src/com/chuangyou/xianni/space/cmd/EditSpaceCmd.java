package com.chuangyou.xianni.space.cmd;

import com.chuangyou.common.protobuf.pb.space.EditSpaceInfoReqProto.EditSpaceInfoReqMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.space.logic.SpaceLogicFactory;
import com.chuangyou.xianni.space.logic.edit.ISpaceLogic;

@Cmd(code=Protocol.C_REQ_ENDIT_INFO,desc="编辑个人空间")
public class EditSpaceCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		EditSpaceInfoReqMsg req  = EditSpaceInfoReqMsg.parseFrom(packet.getBytes());
		ISpaceLogic logic = SpaceLogicFactory.getSpaceLogic(req.getOp());
		if(logic==null){
			Log.error("不能处理的操作："+req.getOp()+"protcol:"+Protocol.C_REQ_ENDIT_INFO);
			return;
		}
		logic.doProcess(player, req);	
	}
	
	

}
