package com.chuangyou.xianni.space.cmd;

import com.chuangyou.common.protobuf.pb.space.OpSpaceReqProto.OpSpaceReqMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.space.SpaceInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.space.logic.SpaceLogicFactory;
import com.chuangyou.xianni.space.logic.op.ISpaceOpLogic;
import com.chuangyou.xianni.word.WorldMgr;

@Cmd(code=Protocol.C_REQ_SPACE_ACTION,desc="操作空间")
public class OpSpaceCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		OpSpaceReqMsg req = OpSpaceReqMsg.parseFrom(packet.getBytes());
		if(req.getPlayerId() == player.getPlayerId()){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SPACE_ACTION,"不能操作自己的空间");
			return;
		}
		GamePlayer reqPlayer = WorldMgr.getPlayer(req.getPlayerId());
		if(reqPlayer ==null){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_GET_SPACE_INFO,"无效的playerID"+req.getPlayerId());		
			return;
		}
		SpaceInfo info = reqPlayer.getSpaceInventory().getSpaceInfo();
		int op = req.getOp();
		ISpaceOpLogic logic = SpaceLogicFactory.getOpLogic(op);
		if(logic==null){
			Log.error("不能处理的操作："+req.getOp()+"protcol:"+Protocol.C_REQ_ENDIT_INFO);
			return;
		}
		logic.doProcess(player, reqPlayer, info, op);
	}
	
	

}
