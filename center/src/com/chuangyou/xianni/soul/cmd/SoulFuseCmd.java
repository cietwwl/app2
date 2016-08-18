package com.chuangyou.xianni.soul.cmd;

import com.chuangyou.common.protobuf.pb.soul.SoulFuseReqProto.SoulFuseReqMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.soul.logic.fuse.CancelFuseSkillLogic;
import com.chuangyou.xianni.soul.logic.fuse.CommitFuseSkillLogic;
import com.chuangyou.xianni.soul.logic.fuse.FuseOpLogic;
import com.chuangyou.xianni.soul.logic.fuse.IFuseLogic;

/**
 * 融合处理
 * @author laofan
 *
 */
@Cmd(code=Protocol.C_REQ_SOUL_FUSE,desc="融合")
public class SoulFuseCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		SoulFuseReqMsg req = SoulFuseReqMsg.parseFrom(packet.getBytes());
		int index = req.getIndex();
		int op = req.getOp();
		int pos = req.getUseItemPos();
		int useItemId = req.getUseItemId();
		
		if(index<1 || index>4 ){
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SOUL_FUSE,"客户端传来的数据有误："+index);		
			return;
		}
		
		IFuseLogic logic = null;
		switch (op) {
		case 1:
			logic = new FuseOpLogic(op, player, index,pos,useItemId);			
			break;
		case 2:
			logic = new CommitFuseSkillLogic(op, player, index);
			break;
		case 3:
			logic = new CancelFuseSkillLogic(op, player, index);
			break;
		}
		
		if(logic!=null){
			logic.doProcess();
		}else{
			Log.error("不能处理的操作："+req.getOp()+"protcol:"+Protocol.C_REQ_SOUL_FUSE);
			return;
		}
	}
}
