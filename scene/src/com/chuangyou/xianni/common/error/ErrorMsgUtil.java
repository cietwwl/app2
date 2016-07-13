package com.chuangyou.xianni.common.error;

import com.chuangyou.common.protobuf.pb.ErrorRespProto.ErrorRespMsg;
import com.chuangyou.common.util.Log;
import com.chuangyou.xianni.proto.MessageUtil;
import com.chuangyou.xianni.proto.PBMessage;
import com.chuangyou.xianni.protocol.Protocol;
import com.chuangyou.xianni.world.ArmyProxy;

public class ErrorMsgUtil {

	public static void sendErrorMsg(ArmyProxy army, int errorCode, short protocolNum, String desc){
		if(army ==null)return;
		Log.error("playerId:"+army.getPlayerId()+"errorCode:"+errorCode+"protocolNum:"+protocolNum+"DESC:"+desc);
		
		ErrorRespMsg.Builder resp = ErrorRespMsg.newBuilder();
		resp.setErrorCode(errorCode);
		resp.setProtocolNum(protocolNum);
		PBMessage pkg = MessageUtil.buildMessage(Protocol.U_RESP_ERROR, resp);
		army.sendPbMessage(pkg);
	}
}
