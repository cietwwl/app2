package com.chuangyou.xianni.space.cmd;

import com.chuangyou.common.protobuf.pb.space.SetSpaceCollectionReqProto.SetSpaceCollectionReqMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.entity.space.SpaceInfo;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;
import com.chuangyou.xianni.space.logic.SpaceLogicFactory;
import com.chuangyou.xianni.space.logic.collection.ISpaceCollectionLogic;

@Cmd(code=Protocol.C_REQ_SPACE_SET_COLLECTION,desc="设置收藏")
public class SetSapceCollectionCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		SetSpaceCollectionReqMsg req = SetSpaceCollectionReqMsg.parseFrom(packet.getBytes());
		
		int op = req.getOp();
		SpaceInfo info = player.getSpaceInventory().getSpaceInfo();
		
		ISpaceCollectionLogic logic = SpaceLogicFactory.getCollectionLogic(op);
		if(logic==null){
			Log.error("不能处理的操作："+req.getOp()+"protcol:"+Protocol.C_REQ_SPACE_SET_COLLECTION);
			return;
		}
		logic.doProcess(player, info, op, req);
		
//		if(op == 1 || op == 2){
//			SpaceMessageInfo message = player.getSpaceInventory().getMessages().get(req.getMessageId());
//			if(message==null){
//				ErrorMsgUtil.sendErrorMsg(player, ErrorCode.UNKNOW_ERROR, Protocol.C_REQ_SPACE_SET_COLLECTION,"无效的MEssageID"+req.getMessageId());		
//				return;
//			}
//			if(op == 1){
//				
//			}
//			
//			
//		}else if(op == 3){ 
//			
//			
//		}
		
		
	}

}
