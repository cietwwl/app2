package com.chuangyou.xianni.fashion.cmd;

import com.chuangyou.common.protobuf.pb.fashion.FashionGradeUpReqProto.FashionGradeUpReqMsg;
import com.chuangyou.common.protobuf.pb.fashion.FashionGradeUpRespProto.FashionGradeUpRespMsg;
import com.chuangyou.xianni.base.AbstractCommand;
import com.chuangyou.xianni.common.ErrorCode;
import com.chuangyou.xianni.common.error.ErrorMsgUtil;
import com.chuangyou.xianni.entity.fashion.FashionInfo;
import com.chuangyou.xianni.fashion.manager.FashionManager;
import com.chuangyou.xianni.player.GamePlayer;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.socket.Cmd;

@Cmd(code = Protocol.C_FASHION_GRADE_UP, desc = "时装进阶")
public class FashionGradeUpCmd extends AbstractCommand {

	@Override
	public void execute(GamePlayer player, PBMessage packet) throws Exception {
		// TODO Auto-generated method stub
		FashionGradeUpReqMsg req = FashionGradeUpReqMsg.parseFrom(packet.getBytes());
		int fashionId = req.getFashionId();
    	FashionInfo fashion = player.getFashionInventory().getFashion(fashionId);
		if (fashion==null) {
			ErrorMsgUtil.sendErrorMsg(player, ErrorCode.Fashion_Get_Fail, packet.getCode());
			return;
		}
    	//进阶
    	if(!FashionManager.gradeUp(fashion, player, packet.getCode())){
    		return;
    	}
    	//返回
		FashionGradeUpRespMsg.Builder msg = FashionGradeUpRespMsg.newBuilder();
		PBMessage p = MessageUtil.buildMessage(Protocol.U_FASHION_GRADE_UP, msg);
		player.sendPbMessage(p);
	}

}
